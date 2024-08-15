package ejemplos;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm.Coloring;
import org.jgrapht.graph.DefaultEdge;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Style;

public class Ejemplo3 {

	public static void todosLosApartados(Graph<String, DefaultEdge> gf, String file) {
		GreedyColoring<String, DefaultEdge> alg = new GreedyColoring<>(gf);
		Coloring<String> coloring = alg.getColoring();
		System.out.println("Mesas necesarias: " + coloring.getNumberColors());
		System.out.println("Composición de las mesas: ");
		List<Set<String>> composicion = coloring.getColorClasses();
		for (int i = 0; i < composicion.size(); i++) {
			System.out.println("Mesa número " + (i + 1) + ": " + composicion.get(i));
		}

		Map<String, Integer> map = coloring.getColors();

		GraphColors.toDot(gf,
				"resultados/ejemplos/ejemplo3/" + file + ".gv", 
				v -> v.toString(), 
				e -> "",
				v -> GraphColors.color(map.get(v)), 
				e -> GraphColors.style(Style.bold));

		System.out.println(file + ".gv generado en resultados/ejemplos/ejemplo3");

	}

}
