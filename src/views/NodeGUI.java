package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Model.Node;

public class NodeGUI extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int nodeNumber;
	
	private JPanel contentPane;
	
	private JLabel lblID;
	private JLabel lblData;
	private JLabel lblLength;
	private JLabel lblRxmessage;
	private JLabel lblTxmessage;
	
	
	
	private JLabel fldID;
	private JTextField fldData;
	private JTextField fldLength;
	private JTextField fldRX;
	private JTextField fldTX;
	private JButton btnTransmit;
	private JLabel lblIDLen;
	private int retrieved;
	
	/**
	 * Create the frame.
	 */
	public NodeGUI(Node node,java.awt.Frame parent, boolean modal, String title, int nodeNumber) {
		super(parent, modal);
		this.nodeNumber=nodeNumber;
		this.setTitle(title);
		
		
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblID = new JLabel("ID - either 11 or 29 bits:");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblID.setBounds(10, 5, 208, 14);
		contentPane.add(lblID);
		
		lblData = new JLabel("Data");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblData.setBounds(11, 73, 46, 14);
		contentPane.add(lblData);
		
		lblLength = new JLabel("Length");
		lblLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLength.setBounds(11, 98, 46, 20);
		contentPane.add(lblLength);
		
		lblRxmessage = new JLabel("RXmessage");
		lblRxmessage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRxmessage.setBounds(20, 171, 88, 31);
		contentPane.add(lblRxmessage);
		
		lblTxmessage = new JLabel("TXmessage");
		lblTxmessage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTxmessage.setBounds(21, 136, 72, 31);
		contentPane.add(lblTxmessage);
		
	
		if (node.getID().length()==11)
		{
			fldID = new JLabel(node.getID());
			fldID.setBounds(30, 20, 140, 20);
			fldID.setBorder(BorderFactory.createLineBorder(Color.black));
		}else if (node.getID().length()==29)
		{
			fldID = new JLabel("<html>"+node.getID().substring(0, 15)+"<br>"+node.getID().substring(15, 29)+"</html>");
			fldID.setBounds(30, 20, 140, 40);
			fldID.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		fldID.setFont(new Font("Consolas", Font.PLAIN, 16));
		fldID.setBackground(Color.LIGHT_GRAY);
		contentPane.add(fldID);
		
		fldData = new JTextField(node.getData());
		fldData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fldData.setBounds(67, 72, 86, 20);
		contentPane.add(fldData);
		fldData.setColumns(10);
		
		fldLength = new JTextField(String.valueOf(node.getLen()));
		fldLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fldLength.setBounds(67, 100, 86, 20);
		contentPane.add(fldLength);
		fldLength.setColumns(10);
		
		fldRX = new JTextField(node.getRxMessage());
		fldRX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fldRX.setBounds(103, 178, 86, 20);
		contentPane.add(fldRX);
		fldRX.setColumns(10);
		
		fldTX = new JTextField(node.getTxMessage());
		fldTX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fldTX.setBounds(103, 141, 86, 20);
		contentPane.add(fldTX);
		fldTX.setColumns(10);
		
	    btnTransmit = new JButton("Transmit");
		btnTransmit.setBounds(62, 219, 91, 23);
		contentPane.add(btnTransmit);
		
		lblIDLen = new JLabel("("+String.valueOf(node.getID().length())+")");
		lblIDLen.setFont(new Font("Linux Biolinum G", Font.PLAIN, 18));
		lblIDLen.setBounds(0, 10, 65, 40);
		contentPane.add(lblIDLen);
		
		
		btnTransmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			if (Integer.valueOf(fldLength.getText())>=8){
			fldTX.setText(fldData.getText().substring(0,8));
				
				
				Color color =Color.GREEN;
				fldTX.setBackground(color);
				
				Object[] row = new Object[]{nodeNumber,fldTX.getText()};
				
				String captureID = new String();
				captureID=fldID.getText(); 
				if (captureID.length()>11)
				{
					captureID=captureID.replace("</html>", "");
					captureID=captureID.replace("<html>", "");
					captureID=captureID.replace("<br>", "");
				}
				
				if (captureID.length()==29){
					captureID+=".";
				}
				
				if (captureID.length()==11){
					captureID="000000000000000000"+captureID;
				}
				

				ArrayList<String> numberAndMessage = new ArrayList<String>();
				numberAndMessage.add(String.valueOf(nodeNumber)); 
				numberAndMessage.add(fldTX.getText());
				Frame.nodeActivity.put(captureID, numberAndMessage);
				
				if  ( !Frame.nodeTransmitions.contains(nodeNumber+","+captureID+","+fldTX.getText())){
				Frame.nodeTransmitions+=nodeNumber+","+captureID+","+fldTX.getText()+"\n";
				Frame.dTableModel.addRow(row);				
				}
			}
			}
		});
		
		
		
		retrieved=0;
		Frame.btnStep.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (retrieved<Frame.max){
						fldRX.setText(String.valueOf(Frame.sortedTable.getValueAt(retrieved, 1)));
						fldRX.setBackground(Color.red);
						Frame.sortedTable.setRowSelectionInterval(retrieved, retrieved);
						retrieved++;
						if (retrieved == Frame.max)
						{
							retrieved=0;
						}
				}
				
			}
		});
		Frame.btnArbitrateBUS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				retrieved=0;
			}
		});
		
		
		fldData.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			if (fldData.getText().length()<9)
			fldLength.setText(String.valueOf(fldData.getText().length()));
			}
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		
		Frame.btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==Frame.btnReset){
					fldRX.setText("empty");
					fldTX.setText("empty");
					fldData.setText("empty");
					fldLength.setText("0");
					fldRX.setBackground(Color.WHITE);
					fldTX.setBackground(Color.WHITE);
				}
			}
		});
		
		
		
	}

	public int getNodeNumber() {
		return nodeNumber;
	}

	public void setNodeNumber(int nodeNumber) {
		this.nodeNumber = nodeNumber;
	}
	
	public JButton getBtnTransmit() {
		return btnTransmit;
	}

	public void setBtnTransmit(JButton btnTransmit) {
		this.btnTransmit = btnTransmit;
	}

	public int getRetrieved() {
		return retrieved;
	}




	
		
			
		
	
	
}
