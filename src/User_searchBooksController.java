import Connections.Book;
import Connections.Connector;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class User_searchBooksController implements Initializable {

    @FXML
    private TextField search_book;
    @FXML
    private TextField username;
    @FXML
    private TableView<Book> searchBook_table;
    @FXML
    private TableColumn<Book,String> name;
    @FXML
    private TableColumn<Book,String> author;
    @FXML
    private TableColumn<Book,String> category;
    @FXML
    private TableColumn<Book,String> issue_status;
    @FXML
    private ObservableList<Book> _list;
    
    String USERNAME3;
    @FXML 
    public void searchBook(){
         try{
        Connection con = Connector.CreateCon();
        searchBook_table.getItems().clear();
        _list = searchBook_table.getItems();
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        issue_status.setCellValueFactory(new PropertyValueFactory<>("issue_status"));
        String query = "select * from books where Name = ?";
        PreparedStatement pst =con.prepareStatement(query);
        pst.setString(1,search_book.getText());
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
          _list.add(new Book(rs.getString("Name"),rs.getString("Author"),rs.getString("Category"),rs.getString("Date_entered"),rs.getString("Issue_status"),rs.getString("Issued_By")));
        }
        searchBook_table.setItems(_list);
        }catch(SQLException ex){
        ex.printStackTrace(); }
    }
    @FXML
    public void requestBook(){
          try{
        Connection con = Connector.CreateCon();
        String status_ =searchBook_table.getSelectionModel().getSelectedItem().getIssue_status();
        String category_ =searchBook_table.getSelectionModel().getSelectedItem().getCategory();
        if((status_.equalsIgnoreCase("Available"))&&(category_.equalsIgnoreCase("Book"))){
        String book_name = searchBook_table.getSelectionModel().getSelectedItem().getName();
        String Query = "update books set Issue_status = 'requested',requested_by =? where Name = ?";
        PreparedStatement pst = con.prepareStatement(Query);
        pst.setString(1, USERNAME3);
        pst.setString(2, book_name);
        pst.executeUpdate();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Your request has been forwarded.");
        alert.show();
        int selectedIndex = searchBook_table.getSelectionModel().getSelectedIndex();
        Book book = searchBook_table.getSelectionModel().getSelectedItem();
        _list.add(selectedIndex,new Book(book.getName(),book.getAuthor(),book.getCategory(),book.getDate_entered(),"requested",book.getIssued_by()));
        _list.remove(selectedIndex+1);
        searchBook_table.setItems(_list);
        }
        else{
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("This book is either issued by another user or can't be issued.");
        alert.show();}
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(NullPointerException ex){}
        catch(Exception e){}
    }
    
    public void accessUsername2(String s2){
        USERNAME3 =s2;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
