package MODIFFICATEUR;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.jdt.*;
import org.eclipse.jface.text.projection.Fragment;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jdt.internal.core.search.matching.VariableLocator;

import INTERFACE.FORM;

public class BLOCK 
{
	public BLOCK(CompilationUnit cu,List<Statement> stat,String var) 
	{
		for ( Statement stmt : stat) 
		{  		
			  //-----------------For-----------------
			  if (stmt instanceof ForStatement)
			  {
			    ForStatement fs = (ForStatement) stmt;
			    Block b =(Block) fs.getBody();
			    BLOCK BL = new BLOCK(cu,b.statements(),var);
			  }
			  //-----------------while-----------------
			  if (stmt instanceof WhileStatement)
			  {
	            WhileStatement fs = (WhileStatement) stmt;
	            Block b =(Block) fs.getBody();
			    BLOCK BL = new BLOCK(cu,b.statements(),var);
			  }
			  //-----------------Do-----------------
			  if (stmt instanceof DoStatement)
			  {
			    DoStatement fs = (DoStatement) stmt; 
			    Block b =(Block) fs.getBody();
			    BLOCK BL = new BLOCK(cu,b.statements(),var);
			  }
			  //---------------ExpressionStatement-------------------
			  if(stmt instanceof ExpressionStatement)
			  {	  
				  ExpressionStatement ES = (ExpressionStatement)stmt;	
				  EXPRESSION_STMT(cu,ES,var);
			  } 
			  //--------------Return Statment------------------------
			  if(stmt instanceof ReturnStatement)
			  {	  
				ReturnStatement s = (ReturnStatement) stmt;
				EXPRESSION(cu,s.getExpression(),var);	 
			  }
//------------------------------------------------------IF-----------------------------------------------------------------
			  if (stmt instanceof IfStatement)
			  {
	      	    //-----------------Block-----------------
		      	Statement IfStat = ((IfStatement)stmt).getThenStatement();
				if (IfStat instanceof Block)
				{
				 List<Statement> Stmt = (List<Statement>) ((Block)IfStat).statements();
 	      	     BLOCK BL = new BLOCK(cu,Stmt,var); 	      	     
				}
				if (IfStat instanceof ExpressionStatement)
				{
				 ExpressionStatement ES = (ExpressionStatement)IfStat;					 
				 EXPRESSION_STMT(cu,ES,var);	 
				}
				//-----------------Else-----------------			
				Statement ElseStat = ((IfStatement)stmt).getElseStatement();
				if (ElseStat instanceof Block)
				{
				 List<Statement> Stmt = (List<Statement>) ((Block)ElseStat).statements();
 			     BLOCK BL = new BLOCK(cu,Stmt,var); 	      	     
				}
				if (ElseStat instanceof ExpressionStatement)
				{
				 ExpressionStatement ES = (ExpressionStatement)ElseStat;					 
				 EXPRESSION_STMT(cu,ES,var);	 
				}
	      	  }
//-----------------------------------------------------------------------------------------------------------
		}
	}

	public void EXPRESSION_STMT(CompilationUnit cu,ExpressionStatement ES,String var)
	{
		 ASTNode exp = ES.getExpression();
		
		 if(exp instanceof MethodInvocation)
		 {
			 MethodInvocation mi = (MethodInvocation)exp;
			 if(mi.toString().contains(var+"."))
			  mi.getExpression().delete();
		 }
		 if(exp instanceof Assignment)
		 {
			 Assignment as = (Assignment)exp;
			 org.eclipse.jdt.core.dom.Expression s = as.getRightHandSide();
			 if(s instanceof MethodInvocation)
			 {
				 MethodInvocation mi = (MethodInvocation)as.getRightHandSide();
				 if(mi.toString().contains(var+"."))
				  mi.getExpression().delete();
			 }			
		 }
	}
	public void EXPRESSION(CompilationUnit cu,Expression E,String var)
	{
		 ASTNode exp = E;
		
		 if(exp instanceof MethodInvocation)
		 {
			 MethodInvocation mi = (MethodInvocation)exp;
			 if(mi.toString().contains(var+"."))
			  mi.getExpression().delete();
		 }
		 if(exp instanceof Assignment)
		 {
			 Assignment as = (Assignment)exp;
			 org.eclipse.jdt.core.dom.Expression s = as.getRightHandSide();
			 if(s instanceof MethodInvocation)
			 {
				 MethodInvocation mi = (MethodInvocation)as.getRightHandSide();
				 if(mi.toString().contains(var+"."))
				  mi.getExpression().delete();
			 }			
		 }
	}
}