ALTER TABLE ex02_city DROP CONSTRAINT FK_ex02_city_ID
ALTER TABLE ex02_country DROP CONSTRAINT FK_ex02_country_ID
ALTER TABLE ex34_customer DROP CONSTRAINT x34customerDDRSSID
ALTER TABLE ex39_customer DROP CONSTRAINT ex39customeradd_fk
ALTER TABLE ex45_order_line DROP CONSTRAINT x45orderlinerderfk
ALTER TABLE ex59_book DROP CONSTRAINT FK_ex59_book_ID
ALTER TABLE ex59_cd DROP CONSTRAINT FK_ex59_cd_ID
ALTER TABLE ex63bis_book DROP CONSTRAINT FK_ex63bis_book_ID
ALTER TABLE ex27_tag DROP CONSTRAINT ex27_tag_Book23_ID
ALTER TABLE ex28_track DROP CONSTRAINT ex28_track_CD24_ID
ALTER TABLE ex40_order_ex40_order_line DROP CONSTRAINT x40rdrx40rdrdrLnsD
ALTER TABLE ex40_order_ex40_order_line DROP CONSTRAINT x40rdrx40rdrrdr40D
ALTER TABLE ex43_jnd_ord_line DROP CONSTRAINT x43jndordlinerdrfk
ALTER TABLE ex43_jnd_ord_line DROP CONSTRAINT x43jndrdlnerdrlnfk
ALTER TABLE ex46_jnd_art_cd DROP CONSTRAINT ex46jndart_cdcd_fk
ALTER TABLE ex46_jnd_art_cd DROP CONSTRAINT x46jndartcdrtistfk
ALTER TABLE ex49_news_ex49_comment DROP CONSTRAINT x49nwsx49cmcmmntsD
ALTER TABLE ex49_news_ex49_comment DROP CONSTRAINT x49nwsx49cmmNws49D
ALTER TABLE ex51_news_ex51_comment DROP CONSTRAINT x51nwsx51cmcmmntsD
ALTER TABLE ex51_news_ex51_comment DROP CONSTRAINT x51nwsx51cmmNws51D
DROP TABLE ex29_book
DROP TABLE ex01_book
DROP TABLE ex02_address
DROP TABLE ex02_city
DROP TABLE ex02_country
DROP TABLE ex05_news
DROP TABLE ex06_news
DROP TABLE ex09_track
DROP TABLE ex11_book
DROP TABLE ex14_customer
DROP TABLE ex17_credit_card
DROP TABLE ex20_customer
DROP TABLE ex27_book
DROP TABLE ex24_cd
DROP TABLE ex29_customer
DROP TABLE ex32_customer
DROP TABLE ex34_address
DROP TABLE ex34_customer
DROP TABLE ex39_address
DROP TABLE ex39_customer
DROP TABLE ex40_order
DROP TABLE ex40_order_line
DROP TABLE ex43_order
DROP TABLE ex43_order_line
DROP TABLE ex45_order
DROP TABLE ex45_order_line
DROP TABLE ex46_artist
DROP TABLE ex46_cd
DROP TABLE ex49_comment
DROP TABLE ex49_news
DROP TABLE ex51_comment
DROP TABLE ex51_news
DROP TABLE ex53_item
DROP TABLE ex56_item
DROP TABLE ex59_item
DROP TABLE ex59_book
DROP TABLE ex59_cd
DROP TABLE ex60_book
DROP TABLE ex60_cd
DROP TABLE ex60_item
DROP TABLE ex61_book
DROP TABLE ex61_cd
DROP TABLE ex61_item
DROP TABLE ex63_book
DROP TABLE ex63bis_item
DROP TABLE ex63bis_book
DROP TABLE ex66_book
DROP TABLE ex27_tag
DROP TABLE ex28_track
DROP TABLE ex40_order_ex40_order_line
DROP TABLE ex43_jnd_ord_line
DROP TABLE ex46_jnd_art_cd
DROP TABLE ex49_news_ex49_comment
DROP TABLE ex51_news_ex51_comment
DELETE FROM SEQUENCE WHERE SEQ_NAME = 'SEQ_GEN'
