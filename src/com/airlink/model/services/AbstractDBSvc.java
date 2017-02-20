package com.airlink.model.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractDBSvc {

	protected EntityManagerFactory emfactory;
	protected EntityManager em;
	
	public AbstractDBSvc() {
		emfactory = Persistence.createEntityManagerFactory( "Airlink-Core" );
		em = emfactory.createEntityManager();
	}
	
	public void shutdown() {
		em.close();
		emfactory.close();
	}
}
