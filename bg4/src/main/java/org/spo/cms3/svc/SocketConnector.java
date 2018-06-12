package org.spo.cms3.svc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.spo.cms3.model.QMessage;
import org.springframework.stereotype.Component;
@Component
public class SocketConnector {

	
	private String response;
	
	String domainRequest= "";
	String replyMessageText= "";		
	
	
	
	JAXBContext jaxbContext ;
	
	{
		try{
		
		jaxbContext = JAXBContext.newInstance(QMessage.class);
		}catch(Exception e){
			
		}
	}
	public  String getResponse( QMessage domainMessage) throws Exception{
		Writer writer = new StringWriter();
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();		
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(domainMessage, writer);
		domainRequest=writer.toString();
		
		  InetAddress host = InetAddress.getLocalHost();
	        Socket socket = null;
	        ObjectOutputStream oos = null;
	        ObjectInputStream ois = null;
	       
	            //establish socket connection to server
	            socket = new Socket(host.getHostName(), 8087);
	            //write to socket using ObjectOutputStream
	            oos = new ObjectOutputStream(socket.getOutputStream());
	           
	           oos.writeObject(domainRequest);
	           
	            //read the server response message
	            ois = new ObjectInputStream(socket.getInputStream());
	            replyMessageText = (String) ois.readObject();
	            System.out.println("Message: " + replyMessageText);
	            //close resources
	            ois.close();
	            oos.close();
	         socket.close();
	    

		System.out.println("Just said hello to:" + replyMessageText);

			return replyMessageText;

		
	}
	
	

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
