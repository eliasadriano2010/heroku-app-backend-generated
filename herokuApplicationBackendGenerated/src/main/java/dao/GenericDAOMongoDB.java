package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.management.Query;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;

import br.com.caelum.vraptor.ioc.Component;

/**
 * Generic DAO for CRUD Operations
 *
 * @param <T>
 *            must be an Entity Objectify
 */
@Component
public class GenericDAOMongoDB<T> {
	private Class<T> typeParameterClass;
	private List<String> errors = new ArrayList<String>();
	private MongoDatabaseSettings mongoDatabaseSettings;

	ObjectMapper objectMapper;

	protected GenericDAOMongoDB() {
		mongoDatabaseSettings = new MongoDatabaseSettings();
		objectMapper = new ObjectMapper();
	}

	public GenericDAOMongoDB(Class<T> typeParameterClass) {
		this.typeParameterClass = typeParameterClass;
		mongoDatabaseSettings = new MongoDatabaseSettings();
		mongoDatabaseSettings.setCollection(this.typeParameterClass
				.getSimpleName());
	}

	/**
	 * return the entire list of Entity in json format
	 */
	public List<T> getEntities() {
		try {
			return MongoDBManagement.retrieveAllDocuments(
					mongoDatabaseSettings.getCollection(),
					this.typeParameterClass);
		} catch (Exception ex) {
			errors.add("An error has ocurred in method getEntities(): "
					+ ex.getCause());
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * return the entire list of Entity
	 */
	public T getEntityById(String id) {
		try {
			return MongoDBManagement.retrieveDocumentByID(
					mongoDatabaseSettings.getCollection(), typeParameterClass,
					id);
		} catch (Exception ex) {
			errors.add("An error has ocurred in method getEntityById(long id): "
					+ ex.getCause());

			ex.printStackTrace();
		}

		return null;
	}

	/**
	 * Insert one Entity Synchronous insert to get the ID
	 *
	 * @param object
	 *            entity to save
	 */
	public void insertEntity(T object) {
		try {
			
			MongoDBManagement.insertIntoCollection(
					mongoDatabaseSettings.getCollection(), object);
		} catch (Exception ex) {
			errors.add("An error has ocurred in method insertEntity(T object): "
					+ ex.getCause());
			ex.printStackTrace();
		}
	}

	/**
	 * Insert one Entity Synchronous insert to get the ID
	 *
	 * @param object
	 *            entity to save
	 */
	public void updateEntity(T object, String id) {
		try {
			MongoDBManagement.saveOrUpdate(
					mongoDatabaseSettings.getCollection(), object, id);
		} catch (Exception ex) {
			errors.add("An error has ocurred in method insertEntity(T object): "
					+ ex.getCause());
			ex.printStackTrace();
		}
	}

	public void deleteEntity(String id) {
		try {
			MongoDBManagement.deleteOneFromCollection(
					mongoDatabaseSettings.getCollection(), id);
		} catch (Exception ex) {
			errors.add("An error has ocurred in method insertEntities(List<T> objects): "
					+ ex.getCause());
			ex.printStackTrace();
		}

	}

	public Class<T> getTypeParameterClass() {
		return typeParameterClass;
	}

	public void setTypeParameterClass(Class<T> typeParameterClass) {
		this.typeParameterClass = typeParameterClass;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
