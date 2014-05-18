package modelo;

public class Correo {
	private String contacto;
	private String asunto;
	private String mensaje;
	public Correo(String contacto, String asunto, String mensaje) {
		super();
		this.contacto = contacto;
		this.asunto = asunto;
		this.mensaje = mensaje;
	}
	public String getContacto() {
		return contacto;
	}
	public String getAsunto() {
		return asunto;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
