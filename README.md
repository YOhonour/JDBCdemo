# JDBCdemo<br>
 #### 实现思路为：<br>
   获取连接后将数据库中部门信息转化为字符串集合，将雇员转化为对象集合。<br>
   再将部门示例化，将雇员加入部门单独的雇员对象集合中<br>
   由部门对象的方法将所需数据总结出，由计算对象调用完成对题目的实现。
#### （5）创建表emp时，最后一行foreign key(deptno) references dept(deptno) 代表什么意思？<br>
为约束在emp表中插入行时，每一行中deptno数据，必须在于dept表中deptno列内包含
