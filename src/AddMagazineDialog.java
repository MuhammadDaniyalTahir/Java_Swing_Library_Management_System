import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddMagazineDialog extends JDialog {
    private Library lib;
    JLabel titleLabel, costLabel, authorLabel, publisherLabel;
    JTextField inputTitle, inputAuthor, inputPublisher, inputCost;

    public AddMagazineDialog(final Library lib){
        this.lib =  lib;
        this.setSize(300, 300);
        this.setVisible(true);
        this.setLayout(new GridLayout(5, 1));
        this.setLocationRelativeTo(null);
        this.setTitle("ADD Magazine Details");

        //Making all labels that we need in this dialog box.
        titleLabel = new JLabel("Enter Title");
        authorLabel = new JLabel("Enter Author(s)");
        publisherLabel = new JLabel("Enter Pubisher");
        costLabel = new JLabel("Enter Cost");

        //Making all textFiels to take input that we need in this dialog box.
        inputTitle = new JTextField(10);
        inputAuthor = new JTextField(10);
        inputPublisher = new JTextField(10);
        inputCost = new JTextField(10);

        //Making panel to add label and input field for title.
        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.add(titleLabel);
        titlePanel.add(inputTitle);
        this.add(titlePanel);

        //Making panel to add label and input field for author.
        JPanel authorPanel = new JPanel(new FlowLayout());
        authorPanel.add(authorLabel);
        authorPanel.add(inputAuthor);
        this.add(authorPanel);

        //Making panel to add label and input field for Publisher.
        JPanel yearPanel = new JPanel(new FlowLayout());
        yearPanel.add(publisherLabel);
        yearPanel.add(inputPublisher);
        this.add(yearPanel);

        //Making panel to add label and input field for cost.
        JPanel costPanel = new JPanel(new FlowLayout());
        costPanel.add(costLabel);
        costPanel.add(inputCost);
        this.add(costPanel);

        ////Making panel to add Button to add Magazine according to above given data.
        JPanel addBtnPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Add");
        addBtnPanel.add(addBtn);
        this.add(addBtnPanel);

        //Add button click action is defined now.
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputTitle.getText().isEmpty() || inputAuthor.getText().isEmpty() || inputPublisher.getText().isEmpty()
                        || inputCost.getText().isEmpty()){
                    errorMsgDialog();
                }
                else{
                    addMagazine();
                    AddMagazineDialog.this.dispose();
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
    private void addMagazine(){
        String[] data = {inputTitle.getText(), inputAuthor.getText(), inputPublisher.getText(), inputCost.getText()};
        for(int i = 0; i < data.length; i++)
            data[i] = data[i].trim();
        try{
            lib.addItem(2, data);
        }
        catch(IOException e){

        }
    }
}