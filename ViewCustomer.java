import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
public class ViewCustomer extends JFrame implements ActionListener{
    private JLabel lblTitle,lblicon,lblBack,imLabel;
    private JButton btnBack;
    private JTable tableCustomer;
    private DefaultTableModel dtm;
	private CustomerList customerList;
    ViewCustomer(CustomerList customerListLocal){
		this.customerList=customerListLocal;
        setSize(800,600);
        setVisible(true);
		setTitle("Customer Report ");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBackground(new Color(51,2,0));
		setLocationRelativeTo(null);
		
		JPanel northPanel=new JPanel(new GridLayout(1,2));
		northPanel.setBackground(new Color(51,2,0));

        lblTitle=new JLabel("Customer Report");
		lblTitle.setFont(new Font("",1,25));
		lblTitle.setForeground(Color.WHITE);
        lblTitle.setHorizontalAlignment(0);
		northPanel.add(lblTitle);

		add(northPanel,BorderLayout.NORTH);
		
		ImageIcon icon = new ImageIcon("assest/5.jpg");
		Image image = icon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
		imLabel = new JLabel(new ImageIcon(image));
		add(imLabel,BorderLayout.NORTH);
        

        JPanel centerPanel = new JPanel(new GridLayout(2,1,10,10));
        
        String[] columnsName = {"No","Registration No","Customer Name","Quantity","Order Status"};
        dtm = new DefaultTableModel(columnsName,0);  
        tableCustomer = new JTable(dtm);
        JScrollPane tablePane = new JScrollPane(tableCustomer); 
        centerPanel.add(tablePane);
        studentDetails();
        
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        btnBack=new JButton("<- Back to Home Page");
        btnBack.setFont(new Font("",1,18));
        btnBack.setBackground(new Color(51,2,0));
        btnBack.setForeground(Color.WHITE);
        btnBack.addActionListener(this);
        btnPanel.add(btnBack);

        centerPanel.add(btnPanel);

        add(centerPanel,BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new FlowLayout());
	    lblBack = new JLabel("AllRights * Reserved by Rishindu Yohan(CE25260028)");
		lblBack.setForeground(Color.WHITE);
        lblBack.setFont(new Font("", Font.ITALIC, 12));
		southPanel.add(lblBack);
		southPanel.setBackground(new Color(51,2,0));

		add(southPanel,BorderLayout.SOUTH);
    }

    public void studentDetails(){
        int count=1;
        customerList.clear();
        File customerFile = new File("CustomerFile.txt");
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
			
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
        
        
        Customer[] customerArray = customerList.toArray();
        for(Customer c1:customerArray){
            Object[] rowData = {count++,c1.getCusId(),c1.getName(),c1.getQty(),c1.getOrderStatus(c1.getStatus())};
            dtm.addRow(rowData);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==btnBack){
            dispose();
       }
    }
}
