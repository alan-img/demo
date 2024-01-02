package com.dahuatech.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.mapreduce</p>
 * <p>className: WordCountDriver</p>
 * <p>date: 1/1/2024</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */

public class WordCountDriver {
    private static Logger logger = LoggerFactory.getLogger(WordCountDriver.class);

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);
        job.setJarByClass(WordCountDriver.class);
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        String src = "D:\\dev\\idea\\project\\demo\\mapreduce\\src\\main\\resources\\input.txt";
        String dst = "D:\\dev\\idea\\project\\demo\\mapreduce\\src\\main\\resources\\output";
        src = args[0];
        dst = args[1];
        FileInputFormat.setInputPaths(job, src);
        FileOutputFormat.setOutputPath(job, new Path(dst));
        System.out.println("AM Task");
        logger.info("AM Task");
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }
}
