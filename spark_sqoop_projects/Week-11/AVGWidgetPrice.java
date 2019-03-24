//LAVANYA SURIKAPURAM
import java.io.IOException;
import com.cloudera.sqoop.lib.RecordParser.ParseError;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.*;
import java.math.*;


public class AVGWidgetPrice extends Configured implements Tool {

  public static class MaxWidgetMapper
      extends Mapper<LongWritable, Text, Text, FloatWritable> {

    private Widget maxWidget = null;
	private String widname = null;
	private Float wid_price = 0.1f;
	BigDecimal wid_price_bd;

    public void map(LongWritable key, Text value, Context context)
	throws IOException, InterruptedException	{
      Widget widget = new Widget();
      try {
        widget.parse(value); // Auto-generated: parse all fields from text.
      } catch (ParseError pe) {
        // Got a malformed record. Ignore it.
        return;
      }
	  
      Integer id = widget.get_id();
	  widname = widget.get_widget_name();
	  
	  //converting bigdecimal to float
	  wid_price_bd = widget.get_price();
	  
      if (null == id) {
        return;
      } else {
          wid_price = wid_price_bd.floatValue();
		  context.write(new Text(widname), new FloatWritable(wid_price));
      }
    }
  }

  public static class MaxWidgetReducer
      extends Reducer<Text, FloatWritable, Text, FloatWritable> {

    // There will be a single reduce call with key '0' which gets
    // the max widget from each map task. Pick the max widget from
    // this list.
    public void reduce(Text key, Iterable<FloatWritable> vals, Context context)
        throws IOException, InterruptedException {

	  float total = 0.1f;
	  int count = 0;
	  for (FloatWritable w : vals) {
		  total += w.get();
		  count++;
	  }
	  Float avg_wid_price = (Float) total / count;
        context.write(key, new FloatWritable(avg_wid_price));
    }
  }

  public int run(String [] args) throws Exception {
    Job job = new Job(getConf());

    job.setJarByClass(AVGWidgetPrice.class);

    job.setMapperClass(MaxWidgetMapper.class);
    job.setReducerClass(MaxWidgetReducer.class);

    FileInputFormat.addInputPath(job, new Path("widgets"));
    FileOutputFormat.setOutputPath(job, new Path("avgwidgetprice"));

    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(FloatWritable.class);

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(FloatWritable.class);

    job.setNumReduceTasks(1);

    if (!job.waitForCompletion(true)) {
      return 1; // error.
    }

    return 0;
  }

  public static void main(String [] args) throws Exception {
    int ret = ToolRunner.run(new AVGWidgetPrice(), args);
    System.exit(ret);
  }
}
