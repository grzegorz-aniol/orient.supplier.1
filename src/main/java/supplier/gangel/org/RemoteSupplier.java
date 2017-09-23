package supplier.gangel.org;

import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;

public class RemoteSupplier extends SupplierBase {

	public void connect() {
		factory = new OrientGraphFactory("remote:localhost/xdm", "root", "admin").setupPool(1,10);
		graph = factory.getTx();
	}

	public static void main(String[] args) {

		RemoteSupplier supplier = new RemoteSupplier(); 
		
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
