package br.eti.sca.customersapi;

import br.eti.sca.customersapi.exception.ValidationException;
import br.eti.sca.customersapi.model.dto.CustomerDTO;
import br.eti.sca.customersapi.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

public class Routes {

    private static final Logger LOG = LoggerFactory.getLogger(Routes.class);

    public static final String HOST = "localhost";

    public static final int    PORT = 9000;

    public static void main(String[] args) {

        Spark.ipAddress(HOST);

        Spark.port(PORT);

        Spark.after(((request, response) -> response.type("application/json")));

        Spark.exception(ValidationException.class, (e, request, response) -> {
            response.status( 400 );
            response.body( e.getMessage() );
        });

        Spark.exception(RuntimeException.class, (e, request, response) -> {
            response.status( 500 );
            response.body( "Unexpected error!" );
        });

        Spark.post("/signin", (request, response) -> {

            CustomerDTO cDTO = Util.getAsObject(request.body(), CustomerDTO.class);

            CustomerDTO nDTO = new CustomerService().save(cDTO).orElse(null);

            String asJson = Util.getAsJson(nDTO, true);

            LOG.debug( asJson );

            return asJson;
        });
    }
}