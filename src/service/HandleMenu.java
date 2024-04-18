package service;

import java.util.List;
import java.util.Scanner;

import models.Usuario;
import utils.GerenciadorDeUsuarios;

public class HandleMenu {

	// Gerenciador
	GerenciadorDeUsuarios gs = new GerenciadorDeUsuarios();

	// Construtor vazio
	public HandleMenu() {

		// Toda vez que a classe menu, for instanciada, o nosso arquivo será verificado
		gs.verificaOuCria("usuarios.txt");
	}

	public void criar(Scanner sc) {

		System.out.println("Digite o nome:");
		String nome = sc.next();

		System.out.println("Digite a senha:");
		String senha = sc.next();

		int id = getNextId();

		Usuario u = new Usuario(id, nome, senha);
		gs.adicionarUsuario(u);

	}

	public void editar(Scanner sc) {

		String senha = "";
		String nome = "";

		System.out.println("Digite o Id do usuário a ser deletado: ");
		int id = sc.nextInt();

		System.out.println("Digite o que quer editar: \n[1] Nome \n[2] Senha");
		int escolha = sc.nextInt();

		if (escolha == 1) {

			System.out.println("Digite o novo nome: ");
			nome = sc.next();

		} else if (escolha == 2) {

			System.out.println("Digite a nova senha:");
			senha = sc.next();

		}

		gs.editarUsuario(id, nome, senha, escolha);

	}

	public void listar(Scanner sc) {

		gs.listarUsuarios();

	}

	public void deletar(Scanner sc) {

		System.out.println("Digite o Id do usuário a ser deletado: ");
		int id = sc.nextInt();

		gs.deletarUsuario(id);

	}

	public void listarUnico(Scanner sc) {
		System.out.println("Digite o Id do usuário a ser listado: ");
		int id = sc.nextInt();

		gs.ListarUnicoUsuario(id);
	}
	
	
	public void login(Scanner sc) {
		
		System.out.println("Faça Login com nome e senha");
		
		System.out.println("Nome: ");
		String nome = sc.next();
		
		System.out.println("Senha: ");
		String senha = sc.next();
		
		gs.login(nome, senha);
		
		
	}

	private int getNextId() {

		List<Usuario> usuarios = gs.lerUsuarios();

		int maxId = 0;

		// for => foreach
		for (Usuario usuario : usuarios) {
			int id = usuario.getId();

			if (id > maxId) {

				// Lógica para descobrir o último Id
				maxId = id;
			}
		}

		return maxId + 1;

	}

}
