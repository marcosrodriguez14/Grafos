package ejemplos;


import java.util.function.Predicate;

import org.jgrapht.Graph;

import datos_ejemplos.Carretera;
import datos_ejemplos.Ciudad;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.views.SubGraphView;

public class Ejemplo1 {
	
	public static void crearVista(String file, Graph<Ciudad, Carretera> g, Predicate<Ciudad> pv, Predicate<Carretera> pa, String nombreVista) {
		
		//Crear vista
		Graph<Ciudad, Carretera> vista = SubGraphView.of(g, pv, pa);
		
		GraphColors.toDot(vista, 
						"resultados/ejemplos/ejemplo1/" + file + nombreVista + ".gv",
						x -> x.nombre(),
						x -> x.nombre(),
						v -> GraphColors.colorIf(Color.red, vista.edgesOf(v).size() >1),
						e -> GraphColors.color(Color.black));
		
		System.out.println(file + nombreVista + ".gv generado en resultados/ejemplos/ejemplo1");
	}

}
