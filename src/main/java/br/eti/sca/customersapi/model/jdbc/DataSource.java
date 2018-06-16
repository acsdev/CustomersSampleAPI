package br.eti.sca.customersapi.model.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {

    private static final Logger LOG = LoggerFactory.getLogger(DataSource.class);

    private static final DataSource instance = new DataSource();

    private Properties properties = null;

    private ComboPooledDataSource pool = null;

    private DataSource() {

    }

    private void loadProperties() throws Exception {
        if ( properties == null ) {
            InputStream resource = DataSource.class.getClassLoader().getResourceAsStream("mysql.properties");
            this.properties = new Properties();
            this.properties.load( resource );
        }
    }

    private ComboPooledDataSource getDataSource() {
        try {
            this.loadProperties();

            ComboPooledDataSource dataSource = new ComboPooledDataSource();

            dataSource.setDriverClass( properties.getProperty("jdbc.driver") );

            dataSource.setJdbcUrl( properties.getProperty("jdbc.url") );
            dataSource.setUser( properties.getProperty("jdbc.username") );
            dataSource.setPassword(properties.getProperty("jdbc.password"));

            // the settings below are optional -- c3p0 can work with defaults
            dataSource.setMinPoolSize(5);
            dataSource.setAcquireIncrement(5);
            dataSource.setMaxPoolSize(20);

            return dataSource;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static Connection getConnection() {
        if ( DataSource.instance.pool == null ) {
             DataSource.instance.pool = DataSource.instance.getDataSource();
        }
        try {
            return  DataSource.instance.pool.getConnection();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
