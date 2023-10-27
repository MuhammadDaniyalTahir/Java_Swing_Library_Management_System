import java.io.FileReader;
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
                            str = "";
                            i++;
                        }
                    }

                }
                items.add(new Newspaper(title, publisher, popularityCount, date));
            }
        }
    }

    void addItem(final int choice, final String[] data) throws IOException{
        FileWriter fileWriter = new FileWriter("data.txt", true);
        Random rand = new Random();
        int popularityCount = rand.nextInt(4);
        Item it = null;

        if(choice == 1){
            fileWriter.write(1 + "," + data[0] + "," + data[1] + "," + data[2] + "," + ++popularityCount + "," + data[3] + "\n");
            fileWriter.close();
            it = new Book(data[0], data[1],Integer.parseInt(data[2]), Double.parseDouble(data[3]), popularityCount);
            items.add(it);
        }
        else if(choice == 2){
            String[] authorStr = data[1].split(",");
            for(int i = 0; i < authorStr.length; i++){
                authorStr[i] = authorStr[i].trim();
            }
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
            it = new Magazine(data[0], authors, data[2], popularityCount, Double.parseDouble(data[3]));
            items.add(it);
        }
        else if(choice == 3){
            LocalDate Date = LocalDate.now();

            fileWriter.write(3 + "," + data[0] + "," + data[1] + "," + ++popularityCount + "," + Date.toString() + "\n");
            fileWriter.close();

            it = new Newspaper(data[0], data[1], popularityCount, Date.toString());
            items.add(it);
        }
        makeFileToreadItem(it);
    }
    private void makeFileToreadItem(final Item item)throws IOException{
        String fileName = item.getTitle()+".txt";
        File file = new File(fileName);
        FileWriter writer = new FileWriter(fileName);

        writer.write("Title: " + item.getTitle() + "\n");

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 10;
        StringBuilder randomString = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            randomString.append(randomChar);
        }
        writer.write(randomString.toString());
        writer.close();
    }

    void displayInfo(final Item i){
        i.displayInfo();
    }

    void editItem(Item item, final String[] data)throws IOException{
        this.updateTextFile(item, data[0]);
        if(item.getTypeId() == 1){
            this.editBook(item, data);
        }
        else if(item.getTypeId() == 2){
            this.editMagazine(item, data);
        }
        else if(item.getTypeId() == 3){
            this.editNewspaper(item, data);
        }
        FileWriter fw = new FileWriter("data.txt");
        fw.close(); //To make the data.txt file empty.
        for(Item i : items){
            i.writeToFile(); //to write the updated data into data.txt.
        }
    }

    void editBook(Item i,  final String[] data){
        i.setTitle(data[0]);
        i.setAuthor(data[1]);
        i.setYear(Integer.parseInt(data[2]));
        i.setCost(Double.parseDouble(data[3]));
    }

    void editMagazine(Item i, final String[] data){
        String[] authorStr = data[1].split(",");
        for(int j = 0; j < authorStr.length; j++)
            authorStr[j] = authorStr[j].trim();
        List<String> authors = new ArrayList<>();
        for(String s : authorStr)
            authors.add(s);

        i.setTitle(data[0]);
        i.setAuthor(authors);
        i.setPublisher(data[2]);
        i.setCost(Double.parseDouble(data[3]));

    }
    void editNewspaper(Item i, final String[] data){
            i.setTitle(data[0]);
            i.setPublisher(data[1]);
    }

    private void updateTextFile(Item item, final String updatedTitle)throws IOException{
        File file = new File(item.getTitle()+".txt");
        File newFile = new File(updatedTitle+".txt");
        file.renameTo(newFile);//name of the file has been changed.
        newFile = null;

        //Code to read the data of file.
        FileReader read = new FileReader(updatedTitle+".txt");
        Scanner sc = new Scanner(read);
        String fileData = "";
        sc.nextLine();
        sc.nextLine();//to skip to read the title.
        while(sc.hasNext()){
            fileData += sc.nextLine() + "\n";
        }
        sc = null;
        read.close();

        //Code to write the updated title of file into file.
        FileWriter write = new FileWriter(updatedTitle+".txt");
        write.write("Title: " + updatedTitle + "\n\n" + fileData);
        write.close();



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
