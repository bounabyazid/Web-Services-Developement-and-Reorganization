package CLIENTS;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.sun.org.apache.xerces.internal.util.URI.MalformedURIException;

public class ClientPrincipal {
	public static void main(String[] args) throws MalformedURLException, MalformedURIException{
		
		Service EmployeClientService = new Service();
   		try{    
			
       		Call appel = (Call)EmployeClientService.createCall();
			
			String url="http://localhost:8080/WebServiceCho/services/EmployeClient";
			appel.setTargetEndpointAddress(new URL(url));
			appel.setOperationName(new QName("EmployeClient","DemandeConge"));
			Object res = appel.invoke( new Object[]{15}  );
			System.out.println(res);	
			
		}catch (ServiceException e) {
			e.printStackTrace();
			
		}catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (RemoteException e) {
			e.printStackTrace();
		}
	}  
}
