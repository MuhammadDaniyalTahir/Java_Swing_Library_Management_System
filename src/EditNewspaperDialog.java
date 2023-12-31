import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EditNewspaperDialog extends JDialog {
    private Library lib;
    private JLabel titleLabel, publisherLabel;
    private JLabel msgLabel;
    private JTextField inputTitle, inputPublisher;
    private int newspaperID;
    private Item item;

    public EditNewspaperDialog(final Library lib, final int id, JLabel msgLabel, Item item){
        this.item = item;
        this.msgLabel = msgLabel;
        this.lib =  lib;
        this.newspaperID = id;
        this.setSize(300, 300);
        this.setVisible(true);
        this.setLayout(new GridLayout(5, 1));
        this.setLocationRelativeTo(null);
        this.setTitle("Enter Newspaper Details");

        //Making all labels that we need in this dialog box.
        titleLabel = new JLabel("Enter Title");
        publisherLabel = new JLabel("Enter Pubisher");

        //Making all textFiels to take input that we need in this dialog box.
        inputTitle = new JTextField(10);
        inputPublisher = new JTextField(10);

        //Making panel to add label and input field for title.
        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.add(titleLabel);
        titlePanel.add(inputTitle);
        this.add(titlePanel);

        //Making panel to add label and input field for Publisher.
        JPanel publisherPanel = new JPanel(new FlowLayout());
        publisherPanel.add(publisherLabel);
        publisherPanel.add(inputPublisher);
        this.add(publisherPanel);

        ////Making panel to add Button to add Newspaper according to above given data.
        JPanel addBtnPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Add");
        addBtnPanel.add(addBtn);
        this.add(addBtnPanel);

        //Add button click action is defined now.
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputTitle.getText().isEmpty() || inputPublisher.getText().isEmpty()){
                    errorMsgDialog();
                }
                else{
                    editNewspaper();
                    EditNewspaperDialog.this.dispose();
                }
            }
        });

    }
    private void errorMsgDialog(){
        JDialog errorMsgDialog = new JDialog();
        errorMsgDialog.setLayout(new GridLayout(2,1));
        JLabel errorMsg = new JLabel("Kindly fill all fields");
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
    private void editNewspaper(){
        String[] data = {inputTitle.getText(), inputPublisher.getText()};
        for(int i = 0; i < data.length; i++)
            data[i] = data[i].trim();
        try{
            lib.editItem(this.item, data);
        }
        catch(IOException e){

        }
        msgLabel.setText("Newspaper has been edited successfully");
    }
}
