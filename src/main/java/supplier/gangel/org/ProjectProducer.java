package supplier.gangel.org;

import java.util.UUID;

public class ProjectProducer {

	public static DataContainer generateProject() {
		DataContainer p = new DataContainer();
		p.put("name", "Project_" + UUID.randomUUID().toString().substring(1, 10));
		p.put("created_by", "ganiol");
		return p;
	}
	
	public static DataContainer generateFolder(String type) {
		DataContainer c = new DataContainer();
		c.put("name", "Folder_" + UUID.randomUUID().toString().substring(1, 10));
		c.put("data_type", type);
		c.put("version", "0.00");
		c.put("created_by", "ganiol");
		c.put("template_indicator", new Integer(0));
		c.put("tree_level", 0);
		return c;
	}
	
	public static DataContainer generateFile(String type, long version) {
		DataContainer c = new DataContainer();
		c.put("name", "Folder_" + UUID.randomUUID().toString().substring(1, 10));
		c.put("data_type", type);
		c.put("version", String.format("%d.%02d", (int)(version/100), version % 100));
		c.put("major_version", new Integer((int) (version / 100)));
		c.put("minor_version", version % 100);
		c.put("created_by", "ganiol");
		c.put("template_indicator", new Integer(0));
		c.put("tree_level", 0);
		return c;
	}
	
}
