import java.awt.BasicStroke;  
import java.awt.BorderLayout;  
import java.awt.Color;  
import java.awt.Container;  
import java.awt.Graphics2D;  
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;  
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;  
  
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.BorderFactory;  
import javax.swing.JComponent;  
import javax.swing.JFrame;  
import javax.swing.JPanel;  
import javax.swing.JSlider;  
import javax.swing.Painter;  
import javax.swing.UIDefaults;  
import javax.swing.UIManager;  
import javax.swing.UnsupportedLookAndFeelException;  
import javax.swing.UIManager.LookAndFeelInfo;  
  
@SuppressWarnings("serial")  
public class JSlider_customized extends JFrame {  
  
    public JSlider_customized() {  
        // TODO Auto-generated constructor stub  
        for (LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {  
            if (laf.getName().equals("Nimbus")) {  
                try {  
                    UIManager.setLookAndFeel(laf.getClassName());  
                } catch (ClassNotFoundException | InstantiationException  
                        | IllegalAccessException  
                        | UnsupportedLookAndFeelException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
            }  
        }  
  
        JPanel panel = new JPanel(new GridLayout(0, 1, 20, 20));  
        JSlider slider = new JSlider(0, 100, 50);  
        Container c = getContentPane();  
        c.add(panel, BorderLayout.CENTER);  
        panel.setBackground(Color.DARK_GRAY);  
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  
//        panel.add(slider);  
        UIDefaults sliderDefaults = new UIDefaults();  
//        sliderDefaults.put("Slider.thumbWidth", 20);  
//        sliderDefaults.put("Slider.thumbHeight", 20);  
//        sliderDefaults.put("Slider:SliderThumb.backgroundPainter",  
//                new Painter<JComponent>() {  
//                    public void paint(Graphics2D g, JComponent c, int w, int h) {  
//                        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  
//                                RenderingHints.VALUE_ANTIALIAS_ON);  
//                        g.setStroke(new BasicStroke(2f));  
//  
//                        g.setColor(Color.RED);  
//                        g.fillOval(1, 1, w - 3, h - 3);  
//                        g.setColor(Color.WHITE);  
//                        g.drawOval(1, 1, w - 3, h - 3);  
//                           
//                        BufferedImage img;  
//                        File image = new File("bin/time.jpg");
//                        try {  
//                            img = ImageIO.read(image);  
//                            g.drawImage(img, 0, 0, w, h, c); // the same for the BufferedImage when using g.drawImage(img, 0, 0, w, h, null);  
//                        } catch (IOException e) {  
//                            // TODO Auto-generated catch block  
//                            e.printStackTrace();  
//                        }  
//                                   
//                    }  
//                });  
//        sliderDefaults.put("Slider:SliderTrack.backgroundPainter",  
//                new Painter<JComponent>() {  
//                    public void paint(Graphics2D g, JComponent c, int w, int h) {  
//                        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  
//                                RenderingHints.VALUE_ANTIALIAS_ON);  
//                        g.setStroke(new BasicStroke(2f));  
//                        g.setColor(Color.GRAY);  
//                        g.fillRoundRect(0, 6, w - 1, 8, 8, 8);  
//                        g.setColor(Color.WHITE);  
//                        g.drawRoundRect(0, 6, w - 1, 8, 8, 8);  
//                    }  
//                });  
        slider.putClientProperty("Nimbus.Overrides", sliderDefaults);  
        slider.putClientProperty("Nimbus.Overrides.InheritDefaults", false);  
  
        JSlider slider2 = new JSlider(0, 100, 50);  
        panel.add(slider2);  
    }  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
  
        // JFrame.setDefaultLookAndFeelDecorated(true);  
        JSlider_customized frame = new JSlider_customized();  
        // frame.setUndecorated(true);  
        // frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);  
  
        frame.pack();// leaves the frame layout manager in charge of the frame  
                        // size, so that all its contents are at or above their  
                        // preferred sizes  
        // frame.setLocation(100, 100); // set the frame location  
        frame.setLocationRelativeTo(null); // centers a frame onscreen  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);  
    }  
  
}  