package qB4;
import java.sql.*;
public class DatabaseMetadataDemo {
 public static void main(String[] args)throws Exception{
  String url=System.getenv("DB_URL"),user=System.getenv("DB_USER"),password=System.getenv("DB_PASSWORD");
  if(url==null||user==null||password==null)throw new IllegalStateException("Set DB_URL, DB_USER and DB_PASSWORD.");
  try(Connection c=DriverManager.getConnection(url,user,password)){
   DatabaseMetaData m=c.getMetaData();
   System.out.println("Database: "+m.getDatabaseProductName()+" "+m.getDatabaseProductVersion());
   System.out.println("Driver: "+m.getDriverName()+" "+m.getDriverVersion());
   try(ResultSet tables=m.getTables(c.getCatalog(),null,"%",new String[]{"TABLE"})){
    while(tables.next())System.out.println("Table: "+tables.getString("TABLE_NAME"));
   }
  }
 }
}
