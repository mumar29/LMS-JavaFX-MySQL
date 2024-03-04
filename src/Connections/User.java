
package Connections;

import java.util.ArrayList;

public class User {
    private String username;
    private String Password;
    private String f_name;
    private String l_name;
    private String email;
    private String phone;
    public User() {
    }

    public User(String username, String Password, String f_name, String l_name, String email, String phone) {
        this.username = username;
        this.Password = Password;
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.phone = phone;
    }
//    public static void addBook(String name, String author, String date_entered, String issue_status, String email, String phone){
//        al.add(new User(username,Password,f_name,l_name,email,phone));
//    }
//     public static void removeUser(String username, String Password, String f_name, String l_name, String email, String phone){
//        al.remove(new User(username,Password,f_name,l_name,email,phone));
//    }
//     public static void updateUser(String fName, String newfName,String lName, String newlName,String email, String newEmail,String phone, String newPhone){
//        for(int i = 0;i<=al.size();i++){
//          if(al.get(i).f_name.equals(fName)){
//              al.get(i).f_name = newfName;
//              al.get(i).l_name = newlName;
//              al.get(i).email = newEmail;
//              al.get(i).phone = newPhone;
//          } else {  
//          }  
//        }
//    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return Password;
    }

    public String getF_name() {
        return f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

//    public static ArrayList<User> getAl() {
//        return al;
//    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public static void setAl(ArrayList<User> al) {
//        User.al = al;
//    }
    
}
