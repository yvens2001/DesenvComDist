package opet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
	
	@Query("SELECT u FROM Usuario u WHERE u.email=?1")
	Usuario FindByEmail(String email);
}
