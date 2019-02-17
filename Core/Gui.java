package Core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

public class Gui extends JPanel implements ActionListener {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private static Model model;
  static String[] emotions = {"Agreement", "Concentrating", "Disagreement", "Interested", "Thinking", "Unsure"};
  static JLabel[] labels = new JLabel[emotions.length];
  private final int PORT = 1594;
  protected JLabel labelPublishPort;
  private final JButton buttonConnect = new JButton("run");
  static JSlider[] slider = new JSlider[emotions.length];
  private Component createPanelSouth() {

	  JPanel portNumber = new JPanel();
	    portNumber.setBackground(new Color(51,204,255));
	    JLabel temp = new JLabel("  Publishing at port: ");
	    temp.setFont(new Font("Serif", Font.PLAIN, 30));
	    portNumber.add(temp);
	    labelPublishPort = new JLabel("" + PORT);
	    labelPublishPort.setFont(new Font("Serif", Font.PLAIN, 30));
	    portNumber.add(labelPublishPort);
	    portNumber.setPreferredSize(new Dimension(200, 50));
	    portNumber.setMaximumSize(portNumber.getPreferredSize()); 
	    portNumber.setMinimumSize(portNumber.getPreferredSize());
	    
	    for(int i = 0; i < emotions.length; i++) {
	    	labels[i] = new JLabel(emotions[i]);
		    labels[i].setFont(new Font("Serif", Font.PLAIN, 30));
		    labels[i].setHorizontalAlignment(JLabel.CENTER);
	    }
	    JPanel moodPanel = new JPanel(new GridLayout(0, 1));
	    for(int i = 0; i < emotions.length; i++) {
		    moodPanel.add(labels[i]);
		    moodPanel.add(slider[i]);
	    }

	    moodPanel.setPreferredSize(new Dimension(600, 400));
	    moodPanel.setMaximumSize(moodPanel.getPreferredSize()); 
	    moodPanel.setMinimumSize(moodPanel.getPreferredSize());
	    
	    JPanel moodPanel1 = new JPanel(new GridLayout(6, 1));
	    JLabel moodGif = new JLabel(createImageIcon("agree.jpg"));
	    JLabel moodGif1 = new JLabel(createImageIcon("concent.png"));
	    JLabel moodGif2 = new JLabel(createImageIcon("disagree.png"));
	    JLabel moodGif3 = new JLabel(createImageIcon("inter.png"));
	    JLabel moodGif4 = new JLabel(createImageIcon("think.png"));
	    JLabel moodGif5 = new JLabel(createImageIcon("unsure.png"));
	    
	    moodPanel1.add(moodGif);
	    moodPanel1.add(moodGif1);
	    moodPanel1.add(moodGif2);
	    moodPanel1.add(moodGif3);
	    moodPanel1.add(moodGif4);
	    moodPanel1.add(moodGif5);
	    
	    add(moodPanel, BorderLayout.WEST);
	    add(moodPanel1, BorderLayout.CENTER);
	    setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
	    buttonConnect.setPreferredSize(new Dimension(200, 50));
	    buttonConnect.setMaximumSize(moodPanel.getPreferredSize()); 
	    buttonConnect.setMinimumSize(moodPanel.getPreferredSize());
	    JPanel bottomPanel = new JPanel(new BorderLayout());
	    bottomPanel.add(portNumber, BorderLayout.CENTER);
	    bottomPanel.add(buttonConnect, BorderLayout.LINE_END);
	    buttonConnect.addActionListener(this);
	    buttonConnect.setEnabled(true);
	    return bottomPanel; 
  }

  public Gui() {

    model = new Model(new DataGenerator(), new Publisher(PORT));
    this.setBackground(Color.WHITE);
    this.setLayout(new BorderLayout());
    this.add(createPanelSouth(), BorderLayout.SOUTH);
   // Dimension screen = getToolkit().getScreenSize();
    //this.setSize(screen.width / 2, 3 * screen.height / 4);
    //this.setLocation((screen.width - getSize().width) / 2, (screen.height - getSize().height) / 2);
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
	
	for(int i = 0; i < 6; i++) {
		slider[i] = new JSlider(0, 10, 0);
		slider[i].setMajorTickSpacing(1	);
		slider[i].setPaintTicks(true);
		slider[i].setSize(400,100);
		slider[i].setPaintLabels(true);
		slider[i].setForeground(Color.black);
		slider[i].setBackground(new Color(204,204,204));
	}
    JFrame frame = new JFrame("Simulator");
    frame.setLayout(new GridLayout(1, 1));
    frame.add(new Gui());
    frame.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        model.shutdown();
        System.exit(0);
      }
    });
    frame.pack();
    frame.setSize(800, 1000);
    frame.setVisible(true);
  }
  
}