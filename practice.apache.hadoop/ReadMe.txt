//https://hadoop.apache.org/docs/r1.2.1/mapred_tutorial.html
//http://www.journaldev.com/8921/hadoop2-mapreduce-wordcounting-example
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
//http://hadooptutorial.info/creating-custom-hadoop-writable-data-type/
http://coe4bd.github.io/HadoopHowTo/writableMultipleValues/writableMultipleValues.html
https://stackoverflow.com/questions/15810550/output-a-list-from-a-hadoop-map-reduce-job-using-custom-writable

How to run :-
C:\Development\hadoop-2.7.1\sbin> hadoop jar C:\Development\mapreduce_wordcount.jar practice.apache.hadoop.mapreduce.newapi.WordCountProgram /hadooptest/wordcount/input /hadooptest/wordcount/output
C:\Development\hadoop-2.7.1\sbin> hadoop jar C:\Development\mapreduce_wordcount.jar practice.apache.hadoop.mapreduce.customkey.RecordProcessingProgram /hadooptest/customkey/input /hadooptest/customkey/output
C:\Development\hadoop-2.7.1\sbin> hadoop jar C:\Development\mapreduce_wordcount.jar practice.apache.hadoop.mapreduce.customvalue.RecordProcessingProgram /hadooptest/customvalue/input /hadooptest/customvalue/output