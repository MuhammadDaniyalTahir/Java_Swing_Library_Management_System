import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

class Magazine extends Item{
    private String publisher;
    private List authors;

    Magazine(final String title, final List authors, final String publisherCompany,
             final int popularityCount, final Double cost){
        this.cost = cost;

        this.title = title;
        this.publisher = publisherCompany;
        this.popularityCount = popularityCount;
        this.id = Id++;
        this.typeId = 2;
        this.authors = new ArrayList<String>();
        for(int i = 0; i < authors.size(); i++){
            this.authors.add(authors.get(i));
        }
        System.out.println("\n");
        this.isBorrowed = false;
        this.borrowers = new ArrayList<Borrower>();
    }

    @Override
    public void displayInfo(){
        System.out.print("ID: " + id + ", Title: " + title + ", by ");
        for(int i = 0; i < authors.size(); i++){
            System.out.print(authors.get(i));
            System.out.print(", ");
        }
        System.out.println("Published by \'" + publisher + "\'");

    }
    @Override
    void writeToFile()throws IOException{
        FileWriter fileWriter = new FileWriter("data.txt", true);
        fileWriter.write(2 + "," + title + ",");
        for(int i = 0; i < authors.size(); i++){
            fileWriter.write(authors.get(i).toString());
            if(i+1 != authors.size()){
                fileWriter.write(",");
            }
        }
        fileWriter.write(".," + publisher + "," + ++popularityCount + "," + cost + "\n");
        fileWriter.close();
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
        return 2;
    }
    @Override
    void setTitle(final String title){
        this.title = title;
    }
    void setPublisher(final String publisher){
        this.publisher = publisher;
    }
    void setAuthor(final List authors){
        this.authors.clear();

        for(int i = 0; i < authors.size(); i++){
            this.authors.add(authors.get(i));
        }
    }
    String getAuthor(){ // to use in hot picks frame.
        String authorsStr = "";
        for(int i = 0; i < authors.size(); i++){
            authorsStr += this.authors.get(i);
            if(i+1 < authors.size())
                authorsStr += ", ";
        }
        return authorsStr;
    }
    @Override
    public Double calculateCost(){
        return (cost + 200 + (0.2*cost));
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
    List<Borrower> getBorrowers(){
        return this.borrowers;
    }
    @Override
    int getPopularityCount(){
        return this.popularityCount;
    }
    void setCost(final Double cost){
        this.cost = cost;
    }
    Double getCost(){
        return this.cost;
    }
    String getPublisher(){
        return this.publisher;
    }
}
