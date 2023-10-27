import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.awt.event.*;
import java.util.List;

public class BorrowItemFrame extends JFrame {
    private Library lib;
    private static int hoveredRow = -1;
    JTable table;
    DefaultTableModel model;
    JTextField inputID;
    JTextField inputName;
    JLabel labelName;
    JPanel mainPanel;
    public BorrowItemFrame(final Library lib){
        this.lib = lib;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(800, 500);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        model = new DefaultTableModel();
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        mainPanel = new JPanel(new GridLayout(3,1));
        mainPanel.add(scrollPane);
        JLabel label1 = new JLabel("Enter ID of item you want to borrow");
        inputID = new JTextField(10);
        JPanel subInputPanel = new JPanel(new FlowLayout());
        subInputPanel.add(label1);
        subInputPanel.add(inputID);
        mainPanel.add(subInputPanel);
        this.add(mainPanel, BorderLayout.CENTER);

        this.addBackBtn();//Funtion to add back button at the end of the page.

        JButton BorrowBtn = new JButton("Borrow");
        BorrowBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(inputID.getText().toString());
                Item it = lib.getItemById(id);
                if(it.isBorrowed()){
                    //System.out.println("Entered");
                    displayItemNotAvailable();//this function will display that item is not available.
                }
                else{
                    JPanel namePanel = getBorrowerNamePanel();
                    JButton nextBtn = new JButton("Next");
                    namePanel.add(nextBtn);

                    JPanel displayCostWithMsgPanel = costWithMsgPanel(it.calculateCost());
                    JPanel isBorrowedPanel = new JPanel(new GridLayout(2,1));//Panel to hold above made panels.
                    isBorrowedPanel.add(namePanel);
                    mainPanel.add(isBorrowedPanel);
                    revalidate(); // Revalidate the layout
                    repaint();
                    nextBtn.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e){
                            isBorrowedPanel.add(displayCostWithMsgPanel);
                            mainPanel.add(isBorrowedPanel);
                            inputID.setText("");
                            lib.borrowItem(it, new Borrower(inputName.getText()));
                            inputName.setText("");
                            revalidate(); // Revalidate the layout
                            repaint();
                        }
                    });

                }

            }
        });
        subInputPanel.add(BorrowBtn);

        model.addColumn("Item");
        model.addColumn("Title");
        model.addColumn("Author(s)");
        model.addColumn("Publisher_Company");
        model.addColumn("Year");
        model.addColumn("Popularity");
        model.addColumn("Cost");
        model.addColumn("Publication_Date");
        model.addColumn("Read");

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

        List<Item> items = lib.getItems();

        for(Item i : items){
            if(i.getTypeId() == 1){ // now add data if item is book.
                Book b = (Book)i; // Downcasting from item to book.
                model.addRow(new Object[]{"Book", b.getTitle(), b.getAuthor(), "Nill", b.getPublishedYear(),
                        b.getPopularityCount(), b.getCost(), "Nill", createButton(b)});
            }
            else if(i.getTypeId() == 2){ // now add data if item is Magazine.
                Magazine m = (Magazine)i;// Downcasting from item to book.
                model.addRow(new Object[]{"Magazine", m.getTitle(), m.getAuthor(), m.getPublisher(), "Nill",
                        m.getPopularityCount(), m.getCost(), "Nill", createButton(m)});
            }
            else { // now add data if item is Newspaper.
                Newspaper n = (Newspaper)i; //Downcasting from item to newspaper.
                model.addRow(new Object[]{"Newspaper", n.getTitle(), "Nill", n.getPublisher(), "Nill", n.popularityCount,
                        "Nill", n.getPublicationData(), createButton(n)});
            }
        }
        table.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor());

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

    private void displayItemNotAvailable(){
        JPanel msgPanel = new JPanel(new FlowLayout());
        JLabel msgLabel = new JLabel("This Item is not available");
        inputID.setText("");
        msgPanel.add(msgLabel);
        this.add(msgPanel);
    }
    private JPanel getBorrowerNamePanel(){
        JPanel panel = new JPanel(new FlowLayout());
        labelName = new JLabel("Enter name of borrower");
        inputName = new JTextField(10);
        panel.add(labelName);
        panel.add(inputName);
        return panel;
    }
    private JPanel costWithMsgPanel(final Double cost){
        JLabel costLabel = new JLabel("Cost of item is " + cost);
        JLabel msgLabel = new JLabel("Item has been borrowed Successfully");
        JPanel p = new JPanel(new FlowLayout());
        p.add(costLabel);
        p.add(msgLabel);
        return p;
    }
    private void addBackBtn(){
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel backBtnPanel = new JPanel(new FlowLayout());
        backBtnPanel.add(backBtn);
        this.add(backBtnPanel, BorderLayout.SOUTH);
    }
}
