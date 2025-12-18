package cat.almata.dam.amarin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ComboBoxModel;

public class DataActions {
	
	public static List<DTOMainformCiutat> getAllCiutats() throws SQLException{
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world" ,"root","user")){
			//Aquest és l'sql que s'executara en base de dades
			String sql = "SELECT city.name as cityname, country.name as countryname, district, city.population as citypopulation "
					+ "FROM city LEFT JOIN country ON city.CountryCode=country.code order by cityname;";
			Statement statement = con.createStatement();
			ResultSet  result = statement.executeQuery(sql);
			List<DTOMainformCiutat> ciutats = new ArrayList<DTOMainformCiutat>();
			//de cada resultat creo una nova instancia de la classe ciutat i la posa a dins de la llista
			while(result.next()) {
				ciutats.add(new DTOMainformCiutat(
						result.getString("cityname"),
						result.getString("countryname"),
						result.getString("district"),
						result.getLong("citypopulation")
						));
			}
			//ho tanco tot
			result.close();
			statement.close();
			con.close();
			return ciutats;
		}
	}
	public static List<DTOMainformCiutat> getCiutats(String nomCiutat) throws SQLException{
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world" ,"root","user")){
			String sql = "SELECT city.name as cityname, country.name as countryname, district, city.population as citypopulation "
					+ "FROM city LEFT JOIN country ON city.CountryCode=country.code WHERE city.name LIKE '%"+nomCiutat+"%' ORDER BY cityname;";
			Statement statement = con.createStatement();
			ResultSet  result = statement.executeQuery(sql);
			List<DTOMainformCiutat> ciutats = new ArrayList<DTOMainformCiutat>();
			while(result.next()) {
				ciutats.add(new DTOMainformCiutat(
						result.getString("cityname"),
						result.getString("countryname"),
						result.getString("district"),
						result.getLong("citypopulation")
						));
			}
			result.close();
			statement.close();
			con.close();
			return ciutats;
		}
	}
	public static Vector<String> getVectorPaisos() throws SQLException {
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world" ,"root","user")){
			String sql = "SELECT name AS countryname "
					+ "FROM country ORDER BY name;";
			Statement statement = con.createStatement();
			ResultSet  result = statement.executeQuery(sql);
			Vector<String> paisos = new Vector<String>();
			while(result.next()) {
				paisos.add(result.getString("countryname"));
			}
			result.close();
			statement.close();
			con.close();
			return paisos;
		}
	}
	public static Vector<String> getVectorDistrictes(String nomPais) throws SQLException {
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world" ,"root","user")){
			String sql = "SELECT DISTINCT district "
					+ "FROM city LEFT JOIN country ON city.CountryCode=country.code WHERE country.name = '"+nomPais+"' ORDER BY district;";
			Statement statement = con.createStatement();
			ResultSet  result = statement.executeQuery(sql);
			Vector<String> districtes = new Vector<String>();
			while(result.next()) {
				districtes.add(result.getString("district"));
			}
			result.close();
			statement.close();
			con.close();
			return districtes;
		}
	}
	public static boolean esCiutatRepetida(String nomCiutat, String nomPais) throws SQLException{
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world" ,"root","user")){
			int contador=0;
			//Aquest és l'sql que s'executara en base de dades
			String sql = "SELECT * FROM city INNER JOIN country ON city.CountryCode=country.code "
					+ "WHERE city.name ='"+nomCiutat+"' AND country.name = '"+nomPais+"';";
			
			Statement statement = con.createStatement();
			ResultSet  result = statement.executeQuery(sql);
			List<DTOMainformCiutat> ciutats = new ArrayList<DTOMainformCiutat>();
			//de cada resultat sumo 1 al contador perque si és superior a 1 aleshores voldrà dir que hi haurà alguna ciutat al mateix pais
			while(result.next()) {
				contador++;
			}
			//ho tanco tot
			result.close();
			statement.close();
			con.close();
			return contador>0;
		}
	}
	
	
	

	public static void deleteCiutat(String nomCiutat,String nomPais) throws SQLException{
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world" ,"root","user")){
			String sql = "DELETE FROM city WHERE name = '"+nomCiutat+"' AND countrycode = '"+getCountrycode(nomPais)+"';";
			Statement statement = con.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			con.close();
		}
	}
	

	public static void createCiutat(DTOMainformCiutat ciutat) throws SQLException{
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world" ,"root","user")){
			String sql = "INSERT INTO city(name,countrycode,district,population) "
					+ "VALUES('"+ciutat.getNom()+
					"',(SELECT DISTINCT code FROM country WHERE name = '"+ciutat.getPais()+"'), '"
					+ciutat.getDistricte()+"', "+ciutat.getPoblacio()+");";
			Statement statement = con.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			con.close();
		}
	}
	private static String getCountrycode(String nomPais) throws SQLException {
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world" ,"root","user")){
			//Aquest és l'sql que s'executara en base de dades
			/*En teoria la querry per aconseguir el nom del pais no hauria de donar problemes sempre i cuan no es modifiqui la taula country
			per tenir dos paisos amb el mateix nom pero diferent codi, actualment no n'hi ha cap,
			es pot comprovar amb aquesta querry: */
			String codi;
			String sql = "SELECT DISTINCT code FROM country WHERE name = '"+nomPais+"';" ;
			Statement statement = con.createStatement();
			ResultSet  result = statement.executeQuery(sql);
			//Agafo el primer resultat ja que en teoria només n'hi hauria d'haver 1
			result.next();
			codi = result.getString("code");
			//ho tanco tot
			result.close();
			statement.close();
			con.close();
			return codi;
		}
	}
}
