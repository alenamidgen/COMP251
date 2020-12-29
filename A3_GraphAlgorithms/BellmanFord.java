import java.util.*;

public class BellmanFord{

    private int[] distances = null;
    private int[] predecessors = null;
    private int source;

    class BellmanFordException extends Exception{
        public BellmanFordException(String str){
            super(str);
        }
    }

    class NegativeWeightException extends BellmanFordException{
        public NegativeWeightException(String str){
            super(str);
        }
    }

    class PathDoesNotExistException extends BellmanFordException{
        public PathDoesNotExistException(String str){
            super(str);
        }
    }

    BellmanFord(WGraph g, int source) throws NegativeWeightException{
        /* Constructor, input a graph and a source
         * Computes the Bellman Ford algorithm to populate the
         * attributes 
         *  distances - at position "n" the distance of node "n" to the source is kept
         *  predecessors - at position "n" the predecessor of node "n" on the path
         *                 to the source is kept
         *  source - the source node
         *
         *  If the node is not reachable from the source, the
         *  distance value must be Integer.MAX_VALUE
         */
    	
    	//sets size of arrays
    	this.distances = new int[g.getNbNodes()];
    	this.predecessors = new int[g.getNbNodes()];
    	
    	//puts distance to all of them as the max in case it doesn't reach them
    	for(int i =0;i < g.getNbNodes(); i++) {
    		this.distances[i] = Integer.MAX_VALUE;
    	}
    	
    	//sets the source and distance to it is 0
    	this.source = source;
    	this.distances[source] = 0;
    	
    	//relaxes all edges to ensure their distance is correct
    	for(int i = 0; i<g.getNbNodes()-1; i++) {
    		for(Edge e: g.getEdges()) {
    			if(this.distances[e.nodes[1]] > this.distances[e.nodes[0]] + e.weight) {
    				this.distances[e.nodes[1]] = this.distances[e.nodes[0]] + e.weight;
    				this.predecessors[e.nodes[1]] = e.nodes[0];
    			}
    		}
    	}
    	
    	//relaxes again, it anything changes there's a negative weight edge
    	for(int i = 0; i<g.getNbNodes()-1; i++) {
    		for(Edge e: g.getEdges()) {
    			if(this.distances[e.nodes[1]] > this.distances[e.nodes[0]] + e.weight) {
    				throw new NegativeWeightException("There is a negative weighted edge");
    			}
    		}
    	}	

    }

    public int[] shortestPath(int destination) throws PathDoesNotExistException{
        /*Returns the list of nodes along the shortest path from 
         * the object source to the input destination
         * If not path exists an Error is thrown
         */
    	
    	int d = distances[destination];
    	
    	//if the distance to the destination is the max, then there's no path to it
    	if(d == Integer.MAX_VALUE) {
    		throw new PathDoesNotExistException("There is no path to this node");
    	}
    	
    	
    	ArrayList<Integer> path = new ArrayList<Integer>();
    	
    	//filling path using the predecessors array
    	int node = destination;
    	
    	while(node != 0) {
    		path.add(0, node);
    		node = this.predecessors[node];
    	}
    	
    	path.add(0, 0);
    	
    	
    	//making an int array for the path and copying the values over
    	int[] p = new int[path.size()];
    	
    	for(int i = 0; i<path.size();i++) {
    		p[i] = path.get(i);
    	}
    	
    	
        return p;
    }

