package CLIENTS;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class ServicePersonnel {
	
	public  boolean VerifiConge(int nbr_jour_dem)
	{   
		int nbjr = 0,co=20;
		int  nbr_employeur = 15;
		
		if( (nbr_jour_dem < 52)&&(nbr_employeur>15))
		{ 
			 nbjr = 52-co;
			
		    if (nbr_jour_dem <= nbjr)
		      {
		    	Service ServiceDirectionService = new Service();
	    		try{    
	           		Call appel = (Call)ServiceDirectionService.createCall();
	           		for(int i=1;i<=125;i++)
	           		{
	    			
	    			String url="http://localhost:8080/WebServiceCho/services/ServiceDirection";
	    			appel.setTargetEndpointAddress(new URL(url));
	    			appel.setOperationName(new QName("ServiceDirection","Validation"));
	    			Object res = appel.invoke( new Object[]{}  );
	           		
	    			System.out.println(res);	
	           		}
	    		}catch (ServiceException e) {
	    			e.printStackTrace();
	    			
	    		}catch (MalformedURLException e) {
	    			e.printStackTrace();
	    		}catch (RemoteException e) {
	    			e.printStackTrace();
	    		}
	    	    
		    

	        	}
		    else 
			    return(false);
				    }
		else
			return(false);
		return false;
	}
}