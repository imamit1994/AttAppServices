package attapp.services;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Service
public class MongoDBSaveBinaryFile {
	public void savefileinmongodb(){
		try{	
			MongoClient client = new MongoClient("localhost",27017); //with default server and port adress
			System.out.println("Sucessfully connected with database");
			DB db = client.getDB( "test" );
			saveImageIntoMongoDB(db);
			client.close();
	      }catch(Exception e){
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      }
	}
	 private static void saveImageIntoMongoDB(DB db) throws IOException {
	        String dbFileName = "complianceform";
	        File pdfFile = new File("c://Users/573010/Downloads/complianceform.pdf");
	        GridFS gfsPdf = new GridFS(db, "pdf");
	        GridFSInputFile gfsFile = gfsPdf.createFile(pdfFile);
	        gfsFile.setFilename(dbFileName);
	        gfsFile.save();
	    }
	 public void retrievefilefromDb(){
			try{	
				MongoClient client = new MongoClient("localhost",27017); //with default server and port adress
				System.out.println("Sucessfully connected with database");
				DB db = client.getDB( "test" );
				getSingleImageExample(db);
				 listAllImages(db);
			        saveToFileSystem(db);      
			        //Delete images from DB
			        deleteImageFromMongoDB(db);
			         
			        //Verifying if image was deleted or not
			        getSingleImageExample(db);
				client.close();
		      }catch(Exception e){
		         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      }
		}
	 private static void getSingleImageExample(DB db) {
	        String newFileName = "complianceform";
	        GridFS gfsPdf = new GridFS(db, "pdf");
	        GridFSDBFile imageForOutput = gfsPdf.findOne(newFileName);
	        System.out.println(imageForOutput);
	    }
	 private static void listAllImages(DB db) {
	        GridFS gfsPdf = new GridFS(db, "pdf");
	        DBCursor cursor = gfsPdf.getFileList();
	        while (cursor.hasNext()) {
	            System.out.println(cursor.next());
	        }
	    }
	     
	    private static void saveToFileSystem(DB db) throws IOException {
	        String dbFileName = "complianceform";
	        GridFS gfsPdf = new GridFS(db, "pdf");
	        GridFSDBFile imageForOutput = gfsPdf.findOne(dbFileName);
	        imageForOutput.writeTo("D:/complianceform1.pdf");
	    }
	     
	    private static void deleteImageFromMongoDB(DB db) {
	        String dbFileName = "complianceform";
	        GridFS gfsPdf = new GridFS(db, "pdf");
	        gfsPdf.remove(gfsPdf.findOne(dbFileName));
	    }
}
