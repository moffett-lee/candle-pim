
CREATE TABLE `position` (
                            `Id` bigint(11) NOT NULL AUTO_INCREMENT,
                            `name` varchar(256) DEFAULT NULL,
                            `salary` varchar(50) DEFAULT NULL,
                            `city` varchar(256) DEFAULT NULL,
                            PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `position_detail` (
                                   `Id` bigint(11) NOT NULL AUTO_INCREMENT,
                                   `pid` bigint(11) NOT NULL DEFAULT '0',
                                   `description` text DEFAULT NULL,
                                   PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `city` (
                        `Id` bigint(11) NOT NULL AUTO_INCREMENT,
                        `name` varchar(256) DEFAULT NULL,
                        `province` varchar(256) DEFAULT NULL,
                        PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `b_order`(
                          `id`              bigint(20)   NOT NULL AUTO_INCREMENT,
                          `is_del`          bit(1)       NOT NULL DEFAULT 0 COMMENT '是否被删除',
                          `company_id`      int(11)      NOT NULL COMMENT '公司ID',
                          `position_id`     bigint(11)      NOT NULL COMMENT '职位ID',
                          `user_id`         int(11)      NOT NULL COMMENT '用户id',
                          `publish_user_id` int(11)      NOT NULL COMMENT '职位发布者id',
                          `resume_type`     int(2)       NOT NULL DEFAULT 0 COMMENT '简历类型：0 附件 1 在线',
                          `status`          varchar(256) NOT NULL COMMENT '投递状态 投递状态 WAIT-待处理 AUTO_FILTER-自动过滤 PREPARE_CONTACT-待沟通 REFUSE-拒绝 ARRANGE_INTERVIEW-通知面试',
                          `create_time`     datetime     NOT NULL COMMENT '创建时间',
                          `operate_time`    datetime     NOT NULL COMMENT '操作时间',
                          `work_year`       varchar(100)          DEFAULT NULL COMMENT '工作年限',
                          `name`            varchar(256)          DEFAULT NULL COMMENT '投递简历人名字',
                          `position_name`   varchar(256)          DEFAULT NULL COMMENT '职位名称',
                          `resume_id`        int(10)               DEFAULT NULL COMMENT '投递的简历id（在线和附件都记录，通过resumeType进行区别在线还是附件）',
                          PRIMARY KEY (`id`),
                          KEY `index_createTime` (`create_time`),
                          KEY `index_companyId_status` (`company_id`, `status`(255), `is_del`),
                          KEY `i_comId_pub_ctime` (`company_id`, `publish_user_id`, `create_time`),
                          KEY `index_companyId_positionId` (`company_id`, `position_id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE `c_order`(
                          `id`              bigint(20)   NOT NULL AUTO_INCREMENT,
                          `is_del`          bit(1)       NOT NULL DEFAULT 0 COMMENT '是否被删除',
                          `user_id`         int(11)      NOT NULL COMMENT '用户id',
                          `company_id`      int(11)      NOT NULL COMMENT '公司id',
                          `publish_user_id` int(11)      NOT NULL COMMENT 'B端用户id',
                          `position_id`     int(11)      NOT NULL COMMENT '职位ID',
                          `resume_type`     int(2)       NOT NULL DEFAULT 0 COMMENT '简历类型：0 附件 1 在线',
                          `status`          varchar(256) NOT NULL COMMENT '投递状态 投递状态 WAIT-待处理 AUTO_FILTER-自动过滤 PREPARE_CONTACT-待沟通 REFUSE-拒绝 ARRANGE_INTERVIEW-通知面试',
                          `create_time`     datetime     NOT NULL COMMENT '创建时间',
                          `update_time`     datetime     NOT NULL COMMENT '处理时间',
                          PRIMARY KEY (`id`),
                          KEY `index_userId_positionId` (`user_id`, `position_id`),
                          KEY `idx_userId_operateTime` (`user_id`, `update_time`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE `c_user` (
                          `Id` bigint(11) NOT NULL AUTO_INCREMENT,
                          `name` varchar(256) DEFAULT NULL,
                          `pwd_plain` varchar(256) DEFAULT NULL,
                          `pwd_cipher` varchar(256) DEFAULT NULL,
                          PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;