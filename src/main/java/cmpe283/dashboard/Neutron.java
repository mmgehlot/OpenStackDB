package cmpe283.dashboard;

import java.util.List;

import org.openstack4j.model.network.NetFloatingIP;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.Subnet;



public class Neutron extends OpenStackNova {

	// network list 
	public List<? extends Network> getAllNetworks(){
		List<? extends Network> networks = os.networking().network().list();
		return networks;
		}
	
	//subnet list 
	public List<? extends Subnet> getAllSubnets(){
		List<? extends Subnet> subnets = os.networking().subnet().list();
		return subnets;
		}
	
	//floating IP
	public List<? extends NetFloatingIP> getALLFloatingIP(){
		List<? extends NetFloatingIP> floatIP = os.networking().floatingip().list();
		return floatIP;
	}


}