import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class Procedimentos {
	public static int i,j,k;
	
	Principal pm = new Principal();
	DecimalFormat df = new DecimalFormat("###,##0.00");
	
	/////////////////////////////////
	// MENU PRINCIPAL
	/////////////////////////////////
	public void menu() {
		
		// ""+"\n"+
		
		do {
			pm.opcmenu = 0;
			pm.opcmenu = Integer.parseInt(JOptionPane.
					showInputDialog("============================================"+"\n"+			
							        "MENU PRINCIPAL DO SISTEMA"+"\n"+
							        "============================================"+"\n"+
							        "0. INFORMAÇÕES"+"\n"+
							        "1. CADASTROS"+"\n"+
							        "2. CONSULTAS"+"\n"+
							        "3. TRANSAÇÕES"+"\n"+
							        "4. HISTÓRICOS"+"\n"+
							        "5. FINALIZAR SISTEMA"));
			switch(pm.opcmenu) {
				case 0 :{
						informacoes();					
					break;
				}
				case 1 :{
					cadcliente();			
					break;
				}
				case 2 :{
					consultamenu();					
					break;
				}
				case 3 :{
					transacoesmenu();
					break;
				}
				case 4 :{
					historico();
					break;
				}
				case 5 :{
					System.exit(0);
					break;
				}
				default :{
					JOptionPane.showMessageDialog(null,"Escolha inválida!!!");
				}
			} //switch(pm.opcmenu) 
			
		}while((pm.opcmenu <= -1) || (pm.opcmenu > 5));
	} //public void menu()
	
	//historico
	public void historico() {
		String senha = "";
		String outrabusca = "S";
		int opcmenu = 0;
		
		while (outrabusca.equals("S")) {
			
			senha = JOptionPane.showInputDialog("Digite sua senha:");
			
			for (i = 0; i < pm.xcad; i++) {
				if (senha.equals(pm.senha[i])) {
					
					i = pm.xcad;
				}else {
					JOptionPane.showMessageDialog(null,"Senha inválida!!");
					historico();
				}
			}//fim do for
			
			
				do {
				opcmenu = Integer.parseInt(JOptionPane.showInputDialog("COMO DESEJA PESQUISAR O HISTÓRICO:"+"\n\n"+
																	    "1. HISTÓRICO PELO CPF"+"\n\n"+
																		"2. HISTÓRICO PELO NÚMERO DA CONTA"+"\n\n"+
																	    "3. RETORNAR AO MENU PRINCIPAL"));
				switch(opcmenu) {
					case 1: {
						histCPF();
						break;
					}
					case 2: {
						histNumConta();
						break;
					}
					case 3: {
						menu();
						break;
					}
					default : {
						JOptionPane.showMessageDialog(null, "Opção Inválida!!");
					}
				}//fim do switch
				
				}while((opcmenu <= 0) || (opcmenu > 3));
		}//fim do if
	}
	//historico pelo CPF
	public void histCPF() {
		String outrabusca = "S";
		String NomeBusca = "", detalhe;
		
		while (outrabusca.equals("S")) {
			float smdep = 0, smsaq = 0;
			detalhe = "";
			int contador = 0; 
			int cpfencontrado = -1;
			int encontrou = 0;// 0 = não, 1 = sim
			
			NomeBusca = JOptionPane.showInputDialog("Digite o CPF do cliente para o histórico:");
			
			for (i = 0; i < pm.xcad; i++) {
				if (NomeBusca.equals(pm.cpf[i])) {
					cpfencontrado = i;
					encontrou = 1;
					i = pm.xcad;
				}else {
					encontrou = 0;
				}//fim do else
			}//fim do for
			
			if (encontrou == 0) {
				JOptionPane.showMessageDialog(null, "CPF não encontrado!!");
				histCPF();
			}
			
				if (encontrou == 1) {
					String xconta = "";
					
					if (pm.tipoconta[cpfencontrado] == "1") {
						xconta = "POUPANÇA";
					}else {
						xconta = "CORRENTE";
					}
					
					detalhe = "CLIENTE ENCONTRADO PARA HISTÓRICO!!!"+"\n\n"+
							"AGENCIA: "+pm.agencia[cpfencontrado]+"\n"+
							"CONTA:   "+pm.numconta[cpfencontrado]+"\n"+
							"CPF:     "+pm.cpf[cpfencontrado]+"\n"+
							"NOME:    "+pm.nome[cpfencontrado]+"\n"+
							"TIPO:     "+xconta;
					
					detalhe = detalhe + "\n--------------------------------------------\n";
					//DEPÓSITOS DE CLIENTES
					
					detalhe = detalhe + "\n\n";
					detalhe = detalhe + "DEPÓSITOS \n";
					
					for (j = 0; j < pm.ultimodeposito; j++) {
						if (pm.deposito[cpfencontrado][j] > 0) {
							detalhe = detalhe + df.format(pm.deposito[cpfencontrado][j])+"\n";
							smdep = smdep + pm.deposito[cpfencontrado][j];
							contador++;
						}
					}//for
					detalhe = detalhe + "--------------------------------------------\n";
					detalhe = detalhe + "Total de depósitos: "+df.format(smdep)+ "\n";
					detalhe = detalhe + "Qtd Depósitos: "+contador+ "\n";
					//SAQUES DE CLIENTES
					
					detalhe = detalhe + "\n";
					detalhe = detalhe + "SAQUES \n";
					contador = 0;
					
					for (j = 0; j < pm.ultimosaque; j++) {
						if (pm.saque[cpfencontrado][j] > 0) {
							detalhe = detalhe + df.format(pm.saque[cpfencontrado][j])+"\n";
							smsaq = smsaq + pm.saque[cpfencontrado][j];
							contador++;
						}	
					}
					detalhe = detalhe + "--------------------------------------------\n";
					detalhe = detalhe + "Total de saques: "+df.format(smsaq)+ "\n";
					detalhe = detalhe + "\n\nQtd Saques: "+contador+ "";
					
					detalhe = detalhe + "--------------------------------------------\n";
					detalhe = detalhe + "Saldo do Cliente: "+df.format(pm.saldo[cpfencontrado])+ "\n";
					
					JOptionPane.showMessageDialog(null, detalhe);
					
				}// fim if encontrou == 1
				
				do {
				outrabusca = JOptionPane.showInputDialog("Deseja outra busca para histórico: (S)im - (N)ão");
				outrabusca = outrabusca.toUpperCase();
				
				}while((!outrabusca.equals("S")) && (!outrabusca.equals("N")));
			
		}	//fim do while outrabusca.equals
	}//fim historico pelo cpf
	
	public void histNumConta() {
		String outrabusca = "S";
		String NomeBusca = "", detalhe;
		
		while (outrabusca.equals("S")) {
			float smdep = 0, smsaq = 0;
			detalhe = "";
			int contador = 0; 
			int numcontaencontrado = -1;
			int encontrou = 0;// 0 = não, 1 = sim
			
			NomeBusca = JOptionPane.showInputDialog("Digite o número da conta do cliente para o histórico:");
			
			for (i = 0; i < pm.xcad; i++) {
				if (NomeBusca.equals(pm.numconta[i])) {
					numcontaencontrado = i;
					encontrou = 1;
					i = pm.xcad;
				}else {
					encontrou = 0;
				}//fim do else
			}//fim do for
			
			if (encontrou == 0) {
				JOptionPane.showMessageDialog(null, "Número da conta não encontrado!!");
				histNumConta();
			}
			
				if (encontrou == 1) {
					String xconta = "";
					
					if (pm.tipoconta[numcontaencontrado] == "1") {
						xconta = "POUPANÇA";
					}else {
						xconta = "CORRENTE";
					}
					
					detalhe = "CLIENTE ENCONTRADO PARA HISTÓRICO!!!"+"\n\n"+
							"AGENCIA: "+pm.agencia[numcontaencontrado]+"\n"+
							"CONTA:   "+pm.numconta[numcontaencontrado]+"\n"+
							"CPF:     "+pm.cpf[numcontaencontrado]+"\n"+
							"NOME:    "+pm.nome[numcontaencontrado]+"\n"+
							"TIPO:     "+xconta;
					
					detalhe = detalhe + "\n--------------------------------------------\n";
					//DEPÓSITOS DE CLIENTES
					
					detalhe = detalhe + "\n\n";
					detalhe = detalhe + "DEPÓSITOS \n";
					
					for (j = 0; j < pm.ultimodeposito; j++) {
						if (pm.deposito[numcontaencontrado][j] > 0) {
							detalhe = detalhe + df.format(pm.deposito[numcontaencontrado][j])+"\n";
							smdep = smdep + pm.deposito[numcontaencontrado][j];
							contador++;
						}
					}//for
					detalhe = detalhe + "--------------------------------------------\n";
					detalhe = detalhe + "Total de depósitos: "+df.format(smdep)+ "\n";
					detalhe = detalhe + "Qtd Depósitos: "+contador+ "\n";
					//SAQUES DE CLIENTES
					
					detalhe = detalhe + "\n";
					detalhe = detalhe + "SAQUES \n";
					contador = 0;
					
					for (j = 0; j < pm.ultimosaque; j++) {
						if (pm.saque[numcontaencontrado][j] > 0) {
							detalhe = detalhe + df.format(pm.saque[numcontaencontrado][j])+"\n";
							smsaq = smsaq + pm.saque[numcontaencontrado][j];
							contador++;
						}	
					}
					detalhe = detalhe + "--------------------------------------------\n";
					detalhe = detalhe + "Total de saques: "+df.format(smsaq)+ "\n";
					detalhe = detalhe + "\n\nQtd Saques: "+contador+ "";
					
					detalhe = detalhe + "--------------------------------------------\n";
					detalhe = detalhe + "Saldo do Cliente: "+df.format(pm.saldo[numcontaencontrado])+ "\n";
					
					JOptionPane.showMessageDialog(null, detalhe);
					
				}// fim if encontrou == 1
				
				do {
				outrabusca = JOptionPane.showInputDialog("Deseja outra busca para histórico: (S)im - (N)ão");
				outrabusca = outrabusca.toUpperCase();
				
				}while((!outrabusca.equals("S")) && (!outrabusca.equals("N")));
			
		}	//fim do while outrabusca.equals
	}//fim histórico pelo número da conta
	
	//transacoes
	public void transacoesmenu() {
		int opcmenu = 0;
		
		do {
		opcmenu = Integer.parseInt(JOptionPane.showInputDialog("TRANSAÇÕES BANCÁRIAS"+"\n\n"+
															    "1. DEPÓSITOS"+"\n\n"+
																"2. SAQUES"+"\n\n"+
															    "3. SALDO"+"\n\n"+
															    "4. RETORNAR AO MENU PRINCIPAL"));
		switch(opcmenu) {
			case 1: {
				transDepositos();
				break;
			}
			case 2: {
				transSaques();
				break;
			}
			case 3: {
				transSaldo();
				break;
			}
			case 4: {
				menu();
				break;
			}
			default : {
				JOptionPane.showMessageDialog(null, "Opção Inválida!!");
			}
		}//fim do switch
		
		}while((opcmenu <= 0) || (opcmenu > 4));
	}
	
	// informações
	public void informacoes() {
		JOptionPane.showMessageDialog(null, "Em fase de construção!!!");
	}//public void informacoes() 
	
	public void transDepositos() {
		String NomeBusca = "", detalhe;
		String OutroTrans = "S";
		String senha = "";
		
		while (OutroTrans.equals("S")) {
			detalhe = "";
			int cpfencontrado = -1;
			int encontrou = 0;// 0 = não, 1 = sim
			
			NomeBusca = JOptionPane.showInputDialog("Digite o CPF do cliente para o depósito:");
			
			for (i = 0; i < pm.xcad; i++) {
				if (NomeBusca.equals(pm.cpf[i])) {
					cpfencontrado = i;
					encontrou = 1;
					i = pm.xcad;
				}else {
					encontrou = 0;
				}//fim do else
			}//fim do for
			
			if (encontrou == 0) {
				JOptionPane.showMessageDialog(null, "Dados referidos não encontrados!!");
			}
			
			if (encontrou == 1) {
				String xconta = "";
				
				if (pm.tipoconta[cpfencontrado] == "1") {
					xconta = "POUPANÇA";
				}else {
					xconta = "CORRENTE";
				}
				
				detalhe = "CLIENTE ENCONTRADO PARA DEPÓSITO!!!"+"\n\n"+
						"AGENCIA: "+pm.agencia[cpfencontrado]+"\n"+
						"CONTA:   "+pm.numconta[cpfencontrado]+"\n"+
						"CPF:     "+pm.cpf[cpfencontrado]+"\n"+
						"NOME:    "+pm.nome[cpfencontrado]+"\n"+
						"TIPO:     "+xconta;
				
				JOptionPane.showMessageDialog(null, detalhe);
				
					for (j = pm.ultimodeposito; j < pm.xop; j++) {
						
						senha = JOptionPane.showInputDialog("Digite a senha do Cliente");
						
						for (i = 0; i < pm.xcad; i++) {
							if (senha.equals(pm.senha[i])) {
								
								i = pm.xcad;
							}else {
								JOptionPane.showMessageDialog(null, "\n Senha inválida!!");
								transDepositos();
							}
						
							pm.deposito[cpfencontrado][j] = Float.parseFloat(JOptionPane.showInputDialog("Valor do depósito:"));
	
							pm.saldo[cpfencontrado] = pm.saldo[cpfencontrado] + pm.deposito[cpfencontrado][j];
							pm.ultimodeposito = j + 1;
							
							JOptionPane.showMessageDialog(null, "Saldo: "+df.format(pm.saldo[cpfencontrado]));
						
							do {
								OutroTrans = JOptionPane.showInputDialog("Deseja fazer outro depósito: (S)im - (N)ão");
								OutroTrans = OutroTrans.toUpperCase();
								
							}while((!OutroTrans.equals("S")) && (!OutroTrans.equals("N")));
							
								if (OutroTrans.equals("N")) {
									j = pm.xop;
									transacoesmenu();
								}
							
						}//if encontrou == 1
				}//for		
			}
		}	
		transacoesmenu();
	}//fim transDepositos
	
	public void transSaques() {
		String NomeBusca = "", detalhe;
		String OutroTrans = "S";
		String senha = "";
		
		while (OutroTrans.equals("S")) {
			detalhe = "";
			int cpfencontrado = -1;
			int encontrou = 0;// 0 = não, 1 = sim
			
			NomeBusca = JOptionPane.showInputDialog("Digite o CPF do cliente para o saque");
			
			for (i = 0; i < pm.xcad; i++) {
				if (NomeBusca.equals(pm.cpf[i])) {
					cpfencontrado = i;
					encontrou = 1;
					i = pm.xcad;
				}else {
					encontrou = 0;
				}//fim do else
			}//fim do for
			
			if (encontrou == 0) {
				JOptionPane.showMessageDialog(null, "Dados referidos não encontrados!!");
			}
			
			if (encontrou == 1) {
				String xconta = "";
				
				if (pm.tipoconta[cpfencontrado] == "1") {
					xconta = "POUPANÇA";
				}else {
					xconta = "CORRENTE";
				}
				
				detalhe = "CLIENTE ENCONTRADO PARA SAQUE!!!"+"\n\n"+
						"AGENCIA: "+pm.agencia[cpfencontrado]+"\n"+
						"CONTA:   "+pm.numconta[cpfencontrado]+"\n"+
						"CPF:     "+pm.cpf[cpfencontrado]+"\n"+
						"NOME:    "+pm.nome[cpfencontrado]+"\n"+
						"TIPO:     "+xconta;
				
				JOptionPane.showMessageDialog(null, detalhe);
				
						for (j = pm.ultimosaque; j < pm.xop; j++) {
							
							senha = JOptionPane.showInputDialog("Digite a senha do Cliente");
							
							for (i = 0; i < pm.xcad; i++) {
								if (senha.equals(pm.senha[i])) {
									
									i = pm.xcad;
								}else {
									JOptionPane.showMessageDialog(null, "\n Senha inválida!!");
									transSaques();
								}
							
									pm.saque[cpfencontrado][j] = Float.parseFloat(JOptionPane.showInputDialog("Valor do saque:"));
									
									pm.saldo[cpfencontrado] = pm.saldo[cpfencontrado] - pm.saque[cpfencontrado][j];
									pm.ultimosaque = j + 1;
									
									JOptionPane.showMessageDialog(null, "Saldo: "+df.format(pm.saldo[cpfencontrado]));
								
									do {
										OutroTrans = JOptionPane.showInputDialog("Deseja fazer outro saque: (S)im - (N)ão");
										OutroTrans = OutroTrans.toUpperCase();
										
									}while((!OutroTrans.equals("S")) && (!OutroTrans.equals("N")));
									
									if (OutroTrans.equals("N")) {
										j = pm.xop;
										transacoesmenu();
									}
								
						}//if encontrou == 1
					}//for
				}
			}			
		transacoesmenu();
	}//fim transSaques
	
	public void transSaldo() {
		
			String cpf = "";
			String senha = "";
			String Conta = "", detalhe;
			
			detalhe ="";
			int contaencontrada = -1;
			int encontrou = 0;// 0 = não, 1 = sim
			
				Conta = JOptionPane.showInputDialog("Digite o número da conta do cliente");
				cpf = JOptionPane.showInputDialog("Digite o CPF do cliente");
				senha = JOptionPane.showInputDialog("Digite a senha do cliente");
				
				for (i = 0; i < pm.xcad; i++) {
					if (Conta.equals(pm.numconta[i]) && cpf.equals(pm.cpf[i]) && senha.equals(pm.senha[i])) {
						contaencontrada = i;
						encontrou = 1;
						i = pm.xcad;
					}else {
						encontrou = 0;
					}
					if (encontrou == 1) {
						detalhe = "SALDO DO CLIENTE!!!"+"\n\n"+
								  "SALDO: "+pm.saldo[contaencontrada];
							
						JOptionPane.showMessageDialog(null, detalhe);
						}if (encontrou == 0) {
							JOptionPane.showMessageDialog(null, "Dados informados incorretos!!");
							transSaldo();
						}
					}
				}//fim transSaldo
	
	public void cadcliente() {
		JOptionPane.showMessageDialog(null,"CADASTRO DE CLIENTES");
		String Sair = "N", tipoconta = "";
		String xconta ="";
		
		for(i = pm.ultimocliente; i < pm.xcad; i++) {
			pm.agencia[i]  = JOptionPane.showInputDialog("Número da Agencia");
			pm.numconta[i] = JOptionPane.showInputDialog("Número da Conta");
			pm.cpf[i]      = JOptionPane.showInputDialog("Número do CPF");
			pm.nome[i]     = JOptionPane.showInputDialog("Nome do Cliente");
			pm.senha[i]    = JOptionPane.showInputDialog("Crie uma senha");
			
			do {
				tipoconta = JOptionPane.showInputDialog("TIPO DE CONTA"+"\n"+
														"========================="+"\n"+
														"1. CONTA POUPANÇA"+"\n"+
														"2. CONTA CORRENTE"+"\n");
			}while((!tipoconta.equals("1")) && (!tipoconta.equals("2")));
			
			pm.tipoconta[i] = tipoconta;
			
			
			pm.ultimocliente = i +1;
			
			
			if (tipoconta.equals("1")) {
				xconta = "POUPANÇA";
			}else {
				xconta = "CORRENTE";
			}
			
			JOptionPane.showMessageDialog(null,"CLIENTE CADASTRADO COM SUCESSO!!!"+"\n\n"+
												"AGENCIA: "+pm.agencia[i]+"\n"+
												"CONTA:   "+pm.numconta[i]+"\n"+
												"CPF:     "+pm.cpf[i]+"\n"+
												"NOME:    "+pm.nome[i]+"\n"+
												"TIPO:     "+xconta);
			
			do {
				Sair = JOptionPane.showInputDialog("Deseja sair do Cadastro: (S)im - (N)ão");
				Sair = Sair.toUpperCase();
			}while((!Sair.equals("S")) && (!Sair.equals("N")));
			
			if (Sair.equals("S")) {
				i = pm.xcad;
			}
			
		}//for()
		
		menu();
		
	}//public void cadcliente()
	
	// consultas
	public void consultamenu() {
		String outrabusca = "S";
		String senha = "", detalhe;
		
		while(outrabusca.equals("S")) {
			detalhe ="";
			
			senha = JOptionPane.showInputDialog("Digite a senha do Cliente");
			
			for (i = 0; i < pm.xcad; i++) {
				if (senha.equals(pm.senha[i])) {
					
					i = pm.xcad;
				}else {
					JOptionPane.showMessageDialog(null, "\n Senha inválida!!");
					consultamenu();
				}
			} //for (i = 0; i < pm.xcad; i++)
			
				int opcmenu = 0;
				
				do {
				opcmenu = Integer.parseInt(JOptionPane.showInputDialog("MENU CONSULTA"+"\n"+"DESEJA CONSULTAR POR"+"\n\n"+
																	    "1. CPF"+"\n\n"+
																		"2. NÚMERO DA CONTA"+"\n\n"+
																		"3. RETORNAR AO MENU PRINCIPAL"));
				switch(opcmenu) {
					case 1: {
						conscpf();
						break;
					}
					case 2: {
						consnumconta();
						break;
					}
					case 3: {
						menu();
						break;
					}
					default : {
						JOptionPane.showMessageDialog(null, "Opção Inválida!!");
					}
				}//fim do switch
				}while((opcmenu <= 0) || (opcmenu > 3));
			}
		}		
		//montar a consulta pelo cpf
		public void conscpf() {
			String CPFBusca = "", detalhe;
			
			detalhe ="";
			int cpfencontrado = -1;
			int encontrou = 0;// 0 = não, 1 = sim
			
			CPFBusca = JOptionPane.showInputDialog("Digite o CPF do cliente");
			
			for (i = 0; i < pm.xcad; i++) {
				if (CPFBusca.equals(pm.cpf[i])) {
					cpfencontrado = i;
					encontrou = 1;
					i = pm.xcad;
				}else {
					encontrou = 0;
				}//fim do else
			}//fim do for
			
			if (encontrou == 0) {
				JOptionPane.showMessageDialog(null, "CPF inválido!!");
				conscpf();
			}
			
			if (encontrou == 1) {
				String xconta = "";
				//tipoconta.equals("1")
				if (pm.tipoconta[cpfencontrado] == "1") {
					xconta = "POUPANÇA";
				}else {
					xconta = "CORRENTE";
				}
				
				detalhe = "DADOS DO CLIENTE!!!"+"\n\n"+
						  "AGENCIA: "+pm.agencia[cpfencontrado]+"\n"+
						  "CONTA:   "+pm.numconta[cpfencontrado]+"\n"+
						  "CPF:     "+pm.cpf[cpfencontrado]+"\n"+
						  "NOME:    "+pm.nome[cpfencontrado]+"\n"+
						  "TIPO:    "+xconta;
			    JOptionPane.showMessageDialog(null,detalhe);
	
			}//fim  encontrou == 1 	 
		menu();
					
		}//fim consulta pelo cpf	
				
			
		// montar a consulta pelo número da conta
		public void consnumconta() {
			String ContaBusca = "", detalhe;
			
			detalhe ="";
			int contaencontrada = -1;
			int encontrou = 0;// 0 = não, 1 = sim
			
			ContaBusca = JOptionPane.showInputDialog("Digite o número da conta do cliente");
			
			for (i = 0; i < pm.xcad; i++) {
				if (ContaBusca.equals(pm.numconta[i])) {
					contaencontrada = i;
					encontrou = 1;
					i = pm.xcad;
				}else {
					encontrou = 0;
				}//fim do else
			}//fim do for
			
			if (encontrou == 0) {
				JOptionPane.showMessageDialog(null, "número da conta inválido!!");
				consnumconta();
			}
			
			if (encontrou == 1) {
				String xconta = "";
				
				if (pm.tipoconta[contaencontrada] == "1") {
					xconta = "POUPANÇA";
				}else {
					xconta = "CORRENTE";
				}
				
				detalhe = "DADOS DO CLIENTE!!!"+"\n\n"+
						  "AGENCIA: "+pm.agencia[contaencontrada]+"\n"+
						  "CONTA:   "+pm.numconta[contaencontrada]+"\n"+
						  "CPF:     "+pm.cpf[contaencontrada]+"\n"+
						  "NOME:    "+pm.nome[contaencontrada]+"\n"+
						  "TIPO:     "+xconta;
			    JOptionPane.showMessageDialog(null,detalhe);
			    
			}  	 
		menu();
					
		}//fim da consulta pelo número da conta
	
} //public class Procedimentos 
