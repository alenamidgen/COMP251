import java.util.*;
//by Alena Midgen, no collaborators
public class Kruskal{

    public static WGraph kruskal(WGraph g){
    	ArrayList<Edge> edgesToAdd = g.listOfEdgesSorted();
    	WGraph mst = new WGraph();
    	DisjointSets p = new DisjointSets(g.getNbNodes());
    	for(Edge e:edgesToAdd) {
    		if(IsSafe(p, e)) {
    			mst.addEdge(e);
    			p.union(e.nodes[0], e.nodes[1]);
    		}
    	}
        /* Fill this method (The statement return null is here only to compile) */
        
        return mst;
    }

    public static Boolean IsSafe(DisjointSets p, Edge e){
    	if(p.find(e.nodes[1]) != p.find(e.nodes[0])) {
    		return true;
    	}
        /* Fill this method (The statement return 0 is here only to compile) */
        return false;
    
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);

   } 
}
import java.util.*;
//by Alena Midgen, no collaborators
public class Kruskal{
 
    public static WGraph kruskal(WGraph g){
    	ArrayList<Edge> edgesToAdd = g.listOfEdgesSorted();
    	WGraph mst = new WGraph();
    	DisjointSets p = new DisjointSets(g.getNbNodes());
    	for(Edge e:edgesToAdd) {
    		if(IsSafe(p, e)) {
    			mst.addEdge(e);
    			p.union(e.nodes[0], e.nodes[1]);
    		}
    	}
        /* Fill this method (The statement return null is here only to compile) */
        
        return mst;
    }
 
    public static Boolean IsSafe(DisjointSets p, Edge e){
    	if(p.find(e.nodes[1]) != p.find(e.nodes[0])) {
    		return true;
    	}
        /* Fill this method (The statement return 0 is here only to compile) */
        return false;
    
    }
 
    public static void main(String[] args){
 
        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);
 
   } 
}
