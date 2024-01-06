import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import javax.swing.table.DefaultTableModel;



public class FirstAPP {

	private JFrame frame;
	private JTextField bk_nametxt;
	private JTextField bk_editiontxt;
	private JTextField bk_pricetxt;
	private JTextField bk_idtxt;
    private Connection con; 
    private DefaultTableModel tableModel;
    


	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstAPP window = new FirstAPP();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 


	public FirstAPP() {
		initialize();
		connect();
	    
	}

	

	public void connect() {
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/projectjava", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error connecting to the database");
        }
        updatePanelData();
    }
	private void initialize() {
		
		 tableModel = new DefaultTableModel();
		    tableModel.addColumn("ID");
		    tableModel.addColumn("Book Name");
		    tableModel.addColumn("Edition");
		    tableModel.addColumn("Price");
		    JTable table = new JTable(tableModel);
		frame = new JFrame();
		frame.setBounds(100, 100, 813, 551);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setBounds(315, 19, 144, 46);
		lblNewLabel.setFont(new Font("SimSun-ExtB", Font.BOLD, 30));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 85, 328, 333);
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book  Name :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 60, 111, 30);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Edition : ");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(10, 126, 111, 30);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Price :");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(10, 188, 111, 30);
		panel.add(lblNewLabel_1_3);
		
		bk_nametxt = new JTextField();
		bk_nametxt.setBounds(131, 68, 134, 19);
		panel.add(bk_nametxt);
		bk_nametxt.setColumns(10);
		
		bk_editiontxt = new JTextField();
		bk_editiontxt.setColumns(10);
		bk_editiontxt.setBounds(131, 134, 134, 19);
		panel.add(bk_editiontxt);
		
		bk_pricetxt = new JTextField();
		bk_pricetxt.setColumns(10);
		bk_pricetxt.setBounds(131, 196, 134, 19);
		panel.add(bk_pricetxt);
		
		
		JButton btnsave = new JButton("Save");
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				saveDataToDatabase();
				updatePanelData();
 
            }
			

		});
		
		btnsave.setForeground(new Color(64, 0, 64));
		btnsave.setBackground(new Color(64, 0, 128));
		btnsave.setBounds(22, 256, 85, 56);
		panel.add(btnsave);
		
		JButton btnexit = new JButton("Exit");
		btnexit.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				 int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
			        if (result == JOptionPane.YES_OPTION) {
			           
			            try {
			                if (con != null && !con.isClosed()) {
			                    con.close();
			                }
			            } catch (SQLException ex) {
			                ex.printStackTrace();
			            }
			           
			            System.exit(0);
			        }
			}
		});
		btnexit.setForeground(new Color(64, 0, 64));
		btnexit.setBackground(new Color(64, 0, 128));
		btnexit.setBounds(117, 256, 85, 56);
		panel.add(btnexit);
		
		JButton btnclear = new JButton("Clear");
		btnclear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
		        bk_nametxt.setText("");
		        bk_editiontxt.setText("");
		        bk_pricetxt.setText("");
			}
		});
		btnclear.setForeground(new Color(64, 0, 64));
		btnclear.setBackground(new Color(64, 0, 128));
		btnclear.setBounds(212, 256, 85, 56);
		panel.add(btnclear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 417, 328, 84);
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Book ID :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 40, 75, 13);
		panel_1.add(lblNewLabel_2);
		
		bk_idtxt = new JTextField();
		bk_idtxt.setBounds(83, 39, 228, 19);
		panel_1.add(bk_idtxt);
		bk_idtxt.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(352, 430, 189, 71);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
		        String bookId = bk_idtxt.getText();
		        
		        if (bookId.isEmpty()) {
		            JOptionPane.showMessageDialog(frame, "Please enter the book ID to update.", "Update Error", JOptionPane.ERROR_MESSAGE);
		            return;
		           
		        }
		       
		        String bookName = bk_nametxt.getText();
		        String bookEdition = bk_editiontxt.getText();
		        String bookPrice = bk_pricetxt.getText();

		        if (bookName.isEmpty() && bookEdition.isEmpty() && bookPrice.isEmpty()) {
		            JOptionPane.showMessageDialog(frame, "Please enter at least one field (Name, Edition, or Price) to update.", "Update Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        try {
		            
		            String sql = "UPDATE books SET";
		            boolean needsComma = false;

		            
		            if (!bookName.isEmpty()) {
		                sql += " Bookname = ?";
		                needsComma = true;
		            }

		            if (!bookEdition.isEmpty()) {
		                if (needsComma) {
		                    sql += ",";
		                }
		                sql += " Edition = ?";
		                needsComma = true;
		            }

		            if (!bookPrice.isEmpty()) {
		                if (needsComma) {
		                    sql += ",";
		                }
		                sql += " Price = ?";
		            }

		            sql += " WHERE ID = ?";

		            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
		                
		                int parameterIndex = 1;
		                if (!bookName.isEmpty()) {
		                    preparedStatement.setString(parameterIndex++, bookName);
		                }

		                if (!bookEdition.isEmpty()) {
		                    preparedStatement.setString(parameterIndex++, bookEdition);
		                }

		                if (!bookPrice.isEmpty()) {
		                    preparedStatement.setString(parameterIndex++, bookPrice);
		                }

		                
		                preparedStatement.setString(parameterIndex, bookId);

		                
		                int rowsAffected = preparedStatement.executeUpdate();

		                if (rowsAffected > 0) {
		                    JOptionPane.showMessageDialog(frame, "Book information updated successfully!");
		                } else {
		                    JOptionPane.showMessageDialog(frame, "Book with ID " + bookId + " not found.", "Update Error", JOptionPane.ERROR_MESSAGE);
		                }

		                
		                bk_idtxt.setText("");
		                bk_nametxt.setText("");
		                bk_editiontxt.setText("");
		                bk_pricetxt.setText("");
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(frame, "Error updating book information", "Update Error", JOptionPane.ERROR_MESSAGE);
		        }
		       
                updatePanelData();
			}
		});
		btnUpdate.setForeground(new Color(64, 0, 64));
		btnUpdate.setBackground(new Color(64, 0, 128));
		frame.getContentPane().add(btnUpdate);
		
		JButton bntdelete = new JButton("Delete");
		bntdelete.setBounds(566, 430, 202, 71);
		bntdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		        String bookId = bk_idtxt.getText();

		       
		        if (bookId.isEmpty()) {
		            JOptionPane.showMessageDialog(frame, "Please enter the book ID to delete.", "Delete Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        try {
		           
		            String sql = "DELETE FROM books WHERE ID = ?";

		            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
		               
		                preparedStatement.setString(1, bookId);

		               
		                int rowsAffected = preparedStatement.executeUpdate();

		                if (rowsAffected > 0) {
		                    JOptionPane.showMessageDialog(frame, "Book with ID " + bookId + " deleted successfully!");
		                } else {
		                    JOptionPane.showMessageDialog(frame, "Book with ID " + bookId + " not found.", "Delete Error", JOptionPane.ERROR_MESSAGE);
		                }

		                
		                bk_idtxt.setText("");
		                bk_nametxt.setText("");
		                bk_editiontxt.setText("");
		                bk_pricetxt.setText("");
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(frame, "Error deleting book", "Delete Error", JOptionPane.ERROR_MESSAGE);
		        }
		        updatePanelData();
			}
		});
		bntdelete.setForeground(new Color(64, 0, 64));
		bntdelete.setBackground(new Color(64, 0, 128));
		frame.getContentPane().add(bntdelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(348, 85, 441, 317);
		frame.getContentPane().add(scrollPane);
		
		
		scrollPane.setViewportView(table);
		
		
		
		
	}
	private void saveDataToDatabase() {
        try {
            
            String sql = "INSERT INTO books (Bookname, Edition, Price) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                
                preparedStatement.setString(1, bk_nametxt.getText());
                preparedStatement.setString(2, bk_editiontxt.getText());
                preparedStatement.setString(3, bk_pricetxt.getText());

              
                preparedStatement.executeUpdate();

                
                JOptionPane.showMessageDialog(frame, "Data saved successfully!");

            
                bk_nametxt.setText("");
                bk_editiontxt.setText("");
                bk_pricetxt.setText("");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error saving data to the database");
        }
        
}
	private void updatePanelData() {
        try {
            
            String sql = "SELECT * FROM books";

            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();

              
                tableModel.setRowCount(0);

            
                while (resultSet.next()) {
                    Object[] row = {
                            resultSet.getString("ID"),
                            resultSet.getString("Bookname"),
                            resultSet.getString("Edition"),
                            resultSet.getString("Price")
                    };
                    tableModel.addRow(row);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error updating panel data", "Update Panel Error", JOptionPane.ERROR_MESSAGE);
        }

	}
	}

