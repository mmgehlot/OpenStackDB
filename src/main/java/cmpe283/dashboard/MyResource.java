package cmpe283.dashboard;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.openstack4j.model.identity.v3.Project;
import org.openstack4j.model.identity.v3.Role;
import org.openstack4j.model.identity.v3.User;
import org.openstack4j.model.network.NetFloatingIP;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.Subnet;
import org.openstack4j.model.storage.block.VolumeType;
import org.openstack4j.model.compute.*;
import org.openstack4j.model.compute.ext.*;

@Path("/vtDashboard")
public class MyResource {
	
	OpenStackMain keystone = new OpenStackMain();
	NovaServices nova = new NovaServices();
	Neutron neutron = new Neutron();
	GlanceServices glance = new GlanceServices();
	CinderServices cinder = new CinderServices();

    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET 
    @Produces("text/plain")
    public String getIt() {
        return "Hi there!";
    }
    
    @GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers(){
		List<? extends User> usersList = nova.getAllUsers();
		GenericEntity<List<? extends User>> entity = new GenericEntity<List<? extends User>>(usersList) {};
		return Response.status(Status.OK).entity(entity).build();
	}
    
    @GET
	@Path("/nova/flavors")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFlavors(){
    	List<? extends Flavor> flavorList = (List< ? extends Flavor>) nova.getAllFlavors();
    	GenericEntity<List<? extends Flavor>> entity = new GenericEntity<List<? extends Flavor>>(flavorList) {};
		return Response.status(Status.OK).entity(entity).build();
    }
    
    @GET
	@Path("/nova/images")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getImages(){
    	List<? extends Image> flavorList = (List< ? extends Image>) nova.getAllImages();
    	GenericEntity<List<? extends Image>> entity = new GenericEntity<List<? extends Image>>(flavorList) {};
		return Response.status(Status.OK).entity(entity).build();
    }
    
    @GET
	@Path("/nova/allkeypairs")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getKeyPairsAll(){
		List<? extends Keypair> kps = (List<? extends Keypair>) nova.getAllKeyPairs();
    	GenericEntity<List<? extends Keypair>> entity = new GenericEntity<List<? extends Keypair>>(kps) {};
		return Response.status(Status.OK).entity(entity).build();
    }
    
    @GET
    @Path("/nova/allSecGroup")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSecGroupAll(){
    	List<? extends SecGroupExtension> sg = (List<? extends SecGroupExtension>) nova.getAllSecGroups();
    	GenericEntity<List<? extends SecGroupExtension>> entity = new GenericEntity<List<? extends SecGroupExtension>>(sg){};
    	return Response.status(Status.OK).entity(entity).build();
    }

    
    ///List all networks
    @GET
    @Path("/neutron/networks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNetworks(){
    	//OpenStackNova keystone = new OpenStackNova();
    	List<? extends Network> networkList = (List< ? extends Network>) neutron.getAllNetworks();
    	GenericEntity<List<? extends Network>> entity = new GenericEntity<List<? extends Network>>(networkList) {};
		return Response.status(Status.OK).entity(entity).build();
   }
   
    
    ///List all subnets
    @GET
    @Path("/neutron/subnets")
    @Produces(MediaType.APPLICATION_JSON)
    //List all subnets
    public Response getAllSubnets(){
    	//OpenStackNova keystone = new OpenStackNova();
    	List<? extends Subnet> subnets = neutron.getAllSubnets();
    	GenericEntity<List<? extends Subnet>> entity = new GenericEntity<List<? extends Subnet>>(subnets) {};
		return Response.status(Status.OK).entity(entity).build();
   }
  ///List all floating IPs
    @GET
    @Path("/neutron/floatip")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getALLFloatingIP(){
    	//OpenStackNova keystone = new OpenStackNova();
    	List<? extends NetFloatingIP> floatIP = neutron.getALLFloatingIP();
    	GenericEntity<List<? extends NetFloatingIP>> entity = new GenericEntity<List<? extends NetFloatingIP>>(floatIP) {};
		return Response.status(Status.OK).entity(entity).build();
   }
    @GET

    @Path("/nova/allServer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getServers(){
	    List<? extends Server> servers = (List<? extends Server>) nova.getAllServers();
	    GenericEntity<List<? extends Server>> entity = new GenericEntity<List<? extends Server>>(servers){};
	    return Response.status(Status.OK).entity(entity).build();
    }

    @GET
    @Path("/nova/serverByID/{serverID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterServerByID(@PathParam("serverID") String serverID){
	    Server server = (Server) nova.getServerByID(serverID);
	    GenericEntity<Server> entity = new GenericEntity<Server>(server){};
	    return Response.status(Status.OK).entity(entity).build();
    }
    
    @GET
    @Path("/glance/getImageById/{ImageID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getImageById(@PathParam("ImageID") String imageID){
    	Image image = (Image) glance.getImageById(imageID);
    	GenericEntity<Image> entity = new GenericEntity<Image>(image){};
    	return Response.status(Status.OK).entity(entity).build();
    }

    @GET
    @Path("/glance/getAllImages/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllImages(){
    	List<? extends Image> images = (List<? extends Image>) glance.getAllImages();
    	GenericEntity<List<? extends Image>> entity = new GenericEntity<List<? extends Image>>(images){};
    	return Response.status(Status.OK).entity(entity).build();
    }
    
    @GET
    @Path("/nova/getTenantUsages")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTenantUsages(){
    	List<? extends SimpleTenantUsage> tenantUsages = (List<? extends SimpleTenantUsage>) nova.getTenantUsages();
    	GenericEntity<List<? extends SimpleTenantUsage>> entity = new GenericEntity<List<? extends SimpleTenantUsage>>(tenantUsages){};
    	return Response.status(Status.OK).entity(entity).build();
    }
    
    @GET
    @Path("/nova/getRegDomainList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRegDomainList(){
    	List<? extends DomainEntry> domains = (List<? extends DomainEntry>) nova.getRegDomainList();
    	GenericEntity<List<? extends DomainEntry>> entity = new GenericEntity<List<? extends DomainEntry>>(domains){};
    	return Response.status(Status.OK).entity(entity).build();
    }
    
    @GET
    @Path("/glance/getAllVolumes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVolumes(){
    	List<? extends VolumeType> images = (List<? extends VolumeType>) cinder.getAllVolumes();
    	GenericEntity<List<? extends VolumeType>> entity = new GenericEntity<List<? extends VolumeType>>(images){};
    	return Response.status(Status.OK).entity(entity).build();
    }
    
    @GET
    @Path("/nova/getAllRoles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoles(){
    	List<? extends Role> roles = (List<? extends Role>) nova.getAllRoles();
    	GenericEntity<List<? extends Role>> entity = new GenericEntity<List<? extends Role>>(roles){};
    	return Response.status(Status.OK).entity(entity).build();
    }
    
    @GET
    @Path("/nova/getAllProjects")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjects(){
    	List<? extends Project> projects = (List<? extends Project>) nova.getAllProjects();
    	GenericEntity<List<? extends Project>> entity = new GenericEntity<List<? extends Project>>(projects){};
    	return Response.status(Status.OK).entity(entity).build();
    }
    
//    @DELETE
//    @Path("/deleteUser/{userID}")
//    public Response deleteUserByID(@PathParam("userID") String userID){
//    	String status = "Delete successful";
//        ActionResponse response = nova.deleteUser(userID);
//    	return Response.status(Status.OK).entity(response).build();
//    }
    
}
