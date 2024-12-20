import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.table.TableModel;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asus
 */
public class AplikasiPengelolaanKontak extends javax.swing.JFrame {
    private final DataBaseHelper dbHelper;

    /**
     * Creates new form AplikasiPengelolaanKontak
     */
    public AplikasiPengelolaanKontak() {
        initComponents();
        dbHelper = new DataBaseHelper(); // Inisialisasi dbHelper di sini
        loadKontakData(); // Memuat data kontak pada saat form pertama kali dibuka
        buttonTambah.addActionListener(this::buttonTambahActionPerformed);
        buttonEdit.addActionListener(this::buttonEditActionPerformed);
        buttonHapus.addActionListener(this::buttonHapusActionPerformed);
        buttonCari.addActionListener(this::buttonCariActionPerformed);
        buttonEkspor.addActionListener(e -> eksporToCSV());
        buttonImpor.addActionListener(e -> imporToCSV());
        
    tabelKontak.getSelectionModel().addListSelectionListener(e -> {
    if (!e.getValueIsAdjusting() && tabelKontak.getSelectedRow() != -1) {
        int selectedRow = tabelKontak.getSelectedRow();
        textNama.setText((String) tabelKontak.getValueAt(selectedRow, 1)); // Kolom Nama
        textNoTelepon.setText((String) tabelKontak.getValueAt(selectedRow, 2)); // Kolom Nomor
        comboBoxKategori.setSelectedItem((String) tabelKontak.getValueAt(selectedRow, 3)); // Kolom Kategori
    }
});

    }
    
    private void eksporToCSV() {
    try (FileWriter writer = new FileWriter("kontak.csv")) {
        // Tulis header kolom
        writer.append("ID,Nama,Nomor,Kategori\n");

        // Dapatkan daftar kontak dari database dan tulis ke CSV
        for (String[] kontak : dbHelper.getKontakList()) {
            writer.append(String.join(",", kontak));
            writer.append("\n");
        }
        
        JOptionPane.showMessageDialog(this, "Data berhasil diekspor ke kontak.csv!");
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Gagal mengekspor data!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    private void imporToCSV() {
    try (BufferedReader reader = new BufferedReader(new FileReader("kontak.csv"))) {
        String line;
        boolean header = true;
        
        while ((line = reader.readLine()) != null) {
            if (header) { // Lewati baris header
                header = false;
                continue;
            }
            
            String[] data = line.split(",");
            if (data.length == 4) { // Pastikan ada 4 kolom (ID, Nama, Nomor, Kategori)
                String nama = data[1];
                String nomor = data[2];
                String kategori = data[3];
                
                dbHelper.tambahKontak(nama, nomor, kategori);
            }
        }
        
        JOptionPane.showMessageDialog(this, "Data berhasil diimpor dari kontak.csv!");
        loadKontakData(); // Refresh data di JTable setelah impor
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Gagal mengimpor data!", "Error", JOptionPane.ERROR_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelKontak = new javax.swing.JTable();
        textNama = new javax.swing.JTextField();
        textNoTelepon = new javax.swing.JTextField();
        comboBoxKategori = new javax.swing.JComboBox<>();
        textPencarian = new javax.swing.JTextField();
        buttonTambah = new javax.swing.JButton();
        buttonCari = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        buttonHapus = new javax.swing.JButton();
        buttonEkspor = new javax.swing.JButton();
        butttonKeluar = new javax.swing.JButton();
        buttonImpor = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listKategori = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nama           :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("No Telepon :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Kategori      :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Pencarian Nama/No.Telepon :");

        tabelKontak.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelKontak);

        textNama.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        textNoTelepon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        comboBoxKategori.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comboBoxKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Papa", "Mama", "Kakak", "Adek", "Paman", "Tante", "Nenek", "Kai", "Sahabat", "Teman", "Keluarga", "Dosen", "Kantor" }));

        textPencarian.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        buttonTambah.setBackground(new java.awt.Color(204, 255, 204));
        buttonTambah.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        buttonTambah.setText("Tambah");
        buttonTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTambahActionPerformed(evt);
            }
        });

