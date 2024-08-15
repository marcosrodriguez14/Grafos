package tests;

import java.util.List;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.SimpleWeightedGraph;

import datos.Ciudad;
import datos.Trayecto;
import ejercicios.Ejercicio2;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.views.SubGraphView;

public class TestEjercicio2 {

	public static void main(String[] args) {
//------------DATOS DE ENTRADA ------------------------
		

		System.out.println("==============================================================================");
		System.out.println("                             EJERCICIO 2                                      ");
		System.out.println("============================================================================== \n");
		
		DatosDeEntrada("PI3E2_DatosEntrada");
		//------------APARTADO A-------------------------------
		TestApartadoA("PI3E2_DatosEntrada");
		//------------APARTADO B-------------------------------
		TestApartadoB("PI3E2_DatosEntrada");
		//------------APARTADO C-------------------------------
		TestApartadoC("PI3E2_DatosEntrada");
		//------------APARTADO D-------------------------------
		TestApartadoD("PI3E2_DatosEntrada");
		}

//------------DATOS DE ENTRADA ------------------------
	public static void DatosDeEntrada(String file) {
		//Leer grafo
		Graph<Ciudad, Trayecto> gf = GraphsReader.newGraph("ficheros/" + file + ".txt",
					Ciudad::ofFormat,
					Trayecto::ofFormat,
					Graphs2::simpleDirectedGraph);

			GraphColors.toDot(gf, 
							"resultados/ejercicios/ejercicio2/" +"EntradaEjercicio2"+ ".gv",
							x -> x.toString2(),
							x -> x.toString(),
							v -> GraphColors.color(Color.black),
							e -> GraphColors.color(Color.black));
		}
		
//------------APARTADO A-------------------------------
	public static void TestApartadoA(String file) {
		Graph<Ciudad, Trayecto> gf = GraphsReader.newGraph("ficheros/" + file + ".txt",
		Ciudad::ofFormat,
		Trayecto::ofFormat,
		Graphs2::simpleGraph);
			
	
		List<Set<Ciudad>>ls = Ejercicio2.apartadoA(gf);

		System.out.println("===================APARTADO A ================================================");
		System.out.println("Hay " + ls.size() + " grupos de ciudades ");
		for(int i = 0; i<ls.size();i++) {
			System.out.println("Grupo numero "+(i+1)+":"+ls.get(i));
			}
		System.out.println("==============================================================================");
		}
//------------APARTADO B-------------------------------
	public static void TestApartadoB(String file) {
		Graph<Ciudad, Trayecto> gf = GraphsReader.newGraph("ficheros/" + file + ".txt",
		Ciudad::ofFormat,
		Trayecto::ofFormat,
		Graphs2::simpleGraph);
			
	
		Set<Ciudad>ls = Ejercicio2.apartadoB(gf);

		System.out.println("===================APARTADO B ================================================");
		System.out.println("Grupo de ciudades que maximiza la suma de puntuaciones: "+ ls);
		System.out.println("==============================================================================");
		}	
//------------APARTADO C-------------------------------
	public static void TestApartadoC(String file) {
		SimpleWeightedGraph<Ciudad, Trayecto> graph = GraphsReader.newGraph("ficheros/" + file + ".txt", 
				Ciudad::ofFormat,
				Trayecto::ofFormat, 
				Graphs2::simpleWeightedGraph,
				Trayecto::euros);
	
		
		List<List<String>> ls = Ejercicio2.apartadoC(graph);

		Double Precio = Double.parseDouble(ls.get(1).get(0));
		
		System.out.println("===================APARTADO C ================================================");
		System.out.println("Grupo de ciudades a visitar que dan lugar al camino cerraado de menor precio: "+ ls.get(0)+"--> "+Precio + " euros");
		System.out.println("==============================================================================");
		}	
	
	
//------------APARTADO D-------------------------------
	public static void TestApartadoD(String file) {
		SimpleWeightedGraph<Ciudad, Trayecto> graph = GraphsReader.newGraph("ficheros/" + file + ".txt", 
				Ciudad::ofFormat,
				Trayecto::ofFormat, 
				Graphs2::simpleWeightedGraph,
				Trayecto::minutos);

		;
		
		ConnectivityInspector alg = new ConnectivityInspector<>(graph);
		List<Set<Ciudad>> ls = alg.connectedSets();
		
		Set<Ciudad>componente1 = ls.get(0);
		Set<Ciudad>componente2 = ls.get(1);
		
		Double tiempo1 = Ejercicio2.apartadoD(graph,"Apartado D(componente1)", "Ciudad4","Ciudad3");
		Double tiempo2 = Ejercicio2.apartadoD(graph,"Apartado D(componente2)", "Ciudad8","Ciudad9");
		
			
		System.out.println("===================APARTADO D ================================================");
		System.out.println("Para el grupo "+componente1 +" las ciudades no conectadas directamente entre las que\r\n"
				+ "se puede viajar en menor tiempo son:\r\n"
				+ "Origen: Ciudad8 y Destino: Ciudad9 --> Tiempo: "+tiempo1 +" minutos");
		System.out.println("                                                                          ");
		System.out.println("Para el grupo "+componente2 +" las ciudades no conectadas directamente entre las que\r\n"
				+ "se puede viajar en menor tiempo son:\r\n"
				+ "Origen: Ciudad8 y Destino: Ciudad9 --> Tiempo: "+tiempo2 +" minutos");

		System.out.println("==============================================================================");
			}	
	
}

