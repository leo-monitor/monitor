package com.monitor.service;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
            //创建一个可缓存的线程池
            ExecutorService es2= Executors.newCachedThreadPool();
            MyThread my =new MyThread();
            MyThread my2 =new MyThread();
            es2.execute(my);//不用new Thread,一分钟还没有就释放
            es2.execute(my2);//不用new Thread,一分钟还没有就
            es2.execute(my2);//不用new Thread,一分钟还没有就
            es2.execute(my2);//不用new Thread,一分钟还没有就
            es2.execute(my2);//不用new Thread,一分钟还没有就

            System.out.println("当前线程名"+Thread.currentThread().getName());
            es2.shutdown();//关闭线程池，如果不关闭线程池将一直运行。
            System.out.println("开始执行查询");
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
class MyThread implements Runnable{

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for(int i=0;i<10;i++){
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            System.out.println("MyThread---"+i);
        }
    }

}
