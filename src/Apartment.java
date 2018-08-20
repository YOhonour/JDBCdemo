import java.util.ArrayList;
import java.util.List;
/*
对部门实例化操作，成员变量包括：Employee集合、部门号、部门名称、工资（最高、最低、平均）,最高/低的Employee
对外提供： 1.所有成员变量
 */
public class Apartment {

    private List<Employee> numbers = new ArrayList<>();
    private int aprtNum;
    private String aprt_name;
    private Employee manOfHighestSal = null;
    private Employee manOfLowestSal = null;
    private int sal_Top;
    private int sal_Low;
    private double sal_Ave;
/*
构造函数接收部门的名称、部门号，初始化对象
 */
    Apartment(String aprt_name,int aprtNum){
        this.aprt_name = aprt_name;
        this.aprtNum = aprtNum;
    }

    public Employee getManOfHighestSal() {
        return manOfHighestSal;
    }

    public Employee getManOfLowestSal() {
        return manOfLowestSal;
    }

    public List<Employee> getNumbers() {
        return numbers;
    }

    public int getSal_Low() {
        return sal_Low;
    }

    public int getSal_Top() {
        return sal_Top;
    }

    public double getSal_Ave() {
        return sal_Ave;
    }

    public int getAprtNum() {
        return aprtNum;
    }

    public String getAprt_name() {
        return aprt_name;
    }
/*
传入雇员对象集合
 */
    public void add(Employee man){
        numbers.add(man);
    }
    /*
    对本类内部的雇员集合遍历，求出最大/小、平均薪水等所有成员变量
     */
    public void doCount(){
        int top = Integer.parseInt(numbers.get(0).getSal());
        int low = Integer.parseInt(numbers.get(0).getSal());
        manOfHighestSal = numbers.get(0);
        manOfLowestSal = manOfHighestSal;
        double all = 0;
        /*
        遍历
         */
        for (int i = 0; i<numbers.size();i++){
            int n = Integer.parseInt(numbers.get(i).getSal());
            if(top<n) {
                manOfHighestSal = numbers.get(i);
                top = n;
            }
            if(low>n) {
                manOfLowestSal = numbers.get(i);
                low = n;
            }
            all = all + (double)Integer.parseInt(numbers.get(i).getSal());
        }
        sal_Low = low;
        sal_Top = top;
        sal_Ave = all / (double)numbers.size();
    }

}
