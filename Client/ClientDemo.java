package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ClientDemo extends JFrame implements Observer, ActionListener {

  public static HashMap<Integer,String> port_panel_mapping = new HashMap<Integer,String>();
  private final Subscriber [] subscriber = new Subscriber[5];
  private final ExecutorService service;
  JTextArea panel1Text = new JTextArea(" ", 20, 30);
  JTextArea panel2Text = new JTextArea(" ", 30, 30);
  JTextArea panel3Text = new JTextArea(" ", 30, 30);
  JTextArea panel4Text = new JTextArea(" ", 30, 30);
  JTextArea panel5Text = new JTextArea(" ", 30, 30);

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
  private boolean connect_status1 = false;
  private boolean connect_status2 = false;
  private boolean connect_status3 = false;
  private boolean connect_status4 = false;
  private boolean connect_status5 = false;


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
  private JTextField textField ;
  private JTextField textField1 ;
  private JTextField textField2 ;
  private JTextField textField3 ;
  private JTextField textField4 ;
  //  private JComponent panel1 = makeTextPanel("Panel #1");
//  tabbedPane.add
  public ClientDemo() {

    service = Executors.newCachedThreadPool();

    setLayout(new BorderLayout());
    //frame.add(textArea, BorderLayout.CENTER);


    panel1.add(panel1Text, BorderLayout.SOUTH);
    panel2.add(panel2Text, BorderLayout.SOUTH);
    panel3.add(panel3Text, BorderLayout.SOUTH);
    panel4.add(panel4Text, BorderLayout.SOUTH);
    panel5.add(panel5Text, BorderLayout.SOUTH);

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
	buttonConnect1.setName("1");
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel("Enter IP address of Server ");
    filler.setHorizontalAlignment(JLabel.LEFT);
    JLabel filler1 = new JLabel("Select Port Number ");
    filler1.setHorizontalAlignment(JLabel.LEFT);
    String[] portNumber = {"1594","1595","1596","1597","1598"};
    SpinnerListModel portModel = new SpinnerListModel(portNumber);
    JSpinner spinner = new JSpinner(portModel);
    panel.add(filler);
    textField = new JTextField();
    panel.add(textField);
    textField.setColumns(8);
    panel.add(filler1);
    panel.add(spinner);
    panel.add(buttonConnect1);
    port_face = spinner.getValue().toString();

    //subscriber[0] = new Subscriber(ipFace, Integer.parseInt(port_face));
    Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_face), "textPanel1");
    
    spinner.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSpinner spinner = (JSpinner) e.getSource();
            ipFace = textField.getText();
            port_face = spinner.getValue().toString();
            subscriber[0] = new Subscriber(ipFace, Integer.parseInt(port_face));
            Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_face), "textPanel1");
            System.out.println("New port is " + port_face);
        }
    });
    
    buttonConnect1.addActionListener(this);

    /*buttonConnect1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ipFace = textField.getText();
        port_face = spinner.getValue().toString();
        subscriber[0] = new Subscriber(ipFace, Integer.parseInt(port_face));
        Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_face), "textPanel1");
        // To add for loop to connect other ports.

        subscriber[0] = new Subscriber(ipFace, Integer.parseInt(port_face));

      }
    });*/
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
    buttonConnect2.setName("2");
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel("Enter IP address of Server ");
    filler.setHorizontalAlignment(JLabel.LEFT);
    JLabel filler1 = new JLabel("Select Port Number ");
    filler1.setHorizontalAlignment(JLabel.LEFT);
    textField1 = new JTextField();
    textField1.setColumns(8);
    String[] portNumber = {"1594","1595","1596","1597","1598"};
    SpinnerListModel portModel = new SpinnerListModel(portNumber);
    JSpinner spinner = new JSpinner(portModel);
    // panel.setLayout(new GridLayout(4, 4));
    panel.add(filler);
    panel.add(textField1);
    panel.add(filler1);
    panel.add(spinner);
    panel.add(buttonConnect2);
    
    port_eyes = spinner.getValue().toString();
