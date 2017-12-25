# mySsm
spring springmvc mybatis整合demo，以及常用技术与spring的整合。
</br>
1、项目使用mybatis逆向工程，生成bean、mapper和xml，亦可自定义SQL，实现快速开发；逆向工程的使用参考印象笔记。</br>
2、项目使用restful风格定义接口。</br>
3、restful api返回json数据与前台进行交互，满足前后端分离的需求，无缝对接移动端开发。</br>
4、使用HttpMessageConverter进行数据类型转换和格式化，使用JSR-303、hibernate-validator进行数据校验。并将数据绑定错误信息list返回前端。</br>
5、使用@ControllerAdvice 进行controller层统一处理异常，避免将异常直接抛给用户，也减少了捕获异常的冗余编码。</br>
6、使用spring mvc 的CommonsMultipartResolver进行上传文件，比普通的流操作更加简单，迅速。</br>
7、使用动态盐值加密防登录。</br>
8、使用拦截器拦截未登录用户，使用自定义注解跳过不需要拦截登录的接口。</br>
n、项目有两种redis配置方式：单机版redis配置redis-single.xml，使用StringRedisTemplate对象操作；集群模式redis配置redis-cluster.xml，使用JedisCluster对象操作。切换两种配置模式，只需在spring-other.xml中开启或注释引用的配置文件即可，推荐使用集群方式，JedisCluster操作更简单。</br>

</br>
</br>
持续更新中
