package tf;

import java.time.LocalDateTime;

public class Calendario {
	private LocalDateTime fechaHora;
	
	public Calendario(){
		setFechaHora(LocalDateTime.now());
	}

	private LocalDateTime getFechaHora(){
		return fechaHora;
	}
	
	private void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}
	
	public void adelantarDias(int n){
		fechaHora.plusDays(n);
	}
}
