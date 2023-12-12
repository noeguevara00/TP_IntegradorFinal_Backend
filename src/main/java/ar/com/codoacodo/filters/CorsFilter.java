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

	
	public void doFilter(ServletRequest request, 
			ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {
		
		//esto viene desde el front
		String origin = ((HttpServletRequest)request).getHeader("origin");
		
		var origenesPermitidos = List.of("http://localhost:5500","http://127.0.0.1:5500");//Spring
		//este front esta permitido?
		if(origenesPermitidos.contains(origin)) {
			((HttpServletResponse)response).addHeader("Access-Control-Allow-Origin", origin);
			((HttpServletResponse)response).addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
			
		}
		
		chain.doFilter(request, response);
		
	}
	

}
