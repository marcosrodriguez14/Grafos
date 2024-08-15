package tests;

import java.util.List;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import us.lsi.colors.GraphColors;
import us.lsi.common.Files2;
import us.lsi.graphs.Graphs2;

public class TestEjercicio3 {
	public static void main(String[] args) {

		System.out.println("==============================================================================");
		System.out.println("                             EJERCICIO 3                                      ");
		System.out.println("============================================================================== \n");
		
		System.out.println("==============================================================================");
		System.out.println("                 	ENTRADA A ");
		System.out.println("==============================================================================");
		TestEjercicio3A("PI3E3A_DatosEntrada");
		System.out.println("==============================================================================");
		System.out.println("                 	ENTRADA B ");
		System.out.println("==============================================================================");
		TestEjercicio3B("PI3E3B_DatosEntrada");
	}
	public static void TestEjercicio3A(String file) {
		Graph<String, DefaultEdge> g = Graphs2.simpleGraph(String::new, DefaultEdge::new, false);
	
		List<String> lineas=Files2.streamFromFile("ficheros/" + file + ".txt").toList();
		Integer numlinea=0;
		for(String linea:lineas) {
			numlinea++;
			 String []linea2 = linea.replace("Alumno"+numlinea+": ", "").trim().split(",");
				
			for(int i = 0; i<linea2.length;i++) {
				if(linea2.length>1) {
				if(i+1 < linea2.length) {
				g.addVertex(linea2[i].trim());
				g.addVertex(linea2[i+1].trim());
				g.addEdge(linea2[i].trim(), linea2[i+1].trim());}
				else {
					g.addVertex(linea2[i].trim());
					g.addVertex(linea2[0].trim());
					g.addEdge(linea2[i].trim(), linea2[0].trim());
				}}
			}
		}

		GraphColors.toDot(g, "resultados/ejercicios/ejercicio3/" + file + "_inicialA.gv");
		

	ejercicios.Ejercicio3.todosLosApartados(g, "Ejercicio3 A");
	}
	
	public static void TestEjercicio3B(String file) {
		Graph<String, DefaultEdge> g = Graphs2.simpleGraph(String::new, DefaultEdge::new, false);
	
		List<String> lineas=Files2.streamFromFile("ficheros/" + file + ".txt").toList();
		Integer numlinea=0;
		for(String linea:lineas) {
			numlinea++;
			 String []linea2 = linea.replace("Alumno"+numlinea+": ", "").trim().split(",");
				
			for(int i = 0; i<linea2.length;i++) {
				if(linea2.length>1) {
				if(i+1 < linea2.length) {
				g.addVertex(linea2[i].trim());
				g.addVertex(linea2[i+1].trim());
				g.addEdge(linea2[i].trim(), linea2[i+1].trim());}
				else {
					g.addVertex(linea2[i].trim());
					g.addVertex(linea2[0].trim());
					g.addEdge(linea2[i].trim(), linea2[0].trim());
				}}
			}
		}

		GraphColors.toDot(g, "resultados/ejercicios/ejercicio3/" + file + "_inicialB.gv");
		

	ejercicios.Ejercicio3.todosLosApartados(g, "Ejercicio3 B");
	}
}

