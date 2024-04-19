package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Usuario;

public class GerenciadorDeUsuarios {

	// Privado / Pode ser acessado por outros arquivos / é a versão final
	private static final String NOME_ARQUIVO = "usuarios.txt";
	
	// Verifica a existência do nosso banco e cria caso não exista
	public void verificaOuCria (String nomeArquivo) {
		
		// file =>  arquivo
		File arquivo = new File(nomeArquivo);
		
		// Verificar se o arquivo existe
		if(arquivo.exists()) {
			
			System.out.println("Banco funcionando!");
			
		} else {
			
			//Tente criar, caso tenha erro, exibe o erro
			try {
				
				// Criar um novo Arquivo
				arquivo.createNewFile();
				System.out.println("Arquivo criado com sucesso!");
				
			} catch (IOException e) {

				// Exibir a mensagem de erro
				System.err.println("Ocorreu um erro ao criar o arquivo: " + e.getMessage());
				
			}
			
		}
		
		
	}
	
	
	public void adicionarUsuario (Usuario usuario) {
		// Writter = Escrever
		// BufferedWritter, FileWritter
		
		// BufferedWriter, proporciona uma escrita eficiente
		// FileWriter, escreve dentro do arquivo
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO, true)))  {
			
			bw.write(usuario.toString()); // 4 _ Raphael _ 123456
			bw.newLine(); // nova linha no arquivo txt
			
		} catch (IOException e) {
			
			// Exibir a mensagem de erro
			System.err.println("Ocorreu um erro ao criar o arquivo: " + e.getMessage());
			
		}
		
	}
	
	
	public List<Usuario> lerUsuarios(){
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		// Buferred, File, Reader
		
		try (BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
			
			String linha; // linha => 1 _ nome _ senha
			
			// Percorrer todas as linhas equanto seja diferente de vazio
			while((linha = br.readLine()) != null) {
				
				String [] partes = linha.split(" _ "); // Dividir em tres partes
				
				// Adicionar usuários a lista
				usuarios.add(new Usuario(Integer.parseInt(partes[0]), partes[1], partes[2]));
			};
			
		} catch (Exception e) {

			// Exibir a mensagem de erro
			System.err.println("Ocorreu um erro ao criar o arquivo: " + e.getMessage());
			
		}
		
		return usuarios;
	}
	
	
	public void deletarUsuario (int id) {
		
		// Trás uma lista de todos os usuários
		List <Usuario> usuarios = lerUsuarios();
		
		// Procura por ID
	if (usuarios.removeIf(usuario -> usuario.getId() == id)) {
		
		// Ele reescreve o arquivo sem o usuário deletado
		reescreverArquivos(usuarios);
		System.out.println("Usuario deletado com sucesso");
		
	} else {
		
		System.out.println("Usuario não encontrado");
		
		}
		
		
	}
	
	public void ListarUnicoUsuario (int id) {
		
		// Trás uma lista de todos os usuários
		List <Usuario> usuarios = lerUsuarios();
		
		
		for(Usuario usuario : usuarios) {
			if(usuario.getId() == id) {
				System.out.println("\n Lista de usuário\n =================");
				System.out.println("ID: " + usuario.getId() + " _  Nome: " + usuario.getNome() + " _ Senha: " + usuario.getSenha());
			}
			
		}
	}
	
	public void login (String nome, String senha) {
		
		List <Usuario> usuarios = lerUsuarios();
		
		
		boolean existe = false;
		

		for(Usuario usuario : usuarios) {
			
			if(usuario.getNome().equalsIgnoreCase(nome)) {
				
				if(usuario.getSenha().equalsIgnoreCase(senha)) {
				System.out.println("Você está logado!");
				existe = true;
				break;
				}
			} 
		}
		
		
		if (existe == false) {
		System.err.println("Usuário ou senha incorretos!");
		
		System.out.println("");
		
			}
		
	
	}
	
	public void reescreverArquivos(List<Usuario> usuarios) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
			
			for (Usuario usuario : usuarios) {
				bw.write(usuario.toString());
				bw.newLine();
			}
			
		} catch (IOException e) {
			// Exibir a mensagem de erro
			System.err.println("Ocorreu um erro ao criar o arquivo: " + e.getMessage());
		}
	}
	
	public void listarUsuarios() {
		
		List <Usuario> usuarios = lerUsuarios();
		
		// Vrerificando de há usuários cadastrados
		if(usuarios.isEmpty()) {
			
			System.out.println("Nenhum usuário cadastrado");
			
		} else {
			
			// foreach para listar os usuários do arquivo e imprimir na tela
			System.out.println("\n Lista de usuários\n =================");
			for(Usuario usuario : usuarios) {
				System.out.println("ID: " + usuario.getId() + " _  Nome: " + usuario.getNome() + " _ Senha: " + usuario.getSenha());
			}
			
		}
		
		
	}
	
	public void editarUsuario(int id, String novoNome, String novaSenha, int escolha) {
		
		List<Usuario> usuarios = lerUsuarios();
		boolean encontrado = false;
		
		for(Usuario usuario : usuarios) {
			if(usuario.getId() == id) {
				
				if(escolha == 1) {
					
				usuario.setNome(novoNome);
				
				} else if (escolha == 2) {
					
				usuario.setSenha(novaSenha);
				
				}
				encontrado = true;
				break;
			}
		}
		
		if(encontrado) {
			
			reescreverArquivos(usuarios);
			System.out.println("Usuario editado com sucesso!");
			
		} else {
			
			System.out.println("Usuario não encontrado");
		}
	}
	
	 
	public String  loginEsquecido(int id, String senha) {
		
		String senhaAntiga = "";
		
		List<Usuario> usuarios = lerUsuarios();
		
		for(Usuario usuario : usuarios) {
			if(usuario.getId() == id) {
		
				senhaAntiga = usuario.getSenha();
				
				usuario.setSenha(senha);
			
				System.out.println("Senha mudada com sucesso, tente fazer login novamente!");
			}
		}
		
		
		return senhaAntiga;
	}
	
}
