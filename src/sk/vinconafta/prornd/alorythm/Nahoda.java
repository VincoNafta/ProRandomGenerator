package sk.vinconafta.prornd.alorythm;

import javax.swing.JOptionPane;
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
//        System.out.println(hornaHranica);
        int vygenerovane = vygenerujCiselko(dolnaHranica, hornaHranica) ;
        if (opakujuce) {
            while (cisla.contains(vygenerovane)) {
                vygenerovane = vygenerujCiselko(dolnaHranica, hornaHranica);
                if (cisla.size() >= (hornaHranica - 1) - dolnaHranica) {
                    this.ukoncene = true;
                    break;
                }
            }
            if (!this.ukoncene) {
                this.cisla.add(vygenerovane);
            }
        }
        return vygenerovane;
    }

    private int vygenerujCiselko(int dolnaHranica, int hornaHranica) {
        if (dolnaHranica < 0 && hornaHranica<0) {
            int nahoda = rnd.nextInt(Math.abs(hornaHranica) - Math.abs(dolnaHranica)) + Math.abs(dolnaHranica);
            return nahoda * (-1);
        }
        return rnd.nextInt(hornaHranica - dolnaHranica) + dolnaHranica;
    }



    public int vygenerujDalsie(int hornaHranica, boolean opakujuce){
        int vygenerovane = this.vygeneruj(hornaHranica);
        if (opakujuce) {
            while (cisla.contains(vygenerovane)) {

                vygenerovane = this.vygeneruj(hornaHranica);
                if (cisla.size() >= hornaHranica) {
                    this.ukoncene = true;
                    break;
                }
            }
            if (!this.ukoncene) {
                this.cisla.add(vygenerovane);
            }
        }
        return vygenerovane;
    }
    private int vygeneruj(int hranica) {
        if (hranica > 0) {
            return rnd.nextInt(hranica) + 1;
        } else  {
            int vygenerovane = rnd.nextInt(Math.abs(hranica) + 1);
            return vygenerovane * (-1);
        }
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
