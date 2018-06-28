# 微服务集群的断路器监控
端口号*8090*，启动时自动向本地8080端口注册服务。
用于监控集群中设置*actuator*的服务，需要保证被监控的应用上*http://应用ip:端口号/hystrix.stream*是有响应（不能是404）；
如果要监控单台应用的断路器情况，在本服务*http://localhost:8090/hystrix/*页面上输入以上地址（http://应用ip:端口号/hystrix.stream），点击monitor stream按钮就会以图形化的界面展示应用的断路器情况；
如果要监控服务集群的情况（监控的集群在yml文件中配置），在本服务页面上输入*http://localhost:8090/turbine.stream*，然后点击monitor stream。
