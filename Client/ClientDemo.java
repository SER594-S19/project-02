package Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class ClientDemo extends JFrame implements Observer, ActionListener {

  private final Subscriber [] subscriber = new Subscriber[5];
  private final ExecutorService service;
  private JTextArea textArea = new JTextArea();
  private JTabbedPane tabbedPane1 = new JTabbedPane();
  private String ipFace;
  private String ipEyes;
  private String ipSkin;
  private String ipHeart;
  private String ipBCI;
  private String port_face;
  private String port_eyes;
  private String port_skin;
  private String port_heart;
  private String port_bci;


  private JFrame frame = new JFrame();
  private JButton buttonConnect1 = new JButton("connect");
  private JButton buttonConnect2 = new JButton("connect");
  private JButton buttonConnect3 = new JButton("connect");
  private JButton buttonConnect4 = new JButton("connect");
  private JButton buttonConnect5 = new JButton("connect");
  private JComponent panel1 = makeTextPanel1("Face");
  private JComponent panel2 = makeTextPanel2("Eyes");
  private JComponent panel3 = makeTextPanel3("Skin");
  private JComponent panel4 = makeTextPanel4("Heart Rate");
  private JComponent panel5 = makeTextPanel5("BCI");
//  private JComponent panel1 = makeTextPanel("Panel #1");
//  tabbedPane.add
  public ClientDemo() {

    service = Executors.newCachedThreadPool();



    setLayout(new BorderLayout());
    frame.add(textArea, BorderLayout.CENTER);

    frame.add(tabbedPane1);
    tabbedPane1.addTab("Face",panel1);
    tabbedPane1.addTab("Eyes",panel2);
    tabbedPane1.addTab("Skin",panel3);
    tabbedPane1.addTab("Heart Rate",panel4);
    tabbedPane1.addTab("BCI",panel5);
    frame.setSize(500,500);
    frame.setVisible(true);

  }

  protected JComponent makeTextPanel1(String text) {
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel("Enter IP address of Server ");
    filler.setHorizontalAlignment(JLabel.LEFT);
    JLabel filler1 = new JLabel("Select Port Number ");
    filler1.setHorizontalAlignment(JLabel.LEFT);
    JTextField textField = new JTextField();
    textField.setColumns(8);
    String[] portNumber = {"1594","1595","1596","1597","1598"};
    SpinnerListModel portModel = new SpinnerListModel(portNumber);
    JSpinner spinner = new JSpinner(portModel);

   // panel.setLayout(new GridLayout(4, 4));
    panel.add(filler);
    panel.add(textField);
    panel.add(filler1);
    panel.add(spinner);
    panel.add(buttonConnect1);
    buttonConnect1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ipFace = textField.getText();
        port_face = spinner.getValue().toString();
        subscriber[0] = new Subscriber(ipFace, Integer.parseInt(port_face));
      }
    });
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {

        shutdown();
        System.exit(0);

      }
    });
    return panel;
  }
  protected JComponent makeTextPanel2(String text) {
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel("Enter IP address of Server ");
    filler.setHorizontalAlignment(JLabel.LEFT);
    JLabel filler1 = new JLabel("Select Port Number ");
    filler1.setHorizontalAlignment(JLabel.LEFT);
    JTextField textField = new JTextField();
    textField.setColumns(8);
    String[] portNumber = {"1594","1595","1596","1597","1598"};
    SpinnerListModel portModel = new SpinnerListModel(portNumber);
    JSpinner spinner = new JSpinner(portModel);
    // panel.setLayout(new GridLayout(4, 4));
    panel.add(filler);
    panel.add(textField);
    panel.add(filler1);
    panel.add(spinner);
    panel.add(buttonConnect2);
    buttonConnect2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ipEyes = textField.getText();
        port_eyes = spinner.getValue().toString();
        subscriber[1] = new Subscriber(ipEyes, Integer.parseInt(port_eyes));
      }
    });
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {

        shutdown();
        System.exit(0);

      }
    });
    return panel;
  }
  protected JComponent makeTextPanel3(String text) {
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel("Enter IP address of Server ");
    filler.setHorizontalAlignment(JLabel.LEFT);
    JLabel filler1 = new JLabel("Select Port Number ");
    filler1.setHorizontalAlignment(JLabel.LEFT);
    JTextField textField = new JTextField();
    textField.setColumns(8);
    String[] portNumber = {"1594","1595","1596","1597","1598"};
    SpinnerListModel portModel = new SpinnerListModel(portNumber);
    JSpinner spinner = new JSpinner(portModel);

    // panel.setLayout(new GridLayout(4, 4));
    panel.add(filler);
    panel.add(textField);
    panel.add(filler1);
    panel.add(spinner);
    panel.add(buttonConnect3);
    buttonConnect3.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ipSkin = textField.getText();
        port_skin = spinner.getValue().toString();
        subscriber[2] = new Subscriber(ipSkin, Integer.parseInt(port_skin));
      }
    });
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {

        shutdown();
        System.exit(0);

      }
    });
    return panel;
  }
  protected JComponent makeTextPanel4(String text) {
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel("Enter IP address of Server ");
    filler.setHorizontalAlignment(JLabel.LEFT);
    JLabel filler1 = new JLabel("Select Port Number ");
    filler1.setHorizontalAlignment(JLabel.LEFT);
    JTextField textField = new JTextField();
    textField.setColumns(8);
    String[] portNumber = {"1594","1595","1596","1597","1598"};
    SpinnerListModel portModel = new SpinnerListModel(portNumber);
    JSpinner spinner = new JSpinner(portModel);

    // panel.setLayout(new GridLayout(4, 4));
    panel.add(filler);
    panel.add(textField);
    panel.add(filler1);
    panel.add(spinner);
    panel.add(buttonConnect4);
    buttonConnect4.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ipHeart = textField.getText();
        port_heart = spinner.getValue().toString();
        subscriber[3] = new Subscriber(ipHeart, Integer.parseInt(port_heart));
      }
    });
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {

        shutdown();
        System.exit(0);

      }
    });
    return panel;
  }
  protected JComponent makeTextPanel5(String text) {
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel("Enter IP address of Server ");
    filler.setHorizontalAlignment(JLabel.LEFT);
    JLabel filler1 = new JLabel("Select Port Number ");
    filler1.setHorizontalAlignment(JLabel.LEFT);
    JTextField textField = new JTextField();
    textField.setColumns(8);
    String[] portNumber = {"1594","1595","1596","1597","1598"};
    SpinnerListModel portModel = new SpinnerListModel(portNumber);
    JSpinner spinner = new JSpinner(portModel);


    // panel.setLayout(new GridLayout(4, 4));
    panel.add(filler);
    panel.add(textField);
    panel.add(filler1);
    panel.add(spinner);
    panel.add(buttonConnect5);
    buttonConnect5.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ipBCI = textField.getText();
        port_bci = spinner.getValue().toString();
        subscriber[4] = new Subscriber(ipBCI, Integer.parseInt(port_bci));

      }
    });
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {

        shutdown();
        System.exit(0);

      }
    });
    return panel;
  }

  private void close() {
    System.out.println("clossing ....... +++++++");
    subscriber[0].stop();
    subscriber[1].stop();
    subscriber[2].stop();
    subscriber[3].stop();
    subscriber[4].stop();
  }
  
    private void shutdown() {
    subscriber[0].stop();
    subscriber[1].stop();
    subscriber[2].stop();
    subscriber[3].stop();
    subscriber[4].stop();

    service.shutdown();
    try {
      if (!service.awaitTermination(10, TimeUnit.SECONDS)) {
        service.shutdownNow();
      }
    } catch (InterruptedException ex) {
    }
  }
  

  @Override
  public void update(Observable o, Object arg) {
    String data = ((Subscriber) o).getObject().toString();
    if (data.compareTo("FIN") != 0)
      textArea.append(data + "\n" );
    else {
      close();
      buttonConnect1.setEnabled(true);
      buttonConnect2.setEnabled(true);
      buttonConnect3.setEnabled(true);
      buttonConnect4.setEnabled(true);
      buttonConnect5.setEnabled(true);

    }    
  }

  public static void main(String[] args) {
    ClientDemo tester = new ClientDemo();
     
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    buttonConnect1.setEnabled(false);
    buttonConnect2.setEnabled(false);
    buttonConnect3.setEnabled(false);
    buttonConnect4.setEnabled(false);
    buttonConnect5.setEnabled(false);
    service.submit(subscriber[0]);
    subscriber[0].addObserver(this);

    service.submit(subscriber[1]);
    subscriber[1].addObserver(this);

    service.submit(subscriber[2]);
    subscriber[2].addObserver(this);

    service.submit(subscriber[3]);
    subscriber[3].addObserver(this);

    service.submit(subscriber[4]);
    subscriber[4].addObserver(this);

    
  }
}
