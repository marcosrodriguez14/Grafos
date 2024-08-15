package ejemplos;

import java.util.Set;
import java.util.function.Predicate;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm.SpanningTree;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;

import datos_ejemplos.Pasillo;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.views.SubGraphView;

public class Ejemplo4 {
	
	public static Set<String> apartadoA(Graph<String, Pasillo> gf){
		//Recubrimiento de vertices porque me da el número mínimo de vertices que "tocan" 
		//todas las aristas del grafo
		
		GreedyVCImpl<String, Pasillo> algA = new GreedyVCImpl<>(gf);
		Set<String> cruces = algA.getVertexCover();
		return cruces;
	}
	
	public static void apartadoB(Graph<String, Pasillo> gf, Set<String> cruces, String file) {
		
		//Filtrar por los vértices con los que nos hemos quedado en A)
		Predicate<String> pv = c -> cruces.contains(c);
		//Filtrar las aristas con las que nos hemos quedado en A)
		Predicate<Pasillo> pe = p -> cruces.contains(gf.getEdgeSource(p)) && cruces.contains(gf.getEdgeTarget(p));
		
		//Obtengo la vista del grafo de A)
		Graph<String, Pasillo> sgf = SubGraphView.of(gf, pv, pe);
		
		//Para obtener el número de equipos --> componentes conexas
		ConnectivityInspector<String, Pasillo> algB1 = new ConnectivityInspector<>(sgf);
		System.out.println("Numero de equipos necesarios: " + algB1.connectedSets().size());
		
		//Para calcular los metros de cable minimizando costes --> Kruskal
		//Porque es recubirmiento mínimo y me asegura que toco todos los vértices del grafo
		//minimizando la suma de los pesos de las aristas
		KruskalMinimumSpanningTree<String, Pasillo> algB2 = new KruskalMinimumSpanningTree<>(sgf);
		SpanningTree<Pasillo> tree = algB2.getSpanningTree();
		System.out.println(String.format("Metros de cable necesarios: %.1f" , tree.getWeight()));
		
		//Apartado C)
		
		GraphColors.toDot(gf,
				"resultados/ejemplos/ejemplo4/" + file + ".gv",
				c -> c.toString(),
				p -> "",
				v -> GraphColors.colorIf(Color.blue, Color.blank ,cruces.contains(v)),
				e -> GraphColors.colorIf(Color.blue, Color.blank, tree.getEdges().contains(e)));
		
	}
}
