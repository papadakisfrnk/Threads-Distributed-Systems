/*
Papadakis Fragkiskos
321/2017147
*/
public class Main {

    public static void main(String[] args) {
        //threads gia tous chef(enas gia kathe dish)
        Chef c1 = new Chef("Chef 1");
        Chef c2 = new Chef("Chef 2");
        Chef c3 = new Chef("Chef 3");
        c1.start();
        c2.start();
        c3.start();
        //threads gia tous pelates (enas gia kathe dish)
        Pelatis p1 = new Pelatis("fishes");
        Pelatis p2 = new Pelatis("appetizers");
        Pelatis p3 = new Pelatis("meat");
        p1.start();
        p2.start();
        p3.start();
    }
}
