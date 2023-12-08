package ar.com.codoacodo.filters;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/*"})//intercepta todas las peticiones 
public class CorsFilter implements Filter{

	
	private List<String> origenesPermitidos = List.of("http://localhost:5500","http://127.0.0.1:5500");//Spring
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//esto viene desde el front
		String origin = ((HttpServletRequest)request).getHeader("origin");
		
		//este front esta permitido?
		if(origin != null && origenesPermitidos.contains(origin)) {
			((HttpServletResponse)response).addHeader("Access-Control-Allow-Origin", origin);
			((HttpServletResponse)response).addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, HEAD");
			((HttpServletResponse)response).addHeader("Access-Control-Allow-Headers", "*");
			
		}
		
		chain.doFilter(request, response);
		
	}
	

}
