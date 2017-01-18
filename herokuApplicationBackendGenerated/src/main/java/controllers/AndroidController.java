package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.*;

//model imports

import org.apache.tomcat.util.codec.binary.Base64;
import org.bson.types.ObjectId;

import util.AjaxReturn;
import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.util.extjs.ExtJSJson;

import com.google.common.io.ByteStreams;

import dao.GenericDAOMongoDB;

@Resource
@Path("/android")
public class AndroidController {

	// ---------------- attributes
	// ---------------------------------------------------------------

	private GenericDAOMongoDB<Android> genericDAOMongoDBAndroid;
	private Result result;
	private Validator validator;

	// ---------------- Constructors
	// --------------------------------------------------------------

	// dependency injector will call this constructor and get all dependencies
	public AndroidController(Result result, Validator validator) {
		super();
		// Generic Dao could not be called using dependency injection because
		// objectify needs register class
		this.genericDAOMongoDBAndroid = new GenericDAOMongoDB<Android>(
				Android.class);

		this.result = result;
		this.validator = validator;
	}

	// GET /android/list => retrieve a full list (in ExtJSJson JSON format)
	// containing all entities
	@Get
	public void list() {
		try {
			result.use(ExtJSJson.class)
					.from(genericDAOMongoDBAndroid.getEntities()).success()
					.serialize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// resources - generated from services
	// 0 - create | 1 - update | 2 - delete | 3 - retrieve
	// POST /android/saveOrUpdate => this method receives a POST request
	// containing data in JSON and persists related entity
	// accepted format:

	// var data = {
	// android : {
	// "id" : $scope.vm.id,
	// "os" : $scope.vm.os != undefined ? $scope.vm.os:'',
	// "ui" : $scope.vm.ui != undefined ? $scope.vm.ui : ''
	// }
	// }

	@Post
	@Consumes("application/json")
	public void create(Android android) {
		AjaxReturn ajaxReturn = new AjaxReturn();

		// it would be better to use DTO desgin pattern to transfer data
		// following - by now, let this way

		Android androidDTO = new Android();
		if (android.getId() != null) {
			androidDTO = genericDAOMongoDBAndroid
					.getEntityById(android.getId());
		}
		androidDTO.setOs(android.getOs());
		androidDTO.setUi(android.getUi());

		try {
			List<String> errors = validation(androidDTO);

			if (errors.isEmpty()) {
				if (androidDTO.getId() == null) {
					androidDTO.setId(ObjectId.get().toString());
					genericDAOMongoDBAndroid.insertEntity(androidDTO);
				} else {
					genericDAOMongoDBAndroid.updateEntity(androidDTO,
							androidDTO.getId());
				}
			} else {
				ajaxReturn.setSucess(false);
				ajaxReturn.addErrors(errors);
			}
		} catch (Exception ex) {
			ajaxReturn.setSucess(false);
			ajaxReturn.addErrors(genericDAOMongoDBAndroid.getErrors());
		}
		result.use(ExtJSJson.class).from(ajaxReturn).serialize();
	}

	// This private method receives an object performs validation and returns a
	// list containing errors message
	private List<String> validation(Android android) {
		List<String> erros = new ArrayList<String>();
		return erros;
	}

	// POST /android/saveOrUpdate => this method receives a POST request
	// containing data in JSON and persists related entity
	// accepted format:

	// var data = {
	// android : {
	// "id" : $scope.vm.id,
	// "os" : $scope.vm.os != undefined ? $scope.vm.os:'',
	// "ui" : $scope.vm.ui != undefined ? $scope.vm.ui : ''
	// }
	// }

	@Post
	@Consumes("application/json")
	public void update(Android android) {
		AjaxReturn ajaxReturn = new AjaxReturn();
		try {
			List<String> errors = validation(android);

			if (errors.isEmpty()) {
				genericDAOMongoDBAndroid.insertEntity(android);
				ajaxReturn.setSucess(errors.isEmpty());
			} else {
				ajaxReturn.setSucess(false);
				ajaxReturn.addErrors(errors);
			}
		} catch (Exception ex) {
			ajaxReturn.setSucess(false);
			ajaxReturn.addErrors(genericDAOMongoDBAndroid.getErrors());
		}
		result.use(ExtJSJson.class).from(ajaxReturn).serialize();
	}

	@Get
	@Post
	@Consumes("application/json")
	public void retrieve(String id) {
		AjaxReturn ajaxReturn = new AjaxReturn();
		try {
			result.use(ExtJSJson.class)
					.from(genericDAOMongoDBAndroid.getEntityById(id))
					.serialize();
		} catch (Exception ex) {
			ajaxReturn.addError(ex.getMessage());
			result.use(ExtJSJson.class).from(ajaxReturn).serialize();
		}
	}

	// POST /android/delete => this method receives a JSON request containing
	// id as parameter, deleted related entity and
	// answers if the entitiy was deleted successfully.
	// accpted format:
	// var data = {
	// "id" : value
	// }
	@Post
	@Consumes("application/json")
	public void delete(String id) {
		AjaxReturn ajaxReturn = new AjaxReturn();
		try {
			genericDAOMongoDBAndroid.deleteEntity(id);
			if (genericDAOMongoDBAndroid.getErrors().isEmpty()) {
				ajaxReturn.setSucess(true);
				ajaxReturn.setAlert("Deleted sucessfully!");
				result.use(ExtJSJson.class).from(ajaxReturn).serialize();
			} else {
				ajaxReturn.addErrors(genericDAOMongoDBAndroid.getErrors());
				result.use(ExtJSJson.class).from(ajaxReturn).serialize();
			}
		} catch (Exception ex) {
			ajaxReturn.addError(ex.getMessage());
			result.use(ExtJSJson.class).from(ajaxReturn).serialize();
		}

	}

	// create media methods - only generated if the entity has media
}
