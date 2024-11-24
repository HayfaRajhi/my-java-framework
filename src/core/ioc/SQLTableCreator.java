package core.ioc;

import java.lang.reflect.Field;

import core.annotations.orm.Entity;
import core.annotations.orm.JoinColumn;
import core.annotations.orm.ManyToOne;
import core.annotations.orm.OneToMany;
import core.annotations.orm.TableName;
import core.annotations.orm.primaryKey;
import core.jdbc.DataSource;
import core.jdbc.JdbcTemplate;

public class SQLTableCreator {

	private static  DatabaseTypeMapper typeMapper;
	private static DataSource dataSource = new DataSource();

	  public SQLTableCreator( DatabaseTypeMapper typeMapper ,DataSource dataSource ) {
		  this.dataSource=dataSource;
		  this.typeMapper=typeMapper;
	    }
	  	  
	  public void createTableSQL(Class<?> clazz) {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		  String tableName;
			if (clazz.isAnnotationPresent(TableName.class)) {

				// Process only classes with @Entity annotation
				TableName tableNameAnnotation = clazz.getAnnotation(TableName.class);
				// if (tableNameAnnotation != null) {
				tableName = tableNameAnnotation.name();
				System.out.println("Table Name: " + tableName);
			} else {
			
				tableName = clazz.getSimpleName().toLowerCase();;
			}
			Field[] fields = clazz.getDeclaredFields();

			// Create the SQL query to create the table
			StringBuilder sqlBuilder = new StringBuilder("CREATE TABLE ");
			sqlBuilder.append(tableName).append(" (");

			for (Field field : fields) {
				String fieldName = field.getName();
				String fieldType = typeMapper.getDatabaseType(field.getType());

				// Check for association annotations
				if (field.isAnnotationPresent(ManyToOne.class)) {

					JoinColumn joinColumnAnnotation = field.getAnnotation(JoinColumn.class);
					if (joinColumnAnnotation != null) {
						String referencedColumnName = joinColumnAnnotation.name();
						sqlBuilder.append(referencedColumnName).append(" ").append("INT");
						
						String input = field.getType().toString();
						int lastIndex = input.lastIndexOf('.');
						String substring = input.substring(lastIndex + 1);
						substring = substring.toLowerCase();
						//String substring=field.getClass().getSimpleName().toLowerCase();
						
						sqlBuilder.append(",");
						sqlBuilder.append("FOREIGN KEY (").append(referencedColumnName).append(") REFERENCES ")
								.append(getTableName(field) != null ? getTableName(field) : substring)
								.append("(" + getPrimaryKeyFieldName(field) + ")");
					}
				} else if (field.isAnnotationPresent(OneToMany.class)) {
					// Handle One-to-Many association
					// Implement logic accordingly
				} else {

					sqlBuilder.append(fieldName).append(" ").append(fieldType);
				}

				if (field.isAnnotationPresent(primaryKey.class)) {
					sqlBuilder.append(" PRIMARY KEY");
				}

				sqlBuilder.append(",");
			}

			// Remove the trailing comma
			if (fields.length > 0) {
				sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
			}

			sqlBuilder.append(")");
			System.out.println("Generated SQL Statement: " + sqlBuilder.toString());

			// Execute the SQL statement to create the table
			jdbcTemplate.createNewTable(sqlBuilder.toString(), tableName);


	    }
	  
	  private static String getPrimaryKeyFieldName(Field field) {
			Class<?> ourClass = field.getDeclaringClass(); // Get the class of the field
			Field[] fields = ourClass.getDeclaredFields(); // Get all declared fields of the class

			for (Field f : fields) {
				if (f.isAnnotationPresent(primaryKey.class)) {
					System.out.println(f.getName());
					return f.getName(); // Return the name of the field with @PrimaryKey annotation
				}
			}
			return null;

		}

		private static String getTableName(Field field) {
			Class<?> ourClass = field.getDeclaringClass(); // Get the class of the field
			if (ourClass.isAnnotationPresent(Entity.class)) {
				// Process only classes with @Entity annotation
				TableName tableNameAnnotation = ourClass.getAnnotation(TableName.class);
				if (tableNameAnnotation != null) {
					String tableName = tableNameAnnotation.name();
					return tableName;
				}

			}
			return null;
		}
	  
	  
}
