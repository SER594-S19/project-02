package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;

public class ClientDemo extends JFrame implements Observer, ActionListener {
  private final Subscriber  [] subscriber = new Subscriber[5];
  private final ExecutorService service;
  private JTextArea textArea = new JTextArea("This is Text Area");
  private JButton buttonPort1 = new JButton("Connect for BCI");
  private JButton buttonPort2 = new JButton("Connect for Face Recognition");
  private JButton buttonPort3 = new JButton("Connect for Eye Tracking");
  private JButton buttonPort4 = new JButton("Connect for Heart Rate");
  private JButton buttonPort5 = new JButton("Connect for Galvanic Skin Conductivity");
  
  public ClientDemo() {

    service = Executors.newCachedThreadPool();
    subscriber[0] = new Subscriber("localhost", 1596);
    subscriber[1] = new Subscriber("localhost", 1595);
    subscriber[2] = new Subscriber("localhost", 1594);
    subscriber[3] = new Subscriber("localhost", 1597);
    subscriber[4] = new Subscriber("localhost", 1598);
    
    JPanel panelBCI=new JPanel();  
    panelBCI.setPreferredSize(new Dimension(300,180));
    JPanel panelFR=new JPanel(); 
    panelFR.setPreferredSize(new Dimension(300,180));
    JPanel panelET=new JPanel(); 
    panelET.setPreferredSize(new Dimension(300,180));
    JPanel panelHR=new JPanel(); 
    panelHR.setPreferredSize(new Dimension(300,180));
    JPanel panelSC=new JPanel();    
    panelSC.setPreferredSize(new Dimension(300,180));
    buttonPort1.setBackground(Color.PINK);
    buttonPort1.setFont(new Font("Courier",Font.BOLD, 20));
    buttonPort2.setBackground(Color.PINK);
    buttonPort2.setFont(new Font("Courier",Font.BOLD, 20));
    buttonPort3.setBackground(Color.PINK);
    buttonPort3.setFont(new Font("Courier",Font.BOLD, 20));
    buttonPort4.setBackground(Color.PINK);
    buttonPort4.setFont(new Font("Courier",Font.BOLD, 20));
    buttonPort5.setBackground(Color.PINK);
    buttonPort5.setFont(new Font("Courier",Font.BOLD, 20));
    panelBCI.add(buttonPort1);
    panelFR.add(buttonPort2);
    panelET.add(buttonPort3);
    panelHR.add(buttonPort4);
    panelSC.add(buttonPort5);
   
    JTabbedPane tp = new JTabbedPane();  
    tp.setBounds(50,50,800,800);  
    tp.setBackground(Color.WHITE);
    tp.setFont(new Font("Courier",Font.BOLD,17));
    tp.add("BCI",panelBCI);  
    tp.add("FaceRecognition",panelFR);   
    tp.add("EyeTracking",panelET);   
    tp.add("HeartRate",panelHR); 
    tp.add("SkinConductance",panelSC); 
    
    getContentPane().setLayout(new BorderLayout());  
    getContentPane().add(tp, BorderLayout.NORTH);
    getContentPane().add(textArea, BorderLayout.CENTER);
       
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
    setSize(670,500);
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
      buttonPort3.setEnabled(true);
    }    
  }

  public static void main(String[] args) {
    ClientDemo tester = new ClientDemo();
     
  }

  @Override
  public void actionPerformed(ActionEvent e) {
	  if (e.getSource() == buttonPort1) {
		  JOptionPane.showMessageDialog(null,"Port1 BCI Connecting.");
		    service.submit(subscriber[0]);
		    subscriber[0].addObserver(this);
	  }else if (e.getSource() == buttonPort2) {
		  JOptionPane.showMessageDialog(null,"Port2 FaceRecognition Connecting.");
		  	service.submit(subscriber[1]);
		    subscriber[1].addObserver(this);
	  }else if (e.getSource() == buttonPort3) {
		  JOptionPane.showMessageDialog(null,"Port3 EyeTracking Connecting.");
		  	service.submit(subscriber[2]);
		    subscriber[2].addObserver(this);
	  }else if (e.getSource() == buttonPort4) {
		  JOptionPane.showMessageDialog(null,"Port4 HeartRate Connecting.");
		  	service.submit(subscriber[3]);
		    subscriber[3].addObserver(this);
	  }else if (e.getSource() == buttonPort5) {
		  JOptionPane.showMessageDialog(null,"Port5 SkinConductivity Connecting.");
		  	service.submit(subscriber[4]);
		    subscriber[4].addObserver(this);
	  }
  }
}
