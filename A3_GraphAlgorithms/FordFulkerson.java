import java.util.*;
import java.io.File;

public class FordFulkerson {

	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> path = new ArrayList<Integer>();
		/* YOUR CODE GOES HERE*/
		
		//hashmaps to keep track of if nodes are visited and for adjacency lists
		HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		HashMap<Integer, ArrayList<Edge>> adjList = new HashMap<Integer, ArrayList<Edge>>();
		for(int i=0;i < graph.getNbNodes(); i++) {
			visited.put(i, false);
			adjList.put(i, new ArrayList<Edge>());
		}
		
		for(Edge e : graph.getEdges()) {
			if(e.weight==0) {
				graph.getEdges().remove(e);
			}
			int i = e.nodes[0];
			adjList.get(i).add(e);
		}
		
		//stack for dfs
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(graph.getSource());
		
		Integer node = 0;
		
		while(!stack.isEmpty()) {
			node = stack.pop();
			
			//check if visited
			if(!visited.get(node)) {
				
				while(true) {
					if(path.size()>0 && graph.getEdge(path.get(path.size()-1), node)==null) {
						path.remove(path.size()-1);
					}else {
						break;
					}
				}
				
				//visit node
				path.add(node);	
				visited.replace(node, true);
			}
			
			if(node==graph.getDestination()) {
				//got to the destination, so there is a path
				return path;
			}
			
			//counts how many nodes are connected to the current node that aren't visited
			int newPath=0;
			for(Edge e: adjList.get(node)) {
				int newNode = e.nodes[1];
				if(!visited.get(newNode)) {
					stack.push(newNode);
					newPath++;
				}
			}
			
			if(newPath ==0) {
				//remove current node from path, since it's a leaf or doesn't lead to destination
				path.remove(node);
			}

		}
		//otherwise returns an empty path
		return new ArrayList<Integer>();
	}



	public static String fordfulkerson( WGraph graph){
		String answer="";
		int maxFlow = 0;
		int bottleneck = 0;
		
		//creating a hashmap for the flow of each edge, set all to 0
		//also making a list of edges out of the source, to be used later for maxFlow
		HashMap<Edge, Integer> flow = new HashMap<Edge, Integer>();
		ArrayList<Edge> outOfSource = new ArrayList<Edge>();
		for(Edge e : graph.getEdges()) {
			flow.put(e, 0);
			if(e.nodes[0] == graph.getSource()) {
				outOfSource.add(e);
			}
		}
		
		//creating residual graph
		WGraph residual = new WGraph(graph);
		
		//finding the augmenting path
		ArrayList<Integer> path = pathDFS(residual.getSource(), residual.getDestination(), residual);
	
		
		while(path.size()>0) {
			bottleneck = augment(path, residual);
			
		
			//walk through graph, adjusting flow with bottleneck
			for(int i = 0; i<path.size()-1;i++) {
				Edge e = graph.getEdge(path.get(i), path.get(i+1));
				Edge resForward = residual.getEdge(path.get(i), path.get(i+1));
				Edge resBackward = residual.getEdge(path.get(i+1), path.get(i));				
				
				//if the edges in the residual graph don't exist, create them with weight 0
				if(resForward == null) {
					resForward = new Edge(path.get(i), path.get(i+1), 0);
					residual.addEdge(resForward);
				}
				if(resBackward == null) {
					resBackward = new Edge(path.get(i+1), path.get(i), 0);
					residual.addEdge(resBackward);
				}
				
				
				//adds or subtracts bottleneck from e, depending if backward or not
				if(e==null) {
					e = graph.getEdge(path.get(i+1), path.get(i));
					flow.replace(e, flow.get(e)-bottleneck);
					//update residual NOT SURE
					resBackward.weight-=bottleneck;
					resForward.weight += bottleneck;
					
				}else {
					flow.replace(e, flow.get(e)+bottleneck);
					
					//update forward edge in residual
					resForward.weight-=bottleneck;
					//update or create backedge in residual
					resBackward.weight+=bottleneck;
					
				}
				
				//deletes edges from residual if weight 0
				if(resForward.weight == 0) {
					residual.getEdges().remove(resForward);
				}
				if(resBackward.weight == 0) {
					residual.getEdges().remove(resBackward);
				}
				
			}
			
			//updates path for next iteration
			path = pathDFS(residual.getSource(), residual.getDestination(), residual);
			
		}
		
		//get expression for max flow
		for(Edge e: outOfSource) {
			maxFlow += flow.get(e);
		}
		
		//change the weights of the edges to be the flow
		for(Edge e: graph.getEdges()) {
			e.weight=flow.get(e);
		}
		
		answer += maxFlow + "\n" + graph.toString();	
		return answer;
	}
	
	//HELPER METHOD
	public static int augment(ArrayList<Integer> path, WGraph rGraph) {
		
		int bottleneck=-1;
		//iterate through edges in path to find bottleneck
		for(int i = 0; i<path.size()-1;i++) {
			Edge e = rGraph.getEdge(path.get(i), path.get(i+1));
			int diff = e.weight;
			
			//set bottleneck in first iteration, then find the min of the rest to get beta
			if(i == 0) {
				bottleneck = diff;
			}
			if(diff < bottleneck) {
				bottleneck = diff;
			}
		}
		return bottleneck;
	}

	
	 public static void main(String[] args){
		 String file = args[0];
		 File f = new File(file);
		 WGraph g = new WGraph(file);
	         System.out.println(fordfulkerson(g));
		 
	 }
}

