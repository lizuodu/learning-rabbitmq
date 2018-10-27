
USE `test`;

CREATE TABLE IF NOT EXISTS `t_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_code` varchar(50) NOT NULL,
  `quantity` int(11) NOT NULL,
  `flag` int(11) DEFAULT '0' COMMENT '1：删除',
  `create_by` int(11) DEFAULT NULL COMMENT '创建者id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '修改者id',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `t_order` (`id`, `product_code`, `quantity`, `flag`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES
	(1, 'cookie', 100, 0, NULL, NULL, NULL, NULL);


CREATE TABLE IF NOT EXISTS `t_store` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_code` varchar(50) NOT NULL,
  `sku` int(11) NOT NULL,
  `flag` int(11) DEFAULT '0' COMMENT '1：删除',
  `create_by` int(11) DEFAULT NULL COMMENT '创建者id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '修改者id',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


INSERT INTO `t_store` (`id`, `product_code`, `sku`, `flag`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES
	(1, 'cookie', 900, 0, NULL, NULL, NULL, NULL);

