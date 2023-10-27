import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class ReadItemFrame extends JFrame {
    JTextArea displayContent;
    JScrollPane scroll;
    public ReadItemFrame(final String fileName){ //It is recieving the file name where content of item is saved.
        File file = new File(fileName);
        Scanner sc = null;
        try{
            sc = new Scanner(file);
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        String fileData = "";
        while(sc.hasNext()){
            fileData += sc.nextLine() + "\n";
        }
        displayContent = new JTextArea();
        displayContent.setText(fileData);
        scroll = new JScrollPane(displayContent);
        this.add(scroll);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                showDialog();//Dialog to confirm that user want to exit or not.
            }
            });
        this.setVisible(true);
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
    }
    private void showDialog(){
        JDialog dialog = new JDialog();
        dialog.setLayout(new GridLayout(1,2));
        JButton exitBtn = new JButton("Exit");
        JButton cancelBtn = new JButton("Cancel");
        JPanel msgPanel = new JPanel(new FlowLayout());
        JLabel msgLabel = new JLabel("Do you want to exit?");
        msgPanel.add(msgLabel);
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(exitBtn);
        btnPanel.add(cancelBtn);
        dialog.add(msgPanel);
        dialog.add(btnPanel);
        dialog.setSize(300, 300);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        //dialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE); //************** it's effect ?

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialog.dispose();
                ReadItemFrame.this.setVisible(true);
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cancel Button");
                dialog.dispose();
                ReadItemFrame.this.setVisible(true);
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
                ReadItemFrame.this.dispose();
            }
        });
    }
}
