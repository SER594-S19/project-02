package Core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.lang.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Gui extends JPanel implements ActionListener, ChangeListener {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private static Model model;
  static String[] emotions = {"Agreement", "Concentrating", "Disagreement", "Interested", "Thinking", "Unsure"};
  static JLabel[] labels = new JLabel[emotions.length];
  private final int PORT = 1594;
  protected JLabel labelPublishPort;
  private final static JButton buttonConnect = new JButton("Connect");
  static JFrame frame = new JFrame("Face Simulator");
  static AlphaPanel[] ip = new AlphaPanel[6];
  Dimension screen;
  static Box outerBox;
  static JPanel portNumber = new JPanel();
  static JSlider[] slider = new JSlider[emotions.length];
  static Box left = Box.createVerticalBox();
  @SuppressWarnings("deprecation")
private void createPanelSouth() {
	  

	  

	    portNumber.setBackground(new Color(192,192,192));
	    JLabel temp = new JLabel("  Publishing at port: ");
	    System.out.println(frame.getHeight());
	    System.out.println(frame.getWidth());
	    temp.setFont(new Font("Serif", Font.PLAIN, 30));
	    portNumber.add(temp);
	    labelPublishPort = new JLabel("" + PORT);
	    labelPublishPort.setFont(new Font("Serif", Font.PLAIN, 30));
	    portNumber.add(labelPublishPort);
	    portNumber.setPreferredSize(new Dimension(frame.getWidth()/3, frame.getHeight()/11));
	    portNumber.setMaximumSize(portNumber.getPreferredSize()); 
	    portNumber.setMinimumSize(portNumber.getPreferredSize());
	    Container content = frame.getContentPane();
	    content.setLayout(new GridBagLayout());
	    GridBagConstraints constraints = new GridBagConstraints();



	    for(int i = 0;i<6;i++) {
	    	 ImageIcon pic = createImageIcon(Integer.toString(i+1)+".png");
	    	ip[i] = new AlphaPanel(pic, 0.75);
	    }
	    
	    for(int i = 0; i < emotions.length; i++) {
	    	labels[i] = new JLabel(emotions[i]);
		    labels[i].setFont(new Font("Serif", Font.BOLD, 15));
		    labels[i].setHorizontalAlignment(JLabel.CENTER);
	    }
	    
		
		for(int i = 0; i < 6; i++) {
			slider[i] = new JSlider(0, 10, 5);
			slider[i].setMajorTickSpacing(1);
			slider[i].setPaintTicks(true);
			//slider[i].setSize(frame.getWidth()/2, frame.getHeight()/2);
			slider[i].setPaintLabels(true);
			slider[i].setForeground(Color.BLUE);
			slider[i].setBackground(new Color(204,204,204));
			slider[i].addChangeListener(this);
			//slider[i].setPreferredSize(new Dimension(500, 800));
		}

	    for(int i = 0; i < emotions.length; i++) {
	    	
	    	int t=i;
	    	//constraints.fill = GridBagConstraints.BOTH;
	    	
	    	constraints.gridx = 0;
	    	constraints.gridy = t;
	    	constraints.gridwidth = 2;
	    	constraints.gridheight = 1;
	        //ip[i].setPreferredSize(new Dimension(100, 100));
		    constraints.insets = new Insets(0,30,0,0);
		   
	    	constraints.gridwidth = 2;
	    	constraints.fill = GridBagConstraints.HORIZONTAL;
	    	constraints.anchor = GridBagConstraints.NORTH;
	    	content.add(labels[i],constraints);
	    	
	    	constraints.insets = new Insets(10,30,0,0);
			   
	    	constraints.fill = GridBagConstraints.HORIZONTAL;
	    	constraints.anchor = GridBagConstraints.CENTER;
	    	content.add(slider[i],constraints);
	    	
	    	//constraints.gridy = 2*t+1;
	    	
	    	
	    }

		 //addingSliders(t);
	  	Insets insets = frame.getInsets();
	  	int width = frame.getSize().width-(insets.left+insets.right);
	  	int height = frame.getSize().height-(insets.top+insets.bottom);
	  	
	    //outerBox = Box.createHorizontalBox();
	    //outerBox.add(left);
	    //outerBox.setPreferredSize(new Dimension(600, 4600));
	  	System.out.println(width+" "+height);
	  	//left.setPreferredSize(new Dimension(600,4600));


	      constraints.gridheight = 1;
	      constraints.gridwidth = 1;
	      constraints.gridx = 1;
	      constraints.weightx = 1;
	      constraints.weighty = 1;
	     constraints.insets = new Insets(0,50,0,0);
		    //content.add(ip,constraints);
		    for(int i=0;i<6;i++) {
		    	//constraints.insets = new Insets(-300+(i*150), 100, 0, 0);
		    	constraints.anchor = GridBagConstraints.CENTER;
		    	 constraints.gridx = 2;
		    	 constraints.gridy = i;
		        //ip[i].setPreferredSize(new Dimension(100, 100));
		    	content.add(ip[i],constraints);
		    }
//		    

		     constraints.gridx = 0;
		      constraints.gridy = 6; 
		      constraints.insets = new Insets(0,100,0,50);
			    content.add(buttonConnect, constraints);
			    buttonConnect.setPreferredSize(new Dimension(200, 50));
	      constraints.gridx = 0;
	      constraints.gridy = 7;   
	    	 constraints.fill = GridBagConstraints.HORIZONTAL;
	      content.add(portNumber, constraints);
		    

	     // constraints.insets = new Insets(100, -650,-200,-100);

  }




public Gui() {

    model = new Model(new DataGenerator(), new Publisher(PORT));
//    this.setBackground(Color.WHITE);
//    this.setLayout(new BorderLayout());
//    this.add(createPanelSouth(), BorderLayout.SOUTH);
    screen = getToolkit().getScreenSize();
//    frame.addComponentListener(new ComponentAdapter() {
//        public void componentResized(ComponentEvent e) {
//        	createPanelSouth();
//        }
//    });
    //frame.setSize(new Dimension(screen.width/2, 3 * screen.height/4));
    //frame.setLocation((screen.width - getSize().width) / 2, (screen.height - getSize().height) / 2);
    frame.setBounds(0, 0, screen.width, screen.height);
    //System.out.println(frame.getContentPane().getSize());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    createPanelSouth();
    System.out.println("gui done");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("listener trigger");
    if (e.getSource() == buttonConnect) {
      if (buttonConnect.getText().compareTo("run") == 0) {
            System.out.println("start");
        model.start();
        buttonConnect.setText("stop");
      } else if (buttonConnect.getText().compareTo("stop") == 0) {
                    System.out.println("stop");
        model.stop();
        buttonConnect.setText("run");
      }
    }
  }
  
  public void stateChanged(ChangeEvent ev)
  {
	  for(int i=0;i<6;i++) {
		if(ev.getSource() == slider[i]) {
		     int v = slider[i].getValue();
		     System.out.println(v);
             ip[i].setAlpha((float) v / slider[i].getMaximum());
             ip[i].repaint();
             break;
      	}  
	  }


  }
  

  
  public static void func()
  {

  }
  
  protected static ImageIcon createImageIcon(String path) {
      java.net.URL gifURL = Gui.class.getResource(path);
      if (gifURL != null) {
          return new ImageIcon(gifURL);
      } else {
          System.err.println("Cannot find : " + path);
          return null;
      }
  }
  
  public static void main(String[] args) {
    frame.add(new Gui());
	Insets insets = frame.getInsets();
  	int width = frame.getSize().width-(insets.left+insets.right);
  	int height = frame.getSize().height-(insets.top+insets.bottom);
    System.out.println(width+"yo"+height);
    //outerBox.setPreferredSize(new Dimension(width/2, 3*height/4));


    //frame.pack();
    //frame.setSize(800, 1000);
    frame.setVisible(true);

  }
  
}


class AlphaPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage bi;
    private float[] scales = {1f, 1f, 1f, 0.5f};
    private float[] offsets = new float[4];
    private RescaleOp rop;

    public AlphaPanel(ImageIcon icon, double scale) {

        int width = (int) (scale * icon.getIconWidth());
        int height = (int) (scale * icon.getIconHeight());
        this.setPreferredSize(new Dimension(width, height));
        this.bi = new BufferedImage(
            width, height, BufferedImage.TYPE_INT_ARGB);
        this.bi.createGraphics().drawImage(
            icon.getImage(), 0, 0, width/2, height/2, null);
        rop = new RescaleOp(scales, offsets, null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(bi, rop, 0, 0);
    }

    public void setAlpha(float alpha) {
        this.scales[3] = alpha;
        this.rop = new RescaleOp(scales, offsets, null);
    }
}