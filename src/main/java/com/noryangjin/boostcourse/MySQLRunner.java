package com.noryangjin.boostcourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class MySQLRunner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 기본 jdbc api
        try(Connection connection = dataSource.getConnection()) { // connection이라는 리소스를 try안에서 사용하고, 정리를해준다.

            // db 정보 출력
            System.out.println(dataSource.getClass()); // DBCP 정보
            System.out.println(connection.getMetaData().getURL());
            System.out.println(connection.getMetaData().getUserName());

            // USER 테이블 생성
/*            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE USER(ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (id))";
            statement.executeUpdate(sql);*/
        }

        // 기본적인 jdbc api 사용보다 좀 더 편하다.
        jdbcTemplate.execute("INSERT INTO USER VALUES (16, 'junseo')");




        //ddl
/*

        drop table if exists reservation_user_comment_image;
        drop table if exists reservation_user_comment;
        drop table if exists reservation_info_price;
        drop table if exists reservation_info;
        drop table if exists promotion;
        drop table if exists product_price;
        drop table if exists product_image;
        drop table if exists display_info_image;
        drop table if exists display_info;
        drop table if exists product;
        drop table if exists file_info;
        drop table if exists category;
        drop table if exists user_role;
        drop table if exists user;

        -- -----------------------------------------------------
                -- Table `user`
        -- -----------------------------------------------------
                CREATE TABLE `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'user id',
  `name` VARCHAR(255) NOT NULL COMMENT 'user name',
  `password` VARCHAR(255) NOT NULL COMMENT '암호회된 password',
  `email` VARCHAR(255) NOT NULL UNIQUE COMMENT 'login id, email',
  `phone` VARCHAR(255) NOT NULL UNIQUE COMMENT 'phone',
  `create_date` DATETIME NULL DEFAULT NULL COMMENT '등록일',
  `modify_date` DATETIME NULL DEFAULT NULL COMMENT '수정일',
                PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;


        -- -----------------------------------------------------
                -- Table `user_role`
        -- -----------------------------------------------------
                CREATE TABLE `user_role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'role id',
  `user_id` INT(11) NOT NULL COMMENT 'user id fk',
  `role_name` VARCHAR(100) NOT NULL COMMENT 'role 이름 ROLE_ 로 시작하는 값이어야 한다.',
                PRIMARY KEY (`id`),
                FOREIGN KEY (`user_id`)
        REFERENCES `user` (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;


        -- -----------------------------------------------------
                -- Table `category`
        -- -----------------------------------------------------
                CREATE TABLE `category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '카테고리 id',
  `name` VARCHAR(50) NOT NULL COMMENT '카테고리 이름',
                PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

        -- -----------------------------------------------------
                -- Table `fileinfo`
        -- -----------------------------------------------------
                CREATE TABLE `file_info` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '파일id',
  `file_name` VARCHAR(255) NOT NULL COMMENT '원본 파일명',
  `save_file_name` VARCHAR(4000) NOT NULL COMMENT 'save파일 명',
  `content_type` VARCHAR(255) NOT NULL COMMENT '파일 mime type',
  `delete_flag` INT(1) NOT NULL COMMENT '삭제유무 삭제:1, 삭제안됨 : 0',
  `create_date` DATETIME NULL DEFAULT NULL COMMENT '등록일',
  `modify_date` DATETIME NULL DEFAULT NULL COMMENT '수정일',
                PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;


        -- -----------------------------------------------------
                -- Table `product`
        -- -----------------------------------------------------
                CREATE TABLE `product` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '상품id',
  `category_id` INT(11) NOT NULL COMMENT '카테고리id',
  `description` VARCHAR(100) NULL DEFAULT NULL COMMENT '상품 간단 설명',
  `content` TEXT NULL DEFAULT NULL COMMENT '상품 상세 설명',
  `event` VARCHAR(4000) NULL DEFAULT NULL COMMENT '이벤트 정보',
  `create_date` DATETIME NULL DEFAULT NULL COMMENT '생성시간',
  `modify_date` DATETIME NULL DEFAULT NULL COMMENT '수정시간',
                PRIMARY KEY (`id`),
                FOREIGN KEY (`category_id`)
        REFERENCES `category` (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;


        -- -----------------------------------------------------
                -- Table `display_info`
        -- -----------------------------------------------------
                CREATE TABLE `display_info` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '전시정보id',
  `product_id` INT(11) NOT NULL COMMENT 'product id',
  `opening_hours` VARCHAR(400) COMMENT '전시 시간',
  `place_name` VARCHAR(50) NOT NULL COMMENT '장소 명 ex>세종문화회관',
  `place_lot` VARCHAR(100) NULL DEFAULT NULL COMMENT '지번 주소',
  `place_street` VARCHAR(100) NULL DEFAULT NULL COMMENT '도로명 주소',
  `tel` VARCHAR(20) NULL DEFAULT NULL COMMENT '문의전화번호',
  `homepage` VARCHAR(255) NULL DEFAULT NULL COMMENT '홈페이지',
  `email` VARCHAR(255) NULL DEFAULT NULL COMMENT '문의email',
  `create_date` DATETIME NULL DEFAULT NULL COMMENT '생성시간',
  `modify_date` DATETIME NULL DEFAULT NULL COMMENT '수정시간',
                PRIMARY KEY (`id`),
                FOREIGN KEY (`product_id`)
        REFERENCES `product` (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;


        -- -----------------------------------------------------
                -- Table `product_image`
        -- -----------------------------------------------------
                CREATE TABLE `product_image` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '상품 이미지 id',
  `product_id` INT(11) NOT NULL COMMENT '상품 id',
  `type` VARCHAR(2) NOT NULL COMMENT '이미지 type, th: 썸네일, ma: 메인, et: 기타 (etc)',
  `file_id` INT(11) NOT NULL COMMENT 'file id',
                PRIMARY KEY (`id`),
                FOREIGN KEY (`product_id`)
        REFERENCES `product` (`id`),
        FOREIGN KEY (`file_id`)
        REFERENCES `file_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

        -- -----------------------------------------------------
                -- Table `display_info_image`
        -- -----------------------------------------------------
                CREATE TABLE `display_info_image` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '전시정보 이미지 id',
  `display_info_id` INT(11) NOT NULL COMMENT '전시정보 id',
  `file_id` INT(11) NOT NULL COMMENT 'file id',
                PRIMARY KEY (`id`),
                FOREIGN KEY (`display_info_id`)
        REFERENCES `display_info` (`id`),
        FOREIGN KEY (`file_id`)
        REFERENCES `file_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


        -- -----------------------------------------------------
                -- Table `product_price`
        -- -----------------------------------------------------
                CREATE TABLE `product_price` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '상품 가격 id',
  `product_id` INT(11) NOT NULL COMMENT '상품 id',
  `price_type_name` VARCHAR(25) NOT NULL COMMENT '성인(A), 청소년(Y), 유아(B), 셋트(S), 장애인(D), 지역주민(C), 어얼리버드(E) 기타 다른 유형이 있다면 위와 겹치지 않게 1자로 정의하여 기입, VIP(V), R석(R), B석(B), S석(S), 평일(D)',
  `price` INT(11) NOT NULL COMMENT '상품 가격',
  `discount_rate` DECIMAL(5,2) NOT NULL COMMENT '할인율',
  `create_date` DATETIME NULL DEFAULT NULL COMMENT '등록일',
  `modify_date` DATETIME NULL DEFAULT NULL COMMENT '수정일',
                PRIMARY KEY (`id`),
                FOREIGN KEY (`product_id`)
        REFERENCES `product` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

        -- -----------------------------------------------------
                -- Table `promotion`
        -- -----------------------------------------------------
                CREATE TABLE `promotion` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '프로모션 id',
  `product_id` INT(11) NOT NULL COMMENT '프로모션id',
                PRIMARY KEY (`id`),
                FOREIGN KEY (`product_id`)
        REFERENCES `product` (`id`))
        ENGINE=InnoDB DEFAULT CHARSET=utf8;


        -- -----------------------------------------------------
                -- Table `reservation_info`
        -- -----------------------------------------------------
                CREATE TABLE `reservation_info` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '예매 id',
  `product_id` INT(11) NOT NULL COMMENT '상품 id',
  `display_info_id` INT(11) NOT NULL COMMENT '전시상품 id',
  `user_id` INT(11) NOT NULL COMMENT '예매자 아이디',
  `reservation_date` DATETIME NOT NULL COMMENT '예매일',
  `cancel_flag` INT(1) NOT NULL DEFAULT '0' COMMENT '취소 : 1, 예약 : 0',
  `create_date` DATETIME DEFAULT NULL COMMENT '등록일',
  `modify_date` DATETIME DEFAULT NULL COMMENT '수정일',
                PRIMARY KEY (`id`),
                FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
        FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
        FOREIGN KEY (`display_info_id`) REFERENCES `display_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



        -- -----------------------------------------------------
                -- Table `reservation_info_price`
        -- -----------------------------------------------------
                CREATE TABLE `reservation_info_price` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '예매 가격 id',
  `reservation_info_id` INT(11) NOT NULL COMMENT '예매 정보 id',
  `product_price_id` INT(11) NOT NULL COMMENT '상품 가격 id',
  `count` INT(11) NOT NULL COMMENT '예매수',
                PRIMARY KEY (`id`),
                FOREIGN KEY (`reservation_info_id`)
        REFERENCES `reservation_info` (`id`),
        FOREIGN KEY (`product_price_id`)
        REFERENCES `product_price` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


        -- -----------------------------------------------------
                -- Table `reservation_user_comment`
        -- -----------------------------------------------------
                CREATE TABLE `reservation_user_comment` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '한줄평 id',
  `product_id` INT(11) NOT NULL COMMENT '상품 id',
  `reservation_info_id` INT(11) NOT NULL COMMENT '예매 id',
  `user_id` INT(11) NOT NULL COMMENT '회원 아이디',
  `score` DECIMAL(2,1) NOT NULL COMMENT '별점',
  `comment` TEXT NOT NULL COMMENT '한줄평',
  `create_date` DATETIME NULL DEFAULT NULL COMMENT '등록일',
  `modify_date` DATETIME NULL DEFAULT NULL COMMENT '수정일',
                PRIMARY KEY (`id`),
                FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
        FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
        FOREIGN KEY (`reservation_info_id`)  REFERENCES `reservation_info` (`id`)
  )  ENGINE=InnoDB DEFAULT CHARSET=utf8;


        -- -----------------------------------------------------
                -- Table `reservation_user_comment_image`
        -- -----------------------------------------------------
                CREATE TABLE `reservation_user_comment_image` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '상품 커맨트 이미지 id',
  `reservation_info_id` INT(11) NOT NULL COMMENT '예매 id',
  `reservation_user_comment_id` INT(11) NOT NULL COMMENT '한줄평 id',
  `file_id` INT(11) NOT NULL COMMENT '파일 id',
                PRIMARY KEY (`id`),
                FOREIGN KEY (`reservation_info_id`)
        REFERENCES `reservation_info` (`id`),
        FOREIGN KEY (`reservation_user_comment_id`)
        REFERENCES `reservation_user_comment` (`id`),
        FOREIGN KEY (`file_id`)
        REFERENCES `file_info` (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
        */

        //dml