//    subscriber[1] = new Subscriber(ipEyes, Integer.parseInt(port_eyes));
    Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_eyes), "textPanel2");
    
    
    spinner.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSpinner spinner = (JSpinner) e.getSource();
            port_eyes = spinner.getValue().toString();
            ipEyes = textField1.getText();
            subscriber[1] = new Subscriber(ipEyes, Integer.parseInt(port_eyes));
            Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_eyes), "textPanel2");
            System.out.println("New port is " + port_eyes);
        }
    });
    
    buttonConnect2.addActionListener(this);

    /*buttonConnect2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ipEyes = textField.getText();
        port_eyes = spinner.getValue().toString();
        subscriber[1] = new Subscriber(ipEyes, Integer.parseInt(port_eyes));
        Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_eyes), "textPanel2");
      }
    });*/
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
	  buttonConnect3.setName("3");
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel("Enter IP address of Server ");
    filler.setHorizontalAlignment(JLabel.LEFT);
    JLabel filler1 = new JLabel("Select Port Number ");
    filler1.setHorizontalAlignment(JLabel.LEFT);
    textField2 = new JTextField();
    textField2.setColumns(8);
    String[] portNumber = {"1594","1595","1596","1597","1598"};
    SpinnerListModel portModel = new SpinnerListModel(portNumber);
    JSpinner spinner = new JSpinner(portModel);

    // panel.setLayout(new GridLayout(4, 4));
    panel.add(filler);
    panel.add(textField2);
    panel.add(filler1);
    panel.add(spinner);
    panel.add(buttonConnect3);

    port_skin = spinner.getValue().toString();
    subscriber[2] = new Subscriber(ipSkin, Integer.parseInt(port_skin));
    Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_skin), "textPanel3");
    
    spinner.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSpinner spinner = (JSpinner) e.getSource();
            ipSkin = textField2.getText();
            port_skin = spinner.getValue().toString();
            subscriber[2] = new Subscriber(ipSkin, Integer.parseInt(port_skin));
            Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_skin), "textPanel3");
            System.out.println("New port is " + port_skin);
        }
    });
    
    
    buttonConnect3.addActionListener(this);
    
    
    /*buttonConnect3.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ipSkin = textField.getText();
        port_skin = spinner.getValue().toString();
        subscriber[2] = new Subscriber(ipSkin, Integer.parseInt(port_skin));
        Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_skin), "textPanel3");
      }
    });*/
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
	  buttonConnect4.setName("4");
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel("Enter IP address of Server ");
    filler.setHorizontalAlignment(JLabel.LEFT);
    JLabel filler1 = new JLabel("Select Port Number ");
    filler1.setHorizontalAlignment(JLabel.LEFT);
    textField3 = new JTextField();
    textField3.setColumns(8);
    String[] portNumber = {"1594","1595","1596","1597","1598"};
    SpinnerListModel portModel = new SpinnerListModel(portNumber);
    JSpinner spinner = new JSpinner(portModel);

    // panel.setLayout(new GridLayout(4, 4));
    panel.add(filler);
    panel.add(textField3);
    panel.add(filler1);
    panel.add(spinner);
    panel.add(buttonConnect4);
    
    ipHeart = textField3.getText();
    port_heart =spinner.getValue().toString();
//    subscriber[3] = new Subscriber(ipHeart, Integer.parseInt(port_heart));
    Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_heart), "textPanel4");
    
    spinner.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSpinner spinner = (JSpinner) e.getSource();
            ipHeart = textField3.getText();
            port_heart =spinner.getValue().toString();
            subscriber[3] = new Subscriber(ipHeart, Integer.parseInt(port_heart));
            Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_heart), "textPanel4");
            System.out.println("New port is " + port_heart);
        }
    });
    
    buttonConnect4.addActionListener(this);
    /*buttonConnect4.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ipHeart = textField.getText();
        port_heart =spinner.getValue().toString();
        subscriber[3] = new Subscriber(ipHeart, Integer.parseInt(port_heart));
        Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_heart), "textPanel4");
      }
    });*/
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
    buttonConnect5.setName("5");
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel("Enter IP address of Server ");
    filler.setHorizontalAlignment(JLabel.LEFT);
    JLabel filler1 = new JLabel("Select Port Number ");
    filler1.setHorizontalAlignment(JLabel.LEFT);
    textField4 = new JTextField();
    textField4.setColumns(8);
    String[] portNumber = {"1594","1595","1596","1597","1598"};
    SpinnerListModel portModel = new SpinnerListModel(portNumber);
    JSpinner spinner = new JSpinner(portModel);




    // panel.setLayout(new GridLayout(4, 4));
    panel.add(filler);
    panel.add(textField4);
    panel.add(filler1);
    panel.add(spinner);
    panel.add(buttonConnect5);
    

    port_bci = spinner.getValue().toString();
