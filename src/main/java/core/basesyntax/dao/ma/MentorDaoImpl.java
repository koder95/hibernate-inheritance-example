package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.hibernate.SessionFactory;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        return findEntities(builder -> {
            CriteriaQuery<Mentor> query = builder.createQuery(Mentor.class);
            Root<Mentor> root = query.from(Mentor.class);
            query.where(builder.greaterThan(root.get("age"), age));
            return query;
        });
    }
}
