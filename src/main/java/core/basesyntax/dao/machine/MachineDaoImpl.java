package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.criteria.Selection;
import org.hibernate.SessionFactory;

public class MachineDaoImpl extends AbstractDao implements MachineDao {
    public MachineDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Machine save(Machine machine) {
        return saveEntity(machine);
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        return findEntities(builder -> {
            CriteriaQuery<Machine> query = builder.createQuery(Machine.class);
            Root<Machine> root = query.from(Machine.class);
            int thresholdYear = LocalDate.now().getYear() - age;
            query.where(builder.lessThan(root.get("year"), thresholdYear));
            return query;
        });
    }
}
