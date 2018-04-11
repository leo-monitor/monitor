package com.monitor.service;


import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.monitor.dao.TomcatlogMapper;
import com.monitor.utils.JqQuery;
import com.monitor.utils.SimpleListObject;
import com.monitor.utils.StringTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.monitor.entity.Tomcatlog;


@Service
public class TomcatlogService {
    @Autowired
    private TomcatlogMapper tomcatlogmapper;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    public SimpleListObject<Tomcatlog> query(JqQuery query) {

        try {
            PageBounds pagebounds=new PageBounds();
            pagebounds.setLimit(query.getLimit());
            pagebounds.setPage(query.getPage());

            if(StringTool.isNullOrEmpty(query.getOrderfield())){
                StringBuilder sbOrderField=new StringBuilder("oid");
                query.setOrdertype("asc");
                query.setOrderfield(sbOrderField.toString());
            }
            PageList<Tomcatlog> lstResults=this.tomcatlogmapper.query(query,pagebounds);
            SimpleListObject<Tomcatlog> slno=new SimpleListObject<Tomcatlog>();
            slno.setRows(lstResults);
            slno.setResult(1);
            slno.setRecords(lstResults.getPaginator().getTotalCount());
            slno.setTotal(lstResults.getPaginator().getTotalPages());
            return slno;
        } catch (Exception e) {
            logger.error("TomcatlogService.query",e);
            return null;
        }
    }

    public int copylog(String file, String database) {
        if (null == database) {
            return tomcatlogmapper.copylog(file);
        } else if (database.equals("mysql")) {
            return tomcatlogmapper.copylogformysql(file);
        }
        return 0;
    }
}
