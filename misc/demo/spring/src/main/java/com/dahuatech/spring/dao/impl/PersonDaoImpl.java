package com.dahuatech.spring.dao.impl;

import com.dahuatech.spring.dao.PersonDao;
import org.springframework.stereotype.Repository;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.dao.impl</p>
 * <p>className: PersonDaoImpl</p>
 * <p>date: 2023/3/17</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */
@Repository
public class PersonDaoImpl implements PersonDao {
    @Override
    public int getPerson(int id) {
        return id;
    }
}
