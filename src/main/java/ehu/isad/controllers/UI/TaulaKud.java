package ehu.isad.controllers.UI;

import ehu.isad.Main;
import ehu.isad.controllers.DB.TaulaDBKud;
import ehu.isad.model.PHPinfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.util.converter.IntegerStringConverter;

import java.math.BigInteger;
import java.net.URL;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TaulaKud implements Initializable {

    @FXML
    private ImageView imgView;

    @FXML
    private TextField txtURL;

    @FXML
    private Button btnChech;

    @FXML
    private TableView<PHPinfo> tPHP;

    @FXML
    private TableColumn<PHPinfo, String> colURL;

    @FXML
    private TableColumn<PHPinfo, String> colmd5;

    @FXML
    private TableColumn<PHPinfo, String> colVersion;

    @FXML
    private Label lblInfo;

    private List<PHPinfo> eskanList = new ArrayList<>();

    private ObservableList<PHPinfo> eskaneoak;

    @FXML
    void onClick(ActionEvent event) throws SQLException {

        String url = txtURL.getText();
        url = url +"/README";
        String md5 = md5HASHLortu(url);

        boolean emaitza = TaulaDBKud.getInstance().badagoDBan(md5);

        if (emaitza){

            lblInfo.setText("Datubasean Zegoen");
            String version = TaulaDBKud.getInstance().lortu(md5);
            PHPinfo info = new PHPinfo(txtURL.getText(),md5,version);
            eskanList.add(info);
            taulanGehitu();
            System.out.println("badago");

        }
        else{

            lblInfo.setText("Ez dago DatuBasean");
            String version = TaulaDBKud.getInstance().lortu(md5);
            PHPinfo info = new PHPinfo(txtURL.getText(),md5,"");
            eskanList.add(info);
            taulanGehitu();

        }

    }

    private void taulanGehitu() {

        eskaneoak = FXCollections.observableArrayList(eskanList);

        tPHP.setEditable(true);
        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        colURL.setCellValueFactory(new PropertyValueFactory<>("url"));
        colmd5.setCellValueFactory(new PropertyValueFactory<>("md5"));
        colVersion.setCellValueFactory(new PropertyValueFactory<>("version"));

        colVersion.setCellFactory(//editable egiten du
                TextFieldTableCell.forTableColumn());
        tPHP.setItems(eskaneoak);

    }

    private static String md5HASHLortu(String url) {
        if (url.equals("")) {
            return "";
        } else {
            try {
                MessageDigest HashMD5 = MessageDigest.getInstance("MD5");
                byte[] mensajeMatriz = HashMD5.digest(url.getBytes());
                BigInteger numero = new BigInteger(1, mensajeMatriz);
                StringBuilder hashMD5Salida = new StringBuilder(numero.toString(16));

                while (hashMD5Salida.length() < 32) {
                    hashMD5Salida.insert(0, "0");
                }
                return hashMD5Salida.toString();
            } catch (NoSuchAlgorithmException e) {
                System.out.println("Error al obtener el hash: " + e.getMessage());
                return "";
            }
        }
    }

    private Main mainApp;

    public void setMainApp(Main liburuak) {

        this.mainApp=liburuak;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colVersion.setEditable(true);

    }
}
