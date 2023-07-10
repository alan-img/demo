package com.dahuatech.spring.service.impl;

import com.dahuatech.spring.dao.PersonDao;
import com.dahuatech.spring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.service.impl</p>
 * <p>className: PersonServiceImpl</p>
 * <p>date: 2023/3/17</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public int getPerson(int id) {
        return personDao.getPerson(id);
    }
}
