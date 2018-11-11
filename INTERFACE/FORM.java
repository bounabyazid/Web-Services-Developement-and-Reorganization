package INTERFACE;

import java.awt.Frame;
import ANALYSEUR.*;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Dimension;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

import javax.swing.JTree;
import java.awt.Rectangle;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

import java.awt.Button;
import javax.swing.JButton;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JProgressBar;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import ANALYSEUR.METHODE;
import GENERATEUR_WS.COMPILATION;
import GENERATEUR_WS.GENERATEUR_AAR;
import GENERATEUR_WS.GENERATEUR_CLIENTS;
import GENERATEUR_WS.GENERATEUR_WSDL;
import GENERATEUR_WS.GENERATEUR_XMLS;
import GENERATEUR_WS.PREPARER_AAR;
import MODIFFICATEUR.Modifficateur;


public class FORM extends Frame {

	private static final long serialVersionUID = 1L;
	
	public List list = null;
	public List list2 = null;
	
	public JTable jTable1 = null;
	public JTable jTable2 = null;
	public JTable jTable3 = null;
	public JTable jTable4 = null;
	public JTable jTable5 = null;

	public JScrollPane jScrollPane1 = null;
	public JScrollPane jScrollPane2 = null;
	public JScrollPane jScrollPane3 = null;
	public JScrollPane jScrollPane4 = null;
	public JScrollPane jScrollPane5 = null;

	public DefaultTableModel model1 = null;
	
	DefaultTableModel model2 = null;
	DefaultTableModel model3 = null;
	DefaultTableModel model4 = null;
	DefaultTableModel model5 = null;
	
	
	OPEN_PROJECT project = null;
	
	JMenuBar MB = new JMenuBar();
	JMenu édition = new JMenu("Edition");
    JMenu option = new JMenu("Configurer Couplage");

    public Couplage couplage = new Couplage();
    
	public FORM() 
	{
		super();
		MenuBar mbar = new MenuBar( );
	    Menu m1 = new Menu("Fichier");
	    Menu m2 = new Menu("Edition");
	    Menu m3 = new Menu("Aide");
	    
	    final MenuItem m1item1 = new MenuItem("Ouvrir");
	    final MenuItem m1item2 = new MenuItem("Analyse");
	    final MenuItem m1item3 = new MenuItem("Quitter");

	    final MenuItem m2item1 = new MenuItem("Configurer Couplage");
	    final MenuItem m2item2 = new MenuItem("Modifier");
	    final MenuItem m2item3 = new MenuItem("Génerer Services Web");
	    	    
	    final MenuItem m3item1 = new MenuItem("A propos");
	    
	    FORM.this.setMenuBar(mbar);
	    
	    m1.add(m1item1);
	    m1.add(m1item2);m1item2.setEnabled(false);
	    m1.add(m1item3);
	    
	    m2.add(m2item1);
	    m2.add(m2item2);m2item2.setEnabled(false);
	    m2.add(m2item3);m2item3.setEnabled(false);
	    
	    m3.add(m3item1);
	    
	    mbar.add(m1);
	    mbar.add(m2); 
	    mbar.add(m3); 
    	        	 
	        	 m1item1.addActionListener(new ActionListener() 
	        	 {	
					public void actionPerformed(ActionEvent arg0)
					{
						 model1.getDataVector().removeAllElements();
						 jTable1.revalidate();
						 list.clear();
						 list2.clear();
						 project = new OPEN_PROJECT();
						 project.Parcourir();
						 m1item2.setEnabled(true);
					}
				  });
	        	 
	        	  m1item2.addActionListener(new ActionListener() 
	        	  {	
						public void actionPerformed(ActionEvent arg0) 
						{
							model1.getDataVector().removeAllElements();
							jTable1.revalidate();
							list.clear();
							list2.clear();
							project.Analyse(FORM.this);
							m2item2.setEnabled(true);	
						}
				   });
	        	   m1item3.addActionListener(new ActionListener()
	        	   {
						public void actionPerformed(ActionEvent arg0) 
						{
							System.exit(0);
						}
					});
	         
	    
	        	    m2item1.addActionListener(new ActionListener() 
	        	    {
					 public void actionPerformed(ActionEvent arg0)
					 {
					   couplage.setVisible(true);
					 }
				    });
	        	    m2item2.addActionListener(new ActionListener() 
	        	    {
					 public void actionPerformed(ActionEvent arg0)
					 {
				       Modifficateur CM = new Modifficateur(new File(/*project.C.projet*/"C:\\Users\\yazid\\Desktop\\BOUNAB\\ASTWEB\\src\\JavaCodes"),project.C.LISTE_CLASSES);	
				      
				       model1.getDataVector().removeAllElements();
					   jTable1.revalidate();
					   list.clear();
					   list2.clear();
					   project.Analyse(FORM.this);
					   m2item3.setEnabled(true);
					 }
				    });
	        	    m2item3.addActionListener(new ActionListener() 
	        	    {
					 public void actionPerformed(ActionEvent arg0)
					 {
					  new GENERATEUR_XMLS(project.projet, project.C.LISTE_CLASSES);
					  new COMPILATION(project.projet);
					  new GENERATEUR_AAR(project.projet, project.C.LISTE_CLASSES);
					  //new GENERATEUR_CLIENTS(project.projet, project.C.LISTE_CLASSES);
					 }
				    });
	        	    m3item1.addActionListener(new ActionListener() 
	        	    {
					 public void actionPerformed(ActionEvent arg0)
					 {
	                  new apropos();
					  //JOptionPane.showMessageDialog(null, "This Program has been devloped by Med Khider University of Biskra"); 
	                 }
	                }); 
	    
		initialize();
	}
	
