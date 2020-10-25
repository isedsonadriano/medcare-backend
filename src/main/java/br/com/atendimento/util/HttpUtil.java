package br.com.atendimento.util;

import java.util.StringTokenizer;

public class HttpUtil {

	public static String buildToken(String authorization) {
		try {
			String[] authParts = authorization.split("\\s+");
			return new StringTokenizer(authParts[1], ":").nextToken();
		} 
		catch (Exception e) {
			return null;
		}
	}

}
