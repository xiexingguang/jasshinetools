package com.jasshine.tools;

import com.jasshine.io.FileUtil;

/**
 * Created by ecuser on 2016/2/1.
 * 工具方法，实现公司阿里云环境到内网环境的切换
 */
public class SwitchAli2InnerNet {


    public static String DEFAULT_A_LI_HOSTS_ADDRESS = "D:\\Tools\\内网环境配置\\ALI\\hosts";                               // 阿里云环境

    public static String DEFAULT_A_LI_ECDOMAIN_INI_ADRESS = "D:\\Tools\\内网环境配置\\ALI\\ECDomain.ini";

    public static String DEFAULT_INNER_HOSTS_ADDRESS = "D:\\Tools\\内网环境配置\\INNER\\hosts";                          // 内网环境

    public static String DEFAULT_INNDER_ECDOMAIN_INI_ADRESS = "D:\\Tools\\内网环境配置\\INNER\\ECDomain.ini";

    public static String DEFAULT_HOST_ADDRESS = "C:\\Windows\\System32\\drivers\\etc\\hosts";
    public static String DEFAULT_DOMAIN_ADDRESS = "C:\\Users\\ecuser\\Documents\\EC\\ECDomain.ini";


    public static void main(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("please input args which means ali 2 inner or inner 2 ali, ali 2 inner is 1 , inner 2 ali is 2");
        } else {
            if (args[0].equals("1")) {
                switchAli2Inner();
            } else {
                switchInner2Ali();
            }
        }
    }


    //阿里云切换到内网
    public static void switchAli2Inner() {
        // 第一步copy hosts 文件 ,将内网的配置拷贝到 hosts目录
        if (!FileUtil.copyFile(DEFAULT_INNER_HOSTS_ADDRESS, DEFAULT_HOST_ADDRESS, true)) {
            return;
        }
        // 第二步copy domain 文件
        FileUtil.copyFile(DEFAULT_INNDER_ECDOMAIN_INI_ADRESS, DEFAULT_DOMAIN_ADDRESS, true);
        System.out.println("===========切换阿里云到内网环境ok===========");
    }

    //内网切换到阿里云
    public static void switchInner2Ali() {
        // 第一步copy hosts 文件 ,将内网的配置拷贝到 hosts目录
        if (!FileUtil.copyFile(DEFAULT_A_LI_HOSTS_ADDRESS, DEFAULT_HOST_ADDRESS, true)) {
            return;
        }
        // 第二步copy domain 文件
        FileUtil.copyFile(DEFAULT_A_LI_ECDOMAIN_INI_ADRESS, DEFAULT_DOMAIN_ADDRESS, true);
        System.out.println("===========切换内网到阿里云环境ok============");
    }

}
