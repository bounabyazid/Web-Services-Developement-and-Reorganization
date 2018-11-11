package ANALYSEUR;

public class VD 
{
	public String Vnom = null;
	public String Vtype = null;
	public int Repetition = 0;
	public VD(String Vnom,String Vtype) 
	{
	 this.Vnom = Vnom;
	 this.Vtype = Vtype;
	}
	public void SetVnom(String Vnom)
	{
		this.Vnom = Vnom;	
	}
	public void SetVtype(String Vtype)
	{
		this.Vtype = Vtype;	
	}
	public String GetVnom()
	{
	   return Vnom;	
	}
	public String GetVtype()
	{
	   return Vtype;	
	}
}
