package br.com.caelum.financas.teste;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteFuncoesJPQL {
	
	public static void main(String[] args) {
		EntityManager manager = new JPAUtil().getEntityManager();

		manager.getTransaction().begin();

		Conta conta = new Conta();
		conta.setId(2);
			
		String jpql = "SELECT sum(m.valor) FROM Movimentacao m WHERE m.conta = :pConta and m.tipo = :pTipo order by m.valor desc";
		Query query = manager.createQuery(jpql);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);
		
		BigDecimal soma = (BigDecimal) query.getSingleResult();
		
		System.out.println("A soma é: " + soma);
		
		jpql = "SELECT avg(m.valor) FROM Movimentacao m WHERE m.conta = :pConta and m.tipo = :pTipo order by m.valor desc";
		query = manager.createQuery(jpql);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);
		
		Double media = (Double) query.getSingleResult();
		
		System.out.println("A media é: " + media);
		
		manager.getTransaction().commit();
		manager.close();
	}

}
