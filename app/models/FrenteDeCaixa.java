
package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class FrenteDeCaixa extends Model {

	public Date abrirCaixa;
	public Date fecharCaixa;
	public int totalVendaRefeicao=0;
	public double totalVendaRecarga=0;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "operador_id")
	public Cliente cliente; 
	
}
