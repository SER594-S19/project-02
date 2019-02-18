package FacialGesturesClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ClientDemo extends JFrame implements Observer, ActionListener {

	private static final String face = null;
	private final Subscriber[] subscriber = new Subscriber[5];
	private final ExecutorService service;
	private JButton buttonConnect = new JButton("connect");
	private JTextField heartIp = new JTextField();
	private JTextField heartPort = new JTextField();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	private JPanel processPanelEye(String lableName) {

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				shutdown();
				System.exit(0);
			}
		});

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				shutdown();
				System.exit(0);
			}
		});
	}

	private JPanel processPanelHeart(String lableName) {

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				shutdown();
				System.exit(0);
			}
		});

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				shutdown();
				System.exit(0);
			}
		});
	}

	private JPanel processPanelBCI(String lableName) {
		JTextField ipAdd = new JTextField();
		JTextField portInput = new JTextField();

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				shutdown();
				System.exit(0);
			}
		});

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				shutdown();
				System.exit(0);
			}
		});
	}

	private JPanel processPanelSkin(String lableName) {

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				shutdown();
				System.exit(0);
			}
		});

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				shutdown();
				System.exit(0);
			}
		});
	}

	private JPanel processPanelFace(String lableName) {

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				shutdown();
				System.exit(0);
			}
		});

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				shutdown();
				System.exit(0);
			}
		});
	}

	private JPanel ClientPanel() {
	}

	public ClientDemo() {

		service = Executors.newCachedThreadPool();

		// TO TEST, RUN TWO SERVERS IN PORTS 1594 and 1595

		getContentPane().setLayout(new GridLayout(1, 2));

		JPanel panel_5 = new JPanel();
		getContentPane().add(panel_5);
		panel_5.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(16, 16, 635, 199);
		panel_5.add(tabbedPane);

		JPanel panel_11 = new JPanel();
		tabbedPane.addTab("Face", null, panel_11, null);

		JPanel jPanel_face = new JPanel();
		jPanel_face.setBackground(Color.white);
		jPanel_face.setLayout(new GridLayout(1, 2));
		// JTextField ipAdd = new JTextField();
		jPanel_face.add(new JLabel("IP Address"));
		jPanel_face.add(heartIp);

		JPanel jPort_face = new JPanel();
		jPort_face.setBackground(Color.white);
		jPort_face.setLayout(new GridLayout(1, 2));
		// JTextField portInput = new JTextField();
		jPort_face.add(new JLabel("Port #"));
		jPort_face.add(heartPort);

		JPanel connectCondition_face = new JPanel();
		connectCondition_face.setBackground(Color.white);
		connectCondition_face.setLayout(new GridLayout(1, 3));
		JButton disconnect_1 = new JButton("Disconnect");

		disconnect_1.addActionListener(this);
		SpringLayout sl_panel_11 = new SpringLayout();
		panel_11.setLayout(sl_panel_11);
		connectCondition_face.add(disconnect_1);

		JPanel Face = new JPanel();
		sl_panel_11.putConstraint(SpringLayout.NORTH, Face, 0, SpringLayout.NORTH, panel_11);
		sl_panel_11.putConstraint(SpringLayout.WEST, Face, 0, SpringLayout.WEST, panel_11);
		sl_panel_11.putConstraint(SpringLayout.SOUTH, Face, 0, SpringLayout.SOUTH, panel_11);
		sl_panel_11.putConstraint(SpringLayout.EAST, Face, 230, SpringLayout.WEST, panel_11);
		panel_11.add(Face);
		Face.setBackground(Color.white);
		Face.setLayout(new GridLayout(4, 1));
		Face.add(jPanel_face, BorderLayout.AFTER_LAST_LINE);
		Face.add(jPort_face, BorderLayout.AFTER_LAST_LINE);
		JButton connect_1 = new JButton("Connect");
		Face.add(connect_1);
		connect_1.addActionListener(this);
		Face.add(connectCondition_face, BorderLayout.AFTER_LAST_LINE);
		Face.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		// return Face;

		JScrollPane FaceData = new JScrollPane();
		sl_panel_11.putConstraint(SpringLayout.NORTH, FaceData, 0, SpringLayout.NORTH, panel_11);
		sl_panel_11.putConstraint(SpringLayout.WEST, FaceData, 235, SpringLayout.WEST, panel_11);
		sl_panel_11.putConstraint(SpringLayout.SOUTH, FaceData, 153, SpringLayout.NORTH, panel_11);
		sl_panel_11.putConstraint(SpringLayout.EAST, FaceData, 587, SpringLayout.WEST, panel_11);
		panel_11.add(FaceData);

		JPanel bci = new JPanel();
		tabbedPane.addTab("BCI", null, bci, null);
		SpringLayout sl_bci = new SpringLayout();
		bci.setLayout(sl_bci);

		JPanel panel_12 = new JPanel();
		sl_bci.putConstraint(SpringLayout.NORTH, panel_12, 0, SpringLayout.NORTH, bci);
		sl_bci.putConstraint(SpringLayout.WEST, panel_12, 0, SpringLayout.WEST, bci);
		sl_bci.putConstraint(SpringLayout.SOUTH, panel_12, 0, SpringLayout.SOUTH, bci);
		sl_bci.putConstraint(SpringLayout.EAST, panel_12, 230, SpringLayout.WEST, bci);
		panel_12.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		panel_12.setBackground(Color.WHITE);
		bci.add(panel_12);
		panel_12.setLayout(new GridLayout(4, 1));

		JPanel panel_14 = new JPanel();
		panel_14.setBackground(Color.WHITE);
		panel_12.add(panel_14);
		panel_14.setLayout(new GridLayout(1, 2));

		JLabel label_5 = new JLabel("IP Address");
		panel_14.add(label_5);

		textField = new JTextField();
		panel_14.add(textField);

		JPanel panel_15 = new JPanel();
		panel_15.setBackground(Color.WHITE);
		panel_12.add(panel_15);
		panel_15.setLayout(new GridLayout(1, 2));

		JLabel label_6 = new JLabel("Port #");
		panel_15.add(label_6);

		textField_1 = new JTextField();
		panel_15.add(textField_1);

		JPanel panel_16 = new JPanel();
		panel_16.setBackground(Color.WHITE);
		panel_12.add(panel_16);
		panel_16.setLayout(new GridLayout(1, 3));

		JButton button = new JButton("Connect");
		panel_16.add(button);

		JScrollPane scrollPane_1 = new JScrollPane();
		sl_bci.putConstraint(SpringLayout.NORTH, scrollPane_1, 0, SpringLayout.NORTH, bci);
		sl_bci.putConstraint(SpringLayout.WEST, scrollPane_1, 235, SpringLayout.WEST, bci);
		sl_bci.putConstraint(SpringLayout.SOUTH, scrollPane_1, 0, SpringLayout.SOUTH, panel_12);
		sl_bci.putConstraint(SpringLayout.EAST, scrollPane_1, 587, SpringLayout.WEST, bci);
		
				JButton button_1 = new JButton("Disconnect");
				panel_12.add(button_1);
		bci.add(scrollPane_1);

		JPanel skin = new JPanel();
		tabbedPane.addTab("Skin", null, skin, null);
		SpringLayout sl_skin = new SpringLayout();
		skin.setLayout(sl_skin);

		JPanel panel_2 = new JPanel();
		sl_skin.putConstraint(SpringLayout.NORTH, panel_2, 0, SpringLayout.NORTH, skin);
		sl_skin.putConstraint(SpringLayout.WEST, panel_2, 0, SpringLayout.WEST, skin);
		sl_skin.putConstraint(SpringLayout.SOUTH, panel_2, 0, SpringLayout.SOUTH, skin);
		sl_skin.putConstraint(SpringLayout.EAST, panel_2, 230, SpringLayout.WEST, skin);
		panel_2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		panel_2.setBackground(Color.WHITE);
		skin.add(panel_2);
		panel_2.setLayout(new GridLayout(4, 1));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(1, 2));

		JLabel label_1 = new JLabel("IP Address");
		panel_4.add(label_1);

		textField_2 = new JTextField();
		panel_4.add(textField_2);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_2.add(panel_6);
		panel_6.setLayout(new GridLayout(1, 2));

		JLabel label_2 = new JLabel("Port #");
		panel_6.add(label_2);

		textField_3 = new JTextField();
		panel_6.add(textField_3);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		panel_2.add(panel_7);
		panel_7.setLayout(new GridLayout(1, 3));

		JButton button_2 = new JButton("Connect");
		panel_7.add(button_2);

		JScrollPane scrollPane = new JScrollPane();
		sl_skin.putConstraint(SpringLayout.EAST, scrollPane, 587, SpringLayout.WEST, skin);
		
				JButton button_3 = new JButton("Disconnect");
				panel_2.add(button_3);
		sl_skin.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, skin);
		sl_skin.putConstraint(SpringLayout.WEST, scrollPane, 235, SpringLayout.WEST, skin);
		sl_skin.putConstraint(SpringLayout.SOUTH, scrollPane, 153, SpringLayout.NORTH, skin);
		skin.add(scrollPane);

		JPanel heart = new JPanel();
		tabbedPane.addTab("Heart", null, heart, null);
		SpringLayout sl_heart = new SpringLayout();
		heart.setLayout(sl_heart);

		JPanel panel_9 = new JPanel();
		sl_heart.putConstraint(SpringLayout.NORTH, panel_9, 0, SpringLayout.NORTH, heart);
		sl_heart.putConstraint(SpringLayout.WEST, panel_9, 0, SpringLayout.WEST, heart);
		sl_heart.putConstraint(SpringLayout.SOUTH, panel_9, -5, SpringLayout.SOUTH, heart);
		panel_9.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		panel_9.setBackground(Color.WHITE);
		heart.add(panel_9);
		panel_9.setLayout(new GridLayout(4, 1));

		JPanel panel_18 = new JPanel();
		panel_18.setBackground(Color.WHITE);
		panel_9.add(panel_18);
		panel_18.setLayout(new GridLayout(1, 2));

		JLabel label_9 = new JLabel("IP Address");
		panel_18.add(label_9);

		textField_4 = new JTextField();
		panel_18.add(textField_4);

		JPanel panel_19 = new JPanel();
		panel_19.setBackground(Color.WHITE);
		panel_9.add(panel_19);
		panel_19.setLayout(new GridLayout(1, 2));

		JLabel label_10 = new JLabel("Port #");
		panel_19.add(label_10);

		textField_5 = new JTextField();
		panel_19.add(textField_5);

		JPanel panel_20 = new JPanel();
		panel_20.setBackground(Color.WHITE);
		panel_9.add(panel_20);
		panel_20.setLayout(new GridLayout(1, 3));

		JButton button_4 = new JButton("Connect");
		panel_20.add(button_4);

		JScrollPane scrollPane_3 = new JScrollPane();
		sl_heart.putConstraint(SpringLayout.EAST, panel_9, -6, SpringLayout.WEST, scrollPane_3);
		sl_heart.putConstraint(SpringLayout.SOUTH, scrollPane_3, 153, SpringLayout.NORTH, heart);
		
				JButton button_5 = new JButton("Disconnect");
				panel_9.add(button_5);
		sl_heart.putConstraint(SpringLayout.NORTH, scrollPane_3, 0, SpringLayout.NORTH, heart);
		sl_heart.putConstraint(SpringLayout.WEST, scrollPane_3, 236, SpringLayout.WEST, heart);
		sl_heart.putConstraint(SpringLayout.EAST, scrollPane_3, 578, SpringLayout.WEST, heart);
		heart.add(scrollPane_3);

		JPanel eye = new JPanel();
		tabbedPane.addTab("Eye", null, eye, null);

		JPanel panel_22 = new JPanel();
		panel_22.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		panel_22.setBackground(Color.WHITE);

		JPanel panel_24 = new JPanel();
		panel_24.setBackground(Color.WHITE);
		panel_24.setLayout(new GridLayout(1, 2));

		JLabel label_13 = new JLabel("IP Address");
		panel_24.add(label_13);

		textField_6 = new JTextField();
		panel_24.add(textField_6);

		JPanel panel_25 = new JPanel();
		panel_25.setBackground(Color.WHITE);
		panel_25.setLayout(new GridLayout(1, 2));

		JLabel label_14 = new JLabel("Port #");
		panel_25.add(label_14);

		textField_7 = new JTextField();
		panel_25.add(textField_7);

		JPanel panel_26 = new JPanel();
		panel_26.setBackground(Color.WHITE);
		panel_26.setLayout(new GridLayout(1, 3));

		JButton button_6 = new JButton("Connect");
		panel_26.add(button_6);

		JScrollPane scrollPane_4 = new JScrollPane();
				SpringLayout sl_eye = new SpringLayout();
				sl_eye.putConstraint(SpringLayout.WEST, scrollPane_4, 215, SpringLayout.WEST, eye);
				sl_eye.putConstraint(SpringLayout.EAST, scrollPane_4, 587, SpringLayout.WEST, eye);
				sl_eye.putConstraint(SpringLayout.WEST, panel_22, 2, SpringLayout.WEST, eye);
				sl_eye.putConstraint(SpringLayout.NORTH, scrollPane_4, 3, SpringLayout.NORTH, eye);
				sl_eye.putConstraint(SpringLayout.SOUTH, scrollPane_4, 154, SpringLayout.NORTH, eye);
				sl_eye.putConstraint(SpringLayout.NORTH, panel_22, 3, SpringLayout.NORTH, eye);
				sl_eye.putConstraint(SpringLayout.SOUTH, panel_22, 154, SpringLayout.NORTH, eye);
				sl_eye.putConstraint(SpringLayout.EAST, panel_22, 212, SpringLayout.WEST, eye);
				eye.setLayout(sl_eye);
		
				JButton button_7 = new JButton("Disconnect");
		GroupLayout gl_panel_22 = new GroupLayout(panel_22);
		gl_panel_22.setHorizontalGroup(
			gl_panel_22.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_22.createSequentialGroup()
					.addGap(2)
					.addGroup(gl_panel_22.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_24, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_25, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_26, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_7, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)))
		);
		gl_panel_22.setVerticalGroup(
			gl_panel_22.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_22.createSequentialGroup()
					.addGap(3)
					.addComponent(panel_24, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addComponent(panel_25, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addComponent(panel_26, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addComponent(button_7, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
		);
		panel_22.setLayout(gl_panel_22);
		eye.add(panel_22);
		eye.add(scrollPane_4);

		buttonConnect.addActionListener(this);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				shutdown();
				System.exit(0);
			}
		});
		setSize(616, 233);
		setVisible(true);

	}

	private void close() {
		System.out.println("closing ....... +++++++");
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
		String data = ((Subscriber) o).getObject().toString();
		if (data.compareTo("FIN") != 0)
			textArea.append(data + "\n");
		else {
			close();
			buttonConnect.setEnabled(true);
		}
	}

	public static void main(String[] args) {
		ClientDemo tester = new ClientDemo();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buttonConnect.setEnabled(false);
		String faceIP = faceIp.getText() != null ? faceIp.getText() : "localhost";
		String heartIP = heartIp.getText() != null ? heartIp.getText() : "localhost";
		String bciIP = bciIp.getText() != null ? bciIp.getText() : "localhost";
		String skinIP = skinIp.getText() != null ? skinIp.getText() : "localhost";
		String eyeIP = eyeIp.getText() != null ? eyeIp.getText() : "localhost";
		int facePo = facePort.getText() != null ? Integer.parseInt(facePort.getText()) : 0;
		int skinPo = skinPort.getText() != null ? Integer.parseInt(skinPort.getText()) : 0;
		int heartPo = heartPort.getText() != null ? Integer.parseInt(heartPort.getText()) : 0;
		int bciPo = bciPort.getText() != null ? Integer.parseInt(bciPort.getText()) : 0;
		int eyePo = eyePort.getText() != null ? Integer.parseInt(eyePort.getText()) : 0;
		subscriber[0] = new Subscriber(faceIP, facePo);
		subscriber[1] = new Subscriber(heartIP, heartPo);
		subscriber[2] = new Subscriber(bciIP, bciPo);
		subscriber[3] = new Subscriber(skinIP, skinPo);
		subscriber[4] = new Subscriber(eyeIP, eyePo);
		service.submit(subscriber[0]);
		subscriber[0].addObserver(this);

		service.submit(subscriber[1]);
		subscriber[1].addObserver(this);

		service.submit(subscriber[2]);
		subscriber[2].addObserver(this);

		service.submit(subscriber[3]);
		subscriber[3].addObserver(this);

		service.submit(subscriber[4]);
		subscriber[4].addObserver(this);

	}
}