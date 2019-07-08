/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1_5.5
 Source Server Type    : MySQL
 Source Server Version : 50553
 Source Host           : localhost:3306
 Source Schema         : charging_pile

 Target Server Type    : MySQL
 Target Server Version : 50553
 File Encoding         : 65001

 Date: 08/07/2019 19:20:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_permission`;
CREATE TABLE `admin_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单或按钮名字',
  `menu_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单URL',
  `menu_lev` int(4) NULL DEFAULT NULL COMMENT '菜单级别  1 一级菜单  2 二级菜单',
  `menu_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单样式',
  `menu_type` int(4) NULL DEFAULT NULL COMMENT '菜单类型  1 菜单  2按钮',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '上级菜单',
  `menu_order` int(10) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜单资源表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_permission
-- ----------------------------
INSERT INTO `admin_permission` VALUES (1, '系统管理', 'xxxx.html', 1, 'icon-credit-card', 1, 0, 1);
INSERT INTO `admin_permission` VALUES (2, '角色管理', NULL, 2, 'icon-credit-card', 1, 1, 2);
INSERT INTO `admin_permission` VALUES (3, '用户管理', NULL, 2, 'icon-credit-card', 1, 1, 3);
INSERT INTO `admin_permission` VALUES (4, '系统日志', NULL, 2, 'icon-credit-card', 1, 1, 4);
INSERT INTO `admin_permission` VALUES (5, '角色管理添加', NULL, NULL, '', 2, 2, NULL);
INSERT INTO `admin_permission` VALUES (6, '角色管理删除', NULL, NULL, NULL, 2, 2, NULL);
INSERT INTO `admin_permission` VALUES (7, '角色管理查看', NULL, NULL, NULL, 2, 2, NULL);
INSERT INTO `admin_permission` VALUES (8, '用户管理添加', NULL, NULL, NULL, 2, 3, NULL);
INSERT INTO `admin_permission` VALUES (9, '用户管理删除', NULL, NULL, NULL, 2, 3, NULL);
INSERT INTO `admin_permission` VALUES (10, '用户管理查看', NULL, NULL, NULL, 2, 3, NULL);
INSERT INTO `admin_permission` VALUES (11, '系统日志查看', NULL, NULL, NULL, 2, 4, NULL);
INSERT INTO `admin_permission` VALUES (12, '系统监控', NULL, NULL, NULL, 1, 1, 5);
INSERT INTO `admin_permission` VALUES (13, '系统监控查看', NULL, NULL, NULL, 2, 12, NULL);
INSERT INTO `admin_permission` VALUES (14, '分销管理', NULL, 1, 'icon-credit-card', 1, 0, 6);
INSERT INTO `admin_permission` VALUES (15, '分销级别', NULL, 2, 'icon-credit-card', 1, 14, 7);
INSERT INTO `admin_permission` VALUES (16, '分销级别查看', NULL, NULL, NULL, 2, 15, NULL);
INSERT INTO `admin_permission` VALUES (17, '分销级别添加', NULL, NULL, NULL, 2, 15, NULL);

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_parent_id` bigint(20) NULL DEFAULT 0,
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色名',
  `role_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色编码',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `details` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `is_last` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '1:最后一级代理，可创建会员，0:不是最后一级',
  `admin_user_id` bigint(20) NULL DEFAULT 0 COMMENT '所属管理员下级管理员角色',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1088704347015835651 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES (1083303491852402689, NULL, 'admin', '867cd70f-00cd-4a70-a8d9-57c1d3bd9c61', 1547114653771, NULL, 0, 1, '2019-01-25 13:18:18');
INSERT INTO `admin_role` VALUES (1083632255647973378, NULL, '角色管理', 'd7fe35cb-4591-4878-8f1f-608614c3c968', 1547193037167, NULL, 0, 1, '2019-01-25 13:18:18');
INSERT INTO `admin_role` VALUES (1083632642748694530, NULL, '用户管理', 'e0d697e3-c2a5-488e-b420-9db7d88e677b', 1547193129484, NULL, 0, 1, '2019-01-25 13:18:19');
INSERT INTO `admin_role` VALUES (1083641901943209986, NULL, '超级管理员', '0c370f3b-a937-4125-94fc-52778fd37ae6', 1547195337041, NULL, 0, 1, '2019-01-25 15:58:25');
INSERT INTO `admin_role` VALUES (1085418800952483842, NULL, '1', 'a52f9525-779b-4f15-b91d-0ee9f5de2599', 1547618982764, NULL, 0, 1, '2019-01-25 13:18:24');
INSERT INTO `admin_role` VALUES (1088704347015835650, 0, '2', '9289131e-360c-4e4a-afd4-97d88f4de75e', 1548402317988, NULL, 1, 1, '2019-01-25 16:01:19');

