package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Node;
import Model.NodeDOM;


public class Frame extends JFrame {


	public static Timer timer;
	
	private static Object[][] dummyData;
	private static Object[] columns = {"NodeID", "Message"};
	
	static DefaultTableModel dTableModel = new DefaultTableModel(dummyData, columns);

	static DefaultTableModel sortedTableModel = new DefaultTableModel(dummyData, columns);
	
	int numberOfNodes=0;
	static int xWindow=30; 
	static int yWindow=140;
	static int dialogWidth=240;
	static int dialogHeight=300;
	static String nodeTransmitions=new String("");
	static JPanel contentPane;
	private JButton btnAddNode;
	static int max;
	private JTable table; 
	static JButton btnStep;
	static JTable sortedTable;
	static JButton btnReset;
	static JButton btnArbitrateBUS;

	static TreeMap<String,ArrayList<String>> nodeActivity = new TreeMap<String,ArrayList<String>>();
	
	
	public static void resetAll(){
		if (dTableModel.getRowCount() > 0) {
		    for (int i = dTableModel.getRowCount() - 1; i > -1; i--) {
		    	dTableModel.removeRow(i);
		    }
		}
		
		if (sortedTableModel.getRowCount() > 0) {
		    for (int i = sortedTableModel.getRowCount() - 1; i > -1; i--) {
		    	sortedTableModel.removeRow(i);
		    }
		}
		nodeActivity.clear();
	}
	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public Frame() {
		
		
		
		table = new JTable(dTableModel)
		{
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int col)
			{
			return false;
			}
		};
		
		sortedTable = new JTable(sortedTableModel)
		{
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int col)
			{
			return false;
			}
		};
		
		setTitle("CAN Network Simulation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1600, 875);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(1325, 88, 245, 309);
		contentPane.add(scrollPane);
		
		JScrollPane scrollPane1 = new JScrollPane(sortedTable);
		scrollPane1.setBounds(1325, 497, 245, 282);
		contentPane.add(scrollPane1);
		
		btnAddNode = new JButton("Add Node");
		btnAddNode.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnAddNode.setBounds(26, 23, 120, 38);
		contentPane.add(btnAddNode);
		
		btnStep = new JButton("Simulation STEP");
		btnStep.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnStep.setBounds(1325, 790, 245, 38);
		contentPane.add(btnStep);
		
		btnArbitrateBUS = new JButton("<html>Perform CAN BUS Arbitration<br>(Sort Messages by Node ID)</html>");
		btnArbitrateBUS.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnArbitrateBUS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    max = Frame.dTableModel.getRowCount();
				if (sortedTableModel.getRowCount() > 0) {
				    for (int i = sortedTableModel.getRowCount() - 1; i > -1; i--) {
				    	sortedTableModel.removeRow(i);
				    }
				}
				
				NodeDOM nDOM = new NodeDOM();
				ArrayList<Node> allTheNodes = nDOM.getAllNodes();
				ArrayList<String> allTheIDs = new ArrayList<String>();
				for (int i = 0; i < allTheNodes.size(); i++) {
					allTheIDs.add(allTheNodes.get(i).getID());
					}
					
			System.out.println("Size: "+nodeActivity.size());
				
				 Set set = nodeActivity.entrySet();
			      Iterator iterator = set.iterator();
			      while(iterator.hasNext()) {
			    	  ArrayList<String> row = new ArrayList<String>();
			         Map.Entry<String, ArrayList<String>> mentry = (Map.Entry<String, ArrayList<String>>)iterator.next();
			         row = mentry.getValue();
			         System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
			         System.out.println(mentry.getValue());
			        
			         Object[] tableRow = new Object[]{row.get(0),row.get(1)};
				    sortedTableModel.addRow(tableRow); 
			      }
				
			      
			     
			      
			}
		});
		
		
		
		btnArbitrateBUS.setBounds(1325, 408, 245, 68);
		contentPane.add(btnArbitrateBUS);
		
		ImageIcon iconReset = new ImageIcon("reset.png");
	    btnReset = new JButton(iconReset);
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReset.setBounds(1325, 11, 66, 66);
		contentPane.add(btnReset);
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetAll();
				nodeTransmitions="";
			}
		});
		
		
		
	
		
		
		
		ImageIcon backgournd = new ImageIcon("fundal_can.png");
		
		JLabel lblNewLabel = new JLabel(backgournd);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(0, -12, 1592, 873);
		//lblNewLabel.setBounds(182, 23, 283, 92);
		contentPane.add(lblNewLabel);
		
		
		

		
		
		
	}
	public void addButtonActionListener(ActionListener listener) {
	    btnAddNode.addActionListener(listener);
	    btnStep.addActionListener(listener);
	  }
	public JButton getBtnAddNode() {
		return btnAddNode;
	}
	public void setBtnAddNode(JButton btnAddNode) {
		this.btnAddNode = btnAddNode; 
	}
}
