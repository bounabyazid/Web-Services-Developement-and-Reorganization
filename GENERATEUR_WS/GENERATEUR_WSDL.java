package GENERATEUR_WS;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;

import ANALYSEUR.CLASSE;

public class GENERATEUR_WSDL 
{

	public GENERATEUR_WSDL(File projet,Vector<CLASSE> LISTE_CLASSES)
	{
	   try
	   {	 char   c='"';	  	 
			 FileWriter F = new FileWriter(projet+"\\WSDL.bat");
			 F.write("cd "+projet.getPath());
			 for(int i=0;i<LISTE_CLASSES.size();i++) 
			  F.write("\njava org.apache.axis.wsdl.Java2WSDL -o "+LISTE_CLASSES.elementAt(i).Classnom+".wsdl -l "+c+"http://localhost:8080/axis2/services/"+LISTE_CLASSES.elementAt(i).Classnom+c+" "+"SERVICES.impl."+LISTE_CLASSES.elementAt(i).Classnom);
			 F.close();
			 Runtime.getRuntime().exec(projet+"\\WSDL.bat"); 
	   }catch(IOException e){}
	}

	public static void main(String[] args) 
	{
		char   c='"';
		String nompackage = "SERVICES";
		String NomClass = "MATH";
		System.out.println("java org.apache.axis.wsdl.Java2WSDL -o "+NomClass+".wsdl -l"+c+"http://localhost:8080/axis/services/"+NomClass+c+" " +nompackage+".impl."+NomClass);
	}
}