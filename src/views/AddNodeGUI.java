package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddNodeGUI extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fldID;
	private JLabel lblDValidity;
	private JLabel lblPleaseInsertId;
	private JLabel lblCountIDBits; 
	private JLabel lblNewLabel;
	private JButton btnAddNewNode;
	private JLabel lblIDTaken;
	private ImageIcon imageOK;
	private ImageIcon imageNotOK;
	private ImageIcon idTaken;
	
	public AddNodeGUI(java.awt.Frame parent, boolean modal){
		super(parent,modal);
		setTitle("Add new CAN Network Node");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(0, 0, 379, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		fldID = new JTextField();
		fldID.setFont(new Font("Consolas", Font.PLAIN, 20));
		fldID.setBounds(10, 79, 337, 44);
		contentPane.add(fldID);
		fldID.setColumns(10);
		
		 imageNotOK = new ImageIcon("notok.png");
		 imageOK = new ImageIcon("ok.png");
		 idTaken = new ImageIcon("nodeTaken.png");
		   
		   lblDValidity = new JLabel(imageNotOK);
		   lblDValidity.setBounds(247, 169, 86, 82);
		   contentPane.add(lblDValidity);
		   
		   lblPleaseInsertId = new JLabel("<html>Please Insert ID of new CAN Node: "
		   		+"<br>"+"(11 or 29 bits only)</html>");
		   lblPleaseInsertId.setFont(new Font("Consolas", Font.PLAIN, 18));
		   lblPleaseInsertId.setBounds(10, 11, 583, 44);
		   contentPane.add(lblPleaseInsertId);
		   
		   btnAddNewNode = new JButton("Add new Node!");
		   btnAddNewNode.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {
		   	}
		   });
		   btnAddNewNode.setFont(new Font("Linux Biolinum G", Font.PLAIN, 19));
		   btnAddNewNode.setBounds(59, 181, 157, 44);
		   contentPane.add(btnAddNewNode);
		   
		   lblCountIDBits = new JLabel("0");
		   lblCountIDBits.setFont(new Font("Consolas", Font.PLAIN, 26));
		   lblCountIDBits.setBounds(269, 126, 64, 44);
		   contentPane.add(lblCountIDBits);
		   
		   lblNewLabel = new JLabel("Number on bits entered so far:");
		   lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 15));
		   lblNewLabel.setBounds(20, 144, 253, 14);
		   contentPane.add(lblNewLabel);
		   
		   lblIDTaken = new JLabel();
		   lblIDTaken.setBounds(44, 236, 193, 44);
		   contentPane.add(lblIDTaken);
		   
	}
	
		public JTextField getFldID() {
		return fldID;
	}

	public void addAdditionalText(String text){
		this.fldID.setText(text);
	}
		
	public void setFldID(JTextField fldID) {
		this.fldID = fldID;
	}
    
		public JLabel getLblDValidity() {
		return lblDValidity;
	}

	public void setLblDValidity(JLabel lblDValidity) {
		this.lblDValidity = lblDValidity;
	}

	public JLabel getLblCountIDBits() {
		return lblCountIDBits;
	}

	public void setLblCountIDBits(JLabel lblCountIDBits) {
		this.lblCountIDBits = lblCountIDBits;
	}
	
		public JButton getBtnAddNewNode() {
		return btnAddNewNode;
	}

	public void setBtnAddNewNode(JButton btnAddNewNode) {
		this.btnAddNewNode = btnAddNewNode;
	}
	
	public void setImage(ImageIcon imIc){
		this.lblDValidity.setIcon(imIc);
	}
	
	
	
	public ImageIcon getImageOK() {
		return imageOK;
	}

	public void setImageOK(ImageIcon imageOK) {
		this.imageOK = imageOK;
	}

	public ImageIcon getImageNotOK() {
		return imageNotOK;
	}

	public void setImageNotOK(ImageIcon imageNotOK) {
		this.imageNotOK = imageNotOK;
	}
	
	public JLabel getLblIDTaken() {
		return lblIDTaken;
	}

	public void setLblIDTaken(JLabel lblIDTaken) {
		this.lblIDTaken = lblIDTaken;
	}

	
	
	public ImageIcon getIdTaken() {
		return idTaken;
	}

	public void setIdTaken(ImageIcon idTaken) {
		this.idTaken = idTaken;
	}

	public void addMyKeyListener(KeyListener keyListener) {
	    fldID.addKeyListener(keyListener);
	  }
	
	public void addMyButtonListener(ActionListener  buttonListener) {
	    btnAddNewNode.addActionListener(buttonListener);
	  }
}
