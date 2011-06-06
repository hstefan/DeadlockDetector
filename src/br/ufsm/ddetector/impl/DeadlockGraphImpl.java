package br.ufsm.ddetector.impl;

import java.util.TreeSet;

import br.ufsm.ddetector.DeadlockGraph;

public final class DeadlockGraphImpl implements DeadlockGraph<Integer, Byte> {

	private TreeSet<Byte>[] myAdjMatrix;
	
	public DeadlockGraphImpl(int n) {
		myAdjMatrix = (TreeSet<Byte>[])new Object[n*n];
	}
	
	@Override
	public void createConnection(Integer origin, Integer destination,
			Byte symbol) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean removeConnection(Integer origin, Integer destination,
			Byte symbol) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasConnection(Integer origin, Integer destination,
			Byte symbol) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer[] getConnections(Integer node, Byte symbol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfNodes() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer[] hasCycle(Byte symbol) {
		// TODO Auto-generated method stub
		return null;
	}
}
