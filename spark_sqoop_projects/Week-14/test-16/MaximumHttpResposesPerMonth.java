//author : Lavanya surikapuram
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.GzipCodec;


public class MaximumHttpResposesPerMonth {

  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      System.err.println("Usage: MaxTemperatureWithMapOutputCompression " +
        "<input path> <output path>");
      System.exit(-1);
    }
    
    // vv MaxTemperatureWithMapOutputCompression
    Configuration conf = new Configuration();
    conf.setBoolean(Job.MAP_OUTPUT_COMPRESS, true);
    conf.setClass(Job.MAP_OUTPUT_COMPRESS_CODEC, GzipCodec.class,
        CompressionCodec.class);
    Job job = new Job(conf);
    job.setJarByClass(MaximumHttpResposesPerMonth.class);
    job.setJobName("MHRPM TEST 16-1R-TXT LS");

    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    
    job.setMapperClass(MaximumHttpResposesPerMonthMapper.class);
    job.setCombinerClass(MaximumHttpResposesPerMonthReducer.class);
   job.setReducerClass(MaximumHttpResposesPerMonthReducer.class);
    job.setNumReduceTasks(1);
    job.setMapOutputValueClass(Text.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
