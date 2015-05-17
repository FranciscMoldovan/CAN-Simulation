package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import views.AddNodeGUI;
import views.Frame;
import views.NodeGUI;
import Model.Node;
import Model.NodeDOM;

public class Controller {
	private int dialogX = 30;
	private int dialogY = 120;
	private int dialogWidth = 220;
	private int dialogHeight = 300;
	private int numberOfChars = 0;
	private boolean okID = false;
	private boolean idTaken = false;
	private Frame mainWindow = new Frame();
	ArrayList<Node> allNodes;
	private ArrayList<NodeGUI> nodeGUIs;

	private void emptyFrame() {
		for (int i = 0; i < allNodes.size(); i++) {
			nodeGUIs.get(i).dispose();
		}
	}

	private void initNodes() {
		dialogX = 30;
		dialogY = 120;

		NodeDOM nDOM = new NodeDOM();
		allNodes = new ArrayList<Node>();
		allNodes = nDOM.getAllNodes();

		nodeGUIs = new ArrayList<NodeGUI>();

		for (int i = 0; i < allNodes.size(); i++) {
			nodeGUIs.add(new NodeGUI(allNodes.get(i), mainWindow, false,
					"CAN Node #" + i, i));
			if (dialogX + 2 * dialogWidth + 20 < mainWindow.getSize()
					.getWidth()) {
				nodeGUIs.get(i).setBounds(dialogX, dialogY, dialogWidth,
						dialogHeight);
				nodeGUIs.get(i).setVisible(true);
				dialogX += 40 + dialogWidth;
			} else {
				dialogY += dialogHeight + 88;
				dialogX = 30;
				nodeGUIs.get(i).setBounds(dialogX, dialogY, dialogWidth,
						dialogHeight);
				nodeGUIs.get(i).setVisible(true);
				dialogX += 40 + dialogWidth;
			}
			nodeGUIs.get(i).setDefaultCloseOperation(
					WindowConstants.DO_NOTHING_ON_CLOSE);
			nodeGUIs.get(i).addWindowListener(
					new MyWindowAdapter(nodeGUIs.get(i), i));

		}
	}

	public void refreshNodes() {
		emptyFrame();
		initNodes();
	}

	AddNodeGUI addGUI = new AddNodeGUI(mainWindow, true);

	public Controller() {

		NodeDOM nDom = new NodeDOM();
		allNodes = nDom.getAllNodes();
		nodeGUIs = new ArrayList<NodeGUI>();// (mainWindow, false,
											// "CAN Node #"+i);
		initNodes();

		// ///////////EVENT HANDLER for Adding node FIELD/////////////////
		addGUI.addMyKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				idTaken = false;
				addGUI.getLblIDTaken().setIcon(null);
				addGUI.setAlwaysOnTop(true);
				addGUI.setVisible(true);
				for (int i = 0; i < allNodes.size(); i++) {
					if (addGUI.getFldID().getText()
							.equals(allNodes.get(i).getID())) {
						idTaken = true;
						addGUI.getLblIDTaken().setIcon(addGUI.getIdTaken());
					}
				}
				numberOfChars = addGUI.getFldID().getText().length();
				addGUI.getLblCountIDBits().setText(
						String.valueOf(numberOfChars));
				if (Pattern.matches("[0-1]+", addGUI.getFldID().getText())) {
					if (numberOfChars == 11 || numberOfChars == 29) {
						okID = !idTaken;
					} else {
						okID = false;
					}
				}
				if (okID) {
					addGUI.getLblDValidity().setIcon(addGUI.getImageOK());
				} else {
					addGUI.getLblDValidity().setIcon(addGUI.getImageNotOK());
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		// //////////////////////////////////////////////////////////

		// ////EVENT HANDLER FOR MAIN ADDING NODE BUTTON///////////////////
		mainWindow.addButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (evt.getSource() == mainWindow.getBtnAddNode()) {
					
						int n = JOptionPane.showConfirmDialog(mainWindow, "<html>Simulations STOPS when adding new node!!<br>CONTINUE?</html>","ATTENTION!", JOptionPane.YES_OPTION);	
						if (n==JOptionPane.YES_OPTION)
						{
							addGUI.setVisible(true);
							Frame.resetAll();
						}
					
						}
						
					}
		
			
		});
		// ///////////////////////////////////////////////////

		// /////////EVENT HANDLER FOR DIALOG ADD NODE BUTTON/////////////
		addGUI.addMyButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == addGUI.getBtnAddNewNode()) {
					if (okID && !idTaken) {
						NodeDOM nDOM = new NodeDOM();
						Node newNode = new Node(addGUI.getFldID().getText(),
								"empty");
						nDOM.addNode(newNode);

						refreshNodes();

						idTaken = false;
						addGUI.getLblIDTaken().setIcon(null);
						addGUI.setAlwaysOnTop(true);
						addGUI.setVisible(true);
						for (int i = 0; i < allNodes.size(); i++) {
							if (addGUI.getFldID().getText()
									.equals(allNodes.get(i).getID())) {
								idTaken = true;
								addGUI.getLblIDTaken().setIcon(
										addGUI.getIdTaken());
							}
						}
						numberOfChars = addGUI.getFldID().getText().length();
						addGUI.getLblCountIDBits().setText(
								String.valueOf(numberOfChars));
						if (Pattern.matches("[0-1]+", addGUI.getFldID()
								.getText())) {
							if (numberOfChars == 11 || numberOfChars == 29) {
								okID = !idTaken;
							} else {
								okID = false;
							}
						}
						if (okID) {
							addGUI.getLblDValidity().setIcon(
									addGUI.getImageOK());
						} else {
							addGUI.getLblDValidity().setIcon(
									addGUI.getImageNotOK());
						}
					}
				}
			}
		});
		// ////////////////////////////////////////////////////////////////////////////

	}// Constructor end

	private class MyWindowAdapter extends WindowAdapter {
		private JDialog dialog;
		private int index;

		public MyWindowAdapter(JDialog dialog, int index) {
			this.dialog = dialog;
			this.index = index;
		}

		// ///////////////////EVENT HANDLER FOR REMOVING
		// NODES/////////////////////
		@Override
		public void windowClosing(WindowEvent e) {
			int n = JOptionPane.showConfirmDialog(mainWindow,
					"Are you sure you want to remove node" + index + "?",
					"Please Confirm Node Removal!", JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				NodeDOM nDOM = new NodeDOM();
				nDOM.removeNode(allNodes.get(this.index));
				dialog.dispose();
				allNodes.remove(index);
				nodeGUIs.remove(index);
				refreshNodes();
			}
		}
		// //////////////////////////////////////////////////////////////////////////

	}

	public static void main(String[] args) {
		Controller app = new Controller();
		app.mainWindow.setVisible(true); // make visual component appear
	}
}