    public void printPath(int destination){
        /*Print the path in the format s->n1->n2->destination
         *if the path exists, else catch the Error and 
         *prints it
         */
        try {
            int[] path = this.shortestPath(destination);
            for (int i = 0; i < path.length; i++){
                int next = path[i];
                if (next == destination){
                    System.out.println(destination);
                }
                else {
                    System.out.print(next + "-->");
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args){

        String file = args[0];
    	WGraph g = new WGraph(file);
        try{
            BellmanFord bf = new BellmanFord(g, g.getSource());
            bf.printPath(g.getDestination());
        }
        catch (Exception e){
            System.out.println(e);
        }
   } 
}

import java.util.*;
 
public class BellmanFord{
 
    private int[] distances = null;
    private int[] predecessors = null;
    private int source;
 
    class BellmanFordException extends Exception{
        public BellmanFordException(String str){
            super(str);
        }
    }
 
    class NegativeWeightException extends BellmanFordException{
        public NegativeWeightException(String str){
            super(str);
        }
    }
 
    class PathDoesNotExistException extends BellmanFordException{
        public PathDoesNotExistException(String str){
            super(str);
        }
    }
 
    BellmanFord(WGraph g, int source) throws NegativeWeightException{
        /* Constructor, input a graph and a source
         * Computes the Bellman Ford algorithm to populate the
         * attributes 
         *  distances - at position "n" the distance of node "n" to the source is kept
         *  predecessors - at position "n" the predecessor of node "n" on the path
         *                 to the source is kept
         *  source - the source node
         *
         *  If the node is not reachable from the source, the
         *  distance value must be Integer.MAX_VALUE
         */
    	
    	//sets size of arrays
    	this.distances = new int[g.getNbNodes()];
    	this.predecessors = new int[g.getNbNodes()];
    	
    	//puts distance to all of them as the max in case it doesn't reach them
    	for(int i =0;i < g.getNbNodes(); i++) {
    		this.distances[i] = Integer.MAX_VALUE;
    	}
    	
    	//sets the source and distance to it is 0
    	this.source = source;
    	this.distances[source] = 0;
    	
    	//relaxes all edges to ensure their distance is correct
    	for(int i = 0; i<g.getNbNodes()-1; i++) {
    		for(Edge e: g.getEdges()) {
    			if(this.distances[e.nodes[1]] > this.distances[e.nodes[0]] + e.weight) {
    				this.distances[e.nodes[1]] = this.distances[e.nodes[0]] + e.weight;
    				this.predecessors[e.nodes[1]] = e.nodes[0];
    			}
    		}
    	}
    	
    	//relaxes again, it anything changes there's a negative weight edge
    	for(int i = 0; i<g.getNbNodes()-1; i++) {
    		for(Edge e: g.getEdges()) {
    			if(this.distances[e.nodes[1]] > this.distances[e.nodes[0]] + e.weight) {
    				throw new NegativeWeightException("There is a negative weighted edge");
    			}
    		}
    	}	
 
    }
 
    public int[] shortestPath(int destination) throws PathDoesNotExistException{
        /*Returns the list of nodes along the shortest path from 
         * the object source to the input destination
         * If not path exists an Error is thrown
         */
    	
    	int d = distances[destination];
    	
    	//if the distance to the destination is the max, then there's no path to it
    	if(d == Integer.MAX_VALUE) {
    		throw new PathDoesNotExistException("There is no path to this node");
    	}
    	
    	
    	ArrayList<Integer> path = new ArrayList<Integer>();
    	
    	//filling path using the predecessors array
    	int node = destination;
    	
    	while(node != 0) {
    		path.add(0, node);
    		node = this.predecessors[node];
    	}
    	
    	path.add(0, 0);
    	
    	
    	//making an int array for the path and copying the values over
    	int[] p = new int[path.size()];
    	
    	for(int i = 0; i<path.size();i++) {
    		p[i] = path.get(i);
    	}
    	
    	
        return p;
    }
 
    public void printPath(int destination){
        /*Print the path in the format s->n1->n2->destination
         *if the path exists, else catch the Error and 
         *prints it
         */
        try {
            int[] path = this.shortestPath(destination);
            for (int i = 0; i < path.length; i++){
                int next = path[i];
                if (next == destination){
                    System.out.println(destination);
                }
                else {
                    System.out.print(next + "-->");
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
 
    public static void main(String[] args){
 
        String file = args[0];
    	WGraph g = new WGraph(file);
        try{
            BellmanFord bf = new BellmanFord(g, g.getSource());
            bf.printPath(g.getDestination());
        }
        catch (Exception e){
            System.out.println(e);
        }
   } 
}
