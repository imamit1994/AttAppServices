package attapp.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import attapp.model.Representative;

@Service
public class MongoDbConnection {
	
	public void inserIntoEmplyee(Representative r){
		try{
			// Adding comments
			MongoClient client = new MongoClient("localhost",27017); //with default server and port adress
			System.out.println("Sucessfully connected with database");
			DB db = client.getDB( "employee" );
			DBCollection collection = db.getCollection("employee");
			BasicDBObject document = new BasicDBObject();
			document.put("name",r.getName());
			document.put("uuid", r.getUuid());
			document.put("supervisorname", r.getSupervisorName());
			document.put("date", r.getDate());
			collection.insert(document);
			Thread.sleep(1500);
			savepdfIntoMongoDB(db,r);
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
	private static void savepdfIntoMongoDB(DB db,Representative r) throws IOException {
        String dbFileName = r.getUuid();
        //File pdfFile = new File("c://Users/573010/Downloads/codeofbuinessconduct.pdf");
        File pdfFile = convert(r.getFiles());
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
	 public static File convert(MultipartFile file) throws IOException
	 {    
	     File convFile = new File(file.getOriginalFilename());
	     convFile.createNewFile(); 
	     FileOutputStream fos = new FileOutputStream(convFile); 
	     fos.write(file.getBytes());
	     fos.close(); 
	     return convFile;
	 }
}
