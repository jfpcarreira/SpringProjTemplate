package com.pne.arch.constant;

public class PropertiesConstant {

	// Connection
	public static final String CON_DRIVER_CLASS_NAME = "dataSource.driverClassName";
	public static final String CON_JDBC_URL = "dataSource.url";
	public static final String CON_USERNAME = "dataSource.username";
	public static final String CON_PASSWORD = "dataSource.password";

	// Hibernate
	public static final String HIB_DIALECT = "hibernate.dialect";
	public static final String HIB_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	public static final String HIB_SHOW_SQL = "hibernate.show_sql";
	public static final String HIB_GENERATED_DDL = "hibernate.generated.ddl";

	// c3p0
	public static final String C3P0_MIN_SIZE = "hibernate.c3p0.min_size";
	public static final String C3P0_MAX_SIZE = "hibernate.c3p0.max_size";
	public static final String C3P0_TIMEOUT = "hibernate.c3p0.timeout";
	public static final String C3P0_MAX_STATEMENT = "hibernate.c3p0.max_statements";
	public static final String C3P0_IDLE_TEST_PERIOD = "hibernate.c3p0.idle_test_period";

	// Entity manager
	public static final String ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

}
