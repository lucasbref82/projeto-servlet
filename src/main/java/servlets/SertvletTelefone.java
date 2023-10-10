package servlets;

import java.io.IOException;
import java.util.List;

import dao.DAOTelefoneRepository;
import dao.DAOUsuarioRepository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ModelLogin;
import model.ModelTelefone;

@WebServlet("/SertvletTelefone")
public class SertvletTelefone extends ServletGenericUtil {

	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	private DAOTelefoneRepository daoTelefoneRepository = new DAOTelefoneRepository();

	public SertvletTelefone() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
		String acao = request.getParameter("acao");
		
		if (acao != null && !acao.isEmpty() && acao.equals("excluir")) {
			
			String idfone = request.getParameter("id");
			
			daoTelefoneRepository.deleteFone(Long.parseLong(idfone));
			
			String userpai = request.getParameter("userpai");
			
			ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(Long.parseLong(userpai));
			
			List<ModelTelefone> modelTelefones = daoTelefoneRepository.listFone(modelLogin.getId());
			request.setAttribute("modelTelefones", modelTelefones);
			
			request.setAttribute("msg", "Telefone Excluido");
			request.setAttribute("modelLogin", modelLogin);
			request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
			
			return;
		}
		
		
			
		
		String iduser = request.getParameter("iduser");
		
		if (iduser != null && !iduser.isEmpty()) {
				
				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(Long.parseLong(iduser));
			
				List<ModelTelefone> modelTelefones = daoTelefoneRepository.listFone(modelLogin.getId());
				request.setAttribute("modelTelefones", modelTelefones);
				
				request.setAttribute("modelLogin", modelLogin);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
	
		
		}else {
			 List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
		     request.setAttribute("modelLogins", modelLogins);
		     request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
			 request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		}
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
		
			String idUsuarioPai = request.getParameter("id");
			String numero = request.getParameter("numero");
			
			
			if (!daoTelefoneRepository.existeFone(numero, Long.valueOf(idUsuarioPai))) {
			
				ModelTelefone modelTelefone = new ModelTelefone();
				
				modelTelefone.setNumero(numero);
				modelTelefone.setIdUsuarioPai(daoUsuarioRepository.consultaUsuarioID(Long.parseLong(idUsuarioPai)));
				modelTelefone.setIdUsuarioCadastro(super.getUserLogadoObjt(request));
				
				daoTelefoneRepository.gravaTelefone(modelTelefone);
				
				request.setAttribute("msg", "Salvo com sucesso");
			
			}else {
				request.setAttribute("msg", "Telefone já existe");
			}
			
				List<ModelTelefone> modelTelefones = daoTelefoneRepository.listFone(Long.parseLong(idUsuarioPai));
				
				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(Long.parseLong(idUsuarioPai));
				
				request.setAttribute("modelLogin", modelLogin);
				request.setAttribute("modelTelefones", modelTelefones);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
