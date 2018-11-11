package MODIFFICATEUR;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jface.text.Document;

import ANALYSEUR.CLASSE;

public class Modifficateur 
{
	File[] files = null;
	File file = null,projet;
	String Source = null;
	CompilationUnit cu = null;
	Document document = null;
	
	public void Initialisation(File file)
	{
		 try {Source = FileUtils.readFileToString(file);} 
		 catch (IOException e1) {e1.printStackTrace();}   
		 document = new Document(Source);
		 ASTParser parser = ASTParser.newParser(AST.JLS3);
	   	 parser.setSource(document.get().toCharArray());
	   	 cu = (CompilationUnit) parser.createAST(null);
	 	 cu.recordModifications();	
	}
	
	public Modifficateur(File projet,Vector<CLASSE> LISTE_CLASSES)
	{		 
	  this.projet = projet;
	  MODIFICATEUR Form = new MODIFICATEUR();
	  files = projet.listFiles();
	  for(int i=0;i<LISTE_CLASSES.size();i++)
	  {
	    for(int l=0;l<files.length;l++)	
		 if(files[l].getName().matches(LISTE_CLASSES.elementAt(i).Classnom+".java"))
 	     {
		  file = files[l];
		  
		  Initialisation(file);
		  
		  Form.list1.add(cu.toString());  
		  Form.list1.add("\n________________________________________________________________\n");
		  
		  ChangerPackageName(cu, "SERVICES");
	      break;
		 }
	    for(int j=0;j<LISTE_CLASSES.elementAt(i).methodes.size();j++)
		for(int k=0;k<LISTE_CLASSES.elementAt(i).methodes.elementAt(j).MIS.size();k++)
		{
	     if(LISTE_CLASSES.elementAt(i).methodes.elementAt(j).MIS.elementAt(k).Couplage.matches("Fort")&&!LISTE_CLASSES.elementAt(i).methodes.elementAt(j).MIS.elementAt(k).MClass.matches(LISTE_CLASSES.elementAt(i).Classnom))	
	     {
	       AjouterMethode(cu, projet, LISTE_CLASSES.elementAt(i).methodes.elementAt(j).MIS.elementAt(k).MClass, LISTE_CLASSES.elementAt(i).methodes.elementAt(j).MIS.elementAt(k).MName);
	       for(int l=0;l<LISTE_CLASSES.elementAt(i).methodes.elementAt(j).VARS.size();l++)	
	    	if(LISTE_CLASSES.elementAt(i).methodes.elementAt(j).VARS.elementAt(l).Vtype.matches(LISTE_CLASSES.elementAt(i).methodes.elementAt(j).MIS.elementAt(k).MClass))
	         ElliminerVar(cu,LISTE_CLASSES.elementAt(i).methodes.elementAt(j).Mnom,LISTE_CLASSES.elementAt(i).methodes.elementAt(j).VARS.elementAt(l).Vnom);
	       SupprimerDeclaration(cu, LISTE_CLASSES.elementAt(i).methodes.elementAt(j).Mnom,LISTE_CLASSES.elementAt(i).methodes.elementAt(j).MIS.elementAt(k).MClass);
	     }
		}
       
	    for(int j=0;j<LISTE_CLASSES.elementAt(i).methodes.size();j++)
		 for(int k=0;k<LISTE_CLASSES.elementAt(i).methodes.elementAt(j).MIS.size();k++)
		  if(!LISTE_CLASSES.elementAt(i).methodes.elementAt(j).MIS.elementAt(k).Couplage.matches("Fort")&&!LISTE_CLASSES.elementAt(i).methodes.elementAt(j).MIS.elementAt(k).MClass.matches(LISTE_CLASSES.elementAt(i).Classnom))	
		  {
		   if(MethodExiste(cu,LISTE_CLASSES.elementAt(i).methodes.elementAt(j).MIS.elementAt(k).MName))
		   {
		    for(int l=0;l<LISTE_CLASSES.elementAt(i).methodes.elementAt(j).VARS.size();l++)	
		   	 if(LISTE_CLASSES.elementAt(i).methodes.elementAt(j).VARS.elementAt(l).Vtype.matches(LISTE_CLASSES.elementAt(i).methodes.elementAt(j).MIS.elementAt(k).MClass))
		      ElliminerVar(cu,LISTE_CLASSES.elementAt(i).methodes.elementAt(j).Mnom,LISTE_CLASSES.elementAt(i).methodes.elementAt(j).VARS.elementAt(l).Vnom);
		     SupprimerDeclaration(cu, LISTE_CLASSES.elementAt(i).methodes.elementAt(j).Mnom,LISTE_CLASSES.elementAt(i).methodes.elementAt(j).MIS.elementAt(k).MClass);
		   }
		  }
        ModiffierFichier(file);
        Form.list1.add(cu.toString());  
		Form.list1.add("\n________________________________________________________________\n");
	   }
	}
	
	//===============================================================================================
	
	public boolean MethodExiste(CompilationUnit cu,String MethodeName)
	{
		List<AbstractTypeDeclaration> types = cu.types();
	    for (AbstractTypeDeclaration type : types)
	     if (type.getNodeType() == ASTNode.TYPE_DECLARATION)	
	     {
	       List<BodyDeclaration> bodies = type.bodyDeclarations();
	  	   for (BodyDeclaration body : bodies)
	  	    if (body.getNodeType() == ASTNode.METHOD_DECLARATION)
	  	    {
	  	      MethodDeclaration method = (MethodDeclaration)body;
	  	      if(method.getName().getFullyQualifiedName().matches(MethodeName))
	  	       return true;
	  	    }
	     }
	    return false;
	}
	
