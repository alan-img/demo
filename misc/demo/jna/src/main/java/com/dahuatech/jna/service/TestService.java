package com.dahuatech.jna.service;

import com.dahuatech.jna.bean.Person;
import com.sun.jna.Library;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.jna.service</p>
 * <p>className: TestService</p>
 * <p>date: 2023/4/30</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */
public interface TestService extends Library {
    int hello(Person p);
}
