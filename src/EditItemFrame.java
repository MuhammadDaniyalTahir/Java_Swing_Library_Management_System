import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditItemFrame extends JFrame {
    private Library lib;
    private JLabel IDLabel;
    private JTextField inputID;
    JLabel msgLabel;

    public EditItemFrame(final Library lib){
        this.lib = lib;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);

        IDLabel = new JLabel("Enter ID of Item");
        inputID = new JTextField(10);

        JButton addItemBtn = new JButton("Edit Item");
        addItemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editItemBtnFunction();
            }
        });

        JPanel addBtnPanel = new JPanel(new FlowLayout());
        addBtnPanel.add(addItemBtn);

        JPanel IDPanel = new JPanel(new FlowLayout());
        IDPanel.add(IDLabel);
        IDPanel.add(inputID);

        this.setLayout(new BorderLayout());
        this.setTitle("EditItemFrame");

        msgLabel = new  JLabel("");

        JPanel mainPanel = new JPanel(new GridLayout(3,1)); //Panel to hold both above panels.
        mainPanel.add(IDPanel);
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
    private void editItemBtnFunction(){
        int id = Integer.parseInt(this.inputID.getText());
        List<Item> items = this.lib.getItems();
        int typeId = 0;
        for(Item i : items){
            if(i.getId() == id){
                typeId = i.getTypeId();
                break;
            }
        }
        if(typeId == 1){
            EditBookDialog dialog = new EditBookDialog(this.lib, id, msgLabel);
        }
        else if(typeId == 2){
            EditMagazineDialog dialog = new EditMagazineDialog(this.lib, id, msgLabel);
        }
        else if(typeId == 3){
            EditNewspaperDialog dialog = new EditNewspaperDialog(this.lib, id, msgLabel);
        }
        else{
            msgLabel.setText("");
            JDialog errorMsgDialog = new JDialog();
            errorMsgDialog.setLayout(new GridLayout(2,1));
            JLabel errorMsg = new JLabel("Kindly select any option to edit the item");
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
