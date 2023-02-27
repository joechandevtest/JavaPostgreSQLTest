//https://www.tutorialspoint.com/jdbc/jdbc-create-database.htm
//https://www.tutorialspoint.com/postgresql/postgresql_create_table.htm
//https://databasefaqs.com/create-a-table-in-postgresql/

//https://jdbc.postgresql.org/documentation/use/
//https://www.postgresql.org/docs/current/sql-createtable.html

//https://api.elephantsql.com/console/fe83798d-01fe-4e04-ae94-a317218e4d45//details by Edge

/**
 * install PostgreSQL server in PC first
 * https://www.postgresql.org/download/windows/
 *
 * Add the pgJDBC library in IntelliJ
 * https://jdbc.postgresql.org/download/
 */

import org.postgresql.PGProperty;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import java.sql.ResultSet;
import java.util.*;

public class PostgreSQLJDBC {

    public static void main(String args[]) {

        Connection conn = null;
        Statement stmt = null;

        String sql = "";
        Scanner input = new Scanner(System.in);
        ResultSet rs = null;

        String repeat;


        do {
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/JavaTest",
                                "shing", "CPSC2221!");

//                String url ="jdbc:postgresql://krpcolvk:xzgjomtUwt4ee9z-kVG8HG3iufp7Ig9Q@mahmud.db.elephantsql.com/krpcolvk";
////            Properties props = org.postgresql.Driver.parseURL(url, null);
////            int port = Integer.parseInt(props.getProperty(PGProperty.PG_PORT.getName()));
//            Class.forName("org.postgresql.Driver");
//            conn = DriverManager
//                    .getConnection(url,
//                            "krpcolvk", "xzgjomtUwt4ee9z-kVG8HG3iufp7Ig9Q");

                conn.setAutoCommit(false);

                System.out.println("Opened database successfully");

                //ResultSet rs = null;

                //fail to add do while
                System.out.println("Select from menu " +
                        "\n1 for Create table" +
                        "\n2 for Insert data" +
                        "\n3 for Select table" +
                        "\n4 for Update table" +
                        "\n5 for Delete row");
                int option = input.nextInt();

                switch (option) {

                    //ResultSet rs = null; // fail if set here
                    case 1:
                        /**
                         * Create table
                         */
                        stmt = conn.createStatement();
                        sql = "CREATE TABLE COMPANY " +
                                "(ID INT PRIMARY KEY     NOT NULL," +
                                " NAME           TEXT    NOT NULL, " +
                                " AGE            INT     NOT NULL, " +
                                " ADDRESS        CHAR(50), " +
                                " SALARY         REAL)";
                        stmt.executeUpdate(sql);
                        stmt.close();
                        conn.close();


                        break;

                    case 2:

                        /**
                         * Insert data
                         */
                        stmt = conn.createStatement();
                        //String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " + "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
                        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
                                + "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
                        stmt.executeUpdate(sql);

                        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
                                + "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
                        stmt.executeUpdate(sql);

                        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
                                + "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
                        stmt.executeUpdate(sql);

                        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
                                + "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";

                        stmt.executeUpdate(sql);
                        stmt.close();
                        conn.commit();
                        conn.close();

                        break;

                    case 3:
                        /**
                         * Select table
                         */
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery("SELECT * FROM COMPANY;");
                        while (rs.next()) {
                            int id = rs.getInt("id");
                            String name = rs.getString("name");
                            int age = rs.getInt("age");
                            String address = rs.getString("address");
                            float salary = rs.getFloat("salary");
                            System.out.println("ID = " + id);
                            System.out.println("NAME = " + name);
                            System.out.println("AGE = " + age);
                            System.out.println("ADDRESS = " + address);
                            System.out.println("SALARY = " + salary);
                            System.out.println();
                        }
                        rs.close();
                        stmt.close();
                        conn.close();

                        break;
                    case 4:
                        /**
                         * Update Operation
                         */
                        stmt = conn.createStatement();
                        sql = "UPDATE COMPANY set SALARY = 25000.00 where ID=1;";
                        stmt.executeUpdate(sql);
                        conn.commit();

                        rs = stmt.executeQuery("SELECT * FROM COMPANY;");
                        while (rs.next()) {
                            int id = rs.getInt("id");
                            String name = rs.getString("name");
                            int age = rs.getInt("age");
                            String address = rs.getString("address");
                            float salary = rs.getFloat("salary");
                            System.out.println("ID = " + id);
                            System.out.println("NAME = " + name);
                            System.out.println("AGE = " + age);
                            System.out.println("ADDRESS = " + address);
                            System.out.println("SALARY = " + salary);
                            System.out.println();
                        }
                        rs.close();
                        stmt.close();
                        conn.close();

                        break;

                    case 5:
                        stmt = conn.createStatement();
                        sql = "DELETE from COMPANY where ID = 2;";
                        stmt.executeUpdate(sql);
                        conn.commit();

                        rs = stmt.executeQuery("SELECT * FROM COMPANY;");
                        while (rs.next()) {
                            int id = rs.getInt("id");
                            String name = rs.getString("name");
                            int age = rs.getInt("age");
                            String address = rs.getString("address");
                            float salary = rs.getFloat("salary");
                            System.out.println("ID = " + id);
                            System.out.println("NAME = " + name);
                            System.out.println("AGE = " + age);
                            System.out.println("ADDRESS = " + address);
                            System.out.println("SALARY = " + salary);
                            System.out.println();
                        }
                        rs.close();
                        stmt.close();
                        conn.close();

                        break;

                    default:

                }


            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            //System.out.println("Table created successfully");
            //System.out.println("Records created successfully");
            System.out.println("Operation done successfully");

            System.out.println("Want to repeat?");
            repeat = input.next();
            repeat = repeat.toUpperCase();

        }while(repeat.equals("Y"));
    }

    //public static void insert(){
    /**
     * Insert data
     */
//        stmt = conn.createStatement();
//        String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
//                + "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
//        stmt.executeUpdate(sql);
//
//        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
//                + "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
//        stmt.executeUpdate(sql);
//
//        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
//                + "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
//        stmt.executeUpdate(sql);
//
//        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
//                + "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
//
//        stmt.executeUpdate(sql);
//        stmt.close();
//        conn.commit();
//        conn.close();
    //}
}
