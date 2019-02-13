package Core;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Calendar;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**This runs the GUI simulator for Eye Tracking Device
 *  The simulator shows movement of eyes and validation scroller value from 0 - 4
 * @author Bharat Goel
 */
public class Gui extends JPanel implements ActionListener {

  private static Model model;
  private final int PORT = 1594;
  protected JLabel labelPublishPort;
  private final JButton buttonConnect = new JButton("run");

  private Scale mousePosition = new Scale(0, 0);
  private int validation;
  private long pupily;
  private long pupilx;
  static long timeStamp = 0;
  private long fixation;
  private long aoi=0;
  static Data data;
  private Eye leftEye = new Eye(350 - 120, 300, 100, 20);
  private Eye rightEye = new Eye(350 + 120, 300, 100, 20);
  private Font font = new Font("Arial", Font.PLAIN, 50);
  private Font font2 = new Font("Arial", Font.PLAIN, 150);
  final DecimalFormat df = new DecimalFormat("0.####");
  static JSlider Validation = new JSlider();
  final static JTextField text = new JTextField(50);
  final static pupilControl Pupilx = new pupilControl(0, 5000, 0, 1000);
  final static pupilControl Pupily = new pupilControl(0, 5000, 0, 1000);
  HashMap<Set<Integer>,Long> grid = new HashMap<>();
  private Component createPanelSouth() {

    JPanel labels = new JPanel();
    labels.setBackground(Color.GRAY);
    labels.add(new JLabel("  Publishing at port: "));
    labelPublishPort = new JLabel("" + PORT);
    labels.add(labelPublishPort);
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBackground(Color.CYAN);
    panel.add(labels, BorderLayout.WEST);
    panel.add(buttonConnect, BorderLayout.EAST);
    buttonConnect.addActionListener(this);
    buttonConnect.setEnabled(true);
    return panel;

  }

  public Gui() {

    model = new Model(new DataGenerator(), new Publisher(PORT));
    setPreferredSize(new Dimension(800, 600));
    addMouseMotionListener(new MouseHandler());
    this.add(createPanelSouth(), BorderLayout.SOUTH);
    Validation.setMaximum(4);
    Validation.setMajorTickSpacing(1);
    Validation.setPaintTicks(true);
    Validation.setValue(0);
    Validation.setFont(new Font("Calibri", Font.BOLD, 16));
    Validation.setToolTipText("Validating");
    Validation.setForeground(Color.WHITE);
    Validation.setBackground(Color.PINK);
    Validation.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
    Validation.setPaintLabels(true);
    Validation.addChangeListener(new ChangeListener(){ public void stateChanged(ChangeEvent e) {
        validation = Validation.getValue(); }});
  
    Pupilx.setFont(new Font("Calibri", Font.BOLD, 20));
    Pupilx.setToolTipText("PUPIL RIGHT");
    Pupilx.setForeground(Color.WHITE);
    Pupilx.setBackground(Color.GRAY);
    Pupilx.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
    Pupilx.setPaintLabels(true);
    Pupilx.setPaintTicks(true);
    Pupilx.setPreferredSize(new Dimension(150,150));
    Pupilx.addChangeListener(new ChangeListener(){
        @Override
        public void stateChanged(ChangeEvent e) {
            text.setText(df.format(Pupilx.getScaledValue()));
            pupily = (long) Pupilx.getScaledValue();
            rightEye.setR2(pupily*15);
            repaint();
        }
    });
    text.addKeyListener(new KeyAdapter(){
        @Override
        public void keyReleased(KeyEvent ke) {
            String typed = text.getText();
            Pupilx.setValue(0);
            if(!typed.matches("\\d+(\\.\\d*)?")) {
                return;
            }
            double value = Double.parseDouble(typed)*Pupilx.scale;
            Pupilx.setValue((int)value);
        }
    });
    
    Pupily.setFont(new Font("Calibri", Font.BOLD, 16));
    Pupily.setToolTipText("PUPIL LEFT");
    Pupily.setForeground(Color.WHITE);
    Pupily.setBackground(Color.GRAY);
    Pupily.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
    Pupily.setPaintLabels(true);
    Pupily.setPaintTicks(true);
    Pupily.setPreferredSize(new Dimension(150,150));
    Pupily.addChangeListener(new ChangeListener(){
        @Override
        public void stateChanged(ChangeEvent e) {
            text.setText(df.format(Pupily.getScaledValue()));
            pupilx = (long) Pupily.getScaledValue();
            leftEye.setR2(pupilx*15);
            repaint();
        }
    });
    text.addKeyListener(new KeyAdapter(){
        @Override
        public void keyReleased(KeyEvent ke) {
            String typed = text.getText();
            Pupily.setValue(0);
            if(!typed.matches("\\d+(\\.\\d*)?")) {
                return;
            }
            double value = Double.parseDouble(typed)*Pupily.scale;
            Pupily.setValue((int)value);
        }
    });
    
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    leftEye.draw(g, mousePosition);
    rightEye.draw(g, mousePosition);
  }


  private class MouseHandler extends MouseAdapter {

    @Override
    public void mouseMoved(MouseEvent e) {
      mousePosition.setX(e.getX());
      mousePosition.setY(e.getY());
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.HOUR_OF_DAY, 0);
      calendar.set(Calendar.MINUTE, 0);
      calendar.set(Calendar.SECOND, 0);
      long initialTime = calendar.getTimeInMillis();
      long currentTimeStamp = (long)((System.currentTimeMillis() - initialTime) * .001);
      fixation= currentTimeStamp-timeStamp;
      timeStamp = (long)((System.currentTimeMillis() - initialTime) * .001);
      aoi=calculateAoi(e.getX(),e.getY(),fixation);
      data=new Data(timeStamp,e.getX(),e.getY(),pupily,pupilx,validation,fixation,aoi);
      repaint();
    }

      public long calculateAoi(int x,int y,long fixation){
        long value;
        long max=0;
        Set<Integer> pixel=new HashSet<>();
          pixel.add(x);
          pixel.add(y);
          if(grid.containsKey(pixel)){
              value=grid.get(pixel)+fixation;
              if(value>max)
                  max=value;
              grid.put(pixel,value);
          }
          else{
              grid.put(pixel,fixation);
              if(fixation>max)
                  max=fixation;
          }
          return max;
      }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("listener trigger");
    if (e.getSource() == buttonConnect) {
      if (buttonConnect.getText().compareTo("run") == 0) {
        System.out.println("start");
        model.start();
        buttonConnect.setText("stop");
      } else if (buttonConnect.getText().compareTo("stop") == 0) {
        System.out.println("stop");
        model.stop();
        buttonConnect.setText("run");
      }
    }
  }

  public static Data getData(){
    return data;

  }
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        Gui gui = new Gui();
        JFrame frame = new JFrame();
        frame.setTitle("Eye Tracking Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(gui);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setBounds(200, 200, 1000, 700);
        frame.setBackground(Color.YELLOW);
        frame.getContentPane().add(Validation, BorderLayout.SOUTH);
        frame.getContentPane().add(text, BorderLayout.NORTH);
        frame.getContentPane().add(Pupilx, BorderLayout.EAST);
        frame.getContentPane().add(Pupily, BorderLayout.WEST);
     
        gui.requestFocus();
      }

    });
  }
}

class pupilControl extends JSlider {

    final int scale;

    public pupilControl(int min, int max, int value, int scale) {
        super(min, max, value);
        this.scale = scale;
    }

    public double getScaledValue() {
        return ((double)super.getValue()) / this.scale;
    }
}
