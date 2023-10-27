import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EditBookDialog extends JDialog {
    private Library lib;
    private int bookID;
    private JLabel titleLabel, costLabel, authorLabel, yearLabel;
    private JLabel msgLabel;
    private JTextField inputTitle, inputAuthor, inputYear, inputCost;
    private Item item;

    public EditBookDialog(final Library lib, final int id, JLabel msgLabel, Item item){
        this.item = item;
        this.msgLabel = msgLabel;
        this.lib =  lib;
        this.bookID = id;
        this.setSize(300, 300);
        this.setVisible(true);
        this.setLayout(new GridLayout(5, 1));
        this.setLocationRelativeTo(null);
        this.setTitle("Enter Book Details");

        //Making all labels that we need in this dialog box.
        titleLabel = new JLabel("Enter Title");
        authorLabel = new JLabel("Enter Author");
        yearLabel = new JLabel("Enter Year");
        costLabel = new JLabel("Enter Cost");

        //Making all textFiels to take input that we need in this dialog box.
        inputTitle = new JTextField(10);
        inputAuthor = new JTextField(10);
        inputYear = new JTextField(10);
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

        //Making panel to add label and input field for year.
        JPanel yearPanel = new JPanel(new FlowLayout());
        yearPanel.add(yearLabel);
        yearPanel.add(inputYear);
        this.add(yearPanel);

        //Making panel to add label and input field for cost.
        JPanel costPanel = new JPanel(new FlowLayout());
        costPanel.add(costLabel);
        costPanel.add(inputCost);
        this.add(costPanel);

        ////Making panel to add Button to Edit book according to above given data.
        JPanel editBtnPanel = new JPanel(new FlowLayout());
        JButton editBtn = new JButton("Edit");
        editBtnPanel.add(editBtn);
        this.add(editBtnPanel);

        //Add button click action is defined now.
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputTitle.getText().isEmpty() || inputAuthor.getText().isEmpty() || inputYear.getText().isEmpty()
                        || inputCost.getText().isEmpty()){
                    errorMsgDialog();
                }
                else{
                    editBook();
                    EditBookDialog.this.dispose();
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
    private void editBook(){
        String[] data = {inputTitle.getText(), inputAuthor.getText(), inputYear.getText(), inputCost.getText()};
        for(int i = 0; i < data.length; i++)
            data[i] = data[i].trim();

        try{
            //for(int i = 0; i < )
            lib.editItem(this.item, data);
        }
        catch(IOException e){

        }
        msgLabel.setText("Book has been edited successfully");
    }
}
