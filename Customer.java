class Customer{
	private String cusId;
	private String name;
	private int qty;
	private int orderStatus;
	
	Customer(String cusId,String name,int qty,int status){
		this.cusId=cusId;
		this.name=name;
		this.qty=qty;
		this.orderStatus = status;
	}
	public void setCusId(String cusId){
		this.cusId=cusId;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setQty(int qty){
		this.qty=qty;
	}
	public void setStatus(int status){
		this.orderStatus=status;
	}
	public String getCusId(){
		return cusId;
	}
	public String getName(){
		return name;
	}
	public int getQty(){
		return qty;
	}
	public int getStatus(){
		return orderStatus;
	}
	 public String toString(){
		return cusId+","+name+","+qty+","+orderStatus;
	}
	public String getOrderStatus(int orderStatus){
		if (orderStatus==0)return "Preparing";
		if (orderStatus==1)return "Deliverd";
		if (orderStatus==2)return "Cancel";
		
		return "Cannot Find";
	}
}
