package Time;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class Server  {
	 public static void main(String []args) {
     	
     	try {
     		ServerSocket Serversk = new ServerSocket(6002);
     		Socket skServer = Serversk.accept();
	        	System.out.println("Connected....");
				DataInputStream inputServer = new DataInputStream(skServer.getInputStream());
				String n = inputServer.readLine();
				System.out.println("n nhan duoc la "+n);
				int so = Integer.parseInt(n);
				Calendar c = Calendar.getInstance();
				int a = 0;
				switch(so) {
				case 1: a = c.get(Calendar.DAY_OF_MONTH);
				break;
				case 2: a =  c.get(Calendar.MONTH);
				break;
				case 3: a =  c.get(Calendar.YEAR);
				break;
				}
				DataOutputStream outServer =new DataOutputStream(skServer.getOutputStream());
				outServer.writeBytes(String.valueOf(a)+"\ns");;
				outServer.flush();
				outServer.close();
				inputServer.close();
				skServer.close();
			    } catch (Exception e) {
				e.printStackTrace();
		        }
     	
     }
	
}
