package study.java.hanyx.mongodb.util;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;

/**
 * MongoDB 工具类
 * 
 * 	MongoClient包含线程池,故设置为单例
 * @author 韩元旭
 */
public class MongoDBUtil {

	private MongoDBUtil() {
	}

	private static MongoClient instance = null;

	public static MongoClient getInstance() {
		if (instance == null) {
			instance = new MongoClient("192.168.56.101", 27017);
		}
		return instance;
	}

	/**
	 * 获取指定数据库,不存在会创建新的数据库
	 * 
	 * @param dbName
	 *            数据库名称
	 * @return
	 */
	public MongoDatabase getDB(String dbName) {
		if (notEmptyString(dbName)) {
			return instance.getDatabase(dbName);
		}
		return null;
	}

	/**
	 * 获取指定数据库中的集合,不存在会创建新的
	 * @param dbName
	 * @param collName
	 * @return
	 */
	public MongoCollection<Document> getCollection(String dbName, String collName) {
		if (notEmptyString(dbName) && notEmptyString(collName)) {
			return instance.getDatabase(dbName).getCollection(collName);
		}
		return null;
	}

	/**
	 * 获取所有数据库名称列表
	 * @return
	 */
	public List<String> getAllDBNames() {
		List<String> list = new ArrayList<String>();
		MongoIterable<String> dbNames = instance.listDatabaseNames();
		for (String s : dbNames) {
			list.add(s);
		}
		return list;
	}
    
	/**
	 * 获取指定数据库下所有集合名称
	 * @param dbName
	 * @return
	 */
	public List<String> getAllCollections(String dbName) {
		List<String> list = new ArrayList<String>();
		if (notEmptyString(dbName)) {
			MongoIterable<String> colls = getDB(dbName).listCollectionNames();
			for (String s : colls) {
				list.add(s);
				}
		}
		return list;
    }
	
	/**
	 * 根据主键Id查找数据
	 * @param coll
	 * @param id
	 * @return
	 */
	public Document findById(MongoCollection<Document> coll, String id) {
		ObjectId objId = null;
		try {
			objId = new ObjectId(id);
		} catch (Exception e) {
			return null;
		}
		return coll.find(Filters.eq("_id", objId)).first();
	}
	
	/**
	 * 根据条件查询
	 * @param coll
	 * @param filter
	 * @return
	 */
	public MongoCursor<Document> find(MongoCollection<Document> coll, Bson filter) {
    	return coll.find(filter).iterator();
    }
	
	/**
	 * 根据主键Id升序排列分页查询
	 * @param coll
	 * @param pageNo 页码
	 * @param pageSize 页容量
	 * @return
	 */
	public MongoCursor<Document> findByPage(MongoCollection<Document> coll, int pageNo, int pageSize) {
		return findByPage(coll, "_id", pageNo, pageSize, true);
    }
	
	/**
	 * 根据主键Id分页查询
	 * @param coll
	 * @param pageNo 页码
	 * @param pageSize 页容量
	 * @param isAsc 是否升序排列
	 * @return
	 */
	public MongoCursor<Document> findByPage(MongoCollection<Document> coll, int pageNo, int pageSize, boolean isAsc) {
		return findByPage(coll, "_id", pageNo, pageSize, isAsc);
    }
	
	/**
	 * 分页查询
	 * @param coll
	 * @param key 排序字段名
	 * @param pageNo 页码
	 * @param pageSize 页容量
	 * @param isAsc 是否升序排列
	 * @return
	 */
	public MongoCursor<Document> findByPage(MongoCollection<Document> coll, String key, int pageNo, int pageSize, boolean isAsc) {
		key = notEmptyString(key)?key:"_id";
		Bson orderBy = new BasicDBObject(key, isAsc?1:-1);
		return coll.find().sort(orderBy).skip((pageNo - 1) * pageSize).limit(pageSize).iterator();
    }
	
	/**
	 * 根据主键Id删除数据
	 * @param coll
	 * @param id
	 * @return
	 */
	public long deleteById(MongoCollection<Document> coll, String id) {
		ObjectId objId = null;
		try {
			objId = new ObjectId(id);
		} catch (Exception e) {
			return 0L;
		}
		return coll.deleteOne(Filters.eq("_id", objId)).getDeletedCount();
	}
	
	/**
	 * 根据主键Id修改数据
	 * @param coll
	 * @param id
	 * @param newdoc
	 * @return
	 */
	public long updateById(MongoCollection<Document> coll, String id, Document newdoc) {
		ObjectId objId = null;
		try {
			objId = new ObjectId(id);
		} catch (Exception e) {
			return 0L;
		}
		return coll.updateOne(Filters.eq("_id", objId), new Document("$set", newdoc)).getModifiedCount();
	}

	/**
	 * 关闭连接
	 */
	public void close() {
		if (instance != null) {
			instance.close();
			instance = null;
		}
	}
	
	/**
	 * 非空字符串的判断
	 * @param str
	 * @return
	 */
	private boolean notEmptyString(String str) {
		return !(str == null || "".equals(str));
	}
}
