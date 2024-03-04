import Connections.Book;
import Connections.Connector;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Admin_booksController implements Initializable {
    
    @FXML
    private TextField book_name;
    @FXML
    private TextField author;
    @FXML
    private ComboBox<String> category;
    private String[] c_al= {"Book","New Arrival", "Reference Book"};
    @FXML
    private DatePicker date_entered;
    @FXML
    private TextField remove_book_search;
    @FXML
    private TableView<Book> remove_book;
    @FXML
    private TextField upd_name;
    @FXML
    private TextField upd_author;
    @FXML
    private ComboBox<String> upd_category;
    public String[] updc_al = {"Book","New Arrival","Reference Book"};
    @FXML
    private TextField upd_issued_by;
    @FXML
    private TextField upd_issue_status;
    @FXML
    private TextField update_search;
    @FXML
    private TableColumn<Book, String> u_name;
    @FXML
    private TableColumn<Book,String> u_author;
    @FXML
    private TableColumn<Book,String> u_category;
    @FXML
    private TableColumn<Book,String> u_issued_by;
    @FXML
    private TableColumn<Book,String> u_issue_status;
    @FXML
    private TableView<Book> update_book;
    @FXML
    private TableColumn<Book, String> Name;
    @FXML
    private TableColumn<Book, String> Author;
    @FXML
    private TableColumn<Book, String> IssueStatus;
    @FXML
    private TableColumn<Book, String> Category;
    @FXML
    private TableColumn<Book, String> IssuedBy;
    @FXML
    private TableColumn<Book, String> DateEntered;
    @FXML
    public void addBook(){
        try{ 
            if(book_name.getText()!= "" && category.getValue()!="" && author.getText() != "" ){
                Connection con  = Connector.CreateCon();
                String query = "insert into books(Name, Author, Category, Date_entered,epoch_time) value(?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(3,category.getValue());
                pst.setString(4,date_entered.getValue().toString());
                pst.setString(1,book_name.getText());
                pst.setString(2,author.getText());
                pst.setString(5,String.valueOf(System.currentTimeMillis()));
                pst.executeUpdate();
                Alert b = new Alert(Alert.AlertType.INFORMATION);
                b.setContentText("Book added successfully");
                b.show();}
            else{
                Alert b = new Alert(Alert.AlertType.ERROR);
                b.setContentText("All fields must be filled");
                b.show();
            }
                }
                catch(SQLIntegrityConstraintViolationException ex){
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Book already exists already exists");
                    a.show();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }}
        @FXML
        public void remove_search(){
        try{
        Connection con = Connector.CreateCon();
        remove_book.getItems().clear();
        ObservableList<Book> _list = remove_book.getItems();
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Author.setCellValueFactory(new PropertyValueFactory<>("author"));
        Category.setCellValueFactory(new PropertyValueFactory<>("category"));
        IssueStatus.setCellValueFactory(new PropertyValueFactory<>("issue_status"));
        IssuedBy.setCellValueFactory(new PropertyValueFactory<>("issued_by"));
        DateEntered.setCellValueFactory(new PropertyValueFactory<>("date_entered"));
        String query = "select * from books where Name = ?";
        PreparedStatement pst =con.prepareStatement(query);
        pst.setString(1, remove_book_search.getText());
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
          _list.add(new Book(rs.getString("Name"),rs.getString("Author"),rs.getString("Category"),rs.getString("Date_entered"),rs.getString("Issue_status"),rs.getString("Issued_By")));
        }
        remove_book.setItems(_list);
        }catch(SQLException ex){
        ex.printStackTrace(); }
        }
    public void removeBook(){
         try{
        Connection con = Connector.CreateCon();
        String name = remove_book.getSelectionModel().getSelectedItem().getName();
        String query = "delete from books where Name = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1,name);
        pst.executeUpdate();
        ObservableList<Book> _list = remove_book.getItems();
        int selectedIndex = remove_book.getSelectionModel().getSelectedIndex();
        _list.remove(selectedIndex);
        Alert b = new Alert(Alert.AlertType.INFORMATION);
        b.setContentText("Book has been removed successfully");
        b.show();
    }catch(SQLException ex){
        ex.printStackTrace(); }
    catch(Exception e){}
    }
    @FXML
    public void updateSearch(){
        try{
        Connection con = Connector.CreateCon();
        update_book.getItems().clear();
        ObservableList<Book> _list = update_book.getItems();
        u_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        u_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        u_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        u_issue_status.setCellValueFactory(new PropertyValueFactory<>("issue_status"));
        u_issued_by.setCellValueFactory(new PropertyValueFactory<>("issued_by"));
        String query = "select * from books where Name = ?";
        PreparedStatement pst =con.prepareStatement(query);
        pst.setString(1,update_search.getText());
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
          _list.add(new Book(rs.getString("Name"),rs.getString("Author"),rs.getString("Category"),rs.getString("Date_entered"),rs.getString("Issue_status"),rs.getString("Issued_By")));
        }
        update_book.setItems(_list);
        }catch(SQLException ex){
        ex.printStackTrace(); }
    }
    @FXML
    public void updateBook(){
        try{
        String bookname = update_book.getSelectionModel().getSelectedItem().getName();
        Connection con = Connector.CreateCon();
        String query = "update books set Name = ?,Author =?,Category=?,Issued_By =?,Issue_status =? where Name =?";
        PreparedStatement pst =con.prepareStatement(query);
        pst.setString(1,upd_name.getText());
        pst.setString(2,upd_author.getText());
        pst.setString(3,upd_category.getValue());
        pst.setString(4,upd_issued_by.getText());
        pst.setString(5,upd_issue_status.getText());
        pst.setString(6, bookname);
        pst.executeUpdate();
        ObservableList<Book> _list = update_book.getItems();
        String date = update_book.getSelectionModel().getSelectedItem().getDate_entered();
        int selectedIndex = update_book.getSelectionModel().getSelectedIndex();
        _list.add(selectedIndex,new Book(upd_name.getText(),upd_author.getText(),upd_category.getValue(),date,upd_issue_status.getText(),upd_issued_by.getText()));
        _list.remove(selectedIndex+1);
        update_book.setItems(_list);
        Alert b = new Alert(Alert.AlertType.INFORMATION);
        b.setContentText("Book updated successfully");
        b.show();
    }catch(SQLException ex){
        ex.printStackTrace();
    }catch(NullPointerException e){
        e.printStackTrace();
    } 
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        category.getItems().addAll(c_al);
        upd_category.getItems().addAll(updc_al);
    }    
    
}
