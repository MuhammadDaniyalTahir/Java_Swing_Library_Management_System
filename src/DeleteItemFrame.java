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
        this.lib = lib;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 300);
        this.setLayout(new BorderLayout());
        this.setTitle("Delete Item By ID");

        JPanel textPanel = new JPanel(new FlowLayout());

        t1 = new JTextField(10);
        JLabel IDLabel = new JLabel("EnterID:");
        textPanel.add(IDLabel);
        textPanel.add(t1);
        this.add(textPanel, BorderLayout.NORTH);
        JButton back = new JButton("Back");
        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                }
        );
        JPanel p1 = new JPanel(new FlowLayout());
        p1.add(back);
        this.add(p1, BorderLayout.SOUTH);

        JButton deleteBtn = new JButton("Delete");
        textPanel.add(deleteBtn);
        deleteBtn.addActionListener(new DeleteItemActionListener());
        setVisible(true);
        this.setLocationRelativeTo(null);
    }
    private class DeleteItemActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae){
            int ID = Integer.parseInt(t1.getText().toString());
            JLabel result = new JLabel();
            boolean flag = false;
            try{
                flag = lib.deleteItem(ID);
            }
            catch(IOException e){
                result.setText("Error Occurred");
                return;
            }

            if(flag == true)
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
