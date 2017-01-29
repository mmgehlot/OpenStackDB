package cmpe283.dashboard;

import java.util.List;
import org.openstack4j.model.compute.*;
import org.openstack4j.model.compute.Image;

public class GlanceServices extends OpenStackNova {

	public List<? extends Image> getAllImages(){
		List<? extends Image> images = (List<? extends Image>) os.images().list();
		return images;
	}
	
	public Image getImageById(String imageID){
		Image image = (Image) os.images().get(imageID);
		return image;
	}

}