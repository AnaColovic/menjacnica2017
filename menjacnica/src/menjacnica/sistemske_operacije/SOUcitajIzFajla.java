package menjacnica.sistemske_operacije;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.LinkedList;

import menjacnica.Valuta;

public class SOUcitajIzFajla {
	public static LinkedList<Valuta> izvrsi(String putanja){
		LinkedList<Valuta> lista = new LinkedList<Valuta>();
			try {
				ObjectInputStream in = new ObjectInputStream(
						new BufferedInputStream(new FileInputStream(putanja)));
				
				lista = (LinkedList<Valuta>)(in.readObject());
				
				in.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return lista;
	}
}
