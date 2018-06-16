/*
 * This file is generated by jOOQ.
 */
package br.eti.sca.customersapi.model.jooq;


import br.eti.sca.customersapi.model.jooq.tables.TbCustomer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbCompany extends SchemaImpl {

    private static final long serialVersionUID = 1446555949;

    /**
     * The reference instance of <code>db_company</code>
     */
    public static final DbCompany DB_COMPANY = new DbCompany();

    /**
     * The table <code>db_company.tb_customer</code>.
     */
    public final TbCustomer TB_CUSTOMER = br.eti.sca.customersapi.model.jooq.tables.TbCustomer.TB_CUSTOMER;

    /**
     * No further instances allowed
     */
    private DbCompany() {
        super("db_company", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            TbCustomer.TB_CUSTOMER);
    }
}