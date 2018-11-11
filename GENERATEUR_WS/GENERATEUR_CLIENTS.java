package GENERATEUR_WS;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.xml.namespace.QName;

import org.apache.axis.AxisFault;

import ANALYSEUR.CLASSE;
   
public class GENERATEUR_CLIENTS 
{ 
	 public GENERATEUR_CLIENTS(File projet,Vector<CLASSE> LISTE_CLASSES)
	 {
	  try
	  {
		File CLIENTS = new File(projet.getParentFile(),"CLIENTS");
		 if(!CLIENTS.exists())
		  CLIENTS.mkdir(); 
		 else
		 {
		  File[] files = CLIENTS.listFiles();
		  for(int i=0;i<files.length;i++)
		   files[i].delete();
		 }
		for(int i=0;i<LISTE_CLASSES.size();i++)
		{
		  @SuppressWarnings("resource")
		  FileWriter F = new FileWriter(CLIENTS+"\\"+LISTE_CLASSES.elementAt(i).Classnom+"Client.java");
		  F.write("package CLIENTS;\n\n"); 
		  F.write("import java.net.URL;\n");
		  F.write("import javax.xml.namespace.QName;\n");
		  F.write("import org.apache.axis.client.Call;\n");
		  F.write("import org.apache.axis.client.Service;\n");
		  F.write("import javax.xml.rpc.ServiceException;\n");
		  F.write("import java.net.MalformedURLException;\n");
		  F.write("import org.apache.axis.AxisFault;");
		  F.write("import java.rmi.RemoteException;");
			
		  F.write("public class "+LISTE_CLASSES.elementAt(i).Classnom+"Client\n");
		  F.write("{\n");
	      F.write("  Service "+LISTE_CLASSES.elementAt(i).Classnom+" = null;\n");
		  F.write("  String url = null;\n");
		  F.write("  Call appel = null;\n");
		  F.write("  Object[] parametres = new Object[]{};\n");
		
		  F.write("  public "+LISTE_CLASSES.elementAt(i).Classnom+"Client()\n");
		  F.write("  {\n");
		  F.write("    "+LISTE_CLASSES.elementAt(i).Classnom+" = new Service();\n"); 
		  F.write("    url=\"http://localhost:8080/axis2/services/"+LISTE_CLASSES.elementAt(i).Classnom+"?wsdl\";\n");
		  F.write("    try\n");
		  F.write("    {\n");
		  F.write("     appel=(Call)"+LISTE_CLASSES.elementAt(i).Classnom+".createCall();\n");
		  F.write("     appel.setTargetEndpointAddress(new URL(url));\n");
		  F.write("    } catch (ServiceException e) {} catch (MalformedURLException e) {}\n");
		  F.write("  }\n");
		  
		 /* for(int j=0;j<LISTE_CLASSES.elementAt(i).methodes.size();j++)
		  {
			F.write("  public "+LISTE_CLASSES.elementAt(i).methodes.elementAt(j).Type+" "+LISTE_CLASSES.elementAt(i).methodes.elementAt(j).Mnom+"()\n");
			F.write("  {\n");
			F.write("   appel.setOperationName(new QName(\"http://SERVICES\",\""+LISTE_CLASSES.elementAt(i).methodes.elementAt(j).Mnom+"\"));\n");
			//F.write("   Object res = appel.invoke(new Object[]{new Double(100),new Double(7)});\n");
			F.write("   try\n");
			F.write("   {\n");
			F.write("    appel.invoke();\n");
			F.write("   } catch (AxisFault e) {}\n");
			F.write("   return ("+LISTE_CLASSES.elementAt(i).methodes.elementAt(j).Type+")null;\n");
			F.write("  }\n"); 
		  }*/
		  //JOptionPane.showMessageDialog(null, "le résultat de la méthode invoque : " +BC.AppelMethode("SALUT"));
		  F.write("  public Object AppelMethode(String Methode)\n"); 
		  F.write("  {\n");	
		  F.write("   Object res = null;\n");
		  F.write("   try\n");
		  F.write("   {\n");
		  F.write("    appel.setOperationName(new QName(\"http://SERVICES\",Methode));\n");
		  F.write("    res = appel.invoke(parametres);\n");
		  F.write("   } catch (AxisFault e) {} catch (RemoteException e) {}\n");
		  F.write("   return res;\n");
		  F.write("  }\n");
		  
		  F.write("  public static void main(String [] args)\n"); 
		  F.write("  {\n");	
		  F.write("   new "+LISTE_CLASSES.elementAt(i).Classnom+"Client();\n");
		  F.write("  }\n");
		  
		  F.write("}\n");
		  F.close();
		}
	  }catch(IOException e){}
	 }
}