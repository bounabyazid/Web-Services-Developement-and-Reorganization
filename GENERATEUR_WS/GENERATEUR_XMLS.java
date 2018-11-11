package GENERATEUR_WS;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import ANALYSEUR.CLASSE;

public class GENERATEUR_XMLS {

	public GENERATEUR_XMLS(File projet,Vector<CLASSE> LISTE_CLASSES) 
	{
		File WEB_INF = new File(projet,"WEB-INF");
		if(!WEB_INF.exists())
		 WEB_INF.mkdir(); 
		 
		File AAR = new File(WEB_INF,"AAR");
		 if(!AAR.exists())
		  AAR.mkdir(); 
		 else
		  AAR.delete();
       try 
       {
    	 for(int i=0;i<LISTE_CLASSES.size();i++)
    	 {
    		//------------creation d'un repertoir pour xml------------
    		 
    		 File SERVICE = new File(AAR,LISTE_CLASSES.elementAt(i).Classnom);
    		 if(!SERVICE.exists())
    		  SERVICE.mkdir(); 
    		    		
    		 File META_INF = new File(SERVICE+"\\META-INF");
    		 if(!META_INF.exists())
    		  META_INF.mkdir(); 
    		 
    		 File SERVICES = new File(SERVICE+"\\SERVICES");
    		 if(!SERVICES.exists())
    		  SERVICES.mkdir(); 
    		//--------------------------------------------------------
			FileWriter F = new FileWriter(META_INF+"\\services.xml");
			F.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			F.write("<service name=\""+LISTE_CLASSES.elementAt(i).Classnom+"\">\n");
			F.write("<description>"+LISTE_CLASSES.elementAt(i).Classnom+" program</description>\n");
			F.write("<parameter name=\"ServiceClass\" locked=\"false\">SERVICES"/*LISTE_CLASSES.elementAt(i).Classnom*/+"."+LISTE_CLASSES.elementAt(i).Classnom+"</parameter>\n");
			for(int j=0;j<LISTE_CLASSES.elementAt(i).methodes.size();j++)
			{
			  F.write("<operation name=\""+LISTE_CLASSES.elementAt(i).methodes.elementAt(j).Mnom+"\">\n");
			  F.write("<messageReceiver class=\"org.apache.axis2.rpc.receivers.RPCMessageReceiver\" />\n"); 
			  F.write("</operation>\n");
			}
			F.write("</service>\n");
			F.close();
    	 }
	   } catch (IOException e) {}
		 
	}
}
