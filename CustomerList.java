class CustomerList{
	private Customer[] customerArray;
	private int nextIndex;
	private int initCapacity;
	private double loadFact;
	
	CustomerList(int initCapacity,double loadFact){
		this.initCapacity=initCapacity;
		this.loadFact=loadFact;
		nextIndex = 0;
		customerArray = new Customer[initCapacity];
	}
	private void extendArray(){
		Customer[] tempArray = new Customer[customerArray.length+(int)(customerArray.length*loadFact)];
		for (int i = 0; i < customerArray.length; i++){
			tempArray[i]=customerArray[i];
		}
		customerArray=tempArray;
	}
	
	public boolean addFirst(Customer customer){
		return add(0,customer);
	}
	public boolean addLast(Customer customer){
			return add(nextIndex,customer);
	}
	public boolean add(Customer customer){
		return addLast(customer);
	}
	public boolean add(int index,Customer customer){
		if(index>=0 && index<=nextIndex){
			if(nextIndex>=customerArray.length){
				extendArray();
			}
			
			for (int i = nextIndex-1; i >= index; i--){
				customerArray[i+1]=customerArray[i];
			}
			customerArray[index]=customer;
			nextIndex++;
			return true;
		}
		return false;
	}
	public boolean isEmpty(){
		return nextIndex==0;
	}
	public int size(){
		return nextIndex;
	}
	public int capacity(){
		return  customerArray.length;
	}
	public int indexOf(Customer customer){
		for (int i = 0; i < nextIndex; i++){
			if(customerArray[i]==customer){
				return i;
			}
		}
		return -1;
	}
	public int indexOfStr(String cusId){
		for(int i = 0; i < nextIndex; i++){
			String customer = customerArray[i].getCusId();
			if(customer.equalsIgnoreCase(cusId)){
				return i;
			}
		}
		return -1;
	}
	public boolean search(Customer customer){
		return indexOf(customer)!=-1;
	}
	
	public Customer get(int index){
		if(index>=0 && index<nextIndex){
			return customerArray[index];
		}
		return null;
	}
	public void removeFirst(){
		remove(0);
	}
	public void removeLast(){
		remove(nextIndex-1);
	}
	public boolean remove(int index){
		if(index>=0 && index<=nextIndex){
			for (int i = index; i < nextIndex-1; i++){
				customerArray[i]=customerArray[i+1];
			}
			nextIndex--;
			return true;
		}
		return false;
	}
	public boolean set(int index,Customer customer){
		if(index>=0 && index<nextIndex){
			customerArray[index]=customer;
			return true;
		}
		return false;
	}
	public boolean set(Customer customer){
		int index=indexOf(customer);
		return set(index,customer);
	}
	public String toString(){
		String list="[";
		for (int i = 0; i < nextIndex; i++){
			list+=customerArray[i]+", ";
		}
		return nextIndex==0 ? "[empty]":list+"\b\b]";
	}
	
	public void clear(){
		customerArray=new Customer[initCapacity];
		nextIndex=0;
	}
	
	public Customer[] toArray(){
		Customer[] tempArray=new Customer[nextIndex];
		for (int i = 0; i < nextIndex; i++)	{
			tempArray[i]=customerArray[i];
		}
		return tempArray;
	}
	
}
