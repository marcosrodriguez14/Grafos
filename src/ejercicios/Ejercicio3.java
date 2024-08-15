package ejercicios;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm.Coloring;
import org.jgrapht.graph.DefaultEdge;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Style;

public class Ejercicio3 {
	public static void todosLosApartados(Graph<String, DefaultEdge> gf, String file) {
		GreedyColoring<String, DefaultEdge> alg = new GreedyColoring<>(gf);
		Coloring<String> coloring = alg.getColoring();
		System.out.println("Numero de franjas horarias necesarias: " + coloring.getNumberColors());
		System.out.println("Actividades para impartirse en paralelo por franja horaria: ");
		List<Set<String>> composicion = coloring.getColorClasses();
		for (int i = 0; i < composicion.size(); i++) {
			System.out.println("Franja numero " + (i + 1) + ": " + composicion.get(i));
		}

		Map<String, Integer> map = coloring.getColors();

		GraphColors.toDot(gf,
				"resultados/ejercicios/ejercicio3/" + file + ".gv", 
				v -> v.toString(), 
				e -> "",
				v -> GraphColors.color(map.get(v)), 
				e -> GraphColors.style(Style.arrowhead));


	}
}
