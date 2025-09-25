### **本地视频文件管理系统 - 设计计划书**

#### **一、 项目概述**

本项目旨在为个人用户打造一个基于Web的本地视频文件管理系统。用户可以通过浏览器方便地管理分散在电脑硬盘各处的视频文件，实现视频信息的自动化抓取、可视化浏览、标签化管理和便捷的播放与检索。

- **项目名称**: 个人本地视频管理系统 (可自定)
- **核心技术栈**:
  - **后端**: Java Spring Boot (基于若依RuoYi-Vue-Plus框架)
  - **前端**: Vue 3 + Element Plus
  - **数据库**: MySQL 8.0+
  - **核心依赖**: FFmpeg (用于处理视频元数据和缩略图)
- **开发阶段规划**:
  1. **第一阶段 (基础核心)**: 实现媒体库路径管理、视频文件扫描入库、基础文件操作（移动、重命名、删除）以及标签管理功能。
  2. **第二阶段 (体验增强)**: 实现视频元数据（时长、分辨率等）自动抓取、视频封面缩略图自动生成、内置Web视频播放器以及强大的多维度搜索筛选功能。
  3. **未来扩展 (可选)**: 重复文件检测、播放列表、收藏评级等。

#### **二、 系统模块划分图**

为了清晰地展示系统结构，我们将其划分为前端、后端和数据存储三个主要层次，并对后端业务进行细分。

```code
本地视频文件管理系统
├── Frontend - 前端展现层 (Vue3)
│   ├── Views (页面视图)
│   │   ├── 登录页 (Login)
│   │   ├── 主控台布局 (Layout)
│   │   ├── 视频浏览页 (VideoBrowser)
│   │   │   ├── 视频列表/网格视图
│   │   │   ├── 搜索与筛选栏
│   │   │   └── 分页组件
│   │   ├── 视频详情与播放弹窗/页面 (VideoPlayerModal)
│   │   ├── 媒体库管理页 (LibraryManagement)
│   │   └── 标签管理页 (TagManagement)
│   └── API (接口请求)
│       ├── video.js (视频相关接口)
│       ├── library.js (媒体库相关接口)
│       └── tag.js (标签相关接口)
│
├── Backend - 后端业务层 (Spring Boot / RuoYi)
│   ├── ruoyi-framework (框架核心)
│   │   └── (权限、日志、缓存等)
│   ├── ruoyi-system (系统模块)
│   │   └── (用户、角色、菜单等)
│   ├── ruoyi-admin (业务入口)
│   │   ├── Controller (控制器层)
│   │   │   ├── VideoInfoController (处理视频增删改查、文件操作请求)
│   │   │   ├── MediaLibraryController (处理媒体库增删改查、扫描请求)
│   │   │   └── TagInfoController (处理标签增删改查请求)
│   │   ├── Service (业务逻辑层)
│   │   │   ├── IVideoInfoService (视频业务接口)
│   │   │   └── VideoInfoServiceImpl (实现类，包含文件操作、FFmpeg调用等)
│   │   │   └── ... (其他Service)
│   │   ├── Mapper (数据访问层)
│   │   └── ...
│   └── ruoyi-common (通用工具模块)
│       └── utils
│           └── FFmpegUtils (封装调用FFmpeg命令的工具类)
│           └── FileScanUtils (封装文件扫描逻辑的工具类)
│
├── Data & Storage - 数据与存储层
│   ├── 数据库 (MySQL)
│   │   ├── 视频信息表 (video_info)
│   │   ├── 媒体库表 (media_library)
│   │   ├── 标签表 (tag_info)
│   │   └── 视频与标签关联表 (video_tag_relation)
│   └── 本地文件系统
│       ├── 原始视频文件 (User's Hard Drive)
│       └── 系统生成文件 (如缩略图缓存)
│
└── External Dependencies - 外部依赖
    └── FFmpeg (命令行工具)
        ├── 读取视频元数据
        └── 生成视频缩略图
```

#### **三、 核心数据库设计 (MySQL)**

这是项目核心功能的数据库表结构设计。设计遵循了若依框架的命名规范，并包含了必要的字段。

用于存储用户指定需要管理的根文件夹路径。

```sql
CREATE TABLE `media_library` (
  `library_id`   bigint(20)   NOT NULL AUTO_INCREMENT    COMMENT '媒体库ID',
  `library_name` varchar(100) NOT NULL                   COMMENT '媒体库显示名称',
  `library_path` varchar(500) NOT NULL                   COMMENT '媒体库的绝对物理路径',
  `remark`       varchar(500) DEFAULT NULL               COMMENT '备注',
  `create_by`    varchar(64)  DEFAULT ''                 COMMENT '创建者',
  `create_time`  datetime     DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
  `update_by`    varchar(64)  DEFAULT ''                 COMMENT '更新者',
  `update_time`  datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`library_id`),
  UNIQUE KEY `idx_library_path` (`library_path`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='媒体库信息表';
```

这是系统的核心表，存储每一个视频文件的详细信息。

```sql
CREATE TABLE `video_info` (
  `video_id`        bigint(20)    NOT NULL AUTO_INCREMENT   COMMENT '视频ID',
  `library_id`      bigint(20)    NOT NULL                  COMMENT '所属媒体库ID',
  `file_name`       varchar(255)  NOT NULL                  COMMENT '原始文件名 (带后缀)',
  `file_path`       varchar(1024) NOT NULL                  COMMENT '文件当前绝对路径',
  `thumbnail_path`  varchar(1024) DEFAULT NULL              COMMENT '缩略图存储路径',
  `file_hash`       varchar(64)   DEFAULT NULL              COMMENT '文件内容哈希值 (用于查重)',
  `duration`        int(11)       DEFAULT '0'               COMMENT '视频时长 (单位: 秒)',
  `resolution`      varchar(30)   DEFAULT ''                COMMENT '分辨率 (例如: 1920x1080)',
  `file_size`       bigint(20)    DEFAULT '0'               COMMENT '文件大小 (单位: Byte)',
  `format_name`     varchar(20)   DEFAULT ''                COMMENT '视频格式 (例如: mp4, mkv)',
  `status`          char(1)       DEFAULT '0'               COMMENT '状态 (0=正常, 1=文件丢失)',
  `remark`          varchar(500)  DEFAULT NULL              COMMENT '备注',
  `create_by`       varchar(64)   DEFAULT ''                COMMENT '创建者',
  `create_time`     datetime      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by`       varchar(64)   DEFAULT ''                COMMENT '更新者',
  `update_time`     datetime      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`video_id`),
  UNIQUE KEY `idx_file_path` (`file_path`),
  KEY `idx_library_id` (`library_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='视频文件信息表';
```

存储所有创建的标签。

```sql
CREATE TABLE `tag_info` (
  `tag_id`      bigint(20)   NOT NULL AUTO_INCREMENT   COMMENT '标签ID',
  `tag_name`    varchar(50)  NOT NULL                  COMMENT '标签名称',
  `create_by`   varchar(64)  DEFAULT ''                COMMENT '创建者',
  `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by`   varchar(64)  DEFAULT ''                COMMENT '更新者',
  `update_time` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`tag_id`),
  UNIQUE KEY `idx_tag_name` (`tag_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='标签信息表';
```

用于建立视频和标签之间的多对多关系。

```sql
CREATE TABLE `video_tag_relation` (
  `video_id` bigint(20) NOT NULL COMMENT '视频ID',
  `tag_id`   bigint(20) NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`video_id`, `tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频与标签关联表';
```