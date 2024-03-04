
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
import java.sql.*;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Issue_RequestsController implements Initializable {
    
    @FXML
    private DatePicker issue_date;
    @FXML
    private TableView<Book> requests_table;
    @FXML
    private TableColumn<Book,String> name;
    @FXML
    private TableColumn<Book,String> author;
    @FXML
    private TableColumn<Book,String> requested_by;
    @FXML
    private ObservableList<Book> _list;
    
    @FXML
    public void issueBook(){
        try{
        Connection con = Connector.CreateCon();
        String username =requests_table.getSelectionModel().getSelectedItem().getRequested_by();            
        String book_name = requests_table.getSelectionModel().getSelectedItem().getName();
        String author = requests_table.getSelectionModel().getSelectedItem().getAuthor();
        String Query = "update books set Issue_status = 'issued',Issued_By =?, requested_by='' where Name = ?";
        PreparedStatement pst = con.prepareStatement(Query);
        pst.setString(1, username);
        pst.setString(2, book_name);
        pst.executeUpdate();
        String Query2 = "insert into issued_books(username,book_name,issue_date,return_date,Author) value(?,?,?,'',?)";
        PreparedStatement pst2 = con.prepareStatement(Query2);
        pst2.setString(1, username);
        pst2.setString(2, book_name);
        pst2.setString(3, issue_date.getValue().toString());
        pst2.setString(4, author);
        pst2.executeUpdate();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Book has been issued!");
        alert.show();
        int selectedIndex = requests_table.getSelectionModel().getSelectedIndex();
        _list.remove(selectedIndex);
        requests_table.setItems(_list);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
        Connection con = Connector.CreateCon();
        requests_table.getItems().clear();
        _list = requests_table.getItems();
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        requested_by.setCellValueFactory(new PropertyValueFactory<>("requested_by"));
        String query = "select * from books where Issue_status = 'requested'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
          _list.add(new Book(rs.getString("Name"),rs.getString("Author"),rs.getString("Category"),rs.getString("Date_entered"),rs.getString("Issue_status"),rs.getString("Issued_By"),rs.getString("requested_by")));
        }
        requests_table.setItems(_list);
        }catch(SQLException ex){
        ex.printStackTrace(); }
    }  
    
    
}
