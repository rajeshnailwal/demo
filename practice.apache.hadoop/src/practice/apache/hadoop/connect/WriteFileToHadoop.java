package practice.apache.hadoop.connect;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

public class WriteFileToHadoop {

	public static void main(String[] args) throws IOException, URISyntaxException {
		
		Path path = new Path("hdfs://localhost:19000/hadooptest");
		Configuration conf = new Configuration();
	    FileSystem fileSystem = FileSystem.get(new URI("hdfs://localhost:19000"), conf);
	    if(fileSystem instanceof DistributedFileSystem) {
	    	System.out.println("yes : hdfs file system is connected");
	    	
	    	if(fileSystem.exists(path)) {
	    		InputStream inputStream = new BufferedInputStream(new FileInputStream("C:\\Development\\eclipse_neon\\workspace\\practice.apache.hadoop\\LocalFile.txt"));
	    		System.out.println("yes : hdfs file exists");
	    		OutputStream outputStream = null;
	    		try {
	    			path = new Path("hdfs://localhost:19000/hadooptest/Hadoop_File.txt");
	    			outputStream = fileSystem.create(path, new Progressable() {
						
						@Override
						public void progress() {
							System.out.println("..");
						}
					});
	    			IOUtils.copyBytes(inputStream, outputStream, 4096, false);
	    		} finally {
	    			IOUtils.closeStream(inputStream);
	    			IOUtils.closeStream(outputStream);
	    		}
	    	}
	    } else {
	    	System.out.println("Other type of file system "+fileSystem.getClass());
	    }
	}

}
