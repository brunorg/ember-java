package ember.sample.manager;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Singleton
public class EntityManagerProducer {

    private static EntityManagerFactory factory;

    @Produces
    public EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory("pu");
        }
        return factory;
    }

    @Produces
    @EntityManagerRepository
    public EntityManager produceEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

}
