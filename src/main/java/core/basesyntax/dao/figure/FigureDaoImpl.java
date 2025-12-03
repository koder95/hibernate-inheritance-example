package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.figure.Figure;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.hibernate.SessionFactory;

public class FigureDaoImpl<T extends Figure> extends AbstractDao implements FigureDao<T> {
    public FigureDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public T save(T figure) {
        return saveEntity(figure);
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        return findEntities(builder -> {
            CriteriaQuery<T> query = builder.createQuery(clazz);
            Root<T> root = query.from(clazz);
            query.where(builder.equal(root.get("color"), color));
            return query;
        });
    }
}
