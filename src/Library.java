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

    void addItem(final int choice, final String[] data) throws IOException{
        FileWriter fileWriter = new FileWriter("data.txt", true);
        Random rand = new Random();
        int popularityCount = rand.nextInt(4);

        if(choice == 1){
            fileWriter.write(1 + "," + data[0] + "," + data[1] + "," + data[2] + "," + ++popularityCount + "," + data[3] + "\n");
            fileWriter.close();
            System.out.println("Your book has been added Successfully");
            items.add(new Book(data[0], data[1],Integer.parseInt(data[2]), Double.parseDouble(data[3]), popularityCount));
        }
        else if(choice == 2){
            String[] authorStr = data[1].split(",");
            List<String> authors = new ArrayList<>();
            for(String s : authorStr)
                authors.add(s);
            fileWriter.write(2 + "," + data[0] + ",");
            for(int i = 0; i < authors.size(); i++){
                fileWriter.write(authors.get(i).toString());
                if(i+1 != authors.size()){
                    fileWriter.write(",");
                }
            }
            fileWriter.write(".," + data[2] + "," + ++popularityCount + "," + data[3] + "\n");
            fileWriter.close();
            items.add(new Magazine(data[0], authors, data[2], popularityCount, Double.parseDouble(data[3])));
        }
        else if(choice == 3){
            LocalDate Date = LocalDate.now();

            fileWriter.write(3 + "," + data[0] + "," + data[1] + "," + ++popularityCount + "," + Date.toString() + "\n");
            fileWriter.close();

            items.add(new Newspaper(data[0], data[1], popularityCount, Date.toString()));
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

    boolean deleteItem(final int id) throws IOException{
        boolean flag = false;
        for(Item i : items){
            if(i.getId() == id){
                items.remove(i);
                flag = true;
                break;
            }
        }
        if(flag == false)
            return flag;
        FileWriter fw = new FileWriter("data.txt");
        fw.close();
        for(Item i : items){
            i.writeToFile();
        }
        return flag;
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

//    void viewBorrowersList(){
//        for(int i = 0; i < items.size(); i++){
//            if(items.get(i).isBorrowed()){
//                items.get(i).displayInfo();//item
//                //items.get(i).showBorrowers(); // it's borrower
//            }
//        }
//    }

    void borrowItem(final Item it, final Borrower b){
        it.addBorrower(b);
        it.changeBorrowStatus();
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
    List<Item> getItems(){
        return this.items;
    }
    public int countItems() {
        return items.size();
    }
}
