use rljl;
set hive.exec.dynamic.partition.mode=nonstrict;
set hive.exec.dynamic.partition=true;

-- 库相关
create database if not exists rljl comment 'stu_comment';
show databases ;
show databases like 'rljl';
desc database rljl;
desc database extended rljl;
show create database rljl;
-- drop database if exists rljl cascade; -- 可不敢轻易删除

-- 表相关
-- create table demo(name string comment 'name fields alias',age int comment 'age fields alias') comment 'demo table alias' partitioned by (dt string) clustered by (name, age) sorted by (age asc ) into 100 buckets row format delimited fields terminated by '\t' collection items terminated by '_' map keys terminated by ':' stored as parquet tblproperties ('key1'='value1', 'key2'='value2');
create table if not exists stu(name string, age bigint) comment 'stu_comment' partitioned by (dt string) stored as parquet;
show tables ;
show tables like 'stu';
desc stu;
desc extended stu;
show create table stu;
drop table if exists stu;
desc formatted stu;
show tblproperties stu;

-- 视图相关
-- 创建视图 视图是虚拟的表 在HDFS物理上并不存在
create view view_stu as select * from stu;
-- 显示所有视图 注意在show tables时也会显示出来所有的视图
show views ;

-- 清空表
truncate table if exists stu;
-- 修改表名 同库是重命名 不同库是剪切
alter table stu rename to stu_rename;
alter table stu_rename rename to stu;
-- 复制表结构(如果是分区表同样会复制为分区表)
create table if not exists copy_stu_structure like stu;
-- 复制表(连同数据，这种方式即使stu是分区表，复制的表也不是分区表，就是普通表，这要注意)
create table if not exists copy_stu_with_data as select * from stu;
insert into table copy_stu_with_data partition (dt) select * from stu;
-- 官方从HDFS中load数据的方式（静态分区） 这里报红不用管 能正确运行
load data inpath '/user/hive/demo.txt' into table stu partition (dt = '2022');
-- 从本地文件系统加载数据（静态分区） 这里报红不用管
load data local inpath '/user/hive/demo.txt' into table stu partition (dt = '2022');
-- 官方从HDFS中load数据的方式（动态分区 需要设置true和nostrict） 这里报红不用管 能正确运行
load data inpath '/user/hive/demo.txt' into table stu partition (dt);
-- 只覆盖涉及到的分区的数据
load data inpath '/user/hive/demo.txt' overwrite into table stu partition (dt);
-- 最原始的插入数据的方式 一般推荐使用 能正确运行
insert into table stu partition (dt = '2022') values ('alan', 10);
-- select结果插入 推荐使用
insert into table stu partition (dt = '2022') select null, 20;
-- select结果覆盖插入 一般插入测试数据使用 注意overwrite是针对分区的overwrite 对于不涉及的分区数据不overwrite
insert overwrite table stu partition (dt = '2022') select 'adam', 23;
-- 查看表分区
show partitions stu;
-- 增加修改删除表分区
alter table stu add partition (dt = '2023') partition (dt = '2024');
alter table stu drop partition (dt = '2023'), partition (dt = '2024');
-- 查看分区表详细信息
desc formatted stu ;
-- join连接测试 数据准备
create table if not exists t1(a string, b string);
create table if not exists t2(b string, c string);
insert into t1 values ('a', '1'), ('b', '2'), ('c', '3');
insert into t2 values ('2', "b"), ('3', "c"), ('4', 'd');
-- inner join
select * from t1 inner join t2 on t1.b = t2.b;
-- left join
select * from t1 leftjoin t2 on t1.b = t2.b;
-- full join
select * from t1 full join t2 on t1.b = t2.b;
-- semi join
select * from t1 left semi join t2 on t1.b = t2.b;
-- 测试having 数据准备
create table if not exists t3(a bigint, b bigint);
insert into t3 values(1, 2), (1, 3), (1, 4), (2, 5), (2, 6), (2, 7), (3, 8), (3, 9);
-- 和group by结合使用 用于分组后在过滤
select a, avg(b) as avg from t3 group by a having avg > 7;

-- 导出数据
insert overwrite directory '/user/data'
    row format delimited fields terminated by '\t'
    stored as parquet
select * from stu;

-- 获取集合类型元素的方法
select `array`(1, 2, 3)[0];
select struct('alan', 23).col1;
select named_struct('name', 'alan', 'age', 23).name;
select `map`('name', 'alan', 'age', 23)['name'];

-- 函数
-- 显示所有内置函数
show functions ;
-- 显示内置简单用法
desc function upper;
-- 显示内置函数详细用法
desc function extended upper;
select nvl(null, 10);
select concat('alan', 'jack');
select concat_ws('-', 'alan', 'jack');
select * from stu;
-- 测试explode函数 数据准备
create table if not exists fun(arr array<struct<name: string, age: int>>, mapCol map<string, int>, arr1 array<string>);
insert into fun values(
                          `array`(named_struct('name', 'alan', 'age', 23), named_struct('name', 'jack', 'age', 24), named_struct('name', 'jack', 'age', 30)),
                          `map`('address', 10, 'name1', 22, 'name2', 23),
                          `array`('str1', 'str2', 'str3')
                      );
select * from fun;
select arr[0], size(arr), mapcol['address'], size(mapCol), arr[0].name from fun;
select mapCol, mapC from fun lateral view explode(arr) funTableAlias as mapC;
select collect_list(mapCol) from fun lateral view explode(arr) funTableAlias as mapC;
select collect_set(mapCol) from fun lateral view explode(arr) funTableAlias as mapC;
select split('alan-jack', '-');
select explode(split('alan-jack', '-'));
select explode(mapCol) from fun;
select explode(arr1) from fun;
insert into t3 values(1, 2), (1, 3), (1, 4), (2, 5), (2, 6), (2, 7), (3, 8), (3, 9);
-- 测试窗口函数
select
    sum(b) over(partition by a order by b rows between current row and unbounded following),
    rank() over (partition by a order by b),
    dense_rank() over (partition by a order by b),
    row_number() over (partition by a order by b),
    count() over(partition by a),
    max(b) over(partition by a order by b),
    lag(b, 1) over(partition by a order by b),
    lead(b, 1) over(partition by a order by b)
from t3;

-- get_json_object
create table if not exists stu(name string, age bigint) partitioned by (dt string) stored as parquet ;
insert into stu select '[{\"name\":\"alan\",\"age\":23},{\"name\":\"adam\",\"age\":25},{\"name\":\"jack\",\"age\":30}]', 20, 2022;
select get_json_object(name, '$[0].name') from stu;
-- json_tuple
insert into stu select '{\"name\":\"jack\",\"age\":30}', 20, 2022;
select json_tuple(name, 'name', 'age') from stu;

show tables ;

select * from fun;

select get_json_object(cast(arr as string), '$[1].name') from fun;
select cast(arr as string) from fun;
select lcase(name), ucase(name) from stu;
select get_json_object(name, '$[0].name') from stu;