package bakery.repositories;

import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.TableRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TableRepositoryImpl implements TableRepository<Table> {

    private Collection<Table> tables;

    public TableRepositoryImpl() {
        this.tables = new ArrayList<>();
    }

    @Override
    public Collection<Table> getAll() {
        return Collections.unmodifiableCollection(tables);
    }

    @Override
    public void add(Table table) {
        tables.add(table);
    }

    @Override
    public Table getByNumber(int number) {
        Table myTable = null;
        for (Table table : tables) {
            if (table.getTableNumber()==number) {
               myTable = table;
            }
        }
        return myTable;
    }
}
