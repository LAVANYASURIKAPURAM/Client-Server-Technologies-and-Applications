// cc MaxInvalidIdMapper Mapper for MaxInvalidId temperature example
// vv MaxInvalidIdMapper
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxInvalidIdMapper
  extends Mapper<LongWritable, Text, Text, Text> {
  private static final int MISSING = 999999;
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    String line = value.toString();
	String station_key = "station_key";
    String stat_ID = line.substring(4, 10);
    int airTemperature;
    if (line.charAt(87) == '+') { // parseInt doesn't like leading plus signs
      airTemperature = Integer.parseInt(line.substring(88, 92));
    } else {
      airTemperature = Integer.parseInt(line.substring(87, 92));
    }
    String quality = line.substring(92, 93);
    //parsing the valid station id, to find out all the invalid temperature in the valid station id
    if (Integer.parseInt(stat_ID) != 999999) {
      context.write(new Text(station_key), new Text(String.valueOf(stat_ID+"_"+airTemperature)));
    }
  }
}
// ^^ MaxInvalidIdMapper
