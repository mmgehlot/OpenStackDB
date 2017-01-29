package cmpe283.dashboard;

import java.util.List;

import org.openstack4j.model.compute.*;
import org.openstack4j.model.compute.ext.*;
import org.openstack4j.model.identity.v3.Project;
import org.openstack4j.model.identity.v3.Role;
import org.openstack4j.model.identity.v3.User;

public class NovaServices extends OpenStackMain{

	public  List<? extends User> getAllUsers()
	{
		List<? extends User> tenant = null;
		tenant= os.identity().users().list();
		System.out.println(tenant);
		return tenant;
	}
	
	public List<?extends Role> getAllRoles(){
		List<? extends Role> roles =  os.identity().roles().list();
		return roles;
	}
	
	public List<?extends Project> getAllProjects(){
		List<? extends Project> projects =  os.identity().projects().list();
		return projects;
	}
	
	public  List<? extends Flavor> getAllFlavors()
	{
		List<? extends Flavor> flavors = (List<? extends Flavor>) os.compute().flavors().list();
		return flavors;
	}
	
	public List<? extends Image> getAllImages(){
		List<? extends Image> images = (List<? extends Image>) os.compute().images().list();
		return images;
	}
	
	// Get all Keypairs the current account making the request has access to
	public List<? extends Keypair> getAllKeyPairs(){
		List<? extends Keypair> kps = (List<? extends Keypair>) os.compute().keypairs().list();
		return kps;
	}
	
	// Listing all Security Groups that the tenant has access to
	public List<? extends SecGroupExtension> getAllSecGroups(){
		List<? extends SecGroupExtension> sg = os.compute().securityGroups().list();
		return sg;
	}
	
	// Finding all Security Groups assigned to a server instance
	public List<? extends SecGroupExtension> getSecGroupByServerID(String serverId){
		List<? extends SecGroupExtension> sg = os.compute().securityGroups().listServerGroups(serverId);
		return sg;
	}
	
	// Get a Security Group by ID
	public SecGroupExtension getSecGroupByGroupID(String securityGroupId){
		SecGroupExtension group = os.compute().securityGroups().get("securityGroupId");
		return group;
	}
	
	//// Tenant Usage for All Tenants
	public List<? extends SimpleTenantUsage> getTenantUsages(){
		List<? extends SimpleTenantUsage> tenantUsages = os.compute().quotaSets().listTenantUsages();
		return tenantUsages;
	}
	
	// Tenant Usage (detailed) for specific Tenant
	public SimpleTenantUsage getTenantUsages(String tenantId){
		SimpleTenantUsage usage = os.compute().quotaSets().getTenantUsage(tenantId);			
		return usage;
	}
	
	// Lists registered DNS domains published by the DNS drivers
	public List<? extends DomainEntry> getRegDomainList(){
		List<? extends DomainEntry> domains = os.compute().floatingIPDNS().domains().list();
		return domains;
	}
	
	//Finds a unique DNS entry for a specified domain and name
	public List<? extends DNSEntry> getUniqueDNS(String domain, String name){
		List<? extends DNSEntry> entries = os.compute().floatingIPDNS()
            .entries()
            .listByName(domain, name);
		return entries;
	}
	
	//Lists DNS entries for a specified domain and IP
	public List<? extends DNSEntry> getDNSlistforIP(String domain, String IP){
		List<? extends DNSEntry> entries = os.compute().floatingIPDNS()
            .entries()
            .listByIP(domain, IP);
		return entries;
	}
	
	// Get all servers
	public List<? extends Server> getAllServers(){
		List<? extends Server> servers = os.compute().servers().list();
		return servers;
	}


	// Getting a specific server by ID
	public Server getServerByID(String serverId){
		Server server = os.compute().servers().get(serverId);
		return server;
	}
	
	// List all Servers
	public List<? extends Server> getAllServer(){
		List<? extends Server> servers = os.compute().servers().list();
		return servers;
	}

	// List server by ID
	public Server getServerById(String serverId){
		Server server = os.compute().servers().get(serverId);
		return server;
	}
	
	//Delete server by ID
	public void deleteServer(String serverId){
		os.compute().servers().delete(serverId);
	}
	
	// Reboot server
	public void RebootServer(String serverId){
		Server server = getServerById(serverId);
		os.compute().servers().reboot(server.getId(), RebootType.SOFT);
	}
	
	// Perform server activity action
	public void ServerAction(String serverId, String action){
		Server server = getServerById(serverId);
		if(action.equals("Start"))
			os.compute().servers().action(server.getId(), Action.START);
		else if(action.equals("Stop"))
			os.compute().servers().action(server.getId(), Action.STOP);
			else if(action.equals("Pause"))
				os.compute().servers().action(server.getId(), Action.PAUSE);
			else if(action.equals("UnPause"))
				os.compute().servers().action(server.getId(), Action.UNPAUSE);
				else if(action.equals("Suspend"))
					os.compute().servers().action(server.getId(), Action.SUSPEND);
					else if(action.equals("Resume"))
						os.compute().servers().action(server.getId(), Action.RESUME);
						else if(action.equals("Lock"))
							os.compute().servers().action(server.getId(), Action.LOCK);
							else if(action.equals("UnLock"))
								os.compute().servers().action(server.getId(), Action.UNLOCK);
	}
	
	// Tenant Usage (detailed) for specific Tenant
	public SimpleTenantUsage getTenantUsagesById(String tenantId){
		SimpleTenantUsage usage = os.compute().quotaSets().getTenantUsage(tenantId);			
		return usage;
	}
	
//	// Delete the user
//	public ActionResponse deleteUser(String userId){
//		String status = "Delete Successful";
//		ActionResponse response = os.identity().users().delete(userId);
//		return response;
//		
//	}
	
	public static void main (String args[]){
		NovaServices nova = new NovaServices();
		//nova.deleteUser("10362a08df534c40b20faf7ea54055f0");
	}
}
