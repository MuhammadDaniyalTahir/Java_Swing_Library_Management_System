import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ViewBorrowersListFrame extends JFrame {
    private static int hoveredRow = -1;
    public ViewBorrowersListFrame(final Library lib){
        DefaultTableModel model = new DefaultTableModel();

        this.setTitle("View Borrowers of each item Borrowed");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(1000, 500);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        JTable table = new JTable(model);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
        JButton backBtn = new JButton("Back");
        this.add(backBtn, BorderLayout.SOUTH);
        backBtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                }
        );

        //Following code to add mouse hovering in the table.
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                hoveredRow = -1;
                table.repaint();
            }
        });

        table.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Point point = e.getPoint();
                int row = table.rowAtPoint(point);

                if (row != hoveredRow) {
                    hoveredRow = row;
                    table.repaint();
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // Not used in this example
            }
        });
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);

                if (row == hoveredRow) {
                    component.setBackground(Color.gray);
                } else {
                    component.setBackground(table.getBackground());
                }

                return component;
            }
        });


        //Add columns to the table.
        model.addColumn("Borrower_Name");
        model.addColumn("Item");
        model.addColumn("Title");
        model.addColumn("Author(s)");
        model.addColumn("Publisher_Company");
        model.addColumn("Year");
        model.addColumn("Popularity");
        model.addColumn("Cost");
        model.addColumn("Publication_Date");

        List<Item> items = lib.getItems();
        for(Item i : items){
            if(i.isBorrowed()){ //Check before showing the borrowers that the item is borrowed or not.
                List<Borrower> borrowers = i.getBorrowers();
                String borrowerName = borrowers.get(0).getName();
                if(i.getTypeId() == 1){ // now show data if item is book.
                    Book b = (Book)i; // Downcasting from item to book.

                    model.addRow(new Object[]{borrowerName, "Book", b.getTitle(), b.getAuthor(), "Nill", b.getPublishedYear(),
                            b.getPopularityCount(), b.getCost(), "Nill"});
                }
                else if(i.getTypeId() == 2){ // now show data if item is Magazine.
                    Magazine m = (Magazine)i;// Downcasting from item to book.
                    model.addRow(new Object[]{borrowerName, "Magazine", m.getTitle(), m.getAuthor(), m.getPublisher(), "Nill",
                            m.getPopularityCount(), m.getCost(), "Nill"});
                }
                else { // now show data if item is Newspaper.
                    Newspaper n = (Newspaper)i; //Downcasting from item to newspaper.
                    model.addRow(new Object[]{borrowerName, "Newspaper", n.getTitle(), "Nill", n.getPublisher(), "Nill", n.popularityCount,
                            "Nill", n.getPublicationData()});
                }
            }
        }
    }
}