import java.util.*;
import java.io.File;
 
public class FordFulkerson {
 
	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> path = new ArrayList<Integer>();
		/* YOUR CODE GOES HERE*/
		
		//hashmaps to keep track of if nodes are visited and for adjacency lists
		HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		HashMap<Integer, ArrayList<Edge>> adjList = new HashMap<Integer, ArrayList<Edge>>();
		for(int i=0;i < graph.getNbNodes(); i++) {
			visited.put(i, false);
			adjList.put(i, new ArrayList<Edge>());
		}
		
		for(Edge e : graph.getEdges()) {
			if(e.weight==0) {
				graph.getEdges().remove(e);
			}
			int i = e.nodes[0];
			adjList.get(i).add(e);
		}
		
		//stack for dfs
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(graph.getSource());
		
		Integer node = 0;
		
		while(!stack.isEmpty()) {
			node = stack.pop();
			
			//check if visited
			if(!visited.get(node)) {
				
				while(true) {
					if(path.size()>0 && graph.getEdge(path.get(path.size()-1), node)==null) {
						path.remove(path.size()-1);
					}else {
						break;
					}
				}
				
				//visit node
				path.add(node);	
				visited.replace(node, true);
			}
			
			if(node==graph.getDestination()) {
				//got to the destination, so there is a path
				return path;
			}
			
			//counts how many nodes are connected to the current node that aren't visited
			int newPath=0;
			for(Edge e: adjList.get(node)) {
				int newNode = e.nodes[1];
				if(!visited.get(newNode)) {
					stack.push(newNode);
					newPath++;
				}
			}
			
			if(newPath ==0) {
				//remove current node from path, since it's a leaf or doesn't lead to destination
				path.remove(node);
			}
 
		}
		//otherwise returns an empty path
		return new ArrayList<Integer>();
	}
 
 
 
	public static String fordfulkerson( WGraph graph){
		String answer="";
		int maxFlow = 0;
		int bottleneck = 0;
		
		//creating a hashmap for the flow of each edge, set all to 0
		//also making a list of edges out of the source, to be used later for maxFlow
		HashMap<Edge, Integer> flow = new HashMap<Edge, Integer>();
		ArrayList<Edge> outOfSource = new ArrayList<Edge>();
		for(Edge e : graph.getEdges()) {
			flow.put(e, 0);
			if(e.nodes[0] == graph.getSource()) {
				outOfSource.add(e);
			}
		}
		
		//creating residual graph
		WGraph residual = new WGraph(graph);
		
		//finding the augmenting path
		ArrayList<Integer> path = pathDFS(residual.getSource(), residual.getDestination(), residual);
	
		
		while(path.size()>0) {
			bottleneck = augment(path, residual);
			
		
			//walk through graph, adjusting flow with bottleneck
			for(int i = 0; i<path.size()-1;i++) {
				Edge e = graph.getEdge(path.get(i), path.get(i+1));
				Edge resForward = residual.getEdge(path.get(i), path.get(i+1));
				Edge resBackward = residual.getEdge(path.get(i+1), path.get(i));				
				
				//if the edges in the residual graph don't exist, create them with weight 0
				if(resForward == null) {
					resForward = new Edge(path.get(i), path.get(i+1), 0);
					residual.addEdge(resForward);
				}
				if(resBackward == null) {
					resBackward = new Edge(path.get(i+1), path.get(i), 0);
					residual.addEdge(resBackward);
				}
				
				
				//adds or subtracts bottleneck from e, depending if backward or not
				if(e==null) {
					e = graph.getEdge(path.get(i+1), path.get(i));
					flow.replace(e, flow.get(e)-bottleneck);
					//update residual NOT SURE
					resBackward.weight-=bottleneck;
					resForward.weight += bottleneck;
					
				}else {
					flow.replace(e, flow.get(e)+bottleneck);
					
					//update forward edge in residual
					resForward.weight-=bottleneck;
					//update or create backedge in residual
					resBackward.weight+=bottleneck;
					
				}
				
				//deletes edges from residual if weight 0
				if(resForward.weight == 0) {
					residual.getEdges().remove(resForward);
				}
				if(resBackward.weight == 0) {
					residual.getEdges().remove(resBackward);
				}
				
			}
			
			//updates path for next iteration
			path = pathDFS(residual.getSource(), residual.getDestination(), residual);
			
		}
		
		//get expression for max flow
		for(Edge e: outOfSource) {
			maxFlow += flow.get(e);
		}
		
		//change the weights of the edges to be the flow
		for(Edge e: graph.getEdges()) {
			e.weight=flow.get(e);
		}
		
		answer += maxFlow + "\n" + graph.toString();	
		return answer;
	}
	
	//HELPER METHOD
	public static int augment(ArrayList<Integer> path, WGraph rGraph) {
		
		int bottleneck=-1;
		//iterate through edges in path to find bottleneck
		for(int i = 0; i<path.size()-1;i++) {
			Edge e = rGraph.getEdge(path.get(i), path.get(i+1));
			int diff = e.weight;
			
			//set bottleneck in first iteration, then find the min of the rest to get beta
			if(i == 0) {
				bottleneck = diff;
			}
			if(diff < bottleneck) {
				bottleneck = diff;
			}
		}
		return bottleneck;
	}
 
	
	 public static void main(String[] args){
		 String file = args[0];
		 File f = new File(file);
		 WGraph g = new WGraph(file);
	         System.out.println(fordfulkerson(g));
		 
	 }
}
