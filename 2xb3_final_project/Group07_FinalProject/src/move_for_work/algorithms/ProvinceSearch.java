package move_for_work.algorithms;

import java.util.LinkedList;
import java.util.Queue;

import move_for_work.data.Graph;
import move_for_work.data.Province;

public class ProvinceSearch {
	private final int N;
	private Graph graph;
	private Province[] values; //avoids costly Province.values() calls 
	
	public ProvinceSearch() {
		values = Province.values();
		N = values.length;
		graph = new Graph(N);
		initializeEdges();
	}
	
	private void initializeEdges() {
		addEdge(Province.YUKON,
				Province.BRITISH_COLUMBIA);
		addEdge(Province.YUKON,
				Province.NORTHWEST_TERRITORIES);
		addEdge(Province.BRITISH_COLUMBIA,
				Province.ALBERTA);
		addEdge(Province.NORTHWEST_TERRITORIES,
				Province.ALBERTA);
		addEdge(Province.NORTHWEST_TERRITORIES,
				Province.SASKATCHEWAN);
		addEdge(Province.ALBERTA,
				Province.SASKATCHEWAN);
		addEdge(Province.NORTHWEST_TERRITORIES,
				Province.NUNAVUT);
		addEdge(Province.NUNAVUT,
				Province.MANITOBA);
		addEdge(Province.SASKATCHEWAN,
				Province.MANITOBA);
		addEdge(Province.MANITOBA,
				Province.ONTARIO);
		addEdge(Province.ONTARIO,
				Province.QUEBEC);
		addEdge(Province.QUEBEC,
				Province.NEWFOUNDLAND_LABRADOR);
		addEdge(Province.QUEBEC,
				Province.NEW_BRUNSWICK);
		addEdge(Province.NEW_BRUNSWICK,
				Province.PRINCE_EDWARD_ISLAND);
		addEdge(Province.PRINCE_EDWARD_ISLAND,
				Province.NOVA_SCOTIA);
		addEdge(Province.NOVA_SCOTIA,
				Province.NEW_BRUNSWICK);		
		//canada has an edge to all nodes except error
		//error has no edges
		for (Province p : values)
			if (p != Province.ERROR && p != Province.CANADA)
				graph.addDEdge(Province.CANADA.ordinal(), p.ordinal());
	}
	
	//utility, made copy-pasting the above easier :v
	private void addEdge(Province v, Province w) {
		graph.addEdge(v.ordinal(), w.ordinal());
	}
	
	public Province[] getOrder(Province p) {
		/*boolean[] marked = new boolean[N];
		Stack<Province> order = new Stack<Province>();
		order.ensureCapacity(N);
		dfs(order, marked, p.ordinal());		
		Province[] topological = new Province[order.size()];
		int i = 0;
		while (order.size() > 0)
			topological[i++] = order.pop();
		return topological;*/
		
		int s = p.ordinal();
		Queue<Province> order = new LinkedList<Province>();
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] marked = new boolean[N];
		marked[s] = true;
		queue.add(s);
		while (!queue.isEmpty()) {
			int v = queue.remove();
			order.add(values[v]);
			for (int w : graph.adj(v))
				if (!marked[w]) {
					marked[w] = true;
					queue.add(w);
				}
		}
		Province[] x = order.toArray(new Province[order.size()]);
		return x;
	}
	
	/*private void dfs(Stack<Province> order, boolean[] marked, int v) {
		marked[v] = true;
		for (int w : graph.adj(v))
			if (!marked[w])
				dfs(order, marked, w);
		order.add(values[v]);
	}*/
	
	public static void main(String[] args) {
		ProvinceSearch pSearch = new ProvinceSearch();
		Province[] myOrder = pSearch.getOrder(Province.ONTARIO);
		for (Province p : myOrder)
			System.out.println(p);
	}
}
