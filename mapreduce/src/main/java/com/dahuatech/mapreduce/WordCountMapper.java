package com.dahuatech.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.mapreduce</p>
 * <p>className: WordCountMapper</p>
 * <p>date: 1/1/2024</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private Text k = new Text();
    private IntWritable v = new IntWritable(1);
    private Logger logger = LoggerFactory.getLogger(WordCountMapper.class);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        System.out.println("Map Task");
        logger.info("Map Task");
        String[] words = value.toString().split(" ");
        for (String word : words) {
            k.set(word);
            context.write(k, v);
        }
    }
}
