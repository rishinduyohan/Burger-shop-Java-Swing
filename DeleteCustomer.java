import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.util.*;
import java.io.*;
import java.lang.*;
class DeleteCustomer extends JFrame implements ActionListener{
	private JLabel lblTitle,lblBack;
	private JButton btnSearch,btnCancel,btnDelete;
	
	private JLabel lblCusId,lblName,lblQty,lblStatus;
	
	private JTextField txtCusId,txtName,txtQty,txtStatus,txtSearch;
	private CustomerList customerList;
    private Customer cus;
    public  int index;
    
    DeleteCustomer(CustomerList customerListLocal){
		this.customerList = customerListLocal;
		setSize(600,350);
        setVisible(true);
		setTitle("Delete Customer Form");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setBackground(Color.LIGHT_GRAY);
		
		JPanel northPanel=new JPanel(new GridLayout(1,2));
		northPanel.setBackground(new Color(51,2,0));

		lblTitle=new JLabel("Delete Customer");
		lblTitle.setFont(new Font("",1,25));
		lblTitle.setForeground(new Color(255,255,0));
		lblTitle.setHorizontalAlignment(0);
		northPanel.add(lblTitle);

		add(northPanel,BorderLayout.NORTH);
		
		JPanel southRowPanel = new JPanel(new GridLayout(2,1,10,10));
		
		JPanel buttonPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnDelete=new JButton("Delete Customer");
		btnDelete.setFont(new Font("",1,18));
		btnDelete.setBackground(new Color(51,2, 0));
		btnDelete.setForeground(Color.WHITE);
		buttonPanel.add(btnDelete);

		btnCancel=new JButton("Cancel");
		btnCancel.setFont(new Font("",1,18));
		btnCancel.setBackground(Color.RED);
		btnCancel.setForeground(Color.WHITE);
		buttonPanel.add(btnCancel);
		
		southRowPanel.add(buttonPanel);
		
		JPanel southPanel = new JPanel(new FlowLayout());
	    lblBack = new JLabel("AllRights * Reserved by Rishindu Yohan(CE25260028)");
		lblBack.setForeground(Color.WHITE);
        lblBack.setFont(new Font("", Font.ITALIC, 12));
		southPanel.add(lblBack);
		southPanel.setBackground(new Color(51,2,0));

		southRowPanel.add(southPanel);
		
		add(southRowPanel,BorderLayout.SOUTH);
		
		
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		txtSearch=new JTextField();
		txtSearch.setColumns(10);
		txtSearch.setFont(new Font("",1,18));
		searchPanel.add(txtSearch);		
		
		btnSearch=new JButton("Search");
		btnSearch.setFont(new Font("",1,20));
		btnSearch.setBackground(Color.BLUE);
		btnSearch.setForeground(Color.WHITE);
		btnSearch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				File customerFile=new File("CustomerFile.txt");
				customerList.clear();
				try{
					Scanner input = new Scanner(customerFile);
					while(input.hasNext()){
						String line=input.nextLine();
						if (line != null && !line.trim().isEmpty()) {
							String[] rowData=line.split(",");
							String id=rowData[0];
							String name=rowData[1];
							int qty=Integer.parseInt(rowData[2]);
							int status=Integer.parseInt(rowData[3]);
							Customer cus=new Customer(id,name,qty,status);
							customerList.add(cus);
						}
					}
				
				input.close();	
				
				if(customerFile.exists()){
					customerFile.delete();
					System.out.println("File deleted");
				}else{
					System.out.println("File not exist");
				}
				
				String cusId = txtSearch.getText();
				index = customerList.indexOfStr(cusId);
				cus=customerList.get(index);
				
				if(cus==null){
					JOptionPane.showMessageDialog(null, "Customer Not Found.","Not Found",JOptionPane.ERROR_MESSAGE);
					clearTextField();
				}
				int qty = cus.getQty();
				int order = cus.getStatus();
				
				txtCusId.setText(cus.getCusId());
				txtName.setText(cus.getName());
				txtQty.setText(""+qty);
				txtStatus.setText(cus.getOrderStatus(order));
				
				}catch(IOException ex){
					System.out.println(ex.getMessage());
				}
			}
		});
		
		searchPanel.add(btnSearch);

		add(searchPanel,BorderLayout.EAST);
		
		JPanel labelPanel=new JPanel(new GridLayout(4,1,4,4));
		
		lblCusId=new JLabel("Customer ID : ");
		lblCusId.setFont(new Font("",1,18));
		labelPanel.add(lblCusId);

		lblName=new JLabel("Name : ");
		lblName.setFont(new Font("",1,18));
		labelPanel.add(lblName);

		lblQty=new JLabel("Qty : ");
		lblQty.setFont(new Font("",1,18));
		labelPanel.add(lblQty);
		
		lblQty=new JLabel("Order Status : ");
		lblQty.setFont(new Font("",1,18));
		labelPanel.add(lblQty);

		add("West",labelPanel);
		
		JPanel textPanel=new JPanel(new GridLayout(4,1,4,4));
		
		JPanel idPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		txtCusId=new JTextField(10);
		txtCusId.setFont(new Font("",1,18));
		idPanel.add(txtCusId);
		textPanel.add(idPanel);
		
		JPanel namePanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		txtName=new JTextField(10);
		txtName.setFont(new Font("",1,18));
		namePanel.add(txtName);
		textPanel.add(namePanel);
		
		JPanel qtyPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		txtQty=new JTextField(10);
		txtQty.setFont(new Font("",1,18));
		qtyPanel.add(txtQty);
		textPanel.add(qtyPanel);
		
		JPanel StatusPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		txtStatus=new JTextField(10);
		txtStatus.setFont(new Font("",1,18));
		StatusPanel.add(txtStatus);
		textPanel.add(StatusPanel);
		
		add("Center",textPanel);
        btnDelete.addActionListener(this);
        btnCancel.addActionListener(this);
        
      
     }
	public void clearTextField(){
		txtCusId.setText("");
		txtName.setText("");
		txtQty.setText("");
		txtStatus.setText("");
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnDelete){
			boolean isDeleted = customerList.remove(index);
			if(isDeleted){
				try{
					FileWriter fw = new FileWriter("CustomerFile.txt",true);
					for (int i = 0; i < customerList.size(); i++){
						Customer c1 = customerList.get(i);
						fw.write(c1+"\n");
					}
					fw.close();
				}catch(IOException ex){
					System.out.println(ex.getMessage());
				}
				JOptionPane.showConfirmDialog(null, "Customer Data Deleted Sucessfully..","Customer Deleted",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
				clearTextField();
			}else{
				JOptionPane.showMessageDialog(null, "Customer Deleted Failed..","Not Deleted",JOptionPane.ERROR_MESSAGE);
				clearTextField();
			}
		}
		if(e.getSource()==btnCancel){
            dispose();
        }
	}
}


