package CLIENTS;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class EmployeClient {
	
	public void DemandeConge(int nbr_jour_dem)throws IOException{
	
			Service ServicePersonnelService = new Service();
    		try{    
    			
           		Call appel = (Call)ServicePersonnelService.createCall();
    			String url="http://localhost:8080/WebServiceCho/services/ServicePersonnel";
    			appel.setTargetEndpointAddress(new URL(url));
    			appel.setOperationName(new QName("ServicePersonnel","VerifiConge"));
    			Object res = appel.invoke( new Object[]{15}  );
    			System.out.println(res);	
    			
    		}
	   	
	   		catch (ServiceException e) {
    			e.printStackTrace();
    			
    		}catch (MalformedURLException e) {
    			e.printStackTrace();
    		}catch (RemoteException e) {
    			e.printStackTrace();
    		}
    	    }
}