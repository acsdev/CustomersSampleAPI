/*
 * This file is generated by jOOQ.
 */
package br.eti.sca.customersapi.model.jooq;


import br.eti.sca.customersapi.model.jooq.tables.TbCustomer;
import br.eti.sca.customersapi.model.jooq.tables.records.TbCustomerRecord;

import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>db_company</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<TbCustomerRecord, Integer> IDENTITY_TB_CUSTOMER = Identities0.IDENTITY_TB_CUSTOMER;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<TbCustomerRecord> KEY_TB_CUSTOMER_PRIMARY = UniqueKeys0.KEY_TB_CUSTOMER_PRIMARY;
    public static final UniqueKey<TbCustomerRecord> KEY_TB_CUSTOMER_NR_NUMBER = UniqueKeys0.KEY_TB_CUSTOMER_NR_NUMBER;
    public static final UniqueKey<TbCustomerRecord> KEY_TB_CUSTOMER_DS_USENAME = UniqueKeys0.KEY_TB_CUSTOMER_DS_USENAME;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<TbCustomerRecord, Integer> IDENTITY_TB_CUSTOMER = Internal.createIdentity(TbCustomer.TB_CUSTOMER, TbCustomer.TB_CUSTOMER.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<TbCustomerRecord> KEY_TB_CUSTOMER_PRIMARY = Internal.createUniqueKey(TbCustomer.TB_CUSTOMER, "KEY_tb_customer_PRIMARY", TbCustomer.TB_CUSTOMER.ID);
        public static final UniqueKey<TbCustomerRecord> KEY_TB_CUSTOMER_NR_NUMBER = Internal.createUniqueKey(TbCustomer.TB_CUSTOMER, "KEY_tb_customer_nr_number", TbCustomer.TB_CUSTOMER.NR_REGISTER);
        public static final UniqueKey<TbCustomerRecord> KEY_TB_CUSTOMER_DS_USENAME = Internal.createUniqueKey(TbCustomer.TB_CUSTOMER, "KEY_tb_customer_ds_usename", TbCustomer.TB_CUSTOMER.DS_USENAME);
    }
}