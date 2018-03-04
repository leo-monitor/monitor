package com.monitor.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.dao.TomcatlogDao;
import com.monitor.model.Tomcatlog;
import com.monitor.utils.IoUtil;


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
        // TODO Auto-generated method stub
        return tomcatlogDao.testget();
    }
}
