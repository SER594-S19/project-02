package Client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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
import java.awt.FlowLayout;
import javax.swing.JTextField;
public class ClientDemo extends JFrame implements Observer, ActionListener {

  private final Subscriber subscriber;
  private final ExecutorService service;
  private JTextArea textArea = new JTextArea();
  private JButton buttonConnect; 
  private final int PORT = 5914;
  JFrame frame;
  
  
public ClientDemo() {

    service = Executors.newCachedThreadPool();
    subscriber = new Subscriber("localhost", PORT);
    GridLayout gl = new GridLayout(5,3);
   int i=1;
    while(i<6) {    
    // create empty JTextField
    	buttonConnect= new JButton("connect");
    JTextField field1 = new JTextField();
    field1.setText("Ip address");

    // create JTextField with default text
    JTextField field2 = new JTextField("Port number");


    add(field1);
    add(field2);
//    add(field3);
//    add(field4);
    field1.setEnabled(false);
   // setLayout(new GridLayout(5,3));
    //add(textArea, BorderLayout.CENTER);  
   //add(buttonConnect, BorderLayout.EAST);  
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
    setSize(400,400);
    setVisible(true);
    this.getContentPane().setLayout(new GridLayout(5,3));
    //frame.pack();
	 
	  frame.setVisible(true);
	 
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }
  private static void createAndShowGUI() {
	  
	  //Create and set up the window.
	 
//	  JFrame frame = new ClientDemo();
//	 
//	  //Display the window.
//	 
//	  frame.pack();
//	 
//	  frame.setVisible(true);
//	 
//	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	    }
  private void close() {
    System.out.println("clossing ....... +++++++");
    subscriber.stop();
  }
  
    private void shutdown() {
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
    if (data.compareTo("FIN") != 0)
      textArea.append(data + "\n" );
    else {
      close();
      buttonConnect.setEnabled(true);
    }    
  }

  public static void main(String[] args) {
  // ClientDemo tester = 
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
    service.submit(subscriber);
    subscriber.addObserver(this); 
    
  }
}
