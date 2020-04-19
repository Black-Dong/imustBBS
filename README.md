## 科大论坛后端


### 用户登录
### 用户注册
### 管理员登录

### 新增分类
### 修改分类



## 资料
https://v3.bootcss.com/





mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate

# 启动redis
docker run -p 6379:6379 -v $PWD/docker_redis5.0/data:/data -d redis:5.0 redis-server --appendonly yes --name imustBBS_redis