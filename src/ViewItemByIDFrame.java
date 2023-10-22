import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ViewItemByIDFrame extends JFrame {
    private Library lib = null;
    private JTextField t1 = null;
    private DefaultTableModel model = null;
    private JTable table = null;
    ViewItemByIDFrame(final Library lib){
        this.lib = lib;
        model = new DefaultTableModel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("View Item By ID");

        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        JPanel textPanel = new JPanel(new FlowLayout());

         t1 = new JTextField(10);
        JLabel IDLabel = new JLabel("EnterID:");
        textPanel.add(IDLabel);
        textPanel.add(t1);
        this.add(textPanel, BorderLayout.NORTH);


        JButton viewBtn = new JButton("View");
        textPanel.add(viewBtn);
        viewBtn.addActionListener(new viewBtnActionListener());
        setVisible(true);

        //Adding button to go back.
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

    }
    private static JButton createButton() {
        JButton button = new JButton("Read");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "Click Function!");
                System.out.println("Function");
            }
        });
        return button;
    }
    private class viewBtnActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae){
            model.setColumnCount(0);
            model.setRowCount(0);

            int ID = Integer.parseInt(t1.getText().toString());
            t1.setText("");
            Item i = lib.getItemById(ID);


            model.addColumn("Item");
            model.addColumn("Title");
            if(i.getTypeId() == 1){ // now add data if item is book.
                Book b = (Book)i; // Downcasting from item to book.



                model.addColumn("Author");
                model.addColumn("Published Year");
                model.addColumn("Popularity");
                model.addColumn("Cost");
                model.addColumn("Read");
                model.addRow(new Object[]{"Book", b.getTitle(), b.getAuthor(), b.getPublishedYear(),
                        b.getPopularityCount(), b.getCost(), createButton()});
            }
            else if(i.getTypeId() == 2){ // now add data if item is Magazine.
                Magazine m = (Magazine)i;// Downcasting from item to book.
                model.addColumn("Author(s)");
                model.addColumn("Publisher");
                model.addColumn("Popularity");
                model.addColumn("Cost");
                model.addColumn("Read");
                model.addRow(new Object[]{"Magazine", m.getTitle(), m.getAuthor(), m.getPublisher(),
                        m.getPopularityCount(), m.getCost(), createButton()});
            }
            else { // now add data if item is Newspaper.
                Newspaper n = (Newspaper)i; //Downcasting from item to newspaper.
                model.addColumn("Publisher");
                model.addColumn("Popularity");
                model.addColumn("Publication Date");
                model.addColumn("Read");
                model.addRow(new Object[]{"Newspaper", n.getTitle(),n.getPublisher(), n.popularityCount,
                        n.getPublicationData(), createButton()});
            }
            if (table != null) {
                ViewItemByIDFrame.this.remove(table);
            }


            table = new JTable(model) {
                @Override
                public Class getColumnClass(int column) {
                    return column == getColumnCount() - 1 ? JButton.class : Object.class;
                }
            };
            ViewItemByIDFrame.this.add(new JScrollPane(table), BorderLayout.CENTER);
            table.getColumnModel().getColumn(table.getColumnCount() - 1).setCellRenderer(new ButtonRenderer());
            table.getColumnModel().getColumn(table.getColumnCount() - 1).setCellEditor(new ButtonEditor());
            ViewItemByIDFrame.this.revalidate(); //This is important.
            ViewItemByIDFrame.this.repaint();

        }
    }
}

