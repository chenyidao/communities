## neu社区

## 资料
[Spring官方](https://spring.io/guides)  
[Spring框架文档](https://docs.spring.io/spring/docs/5.0.3.RELEASE/spring-framework-reference/)      
[spring-boot文档](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/)  
[es](https://elasticsearch.cn/explore)  
[Github deploy key](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)  
[Github OAuth](https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/)  
[Bootstrap](https://v3.bootcss.com/getting-started/#download)  
[maven资料库](https://mvnrepository.com)  
[okHttp](https://square.github.io/okhttp/)  
[thymeleaf基本用法](https://www.cnblogs.com/topwill/p/7434955.html)  
[UML图](https://mp.weixin.qq.com/s/KR2HCcCoIc-gSDLZ69azYw)  
[Mybatis整合Spring/Springboot](http://mybatis.org/spring/index.html)  
[H2](http://www.h2database.com/html/quickstart.html)  
[Thymeleaf使用文档](https://www.thymeleaf.org/documentation.html)  
[html转译与反转译](https://www.sojson.com/rehtml)  
[ErrorHandler错误处理机制](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-error-handling)  
[JQuery API文档](https://api.jquery.com/)  

## 插件
[Flyway](https://flywaydb.org/getstarted/firststeps/maven)  
[Flyway简单实例](http://ju.outofmemory.cn/entry/339528)  
[Lombok maven](https://projectlombok.org/setup/maven)
[Lombok](https://projectlombok.org/features/all)  
[IDEA热部署Automatic Restart](https://www.cnblogs.com/ming-blogs/p/10289075.html)(清除浏览器缓存：ctrl+shift+delete)  
[Mybatis Generator](http://mybatis.org/generator/configreference/xmlconfig.html)  
[Mybatis Generator分页插件](http://mybatis.org/generator/reference/plugins.html)  
[common lang](https://mvnrepository.com/artifact/org.apache.commons/commons-lang3/3.9)  
[Moment.js—JavaScript 日期处理类库](http://momentjs.cn/)  
[Markdown插件](http://editor.md.ipandao.com/)  
[MarkdownToHtml](https://github.com/pandao/editor.md)  

## 所用工具
[okHttp](https://square.github.io/okhttp/)  
[json解析工具](http://jsoneditoronline.org/)  
## 脚本
```sql
CREATE TABLE USER(
    ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    ACCOUNT_ID VARCHAR(100),
    NAME VARCHAR(50),
    TOKEN CHAR,
    GMT_CREATE BIGINT,
    GMT_MODIFIED BIGINT
);
```
```sql
select *
from table_name 
[LIMIT N],[ OFFSET M]

-- range        page(页码)  offset(偏移量)           size(每页大小)
-- 0-5(0,5)     1           N = size * (page - 1)    5
-- 6-10(5,5)    2            
-- 11-15(10,5)   
    M = 5
    N = size * (page - 1)
    pageCount(页数) = total % offset = 0 ? total/offset : total/offset+1            
```

``` bash
mvn flyway:migrate   //将更新的脚本同步到数据库
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate   //do的创建
```

```
windows用\  mac用/  不然运行成功了还是没有文件生成？
```

```aidl
generator分页插件：<plugin type="org.mybatis.generator.plugins.RowBoundsPlugin"></plugin>
```

```puml
$.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": id,
            "content": content,
            "type": 1
        }),
        success: function (response) {
            console.log(response)
        },
        dataType: "json "
    });
```