        buttonCari.setBackground(new java.awt.Color(204, 255, 204));
        buttonCari.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        buttonCari.setText("Cari");
        buttonCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCariActionPerformed(evt);
            }
        });

        buttonEdit.setBackground(new java.awt.Color(204, 255, 204));
        buttonEdit.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        buttonEdit.setText("Edit");
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });

        buttonHapus.setBackground(new java.awt.Color(204, 255, 204));
        buttonHapus.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        buttonHapus.setText("Hapus");
        buttonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHapusActionPerformed(evt);
            }
        });

        buttonEkspor.setBackground(new java.awt.Color(204, 255, 204));
        buttonEkspor.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        buttonEkspor.setText("Ekspor -> CSV");
        buttonEkspor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEksporActionPerformed(evt);
            }
        });

        butttonKeluar.setBackground(new java.awt.Color(204, 255, 204));
        butttonKeluar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        butttonKeluar.setText("Keluar");
        butttonKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butttonKeluarActionPerformed(evt);
            }
        });

        buttonImpor.setBackground(new java.awt.Color(204, 255, 204));
        buttonImpor.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        buttonImpor.setText("Impor");
        buttonImpor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonImporActionPerformed(evt);
            }
        });

        listKategori.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Papa", "Mama", "Kakak", "Adek", "Paman", "Tante", "Nenek", "Kai", "Sahabat", "Teman", "Keluarga", "Dosen", "Kantor" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listKategori.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listKategoriValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listKategori);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(buttonHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonEkspor)
                        .addGap(27, 27, 27)
                        .addComponent(buttonImpor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(171, 171, 171)
                        .addComponent(butttonKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 944, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCari, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxKategori, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textNama, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textNoTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(buttonTambah)
                        .addGap(179, 179, 179)))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(textNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(textNoTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(comboBoxKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(buttonTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(86, 86, 86)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCari, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(butttonKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonImpor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(buttonHapus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(buttonEkspor, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("APLIKASI PENGELOLAAN KONTAK");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(249, 249, 249)
                .addComponent(jLabel1)
                .addContainerGap(261, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonEksporActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEksporActionPerformed
    try (FileWriter writer = new FileWriter("kontak.csv")) {
        // Tulis header kolom
        writer.append("NAMA,NOMOR,KATEGORI\n");

        // Ambil model tabel
        DefaultTableModel model = (DefaultTableModel) tabelKontak.getModel();
        int rowCount = model.getRowCount();

        // Iterasi setiap baris di tabel dan tulis ke CSV
        for (int i = 0; i < rowCount; i++) {
            String nama = (String) model.getValueAt(i, 1); // Kolom Nama
            String nomor = (String) model.getValueAt(i, 2); // Kolom Nomor
            String kategori = (String) model.getValueAt(i, 3); // Kolom Kategori

            // Tulis data ke file CSV
            writer.append(nama).append(",").append(nomor).append(",").append(kategori).append("\n");
        }

        // Baca data dari TextField
        String namaBaru = textNama.getText().trim(); // Ambil teks dari TextField Nama
        String nomorBaru = textNoTelepon.getText().trim(); // Ambil teks dari TextField Nomor
        String kategoriBaru = (String) listKategori.getSelectedValue(); // Ambil elemen yang dipilih dari JList


        // Validasi input (opsional)
        if (namaBaru.isEmpty() || nomorBaru.isEmpty() || kategoriBaru.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; // Keluar jika ada field kosong
        }

        // Tambahkan data baru ke tabel
        model.addRow(new Object[]{rowCount + 1, namaBaru, nomorBaru, kategoriBaru});

        // Simpan data baru ke database
        dbHelper.tambahKontak(namaBaru, nomorBaru, kategoriBaru);

        JOptionPane.showMessageDialog(this, "Data berhasil diekspor ke CSV dan ditambahkan ke tabel.");
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Gagal mengekspor data: " + e.getMessage());
        e.printStackTrace(); // Log kesalahan
    }

    }//GEN-LAST:event_buttonEksporActionPerformed

    private void buttonTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTambahActionPerformed
    String nama = textNama.getText();
    String nomor = textNoTelepon.getText();
    String kategori = (String) comboBoxKategori.getSelectedItem();
    
    if (!nama.isEmpty() && !nomor.isEmpty() && kategori != null) {
        dbHelper.tambahKontak(nama, nomor, kategori);
        loadKontakData(); // Refresh data di JTable
        textNama.setText("");
        textNoTelepon.setText("");
        comboBoxKategori.setSelectedIndex(0);
    } else {
        JOptionPane.showMessageDialog(this, "Kontak Berhasil Ditambahkan!");
    }
    }//GEN-LAST:event_buttonTambahActionPerformed

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
    int selectedRow = tabelKontak.getSelectedRow();
    
    // Pastikan ada baris yang dipilih
    if (selectedRow != -1) {
        int id = Integer.parseInt((String) tabelKontak.getValueAt(selectedRow, 0)); // Ambil ID dari kolom pertama
        String nama = textNama.getText();
        String nomor = textNoTelepon.getText();
        String kategori = (String) comboBoxKategori.getSelectedItem();
        
        // Validasi data sebelum diperbarui
        if (!nama.isEmpty() && !nomor.isEmpty() && kategori != null) {
            dbHelper.updateKontak(id, nama, nomor, kategori); // Update kontak di database
            loadKontakData(); // Refresh data di JTable
            JOptionPane.showMessageDialog(this, "Kontak berhasil diperbarui!");

            // Kosongkan input setelah diperbarui
            textNama.setText("");
            textNoTelepon.setText("");
            comboBoxKategori.setSelectedIndex(0);
        }
        } else {
        JOptionPane.showMessageDialog(this, "Pilih Kontak yang Ingin Diedit!");
    }
    }//GEN-LAST:event_buttonEditActionPerformed

    private void buttonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHapusActionPerformed
    int selectedRow = tabelKontak.getSelectedRow();
    if (selectedRow != -1) {
        int id = Integer.parseInt((String) tabelKontak.getValueAt(selectedRow, 0));
        dbHelper.hapusKontak(id);
        loadKontakData(); // Refresh data di JTable
    } else {
        JOptionPane.showMessageDialog(this, "Kontak Berhasil Dihapus!");
    }
    }//GEN-LAST:event_buttonHapusActionPerformed

    private void buttonCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCariActionPerformed
    String keyword = textPencarian.getText();
    DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nama", "Nomor", "Kategori"}, 0);
    
    dbHelper.getKontakList().forEach((kontak) -> {
        if (kontak[1].contains(keyword) || kontak[2].contains(keyword)) {
            model.addRow(kontak);
          }
        });
        tabelKontak.setModel(model);
    }


    private void loadKontakData() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nama", "Nomor", "Kategori"}, 0);
        dbHelper.getKontakList().forEach((kontak) -> {
            model.addRow(kontak);
        });
        tabelKontak.setModel(model);
    }//GEN-LAST:event_buttonCariActionPerformed

    private void buttonImporActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonImporActionPerformed
    buttonImpor.addActionListener(e -> imporToCSV());
    }//GEN-LAST:event_buttonImporActionPerformed

    private void listKategoriValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listKategoriValueChanged
    // Tambahkan listener untuk JList (optional)
        listKategori.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedCategory = listKategori.getSelectedValue();
                comboBoxKategori.setSelectedItem(selectedCategory); // Sinkronkan dengan JComboBox
            }
        });
    }//GEN-LAST:event_listKategoriValueChanged

    private void butttonKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butttonKeluarActionPerformed
    System.exit(0); // Menutup aplikasi
    }//GEN-LAST:event_butttonKeluarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AplikasiPengelolaanKontak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AplikasiPengelolaanKontak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AplikasiPengelolaanKontak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AplikasiPengelolaanKontak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AplikasiPengelolaanKontak().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCari;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonEkspor;
    private javax.swing.JButton buttonHapus;
    private javax.swing.JButton buttonImpor;
    private javax.swing.JButton buttonTambah;
    private javax.swing.JButton butttonKeluar;
    private javax.swing.JComboBox<String> comboBoxKategori;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listKategori;
    private javax.swing.JTable tabelKontak;
    private javax.swing.JTextField textNama;
    private javax.swing.JTextField textNoTelepon;
    private javax.swing.JTextField textPencarian;
    // End of variables declaration//GEN-END:variables
}
