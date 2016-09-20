package com.mdzy.bk.modules.sys.constants;


/**
 * 项目中使用的常量定义
 * Created by chengyou on 2015/10/24.
 */
public class Constants {
    public static final Integer PAGE_SIZE = 10;
    public static final Integer OPER_SUCCESS = 1;
    public static final Integer OPER_FAIL = 0;

    /**
     * 作业
     */
    public static class Work{
        //类型：视频
        public static final Integer WORK_TYPE_VIEW = 1;
        //类型：图片和文字
        public static final Integer WORK_TYPE_IMG = 0;
    }

    /**
     * 班级管理
     */
    public static class Classes{
        //班级在校情况--离园
        public static final Integer CLASS_INSCHOOL_NO = 0;
        //班级在校情况--在园
        public static final Integer CLASS_INSCHOOL_IN = 1;
    }

    /**
     * 教师管理
     */
    public static class Teacher{
        //教师新增
        public static final Integer STATUS_ADD = -10;
        //教师注册
        public static final Integer STATUS_REG = 0;
        //班级在校情况--离园
        public static final Integer INSCHOOL_NO = 0;
        //班级在校情况--在园
        public static final Integer INSCHOOL_IN = 1;
        //园长
        public static final String TYPE_YZ = "-1";
    }

    /**
     * 家长管理
     */
    public static class User{
        //家长新增
        public static final Integer STATUS_ADD = -10;
        //家长注册
        public static final Integer STATUS_REG = 0;
    }

    /**
     * 宝宝管理
     */
    public static class Child{
        //宝宝在校情况--离园
        public static final Integer INSCHOOL_NO = 0;
        //宝宝在校情况--在园
        public static final Integer INSCHOOL_IN = 1;
        //添加
        public static final Integer ADD = -10;
    }

    public static class Article{
        public static final String HELPER_CENTER = "help_center";
        public static final String ABOUT = "about_us";
        public static final String CATEGORY_NOTICE = "消息通知";
        public static final String CATEGORY_SCHOOL_DESC = "幼儿园介绍";
    }

    public static class Message{
        //消息通知
        public static final Integer NOTICE_XIAOXI = 2;

    }

    /**
     * JPush
     */
    public static class JPust{
        //教师端
        public static final Integer TYPE_TEACHER = 1;
        //家长端
        public static final Integer TYPE_USER = 0;
        //终端端
        public static final Integer TYPE_TERMINAL = 3;
    }

    /**
     *
     */
    public static class Notice{
        //全部(家长，教师)
        public static final String SEND_TYPE_ALL = "0";
        //家长
        public static final String SEND_TYPE_USER = "1";
        //教师
        public static final String SEND_TYPE_TEACHER = "2";
        //终端
        public static final String SEND_TYPE_TERMINAL = "3";

        public static final String SEND_TYPE_RECTOR = "4";
    }

    /**
     * 视频常量
     */
    public static class Video{
        //首页推荐没页大小
        public static final String HOT_SIZE = "4";

        //类型：直播
        public static final String TYPE_ZHIBO = "0";

        //类型：点播
        public static final String TYPE_DIANBO = "1";

        //推荐
        public static final String LABEL_HOT = "1";

        //上架
        public static final String STATUS_PUB = "1";
    }
}
