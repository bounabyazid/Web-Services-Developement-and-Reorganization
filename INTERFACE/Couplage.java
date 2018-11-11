package INTERFACE;

import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.TextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Couplage extends Frame {

	private static final long serialVersionUID = 1L;
	public TextField textField1 = null;
	public TextField textField2 = null;
	public TextField textField3 = null;
	private JLabel jLabel = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	public Couplage() throws HeadlessException {
		super();
		initialize();
	}
	
	private TextField getTextField1() {
		if (textField1 == null) {
			textField1 = new TextField();
			textField1.setBounds(new Rectangle(100, 45, 140, 25));
			textField1.setText("10");
			textField1.setEditable(true);
		}
		return textField1;
	}

	private TextField getTextField2() {
		if (textField2 == null) {
			textField2 = new TextField();
			textField2.setBounds(new Rectangle(100, 90, 140, 25));
			textField2.setText("5");
			textField2.setEditable(true);
		}
		return textField2;
	}

	private TextField getTextField3() {
		if (textField3 == null) {
			textField3 = new TextField();
			textField3.setBounds(new Rectangle(100, 135, 140, 25));
			textField3.setText("4");
			textField3.setEditable(true);
		}
		return textField3;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(20, 170, 65, 28));
			jButton1.setText("ok");
			jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
				 Couplage.this.setVisible(false);
				}
			});
		}
		return jButton1;
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setBounds(new Rectangle(155, 170, 83, 26));
			jButton2.setText("Annuler");
			jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					textField1.setText("10");
					textField2.setText("5");
					textField3.setText("4");
					Couplage.this.setVisible(false);
				}
			});
		}
		return jButton2;
	}
	
	private void initialize() 
	{
		jLabel3 = new JLabel();
		jLabel3.setBounds(new Rectangle(20, 135, 65, 25));
		jLabel3.setText("Faible :");
		jLabel2 = new JLabel();
		jLabel2.setBounds(new Rectangle(20, 90, 65, 25));
		jLabel2.setText("Moyen :");
		jLabel = new JLabel();
		jLabel.setBounds(new Rectangle(20, 45, 65, 25));
		jLabel.setText("Fort :");
		this.setLayout(null);
		this.setSize(273, 213);
		this.setTitle("Configuration des poids");
		//this.setVisible(true);

		this.add(getTextField1(), null);
		this.add(getTextField2(), null);
		this.add(getTextField3(), null);
		this.add(jLabel, null);
		this.add(jLabel2, null);
		this.add(jLabel3, null);
		this.add(getJButton1(), null);
		this.add(getJButton2(), null);
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"  