package opet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DetalheServico implements UserDetailsService {
	
	@Autowired
	private RepositorioUsuario repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario user = repo.FindByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("Usuario nao encontrado");
		}
		return new Detalhes(user);
	}
}
