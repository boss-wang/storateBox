package com.chao.storagebox.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class GenerateMapperFiles
{
    private static final String spearator = System.getProperty("file.separator");

    // 指定atom包的位置
    private static final String atomPackage = "com.chao.storagebox.atom";
    // 指定bean存放的包
    private static final String modelPackage = "com.chao.storagebox.entity";
    // 指定mapper存放的包
    private static final String mapperPackage = "com.chao.storagebox.dao";
    // 数据库连接信息
    private static final String user = "chao"; // 数据库用户名
    private static final String password = "Dtw10781"; // 数据库密码
    private static final String dbName = "111.231.244.96:3306/storage_box"; // 数据库地址和库名

    public static void main(String[] args) {
        List<String> tableNames = new ArrayList<String>() {
            {
                // 要生成的表名，可添加多个
                add("");
            }
        };

        // 创建一个当前类的对象
        GenerateMapperFiles ct = new GenerateMapperFiles();

        /*下面的方法返回数据库的表名及其列，key存放表名；
          value存放列的数据类型和列名，如：第一个内容是"int",第二个内容是"studentNo",第三个内容是"String",第四个内容是"studentName"，以此类推*/
        Map<String, List<String>> tables = ct.getTables(dbName, tableNames, user, password);

        // 用获得的表名和列名创建javaBean文件
        ct.createModelFiles(tables);
        // 创建mapper接口文件
        ct.createMapperFiles(tables);
        // 创建对应的XML文件
        ct.createMapperXMLFiles(tables);
        // 创建对应atom文件
        ct.createAtomFiles(tables);
    }

    // 创建javaBean文件
    private void createModelFiles(Map<String, List<String>> tables) {

        Set<String> tableNames = tables.keySet();
        for (String tableName : tableNames) {
            StringBuffer sb = new StringBuffer(); // sb用来存放文件内容

            // 准备创建目标文件
            String className = firstCharacterToUpper(regReplace(tableName, "_"));

            sb.append("package ").append(modelPackage).append(";\n\n").append("import lombok.Data;\n");

            // 遍历列名，作为属性加入到sb中
            List<String> columns = tables.get(tableName);

            if (columns.contains("Date")) {
                sb.append("import java.util.Date;\n");
            }

            sb.append("\n@Data\npublic class ").append(className).append(" {\n");

            for (int i = 0; i < columns.size(); i = i + 2) {
                String columnType = columns.get(i);
                String columnName = regReplace(columns.get(i + 1), "_");

                sb.append("\n\tprivate ").append(columnType).append(" ").append(columnName).append(";\n");
            }

            sb.append("}");

            // 写入文件
            String packagePath = modelPackage.replace(".", spearator);

            String basePath = this.getClass().getResource("").getPath();

            basePath = basePath.substring(0, basePath.indexOf("target") - 1) + spearator + "src" + spearator + "main"
                + spearator + "java";

            File file = new File(basePath + spearator + packagePath + spearator + className + ".java");

            writeFile(file, sb);

        }
    }

    // 创建mapper接口文件
    private void createMapperFiles(Map<String, List<String>> tables) {

        Set<String> tableNames = tables.keySet();
        for (String tableName : tableNames) {
            StringBuffer sb = new StringBuffer(); // sb用来存放文件内容

            // 准备创建目标文件名
            String beanName = firstCharacterToUpper(regReplace(tableName, "_"));

            String mapperClassName = beanName + "Mapper";

            sb.append("package ").append(mapperPackage).append(";\n\n").append("import ").append(modelPackage).append(".").append(beanName).append(";\nimport java.util.List;\n\n");

            sb.append("public interface ").append(mapperClassName).append(" {\n");

            sb.append("\tList<").append(beanName).append(">").append(" get").append(beanName).append("List();");

            sb.append("\n}");

            // 写入文件
            String basePath = this.getClass().getResource("").getPath();

            basePath = basePath.substring(0, basePath.indexOf("target") - 1) + spearator + "src" + spearator + "main"
                + spearator + "java";

            String packagePath = mapperPackage.replace(".", spearator);

            File file = new File(basePath + spearator + packagePath + spearator + mapperClassName + ".java");

            writeFile(file, sb);

        }
    }

    // 创建对应的xml文件
    private void createMapperXMLFiles(Map<String, List<String>> tables) {
        Set<String> tableNames = tables.keySet();
        for (String tableName : tableNames) {
            StringBuffer sb = new StringBuffer(); // sb用来存放文件内容

            // 准备创建目标文件名
            String beanName = firstCharacterToUpper(regReplace(tableName, "_"));
            String mapperXMLName = beanName + "Mapper";

            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
                + "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n"
                + "<mapper namespace=\"").append(mapperPackage).append(".").append(mapperXMLName).append("\">\n");

            sb.append(" <sql id=\"Base_Column_List\" >");

            List<String> columns = tables.get(tableName);

            String columnNames = "";
            for (int i = 0; i < columns.size(); i = i + 2) {
                String columnName = columns.get(i + 1) + " as " + regReplace(columns.get(i + 1), "_");
                columnNames += ",\n   " + columnName;
            }
            columnNames = columnNames.substring(1);
            sb.append("  ").append(columnNames);
            sb.append("\n </sql>\n");

            sb.append("\n <select id=\"get").append(beanName).append("List\" resultType=\"").append(modelPackage)
                .append(".").append(beanName).append("\">\n");
            sb.append("  SELECT\n\t<include refid=\"Base_Column_List\" />\n  FROM ").append(tableName);

            sb.append("\n </select>");
            sb.append("\n</mapper>");

            // 写入文件
            String basePath = this.getClass().getResource("").getPath();

            basePath = basePath.substring(0, basePath.indexOf("target") - 1) + spearator + "src" + spearator + "main"
                + spearator + "resources";

            File file = new File(basePath + spearator + "mapper" + spearator + mapperXMLName + ".xml");

            writeFile(file, sb);

        }
    }

    // 创建对应的atom文件
    private void createAtomFiles(Map<String, List<String>> tables) {
        Set<String> tableNames = tables.keySet();
        for (String tableName : tableNames) {
            StringBuffer sb = new StringBuffer(); // sb用来存放文件内容

            // 准备创建目标文件名
            String beanName = firstCharacterToUpper(regReplace(tableName, "_"));

            String atomClassName = beanName + "Atom";
            String mapperName = regReplace(tableName, "_") + "Mapper";

            sb.append("package ").append(atomPackage).append(";\n\n")
                    .append("\nimport org.springframework.stereotype.Service;")
                    .append("\nimport org.springframework.transaction.annotation.Transactional;")
                    .append("\nimport javax.annotation.Resource;")
                    .append("\nimport ").append(modelPackage).append(".").append(beanName).append(";")
                    .append("\nimport ").append(mapperPackage).append(".").append(firstCharacterToUpper(mapperName)).append(";")
                    .append("\nimport java.util.List;\n\n");

            sb.append("@Service\n@Transactional\n").append("public class ").append(atomClassName).append(" {\n");

            sb.append("\t@Resource\n\tprivate ").append(firstCharacterToUpper(mapperName)).append(" ").append(mapperName).append(";\n\n");

            sb.append("\tpublic List<").append(beanName).append(">").append(" get").append(beanName).append("List() {\n")
            .append("\t\treturn ").append(mapperName).append(".get").append(beanName).append("List").append("();")
            .append("\n\t}");

            sb.append("\n}");

            // 写入文件
            String basePath = this.getClass().getResource("").getPath();

            basePath = basePath.substring(0, basePath.indexOf("target") - 1) + spearator + "src" + spearator + "main"
                    + spearator + "java";

            String packagePath = atomPackage.replace(".", spearator);

            File file = new File(basePath + spearator + packagePath + spearator + atomClassName + ".java");

            writeFile(file, sb);

        }
    }

    // 此方法获取列名,并返回带有表名及其列的Map集合
    private Map<String, List<String>> getTables(String dbName, List<String> tableNames, String user, String password) {
        String url = "jdbc:mysql://" + dbName + "?characterEncoding=utf-8";
        Connection connection = null;
        // dbDate用于保存数据库表对象
        DatabaseMetaData dbDate = null;

        // 保存表名和列名的Map
        Map<String, List<String>> tables = new HashMap<>();
        try {
            // 连接数据库
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            dbDate = connection.getMetaData();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        // 遍历表名
        for (String tableName : tableNames) {
            try {
                List<String> columns = new ArrayList<>(); // 准备存放列的数据类型和名字
                ResultSet rst = dbDate.getColumns(null, "%", tableName, "%"); // 根据表名获得列
                // 遍历列中的数据类型和内容
                while (rst.next()) {
                    String columnType = rst.getString("TYPE_NAME").toLowerCase();
                    switch (columnType) {
                        case "varchar":
                        case "char":
                        case "text":
                            columnType = "String";
                            break;
                        case "datetime":
                        case "date":
                            columnType = "Date";
                            break;
                        case "int":
                        case "tinyint":
                        case "smallint":
                            columnType = "Integer";
                            break;
                        case "fload":
                            columnType = "Float";
                            break;
                        case "double":
                            columnType = "Double";
                            break;
                        case "bigint":
                            columnType = "Long";
                            break;
                        default:
                            break;
                    }

                    columns.add(columnType); // 存入数据类型
                    columns.add(rst.getString("COLUMN_NAME")); // 存入列名
                }
                tables.put(tableName, columns); // 将表名和列一起存入Map集合

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (null != connection) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tables;
    }

    private String regReplace(String name, String org) {
        String newString = "";
        int index;

        while (name.contains(org)) {
            index = name.indexOf(org);
            if (index != name.length()) {
                newString = newString + name.substring(0, index) + "";
                name = name.substring(index + org.length());
                name = firstCharacterToUpper(name);
            }
        }
        newString = newString + name;
        return newString;

    }

    private static String firstCharacterToUpper(String srcStr) {
        return srcStr.substring(0, 1).toUpperCase() + srcStr.substring(1);
    }

    private void writeFile(File file, StringBuffer content) {
        // 如果包文件不存在则创建一个
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try {
            FileWriter fw = new FileWriter(file);
            fw.write(content.toString()); // 内容写入文件
            System.out.println(file.getAbsolutePath());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
