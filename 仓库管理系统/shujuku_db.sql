# MySQL-Front 5.1  (Build 4.2)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: shujuku_db
# ------------------------------------------------------
# Server version 5.0.67-community-nt

#
# Source for table t_baojing
#

DROP TABLE IF EXISTS `t_baojing`;
CREATE TABLE `t_baojing` (
  `id` bigint(20) NOT NULL auto_increment,
  `content` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table t_baojing
#

LOCK TABLES `t_baojing` WRITE;
/*!40000 ALTER TABLE `t_baojing` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_baojing` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table t_chuku
#

DROP TABLE IF EXISTS `t_chuku`;
CREATE TABLE `t_chuku` (
  `id` bigint(20) NOT NULL auto_increment,
  `bianhao` varchar(255) default NULL,
  `chukulock` int(11) NOT NULL,
  `chukushuliang` int(11) NOT NULL,
  `createtime` datetime default NULL,
  `jingxiaoshang` varchar(255) default NULL,
  `lianxidianhua` varchar(255) default NULL,
  `shenhefanhui` varchar(255) default NULL,
  `shenhezhuangtai` varchar(255) default NULL,
  `kucunid` bigint(20) default NULL,
  `kufangid` bigint(20) default NULL,
  `liyouid` bigint(20) default NULL,
  `productid` bigint(20) default NULL,
  `quyuid` bigint(20) default NULL,
  `shenheuserid` bigint(20) default NULL,
  `userid` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK9E506E6F9D13A165` (`quyuid`),
  KEY `FK9E506E6F7685CF48` (`liyouid`),
  KEY `FK9E506E6FFB94BF2F` (`productid`),
  KEY `FK9E506E6FBA04F21` (`kufangid`),
  KEY `FK9E506E6FBE2A50B6` (`shenheuserid`),
  KEY `FK9E506E6FA3C379BB` (`userid`),
  KEY `FK9E506E6FC6705735` (`kucunid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table t_chuku
#

LOCK TABLES `t_chuku` WRITE;
/*!40000 ALTER TABLE `t_chuku` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_chuku` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table t_diaochu
#

DROP TABLE IF EXISTS `t_diaochu`;
CREATE TABLE `t_diaochu` (
  `id` bigint(20) NOT NULL auto_increment,
  `createtime` datetime default NULL,
  `diaochulock` int(11) NOT NULL,
  `diaodushuliang` int(11) NOT NULL,
  `rukudanbianhao` varchar(255) default NULL,
  `shenhefanhui` varchar(255) default NULL,
  `shenhezhuangtai` varchar(255) default NULL,
  `kufang1id` bigint(20) default NULL,
  `kufang2id` bigint(20) default NULL,
  `productid` bigint(20) default NULL,
  `quyu1id` bigint(20) default NULL,
  `quyu2id` bigint(20) default NULL,
  `rukuliyouid` bigint(20) default NULL,
  `shenheuserid` bigint(20) default NULL,
  `userid` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK81716772FDBCBC97` (`quyu2id`),
  KEY `FK81716772DC7203BB` (`rukuliyouid`),
  KEY `FK81716772F1B3FD57` (`kufang2id`),
  KEY `FK81716772FB94BF2F` (`productid`),
  KEY `FK81716772F1B3F996` (`kufang1id`),
  KEY `FK81716772BE2A50B6` (`shenheuserid`),
  KEY `FK81716772FDBCB8D6` (`quyu1id`),
  KEY `FK81716772A3C379BB` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table t_diaochu
#

LOCK TABLES `t_diaochu` WRITE;
/*!40000 ALTER TABLE `t_diaochu` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_diaochu` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table t_kucun
#

DROP TABLE IF EXISTS `t_kucun`;
CREATE TABLE `t_kucun` (
  `id` bigint(20) NOT NULL auto_increment,
  `keshengqingkecun` int(11) NOT NULL,
  `shuliang` int(11) NOT NULL,
  `kufangid` bigint(20) default NULL,
  `productid` bigint(20) default NULL,
  `quyuid` bigint(20) default NULL,
  `userid` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK9EC6D0E79D13A165` (`quyuid`),
  KEY `FK9EC6D0E7FB94BF2F` (`productid`),
  KEY `FK9EC6D0E7BA04F21` (`kufangid`),
  KEY `FK9EC6D0E7A3C379BB` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table t_kucun
#

LOCK TABLES `t_kucun` WRITE;
/*!40000 ALTER TABLE `t_kucun` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_kucun` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table t_kufang
#

DROP TABLE IF EXISTS `t_kufang`;
CREATE TABLE `t_kufang` (
  `id` bigint(20) NOT NULL auto_increment,
  `baojingzuidashuliang` int(11) NOT NULL,
  `baojingzuixiaoshuliang` int(11) NOT NULL,
  `beizhu` varchar(255) default NULL,
  `createtime` datetime default NULL,
  `kufangbianhao` varchar(255) default NULL,
  `kufanglock` int(11) NOT NULL,
  `mianji` varchar(255) default NULL,
  `name` varchar(255) default NULL,
  `rushushuliang` int(11) NOT NULL,
  `shijirukushuliang` int(11) NOT NULL,
  `zuidashuliang` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table t_kufang
#

LOCK TABLES `t_kufang` WRITE;
/*!40000 ALTER TABLE `t_kufang` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_kufang` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table t_mingxi
#

DROP TABLE IF EXISTS `t_mingxi`;
CREATE TABLE `t_mingxi` (
  `id` bigint(20) NOT NULL auto_increment,
  `chanpin` varchar(255) default NULL,
  `content` varchar(255) default NULL,
  `createtime` datetime default NULL,
  `gongyingshang` varchar(255) default NULL,
  `jingxiaoshang` varchar(255) default NULL,
  `leixing` varchar(255) default NULL,
  `liyou` varchar(255) default NULL,
  `shenheren` varchar(255) default NULL,
  `shuliang` int(11) NOT NULL,
  `userid` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK3CD8B051A3C379BB` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table t_mingxi
#

LOCK TABLES `t_mingxi` WRITE;
/*!40000 ALTER TABLE `t_mingxi` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_mingxi` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table t_product
#

DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `id` bigint(20) NOT NULL auto_increment,
  `baojingxiaxian` int(11) NOT NULL,
  `createtime` datetime default NULL,
  `gongyingshang` varchar(255) default NULL,
  `kucunshuliang` int(11) NOT NULL,
  `name` varchar(255) default NULL,
  `productlock` int(11) NOT NULL,
  `shengchandizhi` varchar(255) default NULL,
  `userid` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKC585804A3C379BB` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table t_product
#

LOCK TABLES `t_product` WRITE;
/*!40000 ALTER TABLE `t_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_product` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table t_quyu
#

DROP TABLE IF EXISTS `t_quyu`;
CREATE TABLE `t_quyu` (
  `id` bigint(20) NOT NULL auto_increment,
  `quyulock` int(11) NOT NULL,
  `quyuming` varchar(255) default NULL,
  `kufangid` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKCB53794BBA04F21` (`kufangid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table t_quyu
#

LOCK TABLES `t_quyu` WRITE;
/*!40000 ALTER TABLE `t_quyu` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_quyu` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table t_ruku
#

DROP TABLE IF EXISTS `t_ruku`;
CREATE TABLE `t_ruku` (
  `id` bigint(20) NOT NULL auto_increment,
  `createtime` datetime default NULL,
  `rukudanbianhao` varchar(255) default NULL,
  `rukulock` int(11) NOT NULL,
  `rukushuliang` int(11) NOT NULL,
  `shenhefanhui` varchar(255) default NULL,
  `shenhezhuangtai` varchar(255) default NULL,
  `kufangid` bigint(20) default NULL,
  `productid` bigint(20) default NULL,
  `quyuid` bigint(20) default NULL,
  `rukuliyouid` bigint(20) default NULL,
  `shenheuserid` bigint(20) default NULL,
  `userid` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKCB53EBF8DC7203BB` (`rukuliyouid`),
  KEY `FKCB53EBF89D13A165` (`quyuid`),
  KEY `FKCB53EBF8FB94BF2F` (`productid`),
  KEY `FKCB53EBF8BA04F21` (`kufangid`),
  KEY `FKCB53EBF8BE2A50B6` (`shenheuserid`),
  KEY `FKCB53EBF8A3C379BB` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table t_ruku
#

LOCK TABLES `t_ruku` WRITE;
/*!40000 ALTER TABLE `t_ruku` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_ruku` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table t_rukuliyou
#

DROP TABLE IF EXISTS `t_rukuliyou`;
CREATE TABLE `t_rukuliyou` (
  `id` bigint(20) NOT NULL auto_increment,
  `createtime` datetime default NULL,
  `infotype` varchar(255) default NULL,
  `liyou` varchar(255) default NULL,
  `rukuliyoulock` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table t_rukuliyou
#

LOCK TABLES `t_rukuliyou` WRITE;
/*!40000 ALTER TABLE `t_rukuliyou` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_rukuliyou` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table t_user
#

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL auto_increment,
  `createtime` datetime default NULL,
  `password` varchar(255) default NULL,
  `role` int(11) NOT NULL,
  `truename` varchar(255) default NULL,
  `userlock` int(11) NOT NULL,
  `username` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table t_user
#

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,NULL,'111111',1,'admin',0,'admin');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

#
#  Foreign keys for table t_chuku
#

ALTER TABLE `t_chuku`
ADD CONSTRAINT `FK9E506E6FC6705735` FOREIGN KEY (`kucunid`) REFERENCES `t_kucun` (`id`),
ADD CONSTRAINT `FK9E506E6F7685CF48` FOREIGN KEY (`liyouid`) REFERENCES `t_rukuliyou` (`id`),
ADD CONSTRAINT `FK9E506E6F9D13A165` FOREIGN KEY (`quyuid`) REFERENCES `t_quyu` (`id`),
ADD CONSTRAINT `FK9E506E6FA3C379BB` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`),
ADD CONSTRAINT `FK9E506E6FBA04F21` FOREIGN KEY (`kufangid`) REFERENCES `t_kufang` (`id`),
ADD CONSTRAINT `FK9E506E6FBE2A50B6` FOREIGN KEY (`shenheuserid`) REFERENCES `t_user` (`id`),
ADD CONSTRAINT `FK9E506E6FFB94BF2F` FOREIGN KEY (`productid`) REFERENCES `t_product` (`id`);

#
#  Foreign keys for table t_diaochu
#

ALTER TABLE `t_diaochu`
ADD CONSTRAINT `FK81716772A3C379BB` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`),
ADD CONSTRAINT `FK81716772BE2A50B6` FOREIGN KEY (`shenheuserid`) REFERENCES `t_user` (`id`),
ADD CONSTRAINT `FK81716772DC7203BB` FOREIGN KEY (`rukuliyouid`) REFERENCES `t_rukuliyou` (`id`),
ADD CONSTRAINT `FK81716772F1B3F996` FOREIGN KEY (`kufang1id`) REFERENCES `t_kufang` (`id`),
ADD CONSTRAINT `FK81716772F1B3FD57` FOREIGN KEY (`kufang2id`) REFERENCES `t_kufang` (`id`),
ADD CONSTRAINT `FK81716772FB94BF2F` FOREIGN KEY (`productid`) REFERENCES `t_product` (`id`),
ADD CONSTRAINT `FK81716772FDBCB8D6` FOREIGN KEY (`quyu1id`) REFERENCES `t_quyu` (`id`),
ADD CONSTRAINT `FK81716772FDBCBC97` FOREIGN KEY (`quyu2id`) REFERENCES `t_quyu` (`id`);

#
#  Foreign keys for table t_kucun
#

ALTER TABLE `t_kucun`
ADD CONSTRAINT `FK9EC6D0E7A3C379BB` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`),
ADD CONSTRAINT `FK9EC6D0E79D13A165` FOREIGN KEY (`quyuid`) REFERENCES `t_quyu` (`id`),
ADD CONSTRAINT `FK9EC6D0E7BA04F21` FOREIGN KEY (`kufangid`) REFERENCES `t_kufang` (`id`),
ADD CONSTRAINT `FK9EC6D0E7FB94BF2F` FOREIGN KEY (`productid`) REFERENCES `t_product` (`id`);

#
#  Foreign keys for table t_mingxi
#

ALTER TABLE `t_mingxi`
ADD CONSTRAINT `FK3CD8B051A3C379BB` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`);

#
#  Foreign keys for table t_product
#

ALTER TABLE `t_product`
ADD CONSTRAINT `FKC585804A3C379BB` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`);

#
#  Foreign keys for table t_quyu
#

ALTER TABLE `t_quyu`
ADD CONSTRAINT `FKCB53794BBA04F21` FOREIGN KEY (`kufangid`) REFERENCES `t_kufang` (`id`);

#
#  Foreign keys for table t_ruku
#

ALTER TABLE `t_ruku`
ADD CONSTRAINT `FKCB53EBF8A3C379BB` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`),
ADD CONSTRAINT `FKCB53EBF89D13A165` FOREIGN KEY (`quyuid`) REFERENCES `t_quyu` (`id`),
ADD CONSTRAINT `FKCB53EBF8BA04F21` FOREIGN KEY (`kufangid`) REFERENCES `t_kufang` (`id`),
ADD CONSTRAINT `FKCB53EBF8BE2A50B6` FOREIGN KEY (`shenheuserid`) REFERENCES `t_user` (`id`),
ADD CONSTRAINT `FKCB53EBF8DC7203BB` FOREIGN KEY (`rukuliyouid`) REFERENCES `t_rukuliyou` (`id`),
ADD CONSTRAINT `FKCB53EBF8FB94BF2F` FOREIGN KEY (`productid`) REFERENCES `t_product` (`id`);


/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
