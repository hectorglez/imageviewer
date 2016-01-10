package imageviwer;

import imageviwer.model.Image;
import imageviwer.view.ImageDisplay;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements ImageDisplay {

    private Image image;
    private int grades;
    private final Application app;
    private BufferedImage imagen;

    ImagePanel(Image image, Application app) {
        this.image = image;
        grades = 0;
        this.app = app;
        this.setOpaque(false);
        imagen = (BufferedImage) image.bitMap();
        this.setPreferredSize(new Dimension(imagen.getWidth(), imagen.getHeight()));
    }

    public int getGrades() {
        return grades;
    }

    public void setGrades(int grades) {
        if (grades == 0 || this.grades == 270) {
            this.grades = 0;
        } else {
            this.grades = this.grades + grades;
        }
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        double r = Math.toRadians(grades);
        imagen = (BufferedImage) image.bitMap();
        AffineTransform at = AffineTransform.getRotateInstance(r, (imagen.getWidth())/2, (imagen.getHeight())/2);
        g2.drawImage(imagen, at, this);
    }

    @Override
    public Image image() {
        return image;
    }

    @Override
    public void show(Image image, int grades) {
        this.image = image;
        imagen = (BufferedImage) image.bitMap();
        setGrades(grades);
        this.setPreferredSize(new Dimension(imagen.getWidth(), imagen.getHeight()));
        this.repaint();
    }

}
