# 1.找到销售笔记本电脑但不销售个人电脑的制造商，集合差
#SELECT DISTINCT maker FROM product WHERE `type`='pc' 
#Except (SELECT DISTINCT maker FROM product WHERE `type`='laptop');
# 2.找到销售笔记本电脑但不销售个人电脑的制造商，in嵌套查询
SELECT DISTINCT maker FROM product WHERE `type`='pc' 
AND maker NOT IN (SELECT DISTINCT maker FROM product WHERE `type`='laptop');
# 3.找到销售笔记本电脑但不销售个人电脑的制造商，exists嵌套查询
SELECT DISTINCT p1.maker FROM product p1 WHERE p1.`type`='pc' 
AND NOT EXISTS (SELECT * FROM product p2 WHERE p2.`maker`=p1.`maker` AND p2.`type`='laptop');
# 4.查找比3002型打印机便宜的所有打印机的型号，内连接
SELECT p2.`model` FROM printer p1 INNER JOIN printer p2 ON p1.`model`=3002 AND p2.`price` < p1.`price`;
# 5.查找比3002型打印机便宜的所有打印机的型号，比较运算符嵌套查询
SELECT model FROM printer WHERE price < (SELECT price FROM printer WHERE model=3002);
# 6.查找比3002型打印机便宜的所有打印机的型号，exists嵌套查询
SELECT p1.model FROM printer p1 WHERE 
EXISTS (SELECT * FROM printer p2 WHERE p2.model=3002 AND p2.`price`>p1.`price`);
# 7.找到可用速度最高的PC型号，外连接
SELECT a.model FROM pc AS a LEFT JOIN pc AS b ON a.speed<b.speed WHERE b.model IS NULL;
# 8.找到可用速度最高的PC型号，in嵌套查询
SELECT model FROM pc WHERE speed IN (SELECT MAX(speed) FROM pc);
# 9.找到可用速度最高的PC型号，=嵌套查询
SELECT model FROM pc WHERE speed =(SELECT MAX(speed) FROM pc);
# 10.找到可用速度最高的PC型号，>=嵌套查询
SELECT model FROM pc WHERE speed >=ALL (SELECT speed FROM pc);
# 11.找到可用速度最高的PC型号，exists嵌套查询
SELECT model FROM pc WHERE NOT EXISTS( SELECT * FROM pc AS a WHERE a.speed>pc.speed);
# 12.找到至少有三种不同速度的PC制造商，内连接
SELECT p2.`maker` FROM pc p1 INNER JOIN product p2 ON p1.model=p2.`model` 
GROUP BY p2.`maker` HAVING COUNT(DISTINCT p1.`speed`) >= 3;
# 13.找到至少有三种不同速度的PC制造商，分组
SELECT p2.`maker` FROM pc p1 INNER JOIN product p2 ON p1.model=p2.`model` 
GROUP BY p2.`maker` HAVING COUNT(DISTINCT p1.`speed`) >= 3;
# 14.找到至少有三种不同速度的PC制造商，派生( 语法：from (子查询))
#With pp as (select product.maker, pc.speed from product join pc on product.model=pc.model)
#Select distinct a.maker from pp as a where exists (Select * from pp as b where exists
#(Select * from pp as c where a.maker=b.maker and a.maker=c.maker and a.speed!=b.speed and a.speed!=c.speed and b.speed!=c.speed));
# 15.将制造商A生产的所有PC的价格降低10%，含=的更新条件
UPDATE pc SET price=price*0.9 WHERE pc.model=(SELECT model FROM product WHERE maker='A' AND product.model=pc.model);
# 16.将制造商A生产的所有PC的价格降低10%，含in的更新条件
UPDATE pc SET price=price*0.9 WHERE pc.model IN (SELECT model FROM product WHERE maker='A');
# 17.将制造商A生产的所有PC的价格降低10%，含exists的更新条件
UPDATE pc SET price=price*0.9 WHERE EXISTS (SELECT model FROM product WHERE maker='A' AND product.model=pc.model);