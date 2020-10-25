package br.com.atendimento.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.atendimento.domain.Usuario;

@Service
public class AutenticacaoService extends GenericService implements UserDetailsService {
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		Optional<Usuario> usuarioDB = usuarioService.findByUsuario(usuario);
		if(usuarioDB.isPresent()) {
			return usuarioDB.get();
		}
		throw new UsernameNotFoundException("Login inválido");
	}

}
