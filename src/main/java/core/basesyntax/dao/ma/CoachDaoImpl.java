package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.hibernate.SessionFactory;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int experience) {
        return findEntities(builder -> {
            CriteriaQuery<Coach> query = builder.createQuery(Coach.class);
            Root<Coach> root = query.from(Coach.class);
            query.where(builder.greaterThan(root.get("experience"), experience));
            return query;
        });
    }
}
