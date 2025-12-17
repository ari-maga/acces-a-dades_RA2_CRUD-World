package cat.almata.dam.amarin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataActions {
	
	public static List<DTOMainformCiutat> getAllCiutats() throws SQLException{
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world" ,"root","user")){
			String sql = "SELECT city.name as cityname, country.name as countryname, district, city.population as citypopulation "
					+ "FROM city LEFT JOIN country ON city.CountryCode=country.code order by cityname;";
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
	public static List<DTOMainformCiutat> getCiutats(String nomCiutat) throws SQLException{
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world" ,"root","user")){
			String sql = "SELECT city.name as cityname, country.name as countryname, district, city.population as citypopulation "
					+ "FROM city LEFT JOIN country ON city.CountryCode=country.code WHERE cityname LIKE '%"+nomCiutat+"%' ORDER BY cityname;";
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
	public static void deleteCiutat(String nomCiutat,String nomPais) throws SQLException{
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world" ,"root","user")){
			String sql = "DELETE FROM city WHERE city.name = '"+nomCiutat+"' AND '"+nomPais+"' = (SELECT country.name FROM country "
					+ "INNER JOIN city ON country.code = city.countrycode";
			Statement statement = con.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			con.close();
		}
	}
}
