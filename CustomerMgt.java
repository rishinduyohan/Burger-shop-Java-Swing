import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
class CustomerMgt extends JFrame implements ActionListener{
    private JLabel lblTitle,lblDis,lblicon,lblSubTitle,imLabel,lblBack;
    private JButton btnAddCus,btnUpdateCus,btnDeleteCusProf,btnBack;
    private CustomerList customerList;
    
    CustomerMgt(CustomerList customerListLocal){
		this.customerList = customerListLocal;
        setSize(760, 500);
        setTitle("Burger Shop");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.DARK_GRAY);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel labelPanel = new JPanel(new GridLayout(3, 1));
        labelPanel.setBackground(new Color(51,0,0));

        lblicon = new JLabel("Burger Shop");
        lblicon.setFont(new Font("", Font.BOLD, 35));
        lblicon.setForeground(new Color(255,255, 0));
        lblicon.setHorizontalAlignment(JLabel.CENTER);
        labelPanel.add(lblicon);
        
        lblTitle = new JLabel("Customer Management");
        lblTitle.setFont(new Font("", Font.BOLD, 35));
        lblTitle.setForeground(new Color(255,255, 0));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        labelPanel.add(lblTitle);

        lblSubTitle = new JLabel("Choose an option form the menu below to get started");
        lblSubTitle.setFont(new Font("", Font.BOLD, 15));
        lblSubTitle.setHorizontalAlignment(JLabel.CENTER);
        lblSubTitle.setForeground(Color.WHITE);
        labelPanel.add(lblSubTitle);
        add(labelPanel, BorderLayout.NORTH);
        
        ImageIcon icon = new ImageIcon("assest/1.jpg");
		Image image = icon.getImage().getScaledInstance(760, 400, Image.SCALE_SMOOTH);
		imLabel = new JLabel(new ImageIcon(image));
		add(imLabel,BorderLayout.EAST);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(Box.createVerticalStrut(50));

        btnAddCus = new JButton("Add Customer");
        btnAddCus.setFont(new Font("", Font.BOLD, 15));
        btnAddCus.setMaximumSize(new Dimension(150, 50));
        btnAddCus.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAddCus.setBackground(new Color(51, 1, 0));
        btnAddCus.setForeground(Color.WHITE);
        btnAddCus.addActionListener(this);
        buttonPanel.add(btnAddCus);

        btnUpdateCus = new JButton("Update Customer");
        btnUpdateCus.setFont(new Font("", Font.BOLD, 15));
        btnUpdateCus.setMaximumSize(new Dimension(150, 50));
        btnUpdateCus.setAlignmentX(Component.CENTER_ALIGNMENT); 
        btnUpdateCus.setBackground(new Color(51, 1, 0));
        btnUpdateCus.setForeground(Color.WHITE);
        btnUpdateCus.addActionListener(this);   
        buttonPanel.add(btnUpdateCus);

        btnDeleteCusProf = new JButton("Delete Customer");
        btnDeleteCusProf.setFont(new Font("", Font.BOLD, 15));
        btnDeleteCusProf.setMaximumSize(new Dimension(150, 50));
        btnDeleteCusProf.setAlignmentX(Component.CENTER_ALIGNMENT); 
        btnDeleteCusProf.setBackground(new Color(51, 1, 0));
        btnDeleteCusProf.setForeground(Color.WHITE);
        btnDeleteCusProf.addActionListener(this);   
        buttonPanel.add(btnDeleteCusProf);

        btnBack = new JButton("Exit");  
        btnBack.setFont(new Font("", Font.BOLD, 15));
        btnBack.setMaximumSize(new Dimension(150, 50));
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT); 
        btnBack.setBackground(Color.RED);
        btnBack.setForeground(Color.WHITE);
        btnBack.addActionListener(this);   
        buttonPanel.add(btnBack);

        add(buttonPanel, BorderLayout.WEST);
        
        JPanel southPanel = new JPanel(new FlowLayout());
	    lblBack = new JLabel("AllRights * Reserved by Rishindu Yohan(CE25260028)");
		lblBack.setForeground(Color.WHITE);
        lblBack.setFont(new Font("", Font.ITALIC, 12));
		southPanel.add(lblBack);
		southPanel.setBackground(new Color(51,2,0));

		add(southPanel,BorderLayout.SOUTH);
		
	}
	public void actionPerformed(ActionEvent e){
			if(e.getSource()==btnAddCus){
			  new AddCustomer(customerList).setVisible(true);
			  
			}else if(e.getSource()==btnUpdateCus){
			 new UpdateCustomer(customerList).setVisible(true);
			  
			}else if(e.getSource()==btnDeleteCusProf){
			  new DeleteCustomer(customerList).setVisible(true);
			}
			if(e.getSource()==btnBack){
			  dispose();
			}
	}
}
