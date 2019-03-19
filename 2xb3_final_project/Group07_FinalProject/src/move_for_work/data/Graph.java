package move_for_work.data;

public class Graph {
	private final int V;
	private Bag<Integer>[] adj;
	
	public Graph(int V) {
		this.V = V;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Bag<Integer>();
	}
	
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
	}
	
	public void addDEdge(int v, int w) {
		adj[v].add(w);
	}
	
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	
	/*public static void main(String[] args) {
		System.out.println("hello");
		Province p = Province.CANADA;
		System.out.println("Province: " + p);
		int pValue = p.ordinal();
		System.out.println("Ordinal: " + pValue);
	}*/
}
