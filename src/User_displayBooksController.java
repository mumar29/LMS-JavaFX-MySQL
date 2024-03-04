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


public class User_displayBooksController implements Initializable {
    @FXML
    private TableView<Book> available_table;
    @FXML
    private TextField username;
    @FXML
    private TableColumn<Book,String> name;
    @FXML
    private TableColumn<Book,String> author;
    @FXML
    private TableColumn<Book,String> category;
    @FXML
    private TableColumn<Book,String> status;
    @FXML
    ObservableList<Book> _list; 
    
    public String USERNAME4;
    @FXML
    public void requestBook(){
        try{
        Connection con = Connector.CreateCon();
        String status_ =available_table.getSelectionModel().getSelectedItem().getIssue_status();
        String category_ =available_table.getSelectionModel().getSelectedItem().getCategory();
        if((status_.equalsIgnoreCase("Available"))&&(category_.equalsIgnoreCase("Book"))){
            
        String book_name = available_table.getSelectionModel().getSelectedItem().getName();
        String Query = "update books set Issue_status = 'requested',requested_by =? where Name = ?";
        PreparedStatement pst = con.prepareStatement(Query);
        pst.setString(1, USERNAME4);
        pst.setString(2, book_name);
        pst.executeUpdate();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Your request has been forwarded.");
        alert.show();
        int selectedIndex = available_table.getSelectionModel().getSelectedIndex();
        Book book = available_table.getSelectionModel().getSelectedItem();
        _list.add(selectedIndex,new Book(book.getName(),book.getAuthor(),book.getCategory(),book.getDate_entered(),"requested",book.getIssued_by()));
        _list.remove(selectedIndex+1);
        available_table.setItems(_list);
        }
        else{
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("This book is either issued by another user or can't be issued.");
        alert.show();}
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch (Exception ex){
            
        }
        
    }
    public void accessUsername3(String s3){
        USERNAME4 =s3;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            Connection con2 = Connector.CreateCon();
            String Query2 = "select * from books where Category = 'New Arrival'";
            Statement st2 = con2.createStatement();
            ResultSet rs2 = st2.executeQuery(Query2);
            double time = 1210000000;
            while(rs2.next()){
                double time_elapsed =Double.parseDouble(rs2.getString("epoch_time"));
                if(System.currentTimeMillis()-time_elapsed==time){
                    Connection con3 = Connector.CreateCon();
                    String Query3 = "update books set Category = 'Book' where Name = ?";
                    PreparedStatement pst3 = con3.prepareStatement(Query3);
                    pst3.setString(1, rs2.getString("Name"));
                    pst3.executeUpdate();
               }
            }
            _list = available_table.getItems();
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            author.setCellValueFactory(new PropertyValueFactory<>("author"));
            category.setCellValueFactory(new PropertyValueFactory<>("category"));
            status.setCellValueFactory(new PropertyValueFactory<>("issue_status"));
            Connection con = Connector.CreateCon();
            String Query = "select * from books where Issue_status = 'Available'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(Query);
            while(rs.next()){
                _list.add(new Book(rs.getString("Name"),rs.getString("Author"),rs.getString("Category"),rs.getString("Date_entered"),rs.getString("Issue_status"),rs.getString("Issued_By"),rs.getString("requested_by")));
            }
            available_table.setItems(_list);
            
           
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }    
    
}
