package br.com.atendimento.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.atendimento.dto.LoginDTO;
import br.com.atendimento.dto.TokenResponseDTO;
import br.com.atendimento.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController extends GenericController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenResponseDTO> autenticar(@RequestBody @Valid LoginDTO login) {
		try {
			Authentication authentication = authManager.authenticate(buildAuthenticate(login));
			return ResponseEntity.ok(new TokenResponseDTO(tokenService.buildToken(authentication), login.getUsuario()));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();			
		}
	}

	private Authentication buildAuthenticate(@Valid LoginDTO login) {
		return new UsernamePasswordAuthenticationToken(login.getUsuario(), login.getSenha());
	}
}
