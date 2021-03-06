package controllers;

import java.io.IOException;
import java.util.List;

import interceptors.Seguranca;
import models.Cliente;
import models.Sugestao;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;
 
public class Sugestoes extends Controller{

	public static void formSugestao(Sugestao sugestao) {
		render(sugestao); 
	}
	
	public static void formSugestaoOperador(Sugestao sugestao) {
		render(sugestao);
	}
	
	public static void salvar(@Valid Sugestao sugestao) {
		
		 System.out.println(validation.hasErrors());
			
			if(validation.hasErrors()) {
				validation.keep();
				params.flash();
				formSugestao(sugestao);
			}
		long id = new Long(session.get("idClienteLogado"));
		Cliente cliente = Cliente.findById(id);
			sugestao.cliente = cliente;
		sugestao.save();
		flash.success("Mensagem enviada!");
		String busca = "admin@123";
		List<Cliente> clientes = Cliente.find("email like ?", "%" + busca + "%").fetch();
		System.out.println("Nome cliente = " + cliente.email);
		if(cliente.email.equals("admin@123"))
			listagemOperador();
		else
			listSugestao();
	}
	
	
	public static void detalhes(Sugestao sugestao) {
		render(sugestao);
	}
	
	public static void listSugestao() {
		List<Sugestao> sugestoes = Sugestao.findAll();
		render(sugestoes);
	}
	
	public static void listagemOperador() {
		List<Sugestao> sugestoes = Sugestao.findAll();
		render(sugestoes);
	}
	
	public static void remover(Long id) {
		Sugestao sugestao = Sugestao.findById(id);
		flash.success("Mensagem Removida!");
		sugestao.delete();
		listSugestao();
	}
	
}
