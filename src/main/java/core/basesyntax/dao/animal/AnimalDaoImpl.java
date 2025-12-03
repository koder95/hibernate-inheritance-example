package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.zoo.Animal;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.hibernate.SessionFactory;

public class AnimalDaoImpl extends AbstractDao implements AnimalDao {
    public AnimalDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Animal save(Animal animal) {
        return saveEntity(animal);
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        return findEntities(builder -> {
            CriteriaQuery<Animal> query = builder.createQuery(Animal.class);
            Root<Animal> root = query.from(Animal.class);
            query.where(builder.like(root.get("name"), character + "%"));
            return query;
        });
    }

}
