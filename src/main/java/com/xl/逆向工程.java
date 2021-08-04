package com.xl;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author XLong
 * @create 2021-08-04 20:26
 */
public class 逆向工程 {
    public void generator() throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("E:\\IdeaProjects\\SpringBoot_Code\\myauction\\src\\main\\resources\\generator.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);
        System.out.println("生成成功");
    }

    public static void main(String[] args) throws Exception {
        try {
            逆向工程 demo = new 逆向工程();
            demo.generator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
