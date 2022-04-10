import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @description： 代码生成器
 * @author: 張青云
 * @create: 2021-05-02 17:05
 **/
public class CodeGenerator {
    public static void main(String[] args) {
        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        //  代码最终输出到哪
        gc.setOutputDir("E:\\Mystudy\\大三下课内学习\\数据库系统\\实验\\lab\\HIT-DB-Lab\\lab3\\src\\main\\java");

        gc.setAuthor("張青云");  // 作者
        gc.setOpen(false);  // 生成后是否打开资源管理器
        gc.setFileOverride(false);  // 重新生成时文件是否覆盖
        gc.setServiceName("%sService");	 // 去掉Service接口的首字母I
        gc.setIdType(IdType.AUTO);  // 主键策略
        gc.setDateType(DateType.ONLY_DATE);  // 定义生成的实体类中日期类型，此处为Date
        gc.setSwagger2(false);  // 开启Swagger2模式

        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/hit_db?serverTimezone=GMT%2B8&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("A1146985270ZZQ");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、生成的代码所在的包的配置
        PackageConfig pc = new PackageConfig();
        //  com.qingYun
        pc.setParent("com.qingyun");  // 包名
        pc.setModuleName("db"); // 包下的模块名
        //  com.qingYun.community.post.controller
        pc.setController("controller");
        pc.setEntity("bean");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、生成代码的策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("class");  // 根据哪个表来生成代码
        strategy.setNaming(NamingStrategy.underline_to_camel);  //数据库表映射到实体的命名策略
        strategy.setTablePrefix(pc.getModuleName() + "_");  //生成实体时去掉表前缀

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

        mpg.setStrategy(strategy);


        // 6、执行
        mpg.execute();
    }
}
