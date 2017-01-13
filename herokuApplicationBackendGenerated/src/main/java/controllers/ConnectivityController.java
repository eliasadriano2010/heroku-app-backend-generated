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
@Path("/connectivity")
public class ConnectivityController {

// ---------------- attributes
// ---------------------------------------------------------------

private GenericDAOMongoDB<Connectivity> genericDAOMongoDBConnectivity;
	private Result result;
	private Validator validator;
	// ---------------- Constructors
	// --------------------------------------------------------------

	// dependency injector will call this constructor and get all dependencies
	public ConnectivityController(Result result, Validator validator) {
		super();
		// Generic Dao could not be called using dependency injection because
		// objectify needs register class
		this.genericDAOMongoDBConnectivity = new GenericDAOMongoDB<Connectivity>(
				Connectivity.class);
		
		this.result = result;
		this.validator = validator;
	}

	// GET /connectivity/list => retrieve a full list (in ExtJSJson JSON format)
	// containing all entities
	@Get
	public void list() {
		try {
			result.use(ExtJSJson.class).from(genericDAOMongoDBConnectivity.getEntities())
					.success().serialize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// resources - generated from  services
	// 0 - create | 1 - update | 2 - delete | 3 - retrieve 
	// POST /connectivity/saveOrUpdate => this method receives a POST request
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
		public void create(Connectivity connectivity) {
			AjaxReturn ajaxReturn = new AjaxReturn();
			
			// it would be better to use DTO desgin pattern to transfer data following - by now, let this way
			
			Connectivity connectivityDTO = new Connectivity();
			if(connectivity.getId()!=null){
					connectivityDTO = genericDAOMongoDBConnectivity.getEntityById(connectivity.getId());
			}
			
			connectivityDTO.setBluetooth(connectivity.getBluetooth());
			connectivityDTO.setCell(connectivity.getCell());
			connectivityDTO.setGps(connectivity.getGps());
			connectivityDTO.setInfrared(connectivity.getInfrared());
			connectivityDTO.setWifi(connectivity.getWifi());
			try {
				List<String> errors = validation(connectivityDTO);
	
				if (errors.isEmpty()) {
					genericDAOMongoDBConnectivity.insertEntity(connectivityDTO);
					ajaxReturn.setSucess(errors.isEmpty());
				} else {
					ajaxReturn.setSucess(false);
					ajaxReturn.addErrors(errors);
				}
			} catch (Exception ex) {
				ajaxReturn.setSucess(false);
				ajaxReturn.addErrors(genericDAOMongoDBConnectivity.getErrors());
			}
			result.use(ExtJSJson.class).from(ajaxReturn).serialize();
		}
	
		// This private method receives an object performs validation and returns a
		// list containing errors message
		private List<String> validation(Connectivity connectivity) {
			List<String> erros = new ArrayList<String>();
			return erros;
		}
	// POST /connectivity/saveOrUpdate => this method receives a POST request
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
		public void update(Connectivity connectivity) {
			AjaxReturn ajaxReturn = new AjaxReturn();
			try {
				List<String> errors = validation(connectivity);
	
				if (errors.isEmpty()) {
					genericDAOMongoDBConnectivity.insertEntity(connectivity);
					ajaxReturn.setSucess(errors.isEmpty());
				} else {
					ajaxReturn.setSucess(false);
					ajaxReturn.addErrors(errors);
				}
			} catch (Exception ex) {
				ajaxReturn.setSucess(false);
				ajaxReturn.addErrors(genericDAOMongoDBConnectivity.getErrors());
			}
			result.use(ExtJSJson.class).from(ajaxReturn).serialize();
		}
	@Get
			@Post
			@Consumes("application/json")
			public void retrieve(String id) {
				AjaxReturn ajaxReturn = new AjaxReturn();
				try{
					result.use(ExtJSJson.class).from(genericDAOMongoDBConnectivity.getEntityById(id))
						.serialize();
				}catch(Exception ex){
					ajaxReturn.addError(ex.getMessage());
					result.use(ExtJSJson.class).from(ajaxReturn).serialize();
				}
			}
	// POST /connectivity/delete => this method receives a JSON request containing
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
				genericDAOMongoDBConnectivity.deleteEntity(id);
				if (genericDAOMongoDBConnectivity.getErrors().isEmpty()) {
					ajaxReturn.setSucess(true);
					ajaxReturn.setAlert("Deleted sucessfully!");
					result.use(ExtJSJson.class).from(ajaxReturn).serialize();
				} else {
					ajaxReturn.addErrors(genericDAOMongoDBConnectivity.getErrors());
					result.use(ExtJSJson.class).from(ajaxReturn).serialize();
				}
			} catch (Exception ex) {
				ajaxReturn.addError(ex.getMessage());
				result.use(ExtJSJson.class).from(ajaxReturn).serialize();
			}
	
		}
	
	// create media methods - only generated if the entity has media
}
