import Connections.Connector;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AdminController implements Initializable {
    
    @FXML
    private Label total_books;
    @FXML
    private Label total_users;
    @FXML
    private Label total_issued;
    @FXML
    private Label total_admins;
    @FXML
    private Label total_authors;
    
    @FXML
    public void USERS(){
        try{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("admin_userspage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}
        catch(IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    public void BOOKS(){
        try{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("admin_books.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}
        catch(IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    public void ISSUE(){
        try{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("issue_Requests.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try{ 
                Connection con  = Connector.CreateCon();
                String query = "select count(Name) from books";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                rs.next();
                String totalBooks = rs.getString("count(Name)");
                total_books.setText(totalBooks);
                String query1 = "select count(username) from users";
                Statement st1 = con.createStatement();
                ResultSet rs1 = st1.executeQuery(query1);
                rs1.next();
                String totalUsers = rs1.getString("count(username)");
                total_users.setText(totalUsers);
                String query2 = "select count(Name) from books where Issue_status = 'issued'";
                Statement st2 = con.createStatement();
                ResultSet rs2 = st2.executeQuery(query2);
                rs2.next();
                String totalIssued = rs2.getString("count(Name)");
                total_issued.setText(totalIssued);
                String query3 = "select count(username) from users where admin = '1'";
                Statement st3 = con.createStatement();
                ResultSet rs3 = st3.executeQuery(query3);
                rs3.next();
                String totalAdmins = rs3.getString("count(username)");
                total_admins.setText(totalAdmins);
                String query4 = "select distinct Author from books";
                Statement st4 = con.createStatement();
                ResultSet rs4 = st4.executeQuery(query4);
                int totalAuthors = 0;
                while(rs4.next()){
                    totalAuthors++;}
                total_authors.setText(String.valueOf(totalAuthors));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
    }    
    
}
