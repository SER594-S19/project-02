package Client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class ClientDemo extends JFrame implements Observer, ActionListener {

  private final Subscriber  [] subscriber = new Subscriber[5];
  private final ExecutorService service;
  private JTextArea textArea = new JTextArea();
  private JButton buttonConnect = new JButton("CONNECT");
  
  JFrame frame;
  
  public ClientDemo() {

    service = Executors.newCachedThreadPool();
    
    int port[] = {1594,1595,1596,1597,1598};
    
    // TO TEST, RUN TWO SERVERS IN PORTS 1594 and 1595
    
    for(int i=0;i<5;i++)
    {
    
    subscriber[i] = new Subscriber("localhost", port[i]);
    
    }
    
    GridLayout gl = new GridLayout(5,3);
    int i=1;
     while(i<6) {    
     // create empty JTextField
     buttonConnect= new JButton("CONNECT");
     
     JLabel label = new JLabel();		
	 label.setText("ENTER PORT NUMBER:");
	 
	// create JTextField with default text
     JTextField field2 = new JTextField();
	 
     JLabel label1 = new JLabel();		
	 label1.setText("ENTER IP ADDRESS:");
	 
     JTextField field1 = new JTextField();
     //field1.setText("IP Address");

     

     add(label);
     add(field2);
     add(label1);
     add(field1);
     
     field1.setEnabled(false); 
     add(buttonConnect);
     buttonConnect.addActionListener(this);
     i++;
     }    
     
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        shutdown();
        System.exit(0);
      }
    });
    setSize(800,200);
    setVisible(true);
    
    this.getContentPane().setLayout(new GridLayout(5,3));
    frame.pack();
	 
	  frame.setVisible(true);
	 
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
  }

  private static void createAndShowGUI() {
	  
  }
    

  private void close() {
    System.out.println("clossing ....... +++++++");
    for(int i=0;i<5;i++)
    {
    subscriber[i].stop();
    }
  }
  
    private void shutdown() {
    for(int i=0;i<5;i++)
    {
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
   // ClientDemo tester = new ClientDemo();
    new ClientDemo();
    
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
		  
		  public void run() {
		   
		      createAndShowGUI(); 
		  }
	   
		    });
   
     
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    buttonConnect.setEnabled(false);
    for(int i=0;i<5;i++)
    {
    service.submit(subscriber[i]);
    subscriber[i].addObserver(this); 

    }
  }
}
