package acquisition;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Created by samskim on 5/9/16.
 */
public class Dumper {

    MongoClient mongoClient;
    MongoDatabase db;
    MongoCollection collection;

    public Dumper(){
        mongoClient = new MongoClient();
        db = mongoClient.getDatabase("election-2016");

    }

    public void getYesterday(){

    }



}
