import java.io.IOException;
import java.util.*;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaximumHttpResposesPerMonthReducer
  extends Reducer<Text, Text, Text, Text> {
  Text emitValue = new Text();
  @Override
  public void reduce(Text key, Iterable<Text> values,
      Context context)
      throws IOException, InterruptedException {
    HashMap<String,Integer> url_map = new HashMap<String,Integer>();
    int url_chk = 1;
    for (Text v : values) {
      String URL = v.toString();
      if(URL.length() > 1)
      {
        if(!url_map.containsKey(URL)){
          url_map.put(URL,url_chk);
        }
   else
	{
          url_map.put(URL,url_map.get(URL)+1);            
        }
      }
    }  

    String MAX_HIT_URL="";
    int max_hit_url_count = 0;
    for (String URL: url_map.keySet())
	{
      int final_url_tmp = url_map.get(URL);
      if (final_url_tmp>max_hit_url_count){
        MAX_HIT_URL = URL;
        max_hit_url_count = final_url_tmp;
      }
	  
    }
	 String myValue = MAX_HIT_URL + "\t" + max_hit_url_count;
     emitValue.set(myValue);
    context.write(emitValue, new Text(key));
  }
}
