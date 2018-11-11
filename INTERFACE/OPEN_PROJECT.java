package INTERFACE;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.awt.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import ANALYSEUR.CLASSES;
import ANALYSEUR.FILE;


public class OPEN_PROJECT {
	CLASSES C =null;
	File projet = null;
	
	public OPEN_PROJECT() 
	{
	}
	public void Parcourir() 
	{
	   JFileChooser chooser = new JFileChooser();
	   chooser.setCurrentDirectory(new java.io.File("."));
	   chooser.setDialogTitle("Source de projet :");
	   chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	   if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
	   {
		projet = chooser.getSelectedFile();
	    
		File SERVICES = new File("C:\\Users\\yazid\\Desktop\\BOUNAB\\ASTWEB\\src\\SERVICES");
		  if(!SERVICES.exists())
		   SERVICES.mkdir(); 
		  else
		  {
		   SERVICES.delete();
 		   //SERVICES.deleteOnExit();
		   File[] files = SERVICES.listFiles();
		   for(int i=0;i<files.length;i++)
	 	    files[i].delete();
		  }
		  
		  FILE F = new FILE();
		  try {
			F.copyDirectory(projet, SERVICES);
		  } catch (IOException e) {}
		  this.projet = SERVICES;
	   }
	   else
		System.out.println("You canceled.");
	}
	public void Analyse(FORM Form)
	{
		 if(projet!=null)
		 {
			C =new CLASSES(projet,Form);
			for(int i=0;i<C.LISTE_CLASSES.size();i++)
			 for(int j=0;j<C.LISTE_CLASSES.elementAt(i).methodes.size();j++)
			 {
			  C.LISTE_CLASSES.elementAt(i).methodes.elementAt(j).Couplage();
			  C.LISTE_CLASSES.elementAt(i).methodes.elementAt(j).CATEGORIE();
			 }
		 }
		 else
		  JOptionPane.showMessageDialog(null,"Choisir un dossier s.v.p !");
	}
}
