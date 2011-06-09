package br.ufsm.ddetector;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

public class GraphBuilder {
	ListenableDirectedGraph<Integer, Object> graph;
	public GraphBuilder(Collection<Operation> operations) {
		graph = new ListenableDirectedGraph<Integer, Object>(DefaultEdge.class);
		Iterator<Operation> iter = operations.iterator();
		Map<Integer, Integer> locks = new TreeMap<Integer, Integer>();
		Operation op = null;
		Set<Integer> vertex = new HashSet<Integer>();
		while(iter.hasNext()) {
			op = iter.next();
			if(!vertex.contains(op.getTrans())) {
				vertex.add(op.getTrans());
				graph.addVertex(op.getTrans());
			}
			if(op.getOp() == Operation.BLOCK) {
				if(!locks.containsKey(op.getVar()))
					locks.put(op.getVar(), op.getTrans());
				else {
					Integer trans = locks.get(op.getVar());
					if(!trans.equals(op.getTrans())) {
						graph.addEdge(op.getTrans(), trans);
					}
				}
			} else if(op.getOp() == Operation.UNBLOCK) {
				if(locks.get(op.getVar()).equals(op.getTrans())) {
					Set<Object> arriving = graph.incomingEdgesOf(locks.get(op.getVar()));
					locks.remove(op.getVar());
					for(Object obj : arriving) {
						graph.removeEdge(obj);
					}
					
				}
			} 
		}
	}
	
	public ListenableDirectedGraph<Integer, Object> getBuiltGraph() {
		return graph;
	}
}
