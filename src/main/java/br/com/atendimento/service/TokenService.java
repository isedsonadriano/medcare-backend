package br.com.atendimento.service;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.atendimento.domain.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Transactional(readOnly = false)
public class TokenService extends GenericService{
	
	@Value("${app.jwt.expiration}")
	private String expiration;
	
	@Value("${app.jwt.secret}")
	private String secret;

	public String buildToken(@Valid Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		return Jwts.builder()
				.setIssuer("api")
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + new Long(expiration)))
				.signWith(SignatureAlgorithm.HS256, secret)
				.setSubject(usuario.getId().toString()).compact();
		
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		try {
			Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
			return Long.parseLong(claims.getSubject());
		} catch (Exception e) {
			throw e;
		}
	}

}
