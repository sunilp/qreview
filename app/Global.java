


import helpers.Common;

import java.lang.reflect.Method;
import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import play.GlobalSettings;
import play.mvc.Action;
import play.mvc.Http.Request;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;

public class Global extends GlobalSettings {


	
	
	
	@Override
public void onStart(play.Application arg0) {
		try {
			//MongoClient mongoClient  = new MongoClient( "localhost" , 27017 );
		//	DB db = Common.mongoClient.getDB("qreview");
		//	Common.chats = db.getCollection("chats");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	


	@Override
  public Result onHandlerNotFound(RequestHeader header) {
	  System.out.println(header.uri() + " not found");
    return super.onHandlerNotFound(header);
  }  
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public Action onRequest(Request request, Method actionMethod) {
	   System.out.println("before each request..." + request.toString());
	//   System.out.println(request.headers());
	   return super.onRequest(request, actionMethod);
	}

    
}