package ejemplos;

import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleWeightedGraph;

import datos_ejemplos.Carretera;
import datos_ejemplos.Ciudad;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Style;
import us.lsi.graphs.Graphs2;

public class Ejemplo2 {
	
	//========================================================================================
	//APARTADO A
	//========================================================================================

	private static Boolean someEqualsTo1000(Ciudad v, Graph<Ciudad, Carretera> g) {
		boolean res = false;
		for (Carretera c : g.edgesOf(v)) {
			if (c.km() == 1000.) {
				res = true;
				break;
			}
		}

		return res;
	}

	public static void apartadoA(SimpleWeightedGraph<Ciudad, Carretera> gf, String file) {
		// Grafo no completo a completo
		Graph<Ciudad, Carretera> g = Graphs2.explicitCompleteGraph(gf, 1000., Graphs2::simpleWeightedGraph,
				() -> Carretera.of(1000.), Carretera::km);
		GraphColors.toDot(g, "resultados/ejemplos/ejemplo2/" + file + "A.gv", x -> x.nombre(), x -> "",
				v -> GraphColors.colorIf(Color.blue, someEqualsTo1000(v, g)),
				// v -> GraphColors.colorIf(Color.blue, g.edgesOf(v).stream().anyMatch(e ->
				// ((Carretera) e).km() == 100.)),
				e -> GraphColors.colorIf(Color.blue, g.getEdgeWeight(e) == 1000.));

		System.out.println(file + "A.gv generado en resultados/ejemplo2");

	}
	//========================================================================================
	//APARTADO B
	//========================================================================================

	private static Ciudad getVertexOf(Graph<Ciudad, Carretera> graph, String nombre) {
		return graph.vertexSet().stream().filter(c -> c.nombre().equals(nombre)).findFirst().get();
	}

	public static void apartadoB(SimpleWeightedGraph<Ciudad, Carretera> gf, String file, String c1, String c2) {
		// Camino minimo entre origen y destino: Dijkstra
		DijkstraShortestPath<Ciudad, Carretera> alg = new DijkstraShortestPath<>(gf);

		Ciudad origen = getVertexOf(gf, c1);
		Ciudad destino = getVertexOf(gf, c2);

		GraphPath<Ciudad, Carretera> path = alg.getPath(origen, destino);

		GraphColors.toDot(gf, "resultados/ejemplos/ejemplo2/" + file + "B.gv", x -> x.nombre(), x -> x.nombre(),
				v -> GraphColors.styleIf(Style.bold, path.getVertexList().contains(v)),
				e -> GraphColors.styleIf(Style.bold, path.getEdgeList().contains(e)));

		System.out.println(file + "B.gv generado en  resultados/ejemplos/ejemplo2");

	}
	//========================================================================================
	//APARTADO C
	//========================================================================================
	
	/* Necesario para el Ejemplo 2 c)
		public static Carretera of(Double kms, String nombre) {
	        Double km = kms;
	        String nomb = nombre;           
	        Integer id = num;
	        num++;
	        return new Carretera(id, km, nomb);
		} */
	
	
	//====================================================================================================================
		//Apartado C
		//====================================================================================================================
			
		public static void apartadoC(SimpleWeightedGraph<Ciudad, Carretera> gf, String file) {
			
			//Para pasar de no dirigido a dirigido: toDirectedWeightedGraph
			Graph<Ciudad,Carretera> g = Graphs2.toDirectedWeightedGraph(gf, (Carretera x) -> Carretera.of(x.km(), x.nombre()));
			
			GraphColors.toDot(g,
							"resultados/ejemplos/ejemplo2/" + file + "C.gv",
							x -> x.nombre(),
							x -> x.nombre(),
							v -> GraphColors.color(Color.black),
							e -> GraphColors.style(Style.bold));
			
			System.out.println(file + "C.gv generado en resultados/ejemplo2");
		}
		
		//====================================================================================================================
		//Apartado D
		//====================================================================================================================
				
		public static void apartadoD(SimpleWeightedGraph<Ciudad, Carretera> gf, String file) {
			//Componentes conexas
	    //Carro con sapato: https://youtube.com/shorts/9ZeiC19xqV8
			ConnectivityInspector alg = new ConnectivityInspector<>(gf);
			List<Set<Ciudad>> ls = alg.connectedSets();
			System.out.println("Hay " + ls.size() + " componentes conexas.");
			
			GraphColors.toDot(gf,
							"resultados/ejemplos/ejemplo2/" + file + "D.gv", 
							x -> x.nombre(),
							x -> x.nombre(),
							v -> GraphColors.color(asignaColor(v,ls,alg)),
							e -> GraphColors.color(asignaColor(gf.getEdgeSource(e), ls, alg)));
			
			System.out.println(file + "D.gv generado en resultados/ejemplos/ejemplo2");
		}
		
		private static Color asignaColor(Ciudad v, List<Set<Ciudad>> ls, ConnectivityInspector<Ciudad, Carretera> alg) {
			Color[] vc = Color.values();//Lista de colores
			Set<Ciudad> s = alg.connectedSetOf(v); //La componente conexa a la que pertenece el vertice
			return vc[ls.indexOf(s)]; 
		}
	
	
	

}
