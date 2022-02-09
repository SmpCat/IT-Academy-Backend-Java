package S107n1Exercici2;

public abstract class Factura {
	
	private String id;
	private double importe;
	
	public Factura() {
		this.id = "";
		this.importe = 0;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public double getImporte() {
		return importe;
	}
	
	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	@Deprecated
	public abstract double calcularTotal();
}

