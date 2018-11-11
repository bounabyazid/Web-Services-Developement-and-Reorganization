package ANALYSEUR;
import java.util.List;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Statement;

import INTERFACE.FORM;


public class METHODES
{
	public FORM Form = null;
	public METHODES(CLASSE classe,AbstractTypeDeclaration type,FORM Form) 
	{   
		  this.Form = Form; 
	      List<BodyDeclaration> bodies = type.bodyDeclarations();
	      for (BodyDeclaration body : bodies)
	      {
	       //-----------------------ATTRIBUTS-------------------------
	       /*if (body.getNodeType() == ASTNode.FIELD_DECLARATION)
	       {
	    	  FieldDeclaration F = (FieldDeclaration)body;   	  
	    	  Object LF[] = F.fragments().toArray();
	    	  for(int k=0;k<LF.length;k++)
	    	   System.out.println(LF[k].toString());		     
	       }*/
	       //-----------------------METHODES--------------------------
	       if (body.getNodeType() == ASTNode.METHOD_DECLARATION)
	       {
	    	 MethodDeclaration method = (MethodDeclaration)body;
             classe.methodes.add(new METHODE(classe,method.getName().getFullyQualifiedName(), method.getReturnType2().toString(),Form));
             for(int j=0;j<method.parameters().size();j++)
             {
            	String Type = method.parameters().get(j).toString().substring(0,method.parameters().get(j).toString().indexOf(" "));
                String NOM = method.parameters().get(j).toString().substring(method.parameters().get(j).toString().indexOf(" ")+1,method.parameters().get(j).toString().length());
            	classe.methodes.lastElement().Mpara.add(new VD(NOM,Type));
             }             
           }
	      }
	      int i=0;
	      for (BodyDeclaration body : bodies)
	      {
	       if (body.getNodeType() == ASTNode.METHOD_DECLARATION)
	       {
	    	 MethodDeclaration method = (MethodDeclaration)body;
	    	 Block block = method.getBody();
             List<Statement> STS = block.statements();
             Form.list2.add("Methode "+classe.methodes.elementAt(i).Mnom);
             Form.list2.add("=====================\n\n");
             BLOCK BL = new BLOCK(1,classe.methodes.elementAt(i),STS,Form,"","");
             i++;
	       }
	      }
    }
}
