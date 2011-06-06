/**
 * 
 */
package br.ufsm.ddetector;

/**
 * @author hstefan
 * @param <Tn> node identifier type.
 * @param <Ts> connection symbol.
 */
public interface DeadlockGraph<Tn, Ts> {
	/**
	 * Creates a connection between origin and and destination. 
	 */
	public void createConnection(Tn origin, Tn destination);
	
	/**
	 * Attempts to erase a connection between origin and destination.
	 * @return true if there was such connection, otherwise, false.
	 */
	public boolean removeConnection(Tn origin, Tn destination);
	
	/**
	 * Checks if there's an connection from origin to destination. 
	 */
	public boolean hasConnection(Tn origin, Tn destination);
	
	/**
	 * @param node
	 * @param symbol the connection type we're looking for.
	 * @return Every node that the given node connects to.
	 */
	public Tn[] getConnections(Tn node, Ts symbol);
	
	/**
	 * @return The number of nodes.
	 */
	public int getNumberOfNodes();

	
	/**
	 * @param symbol 
	 * @return the first cycle found.
	 */
	public Tn[] hasCycle(Ts symbol);
}
