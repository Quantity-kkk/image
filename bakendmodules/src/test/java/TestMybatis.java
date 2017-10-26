import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Description:
 * Copyright: © 2017 CSNT. All rights reserved.
 * Company:CSTC
 *
 * @version 1.0
 * @author: kyq1024
 * @timestamp: 2017-10-24 14:27
 */
public class TestMybatis {

    public static void main(String[] args) {
//        System.out.print(UUIDUtil.getUUID());
//        String name = "Pic_1_2_6300201_82_2_青A12345_0_1380390677774885489_20170905121212.jpg";
//        String arr[] = name.split("_");
//        System.out.println(arr.length);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            System.out.println(sf.parse("20170905121212"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testSelect(){
        ApplicationContext ctx=null;
        ctx=new ClassPathXmlApplicationContext("applicationContext.xml");

        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) ctx.getBean("sqlSessionFactory");

        SqlSession sqlSession = sqlSessionFactory.openSession();

        System.out.println(sqlSession.selectList("findRecords","select * from user"));
        sqlSession.close();
    }

    public void testCommon(){

    }
}
