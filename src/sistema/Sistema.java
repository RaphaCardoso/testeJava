package sistema;

import java.util.Scanner;

import service.HandleMenu;
import service.HandleMenuProduto;


public class Sistema {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Criar Scanner para capturar dados
		Scanner sc = new Scanner(System.in);
		
		HandleMenu hm = new HandleMenu();
		HandleMenuProduto hmP = new HandleMenuProduto();
		
		
		int opcao = 0;
		
		do {
			
			System.out.println("");
			System.out.println("Gerenciar Usuários [1] Produtos [2]");
			System.out.println("");
			int opcaoMaster = sc.nextInt();
			
			// \n 
			System.out.print("\nSitema de Gerenciamento \n======================= \n1 - Criar \n2 - Editar \n"
					+ "3 - Deletar \n4 - Listar \n5 - Listar único \n6 - Login \n7 - Detalhes Estoque \nSair \n");
		
			opcao = sc.nextInt();
			

			
			if(opcaoMaster == 1) {
			
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
			

		} else if (opcaoMaster == 2) {

			
			switch (opcao) {
			case 1: {
				
				hmP.criar(sc);
				
				break;
			}
			
			case 2: {
				
				hmP.editar(sc);
				break;
			} 			
			
			case 3: {
				
				hmP.deletar(sc);
				break;
				
			}
			
			case 4: {
				
				hmP.listar(sc);
				break;
				
			}
			
			case 5: {
				
				hmP.listarUnico(sc);
				break;
			}
			
			case 6: {
				
				hmP.login(sc);
				break;
				
			}
			
			case 7: {
				
				hmP.detalheEstoque(sc);
				break;
			}
			
			case 9: {
				
				System.out.println("\n Fechando Sitema");
				System.exit(0);
			}
			
			
			default:
				System.err.println("Opção Inválida!");
			}
			
			
			
			} 
			
		} while (opcao != 0);
		
		sc.close();
	}

		
		
}
