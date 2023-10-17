import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Book extends Item{
    private int year;
    private String author;

    Book(final String title, final String author, final int publishedYear,
         final double cost, final int popularityCount){
        this.title = title;
        this.author = author;
        this.cost = cost;
        this.year = publishedYear;
        this.popularityCount = popularityCount;
        id = Id++;
        this.typeId = 1;
        this.isBorrowed = false;
        this.borrowers = new ArrayList<Borrower>();
    }

    @Override
    public void displayInfo(){
        System.out.println("ID: " + id + ", Title: " + title + ", by " + author + ", (" + year + ")");
    }
    @Override
    public Double calculateCost(){
        return (cost + 200 + (0.2*cost));
    }
    @Override
    int getId(){
        return id;
    }
    @Override
    int getTypeId(){
        return 1;
    }
    @Override
    void writeToFile()throws IOException{
        FileWriter fileWriter = new FileWriter("data.txt", true);
        fileWriter.write(1 + "," + title + "," + author + "," + year + "," + ++popularityCount + "," + cost + "\n");
        fileWriter.close();
    }
    @Override
    String getTitle(){
        return this.title;
    }
    int getPublishedYear(){
        return this.year;
    }
    void setYear(final int year){
        this.year = year;
    }
    void setAuthor(final String author){
        this.author = author;
    }
    String getAuthor(){ // to use in hot picks frame.
        return this.author;
    }
    @Override
    void setTitle(final String title){
        this.title = title;
    }
    @Override
    void addBorrower(Borrower b){
        borrowers.add(b);
    }
    @Override
    boolean isBorrowed(){
        return this.isBorrowed;
    }
    @Override
    void changeBorrowStatus(){
        this.isBorrowed = !this.isBorrowed;
    }
    void setCost(final Double cost){
        this.cost = cost;
    }
    @Override
    void showBorrowers(){
        for(Borrower b : borrowers){
            System.out.println(b.getName());
        }
    }
    @Override
    int getPopularityCount(){
        return this.popularityCount;
    }
    Double getCost(){
        return this.cost;
    }

}
