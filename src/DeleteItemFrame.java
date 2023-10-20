import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DeleteItemFrame extends JFrame {
    private Library lib;
    private JTextField t1 = null;
    private JTable table = null;
    public DeleteItemFrame(final Library lib){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setSize(500, 300);
        this.setLayout(new BorderLayout());

        JPanel textPanel = new JPanel(new FlowLayout());

        t1 = new JTextField(10);
        JLabel IDLabel = new JLabel("EnterID:");
        textPanel.add(IDLabel);
        textPanel.add(t1);
        this.add(textPanel, BorderLayout.NORTH);


        JButton deleteBtn = new JButton("Delete");
        textPanel.add(deleteBtn);
        deleteBtn.addActionListener(new DeleteItemActionListener());
        setVisible(true);

    }
    private class DeleteItemActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae){
            int ID = Integer.parseInt(t1.getText().toString());
            Library lib = null;
            try{
                lib = new Library();
            }
            catch (IOException e){

            }
            JLabel result = new JLabel();
            boolean flag = true;
            try{
                flag = lib.deleteItem(ID);
            }
            catch(IOException e){
                result.setText("Error Occurred");
                return;
            }

            if(flag)
                result.setText("Item has been deleted successfully");
            else
                result.setText("Item not found");
            result.setBounds(100, 300, 100, 70);
            JPanel p1 = new JPanel();
            p1.add(result);
            DeleteItemFrame.this.add(p1, BorderLayout.CENTER);

            DeleteItemFrame.this.revalidate();

        }
    }
}
