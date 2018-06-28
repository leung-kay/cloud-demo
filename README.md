# 基础Cloud项目
项目中使用了Euraka、Ribbon、Hystrix等最基本的微服务组件（也是@SpringCloudApplication注解默认开启的三种组件）；
项目包括两个基础服务和三个业务应用：
center是基于Euraka的注册中心，所有应用启动时会向此服务注册自己，并获取已注册的服务列表；
watcher是基于hystrix-dashboard的断路器监控（将来还会有更广泛的一个监控中心应用），这个应用也基于注册中心；
commodity相当于服务提供方A，模拟了一个商品管理的业务，维护着c_goods和c_sub_goods表；
inventory相当于服务提供方B，模拟了一个库存管理的业务，维护着i_stock表；
upfront是服务调用方，提供了一个商品库存查询的接口，从commodity和inventory服务中获取数据并整合输出，使用了负责均衡和短路保护。

除了技术结构，项目中也根据经验搭建了一套业务开发规范的示例，包括：
lombok（简化pojo类开发）；
java8 stream流式编程（提升集合操作效率、减少代码）；
OkHttp（相当快的网络请求组件）；
jasypt配置加密；
jpa表关联（OneToMany）；
nexus私服连接（搭建会在另外的文档中介绍）；
封装了简单的响应报文结构Outcoming；
日志打印；
pojo对象体系；
项目包体系；
restful规范。

计划完善的有：
基于AOP的日志打印；
基于mock的单元测试；
基于redis的缓存方案；
mysql数据库的示例应用。

