create table travel(  
UC_SEQ number primary key,
MAIN_TITLE varchar2(400) not null,
GUGUN_NM varchar2(400) not null,
LAT dECIMAL(8,6) not null,
LNG dECIMAL(8,5) not null,
GRIDX NUMBER NOT NULL,
GRIDY NUMBER NOT NULL,
PLACE varchar2(400) not null,
TITLE	varchar2(400) not null,
SUBTITLE	varchar2(400) not null,
ADDR1	varchar2(400) not null,
CNTCT_TEL	varchar2(400) not null,
HOMEPAGE_URL	varchar2(400) not null,
TRFC_INFO	varchar2(600) not null,
USAGE_DAY	varchar2(400) not null,
HLDY_INFO	varchar2(400) not null,
USAGE_DAY_WEEK_AND_TIME	varchar2(400) not null,
USAGE_AMOUNT	varchar2(400) not null,
MIDDLE_SIZE_RM1	varchar2(400) not null,
MAIN_IMG_NORMAL	varchar2(400) not null,
MAIN_IMG_THUMB varchar2(400) not null,
ITEMCNTNTS	varchar2(4000) not null);

create sequence seqtravel;