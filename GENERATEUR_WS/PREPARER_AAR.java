package GENERATEUR_WS;

import java.io.File;
import java.io.IOException;
import ANALYSEUR.FILE;

public class PREPARER_AAR 
{
	public PREPARER_AAR(File projet) 
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
	}
}