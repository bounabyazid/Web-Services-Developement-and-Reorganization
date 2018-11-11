package INTERFACE;

import java.awt.Frame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Acceuil extends Frame {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;
	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(251, 569, 105, 30));
			jButton1.setText("Démarer");
			jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					Acceuil.this.dispose();
					new FORM();
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setBounds(new Rectangle(387, 570, 84, 26));
			jButton2.setText("Quiter");
			jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					System.exit(0);
				}
			});
		}
		return jButton2;
	}

	/**
	 * This method initializes jButton3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setBounds(new Rectangle(500, 569, 84, 26));
			jButton3.setText("Aide");
			jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					System.out.println("Aide"); // TODO Auto-generated Event stub mouseClicked()
				}
			});
		}
		return jButton3;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Acceuil();

	}

	/**
	 * This is the default constructor
	 */
	public Acceuil() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		jLabel2 = new JLabel();
		jLabel2.setBounds(new Rectangle(9, 136, 877, 420));
		jLabel2.setIcon(new ImageIcon(getClass().getResource("/WSDR.png")));
		jLabel2.setText("");
		jLabel1 = new JLabel();
		jLabel1.setBounds(new Rectangle(9, 30, 877, 104));
		jLabel1.setIcon(new ImageIcon(getClass().getResource("/UNIV.png")));
		jLabel1.setText("");
		this.setLayout(null);
		this.setSize(888, 623);
		this.setTitle("Acceuil");
		this.setResizable(false);
		this.setVisible(true);

		this.add(jLabel1, null);
		this.add(jLabel2, null);
		this.add(getJButton1(), null);
		this.add(getJButton2(), null);
		this.add(getJButton3(), null);
	}

}  //  @jve:decl-index=0:visual-constraint="14,-1"
