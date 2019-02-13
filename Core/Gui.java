package Core;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class Gui extends JPanel implements ActionListener {

  private static final LayoutManager FlowLayout = null;

private static final LayoutManager BorderLayout = null;

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
    JPanel panel = new JPanel();
    panel.setBackground(Color.CYAN);
    panel.add(Box.createHorizontalGlue());
    panel.add(labels);
    panel.add(Box.createRigidArea(new Dimension(40,0)));
    panel.add(buttonConnect);
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
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
  
    JPanel voltagePanel = new JPanel();
    JPanel conductancePanel = new JPanel();
    JPanel graphPanel = new JPanel();
    JPanel portPanel = new JPanel();
    
    this.add(voltagePanel);
    this.add(conductancePanel);
    this.add(graphPanel);
    this.add(portPanel);
    
    voltagePanel.add(Box.createHorizontalGlue());
    JLabel vlabel = new JLabel("Voltage");
    vlabel.setHorizontalAlignment(getX());
    voltagePanel.add(vlabel);
    voltagePanel.add(Box.createRigidArea(new Dimension(40,0)));
    voltagePanel.add(createPanelVoltage());
     
    conductancePanel.add(Box.createHorizontalGlue());
    JLabel clabel = new JLabel("Conductance");
    clabel.setHorizontalAlignment(getX());
    conductancePanel.add(clabel);
    conductancePanel.add(Box.createRigidArea(new Dimension(10,0)));
    conductancePanel.add(createPanelConductance());
    
    graphPanel.add(Box.createRigidArea(new Dimension(40,20)));
    
    portPanel.add(Box.createHorizontalGlue());
    portPanel.add(createPanelSouth());

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
      frame.setMinimumSize(frame.getPreferredSize());
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
      frame.setSize(400, 300);
      frame.setVisible(true);
    }
  
}
