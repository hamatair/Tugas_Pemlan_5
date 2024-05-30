package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Buku {
    static Connection conn;
    public Statement stmt;
    public ResultSet rs;
    public Buku(DBConn db){
        stmt = db.stmt;
        rs = db.rs;
        conn  = db.conn;
    }
    public void inputBuku(String judul, String pengarang) {
        try {
                // query simpan
            String sql = "INSERT INTO buku (judul, pengarang) VALUE('%s', '%s')";
            sql = String.format(sql, judul, pengarang);

            System.out.println(sql);

                // simpan buku
            stmt.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteBuku(String judul, String pengarang) {
        try {
            String sql = "DELETE FROM buku WHERE judul='%s' AND pengarang='%s'";
            sql = String.format(sql, judul, pengarang);
            System.out.println(sql);

                // hapus data
            stmt.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getBuku() {
        String sql = "SELECT * FROM buku";
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
