import java.io.*;
import java.net.*;

class CReceive extends Thread{
  Socket connectionSocket;
  BufferedReader inFromClient;
  public CReceive(Socket s)throws IOException{
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
class CSend extends Thread{
  Socket connectionSocket;
  DataOutputStream outToClient;
  BufferedReader br;
  public CSend(Socket s)throws IOException{
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
public class chatclient{
  public static void main(String[] args) throws IOException {
    Socket connectionSocket = new Socket("localhost",60789);
    CReceive r = new CReceive(connectionSocket);
    CSend s = new CSend(connectionSocket);
    r.start();
    s.start();
  }
}
