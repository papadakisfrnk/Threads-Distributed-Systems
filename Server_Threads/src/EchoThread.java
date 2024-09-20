/*
Papadakis Fragkiskos
321/2017147
*/
import java.net.Socket;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoThread implements Runnable //klasi echothread pou ulopoiei tin diepafi Runnable
//gia tin xrisi twn Thread
{

    private Socket incoming;
    private Order fishes;
    private Order appetizers;
    private Order meat;

    public EchoThread(Socket incoming, Order fishes, Order mezedes, Order meat) {
        this.incoming = incoming;
        this.fishes = fishes;
        this.meat = meat;
        this.appetizers = mezedes;
    }

    @Override
    public void run() {
        try {
            //diamorfosi paketou gia lipsi kai apostoli minimatos
            BufferedReader instream = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
            BufferedWriter outstream = new BufferedWriter(new OutputStreamWriter(incoming.getOutputStream()));
            System.out.println("Local Address: " + incoming.getLocalAddress() + " Port: " + incoming.getPort());

            
            do {
                String select;
                select = instream.readLine();
                if (select.equals("Prepare the Fishes")) {
                    int tupospsariou = Integer.parseInt(instream.readLine()); //megethos paraggelias fishes
                    fishes.put(tupospsariou); //prosthiki psariwn sto order

                } else if (select.equals("Prepare the Appetizers")) {
                    int tuposmezedes = Integer.parseInt(instream.readLine()); //megethos paraggelias mezedes
                    appetizers.put(tuposmezedes); //prosthiki psariwn sto order
                } else if (select.equals("Prepare the Meat")) {
                    int tuposmeat = Integer.parseInt(instream.readLine()); //megethos paraggelias meat
                    meat.put(tuposmeat); //prosthiki meat sto order
                } else if (select.equals("fishes")) { 
                    int posapsaria = Integer.parseInt(instream.readLine()); //megethos paraggelias apo to pelati
                    fishes.get(posapsaria); // kalesma methodou get psariwn gia to pelati me tyxaio value

                } else if (select.equals("appetizers")) {
                    int posamezedes = Integer.parseInt(instream.readLine());
                    appetizers.get(posamezedes);

                } else if (select.equals("meat")) {
                    int posameat = Integer.parseInt(instream.readLine());
                    meat.get(posameat);

                }
            } while (true);
            
            
        } catch (IOException ex) {
            try {

                incoming.close();
                
            } catch (IOException ex1) {
                Logger.getLogger(EchoThread.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

    }

}
