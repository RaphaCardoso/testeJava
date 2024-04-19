package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Produto;



public class GerenciamentoDeProdutos {

	private static final String NOME_ARQUIVO = "produtos.txt";

	public void verificaOuCria(String nomeArquivo) {

		File fl = new File(nomeArquivo);

		if (fl.exists()) {
			System.out.println("_______________________________");
		} else {

			try {

				fl.createNewFile();

				System.out.println("Arquivo criado com sucesso!");

			} catch (IOException e) {

				System.err.println("Ocorreu um erro ao criar o arquivo: " + e.getMessage());

			}

		}

	}

	public void criarProduto(Produto newProduto) {

		try (BufferedWriter bwp = new BufferedWriter(new FileWriter(NOME_ARQUIVO, true))) {

			bwp.write(newProduto.toString());
			bwp.newLine();

		} catch (IOException e) {

			System.err.println("Ocorreu um erro ao criar o arquivo: " + e.getMessage());

		}

	}

	public List<Produto> lerProdutos() {

		List<Produto> produtos = new ArrayList<Produto>();

		try (BufferedReader brp = new BufferedReader(new FileReader(NOME_ARQUIVO))) {

			String linha;

			while ((linha = brp.readLine()) != null) {

				String[] partes = linha.split(" _ ");

				produtos.add(new Produto(Long.parseLong(partes[0]), partes[1], Double.parseDouble(partes[2]),
						Integer.parseInt(partes[3])));
			}

		} catch (IOException e) {

			System.err.println("Ocorreu um erro ao criar o arquivo: " + e.getMessage());

		}

		return produtos;
	}

	public void listarProdutos() {

		List<Produto> produtos = lerProdutos();

		if (produtos.isEmpty()) {

			System.out.println("Nenhum Produto cadastrado");

		} else {

			// foreach para listar os usuários do arquivo e imprimir na tela
			System.out.println("\n Lista de Produtos\n =================");
			for (Produto produto : produtos) {
				System.out.println("ID: " + produto.getId() + " _  Nome: " + produto.getNome() + " _ Preço: "
						+ produto.getPreco() + " _ Quantidade: " + produto.getQuantidade());
			}
		}
	}

	public void ListarUnicoProduto(long id) {

		// Trás uma lista de todos os usuários
		List<Produto> produtos = lerProdutos();

		for (Produto produto : produtos) {
			if (produto.getId() == id) {
				System.out.println("\n Lista de Produtos\n =================");
				System.out.println("ID: " + produto.getId() + " _  Nome: " + produto.getNome() + " _ Preço: "
						+ produto.getPreco() + " _ Quantidade: " + produto.getQuantidade());
				System.out.println("");
			}

		}
	}

	public void EditarProduto(long id, String novoNome, double novoPreco, int novaQuantidade, int escolha) {

		List<Produto> produtos = lerProdutos();
		boolean encontrado = false;

		for (Produto produto : produtos) {

			if (produto.getId() == id) {

				switch (escolha) {
				case 1: {

					produto.setNome(novoNome);
					System.out.println("Nome alterado com Sucesso!");
					System.out.println("");
					encontrado = true;

					break;
				}

				case 2: {

					produto.setPreco(novoPreco);
					System.out.println("Preço alterado com Sucesso!");
					System.out.println("");
					encontrado = true;

					break;

				}

				case 3: {

					produto.setQuantidade(novaQuantidade);
					System.out.println("Quantidade alterado com Sucesso!");
					System.out.println("");
					encontrado = true;

					break;
				}

				default:
					System.out.println("Não achou a opção");
				}

				if (encontrado) {

					reescreverArquivos(produtos);
					System.out.println("Usuario editado com sucesso!");
					ListarUnicoProduto(id);


				} else {

					System.out.println("Produto não encontrado");
				}

			}
		}

	}
	
	
	public void reescreverArquivos(List<Produto> produtos) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
			
			for (Produto produto : produtos) {
				bw.write(produto.toString());
				bw.newLine();
			}
			
		} catch (IOException e) {
			// Exibir a mensagem de erro
			System.err.println("Ocorreu um erro ao criar o arquivo: " + e.getMessage());
		}
	}

	public void deletar(long id) {

		List<Produto> produtos = lerProdutos();

		if (produtos.removeIf(produto -> produto.getId() == id)) {

			reescreverArquivos(produtos);
			System.out.println("Produto deletado com sucesso");
			
		} else {
			
			System.out.println("Produto não encontrado");
			
			}
		
		
	}

	
	public void login (String nome, Long id) {
		
		List <Produto> produtos = lerProdutos();
		
		boolean existe = false;
		
		for(Produto produto : produtos) {
			
			if(produto.getNome().equalsIgnoreCase(nome)) {
				
				if(produto.getId() == id) {
					
					if(produto.getNome().equalsIgnoreCase(nome)) {
						System.out.println("Você está logado!");
						existe = true;
						break;
					}
					
				} else {
					System.err.println("Produto não encontrado!");
				}
			} 
		}
		
		
		if (existe == false) {
		System.err.println("Nome ou ID incorretos!");
		}
		
	}
	


	public void detalharEstoque(int escolha, Long idMax) {

		List<Produto> produtos = lerProdutos();
		
	int id =  1;
	int qntd;
	double precos;

	switch (escolha) {
	case 1: {
		double precoTotal = 0;
		
		for (Produto produto : produtos) {

			do {
				if (produto.getId() == id) {

					qntd  = produto.getQuantidade();
					
					precos = produto.getPreco();
		
					precoTotal += (qntd * (precos));
				}
				
				qntd = 0;
				precos = 0;
			
			id++;
			} while (id != idMax);
			
		}		
		
		System.out.println("O preço total somando todas as mercadorias é: " + precoTotal);
		break;
	}
	
	
	case 2: {
		
		int quantidadeTotal = 0;

		for (Produto produto : produtos) {
		
			do {
				if (produto.getId() == id) {

					qntd  = produto.getQuantidade();
					
					quantidadeTotal += qntd;
		
				}
				
			
			id++;
			} while (id != idMax);
					
		}	
		
		System.out.println("A quantidade total de Produtos é: " + quantidadeTotal);
		break;
		
		
	}
		

	default:
		System.err.println("Erro");
		
		}
	
	
	}
	
}


	
	

