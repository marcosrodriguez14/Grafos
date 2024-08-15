package tests_ejemplos;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import ejemplos.Ejemplo3;
import us.lsi.colors.GraphColors;
import us.lsi.common.Files2;
import us.lsi.graphs.Graphs2;

public class TestEjemplo3 {

	public static void main(String[] args) {
		testEjemplo3("Comensales");
	}

	public static void testEjemplo3(String file) {
		Graph<String, DefaultEdge> g = Graphs2.simpleGraph(String::new, DefaultEdge::new, false);

		Files2.streamFromFile("ficheros/" + file + ".txt").forEach(linea -> {
			String[] v = linea.split(",");
			g.addVertex(v[0]);
			g.addVertex(v[1]);
			g.addEdge(v[0], v[1]);
		});

		GraphColors.toDot(g, "resultados/ejemplos/ejemplo3/" + file + "_inicial.gv");

		Ejemplo3.todosLosApartados(g, file);

	}

}
