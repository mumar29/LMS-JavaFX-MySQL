
package Connections;

public class Book {
    private String name;
    private String author;
    private String category;
    private String date_entered;
    private String issue_status;
    private String issued_by;
    private String requested_by;
    
    public Book(String name, String author, String category, String date_entered, String issue_status, String issued_by) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.date_entered = date_entered;
        this.issue_status = issue_status;
        this.issued_by = issued_by;
    }

    public Book(String name, String author, String category, String date_entered, String issue_status, String issued_by, String requested_by) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.date_entered = date_entered;
        this.issue_status = issue_status;
        this.issued_by = issued_by;
        this.requested_by = requested_by;
    }

    public Book(String name, String author, String issued_by) {
        this.name = name;
        this.author = author;
        this.issued_by = issued_by;
    }

    public Book() {
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getDate_entered() {
        return date_entered;
    }

    public String getIssue_status() {
        return issue_status;
    }

    public String getIssued_by() {
        return issued_by;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate_entered(String date_entered) {
        this.date_entered = date_entered;
    }

    public void setIssue_status(String issue_status) {
        this.issue_status = issue_status;
    }

    public void setIssued_by(String issued_by) {
        this.issued_by = issued_by;
    }

    public String getRequested_by() {
        return requested_by;
    }

    public void setRequested_by(String requested_by) {
        this.requested_by = requested_by;
    }

    }

