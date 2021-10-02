package servlets;

import java.io.Serializable;

import dao.DAOUsuarioRepository;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.ModelLogin;

public class ServletGenericUtil extends HttpServlet implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	
	public Long getUserLogado(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		return daoUsuarioRepository.consultaUsuarioLogado(usuarioLogado).getId();
		
	}
	
	
		public ModelLogin getUserLogadoObjt(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		return daoUsuarioRepository.consultaUsuarioLogado(usuarioLogado);
		
	}

}
