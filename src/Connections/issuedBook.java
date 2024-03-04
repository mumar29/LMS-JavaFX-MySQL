
package Connections;

public class issuedBook extends Book{
        
    private String issue_date;
    private String return_date;
    private int issueID;
    
    public String getIssue_date() {
        return issue_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public issuedBook(String name, String author, String issued_by,String issue_date, String return_date,int IID) {
        super(name, author, issued_by);
        this.issue_date = issue_date;
        this.return_date = return_date;
        this.issueID =IID;
    }
    public issuedBook() {
    }

    public int getIssueID() {
        return issueID;
    }
    
}
