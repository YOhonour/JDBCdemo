/*
本类功能：接收传入的数据库信息，建立Employee对象
对外提供： 1.员工姓名
          2.员工薪水
          3.员工所在部门号
 */
public class Employee {
    private int empno;           //员工工号
    private String ename;        //员工姓名
    private String job;          //员工岗位
    private String mgr;          //员工上司
    private String sal;          //员工薪水
    private int deptno;         //所在部门号

    Employee(int empno,String ename,String job,String mgr,String sal,int deptno){
        this.deptno = deptno;
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.sal = sal;
    }
    Employee(int empno,String ename,String job,String sal,int deptno){
        this.deptno = deptno;
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.sal = sal;
    }
/*
获取工资
 */
    public String getSal() {
        return sal;
    }
/*
获取部门号
 */
    public int getDeptno() {
        return deptno;
    }
/*
获取姓名
 */
    public String getEname() {
        return ename;
    }

}
