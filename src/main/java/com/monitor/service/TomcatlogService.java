package com.monitor.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.dao.TomcatlogDao;
import com.monitor.entity.Tomcatlog;


@Service
public class TomcatlogService {
    @Autowired
    private TomcatlogDao tomcatlogDao;

    public int copylog(String file, String database) {
        if (null == database) {
            return tomcatlogDao.copylog(file);
        } else if (database.equals("mysql")) {
            return tomcatlogDao.copylogformysql(file);
        }
        return 0;
    }

    public List<Tomcatlog> testget() {
        return tomcatlogDao.testget();
    }
}
