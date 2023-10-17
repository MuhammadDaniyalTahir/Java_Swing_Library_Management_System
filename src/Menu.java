import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


public class Menu extends JFrame {
    private JButton hotPicksBtn;
    private JButton BorrowItemBtn;
    private JButton addItemBtn;
    private JButton editItemBtn;
    private JButton deleteItemBtn;
    private JButton viewAllItemsBtn;
    private JButton viewItemsByIDBtn;
    private JButton viewBorrowersListBtn;
    private JButton exitBtn;

    public Menu(){
          //To load all the data from file.
        //Initializing all buttons.
        this.hotPicksBtn = new JButton("Hot Picks!");
        this.BorrowItemBtn = new JButton("Borrow Item");
        this.addItemBtn = new JButton("Add Item");
        this.editItemBtn = new JButton("Edit Item");
        this.deleteItemBtn = new JButton("Delete Item");
        this.viewAllItemsBtn = new JButton("View all Items");
        this.viewItemsByIDBtn = new JButton("View Item by ID");
        this.viewBorrowersListBtn = new JButton("View Borrowers List");
        this.exitBtn = new JButton("Exit");

        //Creating JFrame and adding all buttons into it.
        Container c = this.getContentPane();
        JPanel p1 = new JPanel(new GridLayout(9,1));
        c.add(p1);
        p1.add(this.hotPicksBtn);
        p1.add(this.BorrowItemBtn);
        p1.add(this.addItemBtn);
        p1.add(this.editItemBtn);
        p1.add(this.deleteItemBtn);
        p1.add(this.viewAllItemsBtn);
        p1.add(this.viewItemsByIDBtn);
        p1.add(this.viewBorrowersListBtn);
        p1.add(this.exitBtn);
        this.setSize(400, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Adding ActionListeners to each button.
        this.hotPicksBtn.addActionListener(new HotPicksActionListener());
        this.BorrowItemBtn.addActionListener(new BorrowItemActionListener()); //Not Implemented yet.
        this.viewItemsByIDBtn.addActionListener(new viewItemsByIDActionListener());
        this.deleteItemBtn.addActionListener(new DeleteItemActionListener());
    }
    private class HotPicksActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            try{
                HotPicksFrame frame = new HotPicksFrame(); //Goes to another class to open a new frame.
            }
            catch (IOException e){
                System.out.println("IOException in Hot picks frame in menu class");
            }
        }
    }
    private class BorrowItemActionListener implements ActionListener{ //Not Implemented Yet.
        public void actionPerformed(ActionEvent ae){
           // BorrowItemFrame frame = new BorrowItemFrame(); //Not made it's class yet.

        }
    }
    private class viewItemsByIDActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae){
            try{
                ViewItemByIDFrame frame = new  ViewItemByIDFrame();
            }
            catch (IOException e){

            }
        }
    }
    private class DeleteItemActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae){

        }
    }

}
