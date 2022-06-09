package bcd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class App {


    public static void main( String[] args ) 
    {
        final String DB_URI = "jdbc:sqlite:app/src/main/resources/agenda.sqlite";

        System.out.print("Entre com o nome da cidade: ");
        Scanner ioIn = new Scanner( System.in );
        String cidade = ioIn.nextLine();

        // suscetível ao SQL Injection
        String query = "SELECT * FROM Pessoa WHERE cidade = ?";
        System.out.println("Query: " + query);

        try ( Connection conexao = DriverManager.getConnection( DB_URI );  
              PreparedStatement statement = conexao.prepareStatement(query)) 
        {
            statement.setString(1, cidade);
            ResultSet rs = statement.executeQuery();

            while ( rs.next() )
            {
                System.out.println( "IdPessoa: " + rs.getInt("idPessoa") + ", Nome: " + rs.getString("nome") );
            }

            rs.close();
            
        } catch ( Exception e ) 
        {
            System.err.println(e.toString());
        }

        ioIn.close();
    }
}
