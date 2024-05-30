import Database.DBConn;
import Panel.Panel;

public class Main {
    public static void main(String[] args) {
        DBConn db = new DBConn();
        db.DBRun();
        new Panel(db);
    }
}