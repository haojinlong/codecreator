
drop table if exists t_database_info;
create table t_database_info(
	id int not null primary key auto_increment comment '唯一标识',
	name varchar(30) comment '数据库名',
	db_key varchar(30) comment '数据库验证标识',
	description varchar(100) comment '数据库说明',
	version int comment '当前数据库版本' ,
	user_id int comment '当前用户ID' ,
	create_time datetime comment '创建时间',
	update_time datetime comment '最后修改时间',
	available bit comment '是否可用',
	locked bit comment '是否被锁定'
) comment '数据库名';


drop table if exists t_user;
create table t_user(
	id int not null primary key auto_increment comment '唯一标识',
	name varchar(20) comment '登陆用户名',
	password varchar(20) comment '密码',
	full_name varchar(20) comment '真实姓名',
	phone_no varchar(20) comment '电话号码',
	email varchar(50) comment '邮箱',
	create_time datetime comment '账号生成时间',
	expire_day date comment '账号到期时间'
) comment '用户信息';


drop table if exists t_table_info;
create table t_table_info(
	id int not null primary key auto_increment comment '唯一标识',
	name varchar(20) comment '表名',
	description varchar(50) comment '表格说明',
	db_id int comment '所属数据库id',
	version int comment '当前表格版本',
	create_time datetime comment '创建时间',
	create_db_version int comment '创建时数据库的版本',
	update_time datetime comment '最后修改时间',
	update_db_version int comment '最后修改数据库的版本'
);

drop table if exists t_column_info;
create table t_column_info(
	id int not null primary key auto_increment comment '唯一标识',
	name varchar(20) comment '字段名',
	type_id int comment '字段类型id',
	`length` int comment '字段长度',
	table_id int comment '所属的表格id',
	table_version int comment '当前表格版本',
	description varchar(50) comment '字段说明'
) comment '字段信息';

drop table if exists t_column_update;
create table t_column_update(
	id int not null primary key auto_increment comment '唯一标识',
	table_id int comment '表格ID',
	table_version int comment '表格修改版本',
	old_column_name varchar(20) comment '原字段名称',
	new_column_name varchar(20) comment '新字段名称',
	type_code int comment '字段类型id',
	`length` int comment '字段长度',
	description varchar(50) comment '字段说明'
);

drop table if exists sys_column_type;
create table sys_column_type(
	id int not null primary key auto_increment comment '唯一标识',
	code int comment '类型编码',
	name varchar(20) comment '字段类型名', 
	need_length bit comment '是否需要长度属性'
) comment '类型信息说明';
