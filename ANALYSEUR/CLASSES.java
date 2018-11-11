package ANALYSEUR;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jface.text.Document;
import INTERFACE.FORM;

public class CLASSES
{
    public Vector<CLASSE> LISTE_CLASSES = new Vector<CLASSE>();
    File[] files = null;
    public File projet = null;
	public CLASSES(File projet,FORM Form) 
	{
		  this.projet = projet;
		  files = projet.listFiles();
		  for(int i=0;i<files.length;i++)
		  {
			if(!files[i].isDirectory())
			{
		    String source = null;
			try {source = FileUtils.readFileToString(files[i]);} 
			catch (IOException e1) {e1.printStackTrace();}
		    Document document = new Document(source);
		    ASTParser parser = ASTParser.newParser(AST.JLS3);
		    
		    parser.setSource(document.get().toCharArray());
		    final CompilationUnit unit = (CompilationUnit)parser.createAST(null);
		 
		    List<AbstractTypeDeclaration> types = unit.types();
		    for (AbstractTypeDeclaration type : types)
		     if (type.getNodeType() == ASTNode.TYPE_DECLARATION)
		     {
		      LISTE_CLASSES.add(new CLASSE(LISTE_CLASSES,type.getName().toString(),Form));   
		      LISTE_CLASSES.lastElement().SetMethodes(types);
		     }
		    }
		  }
		  this.afficher_classes();
	}
	//-------------------------------------------------------
	public boolean ClassExiste(String S)
	{
	 for(int j=0;j<LISTE_CLASSES.size();j++)
	 {
      if(LISTE_CLASSES.get(j).Classnom.matches(S))
       return true;
	 }
	 return false;
	}
	//-------------------------------------------------------
	public void afficher_classes()
	{
	 for(int i=0;i<LISTE_CLASSES.size();i++)
	 {
	  LISTE_CLASSES.get(i).Repetition();
	  LISTE_CLASSES.get(i).Afficher_classe();	
	 }
	}
}
