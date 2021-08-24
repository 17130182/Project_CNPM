﻿create database nongsanspringboot;
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;
go
use nongsanspringboot;
go
CREATE TABLE roles (
	id int IDENTITY(1,1) PRIMARY KEY,
	name VARCHAR ( 150 ) NOT NULL
);

create table users(
    id int IDENTITY(1,1) PRIMARY KEY,
	name varchar(100) not null,
	email varchar(100) UNIQUE not null,
	phone nvarchar(20) not null,
	username varchar(100) not null,
	password varchar(255) not null
);
create table user_role(
   id int IDENTITY(1,1) PRIMARY KEY,
   user_id int not null,
   role_id int not null
);
alter table user_role
  add constraint user_role_fk1 foreign key (user_id)
  references users(id)
 
alter table user_role
  add constraint user_role_fk2 foreign key (role_id)
  references roles(id);
alter table user_role
  add constraint user_role_uk unique (user_id, role_id);

  -- Used by Spring Remember Me API.  
create table persistent_logins (
 
    username varchar(64) not null,
    series varchar(64) primary key,
    token varchar(64) not null,
    last_used timestamp not null
     
);


INSERT INTO roles VALUES 
('ROLE_ADMIN'),
('ROLE_USER');

INSERT INTO users VALUES 
('Admin','admin@gmail.com','0123456789','admin','$2a$10$wUfvCxoRy5fkMjCuOq.ISelwdAnEJBHBYWdPa8FB/LbwrkvicrfcW'),
('User','user@gmail.com','0123456789','user','$2a$10$wUfvCxoRy5fkMjCuOq.ISelwdAnEJBHBYWdPa8FB/LbwrkvicrfcW');

insert into user_role (user_id,role_id)
values (1,1);
 
insert into user_role (user_id,role_id)
values (1, 2);
 
insert into user_role (user_id, role_id)
values (2, 2);

create table catalog
(
	id int IDENTITY(1,1) PRIMARY KEY ,
	name nvarchar(50) not null,
	parent_id nvarchar(50) 
);


CREATE TABLE product (
	id int IDENTITY(1,1) PRIMARY KEY ,
	catalog_id INT NOT NULL,
	name nvarchar ( 50 ) NOT NULL,
	price nvarchar ( 20 ) NOT NULL,
	qty INT,
	status INT NOT NULL,
	description nvarchar ( 4000 ) NOT NULL,
	content nvarchar ( 4000 ) NOT NULL,
	discount int,
	image_link nvarchar ( 4000 ) NOT NULL,
	created date
);
ALTER TABLE product ADD CONSTRAINT fk_product_catalog FOREIGN KEY ( catalog_id ) REFERENCES catalog ( id ) ON DELETE CASCADE;

CREATE TABLE review (
                        id int IDENTITY(1,1) PRIMARY KEY,
                        product_id INT NOT NULL,
                        name nvarchar ( 50 ) NOT NULL,
                        email nvarchar ( 50 ) NOT NULL,
                        content nvarchar ( 4000 ) NOT NULL,
                        created date
);
ALTER TABLE review ADD CONSTRAINT fk_review_product FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE;

create table boardnew(
    id int IDENTITY(1,1) PRIMARY KEY,
    title nvarchar(200) not null,
    content nvarchar(4000) not null,
    image_link nvarchar(4000) not null,
    author nvarchar(50) not null,
    created date not null
);


create table cart(
id nchar(10)primary key not null,
username nchar(50),
idproduct  int,
price nchar(10),
quantity int,
totalcost float

);

ALTER TABLE cart ADD CONSTRAINT fk_cart_product FOREIGN KEY ( idproduct ) REFERENCES product ( id ) ON DELETE CASCADE;



create table bill(
id int identity(1,1) primary key not null,
username nvarchar(50),
address nvarchar(50),
phone nvarchar(20),
payment nchar(10),
totalcost float,
time datetime,
note nvarchar(50),
status nvarchar(50)


);
create table bill_detail(
id int identity(1,1) primary key not null,
idbill int,
idproduct int,
name nvarchar(50),
price float,
quantity int,
totalcost float

);
ALTER TABLE bill_detail ADD CONSTRAINT fk_billdetail_product FOREIGN KEY ( idproduct ) REFERENCES product ( id ) ON DELETE CASCADE;
ALTER TABLE bill_detail ADD CONSTRAINT fk_billdetail_bill FOREIGN KEY ( idbill ) REFERENCES bill( id ) ON DELETE CASCADE;



INSERT INTO catalog(name,parent_id) 
	VALUES (N'Rau củ quả',null);
INSERT INTO catalog(name,parent_id) 
	VALUES (N'Các Loại Hạt',null);
INSERT INTO catalog(name,parent_id) 
	VALUES (N'Trái Cây',null);
INSERT INTO catalog(name,parent_id) 
	VALUES (N'Mật Ong & Tinh Dầu',null);
INSERT INTO catalog(name,parent_id) 
	VALUES (N'Sản Phẩm Mới',null);
INSERT INTO catalog(name,parent_id) 
	VALUES (N'Sản Phẩm Bán Chạy',null);
INSERT INTO catalog(name,parent_id) 
	VALUES (N'Sản Phẩm Giảm Giá',null);

insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (1,N'Rau Cải','15000','1000','1',N'Đây là rau sạch',N'Rau cải là loại rau không mấy xa lạ với người Việt ta. Bởi nó được dùng để chế biến thành các món ăn rất ngon và hợp khẩu vị. Nhưng nó ít được biết đến là vị thuốc chữa được rất nhiều loại bệnh. Ngoài cái tên cải bó xôi, nó còn có nhiều tên khác như: rau chân vịt, rau bắp xôi, rau nhà chùa, cải bina… Đây là loài cây thuộc họ nhà Dền và có xuất xứ từ miền Trung và Tây Nam Á.',0,'rau_cai.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (1,N'Rau Muống','14000','1000','1',N'Đây là rau sạch',N'Rau muống là loại rau có giá rất rẻ so với các loại rau khác nhưng lại đem lại lượng khoáng chất và vitamin dồi dào như protein, sắt, canxin, chất xơ, vitamin A... Những chất này là những dưỡng chất cần thiết cho cơ thể.',10,'rau_muong.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (1,N'Đậu Nhật','50000','1000','1',N'Đậu Nhật',N'Ðậu nành Nhật hay còn gọi là Đậu nành rau hay Đậu nành lông có nguồn gốc từ Nhật Bản, rất ngon và có giá trị dinh dưỡng cao, là loại thực phẩm rất tốt cho sức khỏe và bổ dưỡng vì hàm lượng của nó giàu chất khoáng, vitamin, protein, chất béo, chất xơ, rất tốt cho đường ruột, làm mịn da mặt và còn có tác dụng ngăn ngừa một số bệnh về ung thư',0,'dau_nhat.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (1,N'Hành baro','45000','1000','1',N'Hành Baro',N'Hành Baro có tên gọi khác là tỏi tây. Đây là một trong những nguyên liệu không thể thiếu trong rất nhiều món ăn, tạo mùi vị hấp dẫn, khó quên. Không những thế, tỏi tây còn là nguồn dinh dưỡng dồi dào đối với cơ thể. Để có thêm nhiều kiến thức về Hành Baro đừng bỏ lỡ bài viết này nhé.',10,'hanh_baro.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (1,N'Rau Cần Tây','55000','1000','1',N'Rau Cần Tây',N'Rau cần tây là một trong những loại rau được đánh giá là “ siêu thực phẩm” chứa hàm lượng dinh dưỡng cao, và ăn cực kỳ ngon nhất là khi kết hợp mùi thơm đặc biệt của cần tây xào với thịt bò…Ngoài sử dụng ở dạng tươi sống khi chế biến món ăn thì bạn cũng có thể làm nước ép cần tây tác dụng giảm cân, thanh nhiệt giải độc cơ thể rất tốt.',10,'rau_can_tay.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (1,N'Rau mùi - Ngò','120000','1000','1',N'Rau mùi - Ngò',N'Rau mùi hay còn được gọi bằng các tên gọi khác như: rau ngò, ngò rí, mùi ta, ngổ, mùi tui, nguyên tuy, hồ tuy, hương tuy, ngổ thơm,....Rau mùi là một loại cây thân thảo, loại rau này có nguồn gốc từ các nước Tây Nam Á và Châu Phi. ',10,'rau_mui.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (1,N'Xà lách Mỹ - Cuộn Xanh','80.000','1000','1',N'Xà lách',N'Xà lách Mỹ có nguồn gốc từ vùng nhiệt đới và đến ngày nay nó đã  trở thành cây của toàn thế giới. Ở nước ta, Rau xà lách ưa khí hậu mát, đặc biệt là khí hậu ở Đà Lạt ',10,'xa_lach.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (1,N'Súp Lơ Tím','100000','1000','1',N'Súp Lơ',N'Súp lơ tím có tên gọi khác là bông cải tím. Đây là một trong những loại rau trồng được các bà nội trợ ưa chuộng với giá trị dinh dưỡng cao. Đó cũng là lý do giá súp lơ tím cao hơn so với các loại rau thông thường.',10,'sup_lo.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (2,N'Hạt Sen','75000','1000','1',N'Hạt Sen',N'Hạt sen sấy là một loại hạt dinh dưỡng, mang lại rất nhiều lợi ích đối với sức khỏe người sử dụng. Từ xưa, cây sen đã là một loài cây trồng quen thuộc và là biểu tượng của người dân Việt Nam. Hạt sen sấy là sản phẩm của hạt sen tươi khi đã chín được người nông dân thu về và chế biến, bảo quản. Hạt sen sấy giòn (hạt sen sấy khô) sẽ bảo quản được thời gian lâu hơn so với hạt sen tươi. Hạt sen sấy có rất nhiều công dụng khác nhau. Ngoài sử dụng như một vị thuốc không thể thiếu trong Đông Y, sen sấy khô còn là nguyên liệu để chế biến rất nhiều món ăn thơm ngon, bổ dưỡng chẳng hạn như: chè hạt sen long nhãn, chè hạt sen nấm tuyết, cháo hạt sen, hạt sen sấy giòn…',5,'hat_sen.jpg','2017-1-1');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (2,N'Hạt Ươi','45000','1000','1',N'Hạt Ươi',N'Hạt Ươi (Sterculia lychonophora Hnce) còn có tên gọi khác là hạt ươi bay, hạt đười ươi, an nam tứ, đại đồng quả, pang da hai,...Đây là một trong những loại hạt được pha làm nước uống bổ dưỡng vào mùa hè, xua tan đi cái nóng và mệt mỏi. Vòng tuần hoàn ra quả của cây ươi đó là 4 năm một lần. Cây ươi sinh trường, phát triển ở trong rừng thường có mặt ở các nước thuộc khu vực Đông Nam Á như: Thái Lan, Việt Nam, Lào hay Malaysia. Tại Việt Nam, cây ươi mọc ở Tây Nguyên và vùng Trung Trung Bộ. Hạt ươi rừng sau khi thu hoạch sẽ được sàng lọc thêm một lần nữa để chọn ra những hạt chất lượng nhất. Sau đó sơ chế để bán lẻ và xuất khẩu đi một số nước. ',0,'hat_uoi.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (2,N'Hạt Phỉ','250000','1000','1',N'Hạt Phỉ',N'Hạt phỉ còn có tên gọi khác là Hazelnut, tên thực vật của nó là Corylus avellana. Hạt phỉ được trồng nhiều ở nhiều quốc gia như: Hy Lạp, Georgia, Thổ Nhĩ Kì, Italia, Anh, phía nam Tây Ban Nha và 2 bang Oregon  và Washington  của Mỹ. Thổ Nhĩ Kì là nơi cung cấp nguồn hạt phỉ lớn nhất trên thế giới, chiếm tới 75% sản lượng toàn thế giới. Hạt phỉ ngon, nhiều chất dinh dưỡng rất tốt cho sức khỏe. Không chỉ thế, mùi vị của hạt phỉ thơm ngon, bùi béo nên nó thường được dùng để nấu ăn, trong các món bánh.',20,'hat_phi.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (2,N'Quả Hạch','300000','1000','1',N'Quả Hạch',N'Quả hạch khô còn có tên gọi khác là hạt bào ngư, hạt móng ngựa, hình dáng của quả hạch bên ngoài xù xì, có lớp vỏ cứng màu nâu sẫm. Quả hạch có phần nhân bên trong màu trắng, nhận được bao bọc 1 lớp màn mỏng nâu. Phần nhân béo gùi, ăn rất thơm. Quả hạch được chọn to đèu, không bị dập nát, bị thối, sau đó mang đi sấy khô với công nghệ hiện đại. Quả hạch sẽ được tách lớp vỏ cứng bên ngoài rồi  mới đóng gói trong các bao bì an toàn, hợp vệ sinh. Hạt quả hạch đóng gói tiện lợi, dễ sử dụng, bạn chỉ cần bảo quản ở nơi thoáng mát, khô ráo và dùng mỗi ngày, không nhất thiết phải để trong tủ lạnh.',0,'hach_kho.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (2,N'Ô Mai Khô ','200000','1000','1',N'Ô Mai',N'Ô Mai còn có tên gọi khác là mơ đen, từ xưa ô mai là tên một vị thuốc làm từ quả mơ phơi khô cho đên khi đen và quắt lại. Trong y học cổ truyền, ô mai là vị thuộc rất phổ biến ở các nước châu á như Việt Nam, Trung Quốc. Ngày nay thì ô mai thường được dùng như một loại đồ ăn vặt được làm từ các loại quả khác nhau như: mận, me, sấu mơ, sử dụng ăn vặt nhiều hơn là làm thuốc. Những loại quả để làm mở cần phải được chọn lựa kỹ càng và chế biến với nhiều hương liệu, nguyên liệu khác nhau thì mới làm ra được món ô mai ngon nhất.',20,'o_mai.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (2,N'Sa Nhân','450000','1000','1',N'Sa Nhân',N'Sa Nhân còn có tên gọi khác trong tiếng Tày là mác nẻng, trong tiếng là co nénh, sa nhân là loại cây mọc hoang rất nhiều ở các vùng rừng núi, dưới tán cây sa nhân râm mát. Bọ phận thường dùng làm thuốc của sa nhân là hạt quả. Quả thường được hát vào mùa hè, bóc vỏ rồi lấy hạt ở phía trong, sây khô sử dụng dần.',20,'sa_nhan.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (2,N'Thảo Quả','125000','1000','1',N'Thảo Quả',N'Tên tiếng Việt khác: Thảo quả đỏ / Thảo quả đen Tên danh pháp hai phần: Amomum tsao-ko Thuộc họ: Gừng(Zingiberaceae) Hoa thảo quả thường nở vào mùa hè ( khoảng từ tháng 5 đến tháng 7) và ra quả vào mùa đông Ở Việt Nam, thảo quả mọc chủ yếu ở vùng Tây Bắc và dãy núi Hoàng Liên Sơn, nó mọc nhiều nhất ở các tỉnh Lào Cai, Yên Bái, Hà Giang, Lai Châu Thảo quả là một loại cây thảo mộc có vị cay nóng, mùi thơm đặc trưng, chúng thường được sử dụng trong ẩm thực và làm thuốc chữa bệnh. Nó còn được mệnh danh là “nữ hoàng gia vị” trong chế biến các món ăn ngon và nổi tiếng của Việt Nam.',0,'thao_qua.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (2,N'Hạt Điều','150000','1000','1',N'Hạt Điều',N'Điều hay còn gọi là đào lộn hột, là một cây công nghiệp thuộc họ xoài. Nó được trồng ở khí hậu nhiệt đới để lấy nhân hạt chế biến làm thực phẩm.Hạt điều ăn rất giòn và có hương thơm đặc trưng. Thường mọi người hay tìm mua hạt điều rang muối. Đây là đặc sản của Việt Nam nên thị trường hạt điều xuất khẩu luôn dẫn đầu cao.',10,'hat_dieu.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (3,N'Xoài Tượng Bình Định','35000','1000','1',N'Xoài Tượng Bình Định ',N'Ở Bình Định nổi tiếng với nhiều loại xoài như: Xoài tượng, xoài thanh ca, xoài mật, xoài tro, xoài xẻ...vv. Mỗi loại xoài đều có những hương vị độc đáo riêng. Trong đó ngon nhất và ấn tượng nhất thì phải kể đến xoài tượng. Ngoài vị thơm, ngọt thì xoài tượng có cho giá trị kinh tế cao nhất và được thị trường rất ưa chuộng. Xoài Tượng được trồng ở rất nhiều nơi nhưng nhắc đến xoài tượng là người ta nghĩ ngay đến Đại An (Bình Định) vì nơi đây trồng xoài tượng nhiều nhất và ngon nhất hiện nay.',10,'xoai_tuong.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (3,N'Vú Sữa Lò Rèn','50000','1000','1',N'Vũ Sữa Lò Rèn',N'Vú sữa Lò rèn Vĩnh Kim là một loại trái cây ngon mọi người dân Nam Bộ và quốc gia đều biết đến và hiện nay được trồng ở quy mô công nghiệp, nhưng giống vú sữa Lò rèn Vĩnh Kim gắn với vùng đất đặc biệt này vẫn được người dân gìn giữ, đặc biệt cây vú sữa Lò rèn Vĩnh Kim được trồng từ hạt của cây vú sữa đầu tiên. Thịt quả có màu trắng đục, mềm, nước dạng sữa, dày thịt, tỷ lệ thịt quả cao, ít hạt, có mùi vị rất ngọt, béo, mùi thơm dịu đặc trưng. ngon. Vú sữa lò rèn vĩnh kim có vị ngọt, được ăn tươi hoặc làm lạnh. Đây là loại quả được người Việt Nam ưa chuộng và sử dụng từ hàng trăm năm trước.',0,'vu_sua.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (3,N'Hồng Xiêm','25000','1000','1',N'Hồng Xiêm',N'Quả Hồng Xiêm là tên gọi ở miền Bắc, bà con miền Nam gọi là trái sapoche, đây là loại quả quí, lành tính dùng được cho người khỏe mạnh và cả người ốm. Để chất lượng quả thơm, ngọt lúc chín như ý cần lấy được quả già và biết cách dấm vừa độ chín.',10,'hong_xiem.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (3,N'Nhãn Lồng Hưng Yên ','40000','1000','1',N'Nhãn Lồng Hưng Yên',N'Nhãn có tên khoa học là Dimocarpus longan, tiếng hán việt gọi là “long nhãn”. Đây là một loại trái cây điển hình của vùng nhiệt đới thuộc thân gỗ, sống lâu năm. Theo tổng hợp từ các địa phương, hiện nay Hưng Yên là nơi có diện tích trồng lớn nhất và cho chất lượng nhãn ngon nhất, hiện nay Hưng Yên có gần 4 nghìn ha nhãn, trong đó, diện tích cho thu hoạch khoảng 3 nghìn ha.',15,'nhan_long.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (3,N'Quả Thanh Mai','90000','1000','1',N'Quả Thanh Mai',N'Quả thanh mai có cân bằng axit - đường tốt và là nguồn cung cấp thiamine, riboflavin, carotene, khoáng chất, chất xơ và hàm lượng vitamin rất cao. Chúng cũng là một nguồn tốt cung cấp chất chống oxy hóa tương tự (ví dụ anthocyanin) có màu đỏ rượu vang lợi ích sức khỏe. Ngoài anthocyanin, bayberry còn chứa flavonol, ellagitannin và các hợp chất phenolic như axit gallic, quercetin hexoside,...',0,'thanh_mai.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (3,N'Cam Sành','25000','1000','1',N'Cam Sành',N'Cam sành là một giống cây ăn quả thuộc chi Cam chanh phân bố rộng khắp Việt Nam từ Tuyên Quang, Hà Giang, Yên Bái tới Vĩnh Long, Tiền Giang, Cần Thơ. Nhưng nhìn chung cam sành thích hợp với vùng đất phù sa cổ màu mỡ,khí hậu mát ẩm. Cam sành vietgap đang là tiêu chuẩn trồng và chăm sóc chính của bà con nơi đây',5,'cam_sanh.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (3,N'Na Lạng Sơn ','30000','1000','1',N'Na Lạng Sơn',N'Na, hay còn gọi là mãng cầu ta, mãng cầu dai/giai, sa lê, phan lệ chi, là một loài thuộc chi Na (Annona) có nguồn gốc ở vùng châu Mỹ nhiệt đới. Quả na là một trong những trái cây nhiệt đới ngon nhất có nguồn gốc từ các thung lũng Andean của Peru và Ecuador. Ngày nay, loại quả vừa ngon vừa tốt cho sức khỏe này đã được trồng phổ biến ở khắp thể giới.',5,'na.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (3,N'Thanh Long','35000','1000','1',N'Thanh Long',N'Thanh long ruột trắng thuộc chi Hylocereus tên khoa học là Hylocereus undatus. Vỏ quả có màu hồng hoặc đỏ. Người ta gọi là thanh long ruột trắng vì ở Phan Thiết- Bình Thuận có còn một loại thanh long nữa là thanh long ruột đỏ',5,'thanh_long.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (4,N'Tinh Dầu Nghệ','50000','1000','1',N'Tinh Dầu Nghệ',N'Là một sản phẩm được chiết xuất từ củ nghệ, sản xuất bằng phương pháp chưng cất hơi nước, tinh dầu nghệ có dạng nước, màu vàng và mùi nghệ, hăng cay đặc trưng. Trong tinh dầu nghệ có chứa hơn 300 hợp chất phenol, trong đó nổi bật là hoạt chất Curcumin bao gồm các phân tử có khả năng tăng cường lượng oxy cung cấp cho não. Ngoài ra, tinh dầu nghệ cũng chứa các chất kháng khuẩn, chống nấm, kháng virut, chống giun, chống dị ứng và chống ký sinh trùng đem đến những công dụng tuyệt vời cho người dùng không chỉ về sức khỏe mà còn trong việc làm đẹp của chị em phụ nữ.',10,'tinh_dau_nghe.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (4,N'Tinh Dầu Hoa Hồng','50000','1000','1',N'Tinh Dầu Hoa Hồng',N'Tinh dầu hoa hồng là sản phẩm được chiết xuất từ cánh hoa hồng bằng phương pháp trưng cất hơi nước. Với mùi hương quyến rũ cùng nhiều dưỡng chất đặc biệt, tinh dầu hoa hồng được coi là nguồn cảm hứng bất tận của các nhà nghiên cứu và luôn có một chỗ đứng vững chắc trong nền công nghiệp mỹ phẩm. Không chỉ vậy, trong tinh dầu hoa hồng còn chứa rất nhiều những dưỡng chất có lợi cho sức khỏe như:  Ethanol, Stearpoten, Citronellyl Acetate, Eugenol, Nonanol, Nonanal, Citronellol, Citral, Carvone, Nerol, Phenyl Acetaldehyd, Farnesol, Phenylmenthyl Acetate, Methyl Eugenol…',5,'tinh_dau_hoa_hong.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (4,N'Tinh Dầu Dừa','50000','1000','1',N'Tinh Dầu Dừa',N'Đây là thành phẩm được làm, chiết xuất từ lượng dưỡng chất có trong quả dừa già. Không giống như những sản phẩm dầu dừa ép nóng bằng phương pháp thủ công thông thường, dầu dừa ép lạnh được sản xuất trên dây truyền sản xuất hiện đại nhờ công nghệ sấy lạnh, tách thành nhiều khâu khác nhau, đòi hỏi sự tỉ mỉ và kiên nhẫn. Dầu dừa ép lạnh luôn đảm bảo giữ được những thành phần dưỡng chất tốt, quan trọng, cần thiết nhất, đem lại hiệu quả cao cho người sử dụng. Bên cạnh đó, dầu dừa ép lạnh 100% tinh chất, không chứa nước hay bất kỳ tạp chất giúp bạn có thể bảo quản và sử dụng trong thời gian dài hơn so với các sản phẩm được làm từ phương pháp truyền thống.',5,'tinh_dau_dua.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (4,N'Tinh Dầu Gừng','50000','1000','1',N'Tinh Dầu Gừng',N'Tinh dầu gừng là thành phẩm được làm từ củ gừng, do vậy nó chứa các thành phần dưỡng chất có trong củ gừng và các đặc tính có lợi chẳng hạn như: nhuận tràng, khử trùng, cung cấp các loại vitamin,...Tinh dầu gừng được sử dụng trong việc bảo vệ duy trì tốt sức khỏe như: trị viêm họng, buồn nôn, đạu bụng, điều trị một số bệnh ảnh hưởng đến đường hô hấp, rong kinh, rối loạn kinh nguyệt,...Không những thế, tinh dầu gừng được sử dụng như một loại nước hoa để tạo mùi thơm, khiến chúng ta tự tin hơn.',5,'tinh_dau_gung.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (4,N'Mật Ong Rừng','100000','1000','1',N'Mật ong rừng',N'Mật ong rừng ở Hà Giang không hề có sự tác động của con người và hoàn toàn tự nhiên, mật ong rừng thường được thu hoạch duy nhất trong 1 mùa tầmk hoảng từ tháng 3 tới tháng 6 mỗi năm, mật ong rừng mang đậm vị đặc trưng của các bông hoa rừng, có vị ngọt và thanh. Mật ong rừng nguyên chất có màu rất đa dạng, từ nâu sẫm tới vàng sẫm, độ kết dính tương đối cao.',0,'mat_ong.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (4,N'Mật Ong Bạc Hà','100000','1000','1',N'Mật ong bạc hà',N'Mật ong hoa bạc hà có nguồn gốc từ khu vực Đồng Văn - Hà Giang, là một loài hoa mọc dại. Hoa bạc hà nở nhiều nhất vào tầm tháng 10 tới tháng 1. Dó đó mà mật ong bạc hà trên thị trường tương đối khan hiếm bởi tác dụng đặc biệt cho sức khỏe con người.',10,'mat_ong_bac_ha.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (5,N'Kiwi xanh','10000','1000','1',N'Kiwi xanh',N'Kiwi xanh chứa nhiều vitaminC, kali, acid folic và chất xơ. Giúp bồi dưỡng sức khỏe cho trẻ em và phụ nữ sau khi sinh. VitaminC trong kiwi xanh nhiều gấp đôi so với trái cam (cam sành, cam canh, cam cao phong, ... ). Chúng giúp cải thiện chức năng của hệ miễn dịch, phòng ngừa những tác động của chứng viêm gan và sự tấn công của virus và vi khuẩn, nâng cao sự miễn dịch, chống lại bệnh liệt dương. Ăn Kiwi xanh sẽ ngăn chặn việc tạo thành chất gelatin (chất keo) hoặc đông cục khi tiêu thụ các loại thực phẩm được chế biến từ sữa nhờ vào thành phần actindin.',0,'kiwi.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (5,N'Hạnh nhân','200000','1000','1',N'Hạnh nhân',N'Hạt hạnh nhân thuộc phân chi Amygdalus, tên gọi khác của nó là hạnh đào. Lớp vỏ của hạnh nhân rất cứng (vỏ phía trong), nếp nhăn quả hạnh nhân lượn sóng, bao quanh hạt giống. Hạnh nhân không phải là loại quả kiên, đây là 1 loại quả hạch. Bạn có thể rang hoặc ăn sống, hạnh nhân được dùng trong khá nhiều món ăn và cũng là loại hạt nằm trong bộ hạt dinh dưỡng đối với bà bầu.Có 2 loại hạnh nhân hiện nay đó là: hạnh nhân ngọt và hạnh nhân đắng. Hạnh nhân đắng có vị đắng, tính ấm, hơi độc.',10,'hanh_nhan.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (5,N'Súp lơ xanh','90000','1000','1',N'Súp lơ xanh',N'Súp lơ xanh còn gọi là bông cải xanh, nó thuộc loài cải bắp dại, có hoa lớn ở đầu và được sử dụng làm rau. Nó rất tốt cho sức khỏe, chúng chứa nhiều vitamin A, C, và E hơn các rau củ xanh khác, giúp bổ sung dinh dưỡng và tăng cường đề kháng cho cơ thể.Các nhà nghiên cứu khoa học đã chỉ ra rằng, súp lơ xanh giúp làm giảm nguy cơ mắc bệnh ung thư và nhiều loại bệnh tật khác.',0,'sup_lo_xanh.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (5,N'Bí Ngô','50000','1000','1',N'Bí Ngô',N'Bí Ngô (Cucurbita pepo) còn có tên gọi khác là bí rợ, bí đỏ thuộc họ nhà bầu bí. Đây là một thực phẩm rất tốt, có công dụng cực kỳ hữu ích. Hầu hết tất cả các bộ phận của cây bí đỏ đều có thể sử dụng được từ hoa bí đỏ, ngọn bí đỏ cho tới quả bí đỏ và hạt bí đỏ.',10,'bi_do.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (5,N'Hạt Điều Rang Muối','150000','1000','1',N'Hạt Điều Rang Muối',N'Điều hay còn gọi là đào lộn hột, là một cây công nghiệp thuộc họ xoài. Nó được trồng ở khí hậu nhiệt đới để lấy nhân hạt chế biến làm thực phẩm.Hạt điều ăn rất giòn và có hương thơm đặc trưng. Thường mọi người hay tìm mua hạt điều rang muối. Đây là đặc sản của Việt Nam nên thị trường hạt điều xuất khẩu luôn dẫn đầu cao.',10,'hatdieu.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (6,N'Xà lách đăm','50000','1000','1',N'Xà Lách',N'Xà lách đăm còn gọi là cải bèo, rau diếp. Tên khoa học là Lactuca sativa, là loại thực vật có hoa thuộc họ Cúc, có nguồn gốc khá lâu đời từ các nước Châu Âu, được mô tả khoa học lần đầu vào năm 1753.Tên của rau xà lách đăm bắt nguồn từ việc từ thân cây rau sau khi bị cắt sẽ chảy ra một loại nước trắng đục như cao su. Xà lách đăm là một loại rau ăn lá, có tính giải khát vì sự tinh khiết và tính an thần của nó.',5,'salach.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (6,N'Mật ong ','500000','1000','1',N'Mật ong',N'Mật ong rừng ở Hà Giang không hề có sự tác động của con người và hoàn toàn tự nhiên, mật ong rừng thường được thu hoạch duy nhất trong 1 mùa tầmk hoảng từ tháng 3 tới tháng 6 mỗi năm, mật ong rừng mang đậm vị đặc trưng của các bông hoa rừng, có vị ngọt và thanh. Mật ong rừng nguyên chất có màu rất đa dạng, từ nâu sẫm tới vàng sẫm, độ kết dính tương đối cao.',0,'matong.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (6,N'Mật ong gừng','250000','1000','1',N'Mật ong gừng',N'Mật ong gừng là thực phẩm rất quen thuốc với tất cả mọi người, mật ong gừng được dùng rất nhiều trong cuộc sống hàng ngàu. Khi kết hợp mật ong và gừng sẽ đem lại tác dụng gì tới sức khỏe con người. Rất nhiều nghiên cứu để chỉ ra rằng mật ong gừng có tác dụng rất tốt trong việc điều trị bệnh, tăng cường sức khỏe.',5,'mat_ong_gung.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (6,N'Cà chua bi','50000','1000','1',N'Cà chua bi',N'Cà chua bi, ngoài ra loại quả này còn được gọi với cái tên khá mỹ miều đó là Cherry Tomato . Đây là loại cà chua trái nhỏ quả hình tròn hoặc dài, màu đỏ, quả đều nhìn rất đẹp. Cà chua bi tuy quả nhỏ, ngọt hơn cà chua thông thường. Cà chua bi rât dễ trồng, quả rất sai, có thể trồng được quanh năm, sai quả, giá thành gấp 2 – 3 lần so với loại cà chua thông thường.',0,'ca_chua.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (6,N'Bông Atiso tươi','12000','1000','1',N'Bông Atiso',N'Bông Atiso là một trong những đặc sản nổi tiếng và đặc trưng nhất của Đà Lạt. Trong hoa atiso chứa hàm lượng dinh dưỡng cao có nhiều công dụng cho sức khỏe người sử dụng. ',5,'hoa_atiso.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (7,N'Măng tây','150000','1000','1',N'Măng tây',N'Măng tây không còn xa lạ gì với người Việt Nam, hành tía là loại thực phẩm không thể thiếu trong bữa cơm gia đình Việt. Hành tía giúp cho hương vị của món ăn trở nên hấp dẫn hơn nhiều. Không chỉ là một loại thực phẩm làm cho món ăn trở nên hấp dẫn hơn, hành tía còn mang đến cho chúng ta nhiều chất dinh dưỡng tốt cho sức khỏe.',0,'mangtay.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (7,N'Dưa lưới','80000','1000','1',N'Dưa lưới',N'Dưa lưới là một loại hoa quả sạch có hình tròn, khá to, có các đường gân trắng khá độc đáo. Ngoài ra, hương vị của nó cũng rất đặc biệt, ăn giòn và vị ngọt mát. Đây là loại trái cây giúp bổ sung nhiều dinh dưỡng cho cơ thể và thường được sử dụng để giải nhiệt . Nó gồm có 2 loại là dưa lưới vàng và dưa lưới xanh. ',5,'dua_luoi.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (7,N'Dưa leo','50000','1000','1',N'Dưa leo',N'Dưa leo (dưa chuột) là thực phẩm rất quen thuộc hàng ngày, đặc biệt là những công dụng của dưa chuột đối với sức khỏe. Dưa chuột chứa hàm lượng nước cao (trong quả dưa chuột chứa tới 95% lượng nước), giàu vitamin C và chất xơ giúp làn da trắng sáng, khỏe khoắn. Hơn nữa, dưa chuột còn có tác dụng làm giảm nhiệt và kháng viêm. Dưa chuột là món ăn nhẹ rất tốt trong mùa hè. ',15,'dua_chuot.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (7,N'Hạt mắc ca','350000','1000','1',N'Hạt mắc ca',N'Hạt mắc ca khi so với nhiều loại hạt dinh dưỡng khác như hạnh nhân, óc chó, hạt thông.. thì trong mắc ca chứa nhiều chất béo nhưng ít hàm lượng protein. Lượng chất béo không bão hòa đơn trong hạt mắc ca là cao nhất so với các loại hạt dinh dưỡng khác. Axit béo omega 7 có khoảng 22% trong hạt mắc ca hoạt động sinh học giống như 1 chất béo chưa bão hòa đơn. Ngoài ra, trong hạt mắc ca có chứa 2% sơ dinh dưỡng, 9% cacbohydrat, 9% protein cũng như các chất selen, photpho, natri, kali, riboflavin, thiamin, sắt và niacin.',20,'hat_mac_ca.jpg','2020-05-22');
insert into product(catalog_id,name,price,qty,status,description,content,discount,image_link,created) values (7,N'Táo xanh Pháp','85000','1000','1',N'Táo xanh Pháp',N'Từ lâu, táo xanh được công nhận là loại trái cây lành mạnh, đem lại nhiều lợi ích cho sức khỏe.Với thương hiệu Pháp, Táo xanh Pháp được nhiều người dân Việt ưa chuộng và lựa chọn tin cậy. Mùa vụ táo xanh chỉ khoảng 1 đợt trong năm, nhưng giá thành lại không cao.Chất xơ phức tạp của táo giúp no lâu hơn mà không bị tiêu thụ nhiều calo (một quả táo bình thường chỉ chứa khoảng 95 calo). Một loại axit có trong vỏ táo là Axit Ursolic làm giảm nguy cơ béo phì, Axit Ursolic thúc đẩy cơ thể đốt cháy calo, tăng việc hình thành cơ và giảm chất béo lâu năm trong cơ thể.',0,'tao_xanh.jpg','2020-05-22');


INSERT INTO review(product_id,name,email,content,created)
VALUES (3,'Kim Vy','kimvy@gmail.com',N'An toàn, sạch sẽ!','2020-10-10');
INSERT INTO review(product_id,name,email,content,created)
VALUES (4,'Kim Vy','kimvy@gmail.com',N'Sản phẩm chất lượng','2020-10-11');
INSERT INTO review(product_id,name,email,content,created)
VALUES (5,'Kim Vy','kimvy@gmail.com',N'Ngon, lần sau tôi sẽ mua tiếp','2020-10-12');


Insert into boardnew(title, content, image_link, author, created) values (N'Thực phẩm sạch tăng mạnh sau dịch Covid-19', N'Trong giai đoạn dịch Covid-19 bùng phát, ý thức về việc nâng cao sức khỏe của người dân ngày càng cao, thực phẩm sạch, an toàn đã được kiểm chứng đang là lựa chọn hàng đầu được nhiều gia đình hướng đến. <br> <br>Trước tình trạng thực phẩm kém chất lượng, hàng giả tràn lan trên thị trường, người tiêu dùng đang dần khắt khe hơn trong sự lựa chọn của mình. Trong vài năm trở lại đây, xu hướng thực phẩm an toàn, có nguồn gốc tự nhiên, thân thiện với môi trường trở nên phổ biến và trở thành một xu hướng mới trong ngành thực phẩm tại Việt Nam. Các cơ sở kinh doanh, chuỗi cửa hàng thực phẩm an toàn ngày càng nhiều với đủ mọi mặt hàng để phục vụ nhu cầu của người tiêu dùng. <br> <br>Báo cáo xu hướng tiêu dùng thực phẩm hữu cơ của AC Nielsen cho thấy có đến 86% người tiêu dùng Việt Nam ưu tiên lựa chọn sản phẩm organic cho những bữa ăn hàng ngày bởi tính an toàn, giàu dinh dưỡng và hương vị thơm ngon. Các chuyên gia cũng cho rằng, khi thu nhập của người dân tăng lên, nhu cầu về đời sống cao hơn, tỷ lệ dân số trẻ cao và tầng lớp trung lưu phát triển, người tiêu dùng sẽ dần trở thành những người tiêu dùng thông minh và hướng đến một lối sống xanh và lành mạnh thông qua việc sử dụng các thực phẩm có nguồn gốc hữu cơ và nguyên liệu sạch. <br> <br>Kinh doanh thực phẩm sạch đã 3 năm nay, cơ sở rau Khánh Thành cho biết nhiều khi “cung không đủ cầu”, bởi nhu cầu của khách hàng rất cao mà nguồn thực phẩm chất lượng lại có hạn. Thực phẩm ở cửa hàng của của chị đều có xuất xứ rõ ràng, từ các vùng trên khắp cả nước và có cả thực phẩm nhập khẩu, chủ yếu là các mặt hàng như thịt, rau củ quả, hải sản… Theo co sở, tuy giá của các loại thực phẩm hữu cơ, có nguồn gốc tự nhiên có giá thành cao hơn những thực phẩm thông thường nhưng khách hàng vẫn sẵn sàng chi trả để đảm bảo sức khỏe cho gia đình.<br> <br>Để khách hàng tin tưởng, cơ sở rau Khánh Thành đều công khai nguồn gốc xuất xứ, đồng thời dán tem QR code để có thể truy xuất nguồn gốc thực phẩm cùng những thông tin cơ bản. Mỗi ngày cửa hàng của chị nhận được hàng chục đơn hàng, lúc cao điểm có thể lên tới hàng trăm đơn. Tất cả các thực phẩm được sản xuất tại công ty luôn đảm bảo tiêu chuẩn VietGAP dựa trên 4 tiêu chí (Tiêu chuẩn về kỹ thuật sản xuất, An toàn thực phẩm, Môi trường làm việc, Truy tìm nguồn gốc sản phẩm) để tới tay người tiêu dùng. <br> <br>Dọc quanh các con phố, không khó để bắt gặp hình ảnh các cửa hàng thực phẩm hữu cơ, hoa quả sạch. Không chỉ đẩy mạnh việc bán hàng trực tiếp, các cửa hàng này còn phát triển các kênh bán hàng online, quảng bá về sản phẩm. <br> <br>', 'blog_1.jpg', N'Kim Vy', '2020-06-05');
Insert into boardnew(title, content, image_link, author, created) values (N'Phát hiện loại rau củ chứa chất chữa được bệnh nan y', N'Rau củ "siêu giàu" vitamin A đã giúp các nhà khoa học tạo ra thuốc chữa. <br><br>Điều đáng ngạc nhiên là chất cơ bản để họ tạo nên "thần dược" này lại là axit retinoic, một chất rất dễ tìm kiếm trong tự nhiên. Axit retinoic là hóa chất được tạo ra trong quá trình cơ thể chúng ta phân hủy vitamin A "siêu nạp" – được tìm thấy trong các loại rau củ được biết đến rất giàu vitamin A như cà rốt hay các loại rau mầm như cải brussel.<br><br>Theo các tác giả, axit retinoic là một hóa chất cực kỳ tốt cho hệ thần kinh. Khi được ứng dụng vào thuốc, nó có thể đem lại tác động mạnh mẽ hơn nhiều so với cách ăn trực tiếp và sẽ có tác dụng chữa bệnh. <br><br>Giáo sư Peter McCaffery, đến từ Đại học Aberdeen, thành viên nhóm nghiên cứu mô tả thuốc như một phiên bản khuyếch đại những gì mà quá trình phân hủy vitamin A đã tạo ra cho cơ thể. <br><br>Với cách ăn trực tiếp, các loại rau củ giàu vitamin A chỉ dừng lại ở mức tăng cường sức khỏe mắt, hệ thần kinh, ngăn ngừa các nguy cơ bệnh tật, hỗ trợ điều trị… chưa thể tác động mạnh mẽ như một loại thuốc thực sự. <br><br>Axit retinoic kích thích khả năng tái tạo các dây thần kinh và tế bào não, vì vậy ngoài Alzheimer, họ còn định ứng dụng hóa chất kỳ diệu này làm thuốc chữa bệnh Parkinson và một số bệnh thần kinh vận động khác.<br><br>Dự án có giá trị lên đến 250.000 bảng Anh và đã được thực hiện suốt 2 năm qua. Nhóm nghiên cứu cho biết hóa chất họ tạo nên đang trong giai đoạn kiểm tra lần cuối. Sẽ cần thêm một số thủ tục để nó được ứng dụng ra thị trường. Chi tiết nghiên cứu sẽ được công bố trên tạp chí khoa học trong năm tới.', 'blog_2.jpg', N'Lan Ngọc', '2020-05-22');
Insert into boardnew(title, content, image_link, author, created) values (N'Phù phép rau chợ đầu mối thành rau sạch tuồn vào trường học', N'Rau mua ngoài chợ, sau đó được sơ chế và phù phép thành các loại rau an toàn, rồi chuyển đến các trường tiểu học và mầm non trên địa bàn quận Tây Hồ (Hà Nội) <br><br>Thông tin từ Phòng cảnh sát phòng chống tội phạm về môi trường (PC49 - Công an Hà Nội), 2h30 sáng 14/1, tại chợ đầu mối rau Vân Nội (Đông Anh, Hà Nội), các nhân viên của Cty Rau củ quả Trung Thành (địa chỉ: Xóm Đầm, Vân Nội, huyện Đông Anh) đi thu mua rau tại chợ. Chỉ trong vòng 30 phút, họ đã thu gom được hàng trăm kg rau, củ quả. <br><br>3h30 sáng, ngay tại sân của Cty này, số hàng trên đã được sơ chế và phù phép thành các loại rau an toàn, sau đó được chất lên những chiếc xe máy chuyển đến các trường tiểu học và mầm non trên địa bàn quận Tây Hồ.<br><br>Sau quá trình trinh sát và thu thập chứng cứ, 6h30 cùng ngày, tổ công tác gồm Đội 4, PC49 và Thanh tra Sở NN-PTNT Hà Nội kiểm tra, phát hiện nhân viên của Cty CP Rau củ quả Trung Thành là Trần Văn Đỗ đang vận chuyển gần 2 tạ rau, củ, quả cho 2 trường mầm non trên địa bàn quận Tây Hồ là trường Mầm non Nhật Tân và trường mầm non Tứ Liên.<br><br>Tại thời điểm kiểm tra, chủ hàng không xuất trình được bất kỳ giấy tờ nào chứng minh nguồn gốc của sản phẩm. Điều đáng nói là hợp đồng ký kết giữa Cty Trung Thành và nhà trường cam kết với hàng ngàn phụ huynh học sinh của trường này cung cấp rau và thực phẩm an toàn có nguồn gốc xuất xứ.<br><br>Tuy nhiên, theo khẳng định của PC49, số thực phẩm này không thể đảm bảo để nhà trường chế biến thành các suất ăn. Đáng chú ý, nguồn cung cấp rau của Cty Trung Thành trước đây là HTX rau Đạo Đức, đã bị cơ quan công an phát hiện hành vi trà trộn rau bẩn vào rau an toàn hồi tháng 6 vừa qua.', 'blog_3.jpg', N'Lê Thạch', '2020-05-22');
Insert into boardnew(title, content, image_link, author, created) values (N'Việt Nam đứng thứ 6 thế giới về xuất khẩu mật ong', N'Theo Cục Chăn nuôi, các nhà nuôi ong và đàn ong nằm rải rác khắp cả nước, nhưng tập trung chủ yếu ở miền núi và trung du (trên 442.000 đàn), Tây Nguyên (trên 361.000 đàn), đây là những vùng sinh thái được đánh giá có tiềm năng để trở thành vùng SX mật ong tập trung. Thấp nhất là ĐBSCL (trên 35.000 đàn).<br><br>So với năm 2017 thì số lượng đàn ong năm 2018 tăng 6,7%; khu vực tăng cao nhất là vùng ĐBSH (14,34%), tăng thấp nhất là Tây Nguyên (2,95%), đặc biệt là vùng ĐBSCL số lượng đàn ong giảm 18,53%.<br><br>Nghề nuôi ong mật không những mang lại hiệu quả kinh tế cao, mà còn là nghề góp phần bảo vệ môi trường sinh thái, đem lại lợi ích cho cây trồng. Việc phát triển cây trồng cũng là tăng cường nguồn thức ăn tự nhiên cho đàn ong mật. Nghề nuôi ong đã chuyển dần từ hình thức chăn nuôi tự phát, nhỏ lẻ sang hình thức đầu tư nuôi ong tập trung với số lượng lớn. Hình thành các mô hình câu lạc bộ, HTX hoạt động có hiệu quả và nhanh chóng nhân rộng.<br><br>Một số tỉnh như Hà Giang, Sơn La đã xây dựng được thương hiệu, được Cục Sở hữu trí tuệ (Bộ Khoa học và Công nghệ) cấp giấy chứng nhận đăng ký chỉ dẫn địa lý cho sản phẩm mật ong bạc hà Mèo Vạc, cấp giấy chứng nhận đăng ký nhãn hiệu tập thể mật ong Sơn La…<br><br>Chất lượng sản phẩm tạo ra từ nuôi ong mật như mật ong, sữa ong chúa, sáp ong, keo ong, phấn hoa… được nâng lên, được người tiêu dùng trong và ngoài nước quan tâm, ưa chuộng.<br><br>Tại Hòa Bình, hiện có khoảng 60.000 đàn ong, với sản lượng mật đạt 600 tấn/năm. Chăn nuôi ong hiện nay trên địa bàn tỉnh đã thực sự là 1 nghề khai thác tốt tiềm năng, lợi thế góp phần xóa đói giảm nghèo.<br><br>Ong được nuôi để lấy mật trên địa bàn tỉnh chủ yếu là 2 loại giống: Ong ngoại và ong nội. Trong đó, ong nội chiếm chủ yếu. Năng suất ong nội đạt bình quân 12kg mật/đàn/năm, ong ngoại đạt trên 25kg mật/đàn/năm.<br><br>', 'blog_4.png', N'Lan Ngọc', '2020-05-22');











