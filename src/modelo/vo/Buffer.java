package modelo.vo;

import net.datastructures.Graph;

public interface Buffer {
	 public void set(Graph<String, Integer> grafo) throws InterruptedException;

	    public Graph<String, Integer> get() throws InterruptedException;    
	    
	    public int size();

}
