package controllers;

import java.util.Date;
import java.util.List;

import models.Cliente;
import models.FrenteDeCaixa;
import play.mvc.Controller;

public class FrenteDeCaixas extends Controller{
	
	public static void fechaCaixa() {
		long idOp = new Long(session.get("idClienteLogado"));
		Cliente cliente = Cliente.findById(idOp);
		
		long id = new Long(session.get("idFrenteCaixa"));
		FrenteDeCaixa frenteDeCaixa = FrenteDeCaixa.findById(id);
		frenteDeCaixa.fecharCaixa = new Date();
		
		frenteDeCaixa.save();
		render(frenteDeCaixa, cliente);
	}
	
 	public static void listagemCaixa() {
		List<FrenteDeCaixa> frenteDeCaixas = FrenteDeCaixa.findAll();
		render(frenteDeCaixas); 
	}
 	

 	public static void relatoriosCaixa(Date d1, Date d2) {
		List<FrenteDeCaixa> frenteDeCaixas = FrenteDeCaixa.findAll();
		
	//List<FrenteDeCaixa> fC = FrenteDeCaixa.find("abrirCaixa like ? or fecharCaixa like ?", "%" + d1 + "%", "%" + d2 + "%").fetch();
		System.out.println("data1 = " + d1);
		System.out.println("data2 = " + d2);
		int totRefiecao = 0;
		double totRecarga = 0.0;
		for(FrenteDeCaixa caixa: frenteDeCaixas) {
			totRefiecao += caixa.totalVendaRefeicao;
			totRecarga += caixa.totalVendaRecarga;
		}
		render(frenteDeCaixas, totRefiecao, totRecarga); 
	} 
}





