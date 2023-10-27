import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

public class ViewAllItemsFrame extends JFrame {
    private static int hoveredRow = -1;
    public ViewAllItemsFrame(final Library lib) {
        List<Item> items = lib.getItems();
        DefaultTableModel model = new DefaultTableModel(){ //Making dynamic table model.
            @Override
            public boolean isCellEditable(int row, int col){ //making the model non-editable.
                return false;
            }
        };
        model.addColumn("Item");
        model.addColumn("Title");
        model.addColumn("Author(s)");
        model.addColumn("Publisher_Company");
        model.addColumn("Year");
        model.addColumn("Popularity");
        model.addColumn("Cost");
        model.addColumn("Publication_Date");
        model.addColumn("Read");


        JTable table = new JTable(model) {
            @Override
            public Class getColumnClass(int column) {
                return column == 8 ? JButton.class : Object.class; // Set column 8 to render buttons
            }
        };
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

        for (Item i : items) {
            if (i.getTypeId() == 1) { // now add data if item is book.
                Book b = (Book) i; // Downcasting from item to book.
                model.addRow(new Object[]{"Book", b.getTitle(), b.getAuthor(), "Nill", b.getPublishedYear(),
                        b.getPopularityCount(), b.getCost(), "Nill", createButton(b)});
            } else if (i.getTypeId() == 2) { // now add data if item is Magazine.
                Magazine m = (Magazine) i;// Downcasting from item to book.
                model.addRow(new Object[]{"Magazine", m.getTitle(), m.getAuthor(), m.getPublisher(), "Nill",
                        m.getPopularityCount(), m.getCost(), "Nill", createButton(m)});
            } else { // now add data if item is Newspaper.
                Newspaper n = (Newspaper) i; //Downcasting from item to newspaper.
                model.addRow(new Object[]{"Newspaper", n.getTitle(), "Nill", n.getPublisher(), "Nill", n.popularityCount,
                        "Nill", n.getPublicationData(), createButton(n)});
            }
        }


        table.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());

        table.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor());

        JScrollPane scrollPane = new JScrollPane(table);
        this.setTitle("View All Items");
//        this.addWindowListener(    //Code to close the JFrame.
//                new WindowAdapter() {
//                    @Override
//                    public void windowClosing(WindowEvent e) {
//                        setVisible(false);
//                    }
//                }
//        );
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());

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
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(p1, BorderLayout.SOUTH);

        this.setSize(1000, 500);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

    private static JButton createButton(final Item item) {
        JButton button = new JButton("Read");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReadItemFrame read = new ReadItemFrame(item.getTitle()+".txt");
            }
        });
        return button;
    }
}
