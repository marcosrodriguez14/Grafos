package ejercicios;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.tour.HeldKarpTSP;
import datos.Ciudad;
import datos.Trayecto;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Style;
import us.lsi.common.Set2;
import us.lsi.graphs.views.SubGraphView;

public class Ejercicio2 {
	
	// ========================================================================================
	// APARTADO A
	// ========================================================================================
	/*a. Determine cuántos grupos de ciudades hay y cuál es su composición. Dos
	ciudades pertenecen al mismo grupo si están relacionadas directamente entre sí o
	si existen algunas ciudades intermedias que las relacionan. Muestre el grafo
	configurando su apariencia de forma que se coloree cada grupo de un color
	diferente.*/
	
	public static List<Set<Ciudad>> apartadoA(Graph<Ciudad, Trayecto> gf) {
		//Componentes conexas

		ConnectivityInspector alg = new ConnectivityInspector<>(gf);
		List<Set<Ciudad>> ls = alg.connectedSets(); //Listas con las componentes conexas que hay

		
		Set<Ciudad> vertices0 = ls.get(0); //vertices primera componente
		Set<Trayecto> aristas0 = Set2.empty(); //aristas primera componente
		for(Ciudad v : vertices0) {
			aristas0.addAll(gf.edgesOf(v));
		}	
		GraphColors.toDot(gf,
						"resultados/ejercicios/ejercicio2/" + "Apartado A" + ".gv", 
						x -> x.nombre(),
						x -> "",
						v -> GraphColors.color(vertices0.contains(v)?Color.green:Color.yellow),
						e -> GraphColors.color(aristas0.contains(e)?Color.green:Color.yellow));
		return ls;
	}
	// ========================================================================================
	// APARTADO B
	// ========================================================================================
	/*b. Determine cuál es el grupo de ciudades a visitar si se deben elegir las ciudades
	conectadas entre sí que maximice la suma total de las puntuaciones. Muestre el
	grafo configurando su apariencia de forma que se resalten dichas ciudades.*/
	public static Set<Ciudad> apartadoB(Graph<Ciudad, Trayecto> gf) {
		//Componentes conexas

		ConnectivityInspector alg = new ConnectivityInspector<>(gf);
		List<Set<Ciudad>> ls = alg.connectedSets();

		
		Set<Ciudad> vertices0 = ls.get(0);
		Set<Trayecto> aristas0 = Set2.empty();
		for(Ciudad v : vertices0) {
			aristas0.addAll(gf.edgesOf(v));
		}	
		Set<Ciudad> vertices1 = ls.get(1);
		Set<Trayecto> aristas1 = Set2.empty();
		for(Ciudad v : vertices1) {
			aristas1.addAll(gf.edgesOf(v));
		}	
		
		Integer puntuacion0=0;  //Puntuacion del primer grupo de ciudades
		for (Ciudad v : vertices0) {
			puntuacion0=puntuacion0+v.puntuacion();
		}
		Integer puntuacion1=0; //Puntuacion del segundo grupo de ciudades
		for (Ciudad v : vertices1) {
			puntuacion1=puntuacion1+v.puntuacion();
		}

		Set<Ciudad>ciudadesPuntacionMasAlta = puntuacion0>puntuacion1? vertices0:vertices1; // nos quedamos con el mas alto
		Set<Trayecto>aristasPuntacionMasAlta = Set2.empty();

		for(Ciudad v : ciudadesPuntacionMasAlta) {
			aristasPuntacionMasAlta.addAll(gf.edgesOf(v));
		}	
	
		GraphColors.toDot(gf,
						"resultados/ejercicios/ejercicio2/" + "Apartado B" + ".gv", 
						x -> x.nombre() +"\n"+x.puntuacion()+" puntos",
						x -> "",
						v -> GraphColors.color(ciudadesPuntacionMasAlta.contains(v)?Color.blue:Color.blank),
						e -> GraphColors.color(aristasPuntacionMasAlta.contains(e)?Color.blue:Color.blank));
		
		return ciudadesPuntacionMasAlta;
	}
	// ========================================================================================
		// APARTADO C
		// ========================================================================================
		/*c. Determine cuál es el grupo de ciudades a visitar si se deben elegir las ciudades
		conectadas entre sí que den lugar al camino cerrado de menor precio que pase por
		todas ellas. Muestre el grafo configurando su apariencia de forma que se resalte
		dicho camino.*/

