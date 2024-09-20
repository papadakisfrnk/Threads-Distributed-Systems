/*
Papadakis Fragkiskos
321/2017147
*/
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Chef extends Thread {

    private String nameChef;

    public Chef(String nameChef) {

        this.nameChef = nameChef;

    }

    @Override
    public void run() { //override methodo run poy kalite me kathe start tou thread
        Socket sock = null;
        try {
            sock = new Socket("127.0.0.1", 5555); //dimiourgia socket me localurl kai port 5555
            //diamorfosi paketou gia lipsi kai apostoli minimatos
            BufferedReader instream = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            BufferedWriter outstream = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));

            for (int i = 1; i <= 10; i++) {
                if (nameChef.equals("Chef 1")) { //ean kalite o prwtos chef
                    outstream.write("Prepare the Fishes" + "\n");
                    outstream.flush();
                    outstream.write((int) (Math.random() * 10) + "\n");
                    outstream.flush();
                } else if (nameChef.equals("Chef 2")) { //ean kalite o deuteros chef
                    outstream.write("Prepare the Appetizers" + "\n");
                    outstream.flush();
                    outstream.write((int) (Math.random() * 10) + "\n"); //random parraggelia mexri 10
                    outstream.flush();
                } else if (nameChef.equals("Chef 3")) {
                    outstream.write("Prepare the Meat" + "\n");
                    outstream.flush();
                    outstream.write((int) (Math.random() * 10) + "\n");
                    outstream.flush();
                }
                System.out.println("#" + this.nameChef + " put ");
                try {
                    sleep((int) (Math.random() * 10000)); //tuxaios xronos kathisterisis
                } catch (InterruptedException ex) {
                }

            }
        } catch (IOException ex) {
            System.out.println("I/O Error..");
        } finally {
            try {
                sock.close();
            } catch (IOException ex) {
                Logger.getLogger(Chef.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
