

package datos;

public record Persona(Integer id, String nombre,Integer anyo,String ciudadNacimiento)  {



	public static Persona ofFormat(String[] formato) {
		Integer id = Integer.parseInt(formato[0]);
		String nombre = formato[1];
		Integer anyo = Integer.parseInt(formato[2]);
		String ciudadNacimiento= formato[3];
		return new Persona(id,nombre,anyo,ciudadNacimiento);
	}
	
	public static Persona of(Integer id, String nombre,Integer anyo,String ciudadNacimiento) {
		return new Persona(id,nombre,anyo,ciudadNacimiento);
	}
	
	@Override
	public String toString() {
		//String nn = this.nombre + "\n" + this.ciudadNacimiento+ " " + this.anyo;
		return this.nombre;
	}
	
	public String toString2() {
		String res = this.nombre + "\n" + this.ciudadNacimiento+ " " + this.anyo;
		return res;
	}
	
	
	
}
