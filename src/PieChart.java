import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.text.DecimalFormat;
import java.util.List;

class PieChart extends JPanel {
    private List<Item> items;
    public PieChart(final List<Item> items) {
        this.items = items;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        int diameter = Math.min(width, height) - 40;

        int totalPopularityCount = 0;
        for (Item item : items) {
            totalPopularityCount += item.getPopularityCount();
        }

        double startAngle = 0;
        for (Item item : items) {
            double arcAngle = (item.getPopularityCount() / (double) totalPopularityCount) * 360;

            // Draw pie segment
            g2d.setColor(getRandomColor());
            g2d.fill(new Arc2D.Double((width - diameter) / 2, (height - diameter) / 2, diameter, diameter,
                    startAngle, arcAngle, Arc2D.PIE));

            // Draw text label
            double midAngle = startAngle + arcAngle / 2;
            double labelX = (width / 2) + (diameter / 2) * Math.cos(Math.toRadians(midAngle));
            double labelY = (height / 2) + (diameter / 2) * Math.sin(Math.toRadians(midAngle));

            g2d.setColor(Color.BLACK);
            double percentage = (item.popularityCount / (double) totalPopularityCount) * 100;
            String label = item.getPopularityCount() + ": " + new DecimalFormat("0.00").format(percentage) + "%";
            g2d.drawString(label, (float) labelX, (float) labelY);

            startAngle += arcAngle;
        }
    }

    private Color getRandomColor() {
        return new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
    }
}
