import Connections.Book;
import Connections.Connector;
import Connections.User;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.sql.*;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Admin_userspageController implements Initializable {
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
    private TextField delete_user;
    @FXML
    private TextField update_user;
    @FXML
    private TableView<User> remove_table;
    @FXML
    private TableColumn<User, String> r_first_name;
    @FXML
    private TableColumn<User, String> r_last_name;
    @FXML
    private TableColumn<User, String> r_username;
    @FXML
    private TableColumn<User, String> r_email;
    @FXML
    private TableColumn<User, String> r_phone;
    @FXML
    private TableColumn<User, String> r_password;
    @FXML
    private TableView<User> update_table;
    @FXML
    private TableColumn<User, String> u_first_name;
    @FXML
    private TableColumn<User, String> u_last_name;
    @FXML
    private TableColumn<User, String> u_username;
    @FXML
    private TableColumn<User, String> u_email;
    @FXML
    private TableColumn<User, String> u_phone;
    @FXML
    private TableColumn<User, String> u_password;
    @FXML   
    ObservableList<User> u_list;
    @FXML
    private TextField upd_fname;
    @FXML
    private TextField upd_lname;
    @FXML
    private TextField upd_uname;
    @FXML
    private TextField upd_password;
    @FXML
    private TextField upd_email;
    @FXML
    private TextField upd_phone;
    @FXML
    private TableView<User> display_table;
    @FXML
    private TableColumn<User, String> d_first_name;
    @FXML
    private TableColumn<User, String> d_last_name;
    @FXML
    private TableColumn<User, String> d_username;
    @FXML
    private TableColumn<User, String> d_email;
    @FXML
    private TableColumn<User, String> d_phone;
    @FXML
    private TableColumn<User, String> d_password;
    @FXML   
    ObservableList<User> d_list;
    @FXML
    private TextField df_name;
    @FXML
    private TextField dl_name;
    @FXML
    private TextField du_name;
    @FXML
    private TextField dp_word;
    @FXML
    private TextField de_mail;
    @FXML
    private TextField dp_no;
    @FXML
    public void addUser(){
        try{ 
            if(u_name.getText() != ""&& p_word.getText()!=""){
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
                Alert b = new Alert(Alert.AlertType.INFORMATION);
                b.setContentText("User added successfully");
                b.show();}
            else{
                Alert b = new Alert(Alert.AlertType.ERROR);
                b.setContentText("Username or Password is empty");
                b.show();
            }
            //u_list.add(new User(u_name.getText(),p_word.getText(),f_name.getText(),l_name.getText(),e_mail.getText(),p_no.getText()));
                }
                catch(SQLIntegrityConstraintViolationException ex){
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Username already exists");
                    a.show();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }}
    @FXML
    public void r_search(){
        try{
        Connection con = Connector.CreateCon();
        remove_table.getItems().clear();
        ObservableList<User> list = remove_table.getItems();
        r_first_name.setCellValueFactory(new PropertyValueFactory<>("f_name"));
        r_last_name.setCellValueFactory(new PropertyValueFactory<>("l_name"));
        r_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        r_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        r_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        r_password.setCellValueFactory(new PropertyValueFactory<>("Password"));
        String query = "select * from users where first_name = ?";
        PreparedStatement pst =con.prepareStatement(query);
        pst.setString(1, delete_user.getText());
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
          list.add(new User(rs.getString("username"),rs.getString("password"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("email"),rs.getString("phone")));
        }
        remove_table.setItems(list);
        }catch(SQLException ex){
        ex.printStackTrace(); }
    }
    public void removeUser(){
        try{
        Connection con = Connector.CreateCon();
        String name = remove_table.getSelectionModel().getSelectedItem().getF_name();
        String query = "delete from users where first_name = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1,name);
        pst.executeUpdate();
        ObservableList<User> _list = remove_table.getItems();
        int selectedIndex = remove_table.getSelectionModel().getSelectedIndex();
        _list.remove(selectedIndex);
        Alert b = new Alert(Alert.AlertType.INFORMATION);
        b.setContentText("User removed successfully");
        b.show();    
    }catch(SQLException ex){
        ex.printStackTrace(); }
    catch(Exception e){}
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try{
        Connection con = Connector.CreateCon();
        display_table.getItems().clear();
        d_list = display_table.getItems();
        d_first_name.setCellValueFactory(new PropertyValueFactory<>("f_name"));
        d_last_name.setCellValueFactory(new PropertyValueFactory<>("l_name"));
        d_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        d_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        d_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        d_password.setCellValueFactory(new PropertyValueFactory<>("Password"));
        String query = "select * from users";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
          d_list.add(new User(rs.getString("username"),rs.getString("password"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("email"),rs.getString("phone")));
        }
        }catch(SQLException ex){
        ex.printStackTrace(); }
    }  
    @FXML
    public void u_search(){
        try{
        Connection con = Connector.CreateCon();
        update_table.getItems().clear();
        ObservableList<User> _list = update_table.getItems();
        u_first_name.setCellValueFactory(new PropertyValueFactory<>("f_name"));
        u_last_name.setCellValueFactory(new PropertyValueFactory<>("l_name"));
        u_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        u_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        u_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        u_password.setCellValueFactory(new PropertyValueFactory<>("Password"));
        String query = "select * from users where first_name = ?";
        PreparedStatement pst =con.prepareStatement(query);
        pst.setString(1, update_user.getText());
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
          _list.add(new User(rs.getString("username"),rs.getString("password"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("email"),rs.getString("phone")));
        }
        update_table.setItems(_list);
        }catch(SQLException ex){
        ex.printStackTrace(); }
    }
    @FXML
    public void update(){
        try{
        String username = update_table.getSelectionModel().getSelectedItem().getUsername();
        Connection con = Connector.CreateCon();
        String query = "update users set username = ?,password =?,first_name =?,last_name=?,email =?,phone =? where username =?";
        PreparedStatement pst =con.prepareStatement(query);
        pst.setString(3,upd_fname.getText());
        pst.setString(4,upd_lname.getText());
        pst.setString(1,upd_uname.getText());
        pst.setString(2,upd_password.getText());
        pst.setString(5,upd_email.getText());
        pst.setString(6,upd_phone.getText());
        pst.setString(7, username);
        pst.executeUpdate();
        ObservableList<User> _list = update_table.getItems();
        //String date = update_book.getSelectionModel().getSelectedItem().getDate_entered();
        int selectedIndex = update_table.getSelectionModel().getSelectedIndex();
        _list.add(selectedIndex,new User(upd_uname.getText(),upd_password.getText(),upd_fname.getText(),upd_lname.getText(),upd_email.getText(),upd_phone.getText()));
        _list.remove(selectedIndex+1);
        Alert b = new Alert(Alert.AlertType.INFORMATION);
        b.setContentText("User updated successfully");
        b.show();
    }catch(SQLException ex){
        ex.printStackTrace();}   
    }
    @FXML
    public void displayUpdate(){
        try{
        String username = display_table.getSelectionModel().getSelectedItem().getUsername();
        Connection con = Connector.CreateCon();
        String query = "update users set username = ?,password =?,first_name =?,last_name=?,email =?,phone =? where username =?";
        PreparedStatement pst =con.prepareStatement(query);
        pst.setString(3,df_name.getText());
        pst.setString(4,dl_name.getText());
        pst.setString(1,du_name.getText());
        pst.setString(2,dp_word.getText());
        pst.setString(5,de_mail.getText());
        pst.setString(6,dp_no.getText());
        pst.setString(7, username);
        pst.executeUpdate();
        d_list = display_table.getItems();
        int selectedIndex = display_table.getSelectionModel().getSelectedIndex();
        d_list.add(selectedIndex,new User(du_name.getText(),dp_word.getText(),df_name.getText(),dl_name.getText(),de_mail.getText(),dp_no.getText()));
        d_list.remove(selectedIndex+1);
        Alert b = new Alert(Alert.AlertType.INFORMATION);
        b.setContentText("User updated successfully");
        b.show();
    }catch(SQLException ex){
        ex.printStackTrace();
    }}
    public void removeDisplayUser(){
        try{
        Connection con = Connector.CreateCon();
        String name = display_table.getSelectionModel().getSelectedItem().getF_name();
        String query = "delete from users where first_name = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1,name);
        pst.executeUpdate(); d_list = display_table.getItems();
        int selectedIndex = display_table.getSelectionModel().getSelectedIndex();
        d_list.remove(selectedIndex);
        Alert b = new Alert(Alert.AlertType.INFORMATION);
        b.setContentText("User removed successfully");
        b.show();    
    }catch(SQLException ex){
        ex.printStackTrace(); }
    catch(Exception e){}
    }
    
}
