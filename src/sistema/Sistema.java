package sistema;

import java.util.Scanner;

import service.HandleMenu;


public class Sistema {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Criar Scanner para capturar dados
		Scanner sc = new Scanner(System.in);
		
		HandleMenu hm = new HandleMenu();
		
		int opcao = 0;
		
		do {
			
			// \n 
			System.out.print("\nSitema de Gerenciamento \n======================= \n1 - Criar \n2 - Editar \n"
					+ "3 - Deletar \n4 - Listar \n5 - Listar único \n6 - Login \n9 - Sair \n");
		
			opcao = sc.nextInt();
			
			switch (opcao) {
			case 1: {
				
				hm.criar(sc);
				
				break;
			}
			
			case 2: {
				
				hm.editar(sc);
				break;
			}
			
			case 3: {
				
				hm.deletar(sc);
				break;
				
			}
			
			case 4: {
				
				hm.listar(sc);
				break;
				
			}
			
			case 5: {
				
				hm.listarUnico(sc);
				break;
			}
			
			case 6: {
				
				hm.login(sc);
				break;
				
			}
			
			case 9: {
				
				System.out.println("\n Fechando Sitema");
				System.exit(0);
			}
			
			default:
				System.err.println("Opção Inválida!");
			}
			

		} while (opcao != 0);
		
		sc.close();
	}

}
