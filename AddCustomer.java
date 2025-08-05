import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.util.*;
import java.io.*;
import java.lang.*;
class AddCustomer extends JFrame implements ActionListener{
	private JLabel lblTitle,imLabel,lblBack;
	private JButton btnAdd,btnCancel,btnPrice;
	
	private JLabel lblCusId,lblName,lblQty,lblPrice;
	
	private JTextField txtCusId,txtName,txtQty,txtPrice;
	private CustomerList customerList;
    private Customer cus;
    public  int index;
    
    AddCustomer(CustomerList customerListLocal){
		this.customerList = customerListLocal;
		setSize(600,350);
        setVisible(true);
		setTitle("Add Customer Form");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JPanel northPanel=new JPanel(new GridLayout(1,2));
		northPanel.setBackground(new Color(51,2,0));

		lblTitle=new JLabel("Add Customer");
		lblTitle.setFont(new Font("",1,25));
		lblTitle.setForeground(new Color(255,255, 0));
		lblTitle.setHorizontalAlignment(0);
		northPanel.add(lblTitle);

		add(northPanel,BorderLayout.NORTH);
		
		ImageIcon icon = new ImageIcon("assest/6.png");
		Image image = icon.getImage().getScaledInstance(300, 350, Image.SCALE_SMOOTH);
		imLabel = new JLabel(new ImageIcon(image));
		add(imLabel,BorderLayout.EAST);
        
        JPanel southRowPanel = new JPanel(new GridLayout(2,1,10,10));
        
		JPanel buttonPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnAdd=new JButton("Add Customer");
		btnAdd.setFont(new Font("",1,18));
		btnAdd.setBackground(new Color(51,2,0));
		btnAdd.setForeground(Color.WHITE);
		buttonPanel.add(btnAdd);
		
		btnPrice=new JButton("Get price");
		btnPrice.setFont(new Font("",1,18));
		btnPrice.setBackground(new Color(51,2,0));
		btnPrice.setForeground(Color.WHITE);
		buttonPanel.add(btnPrice);
		btnPrice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int qty = Integer.parseInt(txtQty.getText());
				txtPrice.setText(""+qty*500);
			}
		});
		
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
		
		JPanel labelPanel=new JPanel(new GridLayout(5,1,4,4));
		lblCusId=new JLabel("Customer ID : ");
		lblCusId.setFont(new Font("",1,18));
		labelPanel.add(lblCusId);

		lblName=new JLabel("Name : ");
		lblName.setFont(new Font("",1,18));
		labelPanel.add(lblName);

		lblQty=new JLabel("Qty : ");
		lblQty.setFont(new Font("",1,18));
		labelPanel.add(lblQty);
		
		lblPrice=new JLabel("Price : ");
		lblPrice.setFont(new Font("",1,18));
		labelPanel.add(lblPrice);

		add("West",labelPanel);
		
		JPanel textPanel=new JPanel(new GridLayout(5,1,4,4));
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
		
		JPanel pricePanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		txtPrice=new JTextField(10);
		txtPrice.setFont(new Font("",1,18));
		pricePanel.add(txtPrice);
		textPanel.add(pricePanel);
		
		add("Center",textPanel);
        btnAdd.addActionListener(this);
        btnCancel.addActionListener(this);
        
        fileWrite();
     }
	public void clearTextField(){
		txtCusId.setText("");
		txtName.setText("");
		txtQty.setText("");
		txtPrice.setText("");
	}
	public void fileWrite(){
		File customerFile = new File("CustomerFile.txt");
				try{
					Scanner input = new Scanner(customerFile);
					while(input.hasNext()){
						String line = input.nextLine();
						if(line!=null && !line.trim().isEmpty()){
							String[] rowData = line.split(",");
							String cusid=rowData[0];
							String cusname=rowData[1];
							int cusqty=Integer.parseInt(rowData[2]);
							int cusorder=Integer.parseInt(rowData[3]);
							Customer newCus=new Customer(cusid,cusname,cusqty,cusorder);
							customerList.add(newCus);
						}	
					}
					input.close();
					if(customerFile.exists()){
						customerFile.delete();
						System.out.println("File deleted");
					}else{
						System.out.println("File not exist");
					}
					
				}catch(IOException ex){
					System.out.println(ex.getMessage());
				}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnAdd){
			    
				String id = txtCusId.getText();
				String name = txtName.getText();
				int qty = Integer.parseInt(txtQty.getText());
				
				Customer c1 = new Customer(id,name,qty,0);
				customerList.add(c1);
				
				try{
					FileWriter fw = new FileWriter("CustomerFile.txt",true);
					for (int i = 0; i < customerList.size(); i++){
						Customer cus = customerList.get(i);
						fw.write(cus+"\n");
					}
					fw.close();
					JOptionPane.showConfirmDialog(null, "Customer Added Successfully." ,"Confirm",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
					clearTextField();
					
				}catch(IOException ex){
					System.out.println(ex.getMessage());
				}
		}
		if(e.getSource()==btnCancel){
            dispose();
        }
	}
}
