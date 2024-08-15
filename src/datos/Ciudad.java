package datos;

public record Ciudad(String nombre,Integer puntuacion) {


	public static Ciudad ofFormat(String[] formato) {
		String nombre = formato[0];
		Integer puntuacion = Integer.parseInt(formato[1].replace("p", "".trim()));

		
		return new Ciudad(nombre,puntuacion);
	}
	
	public static Ciudad of(String nombre,Integer puntuacion) {
		return new Ciudad(nombre,puntuacion);
	}
	
	@Override
	public String toString() {
		//String nn = this.nombre + "\n" + this.ciudadNacimiento+ " " + this.anyo;
		return this.nombre;
	}
	
	public String toString2() {
		String res = this.nombre + "\n" + this.puntuacion + " puntos";
		return res;
	}
	
	
}
