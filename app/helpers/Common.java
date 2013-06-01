package helpers;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.*;
import play.*;

public class Common {

	public static final MongoClient mongoClient  = getMongoClientInstance();
	
	private static MongoClient getMongoClientInstance() {
		try {
			if(mongoClient==null)
			{	
				String text = Play.application().configuration().getString("MONGOLAB_URI");

			MongoClientURI uri = new MongoClientURI(text);
			return new MongoClient("localhost",27017);
		     }
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return mongoClient;
	}
	
}
