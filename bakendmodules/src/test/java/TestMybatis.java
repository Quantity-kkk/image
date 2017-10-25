import com.kyq.zwj.backend.entity.User;
import com.kyq.zwj.backend.modules.laneimagefile.dao.UserDao;
import com.kyq.zwj.backend.util.UUIDUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        System.out.print(UUIDUtil.getUUID());
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
        ApplicationContext ctx=null;
        ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao=(UserDao) ctx.getBean("userDao");

        User user=new User();
        //添加两条数据
        user.setId(1);
        user.setUsername("Jessica");
        user.setPassword("123");
        userDao.addUser(user);
        user.setId(2);
        user.setUsername("Jessica2");
        user.setPassword("123");
        userDao.addUser(user);
        System.out.println("添加成功");
        //查询数据
        user.setUsername("Jessica");
        user.setPassword("123");
        System.out.println(userDao.getUser(user).toString());
        user.setUsername("Jessica2");
        user.setPassword("123");
        System.out.println(userDao.getUser(user).toString());
        //修改数据
        user.setId(2);
        user.setPassword("802");
        userDao.updateUser(user);
        System.out.println("修改成功");
        //删除数据
        userDao.deleteUser(1);
        System.out.println("删除成功");
    }
}
