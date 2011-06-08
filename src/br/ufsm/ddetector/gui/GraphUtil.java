package br.ufsm.ddetector.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jgraph.JGraph;
import org.jgraph.graph.AttributeMap.SerializableRectangle2D;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;

public class GraphUtil {
	
	private static final Color     DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
    private static final Dimension DEFAULT_SIZE = new Dimension(700, 500);
    
    private JGraphModelAdapter m_jgAdapter; 
    private ListenableGraph g;
    
    /**
     * Retorna um grafo pronto para ser exibido num frame.
     * 
     * <p> As posicoes dos nodos sao setadas aleatoriamente.</p>
     * <p>Usar <b>getContentPane().add(jgraph)</b> para adicionar o grafo</p>
     * 	
     * @param graph grafo de algoritmo
     * @return grafo de desenho
     */
	public JGraph getJGraph(ListenableGraph graph) {	
		
		g = graph;
		
		// create a visualization using JGraph, via an adapter
	    m_jgAdapter = new JGraphModelAdapter(g);
	    JGraph jgraph = new JGraph(m_jgAdapter);

	    adjustDisplaySettings(jgraph);
	    positionVertexes(jgraph);
	    
		return jgraph;	    
	}
	
	// Auxiliares	

	private void adjustDisplaySettings(JGraph jg) {
		jg.setPreferredSize(DEFAULT_SIZE);
        Color c = DEFAULT_BG_COLOR; 
        jg.setBackground(c);
    }


    private void positionVertexAt(Object vertex, int x, int y) {
        DefaultGraphCell 			cell = m_jgAdapter.getVertexCell(vertex);
        Map             			attr = cell.getAttributes();
        SerializableRectangle2D     b    = (SerializableRectangle2D) GraphConstants.getBounds(attr);

        GraphConstants.setBounds(attr, new SerializableRectangle2D(x, y, b.width, b.height));

        Map cellAttr = new HashMap<DefaultGraphCell, Map>();
        cellAttr.put(cell, attr);
        m_jgAdapter.edit(cellAttr, null, null, null);
    }  
    
    private void positionVertexes(JGraph jg) {
		
    	Set vertexes = g.vertexSet();
    	int x = 0;
    	int y = 0;
    	
    	for (Object object : vertexes) {
    		x += 100;
    		y += 100;    		
    		positionVertexAt(object, x, y);
		}
		
	}

}
