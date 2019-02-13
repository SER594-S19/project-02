package Core;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class Gui extends JPanel implements ActionListener {

  private static Model model;

  private final int PORT = 1594;
  private JLabel labelPublishPort;
  private final JButton buttonConnect = new JButton("run");
  private JSlider voltage, conductance;

  private Component createPanelConductance() {
    conductance = new JSlider(JSlider.HORIZONTAL,0,3,0);
    conductance.setMajorTickSpacing(10);
    conductance.setPaintTicks(true);

    Hashtable conductanceLabels = new Hashtable();
    conductanceLabels.put(new Integer( 0 ), new JLabel("0") );
    conductanceLabels.put(new Integer( 1 ), new JLabel("1") );
    conductanceLabels.put(new Integer( 2 ), new JLabel("2") );
    conductanceLabels.put(new Integer( 3 ), new JLabel("3") );

    conductance.setLabelTable(conductanceLabels);
    conductance.setPaintLabels(true);

    conductance.addChangeListener(changeListener);

    return conductance;
  }

  private Component createPanelVoltage() {
    voltage = new JSlider(JSlider.HORIZONTAL,0,3,0);
    voltage.setMajorTickSpacing(10);
    voltage.setPaintTicks(true);

    Hashtable voltageLabels = new Hashtable();
    voltageLabels.put(new Integer( 0 ), new JLabel("0") );
    voltageLabels.put(new Integer( 1 ), new JLabel("1") );
    voltageLabels.put(new Integer( 2 ), new JLabel("2") );
    voltageLabels.put(new Integer( 3 ), new JLabel("3") );

    voltage.setLabelTable(voltageLabels);
    voltage.setPaintLabels(true);

    voltage.addChangeListener(changeListener);

    return voltage;
  }


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

  public ChangeListener changeListener = new ChangeListener() {
    //making the slider react to changing positions
    public void stateChanged(ChangeEvent event) {
      voltage = (JSlider) event.getSource();


      if (!voltage.getValueIsAdjusting()) {
        model.updatePara("voltage", voltage.getValue());
        System.out.println(voltage.getValue());


        conductance = (JSlider) event.getSource();
        if (!voltage.getValueIsAdjusting()) {
          model.updatePara("conductance", voltage.getValue());
          System.out.println(conductance.getValue());
        }

      }
    }
  };

  public Gui() {

    model = new Model(new DataGenerator(), new Publisher(PORT));
    this.setBackground(Color.WHITE);
    this.setLayout(new BorderLayout());
    //this.add(voltage, BorderLayout.NORTH);
    this.add(createPanelVoltage(), BorderLayout.NORTH);
    this.add(createPanelConductance(), BorderLayout.CENTER);


    this.add(createPanelSouth(), BorderLayout.SOUTH);
    Dimension screen = getToolkit().getScreenSize();
    this.setSize(screen.width / 2, 3 * screen.height / 4);
    this.setLocation((screen.width - getSize().width) / 2, (screen.height - getSize().height) / 2);
    System.out.println("gui done");

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("listener trigger");
    if (e.getSource() == buttonConnect) {
      if (buttonConnect.getText().compareTo("run") == 0) {
        System.out.println("start");
        //dataGen();
        model.start();
        buttonConnect.setText("stop");
      } else if (buttonConnect.getText().compareTo("stop") == 0) {
        System.out.println("stop");
        model.stop();
        buttonConnect.setText("run");
      }
    }
  }


    public static void main(String[] args) {

      JFrame frame = new JFrame("Simulator");
      frame.setLayout(new GridLayout(1, 1));
      frame.add(new Gui());
      frame.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent e) {
          model.shutdown();
          System.exit(0);
        }
      });

      SwingUtilities.invokeLater(() -> {
        LineChart example = new LineChart("Line Chart Example");
        example.setAlwaysOnTop(true);
        example.pack();
        example.setSize(600, 400);
        example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        example.setVisible(true);
      });

      frame.pack();
      frame.setSize(600, 600);
      frame.setVisible(true);
    }
  
}
