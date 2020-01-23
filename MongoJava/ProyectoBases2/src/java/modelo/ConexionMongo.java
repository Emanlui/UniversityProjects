/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.gridfs.*;
import com.mongodb.util.JSON;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Emanuelle
 */
public class ConexionMongo {
    
    DB db;

    public void conectar() {
        
        db = (new MongoClient("localhost",27017)).getDB("proyecto");
        
    }
    
    public void insertar(int id, String dueno, String autor,String path) throws IOException{
        
       
            if(!dueno.equals("null") && !autor.equals("null")){
                
                DBCollection channel = db.getCollection("datosImagen");
        
                channel.insert((DBObject) JSON.parse("{id :" + String.valueOf(id) +
                ", dueno: '" +String.valueOf(dueno) +"', autor: '"+ String.valueOf(autor) + "',image:'" +
                String.valueOf(path) + "'}"));
            }
            
            }
            
           
      
    
    
    
    public DBCursor obtenerDatos(String dato){
        
        DBCollection channel = db.getCollection("datosImagen");
        
        DBCursor cursor = channel.find((DBObject) JSON.parse("{id:"+ dato +"}"));
        return cursor;

    }
    
    public DBCursor obtenerTodosLosDatos(){
        DBCollection channel = db.getCollection("datosImagen");
        
        DBCursor cursor = channel.find();
        return cursor;
        
    }
               
    
    public void eliminar(int id){
        
        DBCollection coll = db.getCollection("datosImagen");
        BasicDBObject del = new BasicDBObject("id",id);
        coll.remove(del);
        
    }
    
    public String cortarString(String a){
        
        String temp;
        int tam = a.length();
        temp = a.substring(2,tam-2);
        return temp;
        
    }
    
    
    public String[] separarCollection(String s){
        
       
        
        char t = '"';
        String[] arrOfStr; 
        String temp =  Character.toString(t);
        arrOfStr = s.split(temp);
        
           return arrOfStr;
       // String[] elements=s.split("\\'");
       /*
       System.out.println(arrOfStr[6]);
       System.out.println(arrOfStr[9]);
       System.out.println(arrOfStr[13]);
*/
       //6
       //9
       //13
        
    }
    
    
    public void actualizar(int id, String autor, String dueno, String path){
        
        
        MongoClient client = new MongoClient("localhost",27017);
        MongoDatabase db = client.getDatabase("proyecto");
        MongoCollection<Document> coll = db.getCollection("datosImagen");
        Document querry = new Document();
        Document setData = new Document();
        Document actualizar = new Document();
      
        querry.append("id",id);
        setData.append("dueno",dueno).append("autor",autor).append("image",path);
        actualizar.append("$set",setData);
        coll.updateOne(querry, actualizar);
        
        
        
    }
}
