package models;

import static akka.pattern.Patterns.ask;
import static java.util.concurrent.TimeUnit.SECONDS;
import helpers.Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import play.libs.Akka;
import play.libs.F.Callback;
import play.libs.F.Callback0;
import play.libs.Json;
import play.mvc.WebSocket;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * A chat room is an Actor.
 */
public class ChatRoom extends UntypedActor {
    
    // Default room.
    static ActorRef defaultRoom = Akka.system().actorOf(new Props(ChatRoom.class));
    
    static final DB db = getMongoDB();

	private static DB getMongoDB() {
		if(db==null){
			System.out.print("Mongo db is null");
			return Common.mongoClient.getDB("qreview");
		}
		System.out.print("Mongo db is not null");
		return db;
	}
	
	
    static final DBCollection chats = db.getCollection("chats");
    //static DBCollection chats;// = db.getCollection("testCollection");
    // Create a Robot, just for fun.
    static {
    	
       // new Robot(defaultRoom);
    }
    
    /**
     * Join the default room.
     */
    public static void join(final String username, WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out) throws Exception{
        
        // Send the Join message to the room
        String result = (String)Await.result(ask(defaultRoom,new Join(username, out), 1000), Duration.create(1, SECONDS));
        
        if("OK".equals(result)) {
            
            // For each event received on the socket,
            in.onMessage(new Callback<JsonNode>() {
               public void invoke(JsonNode event) {
                   
                   // Send a Talk message to the room.
                   defaultRoom.tell(new Talk(username, event.get("text").asText()));
                   
               } 
            });
            
            // When the socket is closed.
            in.onClose(new Callback0() {
               public void invoke() {
                   
                   // Send a Quit message to the room.
                   defaultRoom.tell(new Quit(username));
                   
               }
            });
            
        } else {
            
            // Cannot connect, create a Json error.
            ObjectNode error = Json.newObject();
            error.put("error", result);
            
            // Send the error to the socket.
            out.write(error);
            
        }
        
    }
    
    // Members of this room.
    Map<String, WebSocket.Out<JsonNode>> members = new HashMap<String, WebSocket.Out<JsonNode>>();
    
    public void onReceive(Object message) throws Exception {
        
        if(message instanceof Join) {
            
            // Received a Join message
            Join join = (Join)message;
            
            // Check if this username is free.
            if(members.containsKey(join.username)) {
                getSender().tell("This username is already used");
            } else {
                members.put(join.username, join.channel);
                notifyAll("join", join.username, "Online..");
                getSender().tell("OK");
                getEarlierChat(join.username);
            }
            
        } else if(message instanceof Talk)  {
            
            // Received a Talk message
            Talk talk = (Talk)message;
            
            notifyAll("talk", talk.username, talk.text);
            
            BasicDBObject talkEvent = new BasicDBObject("event", "talk").
                    append("username", talk.username).
                    append("text",  talk.text).
                    append("date", System.currentTimeMillis());
            
            
        	
    		
        	
            chats.insert(talkEvent);
            
        
            
        } else if(message instanceof Quit)  {
            
            // Received a Quit message
            Quit quit = (Quit)message;
            
            members.remove(quit.username);
            System.out.println("quit"+":"+quit.username+":"+"Offline..");
          //  notifyAll("quit", quit.username, "Offline...");
        
        } else {
            System.out.println("Unhandled message" + message);
            unhandled(message);
        }
        
    }
    
    // Send a Json event to all members
    public void notifyAll(String kind, String user, String text) {
    	System.out.println(kind+":"+user+":"+text);
        for(WebSocket.Out<JsonNode> channel: members.values()) {
            
            ObjectNode event = Json.newObject();
            event.put("kind", kind);
            event.put("user", user);
            event.put("message", text);
            
            ArrayNode m = event.putArray("members");
            for(String u: members.keySet()) {
                m.add(u);
            }
            
            channel.write(event);
        }
    }
    
    public void getEarlierChat(String username){
    	BasicDBObject param = new BasicDBObject("date",-1);
    	
    	DBCursor cursor = chats.find().addSpecial("$orderby", param).limit(10);
    	try {
    		
    		List<DBObject> dbObjectList = new ArrayList<DBObject>();
    		   while(cursor.hasNext()) {
    			   DBObject dbObject = cursor.next();
    		       System.out.println(dbObject);
    		       dbObjectList.add(dbObject);
    		   }
    		   
    		   Collections.reverse(dbObjectList);
    		   
			for (DBObject dbObject : dbObjectList) {

				ObjectNode event = Json.newObject();
				event.put("kind", String.valueOf(dbObject.get("event")));
				event.put("user", String.valueOf(dbObject.get("username")));
				event.put("message", String.valueOf(dbObject.get("text")));
				ArrayNode m = event.putArray("members");
				for (String u : members.keySet()) {
					m.add(u);
				}

				for (Entry<String, WebSocket.Out<JsonNode>> entry : members
						.entrySet()) {
					if (entry.getKey().equals(username)) {
						entry.getValue().write(event);
					}
				}

			}
    		   
    		   
    		} finally {
    		   cursor.close();
    		}
    	
    }
    
            
    // -- Messages
    
    public static class Join {
        
        final String username;
        final WebSocket.Out<JsonNode> channel;
        
        public Join(String username, WebSocket.Out<JsonNode> channel) {
            this.username = username;
            this.channel = channel;
        }
        
    }
    
    public static class Talk {
        
        final String username;
        final String text;
        
        public Talk(String username, String text) {
            this.username = username;
            this.text = text;
        }
        
    }
    
    public static class Quit {
        
        final String username;
        
        public Quit(String username) {
            this.username = username;
        }
        
    }
    
    
    
}
