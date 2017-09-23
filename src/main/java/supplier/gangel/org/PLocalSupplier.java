package supplier.gangel.org;

import com.tinkerpop.blueprints.impls.orient.OrientGraph;

public class PLocalSupplier extends SupplierBase {

	public void connect() {
			graph = new OrientGraph("plocal:d:/dev/orientdb-community-2.2.27/databases/xdm");
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
