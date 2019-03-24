import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class Parsing_Records_Maxtemperature{

        public static void main(String[] args) throws ClassNotFoundException, SQLException {

                Connection connection_mysql = null;
                BufferedReader buffread = null;
                Statement mysql_statement = null;
						//Connection to mysqldatabase
                        Class.forName("com.mysql.jdbc.Driver");
                        connection_mysql = DriverManager.getConnection(
                                        "jdbc:mysql://127.0.0.1/w_data", "root", "itmd521");
                        mysql_statement = connection_mysql.createStatement();
            System.out.println("My SQL DATA BASE CONNECTION ESTABLISHED");

                        try {
                                String readl;

                                buffread = new BufferedReader(new FileReader("9091-sample.txt"));

                                while ((readl = buffread.readLine()) != null) {
                                        String usaf_wthr_id=readl.substring(4,10);
                                        String wban_wthr_id=readl.substring(4,10);
                                        String observ_year=readl.substring(15,19);
                                 
                                        String latitude=readl.substring(28,33);
                                        String longitude=readl.substring(34,40);
				        String elevation=readl.substring(46,50);
				        String wind_dir=readl.substring(60,62);
                                        String sky_ceiling_heigh=readl.substring(70,74);
                                        String visibility_dis=readl.substring(78,83);
                                           String air_temp;
                                         if (readl.charAt(87)=='+'){
                                air_temp = readl.substring(88, 92);
                                 }
                                 else {
                             air_temp = readl.substring(87, 92);
                                          }
 
                                        String due_pt_temp=readl.substring(93,97);

				        String atmp_pres=readl.substring(99,103);
                                    
                                        String insert= "insert into w_data.ncdc1 values (?,?,?,?,?,?,?,?,?,?,?,?);";
                                        PreparedStatement statement_weather = connection_mysql.prepareStatement(insert);
                                        statement_weather.setString(1,usaf_wthr_id );
                                        statement_weather.setString(2,wban_wthr_id);
                                        statement_weather.setString(3,observ_year);
                                        statement_weather.setString(4,latitude );
                                        statement_weather.setString(5,longitude);
                                        statement_weather.setString(6,elevation );
                                        statement_weather.setString(7,wind_dir );
										statement_weather.setString(8,sky_ceiling_heigh );
										statement_weather.setString(9, visibility_dis );
										statement_weather.setString(10,air_temp );
                                        statement_weather.setString(11,due_pt_temp);
										statement_weather.setString(12,atmp_pres);
                                        statement_weather.execute();
                                }
    String sql_query="select observ_year, max(air_temp) from w_data.ncdc1 where air_temp != 9999 group by observ_year;";
                                PreparedStatement max_temp_stamt = connection_mysql.prepareStatement(sql_query);
								System.out.println("Max Temparature for the concatinated 1992 and 1993 file -- 9293");
								System.out.println("================================================================");
                                ResultSet jdbc_result_set = max_temp_stamt.executeQuery();
                                while (jdbc_result_set.next())
                                {
                                        System.out.println(jdbc_result_set.getString(1)+"   "+jdbc_result_set.getString(2));
                                }
								System.out.println("================================================================");

                        } catch (IOException e) {
                                e.printStackTrace();
                        } finally {
                                try {
                                        if (buffread != null)
                                                buffread.close();
                                } catch (IOException ex) {
                                        ex.printStackTrace();
                                }
                        }

}}