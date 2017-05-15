package menjacnica.gui;

import java.awt.EventQueue;
import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import menjacnica.Menjacnica;
import menjacnica.Valuta;
import menjacnica.gui.models.MenjacnicaTableModel;

public class GUIKontroler {
	private static MenjacnicaGUI frame;
	private static DodajKursGUI dodaj;
	private static Menjacnica sistem;
	private static ObrisiKursGUI obrisi;
	private static IzvrsiZamenuGUI izvrsi;
	
	private static JTable table;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MenjacnicaGUI();
					sistem = new Menjacnica();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void prikaziDodajKursGUI() {
		dodaj = new DodajKursGUI(frame);
		dodaj.setLocationRelativeTo(frame);
		dodaj.setVisible(true);
	}
	
	public static void prikaziObrisiKursGUI(Valuta v) {
		obrisi = new ObrisiKursGUI(frame, v);
		obrisi.setLocationRelativeTo(frame);
		obrisi.setVisible(true);
	}
	
	public static void prikaziIzvrsiZamenuGUI(Valuta v) {
			izvrsi = new IzvrsiZamenuGUI(frame, v);
			izvrsi.setLocationRelativeTo(frame);
			izvrsi.setVisible(true);
		}
	
	public static void ucitajIzFajla() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(frame);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				sistem.ucitajIzFajla(file.getAbsolutePath());
				frame.prikaziSveValute();
			}	
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(frame, e1.getMessage(),"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void sacuvajUFajl() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(frame);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();

				sistem.sacuvajUFajl(file.getAbsolutePath());
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(frame, e1.getMessage(),"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void ugasiAplikaciju() {
		int opcija = JOptionPane.showConfirmDialog(frame,"Da li ZAISTA zelite da izadjete iz apliacije", "Izlazak", JOptionPane.YES_NO_OPTION);

		if (opcija == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	public static void prikaziAboutProzor(){
		JOptionPane.showMessageDialog(frame,"Autor: Bojan Tomic, Verzija 1.0", "O programu Menjacnica",	JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void obrisiValutu(Valuta v) {
		try{
			sistem.obrisiValutu(v);
			frame.prikaziSveValute();
			obrisi.dispose();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(frame, e1.getMessage(),"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void unesiKurs(String naziv, String skraceniNaziv,int sifra,double prodajni,double kupovni,double srednji) {
		try {
			Valuta v = new Valuta();
			v.setNaziv(naziv);
			v.setSkraceniNaziv(skraceniNaziv);
			v.setKupovni(kupovni);
			v.setProdajni(prodajni);
			v.setSrednji(srednji);
			v.setSifra(sifra);
			sistem.dodajValutu(v);
			frame.prikaziSveValute();
			dodaj.dispose();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(frame, e1.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static double izvrsiZamenu(Valuta v, double iznos, boolean prodaja){
		double konacniIznos=0;;
		try{
			konacniIznos = sistem.izvrsiTransakciju(v, prodaja, iznos);
		} catch (Exception e1) {
		JOptionPane.showMessageDialog(frame, e1.getMessage(),
				"Greska", JOptionPane.ERROR_MESSAGE);
	}
		return konacniIznos;
	}

	public static List<Valuta> vratiValute() {
		return sistem.vratiKursnuListu();
	}
	
}
