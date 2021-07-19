import javax.swing.JOptionPane;

public class Principal {
	
	public static int xcad = 100; // contar a quantidade de clientes
	public static int xop  = 1000; // quantidade de operações
	
	public static String agencia[] = new String[100];
	public static String numconta[] = new String[100];
	public static String cpf[] = new String[100];
	public static String nome[] = new String[100];
	public static String tipoconta[] = new String[100];
	public static String senha[] = new String[100];
	
	
	public static float saldo[] = new float[xcad];
	public static float deposito[][] = new float[xcad][xop];
	public static float saque[][] = new float[xcad][xop];
	
	public static String busca[] = new String[100];
	
	public static int ultimocliente  = 0;
	public static int ultimodeposito = 0;
	public static int ultimosaque    = 0;
	
	public static boolean temcadastro = false;
	
	public static int opcmenu = 0;
	public static int i, j, k ;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Procedimentos pc = new Procedimentos();
		String continuanosistema = "N";
		
		while(continuanosistema.equals("N")) {
			
			
			pc.menu();
			
			do {				
				continuanosistema = JOptionPane.showInputDialog("Deseja sair do Sistema - (S)im | (N)ão :");
				continuanosistema = continuanosistema.toUpperCase();
			}while((!continuanosistema.equals("S"))&&(!continuanosistema.equals("N")));
			
		} //while(continuanosistema.equals("S"))

	}

}
