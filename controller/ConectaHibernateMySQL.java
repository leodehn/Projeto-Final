package controller;

import org.hibernate.Session;

public class ConectaHibernateMySQL {
	
	public static void main(String[] args) {
		Session sessao = null;
		try {
		sessao = HibernateUtil.getSessionFactory().openSession();
		} finally {
			sessao.close();
		}
	}

}
