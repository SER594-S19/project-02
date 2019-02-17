package Client;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class ClientDemo extends JFrame implements Observer, ActionListener {

  private final Subscriber  [] subscriber;
  private final ExecutorService service;
  private JButton buttonConnect;
  private JButton setGraph;
  private JFreeChart chart;
  private XYPlot plot;
  private XYLineAndShapeRenderer renderer;
  private ArrayList<JTextField> textFieldsList;
  public static double graphData;
  public static String graphLabel;

  
  JFrame frame;
  
  public ClientDemo() {

    service = Executors.newCachedThreadPool();
    
    graphData = 0.0;
    graphLabel = "Agreement";
    renderer = new XYLineAndShapeRenderer();
    textFieldsList = new ArrayList<JTextField>();
    subscriber = new Subscriber[5];
    
    JPanel connectionPanel = new JPanel(new GridLayout(5, 0));
    connectionPanel.setPreferredSize(new Dimension(100,100));
    JPanel buttonPanel = new JPanel(new GridLayout(0,1));  
    
    JPanel graphPanel = new JPanel(new BorderLayout());
    JPanel chartPanel = createGraphPanel();
    graphPanel.add(chartPanel,BorderLayout.CENTER);
    graphPanel.setVisible(true);
	graphPanel.setPreferredSize(new Dimension(400,400));
	    
    JPanel dropDownPanel = new JPanel(new FlowLayout());
	JComboBox serversList = new JComboBox();
	JLabel dropDownLabel = new JLabel("Show in graph:");
	//setGraph = new JButton("Display");
	serversList.setPreferredSize(new Dimension(100,30));
	dropDownPanel.setPreferredSize(new Dimension(70,70));
	dropDownPanel.add(dropDownLabel);
	dropDownPanel.add(serversList);
	//dropDownPanel.add(setGraph);
	graphPanel.add(dropDownPanel, BorderLayout.NORTH);
	
	serversList.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			String selected = (String) ((JComboBox) event.getSource()).getSelectedItem();
			for(int i=0;i<5;i++) {
				if(null != subscriber[i])
					subscriber[i].setServerPortSelected(selected);
			}
		}
	});
	
    int i=1;
     while(i<6) {    
	     // create empty JTextField	  
         buttonConnect= new JButton("CONNECT");
	     JLabel label = new JLabel();		
		 label.setText("ENTER PORT NUMBER "+i);
		 
		// create JTextField with default text
	     JTextField field2 = new JTextField();
	     textFieldsList.add(field2);
	     JLabel label1 = new JLabel();		
		 label1.setText("ENTER IP ADDRESS "+i);
		 
	     JTextField field1 = new JTextField();
	     //field1.setText("IP Address");
	     field1.setEnabled(false); 
	     connectionPanel.add(label);
	     connectionPanel.add(field2);
	     connectionPanel.add(label1);
	     connectionPanel.add(field1);
	     connectionPanel.add(buttonConnect);
	     
	     serversList.addItem("Server Port "+i);
	     
	     buttonConnect.setVisible(false);
	     i++;
     }  
     buttonConnect= new JButton("CONNECT");
     add(graphPanel, BorderLayout.NORTH);
     add(connectionPanel, BorderLayout.CENTER);
     buttonPanel.add(buttonConnect);
     //buttonPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
     add(buttonPanel,BorderLayout.SOUTH);
     buttonConnect.addActionListener(this);
     
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        shutdown();
        System.exit(0);
      }
    });
    
    //this.getContentPane().setLayout(new GridLayout(5,1));
    this.pack(); 
    this.setTitle("Multimodal Client Connector");
    this.setSize(900, 700);
	this.setVisible(true);
    
  }
  
  private ChartPanel createGraphPanel() {
      XYSeries series = new XYSeries("Data");
     XYSeriesCollection dataset = new XYSeriesCollection(series);
     new Timer(1000, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
             series.add(series.getItemCount(), graphData);
             chart.setTitle(graphLabel);
         }
     }).start();
     chart = ChartFactory.createXYLineChart("Emotion", "Time",
         "Range", dataset, PlotOrientation.VERTICAL, false, false, false);
     plot = chart.getXYPlot();
     renderer.setSeriesPaint(0, Color.GREEN);
     renderer.setSeriesStroke(0, new BasicStroke(2.0f));
     plot.setBackgroundPaint(Color.BLACK);
     plot.setDomainGridlinesVisible(false);
     plot.setRenderer(renderer);
     
     return new ChartPanel(chart) {
		private static final long serialVersionUID = 4960544148862965383L;
		@Override
         public Dimension getPreferredSize() {
             return new Dimension(480, 240);
         }
     };
 }
    

  private void close() {
    System.out.println("clossing ....... +++++++");
    for(int i=0;i<5;i++)
    {
    	if(null != subscriber[i])
    		subscriber[i].stop();
    }
  }
  
    private void shutdown() {
    for(int i=0;i<5;i++)
    {
    	if(null != subscriber[i])
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
    if (data.compareTo("FIN") != 0) {
    	if(!data.isEmpty())
    		graphData = Double.parseDouble(data.split(",")[1].split("=")[1].replace("}", ""));
    	else
    		graphData = 0.0;
    	 System.out.println("Graph Data:"+graphData); 
         System.out.println(data + "\n" );
         //textArea.append();
    }else {
      close();
      buttonConnect.setEnabled(true);
    }    
  }

  public static void main(String[] args) {
    new ClientDemo();  
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int i = 0;
    for(JTextField field : textFieldsList)
    {
    	if(!field.getText().isEmpty()) {
    		if(null != subscriber[i]) 
    			subscriber[i].stop();
    	    subscriber[i] = new Subscriber("localhost", Integer.parseInt(field.getText()));
    	    service.submit(subscriber[i]);
    	    subscriber[i].setServerPortActive("Server Port "+(i+1));
    	    subscriber[i].setServerPortSelected("Server Port 1");
    	    subscriber[i].addObserver(this);
    	}
    	
    	i++;
    }
  }
}
