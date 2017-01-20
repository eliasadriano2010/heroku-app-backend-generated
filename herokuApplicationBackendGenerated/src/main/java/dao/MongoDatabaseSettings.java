package dao;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDatabaseSettings {

	private MongoClientURI uri;
	private MongoClient client;
	private MongoDatabase database;
	private MongoCollection<Document> collection;

	public MongoDatabaseSettings() {
		super();
		uri = new MongoClientURI(
				"mongodb://heroku_zv9b6042:lgo339tf6fue7lat38e4hl3on8@ds117859.mlab.com:17859/heroku_zv9b6042");
		client = new MongoClient(uri);
		database = client.getDatabase(uri.getDatabase());
		collection = database.getCollection("default");
	}

	public MongoDatabaseSettings(MongoClientURI uri, MongoClient client,
			MongoDatabase database, MongoCollection<Document> collection) {
		super();
		this.uri = uri;
		this.client = client;
		this.database = database;
		this.collection = collection;
	}

	public MongoClientURI getUri() {
		return uri;
	}

	public void setUri(MongoClientURI uri) {
		this.uri = uri;
	}

	public MongoClient getClient() {
		return client;
	}

	public void setClient(MongoClient client) {
		this.client = client;
	}

	public MongoDatabase getDatabase() {
		return database;
	}

	public void setDatabase(MongoDatabase database) {
		this.database = database;
	}

	public MongoCollection<Document> getCollection() {
		return collection;
	}

	public void setCollection(MongoCollection<Document> collection) {
		this.collection = collection;
	}

	public void setCollection(String collectionName) {
		this.collection = database.getCollection(collectionName); 
	}
	
	@Override
	public String toString() {
		return "MongoDatabaseSettings [uri=" + uri + ", client=" + client
				+ ", database=" + database + ", collection=" + collection + "]";
	}
	
}
