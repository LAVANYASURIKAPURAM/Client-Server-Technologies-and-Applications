import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaximumHttpResposesPerMonthMapper
  extends Mapper<LongWritable, Text, Text, Text> {

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    
          String line = value.toString(); 
    if(line.charAt(0) != '#'){
     if(!line.startsWith("#")){
		 if (line.charAt(0) == '2' && line.charAt(1) == '0' && line.charAt(2) == '1'){
		  if (line.charAt(3) == '6' || line.charAt(3) == '2' ){
      if(!line.contains("/index.")){
        if(line.contains(" 200 ")){
         String year_month = line.substring(0, 7);
		 
	    int urlstart = line.indexOf("/");
     	    String url_end = line.substring(urlstart);
         int urlend = url_end.indexOf(" - ");
         String URL = url_end.substring(1,(urlend));
		 
		
		context.write(new Text(year_month), new Text(URL));
         
		}
        }
	  }
      }
	 }
    }
  }
}
