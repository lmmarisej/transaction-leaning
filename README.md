## 简介

本地事务、分布式事务，Java、Spring、SpringCloud 测试代码。

## 环境

#### MySQL8

```mysql
create database `transaction_test` default character set utf8mb4 collate utf8mb4_unicode_ci;

use `transaction_test`;

create table t_user
(
    id       int(11)     not null auto_increment,
    username varchar(20) not null,
    sex      char(1)  default null,
    age      int(3)   default null,
    amount   datetime default null,
    primary key (id)
) ENGINE = InnoDB AUTO_INCREMENT = 1, DEFAULT CHARSET = utf8mb4;

-- 查看全局和当前session的事务隔离级别
select @@global.transaction_isolation, @@transaction_isolation;

-- 设置隔离级别
SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
# SET SESSION TRANSACTION ISOLATION LEVEL READ COMMITTED;
-- level: READ UNCOMMITTED, READ COMMITTED, REPEATABLE READ,  SERIALIZABLE

-- session 1:
START TRANSACTION;

UPDATE t_user SET amount = amount + 100 WHERE username = 'BatMan';
UPDATE t_user SET amount = amount - 100 WHERE username = 'SuperMan';

COMMIT;
-- ROLLBACK;

-- session 2:
START TRANSACTION;

SELECT *FROM t_user COMMIT;
```