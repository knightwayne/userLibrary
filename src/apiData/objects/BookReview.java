package apiData.objects;

public class BookReview extends Media {
    String bookTitle;
    String bookCritic; //change to Reviewer
    String bookSummary;

    public String getBookTitle(){
        return this.bookTitle;
    }
    public String getBookCritic(){
        return this.bookCritic;
    }
    public String getBookSummary(){
        return this.bookSummary;
    }
    
    public void setBookTitle(String bookTitle){
        this.bookTitle=bookTitle;
    }
    public void setBookCritic(String bookCritic){
        this.bookCritic=bookCritic;
    }
    public void setBookSummary(String bookSummary){
        this.bookSummary=bookSummary;
    }
    
}
