package ma.supmti.forum.main;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ma.supmti.forum.view.ForumSupMTIOujdaView;

@SuppressWarnings("serial")
public class ForumSwingMain extends JFrame {
	
	private int width  = 784;
	private int height = 562;
	
	private JPanel contentPanel = null;
	
	private ForumSupMTIOujdaView forum = null;
	
	public ForumSwingMain() {
		super("Swing and Pivot Integration : Forum 2014");
		initialize();
	}
	
	private void initialize() {
		this.setSize(width, height);
		this.add(getContentPanel(), BorderLayout.CENTER);
		this.setIconImage(new ImageIcon(getClass().getResource("/ma/supmti/forum/view/img/logo.png")).getImage());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				getForum().close();
				System.exit(0);
			}

		});
		this.setResizable(true);
		this.setVisible(true);
	}

	private JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(getForum().getDisplayHost(), BorderLayout.CENTER);
			forum.init();
		}
		return contentPanel;
	}
	
	private ForumSupMTIOujdaView getForum() {
		if(forum == null) {
			forum = new ForumSupMTIOujdaView();
		}
		return forum;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(
				new Runnable() {
					
					public void run() {
						new ForumSwingMain();
					}
					
				}
		);
	}

}
