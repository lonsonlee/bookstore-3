/*
 * 1.用户表
 */
CREATE TABLE `t_user` (
  `uid` char(32) NOT NULL,
  `loginname` varchar(50) DEFAULT NULL,
  `loginpass` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `activationCode` char(64) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `loginname` (`loginname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*
 * 2.图书商品表
 */
CREATE TABLE `t_book` (
  `bid` int unsigned NOT NULL,
  `bname` varchar(200) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `price` decimal(8,2) DEFAULT NULL,
  `currPrice` decimal(8,2) DEFAULT NULL,
  `discount` decimal(3,1) DEFAULT NULL,
  `press` varchar(100) DEFAULT NULL,
  `publicationTime` char(10) DEFAULT NULL,
  `edition` int(11) DEFAULT NULL,
  `pageNum` int(11) DEFAULT NULL,
  `wordNum` int(11) DEFAULT NULL,
  `printtime` char(10) DEFAULT NULL,
  `booksize` int(11) DEFAULT NULL,
  `paper` varchar(50) DEFAULT NULL,
  `cid` char(32) DEFAULT NULL,
  `image_w` varchar(100) DEFAULT NULL,
  `image_b` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`bid`)
  )ENGINE=InnoDB DEFAULT CHARSET=utf8;

  /*
   * 3.购物车条目表
   */
  CREATE TABLE `t_cartitem` (
  `cartItemId` int(10)  UNSIGNED NOT NULL AUTO_INCREMENT,
  `quantity` int(10) UNSIGNED DEFAULT NULL,
  `bid` INT(20) UNSIGNED DEFAULT NULL,
  `username` CHAR(20) DEFAULT NULL,
  PRIMARY KEY (`cartItemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into t_cartitem(quantity,bid,username) values (1,101,'eason');
insert into t_cartitem(quantity,bid,username) values (2,105,'eason');
insert into t_cartitem(quantity,bid,username) values (1,134,'makx');
insert into t_cartitem(quantity,bid,username) values (1,123,'makx');
insert into t_cartitem(quantity,bid,username) values (1,153,'freyaz');
insert into t_cartitem(quantity,bid,username) values (1,123,'freyaz');

/**
 *订单表
 */
CREATE TABLE `t_order` (
  `oid` int(20) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `orderTime` char(19) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `username` char(15) DEFAULT NULL,
  `phone` char(20) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `uid` char(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 *订单条目表
 */
 
 CREATE TABLE `t_orderitem` (
  `orderItemId` int(20) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `quantity` int(10) DEFAULT NULL,
  `subtotal` decimal(8,2) DEFAULT NULL,
  `bid` int(20) UNSIGNED DEFAULT NULL,
  `oid` INT(20) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




INSERT into t_bank (payId,picName) values('ICBC-NET-B2C','icbc.bmp');
INSERT into t_bank (payId,picName) values('CMBCHINA-NET-B2C','cmb.bmp');
INSERT into t_bank (payId,picName) values('ABC-NET-B2C','abc.bmp');
INSERT into t_bank (payId,picName) values('CCB-NET-B2C','ccb.bmp');
INSERT into t_bank (payId,picName) values('BCCB-NET-B2C','bj.bmp');
INSERT into t_bank (payId,picName) values('BOCO-NET-B2C','bcc.bmp');
INSERT into t_bank (payId,picName) values('CIB-NET-B2C','cib.bmp');
INSERT into t_bank (payId,picName) values('NJCB-NET-B2C','nanjing.bmp');
INSERT into t_bank (payId,picName) values('CMBC-NET-B2C','cmbc.bmp');
INSERT into t_bank (payId,picName) values('BOC-NET-B2C','bc.bmp');
INSERT into t_bank (payId,picName) values('PINGANBANK-NET','pingan.bmp');
INSERT into t_bank (payId,picName) values('CBHB-NET-B2C','bh.bmp');
INSERT into t_bank (payId,picName) values('HKBEA-NET-B2C','dy.bmp');
INSERT into t_bank (payId,picName) values('NBCB-NET-B2C','ningbo.bmp');
INSERT into t_bank (payId,picName) values('ECITIC-NET-B2C','zx.bmp');
INSERT into t_bank (payId,picName) values('SDB-NET-B2C','sfz.bmp');
INSERT into t_bank (payId,picName) values('GDB-NET-B2C','gf.bmp');
INSERT into t_bank (payId,picName) values('SHB-NET-B2C','sh.bmp');
INSERT into t_bank (payId,picName) values('SPDB-NET-B2C','shpd.bmp');
INSERT into t_bank (payId,picName) values('POST-NET-B2C','post.bmp');
INSERT into t_bank (payId,picName) values('BJRCB-NET-B2C','beijingnongshang.bmp');
INSERT into t_bank (payId,picName) values('HXB-NET-B2C','hx.bmp');
INSERT into t_bank (payId,picName) values('CZ-NET-B2C','zheshang.bmp');
INSERT into t_bank (payId,picName) values('CEB-NET-B2C','guangda.bmp');

