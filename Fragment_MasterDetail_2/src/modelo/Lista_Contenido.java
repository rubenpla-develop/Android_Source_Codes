package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.lucky4all.fragment_masterdetail_2.R;

/**
 * @author Ruben
 *
 */
public class Lista_Contenido {

	// Definimos donde se guardan las entradas de la lista

	public static ArrayList<Lista_entrada> ENTRADAS_LISTA = new ArrayList<Lista_entrada>();

	// Donde se asigna el identificador a cada entrada de la lista

	public static Map<String, Lista_entrada> ENTRADAS_LISTA_HASHMAP = new HashMap<String, Lista_entrada>();

	// Creamos estaticamente las entradas

	static {

		nuevaEntrada(new Lista_entrada("0", R.drawable.android, "ANDROID",
				"Sistema Operativo de Google basado en JAVA..."));
		nuevaEntrada(new Lista_entrada("1", R.drawable.ios, "IOS",
				"Sistema Operativo de Apple basado en OBJECTIVE C..."));
		nuevaEntrada(new Lista_entrada("2", R.drawable.firefox, "FIREFOX OS",
				"Sistema Operativo bastado en el standar HTML5/Javascript/CSS3..."));
		nuevaEntrada(new Lista_entrada("3", R.drawable.windowsmobile,
				"WINDOWS MOBILE",
				"Sistema Operativo de Microsoft basado en C++..."));
	}
	
	
	/** Añade una entrada a la lista
	 * @param entrada Elemento que añadimos a la lista
	 */
	private static void nuevaEntrada(Lista_entrada entrada) {
		ENTRADAS_LISTA.add(entrada);
		ENTRADAS_LISTA_HASHMAP.put(entrada.id, entrada);
		
	}
}
