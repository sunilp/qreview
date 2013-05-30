package controllers;

import java.util.HashMap;
import java.util.Map;

import models.ChatRoom;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import play.libs.F.*;
import play.libs.Json;
import play.mvc.*;
import play.*;
import views.html.*;

public class SocketController extends Controller {
	
	
	public static  WebSocket.Out<JsonNode> outter;

	public static WebSocket<JsonNode> index() {
		return new WebSocket<JsonNode>() {

			// Called when the Websocket Handshake is done.
			public void onReady(WebSocket.In<JsonNode> in,
					WebSocket.Out<JsonNode> out) {

				// For each event received on the socket,
				in.onMessage(new Callback<JsonNode>() {
					public void invoke(JsonNode event) {

						// Log events to the console
						System.out.println(event);

					}
				});

				// When the socket is closed.
				in.onClose(new Callback0() {
					public void invoke() {

						System.out.println("Disconnected");

					}
				});

				outter = out;
				// Send a single 'Hello!' message
				
				  ObjectNode notifier = Json.newObject();
				  notifier.put("msg", "Hello");
				  Map<String,String> map =  new HashMap<String, String>();
				  map.put("msg", "Hello");
				out.write(notifier);

			}

		};
	}
	
	
	 /**
     * Display the chat room.
     */
    public static Result chatRoom(String username) {
        if(username == null || username.trim().equals("")) {
            flash("error", "Please choose a valid username.");
            return redirect(routes.Application.index());
        }
        return ok(chatRoom.render(username));
    }
    
    /**
     * Handle the chat websocket.
     */
    public static WebSocket<JsonNode> chat(final String username) {
        return new WebSocket<JsonNode>() {
            
            // Called when the Websocket Handshake is done.
            public void onReady(WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out){
                
                // Join the chat room.
                try { 
                    ChatRoom.join(username, in, out);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }
  
	
	

}
