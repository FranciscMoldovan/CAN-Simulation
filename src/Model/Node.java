package Model;

public class Node {
	private String ID;//11 or 29 bit identifier
	private String data;//data field
	private int len; 
	private String txMessage;
	private String rxMessage;
	
	

	public Node(String ID, String data){
		this.ID=ID;
		this.data=data;

		
		//no messages when node is created
		this.txMessage="empty";
		this.rxMessage="empty";
	}
	

	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public String getTxMessage() {
		return txMessage;
	}
	public void setTxMessage(String txMessage) {
		this.txMessage = txMessage;
	}
	public String getRxMessage() {
		return rxMessage;
	}
	public void setRxMessage(String rxMessage) {
		this.rxMessage = rxMessage;
	}


	public int getLen() {
		return len;
	}


	public void setLen(int len) {
		this.len = len;
	}

	

	
	
}

