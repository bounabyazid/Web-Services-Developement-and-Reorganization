package ANALYSEUR;

import java.util.Vector;
import INTERFACE.FORM;

public class METHODE
{
	public String Mnom = null,Type = null;
	public CLASSE classMere = null;
	public Vector<VD> Mpara = new Vector<VD>();
	public Vector<VD> VARS = new Vector<VD>();
	public Vector<MI> MIS = new Vector<MI>();
	FORM Form = null;

	public METHODE(CLASSE classMere,String Mnom,String Type,FORM Form) 
	{
	 this.classMere = classMere;
	 this.Mnom = Mnom;
	 this.Type = Type;
	 this.Form = Form;
	}
	
	public boolean MethodeExiste(String S)
	{
	 for(int j=0;j<classMere.methodes.size();j++)
	 {
      if(S.equalsIgnoreCase(classMere.methodes.get(j).Mnom));
	   return true;
	 }
	 return false;
	}
	
	public boolean VDisClass(String MiExpression)
	{
	  for(int i=0;i<Mpara.size();i++)
	   if(MiExpression.contains(Mpara.get(i).Vtype))
	    return true;
		 
	  for(int i=0;i<VARS.size();i++)
	   if(MiExpression.contains(VARS.get(i).Vtype))
		return true;
	 return false;
	}
	
	public void Afficher_Methode()
	{
	 //System.out.println(Mnom+" : "+Type);
	 Form.list.add(Mnom+" : "+Type);
	 for(int i=0;i<Mpara.size();i++)
	  //System.out.println("p"+i+" : "+Mpara.get(i).Vnom+" "+Mpara.get(i).Vtype);
	  Form.list.add("p"+i+" : "+Mpara.get(i).Vnom+" "+Mpara.get(i).Vtype+" "+Mpara.get(i).Repetition);
	 for(int i=0;i<VARS.size();i++)
	  //System.out.println("V"+i+" : "+VARS.get(i).Vnom+" "+VARS.get(i).Vtype);
	  Form.list.add("V"+i+" : "+VARS.get(i).Vnom+" "+VARS.get(i).Vtype);
	}
	
	public VD GetVar(String Var)
	{
	 for(int i=0;i<VARS.size();i++)
	  if(VARS.get(i).Vnom.matches(Var))
	   return VARS.get(i);	
	 return null;
	}
	
	public MI MiExiste(String MName)
	{
	 for(int i=0;i<MIS.size();i++)
	  if(MIS.elementAt(i).MName.matches(MName))
	   return MIS.elementAt(i);
	 return null;	
	}
	public void Couplage()
	{
	 for(int i=0;i<MIS.size();i++)
	 {
	  if(MIS.elementAt(i).NbInvoc<=Double.parseDouble(Form.couplage.textField3.getText()))
	   MIS.elementAt(i).Couplage = "Faible";
	  else
	  {
	   if(MIS.elementAt(i).NbInvoc<=Double.parseDouble(Form.couplage.textField2.getText()))
	    MIS.elementAt(i).Couplage = "Moyen";
	   else
	    if(MIS.elementAt(i).NbInvoc>=Double.parseDouble(Form.couplage.textField1.getText()))
         MIS.elementAt(i).Couplage = "Fort";
	  }
	 }
	}
	public void CATEGORIE()
	{
	 for(int i=0;i<MIS.size();i++)
	 {
	  if(MIS.elementAt(i).arbre!=null)
	  {
	   int j=0,k = MIS.elementAt(i).arbre.indexOf(' ',0),N = 0;
	   while(j<MIS.elementAt(i).arbre.length())
	   {
	    String S = MIS.elementAt(i).arbre.substring(j, k);
	    if(S.matches("For")||S.matches("While")||S.matches("DoWhile")) 
		 N++;
	    j=k+1;
	    k=MIS.elementAt(i).arbre.indexOf(' ', j);
	   }
	   if(N>=2)
	    MIS.elementAt(i).Categorie = "Complexe";
	   else
	    MIS.elementAt(i).Categorie = "Simple";
	  }
	  else
	   MIS.elementAt(i).Categorie = "Simple";
	 }
	}
	
	public VD MparaExist(String var)
	{
	   for(int j=0;j<Mpara.size();j++)
		if(Mpara.elementAt(j).Vnom.matches(var))
		  return Mpara.elementAt(j);     
		return null;	
	}
}
