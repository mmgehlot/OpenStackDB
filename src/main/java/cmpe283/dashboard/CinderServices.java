package cmpe283.dashboard;

import java.util.List;
import org.openstack4j.model.storage.block.VolumeType;

public class CinderServices extends OpenStackMain{
	
	public List<? extends VolumeType> getAllVolumes(){
		List<? extends VolumeType> volumes = os.blockStorage().volumes().listVolumeTypes();
		return volumes;
	}
	
	public static void main (String args[]){
		CinderServices cinder = new CinderServices();
		List<? extends VolumeType> listOfVolumes = cinder.getAllVolumes();
		System.out.println(listOfVolumes);
	}

}
