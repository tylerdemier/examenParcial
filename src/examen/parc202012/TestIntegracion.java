package examen.parc202012;

// ATENCIÓN!  Enlaza JUnit 4 para poder compilar y ejecutar este test
import static org.junit.Assert.*;
import org.junit.*;


import java.util.Arrays;
import java.util.TreeSet;

public class TestIntegracion {

	// T2
	
	@Test
	public void test() {
		// Carga la ventana y los ficheros de prueba
		ProcesoCSVMeet.preparaVentana();
		ProcesoCSVMeet.cargaCSVMeet( "src/examen/parc202012/meet2020-10-15.csv" );
		ProcesoCSVMeet.cargaCSVMeet( "src/examen/parc202012/meet2020-10-22.csv" );
		ProcesoCSVMeet.cargaCSVMeet( "src/examen/parc202012/meet2020-10-29.csv" );
		ProcesoCSVMeet.cargaCSVMeet( "src/examen/parc202012/meet2020-11-05.csv" );
		// realiza la integración
		ProcesoCSVMeet.actualizaTablaIntegracion();
		// Comprueba que la integración de usuarios es correcta
		int numU = 0;
		for (UsuarioMeet um : ProcesoCSVMeet.mUsuarios.values()) {
			assertEquals( DatosDePrueba.listaPrueba2.get(numU), um );
			numU++;
		}
		// Comprueba que la integración de fechas es correcta
		TreeSet<String> fechasEsperadas = new TreeSet<String>( Arrays.asList( "15/10/2020", "22/10/2020", "29/10/2020", "05/11/2020" ));
		assertEquals( fechasEsperadas, ProcesoCSVMeet.sFechas );
	}

}
