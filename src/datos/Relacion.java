package datos;

public record Relacion(Integer id) {

private static int num =0;
	
	public static Relacion ofFormat(String[] formato) {
		Integer id = num;
		num++;
		return new Relacion(id);
	}
	


	@Override
	public String toString() {
		String res = "";
		return res;
	}
	
}
