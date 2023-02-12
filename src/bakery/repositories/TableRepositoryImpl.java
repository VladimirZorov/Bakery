package bakery.repositories;

import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.TableRepository;

import java.util.Collection;

public class TableRepositoryImpl implements TableRepository<Table> {

    private Collection<Table> tables;

    public TableRepositoryImpl() {
        this.tables = tables;
    }

    @Override
    public Collection<Table> getAll() {
        return null;
    }

    @Override
    public void add(Table table) {

    }

    @Override
    public Table getByNumber(int number) {
        return null;
    }
}
