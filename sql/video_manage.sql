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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='本地文件夹';


CREATE TABLE `video_info` (
                              `video_id`        bigint(20)    NOT NULL AUTO_INCREMENT   COMMENT '视频ID',
                              `library_id`      bigint(20)    NOT NULL                  COMMENT '所属媒体库ID',
                              `file_name`       varchar(255)  NOT NULL                  COMMENT '原始文件名 (带后缀)',
                              `file_path`       varchar(255) NOT NULL                  COMMENT '文件当前绝对路径',
                              `thumbnail_path`  varchar(255) DEFAULT NULL              COMMENT '缩略图存储路径',
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

CREATE TABLE `video_tag_relation` (
                                      `video_id` bigint(20) NOT NULL COMMENT '视频ID',
                                      `tag_id`   bigint(20) NOT NULL COMMENT '标签ID',
                                      PRIMARY KEY (`video_id`, `tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频与标签关联表';