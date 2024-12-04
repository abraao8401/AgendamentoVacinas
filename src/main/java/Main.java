package br.com.agenda;

import br.com.agenda.model.Usuario;
import br.com.agenda.service.UsuarioService;
import br.com.agenda.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioService usuarioService = new UsuarioService();
        int opcao;

        // Exemplo de dados para usuários e alergias
        Usuario usuario = new Usuario();
        usuario.setNome("Carla Silva");
        usuario.setDataNascimento(LocalDate.of(1990, 5, 15));
        usuario.setSexo(br.com.agenda.enums.Sexo.M);
        usuario.setLogradouro("Rua das Flores");
        usuario.setNumero(123);
        usuario.setSetor("Tecnologia");
        usuario.setCidade("São Paulo");
        usuario.setUf("SP");

        // Menu interativo
        do {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Salvar usuário");
            System.out.println("2. Listar usuários");
            System.out.println("3. Excluir usuário");
            System.out.println("4. Sair");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    // Salvar o usuário
                    try {
                        usuarioService.salvarUsuario(usuario);
                        System.out.println("Usuário salvo com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao salvar usuário: " + e.getMessage());
                    }
                    break;

                case 2:
                    // Listar usuários
                    try {
                        List<Usuario> usuarios = usuarioService.listarUsuarios();
                        if (usuarios.isEmpty()) {
                            System.out.println("Nenhum usuário encontrado.");
                        } else {
                            for (Usuario u : usuarios) {
                                System.out.println("ID: " + u.getId() + ", Nome: " + u.getNome());
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao listar usuários: " + e.getMessage());
                    }
                    break;

                case 3:
                    // Excluir um usuário
                    System.out.println("Digite o ID do usuário para excluir:");
                    Integer idUsuarioExcluir = scanner.nextInt();
                    try {
                        usuarioService.excluirUsuario(idUsuarioExcluir);
                        System.out.println("Usuário excluído com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao excluir usuário: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 4);

        scanner.close();
    }
}
