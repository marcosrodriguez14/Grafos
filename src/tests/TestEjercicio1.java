package tests;
import java.util.Set;
import org.jgrapht.Graph;
import datos.Persona;
import datos.Relacion;
import ejercicios.Ejercicio1;
import ejercicios.Ejercicio1.PrimosHermanosOtros;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class TestEjercicio1 {


	public static void main(String[] args) {

		System.out.println("==============================================================================");
		System.out.println("                             EJERCICIO 1                                      ");
		System.out.println("============================================================================== \n");
		
//------------DATOS DE ENTRADA ------------------------
		DatosDeEntrada("PI3E1A_DatosEntrada");
		DatosDeEntradaB("PI3E1B_DatosEntrada");
		
//------------APARTADO A-------------------------------
		apartadoA_A("PI3E1A_DatosEntrada");
		apartadoA_B("PI3E1B_DatosEntrada");
		
//------------APARTADO B-------------------------------
	
		apartadoB_A("PI3E1A_DatosEntrada");
		apartadoB_B("PI3E1B_DatosEntrada");

//------------APARTADO C-------------------------------
		
		apartadoC_A("PI3E1A_DatosEntrada");
		apartadoC_B("PI3E1B_DatosEntrada");
		
//------------APARTADO D-------------------------------
		
		apartadoD_A("PI3E1A_DatosEntrada");
		apartadoD_B("PI3E1B_DatosEntrada");
		
//------------APARTADO E-------------------------------
		
		apartadoE_A("PI3E1A_DatosEntrada");
		apartadoE_B("PI3E1B_DatosEntrada");
	}
	
//==============================================================
//                   FICHERO ENTRADA A	
//==============================================================
	
//------------DATOS DE ENTRADA ------------------------
	public static void DatosDeEntrada(String file) {
		//Leer grafo
		Graph<Persona, Relacion> gf = GraphsReader.newGraph("ficheros/" + file + ".txt",
						Persona::ofFormat,
						Relacion::ofFormat,
						Graphs2::simpleDirectedGraph);

		GraphColors.toDot(gf, 
						"resultados/ejercicios/ejercicio1/" +"DatosDeEntrada_A"+ ".gv",
						x -> x.toString2(),
						x -> x.toString(),
						v -> GraphColors.color(Color.black),
						e -> GraphColors.color(Color.black));
	}

//------------APARTADO A-------------------------------
	public static void apartadoA_A(String file) {
		Graph<Persona, Relacion> g = GraphsReader.newGraph("ficheros/" + file + ".txt",
				Persona::ofFormat,
				Relacion::ofFormat,
				Graphs2::simpleDirectedGraph);
		
		Set<Persona> s = Ejercicio1.apartadoA(g);

		Ejercicio1.apartadoAColorear(g, s,"Apartado A_A");
		System.out.println("===================APARTADO A_A ================================================");
		System.out.println("Personas cuyos padres aparecen en el grafo y cumplen los requisitos:\n " + s);
		System.out.println("==============================================================================");
	}
//------------APARTADO B-------------------------------
	public static void apartadoB_A(String file) {
			Graph<Persona, Relacion> g = GraphsReader.newGraph("ficheros/" + file + ".txt",
					Persona::ofFormat,
					Relacion::ofFormat,
					Graphs2::simpleDirectedGraph);
			
			Persona Maria = Persona.of(13,"Maria",2008,"Sevilla");
			Set<Persona> s = Ejercicio1.apartadoB(g,Maria);

			Ejercicio1.apartadoBColorear(g, s,Maria,"Apartado B_A");
			System.out.println("===================APARTADO B_A ================================================");
			System.out.println("Ancestros de Maria:\n " + s);
			System.out.println("==============================================================================");
		}
//------------APARTADO C-------------------------------
	public static void apartadoC_A(String file) {
			Graph<Persona, Relacion> g = GraphsReader.newGraph("ficheros/" + file + ".txt",
					Persona::ofFormat,
					Relacion::ofFormat,
					Graphs2::simpleDirectedGraph);
				
				Persona Maria = Persona.of(13,"Maria",2008,"Sevilla");
				Persona Patricia = Persona.of(12,"Patricia",2010,"Cordoba");
				Persona Rafael = Persona.of(16,"Rafael",2020,"Malaga");
				Persona Sara = Persona.of(14,"Sara",2015,"Jaen");
				Persona Carmen = Persona.of(8,"Carmen",1989,"Jaen");
				
				PrimosHermanosOtros MariayPatricia = Ejercicio1.apartadoC(g,Maria,Patricia);
				PrimosHermanosOtros RafaelySara= Ejercicio1.apartadoC(g,Rafael,Sara);
				PrimosHermanosOtros CarmenyRafael= Ejercicio1.apartadoC(g,Carmen,Rafael);

			
				System.out.println("===================APARTADO C_A ================================================");
				System.out.println("Rafael y Sara son: " + RafaelySara);
				System.out.println("Maria y Patricia son: " + MariayPatricia);
				System.out.println("Maria y Patricia son: " + CarmenyRafael);
				System.out.println("==============================================================================");
			}
//------------APARTADO D-------------------------------
	public static void apartadoD_A(String file) {
			Graph<Persona, Relacion> gf = GraphsReader.newGraph("ficheros/" + file + ".txt",
					Persona::ofFormat,
					Relacion::ofFormat,
					Graphs2::simpleDirectedGraph);
			
				Set<Persona> s = Ejercicio1.apartadoD(gf);
				Ejercicio1.apartadoDColorear(gf,s,"Apartado D_A");
				
				System.out.println("===================APARTADO D_A ================================================");
			System.out.println("Personas que tienen hijos/as con distintas personas "+Ejercicio1.apartadoD(gf));
				System.out.println("==============================================================================");
			}
//------------APARTADO E-------------------------------
	public static void apartadoE_A(String file) {
				Graph<Persona, Relacion> gf = GraphsReader.newGraph("ficheros/" + file + ".txt",
						Persona::ofFormat,
						Relacion::ofFormat,
						Graphs2::simpleGraph);
				
					Set<Persona> s = Ejercicio1.apartadoE(gf);
					
					System.out.println("===================APARTADO E_A ================================================");
					Ejercicio1.apartadoEColorear(gf,s,"Apartado E_A");
					System.out.println(s);
					System.out.println("==============================================================================");
				}
	
		
//==============================================================
//  				FICHERO ENTRADA B	
//==============================================================	

//------------DATOS DE ENTRADA ------------------------
	public static void DatosDeEntradaB(String file) {
			//Leer grafo
			Graph<Persona, Relacion> gf = GraphsReader.newGraph("ficheros/" + file + ".txt",
							Persona::ofFormat,
							Relacion::ofFormat,
							Graphs2::simpleDirectedGraph);

			GraphColors.toDot(gf, 
							"resultados/ejercicios/ejercicio1/" +"DatosDeEntrada_B"+ ".gv",
							x -> x.toString2(),
							x -> x.toString(),
							v -> GraphColors.color(Color.black),
							e -> GraphColors.color(Color.black));
		}

//------------APARTADO A-------------------------------
	public static void apartadoA_B(String file) {
			Graph<Persona, Relacion> g = GraphsReader.newGraph("ficheros/" + file + ".txt",
					Persona::ofFormat,
					Relacion::ofFormat,
					Graphs2::simpleDirectedGraph);
			
			Set<Persona> s = Ejercicio1.apartadoA(g);

			Ejercicio1.apartadoAColorear(g, s,"Apartado A_B");
			System.out.println("===================APARTADO A_B ================================================");
			System.out.println("Personas cuyos padres aparecen en el grafo y cumplen los requisitos:\n " + s);
			System.out.println("==============================================================================");
		}
//------------APARTADO B-------------------------------
	public static void apartadoB_B(String file) {
				Graph<Persona, Relacion> g = GraphsReader.newGraph("ficheros/" + file + ".txt",
						Persona::ofFormat,
						Relacion::ofFormat,
						Graphs2::simpleDirectedGraph);
				
				Persona Raquel = Persona.of(13,"Raquel",1993,"Sevilla");
				Set<Persona> s = Ejercicio1.apartadoB(g,Raquel);

				Ejercicio1.apartadoBColorear(g, s,Raquel,"Apartado B_B");
				System.out.println("===================APARTADO B_B ================================================");
				System.out.println("Ancestros de Raquel:\n " + s);
				System.out.println("==============================================================================");
			}
//------------APARTADO C-------------------------------
	public static void apartadoC_B(String file) {
				Graph<Persona, Relacion> g = GraphsReader.newGraph("ficheros/" + file + ".txt",
						Persona::ofFormat,
						Relacion::ofFormat,
						Graphs2::simpleDirectedGraph);
					
					Persona Julia = Persona.of(14,"Julia",1996,"Jaen");
					Persona Angela = Persona.of(6,"Angela",1997,"Sevilla");
					Persona Alvaro = Persona.of(15,"Alvaro",2000,"Sevilla");
					Persona Raquel = Persona.of(13,"Raquel",1993,"Sevilla");
					Persona Laura = Persona.of(3,"Laura",1965,"Jerez");
					
					PrimosHermanosOtros JuliayAngela = Ejercicio1.apartadoC(g,Julia,Angela);
					PrimosHermanosOtros AlvararoyRaquel= Ejercicio1.apartadoC(g,Alvaro,Raquel);
					PrimosHermanosOtros LaurayRaquel= Ejercicio1.apartadoC(g,Laura,Raquel);

				
					System.out.println("===================APARTADO C_B ================================================");
					System.out.println("Julia y Angela son: " + JuliayAngela);
					System.out.println("Alvaro y Raquel son: " + AlvararoyRaquel);
					System.out.println("Laura y Raquel son: " + LaurayRaquel);
					System.out.println("==============================================================================");
				}
//------------APARTADO D-------------------------------
	public static void apartadoD_B(String file) {
				Graph<Persona, Relacion> gf = GraphsReader.newGraph("ficheros/" + file + ".txt",
						Persona::ofFormat,
						Relacion::ofFormat,
						Graphs2::simpleDirectedGraph);
				
					Set<Persona> s = Ejercicio1.apartadoD(gf);
					Ejercicio1.apartadoDColorear(gf,s,"Apartado D_B");
					
					System.out.println("===================APARTADO D_B ================================================");
				System.out.println("Personas que tienen hijos/as con distintas personas "+Ejercicio1.apartadoD(gf));
					System.out.println("==============================================================================");
				}
//------------APARTADO E-------------------------------
	public static void apartadoE_B(String file) {
					Graph<Persona, Relacion> gf = GraphsReader.newGraph("ficheros/" + file + ".txt",
							Persona::ofFormat,
							Relacion::ofFormat,
							Graphs2::simpleGraph);
					
						Set<Persona> s = Ejercicio1.apartadoE(gf);
						
						System.out.println("===================APARTADO E_B ================================================");
						Ejercicio1.apartadoEColorear(gf,s,"Apartado E_B");
						System.out.println(s);
						System.out.println("==============================================================================");
					}

//===============================================================

	
	
	
	
	

}
