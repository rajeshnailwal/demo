package practice.apache.hadoop.mapreduce.newapi;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
public class WordCountProgram {
	
	public static void main(String...strings) throws IOException, ClassNotFoundException, InterruptedException {
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(WordCountProgram.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.setInputPaths(job, new Path(strings[0]));
		FileOutputFormat.setOutputPath(job, new Path(strings[1]));
		
		boolean status = job.waitForCompletion(true);
		if (status) {
            System.exit(0);
        } 
        else {
            System.exit(1);
        }
	}
	
}


//https://hadoop.apache.org/docs/current/hadoop-yarn/hadoop-yarn-site/NodeManager.html#Disk_Checker
//https://stackoverflow.com/questions/29131449/why-does-hadoop-report-unhealthy-node-local-dirs-and-log-dirs-are-bad
//https://stackoverflow.com/questions/28958999/hdfs-write-resulting-in-createsymboliclink-error-1314-a-required-privilege
//https://stackoverflow.com/questions/11458519/how-to-kill-hadoop-jobs
//https://stackoverflow.com/questions/11961517/new-to-hadoop-mapreduce-getting-a-nosuchmethodexception-on-mapred-reducer-init



//https://stackoverflow.com/questions/24075669/mapreduce-job-fail-when-submitted-from-windows-machine
//https://issues.apache.org/jira/browse/MAPREDUCE-4052
//https://stackoverflow.com/questions/23872677/error-while-running-mapreduceyarnfrom-windows-eclipse/24098346#24098346
//https://issues.apache.org/jira/browse/MAPREDUCE-5655
//https://github.com/spring-projects/spring-hadoop-samples/issues/4
//https://discuss.pivotal.io/hc/en-us/community/posts/200760137--Remote-map-reduce-job-fails
