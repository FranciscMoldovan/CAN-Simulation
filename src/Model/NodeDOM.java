package Model;


import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class NodeDOM {

	public void addNode(Node newNode){
		try{
				Document d=DOMHelper.getDocument("src\\Model\\nodes.xml");
				
			    Element nodes=d.getDocumentElement();
				
				//Create node tag
				Element node=d.createElement("node"); 
				
				//Create ID tag
				Element ID=d.createElement("ID");
				ID.appendChild(d.createTextNode(newNode.getID()));
				node.appendChild(ID);
				
				//Create data tag
				Element data=d.createElement("data"); 
				data.appendChild(d.createTextNode(newNode.getData()));
				node.appendChild(data);
								
				//Create rxMessage tag
				Element rxMessage=d.createElement("rxMessage"); 
				rxMessage.appendChild(d.createTextNode(newNode.getRxMessage()));
				node.appendChild(rxMessage);
				
				
				//Create txMessage tag
				Element txMessage=d.createElement("txMessage"); 
				txMessage.appendChild(d.createTextNode(newNode.getTxMessage()));
				node.appendChild(txMessage);	
				nodes.appendChild(node);
				DOMHelper.saveXMLContent(d,"src\\Model\\nodes.xml");
			}catch(Exception e){
			System.out.println(e.getMessage());
		}
		}
	
	public ArrayList<Node> getAllNodes(){
		ArrayList<Node> allNodes= new ArrayList<Node>();
		try{
			Document d = DOMHelper.getDocument("src\\Model\\nodes.xml");
			NodeList n1=d.getElementsByTagName("node"); 
			for (int i = 0; i < n1.getLength(); i++) {
				Element enode=(Element) n1.item(i);
					String ID   = String.valueOf(enode.getElementsByTagName("ID").item(0).getTextContent());
					String data = String.valueOf(enode.getElementsByTagName("data").item(0).getTextContent());
			
				Node aNode = new Node(ID,data);
					
				allNodes.add(aNode);
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
	}
		return allNodes;
	}
	
	public void removeNode(Node aNode){
		try{
				String ID = aNode.getID();
				
			
				Document d = DOMHelper.getDocument("src\\Model\\nodes.xml");
				NodeList n1=d.getElementsByTagName("node"); 
				for (int i = 0; i < n1.getLength(); i++) {
					Element enode=(Element) n1.item(i);
					if(enode.getElementsByTagName("ID").item(0).getTextContent().equals(ID)) 	
					{
								enode.getParentNode().removeChild(enode);
					}
				}
				//Write to file
				DOMHelper.saveXMLContent(d, "src\\Model\\nodes.xml");
			}catch (Exception e){
				System.out.println(e.getMessage());
		}
		}
}
