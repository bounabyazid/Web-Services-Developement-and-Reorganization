package ANALYSEUR;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

import INTERFACE.FORM;

public class BLOCK 
{
	public BLOCK(double NbInvoc,METHODE methode, List<Statement> stat,FORM Form,String type,String arbre) 
	{
		for ( Statement stmt : stat) 
		{  		
			  //-----------------variable-------------
			  if(stmt instanceof VariableDeclarationStatement)
			  {
			   if(stmt!=null)
			   {
				VariableDeclaration fragment = (VariableDeclaration) ((VariableDeclarationStatement) stmt).fragments().get(0);
			    Form.list2.add("VariableDeclaration name : "+fragment.getName().toString());
			    if(fragment.getInitializer() instanceof ClassInstanceCreation)
				{
				 ClassInstanceCreation cl = (ClassInstanceCreation)fragment.getInitializer() ; 
				 if(cl!=null)
				  methode.VARS.add(new VD(fragment.getName().toString(),cl.getType().toString()));
				}
			   }
			  }
			  //---------------try/catch---------------
			  if (stmt instanceof TryStatement)
			  {
			    Form.list2.add("Try Statment");
			    TryStatement ts = (TryStatement) stmt;
			    arbre += "Try ";
			    Block b =(Block) ts.getBody();
			    BLOCK BL = new BLOCK(NbInvoc,methode, b.statements(),Form,type,arbre);
			  }
			  //-----------------For-----------------
			  if (stmt instanceof ForStatement)
			  {
			    Form.list2.add("For Statment");
			    ForStatement fs = (ForStatement) stmt;
			    arbre += "For ";
			    Block b =(Block) fs.getBody();
			    double nb = Double.parseDouble(fs.getExpression().toString().substring(fs.getExpression().toString().indexOf("=")+1,fs.getExpression().toString().length()));
			    BLOCK BL = new BLOCK(NbInvoc*nb,methode, b.statements(),Form,type,arbre);
			  }
			  //-----------------while-----------------
			  if (stmt instanceof WhileStatement)
			  {
	            Form.list2.add("While Statment");
	            WhileStatement fs = (WhileStatement) stmt;
	            Block b =(Block) fs.getBody();
	            arbre += "While ";
			    BLOCK BL = new BLOCK(NbInvoc,methode, b.statements(),Form,type,arbre);
			  }
			  //-----------------Do-----------------
			  if (stmt instanceof DoStatement)
			  {
			    //Form.list2.add("do Statment");
			    DoStatement fs = (DoStatement) stmt; 
			    Block b =(Block) fs.getBody();
			    arbre += "DoWhile ";
			    BLOCK BL = new BLOCK(NbInvoc,methode, b.statements(),Form,type,arbre);
			  }
			  //---------------ExpressionStatement-------------------
			  if(stmt instanceof ExpressionStatement)
			  {	  
				  ExpressionStatement ES = (ExpressionStatement)stmt;	
				  EXPRESSION_STMT(ES,NbInvoc,methode,Form,type,arbre);
			  } 
			  //--------------Return Statment------------------------
			  if(stmt instanceof ReturnStatement)
			  {	  
				arbre += "Return ";
	            Form.list2.add("Return Statment");
				ReturnStatement s = (ReturnStatement) stmt;
				EXPRESSION(s.getExpression(),NbInvoc,methode,Form,type,arbre);
			  }
//------------------------------------------------------IF-----------------------------------------------------------------
			  if (stmt instanceof IfStatement)
			  {
	      	    //-----------------Block-----------------
		      	Statement IfStat = ((IfStatement)stmt).getThenStatement();
				if (IfStat instanceof Block)
				{
				 List<Statement> Stmt = (List<Statement>) ((Block)IfStat).statements();
 	      	     //Form.list2.add("block if Statment "+Stmt);
 	      	     Form.list2.add("If NbInvoc = "+NbInvoc+"*0.5");
 	      	     arbre += "If ";
 	      	     BLOCK BL = new BLOCK(NbInvoc*(0.5),methode, Stmt,Form,type,arbre); 	      	     
				}
				if (IfStat instanceof ExpressionStatement)
				{
				 ExpressionStatement ES = (ExpressionStatement)IfStat;					 
				 EXPRESSION_STMT(ES,NbInvoc,methode,Form,type,arbre);	 
				}
				//-----------------Else-----------------			
				Statement ElseStat = ((IfStatement)stmt).getElseStatement();
				if (ElseStat instanceof Block)
				{
				 List<Statement> Stmt = (List<Statement>) ((Block)ElseStat).statements();
 	      	     // Form.list2.add("block else Statment "+Stmt);
 	      	     Form.list2.add("Else NbInvoc = "+NbInvoc+"*0.5");
 	             arbre += "Else ";
 			     BLOCK BL = new BLOCK(NbInvoc*(0.5),methode, Stmt,Form,type,arbre); 	      	     
				}
				if (ElseStat instanceof ExpressionStatement)
				{
				 ExpressionStatement ES = (ExpressionStatement)ElseStat;					 
				 EXPRESSION_STMT(ES,NbInvoc,methode,Form,type,arbre);	 
				}
	      	  }
//-----------------------------------------------------------------------------------------------------------
		}
	}

	public void EXPRESSION_STMT(ExpressionStatement ES,double NbInvoc,METHODE methode,FORM Form,String type,String arbre)
	{
		 ASTNode exp = ES.getExpression();
		
		 if(exp instanceof MethodInvocation)
		 {
			 MethodInvocation mi = (MethodInvocation)exp;

			   if(mi.getName().toString().matches("setOperationName"))
			   {
				  int i = mi.toString().indexOf("\"")+1;
				  int j = mi.toString().indexOf(",")-1;
				  int k = mi.toString().lastIndexOf("\"");
				  
			      Form.list2.add("Service "+mi.toString().substring(i,j)+" Opération invoquer "+mi.toString().substring(j+3,k)+" NbInvoc = "+NbInvoc);
						  
			      MI M = methode.MiExiste(mi.getName().toString());
			      if(M==null)
			       methode.MIS.add(new MI(mi.toString().substring(j+3,k),mi.toString().substring(i,j),NbInvoc,type,arbre));
			      else
			      {
				    M.NbInvoc += NbInvoc;
				    M.arbre += arbre;
			      }
			   }
		 }
	}
	public void EXPRESSION(Expression E,double NbInvoc,METHODE methode,FORM Form,String type,String arbre)
	{
		 ASTNode exp = E;
		 if(exp instanceof MethodInvocation)
		 {
		   MethodInvocation mi = (MethodInvocation)exp;

		   if(mi.getName().toString().matches("setOperationName"))
		   {
			
			  int i = mi.toString().indexOf("\"")+1;
			  int j = mi.toString().indexOf(",")-1;
			  int k = mi.toString().lastIndexOf("\"");
			  
		      //Form.list2.add("MethodInvocation "+mi.getName().getIdentifier()+" name "+mi.getName()+" NbInvoc = "+NbInvoc);
		      Form.list2.add("Service "+mi.toString().substring(i,j)+" Opération invoquer "+mi.toString().substring(j+3,k)+" NbInvoc = "+NbInvoc);
					  
		      MI M = methode.MiExiste(mi.getName().toString());
		      if(M==null)
		       methode.MIS.add(new MI(mi.toString().substring(j+3,k),mi.toString().substring(i,j),NbInvoc,type,arbre));
		      else
		      {
			    M.NbInvoc += NbInvoc;
			    M.arbre += arbre;
		      }

		   }
		 }
		  
	}
}