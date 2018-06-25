# Commodity商品系统
管理商品和子商品，提供增删改查的功能，端口号*9010*，启动时自动向本地8080端口注册服务。

**注意此项目启动会自动重建相关表结构！**

使用*lombok*简化pojo对象的编写；

使用*jasypt*加密数据库密码；

数据库使用oracle，对应的*ojdbc7.jar*和另外一个子项目生成的*kit.jar*，都放入nexus私服中；

日志打印到项目根目录下的logs文件夹中；

项目结构：
1）基于restful规范的controller层，GET查询、POST新增、PUT修改（PATCH局部修改）、DELETE删除;
2）service层由接口和其实现类构成，使用接口的目的是为了mock测试使用；
3）repository层分为db和http两个目录，db包下通过jpa获取数据、http包下通过okhttpclient获取数据;
4）pojo存放所有对象，dto：数据传输对象，与外部系统交互（接收或发送）使用、po：持久化对象，与数据库交互使用、bo：业务对象，封装复杂业务逻辑、vo：值对象，用于缓存的临时数据结构；
5）为了保证数据传输对象的轻便，po负责与dto的互相转换，或者放到工具类中，kit包存放工具类。