-- ----------------------------
-- Table structure for admin_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_permission`;
CREATE TABLE `admin_role_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `r_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `m_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色菜单关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_role_permission
-- ----------------------------
INSERT INTO `admin_role_permission` VALUES (1, 1083303491852402689, 1);
INSERT INTO `admin_role_permission` VALUES (2, 1083303491852402689, 2);
INSERT INTO `admin_role_permission` VALUES (3, 1083303491852402689, 3);

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '帐号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '昵称',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名',
  `create_time` datetime NULL DEFAULT NULL COMMENT '添加时间',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '其他状态',
  `is_disable` tinyint(1) NULL DEFAULT 0 COMMENT '0:有效 1:禁止登录',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '1:删除，0:未删除',
  `admin_parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父级管理员id',
  `agent_parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父级分销代理商id',
  `user_invite_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代理商邀请码',
  `invitation_path` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '推荐邀请关系树',
  `inspect_status` tinyint(3) NULL DEFAULT 1 COMMENT '审核状态(1-待审核;2-已审核;3-拒绝)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES (1, 'admin', 'ac58ace355cb83a929837bb7ea13b000', NULL, 'admin', NULL, NULL, '2019-07-04 16:25:59', NULL, NULL, 0, 0, 0, NULL, NULL, 1);

-- ----------------------------
-- Table structure for admin_user_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_role`;
CREATE TABLE `admin_user_role`  (
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色用户关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_user_role
-- ----------------------------
INSERT INTO `admin_user_role` VALUES (1083303491852402689, 1);

-- ----------------------------
-- Table structure for agent_user
-- ----------------------------
DROP TABLE IF EXISTS `agent_user`;
CREATE TABLE `agent_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '帐号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '昵称',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '添加时间',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '其他状态',
  `is_disable` tinyint(1) NULL DEFAULT NULL COMMENT '0:有效 1:禁止登录',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '1:删除，0:未删除',
  `admin_parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父级管理员id',
  `agent_parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父级分销代理商id',
  `user_invite_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代理商邀请码',
  `invitation_path` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '推荐邀请关系树',
  `inspect_status` tinyint(3) NULL DEFAULT 1 COMMENT '审核状态(1-待审核;2-已审核;3-拒绝)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '代理商用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of agent_user
-- ----------------------------
INSERT INTO `agent_user` VALUES (1, 'admin', 'ac58ace355cb83a929837bb7ea13b000', NULL, 'admin', NULL, NULL, '2019-07-04 16:25:42', NULL, NULL, 0, 0, 0, NULL, NULL, 1);

-- ----------------------------
-- Table structure for charging_pile_info
-- ----------------------------
DROP TABLE IF EXISTS `charging_pile_info`;
CREATE TABLE `charging_pile_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `charging_stations_id` bigint(20) NULL DEFAULT NULL COMMENT '充电站id',
  `serial_number` char(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
  `parking_lot_no` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车位号',
  `rate_of_work` decimal(25, 4) NULL DEFAULT NULL COMMENT '功率 单位 KW',
  `ac_dc` tinyint(2) NULL DEFAULT NULL COMMENT '1:直流 2:交流',
  `has_a_gun_status` tinyint(2) NULL DEFAULT 1 COMMENT '有枪状态0:无枪 1：有枪',
  `open_start_time` time NULL DEFAULT NULL COMMENT '开放开始时间',
  `open_end_time` time NULL DEFAULT NULL COMMENT '开放结束时间',
  `price` decimal(25, 4) NULL DEFAULT 1.8000 COMMENT '价格单位元度',
  `service_charge` decimal(25, 4) NULL DEFAULT 1.0000 COMMENT '服务费 价格单位元度',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '0:未删除 1:删除',
  `use_status` tinyint(1) NULL DEFAULT 0 COMMENT '0未使用 1：正在使用',
  `off_line_is` tinyint(1) NULL DEFAULT 0 COMMENT '0未离线 1：离线',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '充电桩' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of charging_pile_info
-- ----------------------------
INSERT INTO `charging_pile_info` VALUES (4, 1, '1', NULL, 50.0000, NULL, 1, NULL, NULL, 1.8000, 1.0000, NULL, '2019-07-01 17:21:08', 0, 0, 0);

-- ----------------------------
-- Table structure for charging_record
-- ----------------------------
DROP TABLE IF EXISTS `charging_record`;
CREATE TABLE `charging_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stock_user_id` bigint(20) NULL DEFAULT NULL COMMENT '充电用户id',
  `charging_stations_id` bigint(20) NULL DEFAULT NULL COMMENT '充电站id',
  `charging_pile_info_id` bigint(20) NULL DEFAULT NULL COMMENT '充电桩id',
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市',
  `county` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '县',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细地点',
  `price` decimal(25, 4) NULL DEFAULT 1.8000 COMMENT '价格单位元',
  `service_charge` decimal(25, 4) NULL DEFAULT NULL COMMENT '服务费 价格单位元度',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `charge_start_time` datetime NULL DEFAULT NULL COMMENT '充电开始时间',
  `charge_end_time` datetime NULL DEFAULT NULL COMMENT '充电结束时间',
  `charge_num` decimal(25, 4) NULL DEFAULT 0.0000 COMMENT '充电度数',
  `charge_total_money` decimal(25, 4) NULL DEFAULT 0.0000 COMMENT '充电总金额',
  `payment_status` tinyint(2) NULL DEFAULT 0 COMMENT '0:未付款 1:已付款',
  `charge_status` tinyint(2) NULL DEFAULT 0 COMMENT '0:未充电 1:充电中 2:充电结束',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '充电记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of charging_record
-- ----------------------------
INSERT INTO `charging_record` VALUES (5, 1, 1, 4, '黑色', '双方都是', '打赏', '大', 1.8000, 1.0000, '2019-06-29 14:19:49', '2019-06-29 14:22:35', '2019-06-29 14:19:49', '2019-06-29 14:22:35', 0.0000, 0.0000, 0, 2);

-- ----------------------------
-- Table structure for charging_stations
-- ----------------------------
DROP TABLE IF EXISTS `charging_stations`;
CREATE TABLE `charging_stations`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `longitude` decimal(25, 8) NULL DEFAULT NULL COMMENT '经度',
  `dimensionality` decimal(25, 8) NULL DEFAULT NULL COMMENT '维度',
  `station_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '充电站名字',
  `station_details` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情',
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市',
  `county` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '县',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地点',
  `price` decimal(25, 4) NULL DEFAULT 1.8000 COMMENT '价格单位元',
  `parking_fee_details` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '停车费',
  `have_parking_fee` tinyint(2) NULL DEFAULT 0 COMMENT '0:无停车费 1:有停车费',
  `open_start_time` time NULL DEFAULT NULL COMMENT '开放开始时间',
  `open_end_time` time NULL DEFAULT NULL COMMENT '开放结束时间',
  `cover_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品封面图',
  `banner_img` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '产品轮播图',
  `has_a_gun_status` tinyint(2) NULL DEFAULT 1 COMMENT '有枪状态0:无枪 1：有枪',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '0:未删除 1:删除',
  `is_disable` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '0:未禁用 1：禁用',
  `gr_serial_number` char(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号（分组id）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `gr_serial_number`(`gr_serial_number`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '充电站' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of charging_stations
-- ----------------------------
INSERT INTO `charging_stations` VALUES (1, 1.20000000, 2.10000000, 'fsdf', 'ewrwe', '黑色', '双方都是', '打赏', '大', 1.8000, '发生的', 12, '06:45:55', '06:45:55', 'swf ', 'v', 1, NULL, '2019-06-12 11:33:50', 1, 0, NULL);
INSERT INTO `charging_stations` VALUES (2, 1.20000000, 2.10000000, 'fsdf', 'ewrwe', '黑色', '双方都是', '打赏', '大', 1.8000, '发生的', 12, '06:45:55', '06:45:55', 'swf ', 'v', 1, '2019-06-12 03:11:35', '2019-06-12 11:11:35', 0, 0, NULL);

-- ----------------------------
-- Table structure for com_config_area
-- ----------------------------
DROP TABLE IF EXISTS `com_config_area`;
CREATE TABLE `com_config_area`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '区域主键',
  `area_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域名称',
  `area_code` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域代码',
  `area_short` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域简称',
  `area_is_hot` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否热门(0:否、1:是)',
  `area_sequence` int(11) NULL DEFAULT NULL COMMENT '区域序列',
  `area_parent_id` int(11) NULL DEFAULT 0 COMMENT '上级主键',
  `letter` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '首字母',
  `init_date` datetime NULL DEFAULT NULL COMMENT '初始时间',
  `init_addr` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '初始地址',
  `longitude` decimal(25, 8) NULL DEFAULT NULL COMMENT '经度',
  `dimensionality` decimal(25, 8) NULL DEFAULT NULL COMMENT '维度',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parent_id`(`area_parent_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 900001 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '区域字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log_admin_agent
-- ----------------------------
DROP TABLE IF EXISTS `log_admin_agent`;
CREATE TABLE `log_admin_agent`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `request_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '请求地址',
  `request_way` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '请求方式',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'IP',
  `method_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '方法地址',
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '方法别名',
  `request_param` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '请求参数',
  `return_param` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '返回参数',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '耗时',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `operator_id` bigint(255) NULL DEFAULT NULL COMMENT '操作人id',
  `operator_platform` tinyint(2) NULL DEFAULT 1 COMMENT '1,admin，2，agent',
  `login_facility` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录设备',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 405 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log_app_pc
-- ----------------------------
DROP TABLE IF EXISTS `log_app_pc`;
CREATE TABLE `log_app_pc`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `request_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '请求地址',
  `request_way` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '请求方式',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'IP',
  `method_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '方法地址',
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '方法别名',
  `request_param` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '请求参数',
  `return_param` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '返回参数',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '耗时',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `operator_id` bigint(20) NULL DEFAULT NULL COMMENT '操作人id',
  `operator_platform` tinyint(2) NULL DEFAULT 1 COMMENT '1,app',
  `login_facility` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录设备',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 570 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for stock_user
-- ----------------------------
DROP TABLE IF EXISTS `stock_user`;
CREATE TABLE `stock_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `user_uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录帐号',
  `tel` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱|帐号',
  `pswd` char(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码，使用md5加密',
  `trade_pwd` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易密码',
  `device_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备号',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `is_disable` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '0:未禁用 1：禁用',
  `is_deleted` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '0:未删除 1:删除',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `head_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信唯一id',
  `agent_user_id` bigint(11) NULL DEFAULT NULL COMMENT '添加代理商id',
  `user_type` tinyint(2) NULL DEFAULT NULL COMMENT '用户类型 1：小程序用户  2：ic卡用户',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account_unique_index`(`user_uid`) USING BTREE,
  UNIQUE INDEX `open_id`(`open_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '手机用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of stock_user
-- ----------------------------
INSERT INTO `stock_user` VALUES (1, NULL, NULL, '7HYHH', '13015582372', NULL, NULL, NULL, NULL, NULL, '2019-06-19 11:20:38', 0, 0, '2019-07-04 14:50:20', NULL, 'oNi5a5CGoV0wKbfgMxapbL1HKXIA', NULL, NULL);
INSERT INTO `stock_user` VALUES (2, NULL, NULL, 'XX88553', NULL, NULL, NULL, NULL, NULL, '2019-06-14 13:46:25', '2019-06-19 11:20:38', 0, 0, '2019-07-04 14:50:18', NULL, '', 1, 2);
INSERT INTO `stock_user` VALUES (4, NULL, NULL, 'XX88175', NULL, NULL, NULL, NULL, NULL, '2019-07-04 16:02:49', NULL, 0, 0, '2019-07-04 16:02:49', NULL, NULL, 0, 2);

-- ----------------------------
-- Table structure for stock_user_capital_fund
-- ----------------------------
DROP TABLE IF EXISTS `stock_user_capital_fund`;
CREATE TABLE `stock_user_capital_fund`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stock_user_id` bigint(20) NOT NULL COMMENT '用户id',
  `stock_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '钱包类型',
  `stock_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '币种名字',
  `usable_fund` decimal(25, 8) NULL DEFAULT 0.00000000 COMMENT '可用',
  `in_all_fee` decimal(25, 8) NULL DEFAULT 0.00000000 COMMENT '总充值金额',
  `out_all_fee` decimal(25, 8) NULL DEFAULT 0.00000000 COMMENT '总提现金额',
  `create_time` datetime NULL DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `agent_user_id` bigint(20) NULL DEFAULT 0 COMMENT '添加代理商id',
  `card_num` char(7) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钱包账号（卡号）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `stock_user_id`(`stock_user_id`, `stock_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '个人资产' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of stock_user_capital_fund
-- ----------------------------
INSERT INTO `stock_user_capital_fund` VALUES (9, 1, 'APPLET', NULL, 0.00000000, 0.00000000, 0.00000000, '2019-07-04 13:43:38', '2019-07-04 21:05:04', 0, '1234567');
INSERT INTO `stock_user_capital_fund` VALUES (10, 2, 'ICCARD', NULL, 0.00000000, 0.00000000, 0.00000000, NULL, '2019-07-04 14:49:51', 0, '10000');
INSERT INTO `stock_user_capital_fund` VALUES (12, 4, 'ICCARD', NULL, 20.00000000, 0.00000000, 0.00000000, '2019-07-04 16:02:49', '2019-07-04 21:04:58', 0, '1234567');

-- ----------------------------
-- Table structure for stock_user_charge
-- ----------------------------
DROP TABLE IF EXISTS `stock_user_charge`;
CREATE TABLE `stock_user_charge`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `swift_no` char(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流水号: 四位项目简称 + yyyyMMddHHmmSS + 4位随机数 + 账号后4位',
  `stock_user_id` bigint(20) NULL DEFAULT NULL COMMENT '手机用户id',
  `stock_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IC卡号',
  `fee` decimal(25, 8) NULL DEFAULT 0.00000000 COMMENT '人民币金额',
  `withdraw_status` tinyint(3) UNSIGNED NULL DEFAULT 1 COMMENT '支付状态:1:未支付，2：支付成功 3：支付失败 4:处理中',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '1:删除，0:未删除',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `tran_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '三方支付平台的流水号',
  `pay_type` tinyint(3) UNSIGNED NULL DEFAULT 1 COMMENT '支付类型：1.微信 2.线下',
  `agent_user_id` bigint(20) NULL DEFAULT 0 COMMENT '充值代理商ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8700 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户出入金表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of stock_user_charge
-- ----------------------------
INSERT INTO `stock_user_charge` VALUES (8694, '1560307993862sh7q5k9k3', 1, '100', 100.00000000, 2, '2019-06-12 02:53:13', 0, '2019-06-12 10:53:13', '0', 2, 0);
INSERT INTO `stock_user_charge` VALUES (8695, '156099972338933dhi1995', 2, '1000000', 100.00000000, 4, '2019-06-20 11:02:03', 0, '2019-06-20 11:02:03', '=wx20110203775491a002f1d0321732610300', 1, NULL);
INSERT INTO `stock_user_charge` VALUES (8696, '15609997739706336ah68f', 2, '1000000', 100.00000000, 4, '2019-06-20 11:02:54', 0, '2019-06-20 11:02:54', '=wx20110254260656828c96d5971593619300', 1, NULL);
INSERT INTO `stock_user_charge` VALUES (8697, '15609997878590w8986zm1', 2, '1000000', 100.00000000, 4, '2019-06-20 11:03:08', 0, '2019-06-20 11:03:08', 'wx20110308141196c8446e53541967918400', 1, NULL);
INSERT INTO `stock_user_charge` VALUES (8698, '156099987291846k27m2vh', 2, '1000000', 100.99980000, 4, '2019-06-20 11:04:33', 0, '2019-06-20 11:04:33', 'wx20110433246643a8da45406c1197452100', 1, NULL);
INSERT INTO `stock_user_charge` VALUES (8699, '15622276428395h34c14yo', 4, 'ICCARD', 20.00000000, 2, '2019-07-04 16:07:24', 0, '2019-07-05 11:09:22', '0', 2, 1);

-- ----------------------------
-- Table structure for stock_user_info
-- ----------------------------
DROP TABLE IF EXISTS `stock_user_info`;
CREATE TABLE `stock_user_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stock_user_id` bigint(20) NULL DEFAULT NULL COMMENT '手机用户id',
  `bank_card_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡号',
  `bank_card_union_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银联号',
  `bank_card_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡类型',
  `bank_card_type_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡类型编码',
  `bank_card_open_bank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户行',
  `bank_card_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡图片',
  `bank_card_expired_time` datetime NULL DEFAULT NULL COMMENT '银行卡过期时间',
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市',
  `county` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '县',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地点',
  `id_card_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `id_card_front_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证正面照',
  `id_card_back_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证背面照',
  `id_card_start_time` datetime NULL DEFAULT NULL COMMENT '身份证有效期开始时间',
  `id_card_end_time` datetime NULL DEFAULT NULL COMMENT '身份证有效期结束时间',
  `selfie_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自拍照',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '1:删除，0:未删除',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `sex` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '性别 0:男 ，1女',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `stock_user_id_unique_index`(`stock_user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '手机用户信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for stock_user_money_detail
-- ----------------------------
DROP TABLE IF EXISTS `stock_user_money_detail`;
CREATE TABLE `stock_user_money_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stock_user_id` bigint(20) NULL DEFAULT NULL COMMENT '手机用户id',
  `stock_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '币种类型',
  `money` decimal(25, 8) NULL DEFAULT 0.00000000 COMMENT '发生金额',
  `money_before` decimal(25, 8) NULL DEFAULT 0.00000000 COMMENT '发生前金额',
  `money_after` decimal(25, 8) NULL DEFAULT 0.00000000 COMMENT '发生后金额',
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `type` tinyint(3) UNSIGNED NULL DEFAULT 1,
  `type_id` bigint(50) NULL DEFAULT NULL COMMENT '来源表id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '1:删除，0:未删除',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `water_type` tinyint(2) NULL DEFAULT 2 COMMENT '1:后台操作，2:正常流水',
  `income` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0：支出 1：收入',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `stock_user_id_index`(`stock_user_id`, `stock_code`, `type`, `type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户资金明细表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of stock_user_money_detail
-- ----------------------------
INSERT INTO `stock_user_money_detail` VALUES (1, 1, '100', 100.00000000, 0.00000000, 100.00000000, 'dwqd', 1, NULL, '2019-06-12 02:10:14', 0, '2019-06-12 10:10:14', 1, 1);
INSERT INTO `stock_user_money_detail` VALUES (2, 1, '100', 100.00000000, 0.00000000, 100.00000000, 'dwqd', 1, NULL, '2019-06-12 02:12:44', 0, '2019-06-12 10:12:44', 1, 1);
INSERT INTO `stock_user_money_detail` VALUES (3, 1, '100', 100.00000000, 0.00000000, 100.00000000, 'dwqd', 1, NULL, '2019-06-12 02:13:55', 0, '2019-06-12 10:13:55', 1, 1);
INSERT INTO `stock_user_money_detail` VALUES (4, 1, '100', 100.00000000, 0.00000000, 100.00000000, 'dwqd', 1, NULL, '2019-06-12 02:17:37', 0, '2019-06-12 10:17:37', 1, 1);
INSERT INTO `stock_user_money_detail` VALUES (5, 1, '100', 100.00000000, 0.00000000, 100.00000000, 'dwqd', 1, NULL, '2019-06-12 02:18:38', 0, '2019-06-12 10:18:38', 1, 1);
INSERT INTO `stock_user_money_detail` VALUES (6, 1, '100', 100.00000000, 100.00000000, 200.00000000, 'dwqd', 1, NULL, '2019-06-12 02:53:13', 0, '2019-06-12 10:53:13', 1, 1);
INSERT INTO `stock_user_money_detail` VALUES (7, 4, 'ICCARD', 20.00000000, 0.00000000, 20.00000000, '认为人为', 1, NULL, '2019-07-04 16:07:16', 0, '2019-07-04 16:07:18', 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
