package com.qingyun.db.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @description： 人名生成器
 * @author: 張青云
 * @create: 2022-04-10 02:23
 **/
public final class NameGenerator {

    /**
     * 根据性别生成一个人名
     * @param sex 性别，0是女，1是男
     * @return 人名
     */
    public static String getName(boolean sex) {
        //  获取一个姓
        List<String> familyNames = getFamilyNames();
        String familyName = familyNames.get(new Random().nextInt(familyNames.size()));

        //  读磁盘获取一个名
        List<String> lastNames = getLastNames(sex);
        String lastName = lastNames.get(new Random().nextInt(lastNames.size()));
        int i = new Random().nextInt(3);
        if (i == 2) {
            return familyName + lastName;
        } else {
            return familyName + lastName.charAt(i);
        }
    }

    /**
     * 根据姓列表和双字的名列表随机生成一个姓名
     * @param familyNames 姓列表
     * @param lastNames 名列表，要求必须为双字的名
     * @return 姓名
     */
    public static String getName(List<String> familyNames, List<String> lastNames) {
        String familyName = familyNames.get(new Random().nextInt(familyNames.size()));
        String lastName = lastNames.get(new Random().nextInt(lastNames.size()));
        int i = new Random().nextInt(3);
        if (i == 2) {
            return familyName + lastName;
        } else {
            return familyName + lastName.charAt(i);
        }
    }

    /**
     * 获取姓氏列表
     */
    public static List<String> getFamilyNames() {
        InputStream is = NameGenerator.class.getClassLoader()
                .getResourceAsStream("百家姓.txt");
        List<String> familyNames = new ArrayList<>();
        if (is == null) {
            System.out.println("文件不存在");
            return familyNames;
        }
        BufferedReader reader = null;
        String line = "";
        reader = new BufferedReader(new InputStreamReader(is));
        try {
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split("\\s+");
                familyNames.addAll(Arrays.asList(arr));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return familyNames;
    }

    /**
     * 根据性别获取名列表
     */
    public static List<String> getLastNames(boolean sex) {
        List<String> lastNames = new ArrayList<>();
        InputStream in = null;
        if (sex) {
            in = NameGenerator.class.getClassLoader().getResourceAsStream("男性.txt");
        } else {
            in = NameGenerator.class.getClassLoader().getResourceAsStream("女性.txt");
        }
        if (in == null) {
            System.out.println("文件不存在");
            return lastNames;
        }
        BufferedReader reader = null;
        String line = "";
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                String[] arr = line.split("\\s+");
                lastNames.addAll(Arrays.asList(arr));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lastNames;
    }
}
