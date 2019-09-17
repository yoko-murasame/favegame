/*
 Navicat Premium Data Transfer

 Source Server         : cloudMySqlAsRoot
 Source Server Type    : MySQL
 Source Server Version : 50626
 Source Host           : cloud.dmdream.cn:3306
 Source Schema         : favegame

 Target Server Type    : MySQL
 Target Server Version : 50626
 File Encoding         : 65001

 Date: 06/07/2019 11:07:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tab_admin
-- ----------------------------
DROP TABLE IF EXISTS `tab_admin`;
CREATE TABLE `tab_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `createTime` datetime(0) NOT NULL,
  `destroyTime` datetime(0) NULL DEFAULT NULL,
  `version` int(11) NOT NULL,
  `isValid` int(11) NOT NULL,
  `gmAdminUsername` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gmAdminPassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `gmAdminUsername`(`gmAdminUsername`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tab_admin
-- ----------------------------
INSERT INTO `tab_admin` VALUES (1, '2019-06-24 10:07:39', NULL, 1, 1, 'yoko', '123');

-- ----------------------------
-- Table structure for tab_attention
-- ----------------------------
DROP TABLE IF EXISTS `tab_attention`;
CREATE TABLE `tab_attention`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createTime` datetime(0) NOT NULL,
  `destroyTime` datetime(0) NULL DEFAULT NULL,
  `version` int(11) NOT NULL,
  `isValid` int(1) NOT NULL,
  `gmAttentionerId` int(11) NULL DEFAULT NULL,
  `gmGameId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of tab_attention
-- ----------------------------
INSERT INTO `tab_attention` VALUES (1, '2019-06-25 13:24:18', NULL, 1, 1, 1, 8);
INSERT INTO `tab_attention` VALUES (2, '2019-06-25 13:24:36', NULL, 1, 1, 1, 9);
INSERT INTO `tab_attention` VALUES (3, '2019-06-25 13:24:53', NULL, 1, 1, 2, 8);
INSERT INTO `tab_attention` VALUES (4, '2019-06-25 13:25:20', NULL, 1, 1, 3, 8);
INSERT INTO `tab_attention` VALUES (5, '2019-06-25 13:25:34', NULL, 1, 1, 4, 9);

-- ----------------------------
-- Table structure for tab_collection
-- ----------------------------
DROP TABLE IF EXISTS `tab_collection`;
CREATE TABLE `tab_collection`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `createTime` datetime(0) NOT NULL COMMENT '自动生成的创建时间',
  `destroyTIme` datetime(0) NULL DEFAULT NULL COMMENT 'isValid=0时使用',
  `version` int(11) NOT NULL COMMENT '乐观锁版本号,初始化1',
  `isValid` int(11) NOT NULL COMMENT '是否启用,默认1:启用 0:不启用',
  `gmCollectorId` int(11) NULL DEFAULT NULL COMMENT '收藏者id',
  `gmGameId` int(11) NULL DEFAULT NULL COMMENT '收藏的游戏的id',
  `gmGameName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '游戏名称(冗余字段方便显示)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FkGmGameId`(`gmGameId`) USING BTREE,
  INDEX `FkGmCollectorId`(`gmCollectorId`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tab_collection
-- ----------------------------
INSERT INTO `tab_collection` VALUES (24, '2019-07-05 02:20:51', NULL, 1, 1, 21, 47, 'ICEY');
INSERT INTO `tab_collection` VALUES (22, '2019-07-05 01:38:10', NULL, 1, 1, 21, 56, '测试');
INSERT INTO `tab_collection` VALUES (23, '2019-07-05 02:16:17', NULL, 1, 1, 21, 55, 'Fate Grand Order');
INSERT INTO `tab_collection` VALUES (8, '2019-07-03 14:26:17', NULL, 1, 1, 20, 46, 'Muse Dash');
INSERT INTO `tab_collection` VALUES (16, '2019-07-03 15:39:56', NULL, 1, 1, 20, 43, '皇家骑士：300自走棋');
INSERT INTO `tab_collection` VALUES (25, '2019-07-05 09:44:04', NULL, 1, 1, 21, 46, 'Muse Dash');
INSERT INTO `tab_collection` VALUES (26, '2019-07-05 09:46:38', NULL, 1, 1, 21, 50, '纪念碑谷（付费版）');

-- ----------------------------
-- Table structure for tab_comment
-- ----------------------------
DROP TABLE IF EXISTS `tab_comment`;
CREATE TABLE `tab_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `createTime` datetime(0) NOT NULL COMMENT '自动生成的创建时间',
  `destroyTIme` datetime(0) NULL DEFAULT NULL COMMENT 'isValid=0时使用',
  `version` int(11) NOT NULL COMMENT '乐观锁版本号,初始化1',
  `isValid` int(11) NOT NULL COMMENT '是否启用,默认1:启用 0:不启用',
  `gmCritic` int(11) NULL DEFAULT NULL COMMENT '评论人id',
  `gmGameId` int(11) NULL DEFAULT NULL COMMENT '被评论的游戏的id',
  `gmRate` double(3, 1) NULL DEFAULT NULL COMMENT '评价星级(1~10)',
  `gmComment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价内容',
  `favor` int(11) NULL DEFAULT NULL COMMENT '好评数',
  `against` int(11) NULL DEFAULT NULL COMMENT '反对数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FkGmCritic`(`gmCritic`) USING BTREE,
  INDEX `FkGmGameId`(`gmGameId`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tab_comment
-- ----------------------------
INSERT INTO `tab_comment` VALUES (1, '2019-06-24 10:07:39', NULL, 1, 1, 1, 43, 7.2, '垃圾游戏', 12, 31);
INSERT INTO `tab_comment` VALUES (2, '2019-06-24 10:07:39', NULL, 1, 1, 3, 43, 9.9, '游戏一般般', NULL, NULL);
INSERT INTO `tab_comment` VALUES (3, '2019-07-01 10:10:08', NULL, 1, 1, 20, 43, 0.0, '123', NULL, NULL);
INSERT INTO `tab_comment` VALUES (4, '2019-07-01 10:13:37', NULL, 1, 1, 20, 43, 10.0, '123321', 0, 0);
INSERT INTO `tab_comment` VALUES (5, '2019-07-01 14:07:21', NULL, 1, 1, 20, 43, 6.0, '123', 0, 0);
INSERT INTO `tab_comment` VALUES (6, '2019-07-01 14:10:09', NULL, 1, 1, 20, 43, 10.0, '夏佳恒瓜皮', 0, 0);
INSERT INTO `tab_comment` VALUES (7, '2019-07-01 14:11:14', NULL, 1, 1, 20, 43, 8.0, '拉的会计分录', 0, 0);
INSERT INTO `tab_comment` VALUES (8, '2019-07-01 14:44:59', NULL, 1, 1, 20, 43, 8.0, '的房间萨勒夫看见立刻就的房间萨勒夫看见立刻就的房间萨勒夫看见立刻就的房间萨勒夫看见立刻就', 0, 0);
INSERT INTO `tab_comment` VALUES (9, '2019-07-01 14:46:42', NULL, 1, 1, 20, 43, 8.0, '覆盖大负', 0, 0);
INSERT INTO `tab_comment` VALUES (10, '2019-07-02 15:07:29', NULL, 1, 1, 20, 48, 8.0, 'fh fhg mj ', 0, 0);
INSERT INTO `tab_comment` VALUES (11, '2019-07-03 13:53:10', NULL, 1, 1, 20, 46, 6.0, 'asf', 0, 0);
INSERT INTO `tab_comment` VALUES (12, '2019-07-03 15:38:01', NULL, 1, 1, 20, 43, 4.0, '123123', 0, 0);
INSERT INTO `tab_comment` VALUES (13, '2019-07-03 15:38:39', NULL, 1, 1, 20, 43, 6.0, '123123', 0, 0);
INSERT INTO `tab_comment` VALUES (14, '2019-07-05 01:15:31', NULL, 1, 1, 21, 56, 6.0, '123', 0, 0);
INSERT INTO `tab_comment` VALUES (15, '2019-07-05 01:38:47', NULL, 1, 1, 21, 56, 10.0, '还可以', 0, 0);
INSERT INTO `tab_comment` VALUES (16, '2019-07-05 02:16:06', NULL, 1, 1, 21, 55, 10.0, '111', 0, 0);
INSERT INTO `tab_comment` VALUES (17, '2019-07-05 02:20:45', NULL, 1, 1, 21, 47, 8.0, '2', 0, 0);
INSERT INTO `tab_comment` VALUES (18, '2019-07-05 09:45:15', NULL, 1, 1, 21, 46, 10.0, '12131231231231231', 0, 0);

-- ----------------------------
-- Table structure for tab_game
-- ----------------------------
DROP TABLE IF EXISTS `tab_game`;
CREATE TABLE `tab_game`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `createTime` datetime(0) NOT NULL COMMENT '自动生成的创建时间',
  `destroyTIme` datetime(0) NULL DEFAULT NULL COMMENT 'isValid=0时使用',
  `version` int(11) NOT NULL COMMENT '乐观锁版本号,初始化1',
  `isValid` int(11) NOT NULL COMMENT '是否启用,默认1:启用 0:不启用',
  `gmName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '游戏名称,必填',
  `gmIcon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '游戏icon',
  `gmVersion` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '游戏版本号,厂商指定,必填',
  `gmMark` double(3, 1) NULL DEFAULT 10.0 COMMENT '游戏评分',
  `gmPlatformIsAndroid` int(1) NULL DEFAULT NULL COMMENT '是否有安卓平台',
  `gmAndroidUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '安卓版本的下载地址',
  `gmPlatformIsIOS` int(1) NULL DEFAULT NULL COMMENT '是否有IOS平台',
  `gmIOSUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IOS版本的地址',
  `gmOperatorId` int(11) NULL DEFAULT NULL COMMENT '游戏的运营商Id(必填）（暂未设计表）',
  `gmPublisherId` int(11) NOT NULL COMMENT '发行商外键,必填',
  `gmTypeId` int(11) NOT NULL COMMENT '游戏类型外键,必填',
  `gmRunenv` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '游戏运行配置',
  `gmDetail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '游戏详情',
  `gmIntroMedia` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '游戏介绍视频和图片',
  `gmFree` int(1) NULL DEFAULT NULL COMMENT '游戏付费类型',
  `gmPrice` decimal(10, 2) NULL DEFAULT NULL COMMENT '付费金额',
  `gmTag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '游戏标签，靠,逗号分隔',
  `gmPubdate` date NULL DEFAULT NULL COMMENT '发行日期，由发行商填写',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tab_game
-- ----------------------------
INSERT INTO `tab_game` VALUES (46, '2019-07-05 09:53:34', NULL, 1, 1, 'Muse Dash', 'http://img.dmdream.cn/game/8b253649-f233-4d54-927c-ea7e3223a55c.png?imageslim', '1.0.2', 10.0, 1, '', 1, 'https://www.baidu.com/', 3, 32, 51, '', '跑酷+音游的完美结合，跑酷里最好听的，音游里最好玩的，就是 Muse Dash 了！\n——《光影对决》制作人 许慕典\n\n『我们的世界乐章 已被改写』\n\n『你可否谛听我的呢喃？』', 'http://img.dmdream.cn/game/0af20b3e-e0a4-4424-975a-8b515e1d3eba.jpg?imageslim,http://img.dmdream.cn/game/0f4aedf8-675e-487d-9e35-be3e27e346ae.mp4?imageslim', 1, 18.00, NULL, '2019-02-14');
INSERT INTO `tab_game` VALUES (47, '2019-06-28 11:09:07', NULL, 1, 1, 'ICEY', 'http://img.dmdream.cn/game/b87f28ba-68a2-411a-bd7b-302d58460ff8.png?imageslim', '1.10', 10.0, 1, 'http://img.dmdream.cn/game/e9fd5d46-08d4-4321-b9f1-27fae3b68e7d.png?imageslim', NULL, '', -1, 32, 3, '', '《ICEY》（艾希）是一款2D横版动作游戏，跟随旁白的指示，你将会通过ICEY的眼睛去看，去战斗并发掘在游戏中世界的真相。但其实，这也根本不是一款2D横版动作游戏，你也不会通过ICEY的眼睛去看，你也不会去探寻这个世界的真相。 　 \n　　本质上说《ICEY》是一款Meta游戏，玩家需要通过对抗旁白的指示，思考何为游戏、何为真相等等命题。在《ICEY》中，你扮演的是ICEY，而非“你自己”。当然你也可以扮演“你自己”，而非ICEY。这是一个包裹着动作游戏外衣的陷阱。 \n', 'http://img.dmdream.cn/game/1032cecd-0dce-457d-a256-c06661834a20.jpg?imageslim,http://img.dmdream.cn/game/92d40156-e2f1-4cc2-a143-ed1fbefd6190.jpg?imageslim,http://img.dmdream.cn/game/edf8f8b9-960c-4fb4-a745-ba7afd97779f.jpg?imageslim,http://img.dmdream.cn/game/497a4a95-b9dc-4544-87b6-384520510cbd.jpg?imageslim,http://img.dmdream.cn/game/fb042312-93af-4255-bee6-df72dcb7711c.jpg?imageslim', 1, 18.00, NULL, '2018-12-15');
INSERT INTO `tab_game` VALUES (40, '2019-06-28 10:14:06', NULL, 1, 1, '文明重启（测试服）', 'http://img.dmdream.cn/game/f5e92304-fa03-46b9-977c-92fbda71131b.png?imageslim', '1.1', 10.0, 1, 'http://img.dmdream.cn/game/eb733267-f5e9-4bfd-b298-ddcf170a382b.png?imageslim', NULL, '', 3, 31, 50, '', '文明重启是一款开放生存沙盒游戏，这里是坐落于七大洋海域中的无名岛屿，拥有浩瀚广阔的世界，由于EG抗癌疫苗导致的丧尸病席卷全球，作为幸存者你来到了这里...\n这里岛屿物资匮乏、X研究所开启活体实验，到处充满了掠夺、杀戮、背叛、还有丧尸！\n巨型油轮上的X研究所总部发起了”文明重启“计划第2阶段，各地岛屿将于15天后进行毁灭，你需要变得更为强大并逃离至其他岛屿。', 'http://img.dmdream.cn/game/e7ac2a26-d287-4b97-a3ad-a9ab266f3290.jpg?imageslim,http://img.dmdream.cn/game/5373418a-ce00-4794-9356-0996ad263007.jpg?imageslim,http://img.dmdream.cn/game/dd6f8744-12d5-4a41-9348-0eebd80debf8.jpg?imageslim,http://img.dmdream.cn/game/5c00bb9f-3f11-4a46-84b8-d4acf2eefe08.jpg?imageslim,http://img.dmdream.cn/game/7a106337-514e-4863-aafb-9f64790e4b70.jpg?imageslim,http://img.dmdream.cn/game/83503af0-3a7e-4cd9-b884-4b5ae18de67a.jpg?imageslim', NULL, NULL, NULL, '2019-03-26');
INSERT INTO `tab_game` VALUES (48, '2019-06-28 13:41:26', NULL, 1, 1, '三国志汉末霸业', 'http://img.dmdream.cn/game/fd9a6a3a-d34c-41d1-a9de-76e3cf93e505.png?imageslim', '0.9.4', 10.0, 1, 'http://img.dmdream.cn/game/43f3888c-d7a9-4115-bd0d-dd92c624fc4e.png?imageslim', NULL, '', 7, 32, 47, '', '【三国志:汉末霸业】是由成都龙游天下工作室精心制作的一款回合制君主扮演模式的SLG游戏。主要是以国人自己对三国游戏的新思维，新角度来打造不一样的三国世界。在回归传统策略类的基础上深度刻画了三国各城市之间的地域差异以及每个武将自身能力和特性的异同，而在战斗方面突出结合天时地利人和的调配来进行作战的魅力。', 'http://img.dmdream.cn/game/502adc47-550e-4db6-a885-3c6b16df6d19.jpg?imageslim,http://img.dmdream.cn/game/dc95d430-e189-4f92-bf6a-3c562644d7f1.jpg?imageslim,http://img.dmdream.cn/game/5bc2e562-254a-4198-93d1-cd0823d524a7.jpg?imageslim,http://img.dmdream.cn/game/966f2c61-f117-4be4-87de-8dd2c7272cca.jpg?imageslim,http://img.dmdream.cn/game/55dab31a-29e0-4866-9fac-f743d2f88c01.jpg?imageslim', 1, 68.00, NULL, '2018-11-08');
INSERT INTO `tab_game` VALUES (39, '2019-06-28 10:06:17', NULL, 1, 1, '香肠派对', 'http://img.dmdream.cn/game/2e02790e-5fed-4aee-bd0a-8dd24244a205.png?imageslim', '7.68', 10.0, 1, 'http://img.dmdream.cn/game/ebb48b50-2da3-44c9-836e-d140ecc2d108.png?imageslim', NULL, '', 7, 30, 47, '', '香肠派对》是一款画风激萌、玩法独特的生存射击类手游。游戏虽小，但内容不简单。\n\n游戏中您能体会到酣畅、硬核的战斗系统。游戏中的枪械有着真实的弹道下坠、屏息系统；同时，游戏战场中包含信号枪、复活机系统，考验着队伍间的默契程度。\n', 'http://img.dmdream.cn/game/c5c8c5df-55bb-4a30-81b2-3b945e14b49a.jpg?imageslim,http://img.dmdream.cn/game/39bd8df3-fa16-497d-a16e-15b580041ed1.jpg?imageslim,http://img.dmdream.cn/game/979f0397-171d-45f2-bbfe-673faaee4fd7.jpg?imageslim,http://img.dmdream.cn/game/f7c2bede-a425-4d2d-ac18-e99d1ce155d6.jpg?imageslim,http://img.dmdream.cn/game/3c45334f-8fab-4c77-840f-ecbac3442bb7.jpg?imageslim', NULL, NULL, NULL, '2019-05-27');
INSERT INTO `tab_game` VALUES (33, '2019-06-28 09:39:57', NULL, 1, 1, '暖风捕鱼日：2048猫岛', 'http://img.dmdream.cn/game/4812aae9-7d45-4e2b-b1d4-74982f177654.png?imageslim', '1.0.4', 10.0, NULL, '', NULL, '', 7, 6, 49, '', '夏日来临，外面太阳热辣辣的，正是外出海钓的好季节！\n心怀梦想的小老鼠嘟儿，决定找一个安静美丽的小岛，拓展自己的海岛事业，一边钓鱼鱼一边养猫猫。\n鲜美的海鱼和令人着迷的海岛，想想就令人热血澎湃，来，拿起小鱼竿，一起拓建美丽的家园吧~\n', 'http://img.dmdream.cn/game/8f6a8f2a-99a6-4609-bd44-ef9da4d3471b.jpg?imageslim,http://img.dmdream.cn/game/b3988416-46d3-4d67-ae81-ba8ea296fc4d.jpg?imageslim,http://img.dmdream.cn/game/2622e43d-e764-43af-9f81-cee64db1d05d.jpg?imageslim,http://img.dmdream.cn/game/40e6d781-ecc5-410a-8914-fa85f900a0d9.jpg?imageslim,http://img.dmdream.cn/game/4afec8f3-7e36-4354-bb29-1deefd3c6d92.jpg?imageslim', NULL, NULL, NULL, '2019-06-20');
INSERT INTO `tab_game` VALUES (34, '2019-06-28 09:45:37', NULL, 1, 1, '多多自走棋', 'http://img.dmdream.cn/game/f37816bd-e917-4a90-8efe-50d4de10e48f.png?imageslim', '0.2.0', 10.0, 1, 'http://img.dmdream.cn/game/7e98175b-2145-493d-9396-30cf74fcd03a.png?imageslim', 1, 'https://apps.apple.com/cn/app/id1460358976', 7, 30, 48, '', '《多多自走棋》是2019年全球最流行的电子竞技游戏。玩家通过搭建个人局内经济体系，使用各具特色的英雄卡牌进行对战，形成别致的八人割据式的战斗局势。无论来自哪里，都可以与世界范围内的顶尖玩家切磋战斗，更有世界顶级电竞联赛等你来战！\n', 'http://img.dmdream.cn/game/8b46c23b-c55a-4674-ba25-2b56b179d019.jpg?imageslim,http://img.dmdream.cn/game/d6817a42-9753-4ada-a3bd-c8b75e540f21.jpg?imageslim,http://img.dmdream.cn/game/7ff0b1f6-0885-4767-98f0-1b601554dc55.jpg?imageslim,http://img.dmdream.cn/game/23bee0bc-f8e6-4ee0-9f10-870c03fe5b25.jpg?imageslim,http://img.dmdream.cn/game/2478f504-3760-4adb-b52d-014f8054cdbf.jpg?imageslim', NULL, NULL, NULL, '2019-06-14');
INSERT INTO `tab_game` VALUES (35, '2019-06-28 09:48:49', NULL, 1, 1, '王者荣耀', 'http://img.dmdream.cn/game/946e219a-2552-45dd-b736-1f925807a996.png?imageslim', '1.45.1.6', 10.0, 1, 'http://img.dmdream.cn/game/fd2ed9c8-52b8-4232-b40d-7c19ee91bd21.png?imageslim', NULL, '', 7, 6, 47, '', '《王者荣耀》是全球首款5V5英雄公平对战手游，腾讯MOBA手游大作！ 作为一款MOBA类游戏，《王者荣耀》特色多多，在同类的游戏中可谓是一枝独秀，艳压全场。\n5V5王者峡谷、5V5深渊大乱斗、以及3V3、1V1等多样模式一键体验，热血竞技尽享快感！海量英雄随心选择，精妙配合默契作战！10秒实时跨区匹配，与好友组队登顶最强王者！操作简单易上手，一血、五杀、超神，还原经典体验！实力操作公平对战，回归MOBA初心！ \n特色一：5v5！越塔强杀！超神！', 'http://img.dmdream.cn/game/915f9949-b85e-4b8f-8374-63bed0d1baa2.jpg?imageslim,http://img.dmdream.cn/game/b7440ff5-21f6-41e6-aba1-3fb7cfb5c804.jpg?imageslim,http://img.dmdream.cn/game/592dfad5-a622-41f7-b0e4-15c565c888d6.jpg?imageslim,http://img.dmdream.cn/game/e55e6b8b-7201-4f74-978a-79ab326cd1c6.jpg?imageslim,http://img.dmdream.cn/game/7d0fdd89-266c-4c69-9427-32e01f2ea556.jpg?imageslim', NULL, NULL, NULL, '2019-06-06');
INSERT INTO `tab_game` VALUES (36, '2019-06-28 09:51:53', NULL, 1, 1, '濡沫江湖', 'http://img.dmdream.cn/game/e4cadb93-44ab-4789-a4fe-263518d2156e.png?imageslim', '0.5.3', 10.0, 1, 'http://img.dmdream.cn/game/43d943da-ae0f-484d-9253-54a8e88b3502.png?imageslim', NULL, '', 3, 30, 2, '', '《濡沫江湖》是继《江湖风云录》之后的又一部单机国风RPG武侠游戏。性格各异的人物形象，幽默风趣的游戏对白，扑朔迷离的剧情发展，经典新奇的游戏玩法，将为你呈现出一个宏大、真实的武林世界。', 'http://img.dmdream.cn/game/9e16b507-1243-4c01-84f7-9e2119b734f7.jpg?imageslim,http://img.dmdream.cn/game/9c52f2d8-bbae-445d-8085-b97d4b6f5f7f.jpg?imageslim,http://img.dmdream.cn/game/aff6eff3-8dbf-48c3-9a48-4dc4ac19f780.jpg?imageslim,http://img.dmdream.cn/game/bb8082a8-27b2-4d3d-890e-f4790ab36b7a.jpg?imageslim,http://img.dmdream.cn/game/a6fe8876-5052-4a63-8915-83b6d37d9c86.jpg?imageslim', NULL, NULL, NULL, '2019-06-05');
INSERT INTO `tab_game` VALUES (37, '2019-06-28 09:55:11', NULL, 1, 1, '明日方舟', 'http://img.dmdream.cn/game/3a2b9e79-1fde-40cc-b941-71fca7867e83.png?imageslim', '0.7.28', 10.0, 1, 'http://img.dmdream.cn/game/8a59d54d-ac42-4efe-8893-e2f2c2070850.png?imageslim', NULL, '', 7, 31, 49, '', '起因不明并四处肆虐的天灾，席卷过的土地上出现了大量的神秘矿物——被人们称为“源石”。虽然源石的发现历史已久，但是依赖于技术的进步，其蕴含的能量使得文明顺利迈入现代，但与此同时，源石本身也催生出“感染者”的存在。', 'http://img.dmdream.cn/game/e23130ce-d2e8-43a5-affe-ba31f4cdf4d9.jpg?imageslim,http://img.dmdream.cn/game/7f622b95-7394-4c5f-9f62-f52f02b6e219.jpg?imageslim,http://img.dmdream.cn/game/1b682e8f-6d13-423d-85d2-81b1d02d1c14.jpg?imageslim,http://img.dmdream.cn/game/4db2ddf2-b704-4e2e-8bb4-c31692f2c630.jpg?imageslim,http://img.dmdream.cn/game/29305a2b-38ea-4ae8-97e2-fefad4577f2a.jpg?imageslim', NULL, NULL, NULL, '2019-05-29');
INSERT INTO `tab_game` VALUES (38, '2019-06-28 09:59:27', NULL, 1, 1, '和平精英', 'http://img.dmdream.cn/game/76bbd3c7-127f-4892-886d-27541a1eccf3.png?imageslim', '1.129', 10.0, 1, 'http://img.dmdream.cn/game/c457dd2c-e360-43e6-a67c-863932aa9c88.png?imageslim', NULL, '', 6, 6, 47, '', '《和平精英》是腾讯光子工作室群自研打造的反恐军事竞赛体验手游。虚幻引擎4研发，次世代完美画质，极致视听感受；超大实景地图，打造指尖战场，全方面自由施展战术；百人同场竞技，真实弹道，完美的射击手感；好友一键组队，语音开黑；腾讯光子工作室群超过300人团队研发，给您带来一场震撼的竞技体验。', 'http://img.dmdream.cn/game/58c62d97-b083-4e01-b6df-032a615bc574.jpg?imageslim,http://img.dmdream.cn/game/27c9bc21-69f5-494c-b5fb-526caea55127.jpg?imageslim,http://img.dmdream.cn/game/cb9c7116-3524-4c25-a90c-1110de06dad5.jpg?imageslim,http://img.dmdream.cn/game/09abdb26-48c7-438b-bd43-2155dbf1a1f0.jpg?imageslim,http://img.dmdream.cn/game/6818adbc-cfaa-4f45-8a50-2f779db9f062.jpg?imageslim,http://img.dmdream.cn/game/814cb961-162f-4d81-b922-6c65d08f4072.jpg?imageslim,http://img.dmdream.cn/game/7509c38a-fdd9-42e1-8d82-175ec685b075.jpg?imageslim,http://img.dmdream.cn/game/8a40927d-a468-408f-a883-9017313dc62d.jpg?imageslim', NULL, NULL, NULL, '2019-05-27');
INSERT INTO `tab_game` VALUES (41, '2019-06-28 10:19:03', NULL, 1, 1, '猫和老鼠：欢乐互动', 'http://img.dmdream.cn/game/c5e68c16-7167-4b2c-839d-c08615dc2098.png?imageslim', '5.0.1', 10.0, 1, 'http://img.dmdream.cn/game/976db2ce-ae1c-425f-a78d-94ea4abac2f0.png?imageslim', NULL, '', 3, 33, 49, '', '猫鼠追逃，尽享互动乐趣。智勇较量，谁能赢得胜利？《猫和老鼠》是由华纳兄弟互动娱乐正版IP授权，网易游戏诚意打造的欢乐互动手游。缤纷角色、花式道具、经典场景，加入猫鼠追逃，享受抓捕与逃脱的欢乐！\n\n实力组合，打造精品手游\n网易游戏，国内自研游戏实力代表之一，在手游自有品牌挖掘和授权IP领域强势发力。', 'http://img.dmdream.cn/game/936adeb2-8c99-4cc2-8e5b-768d04229ca2.jpg?imageslim,http://img.dmdream.cn/game/4ff6e02a-c89d-46de-805b-782f3e54c2a5.jpg?imageslim,http://img.dmdream.cn/game/4bac6e4c-2a26-44c9-a010-07de7b6f7e21.jpg?imageslim,http://img.dmdream.cn/game/d20d745e-cbb1-4538-8630-ba6f38dd3ba4.jpg?imageslim,http://img.dmdream.cn/game/7f34ab7a-19f0-456f-bfb9-4f82e115dd96.jpg?imageslim', NULL, NULL, NULL, '2019-05-31');
INSERT INTO `tab_game` VALUES (42, '2019-06-28 10:26:36', NULL, 1, 1, '弈剑单机版（测试版）', 'http://img.dmdream.cn/game/70cac55a-88ec-4202-8da7-4c44d5d3b5b8.png?imageslim', '0.2', 10.0, 1, 'http://img.dmdream.cn/game/45e0fcf1-30a4-41c0-a61f-eb36306eaecd.png?imageslim', NULL, '', 6, 31, 3, '', '《弈剑》 是一款由unity3d引擎研发的第三人称PVP即时对战游戏。\n\n本次游戏为单机试玩版，并非游戏最终内容效果，仅测试部分对战内容，当前版本没有加入特效与核心玩法规则，UI为临时使用，大家可以帮助我们在以下方面提供建议，联机版将会根据这次的反馈结果进行优化改进。', 'http://img.dmdream.cn/game/9b5ba7f3-1229-41c2-952b-96a99b185ec4.jpg?imageslim,http://img.dmdream.cn/game/cd541f58-25a4-411a-b17d-f443ed3e6229.jpg?imageslim,http://img.dmdream.cn/game/32b796ca-3da6-4e8f-9ec1-669cc191e403.jpg?imageslim,http://img.dmdream.cn/game/158c4644-86af-4e01-b163-e556d913a3f0.jpg?imageslim,http://img.dmdream.cn/game/7f6eccd7-12d2-4322-80a3-e02a1d50592c.jpg?imageslim,http://img.dmdream.cn/game/52669ddc-0dec-444b-9a22-6e63f5cc96b6.jpg?imageslim,http://img.dmdream.cn/game/9e1e2283-5543-4336-9e53-cc76c87bc1f8.jpg?imageslim', NULL, NULL, NULL, '2019-05-27');
INSERT INTO `tab_game` VALUES (43, '2019-06-28 10:43:59', NULL, 1, 1, '皇家骑士：300自走棋', 'http://img.dmdream.cn/game/d0ff05ea-9ac5-4b32-abb0-088821885034.png?imageslim', '1.2.38', 5.0, 1, 'http://img.dmdream.cn/game/650aa109-206d-4d71-b63a-30e71767b457.png?imageslim', NULL, '', -1, 32, 51, '', '公元2077年，300宇宙的平行世界——奥特奇斯（AutoChess）大陆，在这里八位领主各据一方，而他们交界的正中央矗立着一座宏伟的圣殿。\n\n那些曾经战斗在永恒之地的英雄们、圣都学院的适格者们，已经远离战场，安定祥和的生活在这里，守卫着一种神秘的力量。', 'http://img.dmdream.cn/game/19ab6292-bee1-4484-8e16-ed55fe355135.jpg?imageslim,http://img.dmdream.cn/game/e60d65f1-5352-4d01-8105-3df2112cfb36.jpg?imageslim,http://img.dmdream.cn/game/a384e12f-5d9b-46b1-bbc9-4fdc490e0219.jpg?imageslim,http://img.dmdream.cn/game/5e61fd28-52ab-453a-b245-e86ac73d7d74.jpg?imageslim,http://img.dmdream.cn/game/45c0fb7f-5ff0-4a43-a889-2b2a4814d5cb.jpg?imageslim', NULL, NULL, NULL, '2019-03-26');
INSERT INTO `tab_game` VALUES (44, '2019-06-28 10:48:22', NULL, 1, 1, '无尽之魂', 'http://img.dmdream.cn/game/9511a9c0-4715-4ab9-9ff1-cfa1811a4b44.png?imageslim', '1.025', 10.0, 1, 'http://img.dmdream.cn/game/cc874b94-9a1d-45af-807f-3e014b532366.png?imageslim', NULL, '', -1, 39, 2, '', '独立硬核游戏，Lowpoly风格的 ARPG.\n游戏很难！！没错，非常难！！用可爱的玩家的话就是：“WC，这是什么鬼难度？！”\n虽然很难，但是熟悉套路后是能轻松过关，而且为了照顾手残向玩家，提供商店可以购买升级装备！！\n再次说明，游戏非常难！！下载测试的玩家请做好心理准备！！！', 'http://img.dmdream.cn/game/246385b5-b00f-4456-a6a2-6db4bddbd93f.jpg?imageslim,http://img.dmdream.cn/game/cb59053a-e177-4d3b-bf86-5d75f766c7d4.jpg?imageslim,http://img.dmdream.cn/game/99f7957c-1d9d-4b80-a71c-7f0ecef6a826.jpg?imageslim,http://img.dmdream.cn/game/d4414602-0f51-406f-adff-df0d1cc7067d.jpg?imageslim,http://img.dmdream.cn/game/869b012f-1cd1-450e-9243-8f0ac0faa1c7.jpg?imageslim,http://img.dmdream.cn/game/c1a1d0e7-e0c8-4eab-9879-c515e10be470.jpg?imageslim', NULL, NULL, NULL, '2019-03-07');
INSERT INTO `tab_game` VALUES (45, '2019-06-28 10:57:25', NULL, 1, 1, '弹力果冻', 'http://img.dmdream.cn/game/36c5c3e6-9971-4385-87b0-0d0efda1dd39.png?imageslim', '0.4.3', 10.0, 1, 'http://img.dmdream.cn/game/799ac5cb-8bd4-4495-84cd-56f8d0afe59e.png?imageslim', NULL, '', -1, 39, 49, '', '游戏特色：\n携带多个萌宠与各种萌丑的小怪兽战斗，各种不同类型的武器让你欲罢不能，方糖功能是最大的游戏特色，拥有最多方糖的你就是最强的养成类型的游戏。\n', 'http://img.dmdream.cn/game/7056dcbe-cb6d-4007-a2ff-48e521f93ce0.jpg?imageslim,http://img.dmdream.cn/game/526d7c70-0ea6-4e2d-a1c2-ae67a58cd6b8.jpg?imageslim,http://img.dmdream.cn/game/d2a5d9f3-a6e0-43ad-9c6f-a8ce1c210696.jpg?imageslim,http://img.dmdream.cn/game/62bb585d-3660-4b5d-ab47-f5daee8ea794.jpg?imageslim,http://img.dmdream.cn/game/d66e95a5-374c-4514-ba93-12dd05b95896.jpg?imageslim,http://img.dmdream.cn/game/90611f7e-6398-4617-83d8-0179ddbb9216.jpg?imageslim,http://img.dmdream.cn/game/149fd57f-6628-4417-8f19-4bf59ff54118.jpg?imageslim', NULL, NULL, NULL, '2019-03-16');
INSERT INTO `tab_game` VALUES (49, '2019-07-04 20:41:31', NULL, 1, 1, '双子', 'http://img.dmdream.cn/game/1bdfa050-dc77-4272-bf7e-cea573580c46.png?imageslim', '1.15', 10.0, 1, 'http://img.dmdream.cn/game/066d6a6f-fdff-432f-933b-3d3e73a69285.png?imageslim', NULL, '', 8, 37, 2, '', '游戏是由回音石工作室Echostone Games 独立开发并由心动网络 XD.com 发行的一款情感体验类游戏\n\n无需联网无内购，一次购买畅玩不尽\n\n2017 TapTap 年度最佳音乐提名\n', 'http://img.dmdream.cn/game/6aeb05a3-6a8b-439e-97d0-617070de44a3.jpg?imageslim,http://img.dmdream.cn/game/9ca0038d-133d-4a90-8e40-2656ba94331f.jpg?imageslim,http://img.dmdream.cn/game/bbc1b571-b6b5-4636-9f51-f9f863fcf807.jpg?imageslim,http://img.dmdream.cn/game/05e02e5a-e770-4da3-842d-9d9f26d3a2d7.jpg?imageslim,http://img.dmdream.cn/game/caeaa3dc-e0d3-49b9-88a1-f8e64dfbf6eb.jpg?imageslim', 1, 22.00, NULL, '2019-03-08');
INSERT INTO `tab_game` VALUES (50, '2019-06-28 13:53:36', NULL, 1, 1, '纪念碑谷（付费版）', 'http://img.dmdream.cn/game/1059dba8-b300-4416-953b-20de09d430dd.png?imageslim', '2.37', 10.0, 1, 'http://img.dmdream.cn/game/73d4cba9-8b93-4060-a5d1-5090bb9fdd27.png?imageslim', NULL, '', 6, 32, 49, '', '《纪念碑谷》是一款具有艺术品级画面的解谜游戏，用有趣的空间错位感交织出了清新唯美的迷宫世界，带来了一次神奇建筑与奇妙几何体相结合的梦幻探险。通过探索隐藏小路、发现视力错觉以及击败神秘的乌鸦人来帮助沉默公主艾达走出纪念碑迷阵。作品中采用了转移、旋转、颠倒等解谜手法，而且如幻的境界让人仿佛如梦其中。', 'http://img.dmdream.cn/game/171e4185-3631-4c84-94fe-5121d777c296.jpg?imageslim,http://img.dmdream.cn/game/f9f52187-4b29-427c-9c75-5e4042d257d1.jpg?imageslim,http://img.dmdream.cn/game/06247279-822f-4e3b-a01a-795feae36865.jpg?imageslim,http://img.dmdream.cn/game/d6760a02-8c6f-432e-be2c-8bee3cb5fc3d.jpg?imageslim,http://img.dmdream.cn/game/07efe4a9-d4d8-4620-ba37-fa9bff38889e.jpg?imageslim', 1, 25.00, NULL, '2019-06-03');
INSERT INTO `tab_game` VALUES (51, '2019-06-28 13:55:39', NULL, 1, 1, '字母人', 'http://img.dmdream.cn/game/34c5f1ce-a3ce-4ccd-8624-2dca1b20dde0.png?imageslim', '1.0', 10.0, 1, 'http://img.dmdream.cn/game/49061910-1578-4d54-9989-804ddeb2b2d0.png?imageslim', NULL, '', 6, 32, 2, '', '改变文字，改变世界。\n\n【故事背景】\n黑暗的深处传来凄厉的嚎叫，翻滚的雾气从洞穴中涌出，退路是绝地，前路是荆棘。\n这是哪里？缺失的手臂在哪里？ \n微弱的光芒到底是闪烁的希望还是沾满着毒液的獠牙？', 'http://img.dmdream.cn/game/b6e6bcb1-9889-4c53-9b76-c65324b284b9.jpg?imageslim,http://img.dmdream.cn/game/4bca2b88-88da-4c76-8936-67765facd9d2.jpg?imageslim,http://img.dmdream.cn/game/986e5b0c-06c8-4364-802e-0dd234bc0bb3.jpg?imageslim,http://img.dmdream.cn/game/fc989dc5-e78f-42ff-bb30-7893c7051d20.jpg?imageslim,http://img.dmdream.cn/game/16e5211f-b5be-4b6f-9082-d1131e7040f9.jpg?imageslim', 1, 12.00, NULL, '2019-06-03');
INSERT INTO `tab_game` VALUES (52, '2019-07-03 14:26:10', NULL, 1, 0, '魔兽争霸', 'http://img.dmdream.cn/game/206cd175-6052-4a59-9324-6b8048d78321.png?imageslim', '3.0', 10.0, 1, 'http://img.dmdream.cn/game/e49df58e-7d98-46bd-b997-8ec3d7ae1e60.apk?imageslim', 1, 'https://search.bilibili.com/all?keyword=game%20core', 3, 32, 2, 'IPhoneX以上', '率领众多英灵，夺回人类的未来，前所未有的盛大战斗故事，拉开帷幕！\n率领众多英灵，夺回人类的未来，前所未有的盛大战斗故事，拉开帷幕！', 'http://img.dmdream.cn/game/8c7fe85c-716b-4e36-a870-1f9d413df8c1.mp4?imageslim,http://img.dmdream.cn/game/5a5f9160-475d-4d76-972a-3771e9e97867.png?imageslim,http://img.dmdream.cn/game/c824d678-12b1-48ef-a69c-7daf08b553dc.png?imageslim', 1, 123.00, NULL, '2019-06-06');
INSERT INTO `tab_game` VALUES (53, '2019-07-03 14:25:56', NULL, 1, 0, '测试', 'http://img.dmdream.cn/game/c2849c8c-a330-402f-a745-b5a5cda9bc14.png?imageslim', '3.0', 10.0, NULL, '', NULL, '', 3, 37, 51, 'IPhoneX以上', '无\n率领众多英灵，夺回人类的未来，前所未有的盛大战斗故事，拉开帷幕！\n率领众多英灵，夺回人类的未来，前所未有的盛大战斗故事，拉开帷幕！', 'http://img.dmdream.cn/game/b2d3621c-a73f-4a2a-94e1-3f9510f57713.png?imageslim', 1, 1.00, NULL, '2019-06-05');
INSERT INTO `tab_game` VALUES (54, '2019-07-03 14:25:05', NULL, 1, 0, '魔兽争霸', 'http://img.dmdream.cn/game/eaf18ebf-e576-4ede-9f02-ed51cca48fd3.jpg?imageslim', '3.0', 10.0, 1, 'http://img.dmdream.cn/game/e8fb65cd-1ac5-4696-9421-56aaf3a29a5d.apk?imageslim', NULL, '', 7, 32, 2, 'IPhoneX以上', '好玩\n率领众多英灵，夺回人类的未来，前所未有的盛大战斗故事，拉开帷幕！', 'http://img.dmdream.cn/game/be1237a7-80fa-4ac4-a899-9e7745d34f45.png?imageslim,http://img.dmdream.cn/game/185b0a84-d035-41b6-ad10-9e6de924d21e.png?imageslim', 1, 12.00, NULL, '2019-06-01');

-- ----------------------------
-- Table structure for tab_operator
-- ----------------------------
DROP TABLE IF EXISTS `tab_operator`;
CREATE TABLE `tab_operator`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `createTime` datetime(0) NOT NULL COMMENT '自动生成的创建时间',
  `destroyTIme` datetime(0) NULL DEFAULT NULL COMMENT 'isValid=0时使用',
  `version` int(11) NOT NULL COMMENT '乐观锁版本号,初始化1',
  `isValid` int(11) NOT NULL COMMENT '是否启用,默认1:启用 0:不启用',
  `gmOperatorName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运营商名称',
  `gmOperatorAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运营商地址',
  `gmOperatorWebsite` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运营商网站',
  `gmOperatorIntro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运营商简介',
  `gmOperatorLinkman` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运营商联系人姓名',
  `gmOperatorPhone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运营商联系人电话',
  `gmOperatorAlipay` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运营商支付宝',
  `gmOperatorWechat` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运营商微信',
  `gmOperatorCredit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运营商银行卡号(用于游戏付费前钱的支付)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tab_operator
-- ----------------------------
INSERT INTO `tab_operator` VALUES (7, '2019-07-03 15:00:32', NULL, 1, 1, '腾讯', '深圳', 'www.qq.com', '氪金才能变强', '麻花疼', '18985858585', '18585858585', '18585858585', '6215611202545215215');
INSERT INTO `tab_operator` VALUES (3, '2019-07-03 15:08:04', NULL, 1, 1, '网易', '杭州', 'www.163.com', '这里是网易', '布吉岛', '18548484848', '18548484848', '18548484848', '1854848484818548484848');
INSERT INTO `tab_operator` VALUES (6, '2019-07-03 15:09:38', NULL, 1, 1, '盛大', '上海', 'www.sdo.com', '老牌游戏公司', '布吉岛', '18595959595', '18595959595', '18595959595', '1859595959518595959595');
INSERT INTO `tab_operator` VALUES (8, '2019-07-03 15:10:58', NULL, 1, 1, 'Bilibili游戏', '上海', 'www.biligame.com', '无良企业?', '陈坤', '18656565656', '18656565656', '18656565656', '1865656565618656565656');

-- ----------------------------
-- Table structure for tab_order
-- ----------------------------
DROP TABLE IF EXISTS `tab_order`;
CREATE TABLE `tab_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `createTime` datetime(0) NOT NULL COMMENT '自动生成的创建时间',
  `destroyTIme` datetime(0) NULL DEFAULT NULL COMMENT 'isValid=0时使用',
  `version` int(11) NOT NULL COMMENT '乐观锁版本号,初始化1',
  `isValid` int(11) NOT NULL COMMENT '是否启用,默认1:启用 0:不启用',
  `gmOrderId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号(不同于主键,uuid)',
  `gmOrderPrice` decimal(10, 2) NULL DEFAULT NULL COMMENT '订单金额',
  `gmPayment` int(1) NULL DEFAULT NULL COMMENT '支付方式 微信0/支付宝1',
  `gmOrderPerformance` int(1) NULL DEFAULT NULL COMMENT '订单完成情况0:未完成 1:已完成',
  `gmGameId` int(11) NULL DEFAULT NULL COMMENT '购买的游戏id',
  `gmPurchaserId` int(11) NULL DEFAULT NULL COMMENT '购买的用户id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FkGmGameId`(`gmGameId`) USING BTREE,
  INDEX `FkGmPurchaserId`(`gmPurchaserId`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tab_order
-- ----------------------------
INSERT INTO `tab_order` VALUES (17, '2019-07-02 14:01:08', NULL, 1, 1, '1000000', 200.00, 1, 0, 47, 2);
INSERT INTO `tab_order` VALUES (2, '2019-07-02 11:23:48', '2019-08-05 00:00:00', 1, 1, '1', 14.52, 0, 1, 48, 1);
INSERT INTO `tab_order` VALUES (3, '2019-07-02 11:25:40', '2019-08-05 00:00:00', 1, 1, '1', 4.55, 0, 1, 49, 1);
INSERT INTO `tab_order` VALUES (4, '2019-07-02 11:27:33', '2019-08-05 00:00:00', 1, 1, '1', 4.55, 0, 0, 50, 1);
INSERT INTO `tab_order` VALUES (19, '2019-07-03 15:45:12', NULL, 1, 1, '123', 12.00, 0, 1, 53, 1);
INSERT INTO `tab_order` VALUES (11, '2019-07-02 10:22:30', NULL, 1, 1, '1', 114.50, 0, 2, 52, 1);
INSERT INTO `tab_order` VALUES (12, '2019-07-02 10:54:25', NULL, 1, 1, '1', 210.00, 0, 1, 53, 1);
INSERT INTO `tab_order` VALUES (20, '2019-07-05 02:02:27', NULL, 1, 1, '123123123', 10.00, 10, 1, 46, 21);
INSERT INTO `tab_order` VALUES (21, '2019-07-05 09:17:00', NULL, 1, 1, '3204c62f-c9bf-47d9-801a-4fd6b920210c', 1.00, 1, 0, 55, 21);
INSERT INTO `tab_order` VALUES (22, '2019-07-05 09:20:40', NULL, 1, 1, '334495ff-1f80-49da-90d0-69dd89c53752', 1.00, 1, 0, 55, 21);
INSERT INTO `tab_order` VALUES (23, '2019-07-05 09:45:58', NULL, 1, 1, '4038a619-f8ee-4f31-9b8b-225e43f2f111', 25.00, 1, 1, 50, 21);

-- ----------------------------
-- Table structure for tab_publisher
-- ----------------------------
DROP TABLE IF EXISTS `tab_publisher`;
CREATE TABLE `tab_publisher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `createTime` datetime(0) NOT NULL COMMENT '自动生成的创建时间',
  `destroyTIme` datetime(0) NULL DEFAULT NULL COMMENT 'isValid=0时使用',
  `version` int(11) NOT NULL COMMENT '乐观锁版本号,初始化1',
  `isValid` int(11) NOT NULL COMMENT '是否启用,默认1:启用 0:不启用',
  `gmPublisherName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发行商名称',
  `gmPublisherAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发行商地址',
  `gmPublisherWebsite` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发行商网站',
  `gmPublisherIntro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发行商简介',
  `gmPublisherLinkman` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发行商联系人姓名',
  `gmPublisherPhone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发行商联系人电话',
  `gmPublisherAlipay` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发行商支付宝',
  `gmPublisherWechat` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发行商微信',
  `gmPublisherCredit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发行商银行卡号(用于游戏付费前钱的支付)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 65 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tab_publisher
-- ----------------------------
INSERT INTO `tab_publisher` VALUES (38, '2019-07-02 10:32:59', NULL, 1, 0, '阿里', 'qwrasf', 'asfasf', 'asfasf', '刘强东sb', '17326075238', 'qwarwarf', 'asfasf', '142e234rw3r');
INSERT INTO `tab_publisher` VALUES (32, '2019-07-03 15:58:11', NULL, 1, 1, '阿里123', '凄凄切切', 'asfasf', 'asfasf', '刘强东2', '17326075238', 'qwarwarf', 'asfasf', '142e234rw3r');
INSERT INTO `tab_publisher` VALUES (37, '2019-07-03 15:57:53', NULL, 1, 1, '阿里', '呜呜呜呜', 'asfasf', 'asfasf', '刘强东', '17326075238', 'qwarwarf', 'asfasf', '142e234rw3r');
INSERT INTO `tab_publisher` VALUES (50, '2019-06-27 10:22:13', NULL, 1, 0, '阿里', 'qwrasf', 'asfasf', 'asfasf', '刘强东', '17326075238', 'qwarwarf', 'asfasf', '142e234rw3r');
INSERT INTO `tab_publisher` VALUES (63, '2019-07-03 15:58:33', NULL, 1, 1, 'tianmao', 'sdfdsg', 'zdfsef', 'srfsdf', '雷军', '17326075238', '124235', 'asfsdf', '124235w');
INSERT INTO `tab_publisher` VALUES (57, '2019-06-28 16:20:09', NULL, 1, 1, '阿里', 'qwrasf', 'asfasf', 'asfasf', '刘强东dasd', '17326075238', 'qwarwarf', 'asfasf', '142e234rw3r');

-- ----------------------------
-- Table structure for tab_record
-- ----------------------------
DROP TABLE IF EXISTS `tab_record`;
CREATE TABLE `tab_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `createTime` datetime(0) NOT NULL COMMENT '自动生成的创建时间',
  `destroyTIme` datetime(0) NULL DEFAULT NULL COMMENT 'isValid=0时使用',
  `version` int(11) NOT NULL COMMENT '乐观锁版本号,初始化1',
  `isValid` int(11) NOT NULL COMMENT '是否启用,默认1:启用 0:不启用',
  `gmPurchaserId` int(11) NULL DEFAULT NULL COMMENT '购买者id',
  `gmGameId` int(11) NULL DEFAULT NULL COMMENT '购买的游戏的id',
  `gmOrderId` int(11) NULL DEFAULT NULL COMMENT '生成的消费订单的id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FkGmPurchaserId`(`gmPurchaserId`) USING BTREE,
  INDEX `FkGmGameId`(`gmGameId`) USING BTREE,
  INDEX `FkGmOrderId`(`gmOrderId`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for tab_reply
-- ----------------------------
DROP TABLE IF EXISTS `tab_reply`;
CREATE TABLE `tab_reply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `createTime` datetime(0) NOT NULL COMMENT '自动生成的创建时间',
  `destroyTIme` datetime(0) NULL DEFAULT NULL COMMENT 'isValid=0时使用',
  `version` int(11) NOT NULL COMMENT '乐观锁版本号,初始化1',
  `isValid` int(11) NOT NULL COMMENT '是否启用,默认1:启用 0:不启用',
  `replierId` int(11) NULL DEFAULT NULL COMMENT '回复人',
  `toCommentId` int(11) NULL DEFAULT NULL COMMENT '被回复的评论的id',
  `toReplyId` int(11) NULL DEFAULT NULL COMMENT '回复对象',
  `favor` int(11) NULL DEFAULT NULL COMMENT '好评数',
  `against` int(11) NULL DEFAULT NULL COMMENT '反对数',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tab_reply
-- ----------------------------
INSERT INTO `tab_reply` VALUES (1, '2019-06-26 09:22:36', NULL, 1, 1, 1, 1, NULL, 15, 30, '哇，这游戏是真的弱智');
INSERT INTO `tab_reply` VALUES (2, '2019-06-26 09:22:36', NULL, 1, 1, 3, 2, NULL, 15, 29, '这游戏一般般');
INSERT INTO `tab_reply` VALUES (3, '2019-07-01 17:11:25', NULL, 1, 1, 20, 7, NULL, 0, 0, '看了就爱上了付款记录框架');
INSERT INTO `tab_reply` VALUES (4, '2019-07-01 17:13:39', NULL, 1, 1, 20, 7, NULL, 0, 0, '老咔叽分厘卡圣诞节赖库');
INSERT INTO `tab_reply` VALUES (5, '2019-07-02 11:18:20', NULL, 1, 1, 20, 1, NULL, 0, 0, 'kljdaflkj ');
INSERT INTO `tab_reply` VALUES (6, '2019-07-02 15:07:41', NULL, 1, 1, 20, 10, NULL, 0, 0, 'zsssssbbbb');
INSERT INTO `tab_reply` VALUES (7, '2019-07-02 15:07:54', NULL, 1, 1, 20, 10, NULL, 0, 0, 'cnm');
INSERT INTO `tab_reply` VALUES (8, '2019-07-03 13:53:26', NULL, 1, 1, 20, 11, NULL, 0, 0, '铁憨憨');
INSERT INTO `tab_reply` VALUES (9, '2019-07-03 15:38:20', NULL, 1, 1, 20, 1, NULL, 0, 0, '12312312312312312');
INSERT INTO `tab_reply` VALUES (10, '2019-07-03 15:38:31', NULL, 1, 1, 20, 1, NULL, 0, 0, 'asdfasdf ');
INSERT INTO `tab_reply` VALUES (11, '2019-07-05 01:43:18', NULL, 1, 1, 21, 14, NULL, 0, 0, '啊实打实大');
INSERT INTO `tab_reply` VALUES (12, '2019-07-05 01:43:36', NULL, 1, 1, 21, 14, NULL, 0, 0, '不错很棒这里内层');
INSERT INTO `tab_reply` VALUES (13, '2019-07-05 09:45:24', NULL, 1, 1, 21, 18, NULL, 0, 0, '232323232');

-- ----------------------------
-- Table structure for tab_tag
-- ----------------------------
DROP TABLE IF EXISTS `tab_tag`;
CREATE TABLE `tab_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `createTime` datetime(0) NOT NULL,
  `destroyTime` datetime(0) NULL DEFAULT NULL,
  `version` int(11) NOT NULL,
  `isValid` int(1) NOT NULL,
  `gmTagName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签名称',
  `gmGameId` int(11) NULL DEFAULT NULL COMMENT '外键，标签所属游戏id',
  `gmTagOwnerId` int(11) NULL DEFAULT NULL COMMENT '外键，标签创建人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tab_test
-- ----------------------------
DROP TABLE IF EXISTS `tab_test`;
CREATE TABLE `tab_test`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tab_test
-- ----------------------------
INSERT INTO `tab_test` VALUES (1, 'yoko', NULL);
INSERT INTO `tab_test` VALUES (2, 'yoko2', NULL);
INSERT INTO `tab_test` VALUES (3, 'yoko3', '2019-06-20 20:27:07');
INSERT INTO `tab_test` VALUES (4, 'yoko4', '2019-06-20 20:32:33');
INSERT INTO `tab_test` VALUES (5, 'yoko5', '2019-06-20 20:38:03');
INSERT INTO `tab_test` VALUES (6, 'yoko6', '2019-06-20 20:42:41');
INSERT INTO `tab_test` VALUES (7, 'yoko7', '2019-06-20 20:43:34');
INSERT INTO `tab_test` VALUES (8, 'yoko8', '2019-06-20 20:46:40');
INSERT INTO `tab_test` VALUES (9, 'yoko8', '2019-06-21 01:48:27');
INSERT INTO `tab_test` VALUES (10, 'yoko8', '2019-06-21 01:50:39');
INSERT INTO `tab_test` VALUES (11, 'yoko8', '2019-06-21 01:51:42');
INSERT INTO `tab_test` VALUES (12, 'yoko8', '2019-06-21 09:52:30');
INSERT INTO `tab_test` VALUES (13, 'yoko9', '2019-06-21 09:55:41');

-- ----------------------------
-- Table structure for tab_type
-- ----------------------------
DROP TABLE IF EXISTS `tab_type`;
CREATE TABLE `tab_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `createTime` datetime(0) NOT NULL COMMENT '自动生成的创建时间',
  `destroyTIme` datetime(0) NULL DEFAULT NULL COMMENT 'isValid=0时使用',
  `version` int(11) NOT NULL COMMENT '乐观锁版本号,初始化1',
  `isValid` int(11) NOT NULL COMMENT '是否启用,默认1:启用 0:不启用',
  `gmTypeEnName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型英文缩写',
  `gmTypeChName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型中文名称',
  `gmImgDemo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '样例图片路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tab_type
-- ----------------------------
INSERT INTO `tab_type` VALUES (2, '2019-06-23 14:57:07', NULL, 1, 1, 'AVG', '冒险游戏', 'http://img.dmdream.cn/game/e4cadb93-44ab-4789-a4fe-263518d2156e.png?imageslim');
INSERT INTO `tab_type` VALUES (3, '2019-06-23 14:57:36', NULL, 1, 1, 'ACT', '动作游戏', 'http://img.dmdream.cn/game/2e02790e-5fed-4aee-bd0a-8dd24244a205.png?imageslim');
INSERT INTO `tab_type` VALUES (51, '2019-06-26 09:49:01', NULL, 1, 1, 'DG', '二次元', 'http://img.dmdream.cn/game/1f1a6c18-d7b3-4b41-ae97-7ea5ee94e880.jpg?imageslim');
INSERT INTO `tab_type` VALUES (52, '2019-06-26 09:49:24', NULL, 1, 1, 'JS', '竞速赛车', 'http://img.dmdream.cn/game/36c5c3e6-9971-4385-87b0-0d0efda1dd39.png?imageslim');
INSERT INTO `tab_type` VALUES (47, '2019-06-26 09:43:16', NULL, 1, 1, 'MOBA', '在线战争', 'http://img.dmdream.cn/game/946e219a-2552-45dd-b736-1f925807a996.png?imageslim');
INSERT INTO `tab_type` VALUES (48, '2019-06-26 09:43:35', NULL, 1, 1, 'KP', '卡牌游戏', 'http://img.dmdream.cn/game/d0ff05ea-9ac5-4b32-abb0-088821885034.png?imageslim');
INSERT INTO `tab_type` VALUES (49, '2019-06-26 09:44:07', NULL, 1, 1, 'YZ', '益智策略', 'http://img.dmdream.cn/game/1059dba8-b300-4416-953b-20de09d430dd.png?imageslim');
INSERT INTO `tab_type` VALUES (50, '2019-06-26 09:48:44', NULL, 1, 1, 'MN', '模拟生存', 'http://img.dmdream.cn/game/f5e92304-fa03-46b9-977c-92fbda71131b.png?imageslim');

-- ----------------------------
-- Table structure for tab_user
-- ----------------------------
DROP TABLE IF EXISTS `tab_user`;
CREATE TABLE `tab_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `createTime` datetime(0) NOT NULL COMMENT '自动生成的创建时间',
  `destroyTIme` datetime(0) NULL DEFAULT NULL COMMENT 'isValid=0时使用',
  `version` int(11) NOT NULL COMMENT '乐观锁版本号,初始化1',
  `isValid` int(11) NOT NULL COMMENT '是否启用,默认1:启用 0:不启用',
  `gmUsername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名(昵称)',
  `gmUserPhone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `gmUserQQ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联QQ',
  `gmUserWechat` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联微信/appid',
  `gmDeveloperId` int(11) NULL DEFAULT NULL COMMENT '开发者Id（外键）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `gmUserPhone`(`gmUserPhone`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tab_user
-- ----------------------------
INSERT INTO `tab_user` VALUES (1, '2019-07-03 10:17:01', NULL, 1, 1, '小红', '18989849259', '1224149316', 'cj1224149313', NULL);
INSERT INTO `tab_user` VALUES (2, '2019-07-03 10:20:48', NULL, 1, 1, '小明', '133333111133', '1224149316', 'cj1224149311', NULL);
INSERT INTO `tab_user` VALUES (3, '2019-07-03 10:20:55', NULL, 1, 1, '小花', '13221785878', '1224149316', 'ls1224149232', NULL);
INSERT INTO `tab_user` VALUES (4, '2019-07-03 10:21:37', NULL, 1, 1, '木冰眉', '132217858781', '1224149316', 'ls1224149316', NULL);
INSERT INTO `tab_user` VALUES (5, '2019-07-03 10:21:14', NULL, 1, 1, '红蝶', '132217858782', '1224149316', 'ls1224149316', NULL);
INSERT INTO `tab_user` VALUES (16, '2019-06-25 11:09:42', NULL, 1, 1, '王五', '110', '12414514844', 'ww13131', NULL);
INSERT INTO `tab_user` VALUES (19, '2019-06-25 14:06:40', NULL, 1, 1, '夏佳恒', '17326075238', '111241424152', 'xjh19971110', NULL);
INSERT INTO `tab_user` VALUES (18, '2019-06-25 11:41:25', NULL, 1, 1, '赵六', '13332122454', '1224149311', 'zl1224149311', NULL);
INSERT INTO `tab_user` VALUES (20, '2019-06-25 14:08:26', NULL, 1, 1, '徐寅杰', '17357815996', '133124142145', 'xyj6487123', NULL);
INSERT INTO `tab_user` VALUES (21, '2019-07-03 10:03:28', NULL, 1, 1, 'yoko', '18989849258', '940768250', '18989849258', 38);

-- ----------------------------
-- Table structure for tab_userInfo
-- ----------------------------
DROP TABLE IF EXISTS `tab_userInfo`;
CREATE TABLE `tab_userInfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createTime` datetime(0) NOT NULL,
  `destroyTime` datetime(0) NULL DEFAULT NULL,
  `version` int(11) NOT NULL,
  `isValid` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `userPassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userBirthday` date NULL DEFAULT NULL,
  `userGender` int(1) NULL DEFAULT NULL,
  `userCountry` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userIntroduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userRealname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userIDCard` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userPic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tab_userInfo
-- ----------------------------
INSERT INTO `tab_userInfo` VALUES (1, '2019-06-24 10:07:39', NULL, 1, 1, 1, '123', NULL, NULL, NULL, '夏佳恒冬瓜皮', NULL, NULL, '', 'http://img.dmdream.cn/game/8b253649-f233-4d54-927c-ea7e3223a55c.png?imageslim');
INSERT INTO `tab_userInfo` VALUES (2, '2019-06-24 10:07:39', NULL, 1, 1, 2, '123', NULL, NULL, NULL, '夏佳恒西瓜皮', NULL, NULL, NULL, 'http://img.dmdream.cn/game/8b253649-f233-4d54-927c-ea7e3223a55c.png?imageslim');
INSERT INTO `tab_userInfo` VALUES (3, '2019-06-24 10:07:39', NULL, 1, 1, 3, '123', NULL, NULL, NULL, '夏佳恒香蕉皮', NULL, NULL, NULL, 'http://img.dmdream.cn/game/946e219a-2552-45dd-b736-1f925807a996.png?imageslim');
INSERT INTO `tab_userInfo` VALUES (4, '2019-06-24 10:07:39', NULL, 1, 1, 21, '123', NULL, NULL, NULL, '夏佳恒黑皮', NULL, NULL, NULL, 'http://img.dmdream.cn/game/946e219a-2552-45dd-b736-1f925807a996.png?imageslim');

SET FOREIGN_KEY_CHECKS = 1;
