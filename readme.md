数据库文件放在根目录（随便乱弄的几个数据，你们如果有好的数据那就用自己的）
## 运行前要记得修改配置文件中的数据库密码！！

#### 访问商品详细信息的方式
> localhost:9999/idlecherry/CommodityDetail?id=+数字

比如访问ID为1的商品的方法是
> http://localhost:9999/idlecherry/CommodityDetail?id=1

#### 访问用户信息中心的方式
> idlecherry/user/usercenter/+数字

比如访问ID为4的用户的方式
>  http://localhost:9999/idlecherry/user/usercenter/4

但是必须是已登录的4号用户才能登录，否则会重定向到error页面

[登录页面](http://localhost:9999/idlecherry/login)