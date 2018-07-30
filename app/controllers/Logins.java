package controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import models.Cliente;
import models.FrenteDeCaixa;
import play.libs.Crypto;
import play.libs.Mail;
import play.mvc.Before;
import play.mvc.Controller;

public class Logins extends Controller{
	
	public static void login() {
		render();
	}
	
	public static void autenticar(String login, String senha) {
		Cliente cliente = null;
		
		cliente = Cliente.find("email = ? and senha = ?", login, senha).first();
		  
		if(cliente == null){
			flash.error("Por favor, entre com usuário e senha corretos.");
			login();
		}
		else if(login.equals("admin@123") && senha.equals("admin")) {
			session.put("idClienteLogado", cliente.id);
			
			FrenteDeCaixa frenteDeCaixa = new FrenteDeCaixa();
			frenteDeCaixa.abrirCaixa = new Date();
			frenteDeCaixa.fecharCaixa = new Date();
			frenteDeCaixa.cliente = cliente;
			frenteDeCaixa.save();
			session.put("idFrenteCaixa", frenteDeCaixa.id);
			 
			Operadores.operador(); 
			  
		}
		else{
			session.put("idClienteLogado", cliente.id);
			Application.index(); 
		}
	}
	
	public static void recuperarSenha(String Email) {
		render(Email);
	}
	
	public static void novaSenha(Cliente cliente) throws EmailException{
		Cliente c = Cliente.find("email = ?", cliente.email).first();
		
		if(c != null) {
			String nSenha = Crypto.passwordHash(new Date().toString());
			nSenha = nSenha.substring(0, 6);
			
			c.senha = nSenha;
			c.save();
			
			HtmlEmail email = new HtmlEmail();
			email.addTo(c.email, c.nome);
			email.setFrom("suporte.projeto.siap@gmail.com", "SIAP");
			email.setSubject("Nova senha gerada para o sistema SIAP");
			email.setHtmlMsg("<h1>SIAP - Nova Senha </h1><p>Sua senha agora é "+ nSenha +".</p>");
			Mail.send(email);
			
			flash.success("Uma nova senha foi emviada para seu E-mail!");
			Logins.login();	
		}
		flash.error("Por favor, entre com um email válido.");
		renderTemplate("Logins/recuperarSenha.html");
	}
	
	public static void logout() {
		
		session.clear();
		System.out.println("logout");
		login();
	}
}
