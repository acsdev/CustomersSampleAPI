package br.etc.sca.customerapi.util;

import br.eti.sca.customersapi.Routes;
import br.eti.sca.customersapi.exception.ValidationException;
import br.eti.sca.customersapi.model.dto.CustomerDTO;
import br.eti.sca.customersapi.model.jdbc.DataSource;
import br.eti.sca.customersapi.util.Util;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.*;
import spark.Spark;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import static br.eti.sca.customersapi.model.jooq.tables.TbCustomer.TB_CUSTOMER;

public class SignUpTest {

    @BeforeClass
    public static void setup() {

        Routes.main(new String[]{});

        Spark.awaitInitialization();
    }

    @AfterClass
    public static void setdown() {

        Spark.stop();
    }

    @Before
    public void prepare() {
        // REMOVE JUST IN CASE
        DSLContext dsl = DSL.using(DataSource.getConnection(), SQLDialect.MYSQL);
        dsl.deleteFrom(TB_CUSTOMER).where(TB_CUSTOMER.NR_REGISTER.eq(10000000099L)).execute();
    }

    @Test
    public void signIn() {

        String asJson = Util.getAsJson( getCustomerDTO());

        RequestHTTP.prepare(Routes.HOST, Routes.PORT).doRequest("POST", "signin", asJson);
    }

    @Test(expected = RuntimeException.class)
    public void signInWithoutPassword() {

        CustomerDTO customerDTO = getCustomerDTO();
        customerDTO.setDsPassword( null );

        String asJson = Util.getAsJson( customerDTO );

        RequestHTTP.prepare(Routes.HOST, Routes.PORT).doRequest("POST", "signin", asJson);
    }

    @Test(expected = RuntimeException.class)
    public void signInWithInvalidMail() {

        CustomerDTO customerDTO = getCustomerDTO();
        customerDTO.setDsEmail( "invalidemail" );

        String asJson = Util.getAsJson( customerDTO );

        ResponseHTTP responseHTTP =  RequestHTTP.prepare(Routes.HOST, Routes.PORT).doRequest("POST", "signin", asJson);

        Assert.assertEquals( 200, responseHTTP.getStatus());
    }

    private CustomerDTO getCustomerDTO() {
        long birth = LocalDate.of(1986, 5, 14)
                .atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setNrRegister(10000000099L);
        customerDTO.setDsName("TST.User01");
        customerDTO.setDsUsename("tst.user");
        customerDTO.setDsPassword("123456");
        customerDTO.setDsEmail("tstuser@gmail.com");
        customerDTO.setDtBirth(new Date(birth));

        return customerDTO;
    }
}
