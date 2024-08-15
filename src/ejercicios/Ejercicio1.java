package ejercicios;

import java.util.List;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;
import datos.Persona;
import datos.Relacion;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Style;
import us.lsi.common.List2;
import us.lsi.common.Set2;


public class Ejercicio1 {

	// ========================================================================================
	// APARTADO A
	// ========================================================================================
	/*a. Obtenga una vista del grafo que sólo incluya las personas cuyos padres aparecen 
	en el grafo, y ambos han nacido en la misma ciudad y en el mismo año. Muestre 
	el grafo configurando su apariencia de forma que se resalten los vértices y las 
	aristas de la vista.*/
	
	
	private static List<Persona> getPadres(Graph<Persona, Relacion> gf, Persona p) {
		Set<Relacion> aristasPadres = gf.incomingEdgesOf(p); // añado las aristas que le llegan al vértice p
		List<Persona> padres = List2.empty();
		for (Relacion a : aristasPadres) { 		// recorro las aristas (padres)
			padres.add(gf.getEdgeSource(a)); // añado los vértices de donde sale la arista es decir los padres
		}
		return padres;
	}

	public static Set<Persona> apartadoA(Graph<Persona, Relacion> gf) {
		Set<Persona> res = Set2.empty();
		Set<Persona> personas = gf.vertexSet(); 		// conjunto con todas las personas del grafo
		for (Persona p : personas) { 					// recorro todas las personas
			List<Persona> padres = getPadres(gf, p); 	// obtengo una lista con los padres de cada persona

			if (padres.size() == 2 // si tiene 2 padres y ambos tienen el mismo año y ciudad de nacimiento los
									// añado a la lista
					&& padres.get(0).ciudadNacimiento().equals(padres.get(1).ciudadNacimiento())
					&& padres.get(0).anyo().equals(padres.get(1).anyo())) {
				res.add(p);
			}
		}
		return res;

	}

	public static void apartadoAColorear(Graph<Persona, Relacion> gf, Set<Persona> ls, String file) {

		GraphColors.toDot(gf, "resultados/ejercicios/ejercicio1/" + file + ".gv", // nombre del fichero que voy a guardar
																				// la solución
				p -> p.nombre(), 						// los vertices van a ser el nombre
				r -> "", 								// las aristas son vacias
				p -> GraphColors.color(ls.contains(p) ? // vértices en la lista --> solución (azul)						
						Color.blue : Color.black),
				r -> GraphColors.style(Style.solid));
	}

	// ========================================================================================
	// APARTADO B
	// ========================================================================================
	
	/*b. Implemente un algoritmo que dada una persona devuelva un conjunto con todos 
	sus ancestros que aparecen en el grafo. Muestre el grafo configurando su 
	apariencia de forma que se resalte la persona de un color y sus ancestros de otro. */
	
	
	public static Set<Persona>ancestros(Graph<Persona,Relacion>gf,Persona p,Set<Persona>ls){
		
		List<Persona>padres = getPadres(gf, p); //obtengo los padres de la persona
		for(Persona padre : padres) { //por cada padre hago una llamada recursiva para obtener sus padres
			ls.add(padre); //añado sus padres a la lista
			ancestros(gf, padre, ls);
		}

		return ls;
	}
	
	
	public static Set<Persona> apartadoB(Graph<Persona, Relacion> gf ,Persona p) {
		
		//llamo al método ancestros que devuelve todos los ancestros
		 Set<Persona> res = ancestros(gf, p, Set2.empty()); 

		return res;

	}
	public static void apartadoBColorear(Graph<Persona, Relacion> gf, Set<Persona> ls,Persona persona,String file) {

		GraphColors.toDot(gf, "resultados/ejercicios/ejercicio1/" + file + ".gv", 							
				p -> p.nombre(), 						
				r -> "", 								
				p -> GraphColors.color(p.equals(persona)? //vertice es igual a la persona
					Color.red :							//coloreo de rojo
					ls.contains(p) ? 					//si en los vertices están en los ancestros(azul)			
					Color.blue : Color.black),
				
				r -> GraphColors.style(Style.solid));
	}
	
	// ========================================================================================
	// APARTADO C
	// ========================================================================================
	
	/*Implemente un algoritmo que dadas dos personas devuelva un valor entre los 
	posibles del enumerado {Hermanos, Primos, Otros} en función de si son
	hermanos, primos hermanos, o ninguna de las dos cosas. Tenga en cuenta que 2 
	personas son hermanas en caso de que tengan al padre o a la madre en común, y 
	primas en caso de tener al menos un abuelo/a en común. */
	
	public enum PrimosHermanosOtros{
		HERMANOS,PRIMOS,OTROS	
	}
	
