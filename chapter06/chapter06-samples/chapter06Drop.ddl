ALTER TABLE ex03_customer DROP CONSTRAINT x03customerddrssfk
ALTER TABLE ex21_customer DROP CONSTRAINT x21customerddrssfk
ALTER TABLE ex24_customer DROP CONSTRAINT x24customerddrssfk
DROP TABLE ex03_address
DROP TABLE ex03_customer
DROP TABLE ex21_address
DROP TABLE ex21_book
DROP TABLE ex21_customer
DROP TABLE ex24_address
DROP TABLE ex24_customer
DROP TABLE ex22_address
DROP TABLE ex22_book
DROP TABLE ex22_customer
DROP TABLE ex36_book
DROP TABLE ex38_customer
DROP TABLE ex02_customer
DELETE FROM SEQUENCE WHERE SEQ_NAME = 'SEQ_GEN'
