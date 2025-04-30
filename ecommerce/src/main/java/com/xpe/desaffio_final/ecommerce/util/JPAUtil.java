package com.xpe.desaffio_final.ecommerce.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

	private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("ecommerce");

	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}
}
