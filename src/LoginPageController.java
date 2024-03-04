import Connections.Connector;
import java.io.IOException;
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginPageController implements Initializable {
    @FXML
    private TextField f_name;
    @FXML
    private TextField l_name;
    @FXML
    private TextField u_name;
    @FXML
    private TextField p_word;
    @FXML
    private TextField e_mail;
    @FXML
    private TextField p_no;
    @FXML
    private TextField search_username;
    @FXML
    private TextField admin_username;
    @FXML
    private PasswordField admin_password;
    @FXML
    private TextField user_username;
    @FXML
    private PasswordField user_password;
    @FXML
    public void addUser(){
        try{ 
            if(u_name.getText() != ""&&p_word.getText()!=""){
                Connection con  = Connector.CreateCon();
                String query = "insert into users(username, password, first_name, last_name, email, phone) value(?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(3,f_name.getText());
                pst.setString(4,l_name.getText());
                pst.setString(1,u_name.getText());
                pst.setString(2,p_word.getText());
                pst.setString(5,e_mail.getText());
                pst.setString(6,p_no.getText());
                pst.executeUpdate();
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText("User added Succesfully!");
                a.show();}
            else{
                Alert b = new Alert(AlertType.ERROR);
                b.setContentText("Username or Password is empty");
                b.show();
            }
            f_name.clear();
            l_name.clear();
            u_name.clear();
            p_word.clear();
            e_mail.clear();
            p_no.clear();
                }
                catch(SQLIntegrityConstraintViolationException ex){
                    Alert a = new Alert(AlertType.ERROR);
                    a.setContentText("Username already exists");
                    a.show();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }}
//    @FXML
//    public void removeUser(){
//        try{
//            Connection con = Connector.CreateCon();
//            String query = "delete from users where username =?";
//            PreparedStatement pst = con.prepareStatement(query);
//            pst.setString(1,search_username.getText());
//            pst.executeUpdate();          
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
        
//    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    public void adminLogin(){
        try{
        String au =admin_username.getText();
        String ap = admin_password.getText();
        Connection con = Connector.CreateCon();
        String Query = "select username,password from users where username = ? and password = ? and admin = 1";
        PreparedStatement st = con.prepareStatement(Query);
        st.setString(1, au);
        st.setString(2, ap);
        ResultSet rs = st.executeQuery();
            if(rs.next()){
                Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
                Scene scene = new Scene(root); 
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Admin Login");
                primaryStage.setScene(scene);
                primaryStage.show();
            }else{
                Alert al = new Alert(AlertType.ERROR);
                al.setContentText("Incorrect username or password");
                al.show();
            }
            admin_username.clear();
            admin_password.clear();
            }catch(SQLException | IOException ex){
                ex.printStackTrace();
            }
    }
    public void userLogin(){
        try{
        String uu =user_username.getText();
        String up =user_password.getText();
        Connection con = Connector.CreateCon();
        String Query = "select username,password from users where username = ? and password = ? and admin = 0";
        PreparedStatement st = con.prepareStatement(Query);
        st.setString(1, uu);
        st.setString(2, up);
        ResultSet rs = st.executeQuery();
            if(rs.next()){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("user_dashboard.fxml"));
                Parent root = loader.load();
                User_dashboardController udc = loader.getController();
                udc.welcomeText(uu);
                Scene scene = new Scene(root); 
                Stage primaryStage = new Stage();
                primaryStage.setTitle("User Login");
                primaryStage.setScene(scene);
                primaryStage.show();
                
            }else{
                Alert al = new Alert(AlertType.ERROR);
                al.setContentText("Incorrect username or password");
                al.show();
            }
            user_username.clear();
            user_password.clear();
            }catch(SQLException | IOException ex){
                ex.printStackTrace();
            }
    }
    
}
