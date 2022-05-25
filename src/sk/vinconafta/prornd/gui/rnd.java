package sk.vinconafta.prornd.gui;

import sk.vinconafta.prornd.alorythm.Nahoda;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class rnd {
    private final Nahoda nahoda;
    private JPanel upperDesc;
    private JTextField textField1;
    private JButton confirmButton;
    private JLabel dolnaHranica;
    private JTextField textField2;
    private JButton moreResultsButton;
    private JCheckBox duplicityCheckBox;

    public rnd() {
        this.nahoda = new Nahoda();
        this.confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectResults();
            }
        });
        this.moreResultsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!textField2.isVisible()) {
                    dolnaHranica.setVisible(true);
                    textField2.setVisible(true);
                    moreResultsButton.setText("Zakladné možnosti");
                } else {
                    dolnaHranica.setVisible(false);
                    textField2.setVisible(false);
                    moreResultsButton.setText("Rozšírené možnosti");
                }
            }
        });
        this.confirmButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                selectResults();
            }
        });
        this.duplicityCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(duplicityCheckBox.isSelected());
            }
        });
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    selectResults();
                }
            }
        });
    }

    private void selectResults() {
        if (!textField2.isVisible()) {
            vyhodnotZakladneMenu();
        } else {
            int dolnaHranica = Integer.parseInt(textField2.getText());
            int hornaHranica = Integer.parseInt(textField1.getText());
            //System.out.println(nahoda.vygenerujVIntervale(dolnaHranica, hornaHranica, duplicityCheckBox.isSelected()));

            int vygenerovaneCislo = nahoda.vygenerujVIntervale(dolnaHranica, hornaHranica, duplicityCheckBox.isSelected());
            if (this.nahoda.jeUkoncene()) {
                int x = JOptionPane.showConfirmDialog(null, "Už sa vygenerovali všetky čísla, \n Prajete si premazať medzipamäť ?");
                this.nahoda.reset(x);
            } else {
                JOptionPane.showMessageDialog(null, "Vygenerovalo sa číslo " + vygenerovaneCislo, "Výsledok generovania", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void vyhodnotZakladneMenu() {
        if (!textField1.getText().isEmpty()) {

            try {
                int hornaHranica = Integer.parseInt(textField1.getText());

                int vygenerovaneCislo = nahoda.vygenerujDalsie(hornaHranica, duplicityCheckBox.isSelected());
                if (this.nahoda.jeUkoncene()) {
                    int x = JOptionPane.showConfirmDialog(null, "Už sa vygenerovali všetky čísla, \n Prajete si premazať medzipamäť ?");
                    this.nahoda.reset(x);
                } else {
                    JOptionPane.showMessageDialog(null, "Vygenerovalo sa číslo " + vygenerovaneCislo, "Výsledok generovania", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Vstup musí byť len celé číslo", "Neplatný vstup", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Neplatný vstup", "Neplatný vstup", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ProRandom - Generator");
        frame.setContentPane(new rnd().upperDesc);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
