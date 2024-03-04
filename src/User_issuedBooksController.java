
import Connections.Connector;
import Connections.issuedBook;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.sql.*;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class User_issuedBooksController implements Initializable {
    
    @FXML
    private TextField username;
    @FXML
    private DatePicker date_returned;
    @FXML
    private TableView<issuedBook> my_books;
    @FXML
    private TableColumn<issuedBook,String> name;
    @FXML
    private TableColumn<issuedBook,String> author;
    @FXML
    private TableColumn<issuedBook,String> issue_date;
    @FXML
    private TableColumn<issuedBook,String> issue_ID;
    @FXML
    private ObservableList<issuedBook> _list;
    
    private String USERNAME2;
   @FXML
   public void searchMyBooks(){
       try{
        Connection con = Connector.CreateCon();
        my_books.getItems().clear();
        _list = my_books.getItems();
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        issue_date.setCellValueFactory(new PropertyValueFactory<>("issue_date"));
        issue_ID.setCellValueFactory(new PropertyValueFactory<>("issueID"));
        String query = "select * from issued_books where username = ? and return_date = ''";
        PreparedStatement pst =con.prepareStatement(query);
        pst.setString(1,USERNAME2);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
          _list.add(new issuedBook(rs.getString("book_name"),rs.getString("Author"),rs.getString("username"),rs.getString("issue_date"),rs.getString("return_date"),(rs.getInt("issued_bookID"))));
        }
        my_books.setItems(_list);
        }catch(SQLException ex){
        ex.printStackTrace(); }
   }
   @FXML
   public void returnBook(){
       try{
        Connection con = Connector.CreateCon();
        String book_name = my_books.getSelectionModel().getSelectedItem().getName();
        String issueID =String.valueOf(my_books.getSelectionModel().getSelectedItem().getIssueID());
        String Query = "update books set Issue_status = 'Available',requested_by ='Null',Issued_By='Null' where Name = ?";
        PreparedStatement pst = con.prepareStatement(Query);
        pst.setString(1, book_name);
        pst.executeUpdate();
        String Query2 = "update issued_books set return_date = ? where issued_bookID = ?";
        PreparedStatement pst2 = con.prepareStatement(Query2);
        pst2.setString(1,date_returned.getValue().toString());
        pst2.setString(2,issueID);
        pst2.executeUpdate();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Book has been returned.");
        alert.show();
        int selectedIndex = my_books.getSelectionModel().getSelectedIndex();
        _list.remove(selectedIndex);
        my_books.setItems(_list);
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception e){}
   }
    public void accessUsername(String s){
        USERNAME2 = s;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
