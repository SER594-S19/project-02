package Core;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
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
  static JFrame frame = new JFrame("Simulator");
  static AlphaPanel[] ip = new AlphaPanel[6];
  Dimension screen;
  static Box outerBox;
  static JPanel portNumber = new JPanel();
  static JSlider[] slider = new JSlider[emotions.length];
  static Box left = Box.createVerticalBox();
  @SuppressWarnings("deprecation")
private void createPanelSouth() {
	  

	  

	    portNumber.setBackground(new Color(51,204,255));
	    JLabel temp = new JLabel("  Publishing at port: ");
	    System.out.println(frame.getHeight());
	    System.out.println(frame.getWidth());
	    temp.setFont(new Font("Serif", Font.PLAIN, 30));
	    portNumber.add(temp);
	    labelPublishPort = new JLabel("" + PORT);
	    labelPublishPort.setFont(new Font("Serif", Font.PLAIN, 30));
	    portNumber.add(labelPublishPort);
	    portNumber.setPreferredSize(new Dimension(500, 50));
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
		    labels[i].setFont(new Font("Serif", Font.PLAIN, 30));
		    labels[i].setHorizontalAlignment(JLabel.CENTER);
	    }
	    
		
		for(int i = 0; i < 6; i++) {
			slider[i] = new JSlider(0, 10, 5);
			slider[i].setMajorTickSpacing(1);
			slider[i].setPaintTicks(true);
			slider[i].setSize(800,400);
			slider[i].setPaintLabels(true);
			slider[i].setForeground(Color.BLUE);
			slider[i].setBackground(new Color(204,204,204));
			slider[i].addChangeListener(this);
		}

	    for(int i = 0; i < emotions.length; i++) {
	    	
	    	int t=i;

	    	
	    	constraints.gridx = 0;
	    	constraints.gridy = t;
	    	constraints.gridwidth = 1;
	    	constraints.gridheight = 1;

		    constraints.insets = new Insets(50,30,0,0);
		   
	    	constraints.gridwidth = 1;
	    	constraints.fill = GridBagConstraints.HORIZONTAL;
	    	constraints.anchor = GridBagConstraints.CENTER;
	    	content.add(slider[i],constraints);
	    	constraints.anchor = GridBagConstraints.NORTH;
	    	 constraints.fill = GridBagConstraints.HORIZONTAL;
		     content.add(labels[i],constraints);

	    	
	    	
	    }

	  	Insets insets = frame.getInsets();
	  	int width = frame.getSize().width-(insets.left+insets.right);
	  	int height = frame.getSize().height-(insets.top+insets.bottom);
	  
	      constraints.gridheight = 1;
	      constraints.gridwidth = 1;
	      constraints.gridx = 1;
	      constraints.weightx = 1;
	      constraints.weighty = 1;
	      constraints.insets = new Insets(50,100,0,0);
		    //content.add(ip,constraints);
		    for(int i=0;i<6;i++) {
		    	constraints.anchor = GridBagConstraints.CENTER;
		    	 constraints.gridx = 3;
		    	 constraints.gridy = i;
		    	content.add(ip[i],constraints);
		    }


	      constraints.gridx = 0;
	      constraints.gridy = 6;   
	    	 constraints.fill = GridBagConstraints.HORIZONTAL;
	      content.add(portNumber, constraints);
		    buttonConnect.setPreferredSize(new Dimension(200, 100));

	      constraints.gridx = 3;
	      constraints.gridy = 6; 
	      constraints.insets = new Insets(50,100,0,50);
		    content.add(buttonConnect, constraints);
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
    frame.setBounds((screen.width)/5, (screen.height) /7, screen.width/3, 3 * screen.height/4);
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
            icon.getImage(), 0, 0, width, height, null);
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