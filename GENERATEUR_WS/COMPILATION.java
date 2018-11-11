package GENERATEUR_WS;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

//cd chemin des fichiers.class
//jar -cvf sarah.aar ./*

public class COMPILATION 
{
	public COMPILATION(File projet) 
	{
	  try
	  {		  	 
		 FileWriter F = new FileWriter(projet+"\\COMP.bat");
		 
		 F.write("cd "+projet.getPath());
		 F.write("\njavac -d WEB-INF *.java");
		 F.close();

		 Runtime.getRuntime().exec(projet+"\\COMP.bat"); 
	  }catch(IOException e){}
	}
}