	//===============================================================================================

	public void ModiffierFichier(File file)
	{
	   file.setWritable(true);
		try {
			 FileWriter FW = new FileWriter(projet/*"C:\\Users\\yazid\\Desktop\\BOUNAB\\ASTWEB\\src\\SERVICES"*/+"\\"+file.getName());
			 FW.write(cu.toString());
			 FW.close();
		 } catch (FileNotFoundException e) {} catch (IOException e) {} 
	}

	//===============================================================================================
	
	public AbstractTypeDeclaration RechercheASTclasse(File projet,String className)
	{
	  File[] files = projet.listFiles();
	  for(int i=0;i<files.length;i++)
	  {
	    String Source = null;
		try {Source = FileUtils.readFileToString(files[i]);} 
		catch (IOException e1) {e1.printStackTrace();}
	    Document document = new Document(Source);
	    ASTParser parser = ASTParser.newParser(AST.JLS3);
	    parser.setSource(document.get().toCharArray());
	    final CompilationUnit unit = (CompilationUnit)parser.createAST(null);
	    
	    List<AbstractTypeDeclaration> types = unit.types();
	    for (AbstractTypeDeclaration type : types)
	     if ((type.getNodeType() == ASTNode.TYPE_DECLARATION)&&(type.getName().toString().matches(className)))
	     {
	      return type; 
	     }  
	  }
	  return null;	
	}
	
	//===============================================================================================
	
	public MethodDeclaration RechercheASTmethode(AbstractTypeDeclaration Class,String MethodeName)
	{
	   List<BodyDeclaration> bodies = Class.bodyDeclarations();
	   for (BodyDeclaration body : bodies)
	    if (body.getNodeType() == ASTNode.METHOD_DECLARATION)
	    {
	      MethodDeclaration method = (MethodDeclaration)body;
	      if(method.getName().getFullyQualifiedName().matches(MethodeName))
	       return method;
	    }
	  return null;	
	}
	
	//===============================================================================================
	
	public void ChangerPackageName(CompilationUnit cu,String Name)
	{
	   AST ast = cu.getAST();
       PackageDeclaration pd = cu.getPackage();
 	   pd.setName(ast.newName(Name));	
	}
		
	//===============================================================================================
	
	public void AjouterMethode(CompilationUnit cu, File projet, String ClassName, String MethodName)
	{
		AST ast = cu.getAST();
	    ASTRewrite astRewrite= ASTRewrite.create(ast);
	        
	    MethodDeclaration m = RechercheASTmethode(RechercheASTclasse(projet,ClassName),MethodName);
	    MethodDeclaration method = (MethodDeclaration) ASTNode.copySubtree(ast, m);

	    List bodyDecls = ((TypeDeclaration)cu.types().get(0)).bodyDeclarations();
	    bodyDecls.add(method);
	    ListRewrite lrw = astRewrite.getListRewrite(((TypeDeclaration)cu.types().get(0)), TypeDeclaration.BODY_DECLARATIONS_PROPERTY);
	    lrw.insertLast(method, null);
	}
	
	//===============================================================================================
	
	public void SupprimerDeclaration(CompilationUnit cu, String MethodName , String instance)
	{  
	    List<AbstractTypeDeclaration> types = cu.types();
	    for (AbstractTypeDeclaration type : types)
	     if (type.getNodeType() == ASTNode.TYPE_DECLARATION)
	     {
	      List<BodyDeclaration> bodies = type.bodyDeclarations();
		  for (BodyDeclaration body : bodies)
		   if (body.getNodeType() == ASTNode.METHOD_DECLARATION)
		   {
	         MethodDeclaration method = (MethodDeclaration)body;
	         if(method.getName().toString().matches(MethodName))
	         {
		      List<Statement> stat = method.getBody().statements();
	          for ( Statement stmt : stat) 
			   if(stmt instanceof VariableDeclarationStatement)
			   {
				List<VariableDeclarationFragment> ff = ((VariableDeclarationStatement) stmt).fragments();
				 for(VariableDeclarationFragment f_p : ff)
			      if(f_p.getParent().toString().contains(instance))
		           f_p.getParent().delete();
			   }
	         }
		   }
		 }
	}
	
	//===============================================================================================
		
	public void ElliminerVar(CompilationUnit cu, String MethodName , String var)
	{
		 List<AbstractTypeDeclaration> types = cu.types();
		    for (AbstractTypeDeclaration type : types)
		     if (type.getNodeType() == ASTNode.TYPE_DECLARATION)
		     {
		      List<BodyDeclaration> bodies = type.bodyDeclarations();
			  for (BodyDeclaration body : bodies)
			   if (body.getNodeType() == ASTNode.METHOD_DECLARATION)
			   {
		         MethodDeclaration method = (MethodDeclaration)body;
		         if(method.getName().toString().matches(MethodName))
		         {
			      List<Statement> stat = method.getBody().statements();			      
			      BLOCK b = new BLOCK(cu,stat,var); 
		         }
			   }
			 }		
	}	
}
