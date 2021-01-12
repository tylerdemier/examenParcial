package examen.parc202012;

import java.util.ArrayList;
import java.util.StringTokenizer;

/** Clase que permite gestionar usuarios de meet con sus datos registrados acumulados
 */
public class UsuarioMeet {
	private String nombre;
	private String email;
	private int numSesiones;
	private int durTotalMins;
	private ArrayList<String> fechasDeConexion;
	private String horasDeConexion;
	
	/** Crea un nuevo usuario de meet con 0 sesiones, duración 0 y sin fechas de conexión
	 * @param nombre	Nombre de usuario
	 * @param email	Email (semioculto) de usuario
	 */
	public UsuarioMeet(String nombre, String email ) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.numSesiones = 0;
		this.durTotalMins = 0;
		this.fechasDeConexion = new ArrayList<>();
	}
	
	/** Crea un nuevo usuario de meet con todos los datos
	 * @param nombre	Nombre de usuario
	 * @param email	Email (semioculto) de usuario
	 * @param numSesiones	Número de sesiones del usuario
	 * @param durTotalMins	Duración total en minutos de sus sesiones
	 * @param horasDeConexion	Descripción de horas de conexión en formato de texto  ("dd/mm/aaaa (hh:mm-hh:mm) > dd/mm/aaaa (hh:mm-hh:mm) > ...)
	 */
	public UsuarioMeet(String nombre, String email, int numSesiones, int durTotalMins, String horasDeConexion) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.numSesiones = numSesiones;
		this.durTotalMins = durTotalMins;
		setHorasDeConexion( horasDeConexion );
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getEmail() {
		return email;
	}
	
	/** Devuelve la clave de un usuario (concatenación de nombre y email)
	 * @return	Clave del usuario
	 */
	public String getClave() {
		return nombre + email;
	}
	
	public int getNumSesiones() {
		return numSesiones;
	}
	
	/** Incrementa el número de sesiones del usuario
	 */
	public void incNumSesiones() {
		this.numSesiones++;
	}
	
	/** Devuelve la duración total de conexión del usuario
	 * @return	Duración total en minutos
	 */
	public int getDurTotalMins() {
		return durTotalMins;
	}
	
	/** Incrementa la duración total de conexión del usuario
	 * @param durMins	Minutos adicionales de conexión
	 */
	public void incDurTotalMins(int durMins) {
		this.durTotalMins += durMins;
	}
	
	/** Devuelve la lista de fechas de conexión del usuario
	 * @return	Lista de fechas de conexión del usuario, en formato string ("dd/mm/aaaa")
	 */
	public ArrayList<String> getFechasDeConexion() {
		return fechasDeConexion;
	}

	/** Devuelve las horas de conexión
	 * @return	Horas de conexión en formato de texto  ("dd/mm/aaaa (hh:mm-hh:mm) > dd/mm/aaaa (hh:mm-hh:mm) > ...) 
	 */
	public String getHorasDeConexion() {
		return horasDeConexion;
	}

	/** Modifica las horas de conexión (modificando también la lista de fechas de conexión del usuario)
	 * @param horasDeConexion	Horas de conexión en formato de texto  ("dd/mm/aaaa (hh:mm-hh:mm) > dd/mm/aaaa (hh:mm-hh:mm) > ...)
	 */
	public void setHorasDeConexion(String horasDeConexion) {
		this.horasDeConexion = horasDeConexion;
		this.fechasDeConexion = new ArrayList<>();
		StringTokenizer st = new StringTokenizer( horasDeConexion, ">" );
		while (st.hasMoreTokens()) {
			String fechaHora = st.nextToken().trim();
			String fecha = fechaHora.substring( 0, 10 );
			fechasDeConexion.add( fecha );
		}
	}
	
	@Override
	public String toString() {
		return nombre + ": " + durTotalMins + " minutos";
	}

	// T2
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UsuarioMeet) {
			UsuarioMeet u2 = (UsuarioMeet) obj;
			return u2.nombre.equals(nombre) &&
				u2.email.equals(email) &&
				u2.durTotalMins == durTotalMins &&
				u2.horasDeConexion.equals( horasDeConexion ) &&
				u2.fechasDeConexion.equals( fechasDeConexion );
		} else {
			return false;
		}
	}
	
}
