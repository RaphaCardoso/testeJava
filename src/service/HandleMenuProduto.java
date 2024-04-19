package service;

import java.util.List;
import java.util.Scanner;

import models.Produto;
import utils.GerenciamentoDeProdutos;

public class HandleMenuProduto {

	GerenciamentoDeProdutos gsP = new GerenciamentoDeProdutos();

	public HandleMenuProduto() {
		gsP.verificaOuCria("produtos.txt");
	}

	public void criar(Scanner sc) {

		System.out.println("Digite o nome do produto:");
		String nome = sc.next();
		System.out.println("");

		System.out.println("Digite o preço do produto:");
		double preco = sc.nextDouble();
		System.out.println("");

		System.out.println("Digite a quantidade do produto:");
		int qtnd = sc.nextInt();
		System.out.println("");

		long id = getNextId();

		Produto newProduto = new Produto(id, nome, preco, qtnd);

		gsP.criarProduto(newProduto);

	}

	private long getNextId() {

		List<Produto> produtos = gsP.lerProdutos();

		long maxId = 0;

		for (Produto produto : produtos) {
			long id = produto.getId();

			if (id > maxId) {

				// Lógica para descobrir o último Id
				maxId = id;
			}
		}

		return maxId + 1;

	}

	public void editar(Scanner sc) {

		String nome = "";
		double preco = 0;
		int qntd = 0;
		
		
		System.out.println("Digite o id do Produto a ser editado: ");
		long id = sc.nextLong();
		
		
		System.out.println("Qual parâmetro quer alterar: \nNome [1] \nPreço [2] \nQuantidade [3] ");
		int escolha = sc.nextInt();

		if (escolha == 1) {

			System.out.println("Digite o novo nome: ");
			nome = sc.next();

		} else if (escolha == 2) {

			System.out.println("Digite o novo Preço:");
			preco = sc.nextDouble();

		} else if (escolha == 3) {

			System.out.println("Digite a nova Quantidade:");
			qntd = sc.nextInt();

		} else {
			
			System.err.println("Opção inválida. Nenhuma edição feita!");
		}

		gsP.EditarProduto(id, nome, preco, qntd, escolha);

	}
	
	public void listar(Scanner sc) {

		gsP.listarProdutos();

	}
	
	
	public void listarUnico(Scanner sc) {
		System.out.println("Digite o Id do produto a ser listado: ");
		long id = sc.nextLong();
		
		gsP.ListarUnicoProduto(id);
	}
	
	
	public void deletar(Scanner sc) {
		
		System.out.println("Digite o Id do produto a ser listado: ");
		long id = sc.nextLong();
		
		gsP.deletar(id);
		
	}
	
	public void login (Scanner sc) {
		
		System.out.println("Digite o nome: ");
		String nome = sc.next();
		
		System.out.println("Digite o Id do produto a ser listado: ");
		long id = sc.nextLong();
		
		gsP.login(nome, id);
		
	}
	
	public void detalheEstoque(Scanner sc) {
		
		System.out.println("[1] Valor total de estoque");
		System.out.println("[2] Quantidade total de produtos");
		int escolha = sc.nextInt();
		
		Long idMax = getNextId();
		
		gsP.detalharEstoque(escolha, idMax);
		
	}
	
}
