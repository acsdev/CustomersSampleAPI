package br.eti.sca.customersapi;

import br.eti.sca.customersapi.exception.ValidationException;
import br.eti.sca.customersapi.model.dto.CustomerDTO;
import br.eti.sca.customersapi.model.jdbc.DataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static br.eti.sca.customersapi.model.jooq.tables.TbCustomer.TB_CUSTOMER;

public class CustomerService {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    public Optional<CustomerDTO> save(CustomerDTO customerDTO) {

        // SIMPLE VALIDATION
        Optional.ofNullable(customerDTO.getDsPassword()).orElseThrow(() -> new ValidationException("Password cannot be null"));
        if (! Optional.ofNullable(customerDTO.getDsEmail()).map(VALID_EMAIL_ADDRESS_REGEX::matcher).map(Matcher::find).get()) {
            throw new ValidationException(String.format("Value (%s) is not a valid email address", customerDTO.getDsEmail()));
        }

        // DO OPERATION
        try {
            String hashpw = BCrypt.hashpw(customerDTO.getDsPassword(), BCrypt.gensalt());
            customerDTO.setDsPassword( hashpw );

            DSLContext dsl = DSL.using(DataSource.getConnection(), SQLDialect.MYSQL);
            int store = dsl.newRecord(TB_CUSTOMER, customerDTO).store();

            if (store == 0) {
                throw new RuntimeException("Fail on store new customer");
            }

            List<CustomerDTO> fetched = dsl.select().from(TB_CUSTOMER).where(TB_CUSTOMER.NR_REGISTER.eq(customerDTO.getNrRegister()))
                    .fetchInto(CustomerDTO.class);

            return fetched.stream().findFirst();

        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            throw new RuntimeException(ex.getMessage());
        }
    }
}
