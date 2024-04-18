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
		
		List <Produto> produtos = lerProdutos();
		
		if(produtos.isEmpty()) {
			
			System.out.println("Nenhum Produto cadastrado");
			
		} else {
			
			// foreach para listar os usuários do arquivo e imprimir na tela
			System.out.println("\n Lista de Produtos\n =================");
			for(Produto produto : produtos) {
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
			}

		}
	}
	
	
	

	public void EditarProduto(long id, String novoNome, double novoPreco, int novaQuantidade,  int escolha) {

		
		
		
	}
	

}
