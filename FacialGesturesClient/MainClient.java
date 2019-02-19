package FacialGesturesClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainClient extends JFrame implements ActionListener {

	public MainClient() {

		// service = Executors.newCachedThreadPool();

		setSize(600, 600);
		setVisible(true);

	}

	private static JTabbedPane client = new JTabbedPane();

	public static void main(String[] args) {
		MainClient tester = new MainClient();
		HeartClient heart = new HeartClient();
		SkinClient skin = new SkinClient();
		BCIClient bci = new BCIClient();
		EyeClient eye = new EyeClient();
		FaceClient face = new FaceClient();
		client.setBounds(0, 0, 600, 600);
		client.addTab("Heart", heart.processPanelHeart("Heart"));
		client.addTab("Skin", skin.processPanelSkin("Skin"));
		client.addTab("BCI", bci.processPanelBCI("BCI"));
		client.addTab("Eye", eye.processPanelEye("Eye"));
		client.addTab("Face", face.processPanelFace("Face"));

		tester.setContentPane(client);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}