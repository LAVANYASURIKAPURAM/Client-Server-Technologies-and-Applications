// cc MaxTemperatureLatMapper Mapper for maximum temperature example
// vv MaxTemperatureLatMapper
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxTemperatureLatMapper
  extends Mapper<LongWritable, Text, Text, IntWritable> {

  private static final int MISSING = 9999;
  
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    
    String line = value.toString();
    String lat_input = "";
    String latitude_per_ten_deg = "";
    if (line.charAt(28) == '-') { // parseInt doesn't like leading plus signs
         if (line.charAt(29) == '0'){
               lat_input = line.substring(29, 31);
               latitude_per_ten_deg = "Max Temperature at latitude range 0-9.99";
               }
         if (line.charAt(29) == '1'){
             lat_input = line.substring(29, 31);
             latitude_per_ten_deg = "Max Temperature at latitude range 10-19.99";
             }

         if (line.charAt(29) == '2'){
             lat_input = line.substring(29, 31);
             latitude_per_ten_deg = "Max Temperature at latitude range 20-29.99";
             }

         if (line.charAt(29) == '3'){
             lat_input = line.substring(29, 31);
             latitude_per_ten_deg = "Max Temperature at latitude range 30-39.99";
             }

         if (line.charAt(29) == '4'){
             lat_input = line.substring(29, 31);
             latitude_per_ten_deg = "Max Temperature at latitude range 40-49.99";
             }
         if (line.charAt(29) == '5'){
             lat_input = line.substring(29, 31);
             latitude_per_ten_deg = "Max Temperature at latitude range 50-59.99";
             }
         if (line.charAt(29) == '6'){
             lat_input = line.substring(29, 31);
             latitude_per_ten_deg = "Max Temperature at latitude range 60-69.99";
             }
         
         if (line.charAt(29) == '7'){
             lat_input = line.substring(29, 31);
             latitude_per_ten_deg = "Max Temperature at latitude range 70-79.99";
             }
         if (line.charAt(29) == '8'){
             lat_input = line.substring(29, 31);
             latitude_per_ten_deg = "Max Temperature at latitude range 80-89.99";
             }
		 if (line.charAt(29) == '9'){
             lat_input = line.substring(29, 31);
             latitude_per_ten_deg = "Max Temperature at latitude range 90";
             }
          }
    int airTemperature;
    if (line.charAt(87) == '+') { // parseInt doesn't like leading plus signs
      airTemperature = Integer.parseInt(line.substring(88, 92));
    } else {
      airTemperature = Integer.parseInt(line.substring(87, 92));
    }
    String quality = line.substring(92, 93);
    if (airTemperature != MISSING && quality.matches("[01459]") && latitude_per_ten_deg != "") {
      context.write(new Text(latitude_per_ten_deg), new IntWritable(airTemperature));
    }
  }
}
// ^^ MaxTemperatureLatMapper
