package com.dahuatech.bean;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.Random;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.bean</p>
 * <p>className: GenerateData</p>
 * <p>date: 2025/2/20</p>
 *
 * @author qinjiawei(Administrator)
 * @version 1.0.0
 * @since JDK8.0
 */

public class GenerateData implements SourceFunction<String> {
    private boolean flag = true;

    @Override
    public void run(SourceContext<String> ctx) throws Exception {
        Random random = new Random();

        while (flag) {
            ctx.collect(random.nextInt(100) + "alan" + random.nextInt(100));
        }
    }

    @Override
    public void cancel() {
        flag = false;
    }
}
