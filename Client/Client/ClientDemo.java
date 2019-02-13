package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ClientDemo extends JFrame implements Observer, ActionListener {

  private final Subscriber  [] subscriber = new Subscriber[5];
  private final ExecutorService service;
  private JTextArea textArea = new JTextArea();
  private JButton buttonPort1 = new JButton("connect1");
  private JButton buttonPort2 = new JButton("connect2");
  private JButton buttonPort3 = new JButton("connect3");
  private JButton buttonPort4 = new JButton("connect4");
  private JButton buttonPort5 = new JButton("connect5");
  
  
  public ClientDemo() {

    service = Executors.newCachedThreadPool();
    
    // TO TEST, RUN TWO SERVERS IN PORTS 1594 and 1595
    
    subscriber[0] = new Subscriber("localhost", 1594);
    subscriber[1] = new Subscriber("localhost", 1595);
    subscriber[2] = new Subscriber("localhost", 1596);
    subscriber[3] = new Subscriber("localhost", 1597);
    subscriber[4] = new Subscriber("localhost", 1598);
    Box box = Box.createVerticalBox();
    box.add(buttonPort1);
    box.add(buttonPort2);
    box.add(buttonPort3);
    box.add(buttonPort4);
    box.add(buttonPort5);  
    add(box);  
//    JPanel listPane = new JPanel();
//    listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
//    setLayout(new FlowLayout());

    
    buttonPort1.addActionListener(this);
    buttonPort1.setEnabled(true);
    buttonPort2.addActionListener(this);
    buttonPort2.setEnabled(true);
    buttonPort3.addActionListener(this);
    buttonPort3.setEnabled(true);
    buttonPort4.addActionListener(this);
    buttonPort4.setEnabled(true);
    buttonPort5.addActionListener(this);
    buttonPort5.setEnabled(true);
    
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        shutdown();
        System.exit(0);
      }
    });
    
    setSize(500,500);
    setVisible(true);
    
  }


private void close() {
    System.out.println("clossing ....... +++++++");
    subscriber[0].stop();
    subscriber[1].stop();
  }
  
    private void shutdown() {
    subscriber[0].stop();
    subscriber[1].stop();
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
      buttonPort1.setEnabled(true);
    }    
  }

  public static void main(String[] args) {
    ClientDemo tester = new ClientDemo();
     
  }

  @Override
  public void actionPerformed(ActionEvent e) {
	  if (e.getSource() == buttonPort1) {
		  JOptionPane.showMessageDialog(null,"Port1 Connecting.");
		    service.submit(subscriber[0]);
		    subscriber[0].addObserver(this);
	  }else if (e.getSource() == buttonPort2) {
		  JOptionPane.showMessageDialog(null,"Port2 Connecting.");
		  	service.submit(subscriber[1]);
		    subscriber[1].addObserver(this);
	  }else if (e.getSource() == buttonPort3) {
		  JOptionPane.showMessageDialog(null,"Port3 Connecting.");
		  	service.submit(subscriber[2]);
		    subscriber[2].addObserver(this);
	  }else if (e.getSource() == buttonPort4) {
		  JOptionPane.showMessageDialog(null,"Port4 Connecting.");
		  	service.submit(subscriber[3]);
		    subscriber[3].addObserver(this);
	  }else if (e.getSource() == buttonPort5) {
		  JOptionPane.showMessageDialog(null,"Port5 Connecting.");
		  	service.submit(subscriber[4]);
		    subscriber[4].addObserver(this);
	  }
  }
}
