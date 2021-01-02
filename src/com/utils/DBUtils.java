package com.utils;

import org.apache.commons.beanutils.BeanUtils;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class DBUtils {
    public static Connection getConnection() throws Exception {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/db.properties");
        Properties properties = new Properties();
        properties.load(in);

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        return dataSource.getConnection();
    }

    //获取对象列表
    public static <T> List<T> getList(Class<T> clazz, String sql, Object... args) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> userList = null;

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);

            if (args != null && args.length > 0) {

                for (int i = 0; i < args.length; i++) {

                    ps.setObject(i + 1, args[i]);
                }
            }
            rs = ps.executeQuery();

            //获取结果集元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取当前结果集的列数
            int colnum = rsmd.getColumnCount();

            userList = new ArrayList<>();

            while (rs.next()) {

                //key存放列名， value存放列值，i循环完成后，rowMap存入一条记录
                Map<String, Object> rowMap = new HashMap<String, Object>();
                for (int i = 1; i <= colnum; i++) {
                    String colName = rsmd.getColumnLabel(i);
                    Object colValue = rs.getObject(colName);
                    //判断查询出来的类的类型，如果是java.sql.Date-->java.util.Date
                    if (colValue instanceof java.sql.Date) {
                        java.sql.Date date = (java.sql.Date) colValue;
                        colValue = new java.util.Date(date.getTime());
                    }
                    rowMap.put(colName, colValue);
                }
                //创建一个User对象，给这个User对象属性赋值
                T bean = clazz.newInstance();


                //循环rowMap给user所有属性赋值
                for (Map.Entry<String, Object> entry : rowMap.entrySet()) {
                    String propertyName = entry.getKey();
                    Object propertyValue = entry.getValue();
                    BeanUtils.setProperty(bean, propertyName, propertyValue);
                }

                userList.add(bean);


            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }

        return userList;
    }

    //保存对象方法
    public static boolean save(String sql, Object... args) {

        Connection conn = null;
        PreparedStatement ps = null;
        Integer count = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);

            if (args != null && args.length > 0) {

                for (int i = 0; i < args.length; i++) {

                    ps.setObject(i + 1, args[i]);
                }
            }

            //返回更新记录数
            count = ps.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }

        return count != null && count > 0 ? true : false;
    }

    //获取单个对象
    public static <T> T getSingleObj(Class<T> clazz, String sql, Object... args) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        T bean = null;

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);

            if (args != null && args.length > 0) {

                for (int i = 0; i < args.length; i++) {

                    ps.setObject(i + 1, args[i]);
                }
            }

            rs = ps.executeQuery();

            //获取结果集元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取当前结果集的列数
            int colnum = rsmd.getColumnCount();

            while (rs.next()) {

                //key存放列名， value存放列值，i循环完成后，rowMap存入一条记录
                Map<String, Object> rowMap = new HashMap<String, Object>();
                for (int i = 1; i <= colnum; i++) {
                    String colName = rsmd.getColumnLabel(i);
                    Object colValue = rs.getObject(colName);
                    //判断查询出来的类的类型，如果是java.sql.Date-->java.util.Date
                    if (colValue instanceof java.sql.Date) {
                        java.sql.Date date = (java.sql.Date) colValue;
                        colValue = new java.util.Date(date.getTime());
                    }
                    rowMap.put(colName, colValue);
                }

                bean = clazz.newInstance();

                for (Map.Entry<String, Object> entry : rowMap.entrySet()) {
                    String propertyName = entry.getKey();
                    Object propertyValue = entry.getValue();
                    BeanUtils.setProperty(bean, propertyName, propertyValue);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }
        return bean;
    }
    public static Integer updateForPrimary(String sql, Object...args){

        Connection conn = null;
        PreparedStatement ps = null;
        Integer primaryKey = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            if(args!=null && args.length>0){

                for(int i = 0; i < args.length; i++){
                    //判断当前类型，是不是java.util.Date，转换成java.sql.Date
                    if(args[i] instanceof java.util.Date){
                        java.util.Date date = (java.util.Date)args[i];
                        //转换成java.sql.Date
                        args[i] = new java.sql.Date(date.getTime());
                    }
                    ps.setObject(i+1,args[i]);
                }
            }

            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                primaryKey = rs.getInt(1);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            close(conn, ps,rs);
        }
        return primaryKey;
    }

    //更新操作
    public static boolean update(String sql, Object... args) {

        Connection conn = null;
        PreparedStatement ps = null;
        Integer count = -1;

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);

            if (args != null && args.length > 0) {

                for (int i = 0; i < args.length; i++) {
                    //判断当前类型，是不是java.util.Date，转换成java.sql.Date
                    if (args[i] instanceof java.util.Date) {
                        java.util.Date date = (java.util.Date) args[i];
                        //转换成java.sql.Date
                        args[i] = new java.sql.Date(date.getTime());
                    }
                    ps.setObject(i + 1, args[i]);
                }
            }

            count = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }
        return count > 0 ? true : false;
    }

    //删除操作
    public static boolean delete(String sql, Object... args) {

        Connection conn = null;
        PreparedStatement ps = null;
        Integer count = -1;

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);

            if (args != null && args.length > 0) {

                for (int i = 0; i < args.length; i++) {

                    ps.setObject(i + 1, args[i]);
                }
            }

            count = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }
        return count > 0 ? true : false;
    }

    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (!(rs == null)) {
                rs.close();
                pstmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Integer getCount(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer count = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);


            if (args != null && args.length > 0) {

                for (int i = 0; i < args.length; i++) {

                    ps.setObject(i + 1, args[i]);
                }
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }

        return count;
    }
}
