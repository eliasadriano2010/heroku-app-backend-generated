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
@Path("/gadget")
public class GadgetController {

// ---------------- attributes
// ---------------------------------------------------------------

private GenericDAOMongoDB<Gadget> genericDAOMongoDBGadget;
private GenericDAOMongoDB<Android> genericDAOMongoDBAndroid;
private GenericDAOMongoDB<Battery> genericDAOMongoDBBattery;
private GenericDAOMongoDB<Camera> genericDAOMongoDBCamera;
private GenericDAOMongoDB<Connectivity> genericDAOMongoDBConnectivity;
private GenericDAOMongoDB<Display> genericDAOMongoDBDisplay;
private GenericDAOMongoDB<Hardware> genericDAOMongoDBHardware;
private GenericDAOMongoDB<Storage> genericDAOMongoDBStorage;
	private Result result;
	private Validator validator;
	// ---------------- Constructors
	// --------------------------------------------------------------

	// dependency injector will call this constructor and get all dependencies
	public GadgetController(Result result, Validator validator) {
		super();
		// Generic Dao could not be called using dependency injection because
		// objectify needs register class
		this.genericDAOMongoDBGadget = new GenericDAOMongoDB<Gadget>(
				Gadget.class);
		this.genericDAOMongoDBAndroid = new GenericDAOMongoDB<Android>(
		Android.class);
		this.genericDAOMongoDBBattery = new GenericDAOMongoDB<Battery>(
		Battery.class);
		this.genericDAOMongoDBCamera = new GenericDAOMongoDB<Camera>(
		Camera.class);
		this.genericDAOMongoDBConnectivity = new GenericDAOMongoDB<Connectivity>(
		Connectivity.class);
		this.genericDAOMongoDBDisplay = new GenericDAOMongoDB<Display>(
		Display.class);
		this.genericDAOMongoDBHardware = new GenericDAOMongoDB<Hardware>(
		Hardware.class);
		this.genericDAOMongoDBStorage = new GenericDAOMongoDB<Storage>(
		Storage.class);
		
		this.result = result;
		this.validator = validator;
	}

