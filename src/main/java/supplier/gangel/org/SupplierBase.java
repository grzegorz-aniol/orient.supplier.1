package supplier.gangel.org;

import java.util.Iterator;
import java.util.LinkedList;

import org.gangel.ProjectProducer;
import org.testng.Assert;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;

public abstract class SupplierBase {

	protected OrientGraphFactory factory = null;
	protected OrientGraph graph = null;
	private LinkedList<Object> fileIDs = new LinkedList<>();

	public abstract void connect();
	
	public void disconnect() {
		if (graph != null) {
			graph.shutdown();
		}
	}
	
	public void createData() {
	
		System.out.println("Create data...");
		
		long nVertexes = 0;
		long nEges = 0;
		long nTransactions = 0;
		
		long nStartTime = System.nanoTime();
		long totalTime = 0;
		
		
			try{
				
				for (int v=0; v<500; ++v) {
					
				  long x1 = System.nanoTime();				  
				  Vertex project = graph.addVertex("class:Project", ProjectProducer.generateProject()); // 1st OPERATION: IMPLICITLY BEGIN A TRANSACTION				  
				  graph.commit();				  
				  totalTime += (System.nanoTime() - x1);
				  
				  ++nVertexes;
				  ++nTransactions;
				  
				  for (int i=0; i < 10; ++i) {
					  long x2 = System.nanoTime();
					  Vertex file1 = graph.addVertex("class:File", ProjectProducer.generateFile("File", i));
					  graph.commit();
					  totalTime += (System.nanoTime() - x2);
					  ++nVertexes;
					  ++nTransactions;
					  
					  fileIDs.add(file1.getId());
					  
					  long x3 = System.nanoTime();
					  graph.addEdge(null, project, file1, "contain");
					  graph.commit();
					  totalTime += (System.nanoTime() - x3);
					  ++nEges;
					  ++nTransactions;
				  }
				}
				  
			} catch( Throwable e ) {
				System.out.println(e.getMessage());
				graph.rollback();
			}
		
			nStartTime = System.nanoTime() - nStartTime; 
			
		System.out.println(String.format("Amount of transactions: %d", nTransactions));
		System.out.println(String.format("Amount of vertexes: %d", nVertexes));
		System.out.println(String.format("Amount of edges: %d", nEges));
		System.out.println(String.format("Total time in seconds: %.3f", 1e-9 * nStartTime));
		System.out.println(String.format("Operations time in seconds: %.3f", 1e-9 * totalTime));
		System.out.println(String.format("Transactions per second: %.3f", 1e9 * nTransactions/totalTime));
		System.out.println();
		
	}

	public void readData() {
	
		System.out.println("Read data...");
		
		long nVertexes = 0;
		long nEges = 0;
		long nTransactions = 0;
		
		long nStartTime = System.nanoTime();
		long totalTime = 0;
		
			try{
	
				for (int i=0; i < 100; ++i) {
					
					Iterator<Object> iter = fileIDs.iterator();
					while (iter.hasNext()) {
						Object id = iter.next();
						long x1 = System.nanoTime();				  
						OrientVertex obj = graph.getVertex(id);
						totalTime += (System.nanoTime() - x1);
						
						++nVertexes;
						++nTransactions;
						
						Assert.assertNotNull(obj);
					}
				}
				
				  
			} catch( Throwable e ) {
				System.out.println(e.getMessage());
				graph.rollback();
			}
		
			nStartTime = System.nanoTime() - nStartTime; 
			
		System.out.println(String.format("Amount of transactions: %d", nTransactions));
		System.out.println(String.format("Amount of vertexes: %d", nVertexes));
		System.out.println(String.format("Amount of edges: %d", nEges));
		System.out.println(String.format("Total time in seconds: %.3f", 1e-9 * nStartTime));
		System.out.println(String.format("Operations time in seconds: %.3f", 1e-9 * totalTime));
		System.out.println(String.format("Transactions per second: %.3f", 1e9 * nTransactions/totalTime));
		System.out.println();
		
		
	}

}