package FacialGesturesClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import FacialGesturesClient.Subscriber;

public class SkinClient extends JPanel implements Observer, ActionListener {
	private JTextArea textArea = new JTextArea();
	// private final Subscriber subscriber;
	// private final ExecutorService service;
	private JButton connect = new JButton("Connect");
	private JTextField skinIp = new JTextField();
	private JTextField skinPort = new JTextField();
	private Border border = BorderFactory.createLineBorder(Color.BLACK);

	private Pattern pattern;
	private Matcher matcher;
	private static final String PORT_PATTERN = "[0-9]+";
	private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	public void IPAddressValidator() {
		pattern = Pattern.compile(IPADDRESS_PATTERN);
	}

	/**
	 * Validate ip address with regular expression
	 * 
	 * @param ip
	 *            ip address for validation
	 * @return true valid ip address, false invalid ip address
	 */
	public boolean validate(final String ip) {
		IPAddressValidator();
		matcher = pattern.matcher(ip);
		return matcher.matches();
	}

	public boolean validatePort(String port) {
		Pattern patternPort = Pattern.compile(PORT_PATTERN);
		matcher = patternPort.matcher(port);
		return matcher.matches();
	}

	public JPanel processPanelSkin(String lableName) {

		JPanel jLabel = new JPanel();
		jLabel.setBackground(new Color(0, 94, 181));
		jLabel.setLayout(new GridLayout(1, 1));
		jLabel.add(new JLabel(lableName), BorderLayout.CENTER);

		Font labelFont = jLabel.getFont();

		int componentWidth = jLabel.getWidth();

		// Find out how much the font can grow in width.
		double widthRatio = (double) componentWidth / (double) 5;

		int newFontSize = (int) (labelFont.getSize() * widthRatio);
		int componentHeight = jLabel.getHeight();

		// Pick a new font size so it will not be larger than the height of label.
		int fontSizeToUse = Math.min(newFontSize, componentHeight);

		// Set the label's font size to the newly determined size.
		jLabel.setFont(new Font(labelFont.getName(), Font.BOLD, fontSizeToUse));

		JPanel jPanel = new JPanel();
		jPanel.setBackground(Color.white);
		jPanel.setLayout(new GridLayout(1, 2));
		jPanel.add(new JLabel("IP Address"));
		jPanel.add(skinIp);

		JPanel jPort = new JPanel();
		jPort.setBackground(Color.white);
		jPort.setLayout(new GridLayout(1, 2));
		jPort.add(new JLabel("Port #"));
		jPort.add(skinPort);

		JPanel connectCondition = new JPanel();
		connectCondition.setBackground(Color.white);
		connectCondition.setLayout(new GridLayout(1, 3));

		connect.addActionListener(this);

		connectCondition.add(connect);

		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setLayout(new GridLayout(4, 1));
		panel.add(jLabel, BorderLayout.NORTH);
		panel.add(jPanel, BorderLayout.AFTER_LAST_LINE);
		panel.add(jPort, BorderLayout.AFTER_LAST_LINE);
		panel.add(connectCondition, BorderLayout.AFTER_LAST_LINE);
		panel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

		JPanel outerPanel = new JPanel();
		outerPanel.setBackground(Color.white);
		outerPanel.setLayout(new GridLayout(2, 1));
		outerPanel.add(panel);
		textArea.setBorder(border);
		textArea.setEditable(false);
		textArea.setFont(new Font("Sans-Serif", Font.BOLD, 12));

		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		outerPanel.add(scroll, BorderLayout.CENTER);
		return outerPanel;
	}

	public void update(Observable o, Object arg) {
		String data = ((Subscriber) o).getObject().toString();
		if (data.compareTo("FIN") != 0)
			textArea.append(data + "\n");
		else {
			close();
			connect.setEnabled(true);
		}
	}

	public void close() {
		// System.out.println("Closing...");
		// subscriber.stop();
	}

	public static void setWarningMsg(String text) {
		Toolkit.getDefaultToolkit().beep();
		JOptionPane optionPane = new JOptionPane(text, JOptionPane.WARNING_MESSAGE);
		JDialog dialog = optionPane.createDialog("Warning!");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// connect.setEnabled(false);
		// service.submit(subscriber);
		// subscriber.addObserver(this);
		if (connect.getText().equals("Connect")) {
			if (skinIp.getText().equals("") || skinPort.getText().equals("")) {
				setWarningMsg("Please enter a valid IP Address & Port Number.");
			} else if (validate(skinIp.getText()) && skinPort.getText().length() == 4
					&& validatePort(skinPort.getText())) {
				connect.setText("Disconnect");
			} else {
				setWarningMsg("Please enter a valid IP Address & Port Number.");
			}
		} else {
			connect.setText("Connect");
		}
	}
}
