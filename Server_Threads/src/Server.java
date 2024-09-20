/*
Papadakis Fragkiskos
321/2017147
*/
import java.io.*;
import java.net.*;

public class Server {
//ulopoisi socket server me tin xrisi nimatwn
    public static void main(String[] args) {
        //antikeimena typou order
        Order psaria = new Order("fishes");
        Order mezedes = new Order("appetizers");
        Order meat = new Order("meat");
        try {
            InetAddress address = InetAddress.getLocalHost();
            Thread echothread; //dimiourgia antikeimenou thread
            
            ServerSocket server = new ServerSocket(5555, 0); //dimiorgia server socket me port 5555
            System.out.println("Waiting Incoming Connection...");
            System.out.println("Local Address : " + server.getInetAddress() + " Port: " + server.getLocalPort());

            while (true) { //anoixtos server gia pollous client
                Socket sock = server.accept();
                System.out.println(sock.toString());
                System.out.println("Connection from " + sock.getInetAddress() + " and port:" + sock.getPort() + "\n");
                echothread = new Thread(new EchoThread(sock,psaria,mezedes,meat));
                echothread.start();

            }

        } catch (IOException ex) {
            System.out.println("Error during I/O");
            ex.getMessage();
            ex.printStackTrace();

        }

    }
}
