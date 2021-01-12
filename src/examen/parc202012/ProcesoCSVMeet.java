package examen.parc202012;

import java.awt.Color;
import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableCellRenderer;

	
/** Proceso de ficheros CSV de meet
 */
public class ProcesoCSVMeet {

	@SuppressWarnings("serial")
	public static void main( String[] s ) {
		preparaVentana();
		// Carga de ficheros (quitar si no se quiere cargar los ficheros de inicio)
		cargaCSVMeet( "src/examen/parc202012/meet2020-10-15.csv" );
		cargaCSVMeet( "src/examen/parc202012/meet2020-10-22.csv" );
		cargaCSVMeet( "src/examen/parc202012/meet2020-10-29.csv" );
		cargaCSVMeet( "src/examen/parc202012/meet2020-11-05.csv" );
		// Integración (quitar si no se quiere integrar de inicio)
		actualizaTablaIntegracion();
		// T4 Extra
		ArrayList<UsuarioMeet> l = new ArrayList<UsuarioMeet>( mUsuarios.values() );
		DatosDePrueba.LOG_EN_CONSOLA = false;
		DatosDePrueba.calculoGrupos( l );
		DatosDePrueba.LOG_EN_CONSOLA = true;
		if (DatosDePrueba.grupo1!=null) {
			HashSet<String> noms1 = new HashSet<>(); for (UsuarioMeet um : DatosDePrueba.grupo1) noms1.add( um.getNombre() );
			HashSet<String> noms2 = new HashSet<>(); for (UsuarioMeet um : DatosDePrueba.grupo2) noms2.add( um.getNombre() );
			vIntegra.getJTable().setDefaultRenderer( String.class, new DefaultTableCellRenderer() {
				@Override
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
					Component ret = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
					if (ret instanceof JLabel) {
						JLabel l = (JLabel) ret;
						if (column==0) {
							String nom = (String) value;
							if (noms1.contains( nom ))
								l.setBackground( Color.yellow );
							else if (noms2.contains( nom ))
								l.setBackground( Color.cyan );
							else
								l.setBackground( Color.white );
						} else {
							l.setBackground( Color.white );
						}
					}
					return ret;
				}
				
			});
		}
		vIntegra.getJTable().repaint();
	}
	
	// Parte de Drag&Drop
	
    @SuppressWarnings("serial")
	private static TransferHandler handler = new TransferHandler() {
        public boolean canImport(TransferHandler.TransferSupport support) {
            if (!support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                return false;
            }
            return true;
        }

        @SuppressWarnings("unchecked")
		public boolean importData(TransferHandler.TransferSupport support) {
            if (!canImport(support)) {
                return false;
            }
            Transferable t = support.getTransferable();
            try {
                java.util.List<File> l = (java.util.List<File>)t.getTransferData(DataFlavor.javaFileListFlavor);
                for (File f : l) {
                	if (f.isFile()) {
                		cargaCSVMeet( f.getAbsolutePath() );
                	}
                }
            } catch (UnsupportedFlavorException e) {
                return false;
            } catch (IOException e) {
                return false;
            }
            return true;
        }
    };


	// PARTE DE LÓGICA DE CSV Y DATOS
	
		private static VentanaDatos newVentanaTabla( VentanaGeneral vg, Tabla tabla, String codTabla, int posX, int posY ) {
			try {
				String tit = codTabla + " (" + tabla.size() + ")";
				if (codTabla.equals("Integración")) tit = codTabla;
				VentanaDatos vd = new VentanaDatos( vg, codTabla, tit ); 
				vd.setSize( 600, 450 );
				vd.setTabla( tabla ); 
				vg.addVentanaInterna( vd, codTabla );
				vd.setLocation( posX, posY );
				vd.addBoton( "-> clipboard", new Tabla.CopyToClipboard( tabla, vd ), true );
				vd.addBoton( "guardar", new Guardar( tabla, codTabla ), true );
				vd.setVisible( true );
				try { Thread.sleep( 100 ); } catch (InterruptedException e) {}
				return vd;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
			public static class Guardar implements Runnable {
				private Tabla tabla;
				private String codTabla;
				public Guardar( Tabla tabla, String codTabla ) {
					this.tabla = tabla;
					this.codTabla = codTabla;
				}
				@Override
				public void run() {
					String nomFic = sdf.format( new Date() );
					if (codTabla.equals("Integración")) nomFic = "nombre asignatura";
					nomFic = JOptionPane.showInputDialog( ventana, "Introduce nombre de fichero csv para guardar", nomFic ); 
					if (nomFic!=null && !nomFic.isEmpty()) {
						if (!nomFic.toUpperCase().endsWith( ".CSV" )) nomFic += ".csv";
						if (new File(nomFic).exists()) {
							int resp = JOptionPane.showConfirmDialog( ventana, "El fichero indicado ya existe. ¿Sobreescribirlo? ", "Atención", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE );
							if (resp==JOptionPane.CANCEL_OPTION) return; // Cortar
						}
						try {
							tabla.generarCSV( new File( nomFic ), tabla.getHeaders().toArray( new String[0] ) );
						} catch (IOException e) {
							JOptionPane.showMessageDialog( ventana, "Error al generar csv", "Error", JOptionPane.ERROR_MESSAGE );
						}
					}
				}
			}


	private static ArrayList<Tabla> lTablas = new ArrayList<Tabla>();
	private static ArrayList<String> lFechas = new ArrayList<String>();
	private static VentanaGeneral ventana;
	private static VentanaDatos vIntegra;
	private static Tabla tIntegra;
	private static final SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat( "yyyy-MM-dd" );

	protected static void preparaVentana() {
		ventana = new VentanaGeneral();
		ventana.setSize( 800, 600 );
		ventana.setEnCierre( new Runnable() { public void run() {  } } );
		ventana.setTitle( "Lectura de datos de meet" );
		ventana.setVisible( true );
		ventana.getJDesktopPane().setTransferHandler( handler );
		ventana.setMensaje( "Arrastra ficheros csv de asistencia al panel. Acciones -> Integrar para integrar datos", Color.yellow );
		ventana.addMenuAccion( "Integrar", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.setMensaje( "Arrastra ficheros csv de asistencia al panel. Acciones -> Integrar para integrar datos" );
				boolean ok = actualizaTablaIntegracion();
				if (!ok) {
					JOptionPane.showMessageDialog( ventana, "No se puede generar integración sin datos", "Error", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
		ventana.addMenuAccion( "Carga integración previa", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.setMensaje( "Arrastra ficheros csv de asistencia al panel. Acciones -> Integrar para integrar datos" );
				cargaIntegracionPrevia();
			}
		});
		ventana.addMenuAccion( "Reinicia integración", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reiniciaIntegracion();
			}
		});
		ventana.addMenuAccion( "Prueba integración de Base de Datos", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				compruebaIntegracionConBD();
			}
		});
	}

	private static void reiniciaIntegracion() {
		ventana.setMensaje( "Arrastra ficheros csv de asistencia al panel. Acciones -> Integrar para integrar datos" );
		while (tIntegra.size()>0) tIntegra.removeRow(0);  // Quita todo y vuelve a poner
		mUsuarios = new TreeMap<>();
		sFechas = new TreeSet<>();
		ventana.getJDesktopPane().moveToFront( vIntegra );
		vIntegra.getJTable().revalidate();
	}
	
	private static void cargaIntegracionPrevia() {
		initTablaInt();
		String nomFic = "nombre asignatura";
		nomFic = JOptionPane.showInputDialog( ventana, "Introduce nombre de fichero csv para cargar", nomFic ); 
		if (nomFic!=null && !nomFic.isEmpty()) {
			if (!nomFic.toUpperCase().endsWith( ".CSV" )) nomFic += ".csv";
			if (new File(nomFic).exists()) {
				try {
					while (tIntegra.size()>0) tIntegra.removeRow(0);  // Quita todo y vuelve a poner
					Tabla tIntegra2 = Tabla.processCSV( new File(nomFic) );
					for (int fila=0; fila<tIntegra2.size(); fila++) {
						tIntegra.addDataLine( tIntegra2.getFila( fila ) );
					}
					mUsuarios = new TreeMap<>();
					sFechas = new TreeSet<>();
					for (int i=0; i<tIntegra.size(); i++) {
						// Crea usuario de meet y lo añade al mapa
						String nombre = tIntegra.get( i, "Nombre" );
						String correo = tIntegra.get( i, "Correo" );
						int ses = tIntegra.getInt( i, "Sesiones" );
						String dur = tIntegra.get( i, "Duración total" );
						String fechasHoras = tIntegra.get( i, "Fechas" );
						UsuarioMeet um = new UsuarioMeet( nombre, correo );
						for (int j=0; j<ses; j++) um.incNumSesiones();
						um.incDurTotalMins( calcMinutos( dur ) );
						um.setHorasDeConexion( fechasHoras );
						mUsuarios.put( um.getClave(), um );
						// Actualiza las fechas generales (partiendo de las que se calculan en este usuario desde las horas de conexión)
						for (String fecha : um.getFechasDeConexion()) sFechas.add( fecha );
					}
					ventana.getJDesktopPane().moveToFront( vIntegra );
					vIntegra.getJTable().revalidate();
				} catch (Exception e) {
					JOptionPane.showMessageDialog( ventana, "Error en carga de fichero " + nomFic, "Error en carga", JOptionPane.ERROR_MESSAGE );
				}
			} else {
				JOptionPane.showMessageDialog( ventana, "No existe el fichero indicado " + nomFic, "Error en carga", JOptionPane.ERROR_MESSAGE );
			}
		}
	}
		// Inicializa el mapa de usuarios, el conjunto de fechas y la tabla de datos de integración
		private static void initTablaInt() {
			if (vIntegra==null) {
				mUsuarios = new TreeMap<>();
				sFechas = new TreeSet<>();
				// Nombre	Correo	Duración	Hora a la que se unió	Hora a la que salió	
				ArrayList<String> cabs = new ArrayList<String>( Arrays.asList( "Nombre", "Correo", "Sesiones", "Duración total", "Fechas" ) );
				tIntegra = new Tabla( cabs );
				vIntegra = newVentanaTabla( ventana, tIntegra, "Integración", xIni, yIni );
				xIni += 10; yIni += 10;
			}
		}
	
	protected static TreeMap<String,UsuarioMeet> mUsuarios;  // Mapa ordenado de usuarios
	protected static TreeSet<String> sFechas;  // Set ordenado de fechas
		
	protected static boolean actualizaTablaIntegracion() {
		initTablaInt();  // Inicializa el mapa y el conjunto de fechas (vaciándolos)
		for (int j=0; j<lTablas.size(); j++) {
			Tabla t = lTablas.get(j);
			String fecha = lFechas.get(j);
			// Test previo de fechas (para que el usuario elija si repite una integración si la fecha ya está incluida)
			boolean procesar = true;
			if (sFechas.contains( fecha )) {
				int resp = JOptionPane.showConfirmDialog( ventana, "La integración ya contiene datos de la fecha " + fecha + "\n¿Confirmas que se añada al cálculo?", "Atención", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE );
				if (resp==JOptionPane.CANCEL_OPTION) procesar = false;
			}
			// Proceso de los datos de cada tabla
			if (procesar) {
				try {
					for (int i=0; i<t.size(); i++) {  // Proceso de cada fila de la tabla
						String nombre = t.get( i, "Nombre" );   // Nombre de cada fila
						String correo = t.get( i, "Correo" );   // Email de cada fila
						String duracion = t.get( i, "Duración" );  // Duración de cada fila (en string en formato "xx h xx min")
						String horaI = t.get( i, "Hora a la que se unió" );  // Hora de inicio de cada fila
						String horaF = t.get( i, "Hora a la que salió" );  // Hora de inicio de cada fila
						String fechaHora = fecha + " (" + horaI + "-" + horaF + ")";  // Fecha formateada con horas de cada fila
						// T1
						if (!mUsuarios.containsKey( nombre + correo )) {
							UsuarioMeet um = new UsuarioMeet( nombre, correo );
							um.incNumSesiones();
							um.incDurTotalMins( calcMinutos( duracion ) );
							um.setHorasDeConexion( fechaHora );
							mUsuarios.put( um.getClave(), um );
						} else {
							UsuarioMeet um = mUsuarios.get( nombre + correo );
							um.incNumSesiones();
							um.incDurTotalMins( calcMinutos( duracion ) );
							um.setHorasDeConexion( um.getHorasDeConexion() + " > " + fechaHora );
						}
					}
					sFechas.add( fecha );
				} catch (Exception e) {
					// Tabla no de duraciones - nada que hacer
				}
			}
		}
		// Calcula tabla visual - añade a tIntegra una línea por cada usuario del mapa
		// T1
		if (mUsuarios.size()>0) {
			while (tIntegra.size()>0) tIntegra.removeRow(0);  // Quita todo y vuelve a poner
			for (UsuarioMeet um : mUsuarios.values()) {
				ArrayList<String> linea = new ArrayList<String>( Arrays.asList( 
					um.getNombre(), um.getEmail(), um.getNumSesiones()+"", duracionAHHMM(um.getDurTotalMins()) , um.getHorasDeConexion() ));
				tIntegra.addDataLine( linea );
			}
		}
		vIntegra.getJTable().revalidate();  // Refresca la tabla visual con la nueva tabla de datos calculada
		ventana.getJDesktopPane().moveToFront( vIntegra );
		return mUsuarios.size() > 0;
	}
	
	// Utilidades
	
	/** Devuelve la duración en formato de horas - minutos
	 * @param minutos	Minutos de duración
	 * @return	Esa duración en formato <num> h <num> min
	 */
	public static String duracionAHHMM( int minutos ) {
		return (minutos/60) + " h " + (minutos%60) + " min";
	}
	
	/** Calcula los minutos partiendo del string en formato <num> h <num> min
	 * @param duracion	Duración en formato <num> h <num> min
	 * @return	Minutos correspondientes a esa duración
	 */
	public static int calcMinutos( String duracion ) {
		int ret = 0;
		int posiH = duracion.toLowerCase().indexOf( "h" );
		int posiM = duracion.toLowerCase().indexOf( "min" );
		try {
			if (posiH>0) {
				ret = Integer.parseInt( duracion.substring( 0, posiH ).trim()) * 60;
			}
			if (posiM>0) {
				ret = ret + Integer.parseInt( duracion.substring( posiH+1, posiM ).trim() );
			}
		} catch (Exception e) { e.printStackTrace(); }
		return ret;
	}

	
	
		private static int xIni = 0;
		private static int yIni = 0;
	protected static void cargaCSVMeet( String fic ) {
		ventana.setMensaje( "Arrastra ficheros csv de asistencia al panel. Acciones -> Integrar para integrar datos" );
		try {
			// Saca fecha
			Date fecha = null;
			try {
				Pattern patFecha = Pattern.compile( ".*\\d\\d\\d\\d-\\d\\d-\\d\\d" );  // Fecha dddd-dd-dd  (aaaa-mm-dd)
				Matcher m = patFecha.matcher( fic );
				if (m.find()) {
					int fin = m.end();
					String fechaS = fic.substring( fin-10, fin );
					fecha = sdf2.parse( fechaS );
				}
			} catch (Exception e) {}
			
			// Crea la tabla con todos los datos
			Tabla tablaCSV = Tabla.processCSV( new File( fic ).toURI().toURL() );
			VentanaDatos vd = newVentanaTabla( ventana, tablaCSV, "CSV cargado", xIni, yIni );
			lTablas.add( tablaCSV );
			if (fecha==null) {
				vd.setTitle( "Datos de fecha indefinida - se supone hoy: " + sdf.format( new Date() ) );
				lFechas.add( sdf.format( new Date() ) );
			} else {
				vd.setTitle( "Datos de " + sdf.format( fecha ));
				lFechas.add( sdf.format( fecha ) );
			}
			xIni += 10; yIni += 10;
			// System.out.println( tablaCSV.getHeadersTabs( "" ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	// T3
	
	protected static void compruebaIntegracionConBD() {
		// T3
		new Thread( () -> {
				iniciaBD();
				for (int j=0; j<lTablas.size(); j++) {
					Tabla t = lTablas.get(j);
					try {
						for (int i=0; i<t.size(); i++) {
							String nombre = t.get( i, "Nombre" );
							String correo = t.get( i, "Correo" );
							int duracion = calcMinutos( t.get( i, "Duración" ) );
							integraEnBD( nombre, correo, duracion );
						}
					} catch (Exception e) {
						// Otra tabla, no de sesiones meet. Nada que hacer
					}
				}
				muestraYCierraBD();
		} ).start();
	}
	
	// T3
	private static Connection con;
	private static Statement stat;
	
	// Inicia la conexión de BD y crea la tabla de usuarios vacía
	private static void iniciaBD() {
		String com;
		try {
			// Inicia conexión y sentencia
			Class.forName( "org.sqlite.JDBC" );
			con = DriverManager.getConnection( "jdbc:sqlite:integracionMeet.db" );
			stat = con.createStatement();
			// Crea tabla
			com = "drop table usuario;";
			System.out.println( com );
			stat.executeUpdate( com );
			com = "create table usuario( nombre STRING, correo STRING, sesiones INT, duracion INT )";
			System.out.println( com );
			stat.executeUpdate( com );
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	// Integra cada registro de meet en la tabla de base de datos de datos integrados por usuario (añadiendo o modificando la fila según corresponda)
	private static void integraEnBD( String nombre, String correo, int duracion ) {
		String com;
		try {
			com = "select * from usuario where nombre='" + nombre + "' and correo='" + correo + "';";
			System.out.println( com );
			ResultSet rs = stat.executeQuery( com );
			if (rs.next()) {  // Ya existe - hay que actualizarlo
				int antSes = rs.getInt( "sesiones" );
				int antDur = rs.getInt( "duracion" );
				com = "update usuario set sesiones=" + (antSes+1) + ", duracion=" + (duracion+antDur) + " where nombre = '" + nombre + "' and correo='" + correo + "';";
				System.out.println( com );
				stat.executeUpdate( com );
			} else {  // No existe - hay que insertar nuevo
				com = "insert into usuario values('" + nombre + "', '" + correo + "',1," + duracion + ");";
				System.out.println( com );
				stat.executeUpdate( com );
			}
		} catch (Exception e) { e.printStackTrace(); }
	}

	// Muestra en consola los usuarios ordenados por nombre y cierra la base de datos
	private static void muestraYCierraBD() {
		try {
			String com = "select * from usuario order by nombre;";
			System.out.println( com );
			ResultSet rs = stat.executeQuery( com );
			while (rs.next()) {
				System.out.println( rs.getString("nombre") + "\t" + rs.getInt("sesiones") + "\t" + ProcesoCSVMeet.duracionAHHMM( rs.getInt("duracion") ) );
			}
			stat.close();
			con.close();
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
}

