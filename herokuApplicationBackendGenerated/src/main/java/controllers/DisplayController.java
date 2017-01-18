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
@Path("/display")
public class DisplayController {

// ---------------- attributes
// ---------------------------------------------------------------

private GenericDAOMongoDB<Display> genericDAOMongoDBDisplay;
	private Result result;
	private Validator validator;
	// ---------------- Constructors
	// --------------------------------------------------------------

	// dependency injector will call this constructor and get all dependencies
	public DisplayController(Result result, Validator validator) {
		super();
		// Generic Dao could not be called using dependency injection because
		// objectify needs register class
		this.genericDAOMongoDBDisplay = new GenericDAOMongoDB<Display>(
				Display.class);
		
		this.result = result;
		this.validator = validator;
	}

	// GET /display/list => retrieve a full list (in ExtJSJson JSON format)
	// containing all entities
	@Get
	public void list() {
		try {
			result.use(ExtJSJson.class).from(genericDAOMongoDBDisplay.getEntities())
					.success().serialize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// resources - generated from  services
	// 0 - create | 1 - update | 2 - delete | 3 - retrieve 
	// POST /display/saveOrUpdate => this method receives a POST request
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
		public void create(Display display) {
			AjaxReturn ajaxReturn = new AjaxReturn();
			
			// it would be better to use DTO desgin pattern to transfer data following - by now, let this way
			
			Display displayDTO = new Display();
			if(display.getId()!=null){
					displayDTO = genericDAOMongoDBDisplay.getEntityById(display.getId());
			}
			displayDTO.setScreenResolution(display.getScreenResolution());
			displayDTO.setScreenSize(display.getScreenSize());
			displayDTO.setTouchscreen(display.getTouchscreen());
		
			try {
				List<String> errors = validation(displayDTO);
	
				if (errors.isEmpty()) {
					if (displayDTO.getId() == null) {
						displayDTO.setId(ObjectId.get().toString());
						genericDAOMongoDBDisplay.insertEntity(displayDTO);
					} else {
						genericDAOMongoDBDisplay.updateEntity(displayDTO, displayDTO.getId());
					}
				} else {
					ajaxReturn.setSucess(false);
					ajaxReturn.addErrors(errors);
				}
			} catch (Exception ex) {
				ajaxReturn.setSucess(false);
				ajaxReturn.addErrors(genericDAOMongoDBDisplay.getErrors());
			}
			result.use(ExtJSJson.class).from(ajaxReturn).serialize();
		}
	
		// This private method receives an object performs validation and returns a
		// list containing errors message
		private List<String> validation(Display display) {
			List<String> erros = new ArrayList<String>();
			return erros;
		}
	// POST /display/saveOrUpdate => this method receives a POST request
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
		public void update(Display display) {
			AjaxReturn ajaxReturn = new AjaxReturn();
			try {
				List<String> errors = validation(display);
	
				if (errors.isEmpty()) {
					genericDAOMongoDBDisplay.insertEntity(display);
					ajaxReturn.setSucess(errors.isEmpty());
				} else {
					ajaxReturn.setSucess(false);
					ajaxReturn.addErrors(errors);
				}
			} catch (Exception ex) {
				ajaxReturn.setSucess(false);
				ajaxReturn.addErrors(genericDAOMongoDBDisplay.getErrors());
			}
			result.use(ExtJSJson.class).from(ajaxReturn).serialize();
		}
	@Get
			@Post
			@Consumes("application/json")
			public void retrieve(String id) {
				AjaxReturn ajaxReturn = new AjaxReturn();
				try{
					result.use(ExtJSJson.class).from(genericDAOMongoDBDisplay.getEntityById(id))
						.serialize();
				}catch(Exception ex){
					ajaxReturn.addError(ex.getMessage());
					result.use(ExtJSJson.class).from(ajaxReturn).serialize();
				}
			}
	// POST /display/delete => this method receives a JSON request containing
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
				genericDAOMongoDBDisplay.deleteEntity(id);
				if (genericDAOMongoDBDisplay.getErrors().isEmpty()) {
					ajaxReturn.setSucess(true);
					ajaxReturn.setAlert("Deleted sucessfully!");
					result.use(ExtJSJson.class).from(ajaxReturn).serialize();
				} else {
					ajaxReturn.addErrors(genericDAOMongoDBDisplay.getErrors());
					result.use(ExtJSJson.class).from(ajaxReturn).serialize();
				}
			} catch (Exception ex) {
				ajaxReturn.addError(ex.getMessage());
				result.use(ExtJSJson.class).from(ajaxReturn).serialize();
			}
	
		}
	
	// create media methods - only generated if the entity has media
}
