/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kofisop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.time.LocalDate;

public class Adminpage extends javax.swing.JFrame {

    private Connection connection = null;
    private String dbURL = "jdbc:mysql://localhost/kofisopjava";
    private String dbUser = "root";
    private String dbPassword = "";
    private int userID;
    private String detail;
    
    public Adminpage() {
        initComponents();
        try {
            this.connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            tampilData("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void tampilData(String keyword) {
        try {
            String sql = "SELECT order_id, customer_name, menu_name, total_price FROM orders WHERE customer_name LIKE ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();

            // Buat model untuk tabel
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Order ID");
            tableModel.addColumn("Customer Name");
            tableModel.addColumn("Menu Name");
            tableModel.addColumn("Total Price");

            // Isi data ke dalam model
            while (resultSet.next()) {
                String orderID = resultSet.getString("order_id");
                String customerName = resultSet.getString("customer_name");
                String menuName = resultSet.getString("menu_name");
                String totalPrice = resultSet.getString("total_price");
                tableModel.addRow(new Object[]{orderID, customerName, menuName, totalPrice});
            }

            // Tampilkan data pada tabel
            tablepelanggan.setModel(tableModel);

            // Tutup resultSet dan statement
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    private void tambahData(String customerName, String menuName, double totalPrice,int userID, String detail) {
        try {
            String sql = "INSERT INTO orders (customer_name, menu_name, total_price, user_id, order_date, detail) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customerName);
            statement.setString(2, menuName);
            statement.setDouble(3, totalPrice);
            statement.setInt(4, userID);
            statement.setDate(5, Date.valueOf(LocalDate.now())); // Set order_date dengan tanggal saat ini
            statement.setString(6, detail); // Set detail pesanan
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
                tampilData("");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void ubahData(int orderID, String customerName, String menuName, double totalPrice) {
        try {
            String sql = "UPDATE orders SET customer_name = ?, menu_name = ?, total_price = ? WHERE order_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customerName);
            statement.setString(2, menuName);
            statement.setDouble(3, totalPrice);
            statement.setInt(4, orderID);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
                tampilData("");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void hapusData(int orderID) {
        try {
            String sql = "DELETE FROM orders WHERE order_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderID);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
                tampilData("");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablepelanggan = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        buttonsimpan = new javax.swing.JButton();
        buttonubah = new javax.swing.JButton();
        buttonreset = new javax.swing.JButton();
        buttonhapus = new javax.swing.JButton();
        idbar = new javax.swing.JTextField();
        Menubar = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        namebar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablepelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Order id", "Customer Name", "Menu Name", "Total Price"
            }
        ));
        jScrollPane1.setViewportView(tablepelanggan);

        jLabel1.setText("Form Admin");

        buttonsimpan.setText("Simpan");
        buttonsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonsimpanActionPerformed(evt);
            }
        });

        buttonubah.setText("Update");
        buttonubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonubahActionPerformed(evt);
            }
        });

        buttonreset.setText("Reset");

        buttonhapus.setText("Hapus");
        buttonhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonhapusActionPerformed(evt);
            }
        });

        Menubar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Breakfast 1", "Breakfast 2", "Lunch time", "Midnight Madness", " " }));

        jLabel2.setText("Order id");

        jLabel3.setText("Customer Name");

        jLabel4.setText("Select menu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(buttonreset)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(buttonhapus))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(buttonsimpan)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(buttonubah))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Menubar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(idbar)
                            .addComponent(namebar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(namebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Menubar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonsimpan)
                    .addComponent(buttonubah))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonreset)
                    .addComponent(buttonhapus))
                .addGap(156, 156, 156))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void buttonsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonsimpanActionPerformed
        // Ambil nilai dari input field
    String customerName = namebar.getText();
    String menuName = Menubar.getSelectedItem().toString();
    double totalPrice = 0.0; 
    
    int userID = 1;
    String detail = "Detail pesanan";
    setUserID(userID);
    setDetail(detail);
    // Panggil fungsi tambahData
    }//GEN-LAST:event_buttonsimpanActionPerformed

    private void buttonubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonubahActionPerformed
        // Ambil nilai dari input field
    int orderID = Integer.parseInt(idbar.getText());
    String customerName = namebar.getText();
    String menuName = Menubar.getSelectedItem().toString();
    double totalPrice = 0.0; // Isi sesuai dengan nilai yang diperlukan, misalnya dari input field Total Price

    // Panggil fungsi ubahData
    ubahData(orderID, customerName, menuName, totalPrice);
    }//GEN-LAST:event_buttonubahActionPerformed

    private void buttonhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonhapusActionPerformed
        // Ambil nilai dari input field Order ID
    String orderID = idbar.getText();

    // Panggil fungsi hapusData
    hapusData(Integer.parseInt(orderID));
    }//GEN-LAST:event_buttonhapusActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Menubar;
    private javax.swing.JButton buttonhapus;
    private javax.swing.JButton buttonreset;
    private javax.swing.JButton buttonsimpan;
    private javax.swing.JButton buttonubah;
    private javax.swing.JTextField idbar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField namebar;
    private javax.swing.JTable tablepelanggan;
    // End of variables declaration//GEN-END:variables

    private void tambahData(String customerName, String menuName, double totalPrice) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
