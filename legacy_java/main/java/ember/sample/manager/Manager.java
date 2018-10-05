package ember.sample.manager;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class Manager<Entity, Key> {

    @EntityManagerRepository
    @Inject
    private EntityManager entityManager;

    private Class<Entity> entityClass;

    @SuppressWarnings("unchecked")
    public Manager() {
        this.entityClass = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Entity save(Entity entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    public Entity update(Entity entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    public void delete(Key key) {
        entityManager.getTransaction().begin();
        entityManager.remove(find(key));
        entityManager.getTransaction().commit();
    }

    public Entity find(Key id) {
        return entityManager.find(getEntityClass(), id);
    }

    public List<Entity> findAll() {
        Query query = entityManager.createQuery("SELECT i FROM " + getEntityClass().getSimpleName() + " i");
        return findBy(query);
    }

    public List<Entity> findByIds(List<Key> ids) {
        Query query = getEntityManager().createQuery("SELECT i FROM " + getEntityClass().getSimpleName() + " i WHERE i.id IN (:ids)");
        query.setParameter("ids", ids);
        return findBy(query);
    }

    public Class<Entity> getEntityClass() {
        return entityClass;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @SuppressWarnings("unchecked")
    public List<Entity> findBy(Query query) {
        return (List<Entity>) query.getResultList();
    }

}
