import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class Newspaper extends Item {
    private String publisher;
    private String date;

    Newspaper(final String title, final String publisherCompany,
              final int popularityCount, final String date){
        this.title = title;
        this.cost = 15;
        this.date = date;
        this.popularityCount = popularityCount;
        this.publisher = publisherCompany;
        id = Id++;
        this.typeId = 3;
        this.isBorrowed = false;
        this.borrowers = new ArrayList<Borrower>();
    }
    @Override
    String getTitle(){
        return this.title;
    }
    @Override
    int getId(){
        return id;
    }
    @Override
    int getTypeId(){
        return 3;
    }
    @Override
    void setTitle(final String title){
        this.title = title;
    }
    void setPublisher(final String publisher){
        this.publisher = publisher;
    }
    @Override
    public void displayInfo(){
        System.out.println("ID: " + id + ", Title: " + title + ", published by " + publisher + ", Date: " + date);
    }
    @Override
    void writeToFile()throws IOException{
        FileWriter fileWriter = new FileWriter("data.txt", true);
        fileWriter.write(3 + "," + title + "," + publisher + "," + ++popularityCount + "," + date + "\n");
        fileWriter.close();
    }
    @Override
    public Double calculateCost(){
        return 15.0d;
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
    String getPublisher(){
        return this.publisher;
    }
    String getPublicationData(){
        return this.date;
    }
}
