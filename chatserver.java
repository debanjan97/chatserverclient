import java.io.*;
import java.net.*;

class Receive extends Thread{
  Socket connectionSocket;
  BufferedReader inFromClient;
  public Receive(Socket s)throws IOException{
    connectionSocket = s;
    inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
  }
  public void run()
  {
    try{
      while(true)
        System.out.println("Client>> "+inFromClient.readLine()+"\n");
    }catch(Exception e){}
  }
}
class Send extends Thread{
  Socket connectionSocket;
  DataOutputStream outToClient;
  BufferedReader br;
  public Send(Socket s)throws IOException{
    connectionSocket = s;
    outToClient = new DataOutputStream(connectionSocket.getOutputStream());
    br = new BufferedReader(new InputStreamReader(System.in));
  }
  public void run()
  {
    try{
      while(true){
        outToClient.writeBytes(br.readLine()+"\n");
        System.out.println();
      }
    }catch(Exception e){}
  }
}
public class chatserver{
  public static void main(String[] args) throws IOException {
    ServerSocket skt = new ServerSocket(60789);
    Socket connectionSocket = skt.accept();
    Receive r = new Receive(connectionSocket);
    Send s = new Send(connectionSocket);
    r.start();
    s.start();
  }
}
