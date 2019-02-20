package FacialGesturesClient;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainClient extends JFrame{

	private HeartClient heart;
	private SkinClient skin;
	private EyeClient eye;
	private FaceClient face;
	private BCIClient bci;
	private static MainClient instance = null;
	private static MainClient getInstance() {
        if (instance == null)
            instance = new MainClient();

        return instance;
    }
	private static JTabbedPane client = new JTabbedPane();
	private void shutdown() {
		heart.getSubscriber().stop();
		heart.getService().shutdown();
		skin.getSubscriber().stop();
		skin.getService().shutdown();
		eye.getSubscriber().stop();
		eye.getService().shutdown();
		face.getSubscriber().stop();
		face.getService().shutdown();
		bci.getSubscriber().stop();
		bci.getService().shutdown();
		
            try {
                if (!heart.getService().awaitTermination(10, TimeUnit.SECONDS)) {
                	heart.getService().shutdownNow();
                	
                }
                if (!skin.getService().awaitTermination(10, TimeUnit.SECONDS)) {
                	skin.getService().shutdownNow();
                	
                }
                if (!eye.getService().awaitTermination(10, TimeUnit.SECONDS)) {
                	eye.getService().shutdownNow();
                	
                }
                if (!face.getService().awaitTermination(10, TimeUnit.SECONDS)) {
                	face.getService().shutdownNow();
                	
                }
                if (!bci.getService().awaitTermination(10, TimeUnit.SECONDS)) {
                	bci.getService().shutdownNow();
                	
                }
            } catch (InterruptedException ex) {
                System.out.println("Exception: " + ex);
            }
        
    }
	public static void main(String[] args) {
		MainClient primaryClient = MainClient.getInstance();
		 primaryClient.heart = new HeartClient(new Subscriber("",-1));
		 primaryClient.skin = new SkinClient(new Subscriber("",-1));
		 primaryClient.eye = new EyeClient(new Subscriber("",-1));
		 primaryClient.face = new FaceClient(new Subscriber("",-1));
		 primaryClient.bci = new BCIClient(new Subscriber("",-1));
		 primaryClient.setSize(600,600);
		 primaryClient.setVisible(true);
		client.setBounds(0, 0, 600, 600);
		client.addTab("Heart", primaryClient.heart.processPanelHeart("Heart"));
		client.addTab("Skin", primaryClient.skin.processPanelSkin("Skin"));
		client.addTab("BCI", primaryClient.bci.processPanelBCI("BCI"));
		client.addTab("Eye", primaryClient.eye.processPanelEye("Eye"));
		client.addTab("Face", primaryClient.face.processPanelFace("Face"));

		primaryClient.setContentPane(client);
		primaryClient.addWindowListener(new java.awt.event.WindowAdapter() {
		 @Override
         public void windowClosing(java.awt.event.WindowEvent e) {
			 MainClient.getInstance().shutdown();
             System.exit(0);
         }
     });

	}

}