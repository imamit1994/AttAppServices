package attapp.services;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Service
public class MongoDbConnection {
	
	public void inserIntoEmplyee(String name,String uuid, String supervisorName,String date){
		try{
			
			MongoClient client = new MongoClient("localhost",27017); //with default server and port adress
			System.out.println("Sucessfully connected with database");
			DB db = client.getDB( "employee" );
			DBCollection collection = db.getCollection("employee");
			BasicDBObject document = new BasicDBObject();
			document.put("name",name);
			document.put("uuid", uuid);
			document.put("supervisorname", supervisorName);
			document.put("date", date);
			collection.insert(document);
			Thread.sleep(1000);
			savepdfIntoMongoDB(db,uuid);
			client.close();
	      }catch(Exception e){
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      }
	}
	public void mongooperation(String uuid) throws IOException{
		MongoClient client = new MongoClient("localhost",27017); //with default server and port adress
		System.out.println("Sucessfully connected with database");
		DB db = client.getDB( "employee" );
		//show the database entry
		getSinglepdf(db,uuid);
		saveToFileSystem(db,uuid);
		deletepdfFromMongoDB(db,uuid);
		getSinglepdf(db,uuid);
		client.close();
	}
	private static void savepdfIntoMongoDB(DB db,String uuid) throws IOException {
        String dbFileName = uuid;
        File pdfFile = new File("c://Users/573010/Downloads/codeofbuinessconduct.pdf");
        GridFS gfsPdf = new GridFS(db, "pdf");
        GridFSInputFile gfsFile = gfsPdf.createFile(pdfFile);
        gfsFile.setFilename(dbFileName);
        gfsFile.save();
        System.out.println("pdf sucessfully saved in MongoDb");
    }
	private static void getSinglepdf(DB db,String uuid) {
        String newFileName =uuid;
        GridFS gfsPdf = new GridFS(db, "pdf");
        GridFSDBFile imageForOutput = gfsPdf.findOne(newFileName);
        System.out.println(imageForOutput);
    }
	private static void saveToFileSystem(DB db,String uuid) throws IOException {
        String dbFileName = uuid;
        GridFS gfsPdf = new GridFS(db, "pdf");
        GridFSDBFile imageForOutput = gfsPdf.findOne(dbFileName);
        imageForOutput.writeTo("D:/codeofbuinessconduct1.pdf");
    }
	 private static void deletepdfFromMongoDB(DB db,String uuid) {
	        String dbFileName = uuid;
	        GridFS gfsPdf = new GridFS(db, "pdf");
	        gfsPdf.remove(gfsPdf.findOne(dbFileName));
	    }
}
