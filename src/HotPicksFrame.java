import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;

import java.io.IOException;
import java.util.List;

public class HotPicksFrame extends JFrame {
    public HotPicksFrame()throws IOException{

        Library lib = new Library();
        List<Item> items = lib.getHotPicks();
        DefaultTableModel model = new DefaultTableModel(); //Making dynamic table model.
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

        for(Item i : items){
            if(i.getTypeId() == 1){ // now add data if item is book.
                Book b = (Book)i; // Downcasting from item to book.
                model.addRow(new Object[]{"Book", b.getTitle(), b.getAuthor(), "Nill", b.getPublishedYear(),
                        b.getPopularityCount(), b.getCost(), "Nill", createButton()});
            }
            else if(i.getTypeId() == 2){ // now add data if item is Magazine.
                Magazine m = (Magazine)i;// Downcasting from item to book.
                model.addRow(new Object[]{"Magazine", m.getTitle(), m.getAuthor(), m.getPublisher(), "Nill",
                m.getPopularityCount(), m.getCost(), "Nill", createButton()});
            }
            else { // now add data if item is Newspaper.
                Newspaper n = (Newspaper)i; //Downcasting from item to newspaper.
                model.addRow(new Object[]{"Newspaper", n.getTitle(), "Nill", n.getPublisher(), "Nill", n.popularityCount,
                "Nill", n.getPublicationData(), createButton()});
            }
        }



        table.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());

        table.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor());

        JScrollPane scrollPane = new JScrollPane(table);

        //JFrame frame = new JFrame("JTable with Button Example");
        this.setTitle("HotPicksItems");
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

}

class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return (Component) value;
    }
}

// Custom editor for JButton
//class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
//    private JButton button;
//
//    public ButtonEditor() {
//        button = new JButton();
//        button.setOpaque(true);
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                fireEditingStopped();
//                //JOptionPane.showMessageDialog(null, "Button Editor!");
//                System.out.println("Editor");
//            }
//        });
//    }
//
//    @Override
//    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//        return button;
//    }
//
//    @Override
//    public Object getCellEditorValue() {
//        return button;
//    }
//}
//
class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private JButton button;
    private String originalText;

    public ButtonEditor() {
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                JOptionPane.showMessageDialog(null, "Button Clicked!");
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value instanceof JButton) {
            button = (JButton) value;
            originalText = button.getText();
        }
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        // Restore the original text when editing is stopped
        button.setText(originalText);
        return button;
    }
}