	List getList()
	{
		if (list == null) 
		{
			list = new List();
			list.setBounds(new Rectangle(16, 260, 349, 255));
		}
		return list;
	}
    List getList2() 
    {
		if (list2 == null)
		{
			list2 = new List();
			list2.setBounds(new Rectangle(370, 260, 349, 255));
		}
		return list2;
	}
	private void initialize() 
	{
		this.setTitle("Anallyseur :");
		this.setLayout(null);
		this.setSize(1150, 530);
		this.setVisible(true);
		this.setResizable(false);

		this.add(getList(), null);
		this.add(getList2(), null);

		this.add(getJScrollPane1(), null);
		this.add(getJScrollPane2(), null);
		this.add(getJScrollPane3(), null);
		this.add(getJScrollPane4(), null);
		this.add(getJScrollPane5(), null);
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(16, 55, 226, 200));
			jScrollPane1.setViewportView(getJTable1());
		}
		return jScrollPane1;
	}
	private JTable getJTable1() {
	if (jTable1 == null) 
	{
		String Col [] = {"Class Name"};
		String Data[][] = {};
		model1 = new DefaultTableModel(Data,Col);
		jTable1 = new JTable(model1);
		
		jTable1.addMouseListener(new java.awt.event.MouseAdapter() 
		{
			public void mouseClicked(java.awt.event.MouseEvent e) 
			{
				model2.getDataVector().removeAllElements();
				jTable2.revalidate();
				for(int i=0;i<project.C.LISTE_CLASSES.elementAt(jTable1.getSelectedRow()).methodes.size();i++)
				 FORM.this.model2.addRow(new Object[] {project.C.LISTE_CLASSES.elementAt(jTable1.getSelectedRow()).methodes.elementAt(i).Mnom,project.C.LISTE_CLASSES.elementAt(jTable1.getSelectedRow()).methodes.elementAt(i).Type});
			}
		});

	}
	return jTable1;
    }
	
    
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setBounds(new Rectangle(250, 55, 226, 200));
			jScrollPane2.setViewportView(getJTable2());
		}
		return jScrollPane2;
	}

	private JTable getJTable2() {
		if (jTable2 == null) {
			String Col [] = {"Method Name","Type"};
			String Data[][] = {};
			model2 = new DefaultTableModel(Data,Col);
			jTable2 = new JTable(model2);
			jTable2.addMouseListener(new java.awt.event.MouseAdapter() 
			{
				public void mouseClicked(java.awt.event.MouseEvent e) 
				{
					model3.getDataVector().removeAllElements();
					jTable3.revalidate();
					
					model4.getDataVector().removeAllElements();
					jTable4.revalidate();
					
					model5.getDataVector().removeAllElements();
					jTable5.revalidate();
					
					for(int i=0;i<project.C.LISTE_CLASSES.elementAt(jTable1.getSelectedRow()).methodes.elementAt(jTable2.getSelectedRow()).Mpara.size();i++)
					{
					// if(project.C.ClassExiste(project.C.LISTE_CLASSES.elementAt(jTable1.getSelectedRow()).methodes.elementAt(jTable2.getSelectedRow()).Mpara.elementAt(i).Vtype))
					  FORM.this.model3.addRow(new Object[] {project.C.LISTE_CLASSES.elementAt(jTable1.getSelectedRow()).methodes.elementAt(jTable2.getSelectedRow()).Mpara.elementAt(i).Vnom,project.C.LISTE_CLASSES.elementAt(jTable1.getSelectedRow()).methodes.elementAt(jTable2.getSelectedRow()).Mpara.elementAt(i).Vtype});
					}
					
					for(int i=0;i<project.C.LISTE_CLASSES.elementAt(jTable1.getSelectedRow()).methodes.elementAt(jTable2.getSelectedRow()).VARS.size();i++)
					{
					// if(project.C.ClassExiste(project.C.LISTE_CLASSES.elementAt(jTable1.getSelectedRow()).methodes.elementAt(jTable2.getSelectedRow()).VARS.elementAt(i).Vtype))
                      FORM.this.model4.addRow(new Object[] {project.C.LISTE_CLASSES.elementAt(jTable1.getSelectedRow()).methodes.elementAt(jTable2.getSelectedRow()).VARS.elementAt(i).Vnom,project.C.LISTE_CLASSES.elementAt(jTable1.getSelectedRow()).methodes.elementAt(jTable2.getSelectedRow()).VARS.elementAt(i).Vtype});
					}
					
					for(int i=0;i<project.C.LISTE_CLASSES.elementAt(jTable1.getSelectedRow()).methodes.elementAt(jTable2.getSelectedRow()).MIS.size();i++)
					{
					// if(project.C.ClassExiste(project.C.LISTE_CLASSES.elementAt(jTable1.getSelectedRow()).methodes.elementAt(jTable2.getSelectedRow()).VARS.elementAt(i).Vtype))
                     METHODE m = project.C.LISTE_CLASSES.elementAt(jTable1.getSelectedRow()).methodes.elementAt(jTable2.getSelectedRow());
					 FORM.this.model5.addRow(new Object[] {m.MIS.elementAt(i).MName,m.MIS.elementAt(i).MClass,m.MIS.elementAt(i).NbInvoc,m.MIS.elementAt(i).Couplage,m.MIS.elementAt(i).Categorie,m.MIS.elementAt(i).arbre});
					}
				}
			});
		}
		return jTable2;
	}

	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setBounds(new Rectangle(485, 55, 236, 98));
			jScrollPane3.setViewportView(getJTable3());
		}
		return jScrollPane3;
	}
	
	private JTable getJTable3() {
		if (jTable3 == null) {
			String Col [] = {"parameter Name","parameter Type"};
			String Data[][] = {};
			model3 = new DefaultTableModel(Data,Col);
			jTable3 = new JTable(model3);
		}
		return jTable3;
	}

	private JScrollPane getJScrollPane4() {
		if (jScrollPane4 == null) {
			jScrollPane4 = new JScrollPane();
			jScrollPane4.setBounds(new Rectangle(485, 157, 236, 98));
			jScrollPane4.setViewportView(getJTable4());
		}
		return jScrollPane4;
	}
	
	private JTable getJTable4() {
		if (jTable4 == null) {
			String Col [] = {"Variable Name","Variable Type"};
			String Data[][] = {};
			model4 = new DefaultTableModel(Data,Col);
			jTable4 = new JTable(model4);
		}
		return jTable4;
	}
	
	private JScrollPane getJScrollPane5() {
		if (jScrollPane5 == null) {
			jScrollPane5 = new JScrollPane();
			jScrollPane5.setBounds(new Rectangle(730, 55, 410, 460));//diff14
			jScrollPane5.setViewportView(getJTable5());
		}
		return jScrollPane5;
	}
	
	private JTable getJTable5() {
		if (jTable5 == null) {
			String Col [] = {"Method","Class","Poids","Couplage","Categorie","Arbre"};
			String Data[][] = {};
			model5 = new DefaultTableModel(Data,Col);
			jTable5 = new JTable(model5);
		}
		return jTable5;
	}

	public static void main(String[] args) 
    {
		new FORM();
	}
} 