# 华农学习资源共享平台【ADshare】
1.登录功能：使用微信或者易班API授权登录，不接受注册
登录后，读取用户基本信息;

2.论坛功能：创建悬赏帖

2020-8-8 更新:

    更新了utils类,编写了微信小程序授权登录相关工具类(HttpClient、以及获取微信账号信息API集成)，并且编写了有关用户的VO层类
    更新了微信小程序前端,使得其可以存储skey以及session,但并未编写好全局请求头
    更新了数据库表 v0.9.6


2020-8-15 更新:

    添加了有关用户增删查改的mapper
    添加了有关User的增查改的service层,使得新用户可被写入DB,以及从数据库中查询出来
    更新了微信小程序前端,编写好全局请求头并携带sessionID
    更新了登陆成功后，返回前端数据集
    删除了全局ReslutVO的冗余属性"OK"
    更新了数据库表 v0.9.7

2020-8-18 更新：

    添加包含易班模拟登录服务的package--simulateLogin
    添加和易班登录相关的controller以及VO，实现易班的模拟登录
    
2020-8-19 更新：

    添加对于帖子的增删改查mapper
    添加对于标签的增删改查mapper
    添加了有关帖子以及标签的业务层,可通过标签内容查询相关帖子
    添加了社区的controller层部分功能,点击"新鲜"按最新时间顺序展示话题简略信息

[API文档地址](https://www.showdoc.com.cn/sharePlatform?page_id=5060131993333722)

易班轻应用开发-AD镁铝硅磷小组
