package br.edu.ifsp.estagiei;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.edu.ifsp.estagiei.entity.Usuario;
import br.edu.ifsp.estagiei.repository.UsuarioRepository;

@Component
class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	Usuario usuarioEntidade = new Usuario();
    	Optional<Usuario> usuario = usuarioRepo.findByEmail(email);
    	if(usuario.isPresent()) {
    		usuarioEntidade = usuario.get();
    	}
        return usuarioEntidade;
    }

}
	