	// GET /gadget/list => retrieve a full list (in ExtJSJson JSON format)
	// containing all entities
	@Get
	public void list() {
		try {
			result.use(ExtJSJson.class).from(genericDAOMongoDBGadget.getEntities())
					.success().serialize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// resources - generated from  services
	// 0 - create | 1 - update | 2 - delete | 3 - retrieve 
	// POST /gadget/saveOrUpdate => this method receives a POST request
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
		public void create(Gadget gadget) {
			AjaxReturn ajaxReturn = new AjaxReturn();
			
			// it would be better to use DTO desgin pattern to transfer data following - by now, let this way
			
			Gadget gadgetDTO = new Gadget();
			if(gadget.getId()!=null){
					gadgetDTO = genericDAOMongoDBGadget.getEntityById(gadget.getId());
			}
			
			gadgetDTO.setAdditionalFeatures(gadget.getAdditionalFeatures());
			gadgetDTO.setAndroid(genericDAOMongoDBAndroid.getEntityById(gadget.getAndroid().getId()));
			gadgetDTO.setAvailability(gadget.getAvailability());
			gadgetDTO.setBattery(genericDAOMongoDBBattery.getEntityById(gadget.getBattery().getId()));
			gadgetDTO.setCamera(genericDAOMongoDBCamera.getEntityById(gadget.getCamera().getId()));
			gadgetDTO.setConnectivity(genericDAOMongoDBConnectivity.getEntityById(gadget.getConnectivity().getId()));
			gadgetDTO.setDescription(gadget.getDescription());
			gadgetDTO.setDisplay(genericDAOMongoDBDisplay.getEntityById(gadget.getDisplay().getId()));
			gadgetDTO.setHardware(genericDAOMongoDBHardware.getEntityById(gadget.getHardware().getId()));
			gadgetDTO.setImages(gadget.getImages());
			gadgetDTO.setName(gadget.getName());
			gadgetDTO.setHeight(gadget.getHeight());
			gadgetDTO.setWidth(gadget.getWidth());
			gadgetDTO.setLenght(gadget.getLenght());
			gadgetDTO.setWeight(gadget.getWeight());
			gadgetDTO.setStorage(genericDAOMongoDBStorage.getEntityById(gadget.getStorage().getId()));
			gadgetDTO.setSnippet(gadget.getSnippet());
			try {
				List<String> errors = validation(gadgetDTO);
	
				if (errors.isEmpty()) {
					genericDAOMongoDBGadget.insertEntity(gadgetDTO);
					ajaxReturn.setSucess(errors.isEmpty());
				} else {
					ajaxReturn.setSucess(false);
					ajaxReturn.addErrors(errors);
				}
			} catch (Exception ex) {
				ajaxReturn.setSucess(false);
				ajaxReturn.addErrors(genericDAOMongoDBGadget.getErrors());
			}
			result.use(ExtJSJson.class).from(ajaxReturn).serialize();
		}
	
		// This private method receives an object performs validation and returns a
		// list containing errors message
		private List<String> validation(Gadget gadget) {
			List<String> erros = new ArrayList<String>();
			return erros;
		}
	// POST /gadget/saveOrUpdate => this method receives a POST request
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
		public void update(Gadget gadget) {
			AjaxReturn ajaxReturn = new AjaxReturn();
			try {
				List<String> errors = validation(gadget);
	
				if (errors.isEmpty()) {
					genericDAOMongoDBGadget.insertEntity(gadget);
					ajaxReturn.setSucess(errors.isEmpty());
				} else {
					ajaxReturn.setSucess(false);
					ajaxReturn.addErrors(errors);
				}
			} catch (Exception ex) {
				ajaxReturn.setSucess(false);
				ajaxReturn.addErrors(genericDAOMongoDBGadget.getErrors());
			}
			result.use(ExtJSJson.class).from(ajaxReturn).serialize();
		}
	@Get
			@Post
			@Consumes("application/json")
			public void retrieve(String id) {
				AjaxReturn ajaxReturn = new AjaxReturn();
				try{
					result.use(ExtJSJson.class).from(genericDAOMongoDBGadget.getEntityById(id))
						.serialize();
				}catch(Exception ex){
					ajaxReturn.addError(ex.getMessage());
					result.use(ExtJSJson.class).from(ajaxReturn).serialize();
				}
			}
	// POST /gadget/delete => this method receives a JSON request containing
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
				genericDAOMongoDBGadget.deleteEntity(id);
				if (genericDAOMongoDBGadget.getErrors().isEmpty()) {
					ajaxReturn.setSucess(true);
					ajaxReturn.setAlert("Deleted sucessfully!");
					result.use(ExtJSJson.class).from(ajaxReturn).serialize();
				} else {
					ajaxReturn.addErrors(genericDAOMongoDBGadget.getErrors());
					result.use(ExtJSJson.class).from(ajaxReturn).serialize();
				}
			} catch (Exception ex) {
				ajaxReturn.addError(ex.getMessage());
				result.use(ExtJSJson.class).from(ajaxReturn).serialize();
			}
	
		}
	
	// create media methods - only generated if the entity has media
	@Post
			public void uploadImagesMedia(UploadedFile file, String idGadget) throws IOException {
				AjaxReturn ajaxReturn = new AjaxReturn();
				
				try{
					Gadget gadget = genericDAOMongoDBGadget.getEntityById(idGadget);	
					Media media = new Media ();
					
					media.setMediaType(file.getContentType());
					media.setMediaName(file.getFileName());
					
					media.setMediaData(new String(Base64.encodeBase64(ByteStreams.toByteArray(file.getFile()))));
					
					gadget.setImages(media);
					
					genericDAOMongoDBGadget.updateEntity(gadget, idGadget);
					
					if (genericDAOMongoDBGadget.getErrors().isEmpty()) {
						ajaxReturn.setSucess(true);
						ajaxReturn.setAlert("uploaded sucessfully!");
						result.use(ExtJSJson.class).from(ajaxReturn).serialize();
					} else {
						ajaxReturn.addErrors(genericDAOMongoDBGadget.getErrors());
						result.use(ExtJSJson.class).from(ajaxReturn).serialize();
					}
					
				}catch (Exception ex) {
					ajaxReturn.addError(ex.getMessage());
					result.use(ExtJSJson.class).from(ajaxReturn).serialize();
				}
			}
			
			@Post
			@Consumes("application/json")
			public void getImages(String idGadget){
					//if object has images, send them to front-end, else, do nothing
				
				try{
						 Gadget gadget =  genericDAOMongoDBGadget.getEntityById(idGadget);
						 
						List<Media> media = gadget.getImages();
						
						 if(genericDAOMongoDBGadget.getErrors().isEmpty()){
							 	result.use(ExtJSJson.class).from(media).serialize();
						 }else {
								result.use(ExtJSJson.class).from(genericDAOMongoDBGadget.getErrors()).serialize();
						 }
					}catch (Exception ex) {
						
					}
			}
}
