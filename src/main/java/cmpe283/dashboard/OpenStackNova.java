package cmpe283.dashboard;

import org.openstack4j.openstack.OSFactory;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.Identifier;

public class OpenStackNova {
	
	protected static OSClientV3 os = null;
	
	public  OpenStackNova(){
		Identifier domain = Identifier.byName("default");
		Identifier project = Identifier.byName("admin");
		
		try {
		os = OSFactory.builderV3()
		                       .endpoint("http://127.0.0.1:5000/v3")
		                       .credentials("admin","admin_user_secret", domain)
		                       .scopeToProject(project, domain)
		                       .authenticate();
		}
		catch (Exception e)
		{
			System.out.println(e);
			System.out.println("1"+ System.err);
		}
	}
}