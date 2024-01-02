package com.dahuatech.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.mapreduce</p>
 * <p>className: WordCountReducer</p>
 * <p>date: 1/1/2024</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    private IntWritable v = new IntWritable();
    private Logger logger = LoggerFactory.getLogger(WordCountMapper.class);

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        System.out.println("Reduce Task");
        logger.info("Reduce Task");
        int sum = 0;
        for (IntWritable value : values) {
            sum += value.get();
        }
        v.set(sum);
        context.write(key, v);
    }
}