/*

        insert into user (id, name, password, email, phone, create_date, modify_date)
        values ( 1, '강경미', '$2a$10$G/ADAGLU3vKBd62E6GbrgetQpEKu2ukKgiDR5TWHYwrem0cSv6Z8m', 'carami@connect.co.kr', '010-0000-0001', now(), now());
        insert into user (id, name, password, email, phone, create_date, modify_date)
        values ( 2, '김진수', '$2a$10$G/ADAGLU3vKBd62E6GbrgetQpEKu2ukKgiDR5TWHYwrem0cSv6Z8m', 'kimjinsu@connect.co.kr', '010-0000-0002', now(), now());
        insert into user (id, name, password, email, phone, create_date, modify_date)
        values ( 3, '홍상수', '$2a$10$G/ADAGLU3vKBd62E6GbrgetQpEKu2ukKgiDR5TWHYwrem0cSv6Z8m', 'hongsangsu@connect.co.kr', '010-0000-0003', now(), now());
        insert into user (id, name, password, email, phone, create_date, modify_date)
        values ( 4, '도민상', '$2a$10$G/ADAGLU3vKBd62E6GbrgetQpEKu2ukKgiDR5TWHYwrem0cSv6Z8m', 'dominsang@connect.co.kr', '010-0000-0004', now(), now());
        insert into user (id, name, password, email, phone, create_date, modify_date)
        values ( 5, '고아름', '$2a$10$G/ADAGLU3vKBd62E6GbrgetQpEKu2ukKgiDR5TWHYwrem0cSv6Z8m', 'goarum@connect.co.kr', '010-0000-0005', now(), now());
        insert into user (id, name, password, email, phone, create_date, modify_date)
        values ( 6, '김진호', '$2a$10$G/ADAGLU3vKBd62E6GbrgetQpEKu2ukKgiDR5TWHYwrem0cSv6Z8m', 'kimjinho@connect.co.kr', '010-0000-0006', now(), now());
        insert into user (id, name, password, email, phone, create_date, modify_date)
        values ( 7, '강상권', '$2a$10$G/ADAGLU3vKBd62E6GbrgetQpEKu2ukKgiDR5TWHYwrem0cSv6Z8m', 'kimsangkun@connect.co.kr', '010-0000-0007', now(), now());
        insert into user (id, name, password, email, phone, create_date, modify_date)
        values ( 8, '김은비', '$2a$10$G/ADAGLU3vKBd62E6GbrgetQpEKu2ukKgiDR5TWHYwrem0cSv6Z8m', 'kimrain@connect.co.kr', '010-0000-0008', now(), now());
        insert into user (id, name, password, email, phone, create_date, modify_date)
        values ( 9, '홍길동', '$2a$10$G/ADAGLU3vKBd62E6GbrgetQpEKu2ukKgiDR5TWHYwrem0cSv6Z8m', 'hong@connect.co.kr', '010-0000-0009', now(), now());
        insert into user (id, name, password, email, phone, create_date, modify_date)
        values ( 10, '이순신', '$2a$10$G/ADAGLU3vKBd62E6GbrgetQpEKu2ukKgiDR5TWHYwrem0cSv6Z8m', 'leesunsin@connect.co.kr', '010-0000-0010', now(), now());
        insert into user (id, name, password, email, phone, create_date, modify_date)
        values ( 11, '강감찬', '$2a$10$G/ADAGLU3vKBd62E6GbrgetQpEKu2ukKgiDR5TWHYwrem0cSv6Z8m', 'kangchan@connect.co.kr', '010-0000-0011', now(), now());
        insert into user (id, name, password, email, phone, create_date, modify_date)
        values ( 12, '김수정', '$2a$10$G/ADAGLU3vKBd62E6GbrgetQpEKu2ukKgiDR5TWHYwrem0cSv6Z8m', 'kinsujung@connect.co.kr', '010-0000-0012', now(), now());
        insert into user (id, name, password, email, phone, create_date, modify_date)
        values ( 13, '김미연', '$2a$10$G/ADAGLU3vKBd62E6GbrgetQpEKu2ukKgiDR5TWHYwrem0cSv6Z8m', 'kimmy@connect.co.kr', '010-0000-0013', now(), now());
        insert into user (id, name, password, email, phone, create_date, modify_date)
        values ( 14, '김한국', '$2a$10$G/ADAGLU3vKBd62E6GbrgetQpEKu2ukKgiDR5TWHYwrem0cSv6Z8m', 'kimkorea@connect.co.kr', '010-0000-0014', now(), now());
        insert into user (id, name, password, email, phone, create_date, modify_date)
        values ( 15, '도로시', '$2a$10$G/ADAGLU3vKBd62E6GbrgetQpEKu2ukKgiDR5TWHYwrem0cSv6Z8m', 'dorosi@connect.co.kr', '010-0000-0015', now(), now());


        insert into user_role (id, user_id, role_name) values (1, 1, 'ROLE_USER');
        insert into user_role (id, user_id, role_name) values (2, 2, 'ROLE_USER');
        insert into user_role (id, user_id, role_name) values (3, 3, 'ROLE_USER');
        insert into user_role (id, user_id, role_name) values (4, 4, 'ROLE_USER');
        insert into user_role (id, user_id, role_name) values (5, 5, 'ROLE_USER');
        insert into user_role (id, user_id, role_name) values (6, 6, 'ROLE_USER');
        insert into user_role (id, user_id, role_name) values (7, 7, 'ROLE_USER');
        insert into user_role (id, user_id, role_name) values (8, 8, 'ROLE_USER');
        insert into user_role (id, user_id, role_name) values (9, 9, 'ROLE_USER');
        insert into user_role (id, user_id, role_name) values (10, 10, 'ROLE_USER');
        insert into user_role (id, user_id, role_name) values (11, 11, 'ROLE_USER');
        insert into user_role (id, user_id, role_name) values (12, 12, 'ROLE_USER');
        insert into user_role (id, user_id, role_name) values (13, 13, 'ROLE_USER');
        insert into user_role (id, user_id, role_name) values (14, 14, 'ROLE_USER');
        insert into user_role (id, user_id, role_name) values (15, 15, 'ROLE_USER');
        insert into user_role (id, user_id, role_name) values (16, 1, 'ROLE_ADMIN');
        insert into user_role (id, user_id, role_name) values (17, 2, 'ROLE_ADMIN');


        insert into category values (1,'전시');
        insert into category values (2, '뮤지컬');
        insert into category values (3, '콘서트');
        insert into category values (4, '클래식');
        insert into category values (5, '연극');

        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 1,'1_map_1.png', 'img_map/1_map_1.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 2,'2_map_2.png', 'img_map/2_map_2.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 3,'3_map_3.png', 'img_map/3_map_3.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 4,'4_map_4.png', 'img_map/4_map_4.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 5,'5_map_5.png', 'img_map/5_map_5.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 6,'6_map_6.png', 'img_map/6_map_6.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 7,'7_map_7.png', 'img_map/7_map_7.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 8,'8_map_8.png', 'img_map/8_map_8.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 9,'9_map_9.png', 'img_map/9_map_9.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 10,'10_map_10.png', 'img_map/10_map_10.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 11,'11_map_11.png', 'img_map/11_map_11.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 12,'12_map_12.png', 'img_map/12_map_12.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 13,'13_map_13.png', 'img_map/13_map_13.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 14,'14_map_14.png', 'img_map/14_map_14.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 15,'15_map_15.png', 'img_map/15_map_15.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 16,'16_map_16.png', 'img_map/16_map_16.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 17,'17_map_17.png', 'img_map/17_map_17.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 18,'18_map_18.png', 'img_map/18_map_18.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 19,'19_map_19.png', 'img_map/19_map_19.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 20,'20_map_20.png', 'img_map/20_map_20.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 21,'21_map_21.png', 'img_map/21_map_21.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 22,'22_map_22.png', 'img_map/22_map_22.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 23,'23_map_23.png', 'img_map/23_map_23.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 24,'24_map_24.png', 'img_map/24_map_24.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 25,'25_map_25.png', 'img_map/25_map_25.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 26,'26_map_26.png', 'img_map/26_map_26.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 27,'27_map_27.png', 'img_map/27_map_27.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 28,'28_map_28.png', 'img_map/28_map_28.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 29,'29_map_29.png', 'img_map/29_map_29.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 30,'29_map_30.png', 'img_map/29_map_30.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 31,'29_map_31.png', 'img_map/29_map_31.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 32,'30_map_32.png', 'img_map/30_map_32.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 33,'30_map_32.png', 'img_map/30_map_32.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 34,'30_map_32.png', 'img_map/30_map_32.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 35,'30_map_32.png', 'img_map/30_map_32.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 36,'30_map_32.png', 'img_map/30_map_32.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 37,'31_map_33.png', 'img_map/31_map_33.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 38,'32_map_34.png', 'img_map/32_map_34.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 39,'33_map_35.png', 'img_map/33_map_35.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 40,'34_map_36.png', 'img_map/34_map_36.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 41,'35_map_37.png', 'img_map/35_map_37.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 42,'36_map_38.png', 'img_map/36_map_38.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 43,'37_map_39.png', 'img_map/37_map_39.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 44,'38_map_40.png', 'img_map/38_map_40.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 45,'39_map_41.png', 'img_map/39_map_41.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 46,'40_map_42.png', 'img_map/40_map_42.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 47,'41_map_43.png', 'img_map/41_map_43.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 48,'41_map_44.png', 'img_map/41_map_44.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 49,'42_map_45.png', 'img_map/42_map_45.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 50,'43_map_46.png', 'img_map/43_map_46.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 51,'44_map_47.png', 'img_map/44_map_47.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 52,'45_map_48.png', 'img_map/45_map_48.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 53,'45_map_49.png', 'img_map/45_map_49.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 54,'46_map_50.png', 'img_map/46_map_50.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 55,'47_map_51.png', 'img_map/47_map_51.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 56,'48_map_52.png', 'img_map/48_map_52.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 57,'49_map_53.png', 'img_map/49_map_53.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 58,'49_map_54.png', 'img_map/49_map_54.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 59,'50_map_55.png', 'img_map/50_map_55.png', 'image/png', 0, now(), now());



        insert into product (id, description, content, event, category_id, create_date, modify_date) values (1, 'Paper, Present:너를 위한 선물','대림미술관은 오는 12월 7일부터 2018년 5월 27일까지 세계적인 아티스트들의 섬세한 감각과 아날로그적 소재인 종이가 만나 감성적인 매체로 확장되는 과정을 소개하는 전시 〈PAPER, PRESENT: 너를 위한 선물〉을 개최합니다. 이번 전시에서는 다양한 분야에서 활동하고 있는 아티스트들이 종이의 본래적 속성에 집중하여 재료 자체의 순수한 아름다움을 담은 작품들을 일곱 개의 감각적인 공간에서 선보입니다. 바람, 별빛, 햇살과 같은 자연 요소와 기억, 설렘과 같은 감정의 요소를 종이와 결합하여 구성한 공간들은, 자연 현상을 감각적으로 경험하고 아날로그적 정서를 자극하는 매체로서 종이를 경험하게 하며 종이에 감성을 입혀 예술로 만나는 특별한 시간을 선물할 것입니다.', '', 1, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (2, '퀀틴 블레이크','KT&G 상상마당은 20세기 거장시리즈 기획전 일환으로 전 세계적으로 사랑 받는 영국 최고의 일러스트레이터 퀀틴 블레이크(Quentin Blake, 1932-) 전시를 오는 10월 21일(토)부터 내년 2월 20일(화)까지 서울 서교동에 위치한 KT&G 상상마당 갤러리(4,5F)에서 진행한다. 영국 작가 로알드 달(Roald Dahl, 1916-1990)의 아동 소설 『찰리와 초콜릿 공장』 의 원화 작가로 유명한 퀀틴 블레이크는 지난 60여 년간 편안한 그림체와 성인들도 공감할 수 있는 동화들을 꾸준히 발표해왔다. 이번 전시에는 원화 작가에서 나아가 글과 그림을 통한 스토리텔링에 뛰어난 아티스트로서의 면모를 조명하고, 『찰리와 초콜릿 공장』 원화를 비롯한 퀀틴 블레이크의 작품 180여 점과 그의 작업실을 재현해낸 공간을 선보인다.', '', 1, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (3, 'ALICE : Into The Rabbit Hole','<반 고흐 인사이드>, <클림트 인사이드>에 이은
                ㈜미디어앤아트의 여섯 번째 아트 프로젝트 <ALICE : into the rabbit hole>은
        루이스 캐럴의 <이상한 나라의 앨리스>, <거울나라의 앨리스> 시리즈를
        현대적인 시각으로 표현해낸 새로운 컨셉의 전시입니다.

        동화의 새 패러다임을 열며 전 세계인들의 사랑을 받은 불멸의 명작,
        루이스 캐럴의 ‘앨리스’ 시리즈. 그동안은 책, 애니메이션과 영화 등 2차원 세계에서만
        만나볼 수 있던 이 매력적인 동화의 세계관이 현대적인 감각으로 재해석되어
        예쁘고, 즐겁고, 행복해지는 3차원 테마파크 ‘앨리스 랜드’를 만나보세요.

        개성 넘치는 일러스트레이션 작가, 감각적인 뮤지션, 키치한 설치작가와
        대중문화를 선도하는 영상크루 등 총 23팀이 ㈜미디어앤아트와 만나
        저마다의 ‘앨리스’와 ‘원더랜드’를 만들어냈습니다.

        아티스트 팀들과 협업을 통해 감상의 한계를 뛰어넘어
        앨리스의 기상천외한 모험을 표현해낸 이번 전시는
        딱딱한 일상에서 탈출하는 신나고 즐거운 경험이 될 것입니다.', '', 1, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (4, '99% 디자인 엑스포','99% 디자인 엑스포는 국내 최초로 대학이 주최하는 예술 디자인 박람회 입니다.
                99% 디자인은 ''사용자에 의해 (by the people)완성되는 디자인''을 뜻하며
        소수만 누리는 디자인이 아닌 ''모두를 위한 (for the people)디자인''을 지향합니다.', '', 1, now(), now());

        insert into product (id, description, content, event, category_id, create_date, modify_date) values (5, '알렉산더 지라드전','알렉산더 지라드, 디자이너의 세계 展
                Alexander Girard, A Designer''s Universe

        2017년 12월 22일부터 2018년 3월 4일까지 예술의전당 한가람미술관에서 20세기 미국 모던디자인을 대표하는 시대의 아이콘, 알렉산더 지라드의 대규모 회고전을 아시아 최초로 개최한다. 이번 전시는 지라드의 인테리어와 사진, 텍스타일, 가구, 장식소품, 수집품 등 5,000여 점 이상의 작품을 소장하고 있는 비트라 디자인 미술관(Vitra Design Museum)이 그의 삶과 작품세계를 심층적으로 연구해 기획되었으며, 700여 점의 작품을 총 4부로 구성해 종합적이며 다이나믹하게 보여준다.
                이번 대규모 알렉산더 지라드 회고전에서는 전 세계적으로 큰 사랑을 받고 있는 작품 <LOVE>, <International LOVE>를 포함해 토탈디자인을 추구했던 그의 완전한 디자인 세계를 생생하게 경험할 수 있다. 뿐만 아니라 지라드와 협력한 동시대의 유명 건축가, 디자이너, 예술가인 찰스 임스와 레이 임스(Charles Eames and Ray Eames), 조지 넬슨(George Nelson), 조지아 오키프(Georgia O''keefe), 에밀리오 푸치(Emilio Pucci)등 과의 관계 및 영향 또한 살펴볼 수 있다.
                국내에 베어브릭과 목각인형 컬렉션으로 탄탄한 마니아층을 보유하고 있는 알렉산더 지라드의 디자인은 이번 전시를 통해 국내의 많은 인테리어 디자인 애호가는 물론이고 전 세대를 아울러 특별한 경험을 선물할 것으로 기대한다.', '', 1, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (6, '캠퍼스 라이프 엑스포','스무살을 위한 위키백과 2018 캠퍼스 라이프 엑스포
        ''유스마케팅 전문 기업'' 대학내일과 ''대한민국 No.1 입시전문 교육기업'' 진학사가 공동으로 주최하며,
                입학을 앞둔 예비 대학생 및 스무살 청년들의 진로, 커리어, 라이프 등에 관련된 다양한 기업브랜드와 스무살 타겟에게 특화된 콘텐츠로 구성된 새로운 마케팅 플랫폼입니다.', '', 1, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (7, '파일롯플랜트','파일럿 플랜트는 소규모 시험생산을 뜻합니다. 그래픽 디자이너, 일러스트레이터, 아티스트가 참여하여 시험하고 생산한 이미지들을 대중과 동료에게 선보이고 함께 호흡합니다.', '', 1, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (8, '디뮤지엄 PLASTIC FANTASTIC 빛 컬러 판타지','디뮤지엄(D MUSEUM)은 오는 2018년 3월 4일까지 세계적 디자이너들의 예술적 감성과 플라스틱의 무한한 가능성이 만나

                탄생한 디자인을 소개하는 전시 빛·컬러·판타지>를 개최한다.

                        이번 전시는 20세기 기적의 소재로 불리는 플라스틱이 일상으로 들어와 우리의 라이프 스타일을 다채롭게
                변화시킨 마법 같은 여정을 보여준다. 지난 반 세기 동안 열정 넘치는 40여 명의 세계적인 크리에이터들이 개성과
                도전정신을 불어 넣어 탄생시킨 2,700여 점의 제품, 가구, 조명, 그래픽, 사진 등을 총망라한다.



                ‘빚어서 만든다’라는 플라스틱의 어원처럼 빛·컬러·판타지> 전시는 늘 유연하고 새롭게 변모하는

                플라스틱의 특성과 마스터 디자이너들의 예술적 감성이 서로 영향을 주고 받으며 유기적으로 진화해 온 과정을

                다각도로 보여준다. 더불어, 어쩌면 우리가 일상에서 무심코 지나친 플라스틱이 빛과 컬러를 통해 생활의 풍경을

                아름답게 변화시키는 특별한 순간을 선사한다.', '', 1, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (9, '무민원화전','핀란드 독립 100주년, 캐릭터 무민 탄생 70주년 기념!
                MOOMIN ORIGINAL ARTWORKS EXHIBITION', '', 1, now(), now());

        insert into product (id, description, content, event, category_id, create_date, modify_date) values (10, '마리로랑생展','피카소를 그린 화가, 샤넬을 그린 여자.
                마리 로랑생

                프랑스의 위대한 여성 화가이자 영화보다 더 영화 같은 삶을 살았던 작가 마리 로랑생(1883~1956)은 1, 2차 세계대전의 틈바구니에서 황홀한 색채와 직관을 통해 여자와 소녀, 꽃과 동물 등을 그려냄으로써 세상의 아픔을 보듬고자 했다.

                파블로 피카소, 코코 샤넬, 장 콕토, 알베르 카뮈 등 수 많은 예술가와 교류하며 ''몽마르트르의 뮤즈'' ''핑크 레이디’로 불렸던 그녀는 1910~1930년대 프랑스 파리 예술계에 큰 영향을 미친 ''예술가의 예술가''이기도 했다. 그런 작가의 작품들은 100여 년 전 그린 그림이라고 믿기 어려울 정도로 현대적이며 작가가 평생에 걸쳐 체득한 ''색채의 연금술''은 여전히 미묘하고 신비롭다. ', '', 1, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (11, '뮤지컬 모래시계','시대가 낳은 위대한 걸작, 새로운 역사로 기록될 뮤지컬 <모래시계>
                2017년 12월, 충무아트센터 개막!

                최고 64.5%의 경이로운 시청률! 5.18 광주민주화운동을 정면으로 다룬 최초의 드라마!
                SBS 연기대상 4관왕, 백상예술대상 6관왕, 한국방송 작가상 드라마 부분 수상!
‘귀가시계’라 불리며 화제를 모은 국민 드라마 <모래시계>가
        2017년 12월, 최고의 창작진 손에서 뮤지컬로 다시 태어나다!', '', 2, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (12, '뮤지컬 올슉업','‘사랑에 빠져 미치도록 기분이 좋은 상태! EVERYBODY ALL SHOOK UP!!’', '', 2, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (13, '뮤지컬 광화문연가','기억소환, 추억정산 뮤지컬
                뮤지컬 <광화문 연가>
                        그 시절 우리가 새겨진 이 곳
                2017년 연말, 다시 광화문에 서다!', '', 2, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (14, '뮤지컬 ''공룡이 살아있다'' - 국립중앙박물관 극장용','대한민국 누적관객 6만여명!
        최고의 가족뮤지컬 공룡이 돌아왔다!

                새로운 캐릭터의 등장과 전통적 소재로 업그레이드된 공룡화석!
                최고의 제작진이 선사하는 최고의 작품!
                2018년 겨울 국립중앙박물관 극장용에서 만나요^^', '', 2, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (15, '창작뮤지컬 레미제라블','감동, 용서, 사랑, 희망을 원하는
                사람들의 노래가 울려 퍼진다.
                        올 가을, 겨울 당신의 영혼을 울리게 할 기대작!

                지금까지 레미제라블은 잊어라.
        새로운 시선으로 레미제라블 찾아왔다

        우리나라 최고의 제작진이 만든
        2017년 마지막을 감동으로 장식할 드라마
        자베르!', '', 2, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (16, '뮤지컬 혐오스런 마츠코의 일생','상처만 안기는 세상을 뜨겁게 살다 간 여인 마츠코
                그녀의 드라마틱한 삶이 대한민국 창작 뮤지컬로 되살아난다!

                일본의 저명한 소설가 ‘야마다 무네키’의 작품인 소설 <혐오스런 마츠코의 일생>이
        대한민국 창작 뮤지컬로 재탄생한다.
        사랑을 원하고 사랑받기를 꿈꿨던 여인 마츠코의 기구한 삶을
        흡인력 강한 스토리와 매력적인 캐릭터로 표현하며 베스트셀러에 오르는 등
        화제를 모은 <혐오스런 마츠코의 일생>은 드라마와 영화로도 제작되었고
        소설과는 또 다른 느낌의 감각적이고 독특한 영상미와 섬세한 터치로
        큰 인기를 모으며 각종 시상식을 휩쓸고 작품성과 흥행성을 인정받았다.


                인생이 끝났다고 생각한 순간, 사랑이 찾아왔다!
                사랑스럽던 한 여자의 잔혹한 질주

        마츠코는 누구보다도 사랑을 꿈꾸고 갈구했지만 철저하게 버림받는 여자다.
        그녀의 사랑은 언제나 갈 곳을 잃지만 다시 사랑이라는 운명의 손을 잡는다.
                삶을 원망하거나 외면하지 않고 오히려 더 적극적으로 사랑한
        언제나 사랑받기를 꿈꿨지만 사실 누구보다도 많은 사랑을 남긴 여자 마츠코.
        마츠코의 일생을 통해 그녀의 인생이 진정 혐오스러운 삶이었는지 되묻는다.


                대한민국 대표 창작진이 뭉쳤다. 김민정 연출 x 민찬홍 작곡
        슬프도록 아름다운 그녀의 내면을 감성적인 연출과 음악으로 그려낸다!

                53년간 살다 간 한 여자의 파란만장한 삶을 그린 작품 <혐오스런 마츠코의 일생>의
        뮤지컬화를 위해 대한민국 대표 창작진이 뭉쳤다.
        섬세하고 탄탄한 연출력을 지닌 김민정 연출이 극작과 연출로 진두지휘하고,
                민찬홍 작곡가, 정도영 안무가, 박동우 무대디자이너의 참여로
        흡인력 강한 스토리와 버라이어티한 전개 그리고 매력적인 캐릭터를 무대 위에 그려내며
        슬프도록 아름다운 그녀의 내면을 섬세하고 감성적인 음악과 연출을 통해 관객의 마음을 울릴 것이다.

[시놉시스]
        사랑스럽던 한 여자의 잔혹한 질주
        죽을 만큼 세상을 사랑한 여자, 마츠코
        도쿄에서 백수 생활을 하던 ‘쇼’앞에 어느 날 고향의 아버지가 방문한다.
        행방불명 되었던 고모 ‘마츠코’가 사체로 발견되었으니 유품을 정리하라는 것.

        허물어져가는 아파트에서 이웃들에게
        ''혐오스런 마츠코'' 라고 불리며 살았던 그녀의 물건을 정리하면서,
‘쇼’는 한 번도 만난 적 없는 고모 ‘마츠코’의 일생 속으로 들어가게 된다.

        31년 전 착하고 바른 중학교 교사였던 ‘마츠코’가
        사람들이 괴물 보듯 피하는 폐인이 되기까지 도대체 어떤 일들이 있었던 걸까?', '', 2, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (17, '클래식 뮤지컬 ''첫사랑''','30년 만에 걸려온 첫사랑의 전화…
        설레는 마음으로 길을 나서는 남자!
                말없이 떠나버린 첫사랑, 왜 떠났던 것일까?

        전 세대를 아울러 사랑받아온 가곡과
        주옥같은 오페라 아리아 멜로디에
        우리말 가사를 붙여
        ''첫사랑''의 설렘과 그리움을
        아름답게 담아냈다.', '', 2, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (18, '뮤지컬 - 카라마조프','아버지의 살인을 둘러 싼 치열한 법정 추리극

                톨스토이와 더불어 19세기 러시아 문학을 대표하는 작가이자 세계문학의 거장인 도스토옙스키(Dostoevsky, Fedor Mikhaylovich)의 <카라마조프가의 형제들>(1880)을 소재로 하여 뮤지컬로 각색한 작품이다. 러시아어로 ''검은 얼룩''이라는 뜻을 지닌 이 작품은 2017년 공연예술창작산실 ''올해의 신작''으로 선정되어 대학로 아르코예술극장 대극자에서 초연된다.', '', 2, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (19, '클래식 뮤지컬 ''베토벤''','한밤중 판사의 방문을 거세게 두드리는 사나이가 있었다.
        소송을 하고 있는 베토벤이었다. 판사가 나가달라고 하자
        당신은 지위로 말하면 일개 판사고 나는 음악의 왕이야! 소리친다.
                베토벤의 말대로 음악의 왕이 한밤중 판사를 만나러 간 이유는 무엇일까?

                인류 역사상 누구도 넘볼 수 없는 베토벤의 음악과 함께 예술과 사랑, 꿈과 현실,
                인간과 신의 세계를 넘나드는 베토벤의 삶이 흥미진진하게 펼쳐지는데…', '', 2, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (20, '뮤지컬 - 김종욱찾기','“안녕하십니까! 첫사랑을 찾아드리는 첫사랑 찾기 사무소입니다!”2:8 가르마에 호리호리한 체형, 돌다리도 두드려 보고 건너는 고지식한 성격의 소유자 한기준(공유). 지나치게 강한 책임감과 융통성 제로에 가까운 성격 덕에 회사에서 잘린 기준은 우연한 기회에 기발한 창업 아이템을 찾아 낸다. 바로 아직까지 첫사랑을 잊지 못한 사람들을 위해 첫사랑을 찾아주는 일! 기준은 고객에게 무한 감동을 선사하겠다는 열정과 패기로 지체 없이 1인 기업 ‘첫사랑 찾기 사무소’를 오픈 한다. 턱 선의 외로운 각도, 콧날의 날카로운 지성, 깊고도 낭만적인 목소리...“제 첫사랑의 이름은 김.종.욱…”남자친구로부터 프러포즈를 받은 서지우(임수정)는 딸이 시집가기만을 학수고대하는 홀 아버지의 압박에도 불구하고 결국 프러포즈를 거절하고 만다. 뮤지컬 무대 감독으로 일에만 매달려온 그녀 맘 속에는 ‘김종욱’이라는 잊을 수 없는 사람이 있기 때문. 아버지에게 등 떠밀려 ‘첫사랑 찾기 사무소’를 찾은 서지우는 우여곡절 끝에 이제는 기억 조차 희미한 첫사랑의 상대 김종욱을 찾아보기로 결심한다. “그렇다고 제가 일일이 따라다녀요? 하는 일 관두구?”“정보도 빈약한데, 의지까지 희박해서야 되겠습니까?”지우가 자신의 첫사랑에 대해 알고 있는 정보는 ‘김종욱’이라는 이름 석자가 전부. 하지만 회사의 흥망성쇠를 결정할 첫 의뢰를 성공시키기 위해 필사적인 한기준은 특유의 치밀함과 꼼꼼함으로 ‘김종욱’의 실체에 한 걸음씩 다가간다. 서지우가 고객감동을 위한 한기준의 지나친 열의에 화가 나다 못해 점점 지쳐갈 무렵, 한기준은 전국에 대량 분포(?) 중인 김종욱들을 찾아나서는 여정에 동행할 것을 서지우에게 요구하는데…', '', 2, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (21, '5TARDIUM 2018','Surrealistic EDM Festival with Magnificent Stage and Performances. 2 Days, 10 Artists for 5th Anniversary!', '', 3, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (22, '노브레인 연말 콘서트 [락이 먼저다]','노브레인 연말 콘서트 2017.12.30.(SAT) 저녁 7시 @롤링홀', '', 3, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (23, '[크리스마스 살롱콘서트] 재즈 보컬 듀엣 허성&마리아킴','[크리스마스 살롱 콘서트]
                재즈 보컬 듀엣 허성 & 마리아킴
                Christmas Salon Concert:
        Jazz Vocalist Duet Sung Huh & Maria Kim

        2017년 12월24일 (일) 1회차: 오후 7시 / 2회차: 오후 9시
        강남역 카페 피아노 리브레
        비지정석

        크리스마스 이브! 연인과 친구, 가족들과 함께 아늑한 강남역 피아노 리브레에서 감미로운 재즈 살롱 콘서트를 즐기세요. 모두가 즐길 수 있는 재즈 러브송 들과 크리스마스 캐롤을 들려드립니다. 공연을 관람하시는 분께는 피아노 리브레 시그니쳐 샹그리아와 허성/마리아킴의 앨범 한장을 증정해 드립니다.
        ', '', 3, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (24, 'Odd Christmas
        ','데미안 라이스도 감탄한 거리의 연주자, 어쿠스틱 혼성 듀오
        오드트리(Odd Tree) 단독콘서트 ''Odd Christmas'' 개최 @벨로주

        이번 ''Odd Christmas''에서는 연말을 맞이하여
        크리스마스 캐럴을 포함, 그 동안 선보인 적 없던 다양한 곡을
        오드트리 만의 감성으로 풀어내며 관객들에게 포근하고 아늑한 선물 같은 시간을 선사하고자 합니다.

        이번 공연은 오드트리가 관객들에게 드리는 ''선물''인 만큼, 선착순 30명에 한하여 1+1로 예매 가능합니다.
        ', '', 3, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (25, '뮤지컬 콘서트 ''12월의 선물''
        ','국내 최초의 뮤지컬 전용극장 샤롯데 씨어터를 운영하며 공연 산업 발전에 기여해 온 롯데엔터테인먼트와
                <지킬앤하이드>, <맨오브라만차>, <드라큘라>등
        대한민국 뮤지컬 시장을 선도해 온 오디컴퍼니가 선사하는 윈터 프로젝트!
                뮤지컬 콘서트 <12월의 선물>

        가족, 연인, 친구, 사랑하는 사람들과 함께 하세요.
        2017년 연말, 크리스마스를 환상적인 음악의 향연으로 채워 줄 기적 같은 선물이 찾아옵니다.
        ', '', 3, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (26, 'RAPBEAT SHOW2017
        ','차별화된 라인업으로 돌아온 RAPBEAT SHOW！
        색다른 DNA를 가진, Different & Another 출연진을
        RAPBEAT SHOW 2017 with SAMSUNG MUSIC을 통해 선보입니다.

                다른 곳에서는 볼 수 없는 아티스트들의 무대를 기대하셔도 좋습니다.
        RAPBEAT SHOW 2017 은 Music & More, Samsung Music과 함께합니다.
        새롭고 더욱 편리해진 Samsung Music은 특별한 음악 경험을 드리는 삼성 갤럭시에 최적화된 음악서비스 입니다.

        ', '', 3, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (27, '러브레터 - 이와이 슌지 시네마 콘서트
        ','스톰프뮤직 창립 20주년을 기념하는 아날로그 감성을 깨우는 시간.
        이와이 슈운지 감독의 영화 [러브레터]와 [4월이야기]의 명장면들과
        OST를 라이브 연주로 감상하는 콘서트.
                피아니스트 윤한과 오케스트라가 만들어내는 완성도 높은 사운드를 통해 한층 더 깊어진 울림을 선사할 예정이다.', '', 3, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (28, '거미 콘서트 Feel the Voice Season3
        ','9년만의 정규앨범<STROKE>로 돌아온 거미는 1년 내내 공연으로 전국의 관객들을 만나며 음악으로 꽉 채운 한 해를 보냈습니다.
        그리고 이번 연말, 추운 겨울을 따뜻하게 녹여줄 아름다운 무대로 당신에게 잊지 못할 한 해의 마지막 선문하려 합니다.
                당신의 올 해가 그 누구보다 행복했기를 바라며, 힘들었을 누군가에겐 위로가 되어 줄 수 있는 음악으로 올 연말, 당신을 기다리겠습니다.', '', 3, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (29, '2017국카스텐 연말 전국투어[HAPPENING]
        ','더 이상의 수식어가 필요없는 국카스텐의 연말 투어 HAPPENING
        ', '', 3, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (30, '2017버즈전국투어콘서트 ''JUST ONE''','버즈와 함께하는 단 하나의 특별한 시간 JUST ONE
        2017년 연말을 더욱 따뜻한 이야기로 그려낼 JUST ONE
        가장 멋진 내일이 시작될 순간 JUST ONE
        2017 BUZZ CONCERT JUST ONE

        버즈 전국투어 콘서트 [JUST ONE]이 시작됩니다.
        미니앨범 BE ONE 발매와 함께 많은 사랑을 받은 버즈가
        전국 투어 콘서트로 여러분을 찾아갑니다.

‘사랑하지 않은 것처럼’을 비롯한 미니앨범의 신곡들은 물론.
                남자를 몰라,겁쟁이, 나에게로 떠나는 여행등
        노래방 인기순위 랭킹의 곡들을 버즈와 함께 떼창 하는 시간!
                넘치는 흥을 주체할 수 없도록 신나게 달릴 메들리 곡들까지 만나볼 수 있는 다채로운 콘서트!

                2017년, 여러분의 연말을 가장 특별하게 마무리 할 단 하나의 공연 JUST ONE
        Band 버즈의 빛나는 모습으로 가득할 공연장으로 여러분을 초대합니다.', '', 3, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (31, '창작국악팀 더미소
        ','전통을 기반으로 한 독특한 선율과 서양의 리드미컬한 악기가 더해져 흥겹고 다채로운 음악을 청중들과 소통하고 즐겨보자는 취지로 결성되었습니다.
                *제9회 21C한국음악프로젝트 대상 수상
                (문화체육관광부장관상)', '', 4, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (32, 'SILLA 바실라','2017 정동극장-경주세계문화엑스포 공동기획공연 <바실라>

                더욱 강렬하게 돌아왔다!

                - 정동극장 경주브랜드공연 업그레이드 <SILLA 바실라>

        천년의 역사, 신라 왕국의 숨겨진 이야기

        오직 경주에서 만날 수 있는 단 하나의 절대적인 퍼포먼스 -', '', 4, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (33, '동지동지 冬至同志','2017년 12월 22일 (금)
                동짓날을 맞이하여
                관객과 함께 동지팥죽 한그릇과
                신명하는 전통공연을 선사한다.', '', 4, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (34, '카마라타 크리스마스 특집 콘서트
        ','카마라타뮤직컴퍼니는 국내 최대 규모의 다국적 클래식 음악 비영리 단체로, 세계적 언어인 음악을 통해 글로벌 커뮤니티를 만들고 있습니다. 남아프리카공화국의 노래인 Hope For Resolution (화합을 향한 소망)을 부르며 탈북민과의 교감을 기원합니다.
                또한, 크리스마스를 맞이하여 다양한 캐롤을 준비하여 크리스마스 축제 분위기와 즐거운 연말 분위기를 느끼실 수 있습니다.', '', 4, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (35, '터 무시무종 vol.2
        ','이 작품은 2010년 제31회 서울무용제에서 대상, 음악상, 여자연기상 등 3관왕을 수상한 작품이다. 초연당시 40분이었던 작품을 60분짜리로 확장하여 부족했던 부분을 보완하고자 한다.
        서울무용제 수상작이라는 점에서 작품성을 인정받았다고 할 수 있다.', '', 4, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (36, '리스트 피아노 콩쿠르 우승자 비탈리 피사렌코 리사이틀','“천부적으로 재능을 타고난, 전무후무한 피아니스트” –뉴욕 타임즈

                화려한 테크닉을 지닌 피아노의 귀신 리스트와
                가곡의 왕, 로맨티스트 슈베르트를 함께 만나보는 시간.
                        리스트 콩쿠르의 황태자, 비탈리 피사렌코의 첫 내한 리사이틀.', '', 4, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (37, '창작악단 제94회 정기연주회
        ','퍼시픽림 페스티벌
        2017년 12월 1일(금) 20시 @국립국악원 예악당
        문의 02-580-3300 www.gugak.go.kr', '', 4, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (38, '세종의 신악
        ','뿌리 깊은 나무는 바람에 아니 흔들릴새''
        백성을 사랑하는 세종의 마음이 담긴 용비어천가를 우리 음악과 춤으로 선보입니다.
        한국무용과 음악이 어우러진 장중하고 위엄있는 무대로 특별한 경험을 선물합니다.
                한해를 보내고 새로운 해를 맞이하는 송년.
                귀한 당신을 극진히 모시겠습니다.', '', 4, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (39, '라 트라비아타
        ','장장 4개월동안 펼쳐지는
        오페라를 경험해보지 못한 사람이라면 꼭 봐야 할
        쉽고 재미있지만 품격있는 공연', '', 4, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (40, '민요 피쳐링 되다
        ','민요는 다양한 노랫말과 반복적인 선율을 통해 삶을 노래한다.
        삶의 고단함을 흥겨운 장단에 맞추어 제각기 일을 하면서 부르기도 하고 이별의 아픔을 덤덤하게 부르기도 한다.
                이러한 민요를 모티브로 하여 현 시대의 감수성을 담고자 힙합 VJ 전자음악 등 다양한 장르가 민요라는 큰 그릇 안에 어우러지서 현 시대를 살아가는 사람들과 불러보고자 한다.', '', 4, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (41, '죽여주는이야기','자살업계에서 알아주는 ''안락사''
        그러던 어느날. 이 남자앞에 나타난 정체를 알수없는 신비한 여자.
                그리고 그 여자가 데려온 멍청한 사내.
                죽여주는 곳에서 그들의 사연이 하나씩 밝혀지면서
        서로의 실체가 하나씩 드러난다.
        이들은 과연 본인이 원하는 최후를 맞이할 수 있을 것인가.', '', 5, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (42, '뮤직드라마 - 당신만이
        ','당신만이 사랑입니다♥ 버럭쟁이 봉식이와 변덕쟁이 필례의 좌충우돌 인생STORY!!!', '', 5, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (43, '연극 [연꽃정원]
        ','전석 무료 (자유석, 예약 필수, 120분 공연 예정)

        ', '', 5, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (44, '어바웃 머니
        ','<변치 않는 사랑을 하고 있다면, 이 연극을 선물해 보세요>

“괜찮아! 우린 사랑하니까!”
        이 한마디만 믿고 반 지하 원룸에서 신혼생활을 시작했다.
        하지만 현실은, 툭하면 바퀴벌레 출몰에, 물은 허구한 날 끊기고,
                돈 들어갈 곳은 또 왜 이렇게 많은 거야ㅠㅠ
        정말 괜찮은 거 맞아? 우린 사랑하니까?

        그러던 어느 날,
                받는 고통만큼 무엇이든 쏟아내는 꽃병을 우연히 손에 넣게 되고,
        두 사람의 인생은 제 2막을 맞이하게 되는데..

        세상에 공짜는 없다! 돈을 얻으려는 자, 고통을 견뎌라?!

                ', '', 5, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (45, '보잉보잉 공연
        ','웃다가 기절하는 연극
        관람 연령대의 한계가 없는 연극
        썸남썸녀라면 꼭 봐야하는 연극', '', 5, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (46, '연극 THE HELMET(더 헬멧)
        ','어디서도 본 적 없지만 빠져들게 될 당신의‘첫 번째’ 연극!

                대학로 창작 연극의 저력을 보여주며, 올해로 10주년을 맞이한 연극 <모범생들>
        극장 이곳 저곳을 다니며 극장과 롤 플레잉 게임 형식을 접목한 관객 참여 형 연극 <씨어터 RPG>
        특정된 공간 안에서 벌어지는 세 가지의 독립된 에피소드 선보인 카포네, 벙커 트릴로지 시리즈
        파격적인 구성과 신선한 아이디어를 접목해 공연을 만들어 온 창작 연극의 자존심, 최고의 콤비!
                작가 지이선X연출 김태형과 함께 혁신적인 공연 형식을 성공적으로 선보여온 아이엠컬처의 창작 신작!
                ', '', 5, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (47, '연극 밀레니엄 소년단
        ','1999년 12월 31일 밤,
        고등학교 1학년인 네 명의 친구가 밀레니엄을 맞이하는 순간, 작은 녹음기에 녹음을 시작한다.

                상처받은 과거와 보이지않는 미래 때문에 아프기도 하지만, 네 사람은 진정한 우정을 꿈꾸며 지금의 불안과 두려움을 떨치고 앞으로 나아갈 수 있으리라 믿는다.

                그러나 2000년이 다 지나기도 전에, 친구들의 우정은 보이지 않는 금이 가기 시작하고, 그 중 한명이 뜻하지 않은 사고로 뇌사 상태에 빠진다. 2012년 뇌사 상태에 빠졌던 친구가 깨어나 모두 다시 모이게 되자, 팍팍한 현실, 변해버린 관계 속에서 이들은 자꾸 어긋나기만 하는 데...

        그때 우리는
        지구의 멸망을 이겨낸 용사들, 밀레니엄 소년단이었습니다.
        ', '', 5, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (48, '전쟁터 산책
        ','극단 애인 10주년 기념공연 연극 <전쟁터 산책>

        ', '', 5, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (49, '수상한흥신소1탄!','특별한 능력을 지닌, 백수고시생! 오상우! 그의 기상천외한 비지니스가 시작된다!!
        ', '', 5, now(), now());
        insert into product (id, description, content, event, category_id, create_date, modify_date) values (50, '세대공감 음악극 [사는게 꽃같네] ','가까이 있으면서도 무심했던
                우리 가족의 모습을 더 들여다보고 싶게 하는 이야기
                세대공감음악극 [사는게 꽃같네]', '', 5, now(), now());



        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 1,1, '- 관람시간: 화, 수, 금, 일요일 오전 10시 - 오후 6시 *전시 종료 30분 전 입장 마감됩니다.
                - 야간개관: 목, 토요일 오전 10시 - 저녁 8시 *전시 종료 30분 전 입장 마감됩니다.
        - 휴관안내: 전시 기간 중 매주 월요일 휴무이며, 설 연휴인 2018년 2월 15일, 16일 휴관입니다.
        12월 25일 (월요일) 정상 운영됩니다.', '대림미술관', '서울특별시 종로구 통의동 35-1', '서울특별시 종로구 자하문로4길 21 대림미술관', '02-6403-9961', 'daelimmuseum.org', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 2,2, '- 전시기간: 2017.10.21(토) – 2018.2.20(화)
                - 운영시간: 월-목 am 11:00 - pm 20:00 (19:00 입장마감) / 금-일 am 11:00 - pm 21:00 (20:00 입장마감)
        - 도슨트: 매주 금, 토 14:00, 16:00, 18:00 (3회) / 무료 오디오 가이드 상시 운영', 'KT&G 상상마당 홍대 갤러리 (4, 5F)', '서울특별시 마포구 서교동 367-5', '서울특별시 마포구 어울마당로 65 상상마당빌딩', '', 'https://www.sangsangmadang.com/display/detail/435', 'gallery@sangsangmadang.com', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 3,3, '- 관람시간: 오전 10시 ~ 오후 7시(입장마감 6시)
                - 월요일 휴관
                - 공휴일 정상개관(크리스마스/신정 정상개관)
                - 관람종료 1시간 전까지 발권 및 입장이 가능합니다.
                - 체험존은 관람종료 15분전에 마감합니다.', '서울숲 갤러리아포레 The Seouliteum G층 (B2)
        ', '서울특별시 성동구 성수동1가 685-696', '서울특별시 성동구 왕십리로 85 갤러리아포레
        ', '1522-1796', 'http://thealice.co.kr', 'medianart@mnart.co.kr', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 4,4, '- 2017.12.14.(목)~2017.12.17.(일)
                - 평일&주말 09:00~18:00
                - 연중무휴

        ', '코엑스 Hall B', '서울특별시 강남구 삼성동 159 1층', '서울특별시 강남구 영동대로 513 코엑스
        ', '02-866-6480
        ', 'http://www.smartix.co.kr/', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 5,5, '- 2017.12.22.(금)~2018.3.4.(일)
                - 12월, 1월, 2월: 11:00 ~ 19:00 (마지막 입장: 18시)
        - 3월: 11:00 ~ 20:00 (마지막 입장: 19시)
        (매월 마지막 주 월요일 휴관. 단, 크리스마스와 설연휴는 정상운영합니다.)', '예술의전당 한가람미술관 3층(5,6 전시실)
        ', '서울특별시 서초구 서초동 700', '서울특별시 서초구 남부순환로 2406 예술의전당
        ', '02-6273-4242
        ', 'https://girard.modoo.at/', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 6,6, '- 2017.12.14.(목)~2017.12.17.(일)', '코엑스 Hall B', '서울특별시 강남구 삼성동 159 1층', '서울특별시 강남구 영동대로 513 코엑스', '02-866-6480
        ', 'http://www.smartix.co.kr/
        ', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 7,7, '2017. 12. 15(Fri) – 17(Sun)
                11am – 18pm', '홍익대 대학로 아트센터 갤러리
        ', '서울특별시 종로구 연건동 128-8 홍익대 대학로 아트센터
        ', '서울특별시 종로구 대학로 57 홍익대학교대학로캠퍼스
        ', '010-2794-2936', 'http://pp-graphic.com', 'about@pp-graphic.com
        ', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 8,8, '- (매주 월요일 휴관 , 추석 연휴 10월 4일(수)~5일(목), 설 연휴 휴관)

                -관람 시간 : 화, 수, 목, 일요일 - 오전 10시 ~ 저녁 6시 (입장마감: 전시 종료 30분전)
        금요일, 토요일 – 오전 10시 ~ 저녁 8시 (입장마감: 전시 종료 30분전)
        도슨트 투어: 매시 정각 (11, 13, 14, 15, 16, 17시)
        정규 도슨트 투어는 미술관 사정에 따라 취소 될 수 있습니다.
        ', '디뮤지엄', '서울특별시 용산구 한남동 50-1 Replace한남 F동', '서울특별시 용산구 독서당로29길 5-6 Replace한남 F동', '02-6403-9961
        ', 'daelimmuseum.org/dmuseum', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 9,9, '기간 : 2017.09.02(토) ~ 2017.12.03(일)
                시간 : 11:00-20:00 (입장마감 오후 7시), 11월은 11:00-19:00 (입장마감 오후 6시) ※ 매월 마지막주 월요일 휴관 (9/25, 10/30)', '한가림미술관 제1전시실, 제2전시실', '서울시 서초구 서초동 700번지', '울시 서초구 남부순환로 2406 예술의전당', '02-837-6611', 'https://www.sacticket.co.kr/SacHome/exhibit/detail?searchSeq=30191', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 10,10, '전시기간 : 2017년 12월9일(토) ~ 2018년 3월11일(일)
                관람시간 : 11:00 ~ 19:00 (18:00 입장마감) 12월~2월
        11:00 ~ 20:00 (19:00 입장마감) 3월', '예술의전당 한가람미술관 1층', '서울특별시 서초구 서초동 700', '서울특별시 서초구 남부순환로 2406 예술의전당
        ', '031-8038-3912
        ', 'https://marie2017.modoo.at/
        ', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 11,11, '2017년 12월 5일(화) ~ 2018년 1월 31일
        ', '충무아트센터 대극장', '서울특별시 중구 흥인동 131', '서울특별시 중구 퇴계로 387 충무아트홀
        ', '02-1644-2620', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 12,12, '2017년 11월 24일(금) ~ 2018년 1월 21일(일)', '홍익대 대학로 아트센터 대극장', '서울특별시 종로구 연건동 128-8', '서울특별시 종로구 대학로 57 홍익대학교대학로캠퍼스', '1577-3363
        ', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 13,13, '2017년 12월 15일(금) ~ 2018년 1월 14일(일)', '세종문화회관 대극장', '서울특별시 종로구 세종로 81-3', '서울특별시 종로구 세종대로 175 세종이야기
        ', '02-399-1000', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 14,14, '2018년 1월 27일(토) ~ 2018년 3월 4일(일)
                공연시간 : 화 ~ 일, 2월 17일(토) 11시, 14시', '국릴중앙박물관 극장용', '서울특별시 용산구 용산동6가 168-6', '서울특별시 용산구 서빙고로 137', '1566-5588', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 15,15, '2017년 9월 25일 ~ 2017년 12월 31일', '성균관대학교 새천년홀', '서울특별시 종로구 명륜3가 53', '서울특별시 종로구 성균관로 41', '02-740-1944', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 16,16, '2017년 10월 27일(금) ~ 2018년 1월 7일(일)', '두산아트센터 연강홀', '서울특별시 종로구 연지동 270', '서울특별시 종로구 종로33길 15 두산아트센터', '02-1588-5212', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 17,17, '- 2017년 12월 5일(화) 7시 30분
                - 2017년 12월 13일(수) 7시 30분
                - 2017년 12월 16일(토) 3시
                - 2017년 12월 23일(토) 7시
                - 2017년 12월 25일(월) 4시', '흰물결아트센터', '울특별시 서초구 서초동 1720-4', '서울특별시 서초구 반포대로 150', '02-535-7034', 'http://www.whitehall.kr', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 18,18, '- 2018년 1월 3일 ~ 2018년 1월 14일
                - 평일 오후 8시, 토요일 오후 3시 7시, 일요일 2시 6시(월요일 공연 없음)', '아르코예술극장 대극장', '서울특별시 종로구 대학로8길 7 한국문예회관
        ', '서울특별시 종로구 동숭동 1-111', '02-6339-1232
        ', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 19,19, '- 개막공연 : 2017년 10월 21일(토) 오후 3시
                - 2017년 12월 5일(토) 3시
                - 2017년 12월 16일(토) 7시
                - 2017년 12월 21일(목) 7시 30분
                - 2017년 12월 23일(토) 3시', '흰물결아트센터 화이트홀', '울특별시 서초구 서초동 1720-4', '서울특별시 서초구 반포대로 150', '02-535-7034', 'http://www.whitehall.kr', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 20,20, '- 평일 (월~금) 2시, 5시, 8시
                - 주말 (토~일) 1시, 3시10분, 5시20분, 7시30분
        ', '대학로 쁘띠첼씨어터', '서울특별시 종로구 동숭동 1-144', '서울특별시 종로구 대학로12길 73 낙산재
        ', '02-766-7667', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 21,21, '2017년 12월 12일(토) 12PM', '장소 추후 공개', '서울특별시 중구 태평로1가 31', '서울특별시 중구 세종대로 110', '010-3360-7846', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 22,22, '2017.12.30.(SAT) 저녁 7시
        ', '@롤링홀', '서울특별시 마포구 서교동 402-22', '서울특별시 마포구 어울마당로 35', '02-322-8487', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 23,23, '2017년 12월24일 (일) 1회차: 오후 7시 / 2회차: 오후 9시
        ', '카페 피아노 리브레 강남점
        ', '서울특별시 강남구 역삼동 818-5 혜진빌딩 2층', '서울특별시 강남구 강남대로96길 20 혜진빌딩', '02-554-9913', 'https://youtu.be/BuaEQSxVR5A', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 24,24, '2017년 12월 22일(금) 오후 8시', '망원동 벨로주', '서울특별시 마포구 망원동 422-27 메디움빌딩 4층', '서울특별시 마포구 포은로 117 메디움빌딩 4층
        ', '02-2644-4315', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 25,25, '- 12월 24일(일) 오후 3시
                - 12월 24일(일) 오후 7시 30분
                - 12월 25일(월) 오후 3시
                - 12월 25일(월) 오후 7시 30분
        ', '8층 롯데콘서트홀', '서울특별시 송파구 신천동 29 롯데월드몰', '서울특별시 송파구 올림픽로 300 롯데월드몰', '02-1588-5212', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 26,26, '2017.12.17 7PM', 'YES24라이브홀', '서울특별시 광진구 광장동 319-33', '서울특별시 광진구 구천면로 20', '02-6012-7135', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 27,27, '2018.03.25 5PM', '예술의전당 콘서트홀', '서울특별시 서초구 서초동 700', '서울특별시 서초구 남부순환로 2406 예술의전당', '02-2658-3546', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 28,28, '- 일자 : 2017년 12월 30일(토) ~ 12월 31일(일)
                - 시간 : 토요일 오후 6시 / 일요일 오후 5시
        ', '세종대학교 대양홀', '서울특별시 광진구 군자동 98', '서울특별시 광진구 능동로 209 세종대학교', '02-1644-5690', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 29,29, '- 일자 : 2017년 12월 24일(일) ~ 12월 25일(월)
                - 시간 : 일요일 오후 7시 / 월요일 오후 6시', '서울 잠실실내체육관', '서울특별시 송파구 잠실동 10', '서울특별시 송파구 올림픽로 25 서울종합운동장', '1644-5690', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 30,29, '2017년 12월 17일 (일) 6PM', '대전 컨벤션센터(DCC) 1F 전시홀', '대전광역시 유성구 도룡동 4-19', '대전광역시 유성구 엑스포로 107 대전컨벤션센터', '1588-2532', 'http://www.ggconcert.net', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 31,29, '2017년 11월 25일(토) 오후 7시', 'KBS부산홀', '부산광역시 수영구 남천동 63', '부산광역시 수영구 수영로 429 KBS부산방송총국', '1566-5490', 'http://www.wsmi.co.kr', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 32,30, '11월 18일(토) PM7 , 19일(일) PM5', '경기도문화의전당 대극장', '경기도 수원시 팔달구 인계동 1117', '경기도 수원시 팔달구 효원로307번길 20 경기도문화의전당', '02-1599-2005', 'http://www.sjticket.com', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 33,30, '12월 02일(토) PM7', 'KBS창원홀', '경상남도 창원시 의창구 신월동 97-4', '경상남도 창원시 의창구 중앙대로 178 KBS창원방송총국', '02-1599-2006', 'http://www.sjticket.com', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 34,30, '12월 09일(토) PM7', 'KBS울산홀', '울산광역시 남구 달동 416-7', '울산광역시 남구 번영로 212 한국방송공사', '02-1599-2007', 'http://www.sjticket.com', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 35,30, '12월 17일(일) PM5', 'KBS부산홀', '부산광역시 수영구 남천동 63', '부산광역시 수영구 수영로 429 KBS부산방송총국', '02-1599-2008', 'http://www.sjticket.com', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 36,30, '12월 23일(토) PM7 , 24일(일) PM5', '경북대학교 대강당', '대구광역시 북구 산격동 1370-1', '대구광역시 북구 대학로 80 경북대학교', '02-1599-2009', 'http://www.sjticket.com', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 37,31, '43072.6666666667', '소월아트홀 4층 더미소', '경기도 수원시 권선구 고색동 533-7', '경기도 수원시 권선구 매송고색로 646
        ', '010-6365-5114
        ', 'https://www.facebook.com/themiso12
        ', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 38,32, '2017.03.27~2017.12.31
                월~토 19:30', '경주 세계문화 엑스포 문화센터 공연장', '경상북도 경주시 천군동 130', '경상북도 경주시 경감로 614', '054-740-3800', 'http://www.jeongdong.or.kr', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 39,33, '2017년 12월 22일 (금)
        ', '민속극장풍류
        ', '서울특별시 강남구 삼성동 112-2', '서울특별시 강남구 봉은사로 406 서울중요무형문화재전수회관
        ', '02-3011-2178
        ', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 40,34, '12월 9일(토) 오후 7시 30분', '양재동 온누리교회 횃불회관', '서울특별시 서초구 양재동 55
        ', '서울특별시 서초구 바우뫼로31길 70 횃불선교회관
        ', '010-9806-8655
        ', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 41,35, '2017.12.03 Sun 5:00 PM', '마포아트센터 아트홀 맥', '서울특별시 마포구 대흥동 30-3
        ', '서울특별시 마포구 대흥로20길 28 마포아트센터
        ', '02-2263-4680', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 42,36, '2017.11.25 Sat 8:00 PM', '예술의 전당 IBK 챔버홀', '서울특별시 강서구 등촌3동 667-7 상지빌딩 3층', '서울특별시 강서구 강서로56길 110 상지빌딩 3층', '02-2658-3546', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 43,37, '2017년 12월 1일(금) 20시', '국립국악원 예악당', '서울특별시 서초구 서초3동 700', '서울특별시 서초구 남부순환로 2364 국립국악원
        ', '02-580-3300', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 44,38, '- 2017.12.22-12.27
                - 평일 8시, 주말 3시
                - 월 공연없음', '국립국악원 예악당', '서울특별시 서초구 서초3동 700', '서울특별시 서초구 남부순환로 2364 국립국악원
        ', '02-580-3300', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 45,39, '- 2017.9.8 ~ 12.31
                - 평일 20시
                - 주말, 공휴일 18시', '산암빌딩 지하1층 초콜릿팩토리 극장
        ', '부산광역시 남구 대연동 72-7', '부산광역시 남구 수영로 298
        ', '051-621-4005
        ', 'http://yes-weare.com', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 46,40, '일시 : 2017년 11월 30일(목)
                시간 : PM 8', '홍대 롤링홀', '서울특별시 마포구 서교동 402-22', '서울특별시 마포구 어울마당로 35
        ', '02-6481-1213
        ', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 47,41, '2017.12.8~2018.1.7', '창원오피스텔 지하2층 창원축제소극장
        ', '경상남도 창원시 의창구 용호동 73-49', '경상남도 창원시 의창구 용지로153번길 3 창원오피스텔
        ', '055-1899-9498', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 48,41, '2008.5.15~', '대학로 삼형제극장(죽여주는이야기 전용관)
        ', '서울특별시 종로구 동숭동 199-33', '서울특별시 종로구 이화장길 72
        ', '02-6326-1333
        ', 'http://www.ticketlink.co.kr/
        ', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 49,42, '- 공연기간 : OPEN RUN
                - 공연시간 : 수~금 20시, 토 15시 18시30분, 일 15시, 월화 공연없음
                - 공연장소 : J', '대학로 한성아트트홀 2관', '서울특별시 종로구 이화동 139', '서울특별시 종로구 이화장길 26 대학로 JTN 아트홀
        ', '070-8245-2602', 'https://www.facebook.com/domo2602
        ', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 50,43, '2017년 11월 9일 (목) - 12일 (일)
                목,금 8시 / 토,일 4시', '서강대 메리홀 소극장
        ', '서울특별시 마포구 신수동 1-1
        ', '서울특별시 마포구 백범로 35
        ', '010-9514-7626
        ', 'http://projectsf.blog.me/', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 51,44, '공연기간 : 2016.04.29~OPEN RUN
                공연시간 : 평일 7시 30분 / 주말 및 공휴일 2시, 4시
　　 　 　 (월요일 휴무)', '대학로 아루또소극장', ' 서울특별시 종로구 동숭동 1-47', '서울특별시 종로구 대학로8가길 48
        ', '02-6012-2511
        ', 'https://www.facebook.com/arutto
        ', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 52,45, '2017.12.23~26', '광주문화예술회관 소극장', '광주광역시 북구 운암동 328-16', '광주광역시 북구 북문대로 60
        ', '070-4279-5071', 'http://www.lscompany.co.kr
        ', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 53,45, '2017.12.17.Fri ~ 12.31.Sun', '문화예술전용극장 CT
        ', '대구광역시 중구 남일동 109-2 제일빌딩 지하 1층', '대구광역시 중구 중앙대로 394
        ', '053-252-5733
        ', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 54,46, '공연일시 :2017년 12월 19일(화)~2018년 3월 4일
                공연시간 :화-금 8시, 9시 30분 /
                토, 일,공휴일 3시, 5시, 7시 30분 (월 공연 없음)
                * 12/25(월) 공연 있음,12/26(화) 공연 없음
        12/27(수) 마티네 5시30분,
                1/1(월) 공연 있음 , 1/2(화) 공연 없음', '대학로 아트원씨어터 3관', '서울특별시 종로구 동숭동 1-181', '서울특별시 종로구 대학로12길 83 SW빌딩
        ', '02-541-2929
        ', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 55,47, '2017.11.24~2018.2.4', '5층 동숭소극장
        ', '서울특별시 종로구 동숭동 1-5', '서울특별시 종로구 동숭길 122 동숭아트센타
        ', '02-744-4331
        ', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 56,48, '- 월,화,수 8시
                - 목, 금 3시, 8시
                - 토 3시, 6시
                - 일 3시', '대학로 이음센터 5층 이음홀', '서울특별시 종로구 동숭동 1-117', '서울특별시 종로구 대학로 112 (동숭동, 이음)
        ', '070-8276-0917
        ', '', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 57,49, '43082', '강남아트홀 2관', '서울특별시 서초구 서초동 1307 성우빌리지 B1', '서울특별시 서초구 서초대로73길 30 서초성우빌리지
        ', '070-8129-7420
        ', 'http://cafe.naver.com/extremeplay2010
        ', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 58,49, '2017년 12월 1일 ~ 2017년 12월 31일', '익스트림씨어터1관
        ', '서울특별시 종로구 동숭동 1-33', '서울특별시 종로구 대학로 128
        ', '070-8129-7420
        ', 'http://cafe.naver.com/extremeplay2010
        ', '', now(), now());
        insert into display_info (id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date ) values ( 59,50, '2017.12.20(수)~21.(목)
                16:00, 20:00', '경성대학교 예노소극장', '부산광역시 남구 대연동 산108-5', '부산광역시 남구 수영로 309 경성대학교
        ', '1688-8998
        ', 'http://mcong.kr', '', now(), now());

        insert into display_info_image (id, display_info_id, file_id) values ( 1, 1, 1);
        insert into display_info_image (id, display_info_id, file_id) values ( 2, 2, 2);
        insert into display_info_image (id, display_info_id, file_id) values ( 3, 3, 3);
        insert into display_info_image (id, display_info_id, file_id) values ( 4, 4, 4);
        insert into display_info_image (id, display_info_id, file_id) values ( 5, 5, 5);
        insert into display_info_image (id, display_info_id, file_id) values ( 6, 6, 6);
        insert into display_info_image (id, display_info_id, file_id) values ( 7, 7, 7);
        insert into display_info_image (id, display_info_id, file_id) values ( 8, 8, 8);
        insert into display_info_image (id, display_info_id, file_id) values ( 9, 9, 9);
        insert into display_info_image (id, display_info_id, file_id) values ( 10, 10, 10);
        insert into display_info_image (id, display_info_id, file_id) values ( 11, 11, 11);
        insert into display_info_image (id, display_info_id, file_id) values ( 12, 12, 12);
        insert into display_info_image (id, display_info_id, file_id) values ( 13, 13, 13);
        insert into display_info_image (id, display_info_id, file_id) values ( 14, 14, 14);
        insert into display_info_image (id, display_info_id, file_id) values ( 15, 15, 15);
        insert into display_info_image (id, display_info_id, file_id) values ( 16, 16, 16);
        insert into display_info_image (id, display_info_id, file_id) values ( 17, 17, 17);
        insert into display_info_image (id, display_info_id, file_id) values ( 18, 18, 18);
        insert into display_info_image (id, display_info_id, file_id) values ( 19, 19, 19);
        insert into display_info_image (id, display_info_id, file_id) values ( 20, 20, 20);
        insert into display_info_image (id, display_info_id, file_id) values ( 21, 21, 21);
        insert into display_info_image (id, display_info_id, file_id) values ( 22, 22, 22);
        insert into display_info_image (id, display_info_id, file_id) values ( 23, 23, 23);
        insert into display_info_image (id, display_info_id, file_id) values ( 24, 24, 24);
        insert into display_info_image (id, display_info_id, file_id) values ( 25, 25, 25);
        insert into display_info_image (id, display_info_id, file_id) values ( 26, 26, 26);
        insert into display_info_image (id, display_info_id, file_id) values ( 27, 27, 27);
        insert into display_info_image (id, display_info_id, file_id) values ( 28, 28, 28);
        insert into display_info_image (id, display_info_id, file_id) values ( 29, 29, 29);
        insert into display_info_image (id, display_info_id, file_id) values ( 30, 30, 30);
        insert into display_info_image (id, display_info_id, file_id) values ( 31, 31, 31);
        insert into display_info_image (id, display_info_id, file_id) values ( 32, 32, 32);
        insert into display_info_image (id, display_info_id, file_id) values ( 33, 33, 33);
        insert into display_info_image (id, display_info_id, file_id) values ( 34, 34, 34);
        insert into display_info_image (id, display_info_id, file_id) values ( 35, 35, 35);
        insert into display_info_image (id, display_info_id, file_id) values ( 36, 36, 36);
        insert into display_info_image (id, display_info_id, file_id) values ( 37, 37, 37);
        insert into display_info_image (id, display_info_id, file_id) values ( 38, 38, 38);
        insert into display_info_image (id, display_info_id, file_id) values ( 39, 39, 39);
        insert into display_info_image (id, display_info_id, file_id) values ( 40, 40, 40);
        insert into display_info_image (id, display_info_id, file_id) values ( 41, 41, 41);
        insert into display_info_image (id, display_info_id, file_id) values ( 42, 42, 42);
        insert into display_info_image (id, display_info_id, file_id) values ( 43, 43, 43);
        insert into display_info_image (id, display_info_id, file_id) values ( 44, 44, 44);
        insert into display_info_image (id, display_info_id, file_id) values ( 45, 45, 45);
        insert into display_info_image (id, display_info_id, file_id) values ( 46, 46, 46);
        insert into display_info_image (id, display_info_id, file_id) values ( 47, 47, 47);
        insert into display_info_image (id, display_info_id, file_id) values ( 48, 48, 48);
        insert into display_info_image (id, display_info_id, file_id) values ( 49, 49, 49);
        insert into display_info_image (id, display_info_id, file_id) values ( 50, 50, 50);
        insert into display_info_image (id, display_info_id, file_id) values ( 51, 51, 51);
        insert into display_info_image (id, display_info_id, file_id) values ( 52, 52, 52);
        insert into display_info_image (id, display_info_id, file_id) values ( 53, 53, 53);
        insert into display_info_image (id, display_info_id, file_id) values ( 54, 54, 54);
        insert into display_info_image (id, display_info_id, file_id) values ( 55, 55, 55);
        insert into display_info_image (id, display_info_id, file_id) values ( 56, 56, 56);
        insert into display_info_image (id, display_info_id, file_id) values ( 57, 57, 57);
        insert into display_info_image (id, display_info_id, file_id) values ( 58, 58, 58);
        insert into display_info_image (id, display_info_id, file_id) values ( 59, 59, 59);


        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 60,'1_th_1.png', 'img/1_th_1.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 61,'1_ma_2.png', 'img/1_ma_2.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 62,'2_th_3.png', 'img/2_th_3.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 63,'2_ma_4.png', 'img/2_ma_4.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 64,'2_et_5.png', 'img/2_et_5.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 65,'2_et_6.png', 'img/2_et_6.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 66,'2_et_7.png', 'img/2_et_7.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 67,'2_et_8.png', 'img/2_et_8.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 68,'3_th_9.png', 'img/3_th_9.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 69,'3_ma_10.png', 'img/3_ma_10.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 70,'4_th_11.png', 'img/4_th_11.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 71,'4_ma_12.png', 'img/4_ma_12.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 72,'5_th_13.png', 'img/5_th_13.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 73,'5_ma_14.png', 'img/5_ma_14.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 74,'5_et_15.png', 'img/5_et_15.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 75,'5_et_16.png', 'img/5_et_16.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 76,'5_et_17.png', 'img/5_et_17.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 77,'6_th_18.png', 'img/6_th_18.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 78,'6_ma_19.png', 'img/6_ma_19.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 79,'7_th_20.png', 'img/7_th_20.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 80,'7_ma_21.png', 'img/7_ma_21.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 81,'8_th_22.png', 'img/8_th_22.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 82,'8_ma_23.png', 'img/8_ma_23.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 83,'9_th_24.png', 'img/9_th_24.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 84,'9_ma_25.png', 'img/9_ma_25.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 85,'9_et_26.png', 'img/9_et_26.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 86,'10_th_27.png', 'img/10_th_27.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 87,'10_ma_28.png', 'img/10_ma_28.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 88,'10_et_29.png', 'img/10_et_29.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 89,'11_th_30.png', 'img/11_th_30.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 90,'11_ma_31.png', 'img/11_ma_31.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 91,'12_th_32.png', 'img/12_th_32.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 92,'12_ma_33.png', 'img/12_ma_33.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 93,'13_th_34.png', 'img/13_th_34.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 94,'13_ma_35.png', 'img/13_ma_35.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 95,'14_th_36.png', 'img/14_th_36.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 96,'14_ma_37.png', 'img/14_ma_37.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 97,'15_th_38.png', 'img/15_th_38.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 98,'15_ma_39.png', 'img/15_ma_39.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 99,'16_th_40.png', 'img/16_th_40.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 100,'16_ma_41.png', 'img/16_ma_41.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 101,'17_th_42.png', 'img/17_th_42.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 102,'17_ma_43.png', 'img/17_ma_43.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 103,'17_et_44.png', 'img/17_et_44.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 104,'17_et_45.png', 'img/17_et_45.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 105,'18_th_46.png', 'img/18_th_46.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 106,'18_ma_47.png', 'img/18_ma_47.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 107,'19_th_48.png', 'img/19_th_48.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 108,'19_ma_49.png', 'img/19_ma_49.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 109,'20_th_50.png', 'img/20_th_50.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 110,'20_ma_51.png', 'img/20_ma_51.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 111,'20_et_52.png', 'img/20_et_52.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 112,'21_th_53.png', 'img/21_th_53.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 113,'21_ma_54.png', 'img/21_ma_54.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 114,'22_th_55.png', 'img/22_th_55.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 115,'22_ma_56.png', 'img/22_ma_56.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 116,'23_th_57.png', 'img/23_th_57.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 117,'23_ma_58.png', 'img/23_ma_58.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 118,'24_th_59.png', 'img/24_th_59.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 119,'24_ma_60.png', 'img/24_ma_60.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 120,'25_th_61.png', 'img/25_th_61.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 121,'25_ma_62.png', 'img/25_ma_62.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 122,'26_th_63.png', 'img/26_th_63.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 123,'26_ma_64.png', 'img/26_ma_64.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 124,'27_th_65.png', 'img/27_th_65.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 125,'27_ma_66.png', 'img/27_ma_66.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 126,'28_th_67.png', 'img/28_th_67.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 127,'28_ma_68.png', 'img/28_ma_68.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 128,'29_th_69.png', 'img/29_th_69.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 129,'29_ma_70.png', 'img/29_ma_70.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 130,'30_th_71.png', 'img/30_th_71.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 131,'30_ma_72.png', 'img/30_ma_72.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 132,'31_th_73.png', 'img/31_th_73.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 133,'31_ma_74.png', 'img/31_ma_74.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 134,'31_et_75.png', 'img/31_et_75.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 135,'31_et_76.png', 'img/31_et_76.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 136,'31_et_77.png', 'img/31_et_77.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 137,'32_th_78.png', 'img/32_th_78.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 138,'32_ma_79.png', 'img/32_ma_79.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 139,'32_et_80.png', 'img/32_et_80.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 140,'32_et_81.png', 'img/32_et_81.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 141,'32_et_82.png', 'img/32_et_82.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 142,'33_th_83.png', 'img/33_th_83.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 143,'33_ma_84.png', 'img/33_ma_84.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 144,'34_th_85.png', 'img/34_th_85.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 145,'34_ma_86.png', 'img/34_ma_86.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 146,'34_et_87.png', 'img/34_et_87.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 147,'34_et_88.png', 'img/34_et_88.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 148,'34_et_89.png', 'img/34_et_89.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 149,'35_th_90.png', 'img/35_th_90.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 150,'35_ma_91.png', 'img/35_ma_91.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 151,'36_th_92.png', 'img/36_th_92.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 152,'36_ma_93.png', 'img/36_ma_93.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 153,'37_th_94.png', 'img/37_th_94.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 154,'37_ma_95.png', 'img/37_ma_95.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 155,'38_th_96.png', 'img/38_th_96.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 156,'38_ma_97.png', 'img/38_ma_97.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 157,'39_th_98.png', 'img/39_th_98.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 158,'39_ma_99.png', 'img/39_ma_99.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 159,'39_et_100.png', 'img/39_et_100.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 160,'39_et_101.png', 'img/39_et_101.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 161,'40_th_102.png', 'img/40_th_102.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 162,'40_ma_103.png', 'img/40_ma_103.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 163,'40_et_104.png', 'img/40_et_104.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 164,'41_th_105.png', 'img/41_th_105.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 165,'41_ma_106.png', 'img/41_ma_106.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 166,'42_th_107.png', 'img/42_th_107.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 167,'42_ma_108.png', 'img/42_ma_108.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 168,'42_et_109.png', 'img/42_et_109.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 169,'43_th_110.png', 'img/43_th_110.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 170,'43_ma_111.png', 'img/43_ma_111.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 171,'44_th_112.png', 'img/44_th_112.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 172,'44_ma_113.png', 'img/44_ma_113.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 173,'44_et_114.png', 'img/44_et_114.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 174,'44_et_115.png', 'img/44_et_115.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 175,'45_th_116.png', 'img/45_th_116.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 176,'45_ma_117.png', 'img/45_ma_117.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 177,'45_et_118.png', 'img/45_et_118.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 178,'45_et_119.png', 'img/45_et_119.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 179,'46_th_120.png', 'img/46_th_120.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 180,'46_ma_121.png', 'img/46_ma_121.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 181,'47_th_122.png', 'img/47_th_122.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 182,'47_ma_123.png', 'img/47_ma_123.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 183,'48_th_124.png', 'img/48_th_124.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 184,'48_ma_125.png', 'img/48_ma_125.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 185,'49_th_126.png', 'img/49_th_126.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 186,'49_ma_127.png', 'img/49_ma_127.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 187,'50_th_128.png', 'img/50_th_128.png', 'image/png', 0, now(), now());
        insert into file_info (id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date ) values ( 188,'50_ma_129.png', 'img/50_ma_129.png', 'image/png', 0, now(), now());

        insert into product_image (id, product_id, type, file_id) values ( 1,1, 'th', '60');
        insert into product_image (id, product_id, type, file_id) values ( 2,1, 'ma', '61');
        insert into product_image (id, product_id, type, file_id) values ( 3,2, 'th', '62');
        insert into product_image (id, product_id, type, file_id) values ( 4,2, 'ma', '63');
        insert into product_image (id, product_id, type, file_id) values ( 5,2, 'et', '64');
        insert into product_image (id, product_id, type, file_id) values ( 6,2, 'et', '65');
        insert into product_image (id, product_id, type, file_id) values ( 7,2, 'et', '66');
        insert into product_image (id, product_id, type, file_id) values ( 8,2, 'et', '67');
        insert into product_image (id, product_id, type, file_id) values ( 9,3, 'th', '68');
        insert into product_image (id, product_id, type, file_id) values ( 10,3, 'ma', '69');
        insert into product_image (id, product_id, type, file_id) values ( 11,4, 'th', '70');
        insert into product_image (id, product_id, type, file_id) values ( 12,4, 'ma', '71');
        insert into product_image (id, product_id, type, file_id) values ( 13,5, 'th', '72');
        insert into product_image (id, product_id, type, file_id) values ( 14,5, 'ma', '73');
        insert into product_image (id, product_id, type, file_id) values ( 15,5, 'et', '74');
        insert into product_image (id, product_id, type, file_id) values ( 16,5, 'et', '75');
        insert into product_image (id, product_id, type, file_id) values ( 17,5, 'et', '76');
        insert into product_image (id, product_id, type, file_id) values ( 18,6, 'th', '77');
        insert into product_image (id, product_id, type, file_id) values ( 19,6, 'ma', '78');
        insert into product_image (id, product_id, type, file_id) values ( 20,7, 'th', '79');
        insert into product_image (id, product_id, type, file_id) values ( 21,7, 'ma', '80');
        insert into product_image (id, product_id, type, file_id) values ( 22,8, 'th', '81');
        insert into product_image (id, product_id, type, file_id) values ( 23,8, 'ma', '82');
        insert into product_image (id, product_id, type, file_id) values ( 24,9, 'th', '83');
        insert into product_image (id, product_id, type, file_id) values ( 25,9, 'ma', '84');
        insert into product_image (id, product_id, type, file_id) values ( 26,9, 'et', '85');
        insert into product_image (id, product_id, type, file_id) values ( 27,10, 'th', '86');
        insert into product_image (id, product_id, type, file_id) values ( 28,10, 'ma', '87');
        insert into product_image (id, product_id, type, file_id) values ( 29,10, 'et', '88');
        insert into product_image (id, product_id, type, file_id) values ( 30,11, 'th', '89');
        insert into product_image (id, product_id, type, file_id) values ( 31,11, 'ma', '90');
        insert into product_image (id, product_id, type, file_id) values ( 32,12, 'th', '91');
        insert into product_image (id, product_id, type, file_id) values ( 33,12, 'ma', '92');
        insert into product_image (id, product_id, type, file_id) values ( 34,13, 'th', '93');
        insert into product_image (id, product_id, type, file_id) values ( 35,13, 'ma', '94');
        insert into product_image (id, product_id, type, file_id) values ( 36,14, 'th', '95');
        insert into product_image (id, product_id, type, file_id) values ( 37,14, 'ma', '96');
        insert into product_image (id, product_id, type, file_id) values ( 38,15, 'th', '97');
        insert into product_image (id, product_id, type, file_id) values ( 39,15, 'ma', '98');
        insert into product_image (id, product_id, type, file_id) values ( 40,16, 'th', '99');
        insert into product_image (id, product_id, type, file_id) values ( 41,16, 'ma', '100');
        insert into product_image (id, product_id, type, file_id) values ( 42,17, 'th', '101');
        insert into product_image (id, product_id, type, file_id) values ( 43,17, 'ma', '102');
        insert into product_image (id, product_id, type, file_id) values ( 44,17, 'et', '103');
        insert into product_image (id, product_id, type, file_id) values ( 45,17, 'et', '104');
        insert into product_image (id, product_id, type, file_id) values ( 46,18, 'th', '105');
        insert into product_image (id, product_id, type, file_id) values ( 47,18, 'ma', '106');
        insert into product_image (id, product_id, type, file_id) values ( 48,19, 'th', '107');
        insert into product_image (id, product_id, type, file_id) values ( 49,19, 'ma', '108');
        insert into product_image (id, product_id, type, file_id) values ( 50,20, 'th', '109');
        insert into product_image (id, product_id, type, file_id) values ( 51,20, 'ma', '110');
        insert into product_image (id, product_id, type, file_id) values ( 52,20, 'et', '111');
        insert into product_image (id, product_id, type, file_id) values ( 53,21, 'th', '112');
        insert into product_image (id, product_id, type, file_id) values ( 54,21, 'ma', '113');
        insert into product_image (id, product_id, type, file_id) values ( 55,22, 'th', '114');
        insert into product_image (id, product_id, type, file_id) values ( 56,22, 'ma', '115');
        insert into product_image (id, product_id, type, file_id) values ( 57,23, 'th', '116');
        insert into product_image (id, product_id, type, file_id) values ( 58,23, 'ma', '117');
        insert into product_image (id, product_id, type, file_id) values ( 59,24, 'th', '118');
        insert into product_image (id, product_id, type, file_id) values ( 60,24, 'ma', '119');
        insert into product_image (id, product_id, type, file_id) values ( 61,25, 'th', '120');
        insert into product_image (id, product_id, type, file_id) values ( 62,25, 'ma', '121');
        insert into product_image (id, product_id, type, file_id) values ( 63,26, 'th', '122');
        insert into product_image (id, product_id, type, file_id) values ( 64,26, 'ma', '123');
        insert into product_image (id, product_id, type, file_id) values ( 65,27, 'th', '124');
        insert into product_image (id, product_id, type, file_id) values ( 66,27, 'ma', '125');
        insert into product_image (id, product_id, type, file_id) values ( 67,28, 'th', '126');
        insert into product_image (id, product_id, type, file_id) values ( 68,28, 'ma', '127');
        insert into product_image (id, product_id, type, file_id) values ( 69,29, 'th', '128');
        insert into product_image (id, product_id, type, file_id) values ( 70,29, 'ma', '129');
        insert into product_image (id, product_id, type, file_id) values ( 71,30, 'th', '130');
        insert into product_image (id, product_id, type, file_id) values ( 72,30, 'ma', '131');
        insert into product_image (id, product_id, type, file_id) values ( 73,31, 'th', '132');
        insert into product_image (id, product_id, type, file_id) values ( 74,31, 'ma', '133');
        insert into product_image (id, product_id, type, file_id) values ( 75,31, 'et', '134');
        insert into product_image (id, product_id, type, file_id) values ( 76,31, 'et', '135');
        insert into product_image (id, product_id, type, file_id) values ( 77,31, 'et', '136');
        insert into product_image (id, product_id, type, file_id) values ( 78,32, 'th', '137');
        insert into product_image (id, product_id, type, file_id) values ( 79,32, 'ma', '138');
        insert into product_image (id, product_id, type, file_id) values ( 80,32, 'et', '139');
        insert into product_image (id, product_id, type, file_id) values ( 81,32, 'et', '140');
        insert into product_image (id, product_id, type, file_id) values ( 82,32, 'et', '141');
        insert into product_image (id, product_id, type, file_id) values ( 83,33, 'th', '142');
        insert into product_image (id, product_id, type, file_id) values ( 84,33, 'ma', '143');
        insert into product_image (id, product_id, type, file_id) values ( 85,34, 'th', '144');
        insert into product_image (id, product_id, type, file_id) values ( 86,34, 'ma', '145');
        insert into product_image (id, product_id, type, file_id) values ( 87,34, 'et', '146');
        insert into product_image (id, product_id, type, file_id) values ( 88,34, 'et', '147');
        insert into product_image (id, product_id, type, file_id) values ( 89,34, 'et', '148');
        insert into product_image (id, product_id, type, file_id) values ( 90,35, 'th', '149');
        insert into product_image (id, product_id, type, file_id) values ( 91,35, 'ma', '150');
        insert into product_image (id, product_id, type, file_id) values ( 92,36, 'th', '151');
        insert into product_image (id, product_id, type, file_id) values ( 93,36, 'ma', '152');
        insert into product_image (id, product_id, type, file_id) values ( 94,37, 'th', '153');
        insert into product_image (id, product_id, type, file_id) values ( 95,37, 'ma', '154');
        insert into product_image (id, product_id, type, file_id) values ( 96,38, 'th', '155');
        insert into product_image (id, product_id, type, file_id) values ( 97,38, 'ma', '156');
        insert into product_image (id, product_id, type, file_id) values ( 98,39, 'th', '157');
        insert into product_image (id, product_id, type, file_id) values ( 99,39, 'ma', '158');
        insert into product_image (id, product_id, type, file_id) values ( 100,39, 'et', '159');
        insert into product_image (id, product_id, type, file_id) values ( 101,39, 'et', '160');
        insert into product_image (id, product_id, type, file_id) values ( 102,40, 'th', '161');
        insert into product_image (id, product_id, type, file_id) values ( 103,40, 'ma', '162');
        insert into product_image (id, product_id, type, file_id) values ( 104,40, 'et', '163');
        insert into product_image (id, product_id, type, file_id) values ( 105,41, 'th', '164');
        insert into product_image (id, product_id, type, file_id) values ( 106,41, 'ma', '165');
        insert into product_image (id, product_id, type, file_id) values ( 107,42, 'th', '166');
        insert into product_image (id, product_id, type, file_id) values ( 108,42, 'ma', '167');
        insert into product_image (id, product_id, type, file_id) values ( 109,42, 'et', '168');
        insert into product_image (id, product_id, type, file_id) values ( 110,43, 'th', '169');
        insert into product_image (id, product_id, type, file_id) values ( 111,43, 'ma', '170');
        insert into product_image (id, product_id, type, file_id) values ( 112,44, 'th', '171');
        insert into product_image (id, product_id, type, file_id) values ( 113,44, 'ma', '172');
        insert into product_image (id, product_id, type, file_id) values ( 114,44, 'et', '173');
        insert into product_image (id, product_id, type, file_id) values ( 115,44, 'et', '174');
        insert into product_image (id, product_id, type, file_id) values ( 116,45, 'th', '175');
        insert into product_image (id, product_id, type, file_id) values ( 117,45, 'ma', '176');
        insert into product_image (id, product_id, type, file_id) values ( 118,45, 'et', '177');
        insert into product_image (id, product_id, type, file_id) values ( 119,45, 'et', '178');
        insert into product_image (id, product_id, type, file_id) values ( 120,46, 'th', '179');
        insert into product_image (id, product_id, type, file_id) values ( 121,46, 'ma', '180');
        insert into product_image (id, product_id, type, file_id) values ( 122,47, 'th', '181');
        insert into product_image (id, product_id, type, file_id) values ( 123,47, 'ma', '182');
        insert into product_image (id, product_id, type, file_id) values ( 124,48, 'th', '183');
        insert into product_image (id, product_id, type, file_id) values ( 125,48, 'ma', '184');
        insert into product_image (id, product_id, type, file_id) values ( 126,49, 'th', '185');
        insert into product_image (id, product_id, type, file_id) values ( 127,49, 'ma', '186');
        insert into product_image (id, product_id, type, file_id) values ( 128,50, 'th', '187');
        insert into product_image (id, product_id, type, file_id) values ( 129,50, 'ma', '188');


        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 1,1, 'A',6000,20, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 2,1, 'Y',3000,33, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 3,1, 'B',2000,50, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 4,2, 'A',8000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 5,2, 'Y',3000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 6,2, 'B',2000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 7,3, 'A',11700,10, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 8,3, 'Y',9900,10, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 9,3, 'B',8100,10, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 10,4, 'A',3000,40, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 11,4, 'Y',1500,50, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 12,5, 'A',13000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 13,5, 'Y',10000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 14,5, 'B',8000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 15,6, 'A',3000,40, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 16,6, 'Y',1500,50, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 17,7, 'A',4000,20, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 18,7, 'Y',4000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 19,8, 'A',8000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 20,8, 'Y',5000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 21,8, 'B',3000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 22,9, 'A',13000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 23,9, 'Y',11000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 24,9, 'B',9000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 25,10, 'A',13000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 26,10, 'Y',10000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 27,10, 'B',8000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 28,11, 'V',112000,20, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 29,11, 'R',96000,20, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 30,12, 'V',77000,30, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 31,12, 'R',69300,30, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 32,13, 'B',28000,30, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 33,14, 'V',33000,50, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 34,14, 'R',27500,50, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 35,15, 'V',28000,60, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 36,15, 'R',20000,60, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 37,16, 'R',88000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 38,16, 'S',66000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 39,17, 'V',80000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 40,17, 'R',50000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 41,17, 'A',40000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 42,18, 'R',66000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 43,18, 'S',55000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 44,19, 'V',80000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 45,19, 'R',60000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 46,19, 'S',50000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 47,19, 'A',40000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 48,20, 'D',15800,64, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 49,20, 'E',19800,56, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 50,21, 'R',109000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 51,22, 'R',44000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 52,23, 'A',50000,9, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 53,23, 'Y',35000,36, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 54,24, 'R',20000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 55,25, 'V',98000,30, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 56,25, 'R',72000,40, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 57,25, 'S',45000,50, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 58,26, 'R',59000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 59,27, 'R',75000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 60,27, 'S',55000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 61,27, 'A',35000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 62,28, 'V',132000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 63,28, 'R',110000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 64,28, 'S',99000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 65,28, 'A',88000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 66,29, 'R',110000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 67,29, 'S',99000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 68,30, 'V',121000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 69,30, 'R',110000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 70,30, 'S',99000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 71,31, 'R',10000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 72,32, 'V',50000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 73,32, 'R',30000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 74,32, 'S',20000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 75,33, 'R',0,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 76,34, 'R',25000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 77,35, 'R',30000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 78,35, 'S',20000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 79,36, 'R',65000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 80,36, 'S',45000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 81,37, 'R',20000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 82,38, 'A',20000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 83,38, 'Y',14000,30, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 84,39, 'R',50000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 85,40, 'R',20000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 86,41, 'R',12000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 87,42, 'R',50000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 88,43, 'R',0,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 89,44, 'R',15000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 90,45, 'R',17000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 91,46, 'R',25500,15, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 92,47, 'R',30800,30, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 93,48, 'R',20000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 94,49, 'R',35000,0, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 95,49, 'D',15000,57, now(), now());
        insert into product_price (id, product_id, price_type_name , price, discount_rate, create_date, modify_date) values ( 96,50, 'R',35000,0, now(), now());

        insert into promotion(id, product_id) values( 1, 1);
        insert into promotion(id, product_id) values( 2, 5);
        insert into promotion(id, product_id) values( 3, 6);
        insert into promotion(id, product_id) values( 4, 9);
        insert into promotion(id, product_id) values( 5, 11);
        insert into promotion(id, product_id) values( 6, 12);
        insert into promotion(id, product_id) values( 7, 18);
        insert into promotion(id, product_id) values( 8, 22);
        insert into promotion(id, product_id) values( 9, 34);
        insert into promotion(id, product_id) values( 10, 41);
        insert into promotion(id, product_id) values( 11, 44);

        insert into reservation_info (id, product_id, display_info_id, user_id, reservation_date,
                create_date, modify_date ) values ( 1, 1, 1, 1, now(), now(), now());

        insert into reservation_info (id, product_id, display_info_id, user_id, reservation_date,
                create_date, modify_date ) values ( 2, 1, 1, 2, now(), now(), now());

        insert into reservation_info (id, product_id, display_info_id, user_id, reservation_date,
                create_date, modify_date ) values ( 3, 1, 1, 3, now(), now(), now());

        insert into reservation_info (id, product_id, display_info_id, user_id, reservation_date,
                create_date, modify_date ) values ( 4, 2, 2, 4, now(), now(), now());

        insert into reservation_info (id, product_id, display_info_id, user_id, reservation_date,
                create_date, modify_date ) values ( 5, 2, 2, 5, now(), now(), now());

        insert into reservation_info (id, product_id, display_info_id, user_id, reservation_date,
                create_date, modify_date ) values ( 6, 1, 1, 6, now(), now(), now());

        insert into reservation_info (id, product_id, display_info_id, user_id, reservation_date,
                create_date, modify_date ) values ( 7, 1, 1, 7, now(), now(), now());

        insert into reservation_info (id, product_id, display_info_id, user_id, reservation_date,
                create_date, modify_date ) values ( 8, 1, 1, 8, now(), now(), now());

        insert into reservation_info (id, product_id, display_info_id, user_id, reservation_date,
                create_date, modify_date ) values ( 9, 1, 1, 9, now(), now(), now());

        insert into reservation_info (id, product_id, display_info_id, user_id, reservation_date,
                create_date, modify_date ) values ( 10, 1, 1, 10, now(), now(), now());

        insert into reservation_info (id, product_id, display_info_id, user_id, reservation_date,
                create_date, modify_date ) values ( 11, 1, 1, 11, now(), now(), now());

        insert into reservation_info (id, product_id, display_info_id, user_id, reservation_date,
                create_date, modify_date ) values ( 12, 1, 1, 12, now(), now(), now());

        insert into reservation_info (id, product_id, display_info_id, user_id, reservation_date,
                create_date, modify_date ) values ( 13, 1, 1, 13, now(), now(), now());

        insert into reservation_info (id, product_id, display_info_id, user_id, reservation_date,
                create_date, modify_date ) values ( 14, 1, 1, 14, now(), now(), now());

        insert into reservation_info (id, product_id, display_info_id, user_id, reservation_date,
                create_date, modify_date ) values ( 15, 1, 1, 15, now(), now(), now());

        insert into reservation_info_price (id, reservation_info_id, product_price_id, count) values (1, 1, 1, 1);
        insert into reservation_info_price (id, reservation_info_id, product_price_id, count) values (2, 1, 2, 1);
        insert into reservation_info_price (id, reservation_info_id, product_price_id, count) values (3, 1, 3, 1);
        insert into reservation_info_price (id, reservation_info_id, product_price_id, count) values (4, 2, 2, 2);
        insert into reservation_info_price (id, reservation_info_id, product_price_id, count) values (5, 3, 3, 3);
        insert into reservation_info_price (id, reservation_info_id, product_price_id, count) values (6, 4, 4, 1);
        insert into reservation_info_price (id, reservation_info_id, product_price_id, count) values (7, 4, 5, 1);
        insert into reservation_info_price (id, reservation_info_id, product_price_id, count) values (8, 4, 6, 1);
        insert into reservation_info_price (id, reservation_info_id, product_price_id, count) values (9, 5, 5, 2);
        insert into reservation_info_price (id, reservation_info_id, product_price_id, count) values (10, 6, 1, 1);
        insert into reservation_info_price (id, reservation_info_id, product_price_id, count) values (11, 7, 1, 1);
        insert into reservation_info_price (id, reservation_info_id, product_price_id, count) values (12, 8, 1, 1);
        insert into reservation_info_price (id, reservation_info_id, product_price_id, count) values (13, 9, 1, 1);
        insert into reservation_info_price (id, reservation_info_id, product_price_id, count) values (14, 10, 1, 1);
        insert into reservation_info_price (id, reservation_info_id, product_price_id, count) values (15, 11, 1, 1);
        insert into reservation_info_price (id, reservation_info_id, product_price_id, count) values (16, 12, 1, 1);
        insert into reservation_info_price (id, reservation_info_id, product_price_id, count) values (17, 13, 1, 1);
        insert into reservation_info_price (id, reservation_info_id, product_price_id, count) values (18, 14, 1, 1);
        insert into reservation_info_price (id, reservation_info_id, product_price_id, count) values (19, 15, 1, 1);

        insert into reservation_user_comment(id, product_id, reservation_info_id, user_id, score, comment, create_date, modify_date)
        values (1, 1, 1, 1, 4, '좋았어요.', now(), now());

        insert into reservation_user_comment(id, product_id, reservation_info_id, user_id, score, comment, create_date, modify_date)
        values (2, 1, 2, 2, 5, '최고!!!!', now(), now());

        insert into reservation_user_comment(id, product_id, reservation_info_id, user_id, score, comment, create_date, modify_date)
        values (3, 1, 3, 3, 5, '또 가보고 싶어요.', now(), now());

        insert into reservation_user_comment(id, product_id, reservation_info_id, user_id, score, comment, create_date, modify_date)
        values (4, 1, 4, 4, 2, '가격대비 약간은 실망이었어요.', now(), now());

        insert into reservation_user_comment(id, product_id, reservation_info_id, user_id, score, comment, create_date, modify_date)
        values (5, 1, 5, 5, 3, '지루했어요..', now(), now());

        insert into reservation_user_comment(id, product_id, reservation_info_id, user_id, score, comment, create_date, modify_date)
        values (6, 1, 6, 6, 4, '오 재미남!!', now(), now());

        insert into reservation_user_comment(id, product_id, reservation_info_id, user_id, score, comment, create_date, modify_date)
        values (7, 1, 7, 7, 5, '최고!!!!!!!!', now(), now());

        insert into reservation_user_comment(id, product_id, reservation_info_id, user_id, score, comment, create_date, modify_date)
        values (8, 1, 8, 8, 3, '보통이였어요.', now(), now());

        insert into reservation_user_comment(id, product_id, reservation_info_id, user_id, score, comment, create_date, modify_date)
        values (9, 1, 9, 9, 2, '실망....', now(), now());

        insert into reservation_user_comment(id, product_id, reservation_info_id, user_id, score, comment, create_date, modify_date)
        values (10, 1, 10, 10, 1, '최고로 실망...', now(), now());

        insert into reservation_user_comment(id, product_id, reservation_info_id, user_id, score, comment, create_date, modify_date)
        values (11, 1, 11, 11, 3, '괜찮았어요.', now(), now());

        insert into reservation_user_comment(id, product_id, reservation_info_id, user_id, score, comment, create_date, modify_date)
        values (12, 1, 12, 12, 4, '좋았음.', now(), now());

        insert into reservation_user_comment(id, product_id, reservation_info_id, user_id, score, comment, create_date, modify_date)
        values (13, 1, 13, 13, 4, '재미남.', now(), now());

        insert into reservation_user_comment(id, product_id, reservation_info_id, user_id, score, comment, create_date, modify_date)
        values (14, 1, 14, 14, 5, '또 가고 싶어.', now(), now());

        insert into reservation_user_comment(id, product_id, reservation_info_id, user_id, score, comment, create_date, modify_date)
        values (15, 1, 15, 15, 5, '즐거움!!!', now(), now());

        insert into reservation_user_comment_image(id, reservation_info_id, reservation_user_comment_id, file_id) values (1, 1, 1, 1);

        insert into reservation_user_comment_image(id, reservation_info_id, reservation_user_comment_id, file_id) values (2, 1, 2, 2);

        --
                -- select * from member_role;
        -- select * from member;
        -- select * from reservation_user_comment_image;
        -- select * from reservation_user_comment;
        -- select * from reservation_info_price;
        -- select * from reservation_info;
        -- select * from promotion;
        -- select * from product_price;
        -- select * from product_image;
        -- select * from display_info_image;
        -- select * from display_info;
        -- select * from product;
        -- select * from file_info;
        -- select * from category;
*/







    }
}