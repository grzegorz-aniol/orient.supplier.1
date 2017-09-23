package supplier.gangel.org;

import com.tinkerpop.blueprints.impls.orient.OrientGraph;

public class PLocalSupplier extends SupplierBase {

	public void connect() {
//			factory = new OrientGraphFactory("remote:localhost/xdm", "root", "admin").setupPool(1,10);
//			OrientGraph graph = factory.getTx();
			graph = new OrientGraph("plocal:d:/dev/orientdb-community-2.1.4/databases/xdm");
	}
	
	public static void main(String[] args) {

		PLocalSupplier supplier = new PLocalSupplier(); 
		
		try {
			supplier.connect();
		} catch (Throwable e) {
			System.out.println(e.getMessage());
			return;
		}

		try {
			supplier.createData();
			supplier.readData();
			
		} finally {
			supplier.disconnect();
			
		}		
	}	
}
