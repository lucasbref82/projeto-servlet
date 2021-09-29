package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	private static String banco = "jdbc:postgresql://ec2-34-202-115-62.compute-1.amazonaws.com:5432/dea77marphvqoo?sslmode=require&autoReconnect=true";
	private static String user = "kxnsghdddyavug";
	private static String senha = "78420d96effab1381b8cc5c65612612281c2851f6fdb2e425733417b82945295";
	private static Connection connection = null;
	
	
	/*dddfdfdd*/
	public static Connection getConnection() {
		return connection;
	}
	
	static {
		conectar();
	}
	
	
	public SingleConnectionBanco() {/*quando tiver um instancia vai conectar*/
		conectar();
	}
	
	
	private static void conectar() {
		
		try {
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver"); /*Carrega o driver de conexão do banco*/
				connection = DriverManager.getConnection(banco, user, senha);
				connection.setAutoCommit(false); /*para nao efetuar alteracoes no banco sem nosso comando*/
			}
			
		}catch (Exception e) {
			e.printStackTrace();/*Mostrar qualquer erro no momento de conectar*/
		}
	}

}
