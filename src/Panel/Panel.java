package Panel;

import Database.Buku;
import Database.DBConn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Panel extends JFrame {
    Buku buku;
    JLabel lblJudulBuku = new JLabel("Judul Buku");
    JTextField txJudulBuku = new JTextField(30);
    JLabel lblPengarang = new JLabel("Pengarang");
    JTextField txPengarang = new JTextField(30);
    JTextArea hasil = new JTextArea();
    JButton input = new JButton("Input");
    JButton delete = new JButton("Delete");

    public Panel(DBConn db) {
        buku = new Buku(db);
        setTitle("DATA BUKU");
        setLocation(300, 100);
        setSize(360, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        lblJudulBuku.setBounds(10, 10, 120, 20);
        txJudulBuku.setBounds(130, 10, 200, 20);
        lblPengarang.setBounds(10, 40, 120, 20);
        txPengarang.setBounds(130, 40, 200, 20);
        input.setBounds(60, 70, 100, 20);
        delete.setBounds(180, 70, 100, 20);
        hasil.setBounds(20, 100, 300, 200);

        add(lblJudulBuku);
        add(txJudulBuku);
        add(lblPengarang);
        add(txPengarang);
        add(input);
        add(delete);
        add(hasil);

        input.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                buku.inputBuku(txJudulBuku.getText(), txPengarang.getText());
                showData();
            }
        });

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                buku.deleteBuku(txJudulBuku.getText(), txPengarang.getText());
                showData();
            }
        });

        setVisible(true);
        showData();
    }

    void showData() {
        hasil.setText("");
        hasil.append("ID\tJUDUL\tPENGARANG\n");

        ResultSet rs = buku.getBuku();
        try {
            while (rs != null && rs.next()) {
                int id = rs.getInt("id");
                String judul = rs.getString("judul");
                String pengarang = rs.getString("pengarang");

                hasil.append(id + "\t" + judul + "\t" + pengarang + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DBConn db = new DBConn();
        new Panel(db);
    }
}
