package opet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Controlador {
	
	@Autowired
	private RepositorioUsuario repo;

	@GetMapping("")
	private String index(){
		return "index";
	}
	
	@GetMapping("/cadastrar")
	public String registro(Model model) {
		model.addAttribute("usuario",new Usuario());
		
		return "cadastro";
	}
	
	@PostMapping("/cadastro_processo")
	public String sucessoCadastro(Usuario user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encoderedPassword = encoder.encode(user.getSenha());
		user.setSenha(encoderedPassword);
		
		repo.save(user);
		
		return "cadastro_realizado";
	}
	
	@GetMapping("/usuarios")
	public String filaEspera(Model model) {
		
		List<Usuario> users = repo.findAll();
		model.addAttribute("users", users);
	
		return "usuarios";
	}
}
