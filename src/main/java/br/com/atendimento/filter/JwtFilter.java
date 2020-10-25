package br.com.atendimento.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.atendimento.constantes.Constantes;
import br.com.atendimento.domain.Usuario;
import br.com.atendimento.service.TokenService;
import br.com.atendimento.service.UsuarioService;
import br.com.atendimento.util.HttpUtil;

public class JwtFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UsuarioService usuarioService;

	public JwtFilter(TokenService tokenService, UsuarioService usuarioService) {
		this.tokenService = tokenService;
		this.usuarioService = usuarioService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String authorization = request.getHeader(Constantes.HEADER_AUTHORIZATION);
		String token = HttpUtil.buildToken(authorization);
		if (token != null) {
			boolean isValid = tokenService.isTokenValido(token);
			if(isValid) {
				autenticar(token);
						
			}
		} 
		filterChain.doFilter(request, response);		
	}

	private void autenticar(String token) {
		Usuario usuario = this.usuarioService.findByUserId(tokenService.getIdUsuario(token));
		Authentication auth = new UsernamePasswordAuthenticationToken(usuario , null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth );
	}

}
