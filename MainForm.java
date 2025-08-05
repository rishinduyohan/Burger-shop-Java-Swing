import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Color;
class MainForm extends JFrame implements ActionListener{
    private JLabel lblTitle,lblDis,lblicon,lblSubTitle,imLabel,lblBack;
    private JButton btnCusMgt,btnCancel,btnViewCus;
    private CustomerList cusList = new CustomerList(100,0.5);
     MainForm(){
        setSize(760, 500);
        setTitle("BunsLove Burger");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.DARK_GRAY);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel labelPanel = new JPanel(new GridLayout(3, 1));
        labelPanel.setBackground(new Color(51,0,0));

        lblicon = new JLabel("BunsLove");
        lblicon.setFont(new Font("", Font.BOLD, 40));
        lblicon.setForeground(new Color(255,255, 0));
        lblicon.setHorizontalAlignment(JLabel.CENTER);
        labelPanel.add(lblicon);
        
        lblTitle = new JLabel("Burger Shop");
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
        
        ImageIcon icon = new ImageIcon("assest/2.jpg");
		Image image = icon.getImage().getScaledInstance(760, 400, Image.SCALE_SMOOTH);
		imLabel = new JLabel(new ImageIcon(image));
		add(imLabel,BorderLayout.WEST);
		
		JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(Box.createVerticalStrut(50));

        btnCusMgt = new JButton("Customer Management");
        btnCusMgt.setFont(new Font("", Font.BOLD, 15));
        btnCusMgt.setMaximumSize(new Dimension(150, 50));
        btnCusMgt.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCusMgt.setBackground(new Color(51, 1, 0));
        btnCusMgt.setForeground(Color.WHITE);
        btnCusMgt.addActionListener(this);
        buttonPanel.add(btnCusMgt);
        
        btnViewCus = new JButton("View Customer");
        btnViewCus.setFont(new Font("", Font.BOLD, 15));
        btnViewCus.setMaximumSize(new Dimension(150, 50));
        btnViewCus.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnViewCus.setBackground(new Color(51, 1, 0));
        btnViewCus.setForeground(Color.WHITE);
        btnViewCus.addActionListener(this);
        buttonPanel.add(btnViewCus);
		
        btnCancel = new JButton("Exit");  
        btnCancel.setFont(new Font("", Font.BOLD, 15));
        btnCancel.setMaximumSize(new Dimension(150, 50));
        btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT); 
        btnCancel.setBackground(new Color(51, 1, 0));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.addActionListener(this);   
        buttonPanel.add(btnCancel);

        add(buttonPanel, BorderLayout.EAST);
        
        JPanel southPanel = new JPanel(new FlowLayout());
	    lblBack = new JLabel("AllRights * Reserved by Rishindu Yohan(CE25260028)");
		lblBack.setForeground(Color.WHITE);
        lblBack.setFont(new Font("", Font.ITALIC, 12));
		southPanel.add(lblBack);
		southPanel.setBackground(new Color(51,2,0));

		add(southPanel,BorderLayout.SOUTH);
		
	}
	public void actionPerformed(ActionEvent e){
			if(e.getSource()==btnCusMgt){
			  new CustomerMgt(cusList).setVisible(true);
			}
			if(e.getSource()==btnViewCus){
			  new ViewCustomer(cusList).setVisible(true);
			}
			if(e.getSource()==btnCancel){
			  dispose();
			}
			
	}
}
