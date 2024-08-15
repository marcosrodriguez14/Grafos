package datos;


//Ciudad1,Ciudad2,30euros,45min
public record Trayecto(Integer id,Double euros,Double minutos) {

	private static int num =0;
	
	public static Trayecto ofFormat(String[] formato) {
	
		
		Double euros = Double.parseDouble(formato[2].replace("euros", "").trim());
		Double minutos = Double.parseDouble(formato[3].replace("min", "").trim());	
		Integer id = num;
		num++;
		return new Trayecto(id,euros,minutos);
	}

	public static Trayecto of(Double euros) {
		Double euro = euros;
		Double minuto = null;		
		Integer id = num;
		num++;
		return new Trayecto(id, euro, minuto);
	}
	
	@Override
	public String toString() {
		
		return this.euros+" euros \n"+this.minutos+" minutos" ;
	}
}
