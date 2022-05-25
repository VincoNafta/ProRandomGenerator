package sk.vinconafta.prornd.alorythm;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Nahoda {
    private final Random rnd;
    private final ArrayList<Integer> cisla;
    private boolean ukoncene;

    public Nahoda() {
        this.rnd = new Random();
        this.cisla = new ArrayList<>();
        this.ukoncene = false;
    }

    public int vygenerujVIntervale(int dolnaHranica, int hornaHranica, boolean opakujuce) {
        hornaHranica += 1;
        System.out.println(hornaHranica);
        int vygenerovane = rnd.nextInt(dolnaHranica, hornaHranica) ;
        if (opakujuce) {
            while (cisla.contains(vygenerovane)) {
                vygenerovane = rnd.nextInt(dolnaHranica, hornaHranica);
//                System.out.println(vygenerovane);
                if (cisla.size() >= (hornaHranica - 1) - dolnaHranica) {
                    this.ukoncene = true;
                    break;
                }
            }
            if (!this.ukoncene) {
                this.cisla.add(vygenerovane);
            }
//            System.out.println(this.cisla.toString());
        }
        return vygenerovane;
    }
    public int vygenerujDalsie(int hornaHranica, boolean opakujuce){
        int vygenerovane = rnd.nextInt(hornaHranica) + 1;
        if (opakujuce) {
            while (cisla.contains(vygenerovane)) {
                vygenerovane = rnd.nextInt(hornaHranica) + 1;
//                System.out.println(vygenerovane);
                if (cisla.size() >= hornaHranica) {
                    this.ukoncene = true;
                    break;
                }
            }
            if (!this.ukoncene) {
                this.cisla.add(vygenerovane);
            }
//            System.out.println(this.cisla.toString());
        }
        return vygenerovane;
    }

    public boolean jeUkoncene() {
        return this.ukoncene;
    }

    public void reset(int dialogInput) {
        if (dialogInput == 0) {
            this.ukoncene = false;
            this.cisla.clear();
            JOptionPane.showMessageDialog(null, "Historia čísel bola premazaná!", "Premazaná historia", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
