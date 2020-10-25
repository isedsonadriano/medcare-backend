package br.com.atendimento.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.atendimento.domain.Usuario;
import br.com.atendimento.repository.UsuarioRepository;

@Service
@Transactional(readOnly = true)
public class UsuarioService extends GenericService{

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Optional<Usuario>  findByUserEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
	public Optional<Usuario>  findByUsuario(String usuario) {
		return usuarioRepository.findByUsuario(usuario);
	}

	public Usuario findByUserId(Long id) {
		return usuarioRepository.findById(id).get();
	}
	
}
