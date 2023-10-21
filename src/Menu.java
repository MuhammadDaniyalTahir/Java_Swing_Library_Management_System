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
        Library lib = null;
        try{
            lib = new Library();
        }
        catch(IOException e){}
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
        this.setLocationRelativeTo(null);

        //Adding ActionListeners to each button.
        this.hotPicksBtn.addActionListener(new HotPicksActionListener(lib));
        this.BorrowItemBtn.addActionListener(new BorrowItemActionListener(lib));
        this.viewItemsByIDBtn.addActionListener(new viewItemsByIDActionListener(lib));
        this.deleteItemBtn.addActionListener(new DeleteItemActionListener(lib));
        this.viewBorrowersListBtn.addActionListener(new ViewBorrowersListActionListener(lib));
        this.viewAllItemsBtn.addActionListener(new ViewAllItemActionsListener(lib));
        this.addItemBtn.addActionListener(new AddItemActionListener(lib));
    }
    private class HotPicksActionListener implements ActionListener {
        private Library library;
        public HotPicksActionListener(final Library lib){
            this.library = lib;
        }

        public void actionPerformed(ActionEvent ae) {
            HotPicksFrame frame = new HotPicksFrame(library); //Goes to another class to open a new frame.
        }
    }
    private class BorrowItemActionListener implements ActionListener{
        private Library library;
        public BorrowItemActionListener(final Library lib){
            this.library = lib;
        }
        public void actionPerformed(ActionEvent ae){
            BorrowItemFrame frame = new BorrowItemFrame(library);

        }
    }
    private class viewItemsByIDActionListener implements ActionListener {
        private Library library;
        public viewItemsByIDActionListener(final Library lib){
            this.library = lib;
        }
        @Override
        public void actionPerformed(ActionEvent ae){
            ViewItemByIDFrame frame = new  ViewItemByIDFrame(library);

        }
    }
    private class DeleteItemActionListener implements ActionListener {
        private Library library;
        public DeleteItemActionListener(final Library lib){
            this.library = lib;
        }
        @Override
        public void actionPerformed(ActionEvent ae){
            DeleteItemFrame frame = new DeleteItemFrame(library);
        }
    }
    private class ViewBorrowersListActionListener implements ActionListener{
        private Library library;
        public ViewBorrowersListActionListener(Library lib){
            this.library = lib;
        }
        @Override
        public void actionPerformed(ActionEvent ae){
            ViewBorrowersListFrame frame = new ViewBorrowersListFrame(library);

        }
    }
    private class ViewAllItemActionsListener implements ActionListener{
        private Library library;
        public ViewAllItemActionsListener(Library lib){
            this.library = lib;
        }
        @Override
        public void actionPerformed(ActionEvent ae){
            ViewAllItemsFrame frame = new ViewAllItemsFrame(library);

        }
    }
    private class AddItemActionListener implements ActionListener{
        private Library lib;
        public AddItemActionListener(final Library lib){
            this.lib = lib;
        }
        @Override
        public void actionPerformed(ActionEvent ae){
            AddItemFrame frame = new AddItemFrame(this.lib);
        }
    }

}
