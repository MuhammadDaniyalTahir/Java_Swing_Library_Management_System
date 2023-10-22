import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemFrame extends JFrame {
    private Library lib;
    JRadioButton addBook;
    JRadioButton addMagazine;
    JRadioButton addNewspaper;
    JLabel msgLabel;

    public AddItemFrame(final Library lib){
        this.lib = lib;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);

        addBook = new JRadioButton("Book");
        addMagazine = new JRadioButton("Magazine");
        addNewspaper = new JRadioButton("Newspaper");

        JButton addItemBtn = new JButton("Add Item");
        addItemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItemBtnFunction();
            }
        });

        JPanel radioBtnsPanel = new JPanel(new FlowLayout());
        ButtonGroup buttons = new ButtonGroup();
        buttons.add(addBook);
        buttons.add(addMagazine);
        buttons.add(addNewspaper);

        radioBtnsPanel.add(addBook);
        radioBtnsPanel.add(addMagazine);
        radioBtnsPanel.add(addNewspaper);

        JPanel addBtnPanel = new JPanel(new FlowLayout());
        addBtnPanel.add(addItemBtn);

        this.setLayout(new BorderLayout());
        this.setTitle("ADDItemFrame");

        msgLabel = new  JLabel("");

        JPanel mainPanel = new JPanel(new GridLayout(3,1)); //Panel to hold both above panels.
        mainPanel.add(radioBtnsPanel);
        mainPanel.add(addBtnPanel);
        mainPanel.add(msgLabel);

        //Adding back button.
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel backBtnPanel = new JPanel(new FlowLayout());
        backBtnPanel.add(backBtn);
        this.add(backBtnPanel, BorderLayout.SOUTH);

        this.add(mainPanel, BorderLayout.CENTER);






    }
    private void addItemBtnFunction(){
        if(this.addBook.isSelected()){
            AddBookDialog dialog = new AddBookDialog(this.lib);
            msgLabel.setText("Book has been added successfully");
        }
        else if(this.addMagazine.isSelected()){
            AddMagazineDialog dialog = new AddMagazineDialog(this.lib);
            msgLabel.setText("Magazine has been added successfully");
        }
        else if(this.addNewspaper.isSelected()){
            AddNewspaperDialog dialog = new AddNewspaperDialog(this.lib);
            msgLabel.setText("Newspaper has been added successfully");
        }
        else{
            msgLabel.setText("");
            JDialog errorMsgDialog = new JDialog();
            errorMsgDialog.setLayout(new GridLayout(2,1));
            JLabel errorMsg = new JLabel("Kindly select any option to add the item");
            errorMsgDialog.add(errorMsg);
            errorMsgDialog.setVisible(true);
            JPanel okBtnPanel = new JPanel(new FlowLayout());
            JButton okBtn = new JButton("OK");
            okBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    errorMsgDialog.dispose();
                }
            });
            okBtnPanel.add(okBtn);
            errorMsgDialog.add(okBtnPanel);
            errorMsgDialog.setSize(200,200);
            errorMsgDialog.setLocationRelativeTo(null);
        }

    }
}
