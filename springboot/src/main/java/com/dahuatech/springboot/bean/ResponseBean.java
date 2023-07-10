package com.dahuatech.springboot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.bean</p>
 * <p>className: R</p>
 * <p>date: 2023/3/22</p>
 *
 * @author qinjiawei(336105)
 * @version 1.0.0
 * @since JDK8.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBean {
    private int code;
    private String msg;
}
