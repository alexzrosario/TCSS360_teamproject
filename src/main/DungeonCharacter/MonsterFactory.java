package main.DungeonCharacter;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MonsterFactory {
    public Monster createMonster(String theMonster) {
        SQLiteDataSource ds = null;

        //establish connection (creates db file if it does not exist :-)
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:monsters.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
        String query = "";
        if (theMonster.equalsIgnoreCase("ogre")) {
            query = "SELECT * FROM monsters WHERE NAME= 'Ogre' ";
        } else if (theMonster.equalsIgnoreCase("gremlin")) {
            query = "SELECT * FROM monsters WHERE NAME= 'Gremlin' ";
        } else if (theMonster.equalsIgnoreCase("skeleton")) {
            query = "SELECT * FROM monsters WHERE NAME= 'Skeleton' ";
        } else {
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
                int hitChance = rs.getInt( "HITCHANCE" );
                int healChance = rs.getInt( "HEALCHANCE" );
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
