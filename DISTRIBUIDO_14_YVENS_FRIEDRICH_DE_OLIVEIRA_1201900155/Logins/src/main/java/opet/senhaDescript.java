package opet;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//Classe para teste para criptografia de senhas
public class senhaDescript {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "sega";
		String encodedPassword = encoder.encode(rawPassword);
		
		System.out.println(encodedPassword);

	}
 
}
