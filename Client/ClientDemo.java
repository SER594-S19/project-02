package Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ClientDemo extends JFrame implements Observer, ActionListener {

  private final Subscriber  [] subscriber = new Subscriber[5];
  private final ExecutorService service;

  //JTextField myTextField = new JTextField("Team Awesome!");


  private JTextArea textArea = new JTextArea();
  private JTextArea ipNum1 = new JTextArea(1,10);
  private JTextArea portNum1 = new JTextArea(1,5);
  private JButton buttonConnect = new JButton("Connect");
  private JTextArea ipNum2 = new JTextArea(1,10);
  private JTextArea portNum2 = new JTextArea(1,5);
  private JTextArea ipNum3 = new JTextArea(1,10);
  private JTextArea portNum3 = new JTextArea(1,5);
  private JTextArea ipNum4 = new JTextArea(1,10);
  private JTextArea portNum4 = new JTextArea(1,5);
  private JTextArea ipNum5 = new JTextArea(1,10);
  private JTextArea portNum5 = new JTextArea(1,5);

  public ClientDemo() {

    service = Executors.newCachedThreadPool();

    // TO TEST, RUN TWO SERVERS IN PORTS 1594 and 1595

    //subscriber[0] = new Subscriber("localhost", 1594);
    //subscriber[1] = new Subscriber("localhost", 1595);

    JLabel ipLabel1 = new JLabel("IP Address: ");
    JLabel portLabel1 = new JLabel("Port Number: ");
    JLabel ipLabel2 = new JLabel("IP Address: ");
    JLabel portLabel2 = new JLabel("Port Number: ");
    JLabel ipLabel3 = new JLabel("IP Address: ");
    JLabel portLabel3 = new JLabel("Port Number: ");
    JLabel ipLabel4 = new JLabel("IP Address: ");
    JLabel portLabel4 = new JLabel("Port Number: ");
    JLabel ipLabel5 = new JLabel("IP Address: ");
    JLabel portLabel5 = new JLabel("Port Number: ");
    JPanel topPanel= new JPanel();
    topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

    JPanel inputPanel1 = new JPanel(new BorderLayout());
    JPanel ipPanel1 = new JPanel(new BorderLayout());
    JPanel portPanel1 = new JPanel(new BorderLayout());

    //ipNum1.setLineWrap(true);
    //ipNum1.setPreferredSize(new Dimension(10, 10));

    portNum1.setBorder(BorderFactory.createLineBorder(Color.black));
    ipNum1.setBorder(BorderFactory.createLineBorder(Color.black));

    JPanel inputPanel2 = new JPanel(new BorderLayout());
    JPanel ipPanel2 = new JPanel(new BorderLayout());
    JPanel portPanel2 = new JPanel(new BorderLayout());

    portNum2.setBorder(BorderFactory.createLineBorder(Color.black));
    ipNum2.setBorder(BorderFactory.createLineBorder(Color.black));

    JPanel inputPanel3 = new JPanel(new BorderLayout());
    JPanel ipPanel3 = new JPanel(new BorderLayout());
    JPanel portPanel3 = new JPanel(new BorderLayout());

    portNum3.setBorder(BorderFactory.createLineBorder(Color.black));
    ipNum3.setBorder(BorderFactory.createLineBorder(Color.black));

    JPanel inputPanel4 = new JPanel(new BorderLayout());
    JPanel ipPanel4 = new JPanel(new BorderLayout());
    JPanel portPanel4 = new JPanel(new BorderLayout());

    portNum4.setBorder(BorderFactory.createLineBorder(Color.black));
    ipNum4.setBorder(BorderFactory.createLineBorder(Color.black));

    JPanel inputPanel5 = new JPanel(new BorderLayout());
    JPanel ipPanel5 = new JPanel(new BorderLayout());
    JPanel portPanel5 = new JPanel(new BorderLayout());

    portNum5.setBorder(BorderFactory.createLineBorder(Color.black));
    ipNum5.setBorder(BorderFactory.createLineBorder(Color.black));

    setLayout(new BorderLayout());

    ipPanel1.add(ipLabel1,BorderLayout.WEST);
    ipPanel1.add(ipNum1,BorderLayout.CENTER);
    ipPanel1.setBorder(new EmptyBorder(5, 5, 5, 5));

    portPanel1.add(portLabel1,BorderLayout.WEST);
    portPanel1.add(portNum1,BorderLayout.CENTER);
    portPanel1.setBorder(new EmptyBorder(5, 5, 5, 5));

    ipPanel2.add(ipLabel2,BorderLayout.WEST);
    ipPanel2.add(ipNum2,BorderLayout.CENTER);
    ipPanel2.setBorder(new EmptyBorder(5, 5, 5, 5));

    portPanel2.add(portLabel2,BorderLayout.WEST);
    portPanel2.add(portNum2,BorderLayout.CENTER);
    portPanel2.setBorder(new EmptyBorder(5, 5, 5, 5));

    ipPanel3.add(ipLabel3,BorderLayout.WEST);
    ipPanel3.add(ipNum3,BorderLayout.CENTER);
    ipPanel3.setBorder(new EmptyBorder(5, 5, 5, 5));

    portPanel3.add(portLabel3,BorderLayout.WEST);
    portPanel3.add(portNum3,BorderLayout.CENTER);
    portPanel3.setBorder(new EmptyBorder(5, 5, 5, 5));

    ipPanel4.add(ipLabel4,BorderLayout.WEST);
    ipPanel4.add(ipNum4,BorderLayout.CENTER);
    ipPanel4.setBorder(new EmptyBorder(5, 5, 5, 5));

    portPanel4.add(portLabel4,BorderLayout.WEST);
    portPanel4.add(portNum4,BorderLayout.CENTER);
    portPanel4.setBorder(new EmptyBorder(5, 5, 5, 5));

    ipPanel5.add(ipLabel5,BorderLayout.WEST);
    ipPanel5.add(ipNum5,BorderLayout.CENTER);
    ipPanel5.setBorder(new EmptyBorder(5, 5, 5, 5));

    portPanel5.add(portLabel5,BorderLayout.WEST);
    portPanel5.add(portNum5,BorderLayout.CENTER);
    portPanel5.setBorder(new EmptyBorder(5, 5, 5, 5));

    //northPanel.add(ipPanel, BorderLayout.NORTH);
    //northPanel.add(portPanel, BorderLayout.CENTER);
    inputPanel1.setBorder(new EmptyBorder(5, 5, 5, 5));
    inputPanel1.add(ipPanel1,BorderLayout.WEST);
    inputPanel1.add(portPanel1,BorderLayout.CENTER);


    inputPanel2.setBorder(new EmptyBorder(5, 5, 5, 5));
    inputPanel2.add(ipPanel2,BorderLayout.WEST);
    inputPanel2.add(portPanel2,BorderLayout.CENTER);

    inputPanel3.setBorder(new EmptyBorder(5, 5, 5, 5));
    inputPanel3.add(ipPanel3,BorderLayout.WEST);
    inputPanel3.add(portPanel3,BorderLayout.CENTER);

    inputPanel4.setBorder(new EmptyBorder(5, 5, 5, 5));
    inputPanel4.add(ipPanel4,BorderLayout.WEST);
    inputPanel4.add(portPanel4,BorderLayout.CENTER);

    inputPanel5.setBorder(new EmptyBorder(5, 5, 5, 5));
    inputPanel5.add(ipPanel5,BorderLayout.WEST);
    inputPanel5.add(portPanel5,BorderLayout.CENTER);


    textArea.setEditable(false); // set textArea non-editable
    JScrollPane scroll = new JScrollPane(textArea);
    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    topPanel.add(inputPanel1);
    topPanel.add(inputPanel2);
    topPanel.add(inputPanel3);
    topPanel.add(inputPanel4);
    topPanel.add(inputPanel5);
    topPanel.add(buttonConnect);
    add(topPanel, BorderLayout.NORTH);
    add(scroll, BorderLayout.CENTER);

    //add(buttonConnect, BorderLayout.SOUTH);

    buttonConnect.addActionListener(this);
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        //shutdown();
        System.exit(0);
      }
    });
    setSize(500,500);
    setVisible(true);

  }

  private void close() {
    System.out.println("clossing ....... +++++++");
    for (int i=0;i<5;i++) {
      subscriber[i].stop();
    }
  }

  private void shutdown() {
    for (int i=0;i<5;i++) {
      subscriber[i].stop();
    }
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
      buttonConnect.setEnabled(true);
    }
  }

  public static void main(String[] args) {
    ClientDemo tester = new ClientDemo();

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int [] ports = new int[5];
    String [] ips = new String[5];
    try {
      for(int i=0;i<5;i++) {
        ports[i] = Integer.parseInt(portNum1.getText());
        ips[i++] = ipNum1.getText();
        ports[i] = Integer.parseInt(portNum2.getText());
        ips[i++] = ipNum2.getText();
        ports[i] = Integer.parseInt(portNum3.getText());
        ips[i++] = ipNum3.getText();
        ports[i] = Integer.parseInt(portNum4.getText());
        ips[i++] = ipNum4.getText();
        ports[i] = Integer.parseInt(portNum5.getText());
        ips[i++] = ipNum5.getText();

      }
    } catch (NumberFormatException nfe) {
      System.out.println("Exception: " + nfe);
    }
	  /*for (int i=0;i<5;i++) {
		  System.out.println("ipaddress from textboxes = " + ips[i]);
		  System.out.println("ports from textboxes = " + ports[i]);
	  }*/
    for(int i=0;i<5;i++) {
      subscriber[i] = new Subscriber(ips[i], ports[i]);
      buttonConnect.setEnabled(false);
      service.submit(subscriber[i]);
      subscriber[i].addObserver(this);
    }
  }
}