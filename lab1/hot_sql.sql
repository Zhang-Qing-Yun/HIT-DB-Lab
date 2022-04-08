# 创建数据库
CREATE DATABASE IF NOT EXISTS HIT_DB;

# 执行外部SQL脚本文件，需要在命令行中执行
# source sql脚本文件路径; 

# 查看所有数据库
SHOW DATABASES;

# 切换数据库
USE HIT_DB;

# 查看数据库中的表
SHOW TABLES;

# 查看表结构
DESC product;

# 查看表的索引
SHOW INDEX FROM product;