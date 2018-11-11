package ANALYSEUR;

public class MI 
{
	public String MName = null,MClass = null,Categorie = null,arbre = null;
	public String Couplage = null;
	public double NbInvoc = 0.;
	public MI(String MName,String MClass,double NbInvoc,String Categorie,String arbre) 
	{
     this.MName = MName;
     this.MClass = MClass;
     this.NbInvoc = NbInvoc;
     this.Categorie = Categorie;
     this.arbre = arbre;
	}
}
