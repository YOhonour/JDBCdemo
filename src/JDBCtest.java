/*
本类功能：连接数据库,将数据库中部门、雇员转化为对象集向外提供
对外提供：1.部门的名称String集合
         2.所有雇员数据转化的Employee对象集合
         3.

 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCtest {
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/JDBCdemo?useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT&userSSL=false";
    /*url参数设置以?与地址分隔，参数间以&连接*/
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456zaq";

    private Connection connection;
    private List<String> apart;     //部门的泛型List
    private List<Employee> employees = new ArrayList<>();
    /*
     在构造函数中初始化连接，赋值Connection对象，赋值Statement对象
     */
    JDBCtest(){
        System.out.println("连接中");
        try {
            connection = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
            System.out.println("连接成功");
//            new JDBCtest().creat(connection,stem);//创建表
//            new JDBCtest().insert(connection,stem);//插入数据

        } catch(SQLException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /*
    本方法接收Connection对象和Statement，在连接到的数据库中创建表
     */
    public void creat(Connection connection)throws Exception{
        Statement stem = connection.createStatement();
        String sql_dept = "create table dept \n" +
                "( \n" +
                "deptno int(11) primary key,  #部门号\n" +
                "dname varchar(10)         #部门名\n" +
                "); \n" ;
        String sql_emp = "create table emp \n" +
                "( \n" +
                "empno int(11) primary key,  #员工工号\n" +
                "ename varchar(10),        #员工姓名\n" +
                "job varchar(10),           #员工岗位\n" +
                "mgr varchar(10),          #员工上司\n" +
                "sal varchar(10),           #员工薪水\n" +
                "deptno int(11),           #所在部门号\n" +
                "foreign key(deptno) references dept(deptno) \n" +
                "); ";

        /*
        创建表
         */
        stem.executeUpdate(sql_dept);
        stem.executeUpdate(sql_emp);

    }
    /*
    本方法接收Connection对象，在数据库中已存在的两张表中插入
     */
    public void insert(Connection connection)throws Exception{
        Statement stem = connection.createStatement();
        String insert_1 = "insert into dept values (1,'事业部');";
        String insert_2 = "insert into dept values (2,'销售部');";
        String insert_3 = "insert into dept values (3,'技术部'); ";
        String insert_4 = "insert into emp values (1,'jacky','clerk','tom',2000,1);";
        String insert_5 = "insert into emp values (2,'tom','clerk','',2500,1);";
        String insert_6 = "insert into emp values (7,'biddy','clerk', 'tom',2000,1);";
        String insert_7 = "insert into emp values (3,'jenny','sales','pretty',600,2); ";
        String insert_8 = "insert into emp values (4,'pretty','sales','',800,2);";
        String insert_9 = "insert into emp values (5,'buddy','jishu','canndy',1000,3); ";
        String insert_10 = "insert into emp values (6,'canndy','jishu','',1500,3);";


        stem.executeUpdate(insert_1);
        stem.executeUpdate(insert_2);
        stem.executeUpdate(insert_3);
        stem.executeUpdate(insert_4);
        stem.executeUpdate(insert_5);
        stem.executeUpdate(insert_6);
        stem.executeUpdate(insert_7);
        stem.executeUpdate(insert_8);
        stem.executeUpdate(insert_9);
        stem.executeUpdate(insert_10);

    }
    public List<String> getApart() {
        return apart;
    }
    public List<Employee> getEmployees() {
        return employees;
    }

    /*
     接收Connection对象，查询内部的两张表得到结果集，将结果集转化为部门的名字为元素的String集合（下标为其部门号）
     */
    void find(Connection connection)throws Exception{
        Statement stem = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet_dept = stem.executeQuery("select * from dept");
        apart = toString_apart(resultSet_dept);
        resultSet_dept.close();
        /*
        在初始化下一个结果集时自动关闭上一个结果集
         */
        ResultSet resultSet_emp = stem.executeQuery("select * from emp");
       /*
       遍历获取每一行数据新建雇员对象加入雇员对象集中
        */
        while (resultSet_emp.next()){
            employees.add(new Employee(
                    resultSet_emp.getInt(1),
                    resultSet_emp.getString(2),
                    resultSet_emp.getString(3),
                    resultSet_emp.getString(4),
                    resultSet_emp.getString(5),
                    resultSet_emp.getInt(6)
                    ));
       }
        resultSet_emp.close();
    }

    public Connection getConnection() {
        return connection;
    }


    /*
     接收查询部门表获得部门结果集返回部门对应的List    注：0 下标对应的为空
     */
    private List<String> toString_apart( ResultSet resultSet_dept)throws Exception{
        List<String> list_dept = new ArrayList<>();
        list_dept.add(0,"");
        while (resultSet_dept.next()) {
            list_dept.add(resultSet_dept.getInt(1),resultSet_dept.getString(2));
        }
        return list_dept;
    }
    public static void main(String[] args) throws Exception{
        JDBCtest jdbCtest = new JDBCtest();
        jdbCtest.find(jdbCtest.getConnection());

        Count_emp count_emp = new Count_emp(jdbCtest.getApart());
        //count_emp.show();
        List<Employee> employees = jdbCtest.getEmployees();
        count_emp.add(employees);
        count_emp.initInfo();

        count_emp.question_1();
        count_emp.question_2();
        count_emp.question_3(employees);
        count_emp.question_4();
        /*
        关闭连接
         */
        jdbCtest.connection.close();
    }
}
