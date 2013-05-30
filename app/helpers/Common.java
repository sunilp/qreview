package helpers;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class Common {

	public static final MongoClient mongoClient  = getMongoClientInstance();
	
	private static MongoClient getMongoClientInstance() {
		try {
			if(mongoClient==null)
			return new MongoClient( "localhost" , 27017 );
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return mongoClient;
	}
	
}
