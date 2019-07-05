[![star](https://gitee.com/kongling_admin/charging_pile_cloud/badge/star.svg?theme=dark)](https://gitee.com/kongling_admin/charging_pile_cloud/stargazers)

#### 介绍
 1.项目为 充电桩 小程序项目，共享充电桩
 
 2.项目地址：
    https://github.com/konglingyinxia/charging_pile_cloud
    
    
  接口文档地址：
    https://www.showdoc.cc/360525838410865
    ![Image text](./img/1559196683(1).jpg)
    
    访问密码：123456

  
 3.项目角色级别 ： 
 
     超级管理员 --区域代理 -- 普通用户

### 软件架构
 1、使用技术：
    
    spring boot :2.1.4.RELEASE
    springcloud :Greenwich.SR1
    mybatis-plus:3.0.7.1
    redis:5.0.5  开发 （ 3.0.503）
    mysql : 5.5
    jdk: 1.8
 
 2、项目目录结构：
 
    common-server:各工具包，公共配置项
    
    doc： 存放项目文档   
    
    db： 数据库文件：
    
              1.data.sql    ：包含基础数据文件
              2.ini_data.sql：上线项目初始化数据库脚本sql
              3.com_config_area.sql: 为三级联动区域地址
                小程序中不支持自定义 添加 经纬度，已废除，
                项目中使用全部改为一级
              
    img:说明文档引入图片
    
    log:日志目录
    
    suda-common-constant:公共常量
    
    suda-common-entity:项目实体类
    
    suda-platform-web:项目控制层 业务层 数据层 
    
  3、项目配置文件说明：
  
    1、服务端口：server:port   redis   数据库 datasource
    
   ![Image text](./img/1562032682(1).jpg)
   
   
    2、微信 小程序 配置 wx:pay 及自定义配置文件:my-configuration:
    
   ![Image text](./img/1562032879(1).jpg)
   
     自定义配置文件说明：
         uploadPath：为服务端存储图片路径
         imagePath：添加图片访问路径
         staticPageUrl：为服务端配置静态资源目录
   ---
    3、自定义配置类 方法为：suda-platform-web 服务
       com.suda.platform.common.interceptors.InterceptorConfig.addResourceHandlers
        
   ![Image text](./img/1562033336(1).jpg)
    
    

#### 安装教程

    1、搭建服务器环境 
        1：到 `https://oneinstack.com/` 网站自定义安装包 （数据库，redis ,jdk ,nginx）
        2:服务器上执行从上面网站复制的 命令：
        
   `wget -c http://mirrors.linuxeye.com/oneinstack-full.tar.gz && tar xzf oneinstack-full.tar.gz && ./oneinstack/install.sh --nginx_option 1 --jdk_option 2 --db_option 4 --dbinstallmethod 1 --dbrootpwd oneinstack --redis  --reboot`
        
    2、创建数据库，
    3、服务器上创建项目目录 文件：
        1：静态资源目录：/home/project/staticFile
        2：配置文件目录：/home/project/config
        3: 日志目录：/home/project/log
        4：启动脚本：
            把doc 文件下的shell 脚本 放入 /home/project/ 目录下
            修改脚本名字为：charge.sh
            修改启动脚本为可执行：chmod 777  charge.sh
            修改脚本文件里启动项目名字： 为 change-1.0.jar
     
   ![Image text](./img/1562034264(1).jpg)
   
    4、项目打包成 jar 包 修改名字 为charge-1.0.jar  上传到服务器 /home/project/ 目录下 
         启动项目 ： ./charge.sh  restart 
         查看实时日志： tail -f ./log/catalina.out 
           
    5、上传静态资源 前端文件 到服务器  /home/project/staticFile/ 目录下
        1、该目录下如果创建后台管理页面目录  admin(或其他名字)  则把静态页面放到 admin 目录下
            访问路径为：http://ip:端口/admin/index.html
        2、如果index.html 在  /home/project/staticFile/ 目录下
            则项目访问路径为：http://ip:端口/index.html
        3、可在 /home/project/staticFile/ 下创建多个项目的静态资源文件目录，
            访问路径为：http://ip:端口/静态资源文件目录/index.html
    6、使用 nginx 转发项目 ，在nginx 里配置 https  访问

#### 使用说明

    1、接入小程序，接入充电桩 UART1 通信 可正常使用
    
        



#### 参与贡献


#### 备注：
    1、后台超级管理员 admin 密码初始为：123456  
      前端加密 加密方式为：账号+*+输入密码 MD5加密 例：（MD5(admin*123456)）
    
    

