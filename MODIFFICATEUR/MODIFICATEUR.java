package MODIFFICATEUR;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.List;

public class MODIFICATEUR extends Frame 
{
	private static final long serialVersionUID = 1L;
	private JButton jButton1 = null;
	public List list1 = null;

	public MODIFICATEUR() throws HeadlessException
	{
		super();
		initialize();
	}

	private JButton getJButton1() 
	{
		if (jButton1 == null) 
		{
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(520, 310, 100, 34));
			jButton1.setText("Exit");
			jButton1.addMouseListener(new java.awt.event.MouseAdapter()
			{
				public void mouseClicked(java.awt.event.MouseEvent e) 
				{
					MODIFICATEUR.this.dispose();
				}
			});
		}
		return jButton1;
	}

	private List getList1() 
	{
		if (list1 == null)
		{
			list1 = new List();
			list1.setBounds(new Rectangle(24, 47, 600, 243));
		}
		return list1;
	}
	
	private void initialize()
	{
		this.setLayout(null);
		this.setSize(640, 367);
		this.setTitle("Modiffication du code JAVA :");

		this.add(getJButton1(), null);
		this.add(getList1(), null);
		this.setVisible(true);
		this.setResizable(false);
	}
}