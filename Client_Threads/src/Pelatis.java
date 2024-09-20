/*
Papadakis Fragkiskos
321/2017147
*/
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pelatis extends Thread {

    private String value;

    public Pelatis(String value) {

        this.value = value;
    }

    @Override
    public void run() {

        Socket sock = null;
        try {
            sock = new Socket("127.0.0.1", 5555);
            //diamorfosi paketou gia lipsi kai apostoli minimatos
            BufferedReader instream = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            BufferedWriter outstream = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));

            for (int i = 1; i <= 10; i++) {

                outstream.write(value + "\n");
                outstream.flush();
                int cnt = (int) (Math.random() * (5 + 1)); //tyxaios pou zitaei gia paraggelia apo 1-5
                outstream.write(cnt + "\n");
                outstream.flush();

                try {
                    sleep((int) (Math.random() * 10000)); //tyxaios xronos kathysterisis gia paraggelia 
                } catch (InterruptedException ex) {
                }
                System.out.println("#" + this.value + " served!");

            }
        } catch (IOException ex) {
            System.out.println("Connection is Closed..");

        } finally {
            try {
                sock.close();
            } catch (IOException ex) {
                Logger.getLogger(Pelatis.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
