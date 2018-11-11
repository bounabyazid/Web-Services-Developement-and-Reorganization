package ANALYSEUR;

import java.util.List;
import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;

import INTERFACE.FORM;

public class CLASSE 
{
	public String Classnom = null;
	public Vector<CLASSE> LISTE_CLASSES = null;
	public Vector<METHODE> methodes = new Vector<METHODE>();
	FORM Form = null;
	
	public CLASSE(Vector<CLASSE> LISTE_CLASSES,String Classnom,FORM Form)
	{
	 this.LISTE_CLASSES = LISTE_CLASSES;
	 this.Classnom = Classnom;
	 this.Form = Form;
	}
	
	public void SetMethodes(List<AbstractTypeDeclaration> types) 
	{
	 for (AbstractTypeDeclaration type : types)
	  if (type.getNodeType() == ASTNode.TYPE_DECLARATION) 
	  {
		METHODES methode = new METHODES(this,type,Form);
	  }
	}

	public boolean ClassExiste(String S)
	{
	 for(int j=0;j<LISTE_CLASSES.size();j++)
	 {
      if(LISTE_CLASSES.get(j).Classnom.equalsIgnoreCase(S));
	   return true;
	 }
	 return false;
	}
	
	public void Afficher_classe()
	{
	 Form.list.add("classe :"+Classnom);
	 Form.list.add("______________________");
	 Form.model1.addRow(new Object[] {Classnom});
	 
	 for(int i=0;i<methodes.size();i++)
	  methodes.get(i).Afficher_Methode();
	}
	
	public boolean Mexiste(String S)
	{
	 for(int j=0;j<methodes.size();j++)
	 {
      if(S.contains(methodes.get(j).Mnom));
	   return true;
	 }
	 return false;
	}
	
	public void Repetition()
	{
	 for(int i=1;i<methodes.size();i++)
      for(int j=0;j<methodes.elementAt(i).Mpara.size();j++)
      {
    	VD var = methodes.elementAt(i-1).MparaExist(methodes.elementAt(i).Mpara.elementAt(j).Vnom);
        if(var !=null)
         methodes.elementAt(i).Mpara.elementAt(j).Repetition = var.Repetition+1; 
      }
	} 
}
