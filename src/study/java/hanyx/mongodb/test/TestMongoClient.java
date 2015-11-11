package study.java.hanyx.mongodb.test;

import java.util.Date;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

/**
 * Mongodb 简单的增删改查 演示
 * @author 韩元旭
 */
public class TestMongoClient {
	
	public static void main(String[] args) {
		insertData();
//		find();
//		findWithCondtion();
//		removeData();
//		updateData();
	}
	
	/**
	 * 测试一下插入数据
	 */
	public static void insertData() {
		//获取一个连接
		MongoClient mongo = new MongoClient("192.168.56.101", 27017);
		//获取数据库
		MongoDatabase db = mongo.getDatabase("hanyx");
		//获取集合
		MongoCollection<Document> coll = db.getCollection("users");
		
		//文档
		Document one = new Document();
		one.append("user_name", "hanyx").append("age", 23).append("sex", "M").append("create_date", new Date());
		
		//文档
		Document two = new Document();
		two.append("company_name", "AsiaInfo").append("address", "海淀区").append("ceo", "tian");
		
		//插入数据
		coll.insertOne(one);
		coll.insertOne(two);
		
		mongo.close();
	}
	
	/**
	 * 查询所有数据
	 */
	public static void find() {
		//获取一个连接
		MongoClient mongo = new MongoClient("192.168.56.101", 27017);
		//获取数据库
		MongoDatabase db = mongo.getDatabase("hanyx");
		//获取集合
		MongoCollection<Document> coll = db.getCollection("users");
		
		FindIterable<Document> result = coll.find();
		
		if (result != null) {
			for (Document doc: result) {
				System.out.println(doc.get("user_name") + " : " +doc);
			}
		}
		
		mongo.close();
	}
	
	/**
	 * 测试一下根据条件查询
	 */
	public static void findWithCondtion() {
		//获取一个连接
		MongoClient mongo = new MongoClient("192.168.56.101", 27017);
		//获取数据库
		MongoDatabase db = mongo.getDatabase("hanyx");
		//获取集合
		MongoCollection<Document> coll = db.getCollection("users");
		
		
		MongoCursor<Document> iterator = coll.find(Filters.eq("age", 23)).iterator();
		
		while (iterator.hasNext()) {
			Document doc = iterator.next();
			System.out.println(doc.get("user_name") + " : " +doc);
		}
		iterator.close();
		
		mongo.close();
	}
	
	/**
	 * 测试一下删除数据
	 */
	public static void removeData() {
		//获取一个连接
		MongoClient mongo = new MongoClient("192.168.56.101", 27017);
		//获取数据库
		MongoDatabase db = mongo.getDatabase("hanyx");
		//获取集合
		MongoCollection<Document> coll = db.getCollection("users");
		
		
		DeleteResult delResult = coll.deleteMany(Filters.eq("company_name", "AsiaInfo"));
		System.out.println(delResult);
		
		mongo.close();
	}
	
	/**
	 * 测试一下修改数据
	 */
	public static void updateData() {
		//获取一个连接
		MongoClient mongo = new MongoClient("192.168.56.101", 27017);
		//获取数据库
		MongoDatabase db = mongo.getDatabase("hanyx");
		//获取集合
		MongoCollection<Document> coll = db.getCollection("users");
		
		Document newdoc = new Document();
		newdoc.put("company_name", "AsiaInfo Beijing");
		
		UpdateResult updResult = coll.updateMany(Filters.eq("company_name", "AsiaInfo"), new Document("$set", newdoc));
		System.out.println(updResult);
		
		mongo.close();
	}
}