//    subscriber[4] = new Subscriber(ipBCI, Integer.parseInt(port_bci));
    Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_bci), "textPanel5");
    
    spinner.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSpinner spinner = (JSpinner) e.getSource();
            ipBCI = textField4.getText();
            port_bci = spinner.getValue().toString();
            subscriber[4] = new Subscriber(ipBCI, Integer.parseInt(port_bci));
            Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_bci), "textPanel5");
            System.out.println("New port is " + port_bci);
        }
    });
    
    buttonConnect5.addActionListener(this);
    
    
    /*buttonConnect5.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ipBCI = textField.getText();
        port_bci = spinner.getValue().toString();
        subscriber[4] = new Subscriber(ipBCI, Integer.parseInt(port_bci));
        Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_bci), "textPanel5");
      }
    });*/
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
    int portNo = ((Subscriber) o).getPort();
    String data = ((Subscriber) o).getObject().toString();
    if (data.compareTo("FIN") != 0) {
    	
    	checkPaneltoAppend(portNo, data);

      /*if(portNo == 1594 && connect_status1){
        checkPaneltoAppend(portNo, data);
      }
      else if(portNo == 1595 && connect_status2){
        checkPaneltoAppend(portNo, data);
      }
      else if(portNo == 1596 && connect_status3){
        checkPaneltoAppend(portNo, data);
      }
      else if(portNo == 1597 && connect_status4){
        checkPaneltoAppend(portNo, data);
      }
      else if(portNo == 1598 && connect_status5){
        checkPaneltoAppend(portNo, data);
      }*/

    }
    else {
      close();
      buttonConnect1.setEnabled(true);
      buttonConnect2.setEnabled(true);
      buttonConnect3.setEnabled(true);
      buttonConnect4.setEnabled(true);
      buttonConnect5.setEnabled(true);

    }
  }

  private void checkPaneltoAppend(int portNo, String data) {
    switch (Client.ClientDemo.port_panel_mapping.get(portNo)){
      case "textPanel1":
        panel1Text.append(data + "\n" );
        break;
      case "textPanel2":
        panel2Text.append(data + "\n" );
        break;
      case "textPanel3":
        panel3Text.append(data + "\n" );
        break;
      case "textPanel4":
        panel4Text.append(data + "\n" );
        break;
      case "textPanel5":
        panel5Text.append(data + "\n" );
        break;
    }
  }

  public static void main(String[] args) {
    Client.ClientDemo tester = new Client.ClientDemo();

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String portNumber;
    if (e.getSource() == buttonConnect1) {
      ipFace = textField.getText();
      System.out.println(ipFace);
      System.out.println(port_face);
      if(ipFace.compareTo("")==0){
        JOptionPane.showMessageDialog(null,"Please enter a valid IP address");
        return;
      }
    	connect_status1 = !connect_status1;
      if(connect_status1)
      {

          subscriber[0] = new Subscriber(ipFace, Integer.parseInt(port_face));
    	  service.submit(subscriber[0]);
          subscriber[0].addObserver(this);
          buttonConnect1.setText("DisConnect");
    	  
      }
      else if(buttonConnect1.getText().compareTo("DisConnect") == 0){
    	  
    	  buttonConnect1.setText("Connect");
      }
      
      
    }else if (e.getSource() == buttonConnect2) {
        ipEyes = textField1.getText();
        System.out.println(ipEyes);
        System.out.println(port_eyes);
      if(ipEyes.compareTo("")==0){
        JOptionPane.showMessageDialog(null,"Please enter a valid IP address");
        return;
      }
    	connect_status2 = !connect_status2;
      
      if(connect_status2)
      {
          subscriber[1] = new Subscriber(ipEyes, Integer.parseInt(port_eyes));
          service.submit(subscriber[1]);
          subscriber[1].addObserver(this);
          buttonConnect2.setText("DisConnect");
    	  
      }
      else if(buttonConnect2.getText().compareTo("DisConnect") == 0)
      {
    	  
    	  buttonConnect2.setText("Connect");
      }
      
      
    }else if (e.getSource() == buttonConnect3) {
        ipSkin = textField2.getText();
    	connect_status3 = !connect_status3;
    	
    	if(connect_status3)
    	{
          subscriber[2] = new Subscriber(ipSkin, Integer.parseInt(port_skin));
          service.submit(subscriber[2]);
          subscriber[2].addObserver(this);
          buttonConnect3.setText("DisConnect");
    		
    	}
    	   else if(buttonConnect3.getText().compareTo("DisConnect") == 0)
    	      {
    	    	  
    	    	  buttonConnect3.setText("Connect");
    	      }

    }else if (e.getSource() == buttonConnect4) {
        ipHeart = textField3.getText();
    	connect_status4 = !connect_status4;
    	
    	if(connect_status4) {
              subscriber[3] = new Subscriber(ipHeart, Integer.parseInt(port_heart));
    	      service.submit(subscriber[3]);
    	      subscriber[3].addObserver(this);
              buttonConnect4.setText("DisConnect");
    		
    		
    	}
    	 else if(buttonConnect4.getText().compareTo("DisConnect") == 0)
	      {
	    	  
	    	  buttonConnect4.setText("Connect");
	      }
    
    }else if (e.getSource() == buttonConnect5) {
        ipBCI = textField4.getText();
    	connect_status5 = !connect_status5;
    	
    	if(connect_status5)
    	{
              subscriber[4] = new Subscriber(ipBCI, Integer.parseInt(port_bci));
    	      service.submit(subscriber[4]);
    	      subscriber[4].addObserver(this);
    	      buttonConnect5.setText("DisConnect");
    	}
   	 else if(buttonConnect5.getText().compareTo("DisConnect") == 0)
     {
   	  
   	  buttonConnect5.setText("Connect");
     }

    }


  }
  
  
}