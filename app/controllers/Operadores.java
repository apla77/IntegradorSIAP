package controllers;


import java.util.List;

import interceptors.Seguranca;
import models.Cliente;
import play.mvc.Controller;
import play.mvc.With;
 
@With(Seguranca.class)
public class Operadores extends Controller{

	 	public static void operador() {
	 		try {
	 			long id = new Long(session.get("idClienteLogado"));
				Cliente cliente = Cliente.findById(id);
		    	render(cliente);
			} catch (Exception e) {
				flash.error("Faça login para acessar essa funcionalidade.");
				renderTemplate("Logins/login.html");
			}
	 		
	    }
	 
	 	public static void listar() {
			List<Cliente> clientes = Cliente.findAll();
			render(clientes); 
		}
			
		public static void remover(Long id) {
			Cliente cliente = Cliente.findById(id);
			cliente.delete();
			flash.success("Cliente removido com sucesso!");
			listar();
		}
	 
	 
}