	public static List<Persona>getAbuelos(Graph<Persona,Relacion>gf,Persona p){
		
		List<Persona>res = List2.empty();
		List<Persona> padres = getPadres(gf, p);
		List<Persona> abuelos = List2.empty();
		for (Persona p0 : padres ) {
			abuelos = getPadres(gf, p0);
			for(Persona abuelo:abuelos) {
				res.add(abuelo);
			}
		}
		return res;
	}
	
	public static PrimosHermanosOtros apartadoC(Graph<Persona,Relacion>gf,Persona p1,Persona p2) {
		
		
		List<Persona> abuelosP1 = getAbuelos(gf, p1);
		List<Persona> abuelosP2 = getAbuelos(gf,p2);
		
		List<Persona> padresP1 = getPadres(gf, p1);
		List<Persona> padresP2 = getPadres(gf, p2);
		
		PrimosHermanosOtros res = PrimosHermanosOtros.OTROS;
		
		for(Persona p0 : padresP1) {
				if (padresP2.contains(p0)) {
					res= PrimosHermanosOtros.HERMANOS;
					break;	
			}
				for(Persona a0 : abuelosP1) {
					if (abuelosP2.contains(a0)) {
						res= PrimosHermanosOtros.PRIMOS;
						break;	
					}
				}	
		}
		return res;
	}	
	// ========================================================================================
	// APARTADO D
	// ========================================================================================
	
	/*d. Implemente un algoritmo que devuelva un conjunto con todas las personas que
	tienen hijos/as con distintas personas. Muestre el grafo configurando su apariencia
	de forma que se resalten las personas de dicho conjunto.*/
	
	private static List<Persona> getHijos (Graph<Persona,Relacion>gf,Persona p){
		Set<Relacion> aritasHijos = gf.outgoingEdgesOf(p);
		List<Persona> hijos = List2.empty();
		for(Relacion r : aritasHijos) {
			hijos.add(gf.getEdgeTarget(r));
		}
		return hijos;	
	}
	public static Set<Persona> apartadoD (Graph<Persona,Relacion>gf){
		/*Vamos a preguntar a cada persona del grafo quienes son sus hijos y a cada hijo de la 
		 * persona p vamos a preguntar quienes son sus padres. Sus padres los metemos en un Set de tal formal 
		 * que cada hijo tiene que tener 2 padres, si hemos preguntado a todos los hijos y uno de ellos tiene un padre
		 * diferente en el conjunto que hemos creado habrá 3 padres en vez de dos es decir que esa persona ha tenido 
		 * un hijo con más de una persona*/
		
		Set<Persona>res = Set2.empty();
		Set<Persona>personas = gf.vertexSet(); //Todas las personas del grafo
		for (Persona p: personas) {
			List<Persona>hijos = getHijos(gf, p); //hijos de cada persona
			if(!hijos.isEmpty()){
				Set<Persona> padresCadaHijo = Set2.empty(); //Padres de cada hijo
				for(Persona h :hijos) { // a cada hijo le preguntamos cuales son sus padres
					List<Persona> padres = getPadres(gf, h);
					for(Persona padre: padres) {
						padresCadaHijo.add(padre); //añadimos los padres al set 
					}	
				}
				if(padresCadaHijo.size()>2) { //si hay mas de dos padres significa que todos los hermanos no tienen los mismos padres
					res.add(p);
				}
			}
		}
		return res;

	}
	public static void apartadoDColorear(Graph<Persona, Relacion> gf, Set<Persona> ls,String file) {

		GraphColors.toDot(gf, "resultados/ejercicios/ejercicio1/" + file + ".gv", 							
				p -> p.nombre(), 						
				r -> "", 								
				p -> GraphColors.color(						
					ls.contains(p) ?								
					Color.blue : Color.black),
				
				r -> GraphColors.style(Style.solid));
	}
	// ========================================================================================
	// APARTADO E
	// ========================================================================================
	
	/*e. Se desea seleccionar el conjunto mínimo de personas para que se cubran todas
	las relaciones existentes. Implemente un método que devuelva dicho conjunto.
	Muestre el grafo configurando su apariencia de forma que se resalten las personas
	de dicho conjunto.*/	
	
	public static Set<Persona> apartadoE (Graph<Persona,Relacion>gf) {

		GreedyVCImpl vCover = new GreedyVCImpl(gf);
		Set<Persona> res = vCover.getVertexCover();
		return res;
	}
	
	public static void apartadoEColorear(Graph<Persona, Relacion> gf,Set<Persona>ls,String file) {

		GraphColors.toDot(gf, "resultados/ejercicios/ejercicio1/" + file + ".gv", 							
				p -> p.nombre(), 						
				r -> "", 								
				p -> GraphColors.color(						
					ls.contains(p) ?								
					Color.blue : Color.black),
				r -> GraphColors.style(Style.solid));
	}
}
