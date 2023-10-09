package com.thorben.janssen;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thorben.janssen.dao.ChessGameDao_;
import com.thorben.janssen.model.ChessGame;
import com.thorben.janssen.model.ChessGame_;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestMetamodel {

	Logger log = LogManager.getLogger(this.getClass().getName());

	private EntityManagerFactory emf;

	@Before
	public void init() {
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
	}

	@After
	public void close() {
		emf.close();
	}

	@Test
	public void namedQuery() {
		log.info("... namedQuery ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<ChessGame> games = ChessGame_.findByPlayerWhite(em, "Anish Giri");
		assertThat(games).size().isEqualTo(1);
		log.info(games.get(0));
		
		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void classicNamedQuery() {
		log.info("... classicNamedQuery ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<ChessGame> games = em.createNamedQuery("#findByPlayerWhite", ChessGame.class)
								  .setParameter("playerWhite", "Anish Giri")
								  .getResultList();
		assertThat(games).size().isEqualTo(1);
		log.info(games.get(0));
		
		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void daoCustomQuery() {
		log.info("... daoCustomQuery ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<ChessGame> games = ChessGameDao_.findGamesWithMovesByPlayerWhite(em, "Anish Giri");
		assertThat(games).size().isEqualTo(1);
		log.info(games.get(0));
		
		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void daoCustomNativeQuery() {
		log.info("... daoCustomNativeQuery ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<ChessGame> games = ChessGameDao_.findNativeGamesWithMovesByPlayerWhite(em, "Anish Giri");
		assertThat(games).size().isEqualTo(1);
		log.info(games.get(0));
		
		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void daoSimpleDerived() {
		log.info("... daoSimpleDerived ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<ChessGame> games = ChessGameDao_.justSomeRandomMethodName(em, "Anish Giri", "Magnus Carlsen");
		assertThat(games).size().isEqualTo(1);
		log.info(games.get(0));
		
		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void test() {
		log.info("... daoSimpleDerived ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		// em.unwrap(Session.class).getCriteriaBuilder().
		
		em.getTransaction().commit();
		em.close();
	}
}
