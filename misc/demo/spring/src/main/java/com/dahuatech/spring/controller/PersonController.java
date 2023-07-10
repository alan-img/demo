package com.dahuatech.spring.controller;

import com.dahuatech.spring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.controller</p>
 * <p>className: PersonController</p>
 * <p>date: 2023/3/17</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */
@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    public int getPerson(int id) {
        System.out.println(personService.getPerson(id));
        return 0;
    }
}
