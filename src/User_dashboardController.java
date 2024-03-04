import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class User_dashboardController implements Initializable {
    @FXML
    private TextField USERNAME_txt;
    
    String user;
    @FXML
    public void displayBooks(){
        try{
            FXMLLoader l = new FXMLLoader(getClass().getResource("User_displayBooks.fxml"));
            Parent root = l.load();
            User_displayBooksController udb = l.getController();
            udb.accessUsername3(user);
            Scene scene = new Scene(root); 
            Stage primaryStage = new Stage();
            primaryStage.setTitle("User Login");
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    public void searchBooks(){
        try{
            FXMLLoader l = new FXMLLoader(getClass().getResource("User_searchBooks.fxml"));
            Parent root = l.load();
            User_searchBooksController usb = l.getController();
            usb.accessUsername2(user);
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("User Login");
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    public void issuedBooks(){
        try{
            FXMLLoader l = new FXMLLoader(getClass().getResource("User_issuedBooks.fxml"));
            Parent root = l.load();
            User_issuedBooksController uib = l.getController();
            uib.accessUsername(user);
            Scene scene = new Scene(root); 
            Stage primaryStage = new Stage();
            primaryStage.setTitle("User Login");
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void welcomeText(String username){
        USERNAME_txt.setText(username);
        user = username;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    
    
}
