package ehu.isad.controllers.DB;

import ehu.isad.model.PHPinfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaulaDBKud {

    private static final TaulaDBKud instance = new TaulaDBKud();

    public static TaulaDBKud getInstance() {
        return instance;
    }

    private TaulaDBKud() {
    }

    public List<PHPinfo> lortuLiburuak() {

        String query = "select * from checksums";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<PHPinfo> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {

                String isbn = rs.getString("version");
                String egilea = rs.getString("md5");
                String data = rs.getString("path");

                PHPinfo lib = new PHPinfo(isbn,egilea,data);
                emaitza.add(lib);


            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return emaitza;
    }

    public boolean badagoDBan(String url) throws SQLException {

        String query = "SELECT version FROM checksums WHERE md5= \'"+ url+"\'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        return rs.next();

    }

    public String lortu(String md5) throws SQLException {

        String query = "SELECT version FROM checksums WHERE md5= \'"+ md5+"\'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        if (rs.next()){

            return rs.getString("url");

        }
        else{

            return "";

        }

    }

}
