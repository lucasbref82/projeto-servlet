package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	private static String banco = "jdbc:postgresql://ec2-34-233-105-94.compute-1.amazonaws.com:5432/dcacjntvghgkoa?sslmode=require&autoReconnect=true";
	private static String user = "hkobthaxbofcbl";
	private static String senha = "840bc4444df965dbcd91879edd4377bf06c5fe7cd0a9be7d0098e3862f3532c6";
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
