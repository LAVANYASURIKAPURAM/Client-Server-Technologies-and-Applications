// cc MaxInvalidIdReducer Reducer for maximum invalid temperature in the valid stationID example
// vv MaxInvalidIdReducer
import java.io.IOException;
import java.util.List;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MaxInvalidIdReducer
  extends Reducer<Text, Text, Text, Text> {

  @Override
  public void reduce(Text key, Iterable<Text> values,
      Context context)
      throws IOException, InterruptedException {
	//splitting up the value string into temperature and year  
	   Map<String, List<Integer>> yr_temp_map = new HashMap<String, List<Integer>>();
       List<String> year_temp_rec = new ArrayList<String>();
       List<Integer> airTemp = new ArrayList<Integer>();
       Map<String, String> map_expanded_values = new HashMap<String, String>();

	   
      //splitting up the concatinated values from the mapper 
      int i=0;
      for(Text val: values)
       {
         String yr_airTemp = val.toString();
         String[] str_arr_yr_airTemp = yr_airTemp.split("_");
         year_temp_rec.add(str_arr_yr_airTemp[0]);
         airTemp.add(Integer.parseInt(str_arr_yr_airTemp[1]));
         i++;
       }
	   
	   //Converting the year and temperature to key and value pairs using hash map
       for(i=0; i< year_temp_rec.size(); i++)
       {
         
         if(yr_temp_map.get(year_temp_rec.get(i))== null)
           yr_temp_map.put(year_temp_rec.get(i), new ArrayList<Integer>());
         yr_temp_map.get(year_temp_rec.get(i)).add(airTemp.get(i));
       }
       Map<String, Double> percentage_cal = new HashMap<String, Double>();

      for (Entry<String, List<Integer>> ent : yr_temp_map.entrySet()) {
          String keys = ent.getKey();
          List<Integer> value = ent.getValue();
          double invld_cnt = 0;
          double totalinvld_cnt = 0;
          double invld_per =0;

          for(int invalid_temp : value)
          {
            totalinvld_cnt++;
            if(invalid_temp == 9999)
            invld_cnt++;
          }

          invld_per = (invld_cnt*100)/totalinvld_cnt;

          percentage_cal.put(keys,invld_per);
      }
      Double maxValue = Double.MIN_VALUE;
      for (Entry<String, Double> ent : percentage_cal.entrySet()) {
        String keys = ent.getKey();
        Double value = ent.getValue();
        maxValue = Math.max(maxValue, value);
        }
      Map<String, Double> mapFinal = new HashMap<String, Double>();
      for (Entry<String, Double> ent : percentage_cal.entrySet()) {
        String keys = ent.getKey();
        Double value = ent.getValue();
        int retri_val = Double.compare(value, maxValue);
        if(retri_val == 0)
        {
          mapFinal.put(ent.getKey(),maxValue);
        }
      }
for (Entry<String, Double> ent : mapFinal.entrySet()) {
        context.write(new Text(String.valueOf(ent.getKey())), new Text(String.valueOf(ent.getValue())));
     }
  }
}
// ^^ MaxInvalidIdReducer
