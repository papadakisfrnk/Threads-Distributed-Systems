/*
Papadakis Fragkiskos
321/2017147
*/
public class Order {

    private int[] dishes = {0, 0, 0, 0, 0};
    private boolean bufferEmpty = true;
    private boolean bufferFull = false;
    private final int size = 5;
    private int counter = -1;
    private String type;

    public Order(String type) {
        this.type = type;
    }

    public synchronized int get(int posotita) { //sychronized methodo gia afairesi
        while (counter + 1 < posotita) { //oso i posotita pou zitietai einai perisotero apo toulaxiston 1 
            try {
                System.out.println(type + " dishes is not enough for " + posotita + " dishes\n"); //emfanisi minimatos oti den exoun etoimastei tosa dishes
                wait(); //epistrofi tou kleidiou tou antikeimenou
            } catch (InterruptedException ex) {

            }
        }
        int value = 0;
        System.out.println("Client asked for " + posotita + " dishes\n");
        for (int i = 0; i < posotita; i++) {
            value = dishes[i];
            System.out.println("The #" + value + " dish was served of " + type); //emfanisi minimatos gia to pio dish serviristike
            counter--; //kathe fora pou servirizetai ena dish afaireite spo to order
        }

        bufferFull = false;
        if (counter == -1) { //ean einai adeio
            bufferEmpty = true;
            System.out.println("The buffer is empty");
        }
        notifyAll(); //afupnisi tou paragwgou poy pithanon exei empodistei

        return value;

    }

    public synchronized void put(int value) { //synchroninized methodo gia prosthiki

        while (bufferFull == true) { //oso einai gemato
            try {
                System.out.println(value + " dishes are full!");
                wait(); //epistrofi tou kleidiou tou antikemenou 
            } catch (InterruptedException ex) {

            }
        }
        System.out.println("A new dish of " + type + " is ready!");

        bufferEmpty = false;
        dishes[++counter] = value;
        //elegxos an exei gemisei  
        if (counter == size - 1) {
            bufferFull = true;
            System.out.println("The Orders is full");
        }
        notifyAll(); //afipnisi tou katanalwti pou pithanon perimenei
    }
}
