package GENERATEUR_WS;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import com.sun.org.apache.xerces.internal.util.URI.MalformedURIException;
public class Client {
  public static void main(  String[] args) throws MalformedURLException, MalformedURIException {
    Service ConvertService=new Service();
    try {
      Call appel=(Call)ConvertService.createCall();
      String url="http://localhost:8080/axis2/services/BONJOUR?wsdl";
      appel.setTargetEndpointAddress(new URL(url));
      appel.setOperationName(new QName("http://SERVICES","NINJA"));
      Object res=appel.invoke(new Object[]{/*new Double(100)*/});
      System.out.println(res);
    }
 catch (    ServiceException e) {
      e.printStackTrace();
    }
catch (    MalformedURLException e) {
      e.printStackTrace();
    }
catch (    RemoteException e) {
      e.printStackTrace();
    }
  }
}
