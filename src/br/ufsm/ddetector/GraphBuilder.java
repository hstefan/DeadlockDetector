package br.ufsm.ddetector;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

public class GraphBuilder {
	ListenableDirectedGraph<Integer, Object> graph;
	
	public GraphBuilder(Collection<Operation> operations) {
		graph = new ListenableDirectedGraph<Integer, Object>(DefaultEdge.class);
		Iterator<Operation> iter = operations.iterator();
		Operation op = null;
		Integer locked = null;
		Set<Integer> vertex = new HashSet<Integer>();
		while(iter.hasNext()) {
			op = iter.next();
			if(!vertex.contains(op.getTrans())) {
				vertex.add(op.getTrans());
				graph.addVertex(op.getTrans());
			}
			if(op.getOp() == Operation.BLOCK) {
				if(locked == null)
					locked = op.getTrans();
				else if (!locked.equals(op.getTrans())){
					graph.addEdge(op.getTrans(), locked);
				}
			} else if(op.getOp() == Operation.UNBLOCK) {
				if(locked.equals(op.getTrans())) {
					locked = null;
					Set<Object> arriving = graph.incomingEdgesOf(locked);
					for(Object obj : arriving) {
						graph.removeEdge(obj);
					}
				}
			} else if(op.getOp() == Operation.WRITE) {
				
			}
		}
	}
}
