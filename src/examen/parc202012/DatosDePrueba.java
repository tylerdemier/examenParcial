package examen.parc202012;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class DatosDePrueba {

	public static ArrayList<UsuarioMeet> listaPrueba1 = new ArrayList<UsuarioMeet>( Arrays.asList( 
			new UsuarioMeet( "Persona 1", "1@deusto.es", 4, ProcesoCSVMeet.calcMinutos( "2 h 01 min" ), "15/10/2020 (14:58-16:55) > 22/10/2020 (14:58-16:37)" ),
			new UsuarioMeet( "Persona 2", "2@deusto.es", 4, ProcesoCSVMeet.calcMinutos( "2 h 02 min" ), "15/10/2020 (15:06-16:42) > 29/10/2020 (8:56-11:02) > 05/11/2020 (8:56-11:07)" ),
			new UsuarioMeet( "Persona 3", "3@deusto.es", 5, ProcesoCSVMeet.calcMinutos( "3 h 15 min" ), "15/10/2020 (15:00-16:51) > 22/10/2020 (14:53-16:40)" ),
			new UsuarioMeet( "Persona 4", "4@deusto.es", 5, ProcesoCSVMeet.calcMinutos( "3 h 14 min" ), "15/10/2020 (15:15-16:42) > 22/10/2020 (15:10-16:37) > 29/10/2020 (9:02-11:02) > 05/11/2020 (9:02-11:02)" )
	) );
	public static ArrayList<UsuarioMeet> listaPrueba2 = new ArrayList<UsuarioMeet>( Arrays.asList( 
		new UsuarioMeet( "Aimar Hernando", "aima*********@***.es", 2, ProcesoCSVMeet.calcMinutos( "3 h 35 min" ), "15/10/2020 (14:58-16:55) > 22/10/2020 (14:58-16:37)" ),
		new UsuarioMeet( "Alberto Uranga", "albe***********@***.es", 3, ProcesoCSVMeet.calcMinutos( "5 h 55 min" ), "15/10/2020 (15:06-16:42) > 29/10/2020 (8:56-11:02) > 05/11/2020 (8:56-11:07)" ),
		new UsuarioMeet( "Alcira Escala", "cira*******@***.es", 2, ProcesoCSVMeet.calcMinutos( "2 h 18 min" ), "22/10/2020 (15:10-16:37) > 29/10/2020 (9:36-10:28)" ),
		new UsuarioMeet( "Andoni Couso", "ando*********@***.es", 2, ProcesoCSVMeet.calcMinutos( "3 h 38 min" ), "15/10/2020 (15:00-16:51) > 22/10/2020 (14:53-16:40)" ),
		new UsuarioMeet( "Asier Sanz", "asie**********@***.es", 4, ProcesoCSVMeet.calcMinutos( "6 h 52 min" ), "15/10/2020 (15:15-16:42) > 22/10/2020 (15:10-16:37) > 29/10/2020 (9:02-11:02) > 05/11/2020 (9:02-11:02)" ),	
		new UsuarioMeet( "Daniel Ramón", "dani*********@***.es", 2, ProcesoCSVMeet.calcMinutos( "3 h 52 min" ), "22/10/2020 (14:58-16:37) > 29/10/2020 (9:00-11:12)" ),	
		new UsuarioMeet( "David Hoyo", "davi*************@***.es", 2, ProcesoCSVMeet.calcMinutos( "3 h 47 min" ), "22/10/2020 (15:05-16:37) > 29/10/2020 (9:00-11:14)" ),
		new UsuarioMeet( "Eneko Angulo", "enek**********@***.es", 3, ProcesoCSVMeet.calcMinutos( "5 h 40 min" ), "15/10/2020 (14:59-16:46) > 22/10/2020 (14:52-16:37) > 29/10/2020 (8:53-11:02)" ),	
		new UsuarioMeet( "Erlantz Santos", "erla******@***.es", 2, ProcesoCSVMeet.calcMinutos( "3 h 14 min" ), "15/10/2020 (15:03-16:42) > 22/10/2020 (15:00-16:37)" ),
		new UsuarioMeet( "Iker López", "iker********@***.es", 2, ProcesoCSVMeet.calcMinutos( "2 h 26 min" ), "15/10/2020 (15:00-16:15) > 22/10/2020 (15:00-16:11)" ),
		new UsuarioMeet( "Iñigo Aguilar", "inig*****@***.es", 3, ProcesoCSVMeet.calcMinutos( "3 h 34 min" ), "22/10/2020 (15:00-16:37) > 29/10/2020 (10:04-11:02) > 05/11/2020 (10:04-11:03)" ),
		new UsuarioMeet( "Jorge Román", "jorg************@***.es", 4, ProcesoCSVMeet.calcMinutos( "7 h 18 min" ), "15/10/2020 (14:59-16:43) > 22/10/2020 (15:07-16:37) > 29/10/2020 (8:57-10:59) > 05/11/2020 (8:57-10:57)" ),
		new UsuarioMeet( "Josu Losada", "josu*******@***.es", 4, ProcesoCSVMeet.calcMinutos( "7 h 47 min" ), "15/10/2020 (14:59-16:42) > 22/10/2020 (14:54-16:41) > 29/10/2020 (8:53-11:02) > 05/11/2020 (8:53-11:02)" ),
		new UsuarioMeet( "Josu Morán", "josu*******@***.es", 4, ProcesoCSVMeet.calcMinutos( "7 h 39 min" ), "15/10/2020 (15:01-16:42) > 22/10/2020 (14:57-16:42) > 29/10/2020 (8:55-11:02) > 05/11/2020 (8:55-11:02)" ),
		new UsuarioMeet( "Josu Sanz", "sanz******@***.es", 2, ProcesoCSVMeet.calcMinutos( "1 h 45 min" ), "15/10/2020 (14:58-16:41) > 29/10/2020 (8:58-9:24)" ),
		new UsuarioMeet( "Kerman Uralde", "kerm****************@***.es", 4, ProcesoCSVMeet.calcMinutos( "7 h 23 min" ), "15/10/2020 (15:00-16:45) > 22/10/2020 (15:04-16:37) > 29/10/2020 (9:01-11:02) > 05/11/2020 (9:01-11:05)" ),	
		new UsuarioMeet( "Lander Muñoz", "land*************@***.es", 3, ProcesoCSVMeet.calcMinutos( "4 h 14 min" ), "15/10/2020 (15:14-16:50) > 22/10/2020 (14:55-16:34) > 29/10/2020 (9:59-11:03)" ),
		new UsuarioMeet( "Lourdes Aguilera", "lour************@***.es", 2, ProcesoCSVMeet.calcMinutos( "3 h 44 min" ), "15/10/2020 (15:06-16:42) > 29/10/2020 (8:54-11:02)" ),
		new UsuarioMeet( "Marcos Blasco", "marc*******@***.es", 1, ProcesoCSVMeet.calcMinutos( "1 h 46 min" ), "22/10/2020 (14:57-16:43)" ),
		new UsuarioMeet( "Markel Bilbao", "mark************@***.es", 2, ProcesoCSVMeet.calcMinutos( "2 h 53 min" ), "15/10/2020 (15:00-17:00) > 22/10/2020 (14:59-15:52)" ),	
		new UsuarioMeet( "María Blanco", "mari*********@***.es", 3, ProcesoCSVMeet.calcMinutos( "5 h 48 min" ), "15/10/2020 (14:59-16:50) > 22/10/2020 (14:57-16:41) > 29/10/2020 (8:59-11:12)" ),	
		new UsuarioMeet( "Miguel Solana", "migu********@***.es", 3, ProcesoCSVMeet.calcMinutos( "5 h 43 min" ), "15/10/2020 (15:11-16:42) > 29/10/2020 (8:56-11:02) > 05/11/2020 (8:56-11:02)" ),
		new UsuarioMeet( "Mikel Fernandez", "mike*************@***.es", 2, ProcesoCSVMeet.calcMinutos( "3 h 0 min" ), "15/10/2020 (15:10-16:42) > 22/10/2020 (15:09-16:37)" ),
		new UsuarioMeet( "Nicolás Revilla", "nico**************@***.es", 4, ProcesoCSVMeet.calcMinutos( "7 h 29 min" ), "15/10/2020 (14:57-16:52) > 22/10/2020 (15:03-16:37) > 29/10/2020 (8:58-10:58) > 05/11/2020 (8:58-10:59)" ),	
		new UsuarioMeet( "Víctor Cabel", "vict**************@***.es", 3, ProcesoCSVMeet.calcMinutos( "5 h 41 min" ), "15/10/2020 (14:58-16:42) > 22/10/2020 (14:56-16:44) > 29/10/2020 (8:54-11:03)" ),
		new UsuarioMeet( "Xabier Uribe", "xabi*********@***.es", 3, ProcesoCSVMeet.calcMinutos( "5 h 31 min" ), "15/10/2020 (15:00-16:42) > 22/10/2020 (14:59-16:37) > 29/10/2020 (9:00-11:10)" ),
		new UsuarioMeet( "Álvaro Guerrero", "alva*****@***.es", 3, ProcesoCSVMeet.calcMinutos( "5 h 17 min" ), "15/10/2020 (15:03-16:42) > 22/10/2020 (14:58-16:37) > 29/10/2020 (9:02-11:02)" ),
		new UsuarioMeet( "Álvaro Martínez", "alva*************@***.es", 4, ProcesoCSVMeet.calcMinutos( "7 h 27 min" ), "15/10/2020 (15:08-16:42) > 22/10/2020 (15:20-16:45) > 29/10/2020 (9:00-11:17) > 05/11/2020 (9:00-11:16)" )	
	) );
	
	public static void main(String[] args) {
		calculoGrupos( listaPrueba1 );  // Si se quiere probar el algoritmo recursivo con los datos de prueba 1
		calculoGrupos( listaPrueba2 );  // Si se quiere probar el algoritmo recursivo con los datos de prueba 2
	}
	
	private static int numHomogeneos;              // Conteo de combinaciones de grupos homogéneos
	private static int numCombs;                   // Conteo de todas las combinaciones de grupos
	public static LinkedList<UsuarioMeet> grupo1;  // Primer grupo 1 encontrado (null si no se ha encontrado ninguno)
	public static LinkedList<UsuarioMeet> grupo2;  // Primer grupo 2 encontrado (null si no se ha encontrado ninguno)
	public static boolean LOG_EN_CONSOLA = true;   // flag de si se saca o no registro de cada combinación homogénea de grupos a consola
	
	/** Calcula todas las posibles combinaciones de dos grupos de todos los usuarios, y cuenta los que son homogéneos (mismo número de usuarios, mismas sesiones totales, misma duración total)
	 * @param lista	Lista de todos los usuarios a combinar
	 */
	public static void calculoGrupos( ArrayList<UsuarioMeet> lista ) {
		// Método shell (solo se llama una vez. Llamar al método recursivo que es el de abajo)
		numHomogeneos = 0;
		numCombs = 0;
		grupo1 = null;
		grupo2 = null;
		System.out.println( "Calculando grupos homogéneos en lista de " + lista.size() + " usuarios: " + lista );
		calculoRec( lista, 0, new LinkedList<UsuarioMeet>(), new LinkedList<UsuarioMeet>() );
		System.out.println( "Grupos homogéneos: " + numHomogeneos + " de " + numCombs + " combinaciones." );
		System.out.println();
	}

	// T4 
	
	@SuppressWarnings("unchecked")
	// Calcular los grupos homogéneos de usuarios es:
	//   - Si nos quedan usuarios:
	//     - Añadir usuario a lista 1, calcular grupos homogéneos de usuarios restantes y quitar usuario de lista 1
	//     - Añadir usuario a lista 2, calcular grupos homogéneos de usuarios restantes y quitar usuario de lista 2
	//   - Si no nos quedan usuarios, comprobar si se cumplen las condiciones de homogéneos y contar - sacar a consola (guardando los primeros en grupo1-grupo2)
	private static void calculoRec( ArrayList<UsuarioMeet> lUsus, int n, LinkedList<UsuarioMeet> l1, LinkedList<UsuarioMeet> l2 ) {
		if (n>=lUsus.size()) {
			numCombs++;
			int dur1 = 0; for (UsuarioMeet um : l1) dur1 += um.getDurTotalMins();
			int dur2 = 0; for (UsuarioMeet um : l2) dur2 += um.getDurTotalMins();
			int ns1 = 0; for (UsuarioMeet um : l1) ns1 += um.getNumSesiones();
			int ns2 = 0; for (UsuarioMeet um : l2) ns2 += um.getNumSesiones();
			if (Math.abs(dur1-dur2)<1 && l1.size()==l2.size() && ns1==ns2) {
				if (grupo1==null) {
					grupo1 = (LinkedList<UsuarioMeet>) l1.clone();
					grupo2 = (LinkedList<UsuarioMeet>) l2.clone();
				}
				if (LOG_EN_CONSOLA) System.out.println( l1.size() + "\t" + l2.size() + "\t" + l1 + "\t" + l2 );
				numHomogeneos++;
			}
		} else {
			UsuarioMeet um = lUsus.get(n);
			// usuario a l1
			l1.addLast( um );
			calculoRec( lUsus, n+1, l1, l2 );
			l1.removeLast();
			// usuario a l2
			l2.addLast( um );
			calculoRec( lUsus, n+1, l1, l2 );
			l2.removeLast();
		}
	}

	
	
}
