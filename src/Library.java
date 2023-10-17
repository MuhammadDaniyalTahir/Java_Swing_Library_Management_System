import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Library {
    private List<Item> items;

    Library() throws IOException {
        this.items = new ArrayList<Item>();
        File read = new File("data.txt");
        Scanner sc = new Scanner(read);
        String title = "", author = "", publisher = "", date = "";
        int year = 0, popularityCount = 0;
        double cost = 0.0d;
        while(sc.hasNext()){
            String data = sc.nextLine();
            if(data.charAt(0) == '1'){ //For Book
                //System.out.println(data);
                String str = "";
                int countComma = 1;
                for(int i = 2;i<data.length(); i++){
                    str = str.concat(String.valueOf(data.charAt(i)));
                    if(i+1 == data.length()){

                        cost = Double.parseDouble(str);
                        //System.out.println("hello boy");
                        str = "";
                        i++;
                    }
                    else if(data.charAt(i+1) == ','){
                        if(countComma+1 == 2){
                            countComma++;
                            title = str;

                            str = "";
                            i++;
                        }
                        else if(countComma+1 == 3){
                            countComma++;
                            author = str;
                            str = "";
                            i++;
                        }
                        else if(countComma+1 == 4){
                            countComma++;
                            year = Integer.parseInt(str);
                            str = "";
                            i++;
                        }
                        else if(countComma+1 == 5){

                            countComma++;
                            popularityCount = Integer.parseInt(str);
                            // System.out.println(popularityCount);

                            str = "";
                            i++;
                        }
                    }

                }
                items.add(new Book(title, author, year, cost, popularityCount));
            }
            else if(data.charAt(0) == '2'){

                List authors = new ArrayList<String>();
                String str = "";
                int countComma = 1;
                for(int i = 2;i<data.length(); i++){
                    str = str.concat(String.valueOf(data.charAt(i)));
                    if(i+1 == data.length()){

                        cost = Double.parseDouble(str);
                        str = "";
                        i++;
                    }
                    else if(data.charAt(i+1) == ','){
                        if(countComma+1 == 2){

                            countComma++;
                            title = str;

                            str = "";
                            i++;
                        }
                        else if(countComma+1 == 3){

                            countComma++;

                            if(str.charAt(str.length()-1) == '.'){
                                authors.add(str);
                                i++;
                                str = "";
                            }
                            else{
                                authors.add(str);
                                str = "";
                                for(int j = i+2; j < data.length(); j++){
                                    str += data.charAt(j);
                                    if(data.charAt(j + 1) == ',' || data.charAt(j + 1) == '.'){
                                        authors.add(str);
                                        j++;
                                        str = "";
                                        if(data.charAt(j) == '.'){
                                            i = j+1;
                                            break;
                                        }
                                    }
                                }
                                str = "";
                            }
                        }
                        else if(countComma+1 == 4){

                            countComma++;

                            publisher = str;
                            str = "";
                            i++;
                        }
                        else if(countComma+1 == 5){
                            countComma++;
                            popularityCount = Integer.parseInt(str);
                            // System.out.println(popularityCount);
                            str = "";
                            i++;
                        }
                    }

                }

                items.add(new Magazine(title, authors,publisher, popularityCount, cost));
            }
            else if(data.charAt(0) == '3'){ //For NewsPaper
                String str = "";
                int countComma = 1;
                for(int i = 2;i<data.length(); i++){
                    str = str.concat(String.valueOf(data.charAt(i)));
                    if(i+1 == data.length()){

                        date = str;
                        str = "";
                        i++;
                    }
                    else if(data.charAt(i+1) == ','){
                        if(countComma+1 == 2){
                            countComma++;
                            title = str;

                            str = "";
                            i++;
                        }
                        else if(countComma+1 == 3){
                            countComma++;
                            publisher = str;
                            str = "";
                            i++;
                        }
                        else if(countComma+1 == 4){

                            countComma++;
                            popularityCount = Integer.parseInt(str);
                            //System.out.println(popularityCount);
                            str = "";
                            i++;
                        }
                    }

                }
                items.add(new Newspaper(title, publisher, popularityCount, date));
            }
            //System.out.println(popularityCount);
        }
    }

    void addItem() throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter which item you want to add");
        System.out.println("1: Book");
        System.out.println("2: Magazine");
        System.out.println("3: Newspaper");
        int choice = sc.nextInt();
        sc.nextLine();
        FileWriter fileWriter = new FileWriter("data.txt", true);
        Random rand = new Random();
        int popularityCount = rand.nextInt(4);

        if(choice == 1){
            System.out.println("Enter title of the book: ");
            String title = sc.nextLine();
            System.out.println("Enter name of author of the book: ");
            String author = sc.nextLine();
            System.out.println("Enter published year of the book: ");
            int year = sc.nextInt();
            System.out.println("Enter cost of the Book: ");
            Double cost = sc.nextDouble();



            fileWriter.write(1 + "," + title + "," + author + "," + year + "," + ++popularityCount + "," + cost + "\n");
            fileWriter.close();
            System.out.println("Your book has been added Successfully");
            items.add(new Book(title, author, year, cost, popularityCount));
        }
        else if(choice == 2){
            System.out.println("Enter title of the Magazine: ");
            String title = sc.nextLine();
            System.out.println("Enter name of publisher company");
            String company = sc.nextLine();
            int option = 0;
            List authors = new ArrayList<String>();
            do{
                System.out.println("Enter the name of author: ");
                authors.add(sc.nextLine());
                System.out.println("Is there another author of the magazine");
                System.out.println("1: yes");
                System.out.println("2: No");
                option = sc.nextInt();
                sc.nextLine();
            }while(option == 1);

            System.out.println("Enter the cost of magazine: ");
            Double cost = sc.nextDouble();

            fileWriter.write(2 + "," + title + ",");
            for(int i = 0; i < authors.size(); i++){
                fileWriter.write(authors.get(i).toString());
                if(i+1 != authors.size()){
                    fileWriter.write(",");
                }
            }
            fileWriter.write(".," + company + "," + ++popularityCount + "," + cost + "\n");
            fileWriter.close();
            items.add(new Magazine(title, authors, company, popularityCount, cost));



        }
        else if(choice == 3){
            System.out.println("Enter title of the Newspaper: ");
            String title = sc.nextLine();
            System.out.println("Enter name of publisher company");
            String publisher = sc.nextLine();
            LocalDate Date = LocalDate.now();

            fileWriter.write(3 + "," + title + "," + publisher + "," + ++popularityCount + "," + Date.toString() + "\n");
            fileWriter.close();

            items.add(new Newspaper(title, publisher, popularityCount, Date.toString()));
        }


    }

    void displayInfo(final Item i){
        i.displayInfo();
    }

    void editItem(final int id)throws IOException{
        for(Item i : items){
            if(i.getId() == id){

                if(i.getTypeId() == 1){
                    this.editBook(i);
                    break;
                }
                else if(i.getTypeId() == 2){
                    this.editMagazine(i);
                    break;
                }
                else if(i.getTypeId() == 3){
                    this.editNewspaper(i);
                    break;
                }
            }
        }
        FileWriter fw = new FileWriter("data.txt");
        fw.close();
        for(Item i : items){
            i.writeToFile();
        }
    }

    void editBook(Item i){
        Scanner sc = new Scanner(System.in);
        System.out.println("1: For edit Book author");
        System.out.println("2: For edit Book title");
        System.out.println("3: For edit book published year");
        System.out.println("4: For edit Book cost");
        int choice = sc.nextInt();
        if(choice == 1){
            sc.nextLine();
            System.out.print("Enter author: ");
            i.setAuthor(sc.nextLine());
        }
        else if(choice == 2){
            sc.nextLine();
            System.out.print("Enter title: ");
            i.setTitle(sc.nextLine());
        }
        else if(choice == 3){
            System.out.print("Enter published Year: ");
            i.setYear(sc.nextInt());
        }
        else if(choice == 4){
            System.out.print("Enter cost: ");
            i.setCost(sc.nextDouble());
        }
    }

    void editMagazine(Item i){
        Scanner sc = new Scanner(System.in);
        System.out.println("1: For edit Magazine author");
        System.out.println("2: For edit Magazine title");
        System.out.println("3: For edit Magazine publisher company");
        System.out.println("4: For edit Magazine cost");
        int choice = sc.nextInt();
        if(choice == 1){
            sc.nextLine();

            List authors = new ArrayList<String>();
            int option = 0;
            do{
                System.out.println("Enter the name of author: ");
                authors.add(sc.nextLine());
                System.out.println("Is there another author of the magazine");
                System.out.println("1: yes");
                System.out.println("2: No");
                option = sc.nextInt();
                sc.nextLine();
            }while(option == 1);
            i.setAuthor(authors);
        }
        else if(choice == 2){
            sc.nextLine();
            System.out.print("Enter title: ");
            i.setTitle(sc.nextLine());
        }
        else if(choice == 3){
            sc.nextLine();
            System.out.print("Enter publisher company: ");
            i.setPublisher(sc.nextLine());
        }
        else if(choice == 4){
            System.out.print("Enter cost: ");
            i.setCost(sc.nextDouble());
        }
    }
    void editNewspaper(Item i){
        Scanner sc = new Scanner(System.in);
        System.out.println("1: For edit Magazine title");
        System.out.println("2: For edit Magazine publisher company");
        int choice = sc.nextInt();
        if(choice == 1){
            sc.nextLine();
            System.out.print("Enter title: ");
            i.setTitle(sc.nextLine());
        }
        else if(choice == 2){
            sc.nextLine();
            System.out.print("Enter publisher company name: ");
            i.setPublisher(sc.nextLine());
        }
    }

    void deleteItem(final int id) throws IOException{
        for(Item i : items){
            if(i.getId() == id){
                items.remove(i);
                break;
            }
        }
        FileWriter fw = new FileWriter("data.txt");
        fw.close();
        for(Item i : items){
            i.writeToFile();
        }
    }

    void displayAllItems(){
        for(Item i : items){
            i.displayInfo();
        }
    }

    Item getItemById(final int id){
        Item it = null;
        for(Item i : items){
            if(i.getId() == id){
                it = i;
                break;
            }
        }
        return it;
    }

    void viewBorrowersList(){
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).isBorrowed()){
                items.get(i).displayInfo();
                System.out.print("Above item has borrower: ");
                items.get(i).showBorrowers();
            }

        }
    }

    void borrowItem(){
        Scanner sc = new Scanner(System.in);
        System.out.println("All items are shown below");
        for(Item i : items){
            i.displayInfo();
        }
        int id = 0;
        boolean flag = false;
        do{
            System.out.print("Enter id of item you want to borrow");
            System.out.println("OtherWise enter 0");
            id = sc.nextInt();
            sc.nextLine();

            if(id != 0){
                for(Item i : items){
                    if(i.getId() == id){
                        if(i.isBorrowed()){
                            System.out.println("Item is not available");
                            flag = true;
                        }
                        break;
                    }
                }
            }
            else{
                return;
            }
        }while(flag);

        System.out.print("Enter borrower name: ");
        Borrower b = new Borrower(sc.nextLine());

        for(Item i : items){
            if(i.getId() == id){
                System.out.println("Cost of item is: " + i.calculateCost());
                i.addBorrower(b);
                i.changeBorrowStatus();
                break;
            }
        }
        System.out.println("Item has been borrowed Successfully");
    }

    public List<Item> getHotPicks() {
        List<Item> itemsList = new ArrayList<Item>();
        for (Item i : items) {
            itemsList.add(i);
        }
        Comparator<Item> ageComparator = (s1, s2) -> Integer.compare(s1.getPopularityCount(), s2.getPopularityCount());
        Collections.sort(itemsList, ageComparator);
        return itemsList;
    }
}