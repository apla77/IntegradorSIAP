package interceptors;

import annotations.Operador;
import controllers.Logins;
import play.mvc.Before;
import play.mvc.Controller;
 
public class Seguranca extends Controller{ 
	
	@Before
    static void verificaAutenticacao() {
        if(!session.contains("idClienteLogado")) {
            flash.error("Para acessar essa funcionalidade você deve estar logado no sistema!");
        	Logins.login();
        }
    }
}