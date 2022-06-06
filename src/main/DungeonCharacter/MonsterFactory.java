package main.DungeonCharacter;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The type Monster factory.
 */
public class MonsterFactory {
    /**
     * Create monster factory.
     *
     * @param theMonster the the monster
     * @return the monster
     */
    public Monster createMonster(String theMonster) {
        SQLiteDataSource ds = null;

        //establish connection (creates db file if it does not exist :-)
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:monster.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
        String query = "";
        if (theMonster.equalsIgnoreCase("ogre")) {
            query = "SELECT * FROM monster WHERE NAME= 'Ogre' ";
        } else if (theMonster.equalsIgnoreCase("gremlin")) {
            query = "SELECT * FROM monster WHERE NAME= 'Gremlin' ";
        } else if (theMonster.equalsIgnoreCase("skeleton")) {
            query = "SELECT * FROM monster WHERE NAME= 'Skeleton' ";
        } else if (theMonster.equalsIgnoreCase("Lord of OO")) {
            query = "SELECT * FROM monster WHERE NAME= 'Lord of OO' ";
        }else {
            throw new IllegalArgumentException("Illegal Argument; Monster must be an Ogre, Gremlin, or Skeleton");
        }

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

                return new Monster(name,hp,minDam,maxDam,attkSpeed,hitChance,healChance,minHeal,maxHeal);
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );

        }
        return null;
    }
}
