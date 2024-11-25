import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestaConexao {

    public static void main(String[] args) {
        // Cria o EntityManagerFactory utilizando a unidade de persistência definida no persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("agenda");

        // Cria o EntityManager
        EntityManager em = emf.createEntityManager();

        try {
            // Tenta abrir uma transação
            em.getTransaction().begin();

            // Aqui você pode realizar alguma operação simples, como contar o número de registros em uma tabela
            // Caso o banco esteja acessível, o código abaixo deve funcionar sem exceções
            System.out.println("Conexão bem-sucedida!");

            // Commit da transação (mesmo que não tenha feito nenhuma alteração, apenas para testar)
            em.getTransaction().commit();
        } catch (Exception e) {
            // Caso ocorra algum erro, exibe a mensagem
            System.err.println("Erro ao conectar: " + e.getMessage());
        } finally {
            // Fecha o EntityManager e o EntityManagerFactory
            em.close();
            emf.close();
        }


    }
}
