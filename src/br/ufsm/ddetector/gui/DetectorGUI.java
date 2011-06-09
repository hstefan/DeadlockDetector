package br.ufsm.ddetector.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.SpringLayout;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

import br.ufsm.ddetector.GraphBuilder;
import br.ufsm.ddetector.Operation;
import br.ufsm.ddetector.Parser;


public class DetectorGUI extends JFrame {	
	
	private static final long serialVersionUID = -8802074061797004424L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetectorGUI frame = new DetectorGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DetectorGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);	
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				System.exit(0);
			}
		});
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				// CHAMA LEITOR
				
				// TESTE
				//grafotest();
				//contentPane.setFocusable(true);
				
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos trs", "trs");
				chooser.setFileFilter(filter);
				int ret = chooser.showDialog(null, "Selecione o arquivo.");
				if(ret == JFileChooser.APPROVE_OPTION) {
					File f = chooser.getSelectedFile();
					Parser p = new Parser(f.getAbsolutePath());
					try {
						p.processLineByLine();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ArrayList<Operation> opls = p.getList();
					GraphBuilder graph = new GraphBuilder(opls);
					GraphUtil gu = new GraphUtil();
					contentPane.removeAll();
					contentPane.add(gu.getJGraph(graph.getBuiltGraph()));
					validate();
					repaint();
				}
			}
		});
		
		mnArquivo.add(mntmAbrir);
		mnArquivo.add(mntmSair);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		setResizable(false);
	}
	
	// Desenha grafo teste
	public void grafotest() {
		// create a JGraphT graph
		ListenableGraph<String, Object> g = new ListenableDirectedGraph<String, Object>(DefaultEdge.class);
	    
	    // add some sample data (graph manipulated via JGraphT)
	    g.addVertex("v1");
	    g.addVertex("v2");
	    g.addVertex("v3");
	    g.addVertex("v4");
	
	    g.addEdge("v1", "v2");
	    g.addEdge("v2", "v3");
	    g.addEdge("v3", "v1");
	    g.addEdge("v4", "v3");
	    
	    GraphUtil gu = new GraphUtil();
	    contentPane.add(gu.getJGraph(g));
	    validate();
        repaint();
	}
}
