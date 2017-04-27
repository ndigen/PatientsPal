import java.io.FileNotFoundException;
import java.util.ArrayList;


import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Graph {

	private static int j = 0;
	private Bag<Edge>[] adj;
	private int E;
	private final int V;
	
	public int V(){
		return this.V;
	}
	
	public int E(){
		return this.E;
	}
	
	
	public Graph(int V){
		this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Edge>();
        }
		
	}
	
	
	public Graph(Hospital[] hospitals, ArrayList<Integer> input, ArrayList<Double> distance) throws FileNotFoundException{
		this(input.size()); // Read V and construct this graph.
		int E = input.size(); // Read E.
		
		for(int i = 1; i < E  ; i++){ // Add an edge.
			int v = input.get(0); // Read a vertex,
			int w = input.get(i); // read another vertex,
			double weight = distance.get(i-1);
			Edge e = new Edge(v, w, weight);
			addEdge(e); // and add edge connecting them.
		}
	}

		
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

	


    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }
	
	
	

	
	public String toString(){
		String s = V+ " vertices, "+ E+ " edges\n";
		for(int v = 0; v < V; v++){
			s += v + ": ";
			for(Edge w : this.adj(v))
				s += w + " ";
				s += "\n";
		}
		return s;
	}
	

}
