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
@Path("/hardware")
public class HardwareController {

// ---------------- attributes
// ---------------------------------------------------------------

private GenericDAOMongoDB<Hardware> genericDAOMongoDBHardware;
	private Result result;
	private Validator validator;
	// ---------------- Constructors
	// --------------------------------------------------------------

	// dependency injector will call this constructor and get all dependencies
	public HardwareController(Result result, Validator validator) {
		super();
		// Generic Dao could not be called using dependency injection because
		// objectify needs register class
		this.genericDAOMongoDBHardware = new GenericDAOMongoDB<Hardware>(
				Hardware.class);
		
		this.result = result;
		this.validator = validator;
	}

	// GET /hardware/list => retrieve a full list (in ExtJSJson JSON format)
	// containing all entities
	@Get
	public void list() {
		try {
			result.use(ExtJSJson.class).from(genericDAOMongoDBHardware.getEntities())
					.success().serialize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// resources - generated from  services
	// 0 - create | 1 - update | 2 - delete | 3 - retrieve 
	// POST /hardware/saveOrUpdate => this method receives a POST request
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
		public void create(Hardware hardware) {
			AjaxReturn ajaxReturn = new AjaxReturn();
			
			// it would be better to use DTO desgin pattern to transfer data following - by now, let this way
			
			Hardware hardwareDTO = new Hardware();
			if(hardware.getId()!=null){
					hardwareDTO = genericDAOMongoDBHardware.getEntityById(hardware.getId());
			}
			hardwareDTO.setAccelerometer(hardware.getAccelerometer());
			hardwareDTO.setAudioJack(hardware.getAudioJack());
			hardwareDTO.setCpu(hardware.getCpu());
			hardwareDTO.setFmRadio(hardware.getFmRadio());
			hardwareDTO.setPhysicalKeyboard(hardware.getPhysicalKeyboard());
			hardwareDTO.setUsb(hardware.getUsb());
		
			try {
				List<String> errors = validation(hardwareDTO);
	
				if (errors.isEmpty()) {
					if (hardwareDTO.getId() == null) {
						hardwareDTO.setId(ObjectId.get().toString());
						genericDAOMongoDBHardware.insertEntity(hardwareDTO);
					} else {
						genericDAOMongoDBHardware.updateEntity(hardwareDTO, hardwareDTO.getId());
					}
				} else {
					ajaxReturn.setSucess(false);
					ajaxReturn.addErrors(errors);
				}
			} catch (Exception ex) {
				ajaxReturn.setSucess(false);
				ajaxReturn.addErrors(genericDAOMongoDBHardware.getErrors());
			}
			result.use(ExtJSJson.class).from(ajaxReturn).serialize();
		}
	
		// This private method receives an object performs validation and returns a
		// list containing errors message
		private List<String> validation(Hardware hardware) {
			List<String> erros = new ArrayList<String>();
			return erros;
		}
	// POST /hardware/saveOrUpdate => this method receives a POST request
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
		public void update(Hardware hardware) {
			AjaxReturn ajaxReturn = new AjaxReturn();
			try {
				List<String> errors = validation(hardware);
	
				if (errors.isEmpty()) {
					genericDAOMongoDBHardware.insertEntity(hardware);
					ajaxReturn.setSucess(errors.isEmpty());
				} else {
					ajaxReturn.setSucess(false);
					ajaxReturn.addErrors(errors);
				}
			} catch (Exception ex) {
				ajaxReturn.setSucess(false);
				ajaxReturn.addErrors(genericDAOMongoDBHardware.getErrors());
			}
			result.use(ExtJSJson.class).from(ajaxReturn).serialize();
		}
	@Get
			@Post
			@Consumes("application/json")
			public void retrieve(String id) {
				AjaxReturn ajaxReturn = new AjaxReturn();
				try{
					result.use(ExtJSJson.class).from(genericDAOMongoDBHardware.getEntityById(id))
						.serialize();
				}catch(Exception ex){
					ajaxReturn.addError(ex.getMessage());
					result.use(ExtJSJson.class).from(ajaxReturn).serialize();
				}
			}
	// POST /hardware/delete => this method receives a JSON request containing
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
				genericDAOMongoDBHardware.deleteEntity(id);
				if (genericDAOMongoDBHardware.getErrors().isEmpty()) {
					ajaxReturn.setSucess(true);
					ajaxReturn.setAlert("Deleted sucessfully!");
					result.use(ExtJSJson.class).from(ajaxReturn).serialize();
				} else {
					ajaxReturn.addErrors(genericDAOMongoDBHardware.getErrors());
					result.use(ExtJSJson.class).from(ajaxReturn).serialize();
				}
			} catch (Exception ex) {
				ajaxReturn.addError(ex.getMessage());
				result.use(ExtJSJson.class).from(ajaxReturn).serialize();
			}
	
		}
	
	// create media methods - only generated if the entity has media
}
