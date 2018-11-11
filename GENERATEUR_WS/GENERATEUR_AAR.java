package GENERATEUR_WS;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import ANALYSEUR.CLASSE;
import ANALYSEUR.FILE;

public class GENERATEUR_AAR 
{
	public GENERATEUR_AAR(File projet,Vector<CLASSE> LISTE_CLASSES) 
	{
		 String path = null;
		 File FromSERVICES = new File(projet+"\\WEB-INF\\SERVICES");
		 File ToSERVICES = new File(projet+"\\WEB-INF\\AAR");
		 
		 if(!FromSERVICES.exists())	 
		  FromSERVICES.mkdir();
		/* else
		 {
		  File[] FromFiles = FromSERVICES.listFiles();
		  for(int i=0;i<FromFiles.length;i++) 
		   FromFiles[i].delete();
		 }*/
		   File[] FromFiles = FromSERVICES.listFiles();
		   File[] ToFiles = ToSERVICES.listFiles();
		   for(int i=0;i<FromFiles.length;i++)
		   {
		     FILE F = new FILE();
			 try {
		 	 path = projet+"\\WEB-INF\\AAR\\"+ToFiles[i].getName()+"\\SERVICES\\"+FromFiles[i].getName();		 	 
		 	 //System.out.println(path);
			 F.copy(FromFiles[i], new File(path));
		    } catch (IOException e) {}
		   }		 
	  //new PREPARER_AAR(projet);
	  String axispath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\axis2\\WEB-INF\\services";
	  File PROVIDERS = new File(projet+"\\WEB-INF\\AAR");
	  File[] Files = PROVIDERS.listFiles();
	  try {
	    FileWriter F = new FileWriter(projet+"\\AAR.bat");
	    for(int i=0;i<Files.length;i++)
        {				 	 
		  F.write("cd "+PROVIDERS+"\\"+LISTE_CLASSES.elementAt(i).Classnom);
		  F.write("\njar -cvf "+LISTE_CLASSES.elementAt(i).Classnom+".aar ./*\n");
		  F.write("xcopy /Y "+PROVIDERS+"\\"+LISTE_CLASSES.elementAt(i).Classnom+"\\"+LISTE_CLASSES.elementAt(i).Classnom+".aar "+axispath+"\n");
		}
	    F.close();
	    
	    Runtime.getRuntime().exec(projet+"\\AAR.bat"); 
 
		} catch (IOException e) {} 	
	}
}
//C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\axis2\WEB-INF\services
//C:\Users\yazid\Desktop\BOUNAB\ASTWEB\src\SERVICES\WEB-INF\AAR\Converssion\Converssion.aar
//cd C:\Users\yazid\Desktop\BOUNAB\ASTWEB\src\SERVICES\WEB-INF\AAR\Converssion
//jar -cvf Converssion.aar ./*