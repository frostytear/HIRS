package hirs.attestationca.portal.datatables;

import org.hibernate.Criteria;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import hirs.FilteredRecordsList;
import hirs.persist.CriteriaModifier;
import hirs.persist.OrderedListQuerier;

/**
 * A class to adapt the Javascript DataTable java class abstractions to the DBManager's getting
 * of ordered lists.
 * @param <T> The type of object to query
 */
public final class OrderedListQueryDataTableAdapter<T> {

    private OrderedListQueryDataTableAdapter() {
        // do not construct
    }

    /**
     * Gets the ordered list of records using a default, no-op criteria modifier.
     * @param clazz the type of objects to query for
     * @param dbManager the db manager to execute the actual query
     * @param dataTableInput the JS DataTable query abstraction
     * @param orderColumnName the name of the column (java object field name) to query on
     * @param <T> the parameter type
     * @return the filtered record list
     */
    public static <T> FilteredRecordsList<T> getOrderedList(final Class<? extends T> clazz,
        final OrderedListQuerier<T> dbManager,
        final DataTableInput dataTableInput,
        final String orderColumnName) {

        CriteriaModifier defaultModifier = new CriteriaModifier() {
            @Override
            public void modify(final Criteria criteria) {
                // Do nothing
            }
        };
        return getOrderedList(clazz, dbManager, dataTableInput, orderColumnName, defaultModifier);
    }

    /**
     * Gets the ordered list of records.
     * @param clazz the type of objects to query for
     * @param dbManager the db manager to execute the actual query
     * @param dataTableInput the JS DataTable query abstraction
     * @param orderColumnName the name of the column (java object field name) to query on
     * @param criteriaModifier the criteria modifier
     * @param <T> the parameter type
     * @return the filtered record list
     */
    public static <T> FilteredRecordsList<T> getOrderedList(final Class<? extends T> clazz,
        final OrderedListQuerier<T> dbManager, final DataTableInput dataTableInput,
        final String orderColumnName,
        final CriteriaModifier criteriaModifier) {

        Map<String, Boolean> searchableColumnMap = new HashMap<>();
        for (Column column : dataTableInput.getColumns()) {
            searchableColumnMap.put(column.getData(), column.isSearchable());
        }

        List<Order> orders = dataTableInput.getOrder();
        boolean isAscending = true;
        if (!CollectionUtils.isEmpty(orders)) {
            isAscending = orders.get(0).isAscending();
        }

        return dbManager.getOrderedList(clazz, orderColumnName, isAscending,
                dataTableInput.getStart(), dataTableInput.getLength(),
                dataTableInput.getSearch().getValue(),
                searchableColumnMap, criteriaModifier);
    }
}
