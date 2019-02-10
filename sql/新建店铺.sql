#新建店铺脚本
INSERT INTO usedcar.shop_app_info (appid, shop_id, secret, create_time, update_time)
VALUES ('wxc41ae6a26ee4cf60', 2, '38dd4de9c96105ea328fdca9aa91c89c', now(), now());

INSERT INTO usedcar.shop (shop_id, shop_name, shop_image, address, telephone, link_man, create_time, update_time)
VALUES (2, '大荒二手车', '', '北京市朝阳区霄云路50号 距离市中心约7380米', '4000123', '1', now(), now());

INSERT INTO usedcar.bg_users (user_id, shop_id, username, password, create_time, update_time)
VALUES (2, 2, 'ls', '123456', now(), now());