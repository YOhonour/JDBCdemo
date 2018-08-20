/*
本类功能:
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Count_emp {
    private List<Apartment> Apartments = new ArrayList<>();
    private List<String> apart;//用于接收部门数据数组的泛型数组，部门号和部门名称对应
    Count_emp(List<String> apart){
        this.apart = apart;
        for(int i = 1;i < apart.size();i ++){
            Apartments.add(new Apartment(apart.get(i),i));
        }
    }

//    public void show(){
//        for(int i = 0;i < Apartments.size();i ++){
//            System.out.println("  "+ Apartments.get(i).getAprt_name() + Apartments.get(i).getAprtNum());
//        }
//    }
    public void add(List<Employee> employees)//接收Employee对象判断List中是否存在Employee所属的部门，存在即加入该部门反之即新建部门
    {
        for(int i = 0;i < employees.size();i ++){
            /*
            获取雇员对象的所属部门号，加入以次部门号为下标的部门对象集合中（部门对象集合下标为零处为null）
            注：部门集合中下标为该部门的部门号
             */
            Apartments.get(employees.get(i).getDeptno()-1).add(employees.get(i));
        }
    }

    public void initInfo(){

        for (int i = 0;i<Apartments.size();i ++){
            Apartments.get(i).doCount();
        }
    }

    public List<Apartment> getApartments() {
        return Apartments;
    }

    public void question_1() {
        System.out.println("\n（1）列出各部门的最高工资，最低工资");
        for (int i = 0; i < Apartments.size(); i++) {
            System.out.println(Apartments.get(i).getAprt_name()+" 最高工资：" +Apartments.get(i).getSal_Top()+" 最低工资："+Apartments.get(i).getSal_Low());
        }
    }
    public void question_2(){
        System.out.println("\n（2）按照各个部门的平均工资由高到低对，列出部门名，平均工资");
        Collections.sort(Apartments, new Comparator<Apartment>() {
            @Override
            public int compare(Apartment o1, Apartment o2) {
                if(o1.getSal_Ave() > o2.getSal_Ave())
                    return 1;
                if(o1.getSal_Ave() == o2.getSal_Ave())
                    return 0;
                return -1;
            }
        });
        for(int i = 0;i < Apartments.size();i ++){
            System.out.println("部门名："+Apartments.get(i).getAprt_name()+" 平均工资："+Apartments.get(i).getSal_Ave());
        }
    }
    public void question_3(List<Employee> employees){
        System.out.println("\n（3）根据工资由低而高列出每个员工的姓名，部门名，工资");
        /*
        排序,由低到高
         */
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (Integer.valueOf(o1.getSal()) > Integer.valueOf(o2.getSal()))
                    return 1;
                 if(Integer.valueOf(o1.getSal()) == Integer.valueOf(o2.getSal()))
                    return 0;
                 return -1;
            }
        });
        for (int i = 0;i < employees.size();i++){
            System.out.println("姓名："+employees.get(i).getEname()+" 部门名："+apart.get(employees.get(i).getDeptno())+" 工资："+employees.get(i).getSal());
        }
    }
    public void question_4(){
        System.out.println("\n（4）对于工资高于本部门平均水平的员工，列出部门号，姓名，工资，按部门号排序 ");
        Collections.sort(Apartments, new Comparator<Apartment>() {
            @Override
            public int compare(Apartment o1, Apartment o2) {
                if(o1.getAprtNum() > o2.getAprtNum())
                    return 1;
                if(o1.getAprtNum() == o2.getAprtNum())
                    return 0;
                return -1;
            }
        });
        for(int i = 0;i < Apartments.size();i ++){
            for(int n = 0;n < Apartments.get(i).getNumbers().size();n++){
                if(Integer.valueOf(Apartments.get(i).getNumbers().get(n).getSal()) > Apartments.get(i).getSal_Ave()){
                    System.out.println("部门号 ： "+Apartments.get(i).getNumbers().get(n).getDeptno()+" 姓名 ："+Apartments.get(i).getNumbers().get(n).getEname()+" 工资："+Apartments.get(i).getNumbers().get(n).getSal());
                }
            }
        }

    }
}
