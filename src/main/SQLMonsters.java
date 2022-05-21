package main;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author tom capaul
 *
 * Simple class to demonstrate SQLite connectivity
 * 1) create connection
 * 2) add a table
 * 3) add entries to the table
 * 4) query the table for its contents
 * 5) display the results
 *
 * NOTE: any interactions with a database should utilize a try/catch
 * since things can go wrong
 *
 * @see <a href="https://shanemcd.org/2020/01/24/how-to-set-up-sqlite-with-jdbc-in-eclipse-on-windows/">
https://shanemcd.org/2020/01/24/how-to-set-up-sqlite-with-jdbc-in-eclipse-on-windows/</a>
 *
 * Refactoring original code for monster classes dont run again or it will add the monsters twice, if you
 * want to rerun then you have to delete monsters.db first
 */
public class SQLMonsters {

    public static void main(String[] args) {
        SQLiteDataSource ds = null;

        //establish connection (creates db file if it does not exist :-)
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:monsters.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println( "Opened database successfully" );


        //now create a table
        String query = "CREATE TABLE IF NOT EXISTS monsters ( " +
                "NAME TEXT, " +
                "HP INTEGER, " +
                "MIND INTEGER,"+
                "MADAM INTEGER,"+
                "SPEED INTEGER,"+
                "HITCHANCE FLOAT ,"+
                "HEALCHANCE FLOAT,"+
                "MINHEAL INTEGER,"+
                "MAXHEAL INTEGER)";
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( query );
            System.out.println( "executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        System.out.println( "Created monster table successfully" );

        //next insert two rows of data
        System.out.println( "Attempting to insert 3 rows into monsters table" );

        String query1 = "INSERT INTO monsters ( NAME, HP, MIND, MADAM, SPEED, HITCHANCE, HEALCHANCE, MINHEAL, MAXHEAL ) VALUES ( 'Ogre', 200, 30, 60, 2, 0.6, 0.1, 30, 60 )";
        String query2 = "INSERT INTO monsters ( NAME, HP, MIND, MADAM, SPEED, HITCHANCE, HEALCHANCE, MINHEAL, MAXHEAL ) VALUES ( 'Gremlin', 70, 15, 30, 5, 0.8, 0.4, 20, 40 )";
        String query3 = "INSERT INTO monsters ( NAME, HP, MIND, MADAM, SPEED, HITCHANCE, HEALCHANCE, MINHEAL, MAXHEAL ) VALUES ( 'Skeleton', 100, 30, 50, 3, 0.8, 0.3, 30, 50 )";


        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( query1 );
            System.out.println( "1st executeUpdate() returned " + rv );

            rv = stmt.executeUpdate( query2 );
            System.out.println( "2nd executeUpdate() returned " + rv );

            rv = stmt.executeUpdate( query3 );
            System.out.println( "3rd executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }


        //now query the database table for all its contents and display the results
        System.out.println( "Selecting all rows from test table" );
        query = "SELECT * FROM monsters";

        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            //walk through each 'row' of results, grab data by column/field name
            // and print it
            while ( rs.next() ) {
                String name = rs.getString( "NAME" );
                int hp = rs.getInt( "HP" );
                int minDam = rs.getInt( "MIND" );
                int maxDam = rs.getInt( "MADAM" );
                int attkSpeed = rs.getInt( "SPEED" );
                float hitChance = rs.getFloat( "HITCHANCE" );
                float healChance = rs.getFloat( "HEALCHANCE" );
                int minHeal = rs.getInt( "MINHEAL" );
                int maxHeal = rs.getInt( "MAXHEAL" );

                System.out.println( name + "\n"
                        + hp +"\n"
                        + minDam +"\n"
                        + maxDam +"\n"
                        + attkSpeed +"\n"
                        + hitChance +"\n"
                        + healChance +"\n"
                        + minHeal + "\n"
                        + maxHeal +"\n" );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }

}