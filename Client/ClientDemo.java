package Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.text.StyledDocument;

public class ClientDemo extends JFrame implements Observer, ActionListener {

  private static final Logger LOGGER = Logger.getLogger(ClientDemo.class.getName());
  private Subscriber subscriber;
  //private final Subscriber  [] subscriber = new Subscriber[2];
  private final ExecutorService service;
  private JTextPane textArea = new JTextPane();
  private JButton buttonConnect = new JButton("connect");
  StyledDocument doc = textArea.getStyledDocument();
  JScrollPane scrollPane = new JScrollPane(textArea);
  private Observer obs = this;
  
  private JPanel processPanel(String lableName) {

    JPanel label = new JPanel();
    label.setLayout(new GridLayout(1,1));
    label.add(new JLabel(lableName),BorderLayout.WEST);
    
    JPanel ip = new JPanel();
    ip.setLayout(new GridLayout(1,2));
    JTextField ipInput = new JTextField();
    ip.add(new JLabel("IP" ));
    ip.add(ipInput);

    JPanel port = new JPanel();
    port.setLayout(new GridLayout(1,2));
    JTextField portInput = new JTextField();
    port.add(new JLabel("Port" ));
    port.add(portInput);

    JPanel connectCondition = new JPanel();
    connectCondition.setLayout(new GridLayout(1,3));
    JLabel condition = new JLabel("Connecting...");
    JButton connect = new JButton("Connect");
    JButton disConnect = new JButton("DisConnect");
    connect.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          connect.setEnabled(false);
          subscriber = new Subscriber(ipInput.getText(), Integer.parseInt(portInput.getText()));
            service.submit(subscriber);
            subscriber.addObserver(obs);
      }
});

    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        shutdown();
        System.exit(0);
      }
    });


    disConnect.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          connect.setEnabled(false);
          shutdown();
          close();
      }
});
    
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        shutdown();
        System.exit(0);
      }
    });

    connectCondition.add(condition);
    connectCondition.add(connect);
    connectCondition.add(disConnect);


    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(4,1));
    panel.add(label,BorderLayout.NORTH);
    panel.add(ip, BorderLayout.AFTER_LAST_LINE);
    panel.add(port, BorderLayout.AFTER_LAST_LINE);
    panel.add(connectCondition, BorderLayout.AFTER_LAST_LINE);
    panel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

    return panel;
  }

  private JPanel ClientPanel(){
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(5,1));
    panel.add(processPanel(" BCI "),BorderLayout.NORTH);
    panel.add(processPanel(" Face "),BorderLayout.AFTER_LAST_LINE);
    panel.add(processPanel(" Heart "),BorderLayout.AFTER_LAST_LINE);
    panel.add(processPanel(" Skin "),BorderLayout.AFTER_LAST_LINE);
    panel.add(processPanel(" Eye "),BorderLayout.AFTER_LAST_LINE);
    return panel;
  }
  public ClientDemo() {

    service = Executors.newCachedThreadPool();

    // TO TEST, RUN TWO SERVERS IN PORTS 1594 and 1595

    //subscriber[0] = new Subscriber("localhost", 1594);
    //subscriber[1] = new Subscriber("localhost", 1595);

    //setLayout(new BorderLayout());
    setLayout(new GridLayout(1,2));
    add(ClientPanel(), BorderLayout.NORTH);
   // add(textArea, BorderLayout.CENTER);
    add(scrollPane, BorderLayout.CENTER);
    //add(buttonConnect, BorderLayout.SOUTH);
    /*buttonConnect.addActionListener(this);
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        shutdown();
        System.exit(0);
      }
    });*/
    setSize(800,600);
    setVisible(true);

  }

  private void close() {
    System.out.println("clossing ....... +++++++");
    //subscriber[0].stop();
    //subscriber[1].stop();
    subscriber.stop();
  }

  private void shutdown() {
    //subscriber[0].stop();
    //subscriber[1].stop();
    subscriber.stop();
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
    if (data.compareTo("FIN") != 0) {
    	try {
    	    doc.insertString(doc.getLength(), data + "\n", null);
    	} catch(Exception e) { 
    		LOGGER.log(Level.SEVERE, "Exception while writing in client", e);
    	}
    } else {
      close();
      buttonConnect.setEnabled(true);
    }
  }

  public static void main(String[] args) {
    ClientDemo tester = new ClientDemo();

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    buttonConnect.setEnabled(false);
    //service.submit(subscriber[0]);
    //subscriber[0].addObserver(this);

    //service.submit(subscriber[1]);
    //subscriber[1].addObserver(this);
  }
}