	public static List<List<String>>  apartadoC(Graph<Ciudad, Trayecto> gf) {

		// Obtenemos todas las componentes conexas
		
		ConnectivityInspector alg = new ConnectivityInspector<>(gf);
		List<Set<Ciudad>> ls = alg.connectedSets();
		
		//Creamos subgrafo componente conexa 0
		Set<Ciudad> vertices0 = ls.get(0);
		Predicate<Ciudad> pv0 = c -> vertices0.contains(c);
		Predicate<Trayecto> pe0 = p -> vertices0.contains(gf.getEdgeSource(p)) && vertices0.contains(gf.getEdgeTarget(p));
		Graph<Ciudad, Trayecto> comp0 = SubGraphView.of(gf,pv0,pe0);
		
		
		//Creamos subgrafo componente conexa 1
		Set<Ciudad> vertices1 = ls.get(1);
		Predicate<Ciudad> pv1 = c -> vertices1.contains(c);
		Predicate<Trayecto> pe1 = p -> vertices1.contains(gf.getEdgeSource(p)) && vertices1.contains(gf.getEdgeTarget(p));
		Graph<Ciudad, Trayecto> comp1 = SubGraphView.of(gf,pv1,pe1);
		
		//comparamos que precio es el menor para visitar todas las ciudades
		
		Double precioComp0= new HeldKarpTSP().getTour(comp0).getWeight(); //Precio comp0
		List<String> verticesComp0= new HeldKarpTSP().getTour(comp0).getVertexList(); //camino minimo vertices comp0
		List<String> aristasComp0= new HeldKarpTSP().getTour(comp0).getEdgeList();//camino minimo aristas comp0
		
		Double precioComp1= new HeldKarpTSP().getTour(comp1).getWeight();//Precio comp0
		List<String> verticesComp1= new HeldKarpTSP().getTour(comp1).getVertexList();//camino minimo vertices comp0
		List<String> aristasComp1= new HeldKarpTSP().getTour(comp1).getEdgeList();//camino minimo aristas comp0
		
		//cogemos el conjunto de ciudades que tenga el menor precio
		List<String> resVertices = precioComp0 > precioComp1? verticesComp1:verticesComp0;
		List<String> resAristas = precioComp0 > precioComp1? aristasComp1:aristasComp0;
		
		//Nos quedamos con el precio que nos cuestan  recorrer esas ciudades
		Double resPrecio = precioComp0 > precioComp1? precioComp1:precioComp0;
		List<List<String>> res = List.of(resVertices,List.of(resPrecio.toString()));
		

		GraphColors.toDot(gf,
				"resultados/ejercicios/ejercicio2/" + "Apartado C" + ".gv", 
				x -> x.nombre(),
				x -> x.euros().toString() + " euros",
				v -> GraphColors.color(resVertices.contains(v)?Color.blue:Color.blank),
				e -> GraphColors.color(resAristas.contains(e)?Color.blue:Color.blank));

	
		return res;
	}
	
	// ========================================================================================
	// APARTADO D
	// ========================================================================================
	/*d. De cada grupo de ciudades, determinar cuáles son las 2 ciudades (no conectadas
	directamente entre sí) entre las que se puede viajar en un menor tiempo. Muestre
	el grafo configurando su apariencia de forma que se resalten las ciudades y el
	camino entre ellas.*/

	private static Ciudad getVertexOf(Graph<Ciudad, Trayecto> graph, String nombre) {
		return graph.vertexSet().stream().filter(c -> c.nombre().equals(nombre)).findFirst().get();
	}
	
	public static Double apartadoD(Graph<Ciudad, Trayecto> gf,String file, String c1, String c2) {
		// Camino minimo entre origen y destino: Dijkstra
		DijkstraShortestPath<Ciudad, Trayecto> alg = new DijkstraShortestPath<>(gf);

		Ciudad origen = getVertexOf(gf, c1);
		Ciudad destino = getVertexOf(gf, c2);

		GraphPath<Ciudad, Trayecto> path = alg.getPath(origen, destino);
		Double  tiempo  = path.getWeight();

		GraphColors.toDot(gf, "resultados/ejercicios/ejercicio2/" + file + ".gv", 
				x -> x.nombre(),
				x -> x.minutos().toString() + " minutos",
				v -> GraphColors.styleIf(Style.bold, path.getVertexList().contains(v)),
				e -> GraphColors.styleIf(Style.bold, path.getEdgeList().contains(e)));

		return tiempo;
	}
	
	
	
	
}
