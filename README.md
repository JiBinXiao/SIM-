
# SIM机房课表项目
声明：此项目与学院实验室无关，仅由个人练习和学习使用。

一个关于北京信息科技大学信管学院机房课表查询的练习项目，便于师生随地随时查询机房课表。
使用微信小程序作为前端开发，后台使用Java，数据库使用SQLSERVER 2008。可在手机微信APP上运行。
2017年9月13日。


| 作者   | qq  | git | 博客 |
| :---: | :-: | :-: | :-: |
| JiBinXiao | 941623975 | https://github.com/JiBinXiao |  |

### 招募开源项目参与者：
### 本项目想做成一个便于师生学习交流查询的项目，小程序已上线，可在微信中搜索 SIM机房课表 直接使用。


![](https://github.com/JiBinXiao/SIMSchedule/blob/master/Printscreen/二维码.jpg)

####主要功能如下：
1. 课程显示界面仿照超级课程表。
2. 按实验室和周数查询：显示该实验室本周所有课程。
3. 点击课程项可显示该课程详细信息。
3. 缓存数据。
4. 查询空机房。
5. 课程点名功能。

#### 人员要求：
1. 会简单sql语句，一定java，HTML+CSS+JS基础。
2. 最好有java web开发经验。
3. 不满足以上两条的话，没关系，只要你爱学习也可以加入。
4. 有分享精神，好说话，不为利益。

如果你想加入，加QQ：941623975
### Page文件列表说明

1. 按班级查询页面。

![](https://github.com/JiBinXiao/SIMSchedule/blob/master/Printscreen/classes.gif)


2. 按教师查询页面。

![](https://github.com/JiBinXiao/SIMSchedule/blob/master/Printscreen/teacher.gif)


3. 我的页面。

![](https://github.com/JiBinXiao/SIMSchedule/blob/master/Printscreen/mine.png)




--------------------
# SIM机房课表开发笔记 - 目录

### 开发环境
1. 微信小程序开发平台。
2. Tomcat 7.0 以上。
3. SQLSERVER 2008 R2。
4. JDK 1.6以上。
5. MyEclipse 2014。


### 1.1介绍
1. SIMClassSchedule是微信小程序包，需要在微信小程序开发平台上运行。
2. java后台中的CourseSchedule是用来从Excel读取数据储存到数据库的普通java应用文件。
3. java后台中的CourseScheduleAPI是用来作为后台数据处理的Web Project文件。
4. 数据库中含有建表语句
5. jar包中poi.jar是用来读取Excel的包需要导入到CourseSchedule文件中，sqljdbc4.jar是支持jdbc的包需在CourseSchedule和CourseScheduleAPI中都导入。
6. 上机表存储着每周课表信息的Excel文件。