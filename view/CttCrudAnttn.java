package view;

import java.sql.Date;
import java.util.List;

import br.com.sramos.exemploshibernate.conexao.HibernateUtil;
import br.com.sramos.exemploshibernate.crudxml.Contato;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class CttCrudAnttn {
	
	public void salvar(CttHibAnttn contato){
		Session sessao = null;
		Transaction transacao = null;
		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.save(contato);
			transacao.commit();			
		} catch (HibernateException e) {
			System.out.println("Erro ao inserir contato. Codigo do Erro: "+ e.getMessage());
		}finally{
			try {
				sessao.close();
			} catch (Throwable t) {
				System.out.println("Erro ao finalizar inserção. Codigo do Erro: "+ t.getMessage());
			}
		}
	}
	
	public void atualizar(CttHibAnttn contato){
		Session sessao = null;
		Transaction transacao = null;
		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.update(contato);
			transacao.commit();			
		} catch (HibernateException e) {
			System.out.println("Erro ao alterar contato. Codigo do erro: "+ e.getMessage());
		}finally{
			try {
				sessao.close();
			} catch (Throwable t) {
				System.out.println("Erro ao finalizar operão de atualização. codigo do erro: "+ t.getMessage());
			}
		}
	}
	
	public void excluir(CttHibAnttn contato){
		Session sessao = null;
		Transaction transacao = null;
		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.delete(contato);
			transacao.commit();			
		} catch (HibernateException e) {
			System.out.println("Erro ao excluir contato. Codigo de erro: "+ e.getMessage());
		}finally{
			try {
				sessao.close();
			} catch (Throwable t) {
				System.out.println("Erro ao finalizar exclusão. Codigo do erro: "+ t.getMessage());
			}
		}
	}
	
	public List<CttHibAnttn> listar(){
		Session sessao = null;
		Transaction transacao = null;
		Query consulta = null;
		List<CttHibAnttn> resultado = null;
		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			consulta = sessao.createQuery("from Contato");
			resultado = consulta.list();
			transacao.commit();
			return resultado;
		} catch (HibernateException e) {
			System.out.println("Erro ao selecionar contato. Codigo do erro: "+ e.getMessage());
		}finally{
			try {
				sessao.close();
			} catch (Throwable t) {
				System.out.println("Erro ao finalizar operação de consulta. Codigo de erro: "+ t.getMessage());
			}
		}
		return resultado;
	}
	
	public Contato buscaContato(int valor){
		Session sessao = null;
		Transaction transacao = null;
		Query consulta = null;
		Contato contato = null;
		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			consulta = sessao.createQuery("from Contato where codigo = :parametro");
			consulta.setInteger("parametro", valor);			
			contato = (Contato) consulta.uniqueResult();
			transacao.commit();			
			return contato;
		} catch (HibernateException e) {
			System.out.println("Erro ao buscar contato. Codigo do erro: "+ e.getMessage());
		}finally{
			try {
				sessao.close();
			} catch (Throwable t) {
				System.out.println("Erro ao finalizar operão de busca. Mensagem: "+ t.getMessage());
			}
		}
		return contato;
	}

	
	public static void main(String[] args) {
		CttCrudAnttn contatoCrudAnnotations = new CttCrudAnttn();
		String[] nomes = {"Leonardo", "Nathane", "Ricardo"};
		String[] fones = {"(16) 99609-5276", "(16) 94827-3345", "(16) 97867-5832"};
		String[] emails = {"leo.dehn@hotmail.com", "nathcarolinaribeiromagalhaes@gmail.com", "ricardo@ifsp.edu.gov.br"};
		CttHibAnttn contato = null;
		
		for(int i = 0 ; i < nomes.length ; i++){
			contato = new CttHibAnttn();
			contato.setNome(nomes[i]);
			contato.setTelefone(fones[i]);
			contato.setEmail(emails[i]);
			contato.setDataCadastro(new Date(System.currentTimeMillis()));
			
			contatoCrudAnnotations.salvar(contato);			
		}
		System.out.println("Nº total de registros:" + contatoCrudAnnotations.listar().size());
	}
}
