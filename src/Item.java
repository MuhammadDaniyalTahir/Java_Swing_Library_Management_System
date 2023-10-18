import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

abstract class Item implements Configuration {
    protected String title;
    protected boolean isBorrowed;
    protected int popularityCount;
    protected double cost;
    static int Id = 1;
    protected int id;
    protected int typeId;
    protected List<Borrower> borrowers;

    abstract int getPopularityCount();
    abstract List<Borrower> getBorrowers();
    abstract void changeBorrowStatus();
    abstract boolean isBorrowed();
    abstract void addBorrower(Borrower b);
    abstract int getId();
    abstract void writeToFile()throws IOException;
    abstract int getTypeId();
    abstract void setTitle(String s);
    void setPublisher(String str){}
    void setYear(int year){}
    void setCost(Double cost){}
    void setAuthor(String author){}
    void setAuthor(final List authors){}

    abstract String getTitle();
}
