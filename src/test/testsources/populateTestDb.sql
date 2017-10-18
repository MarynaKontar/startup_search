insert into user (id, username, PASSWORD) values (1, 'user1', 'pass1');
insert into user (id, username, PASSWORD) values (2, 'user2', 'pass2');
insert into user (id, username, PASSWORD) values (3, 'user3', 'pass3');

insert into address  values (1, 'AFGHANISTAN', 'city1', 'region1');
insert into address  values (2, 'AFGHANISTAN', 'city2', 'region2');
insert into address  values (3, 'AFGHANISTAN', 'city3', 'region3');

insert into businessplan values (1, 'idea1', 'state1', 'market1');
insert into businessplan values (2, 'idea2', 'state2', 'market2');
insert into businessplan values (3, 'idea3', 'state3', 'market3');

insert into education values (1, 1, 'institution1', 'faculty1', 'stage1', 'field1', 'FULL_TIME', '1000-01-01 00:00:00.000000', '1000-01-01 00:00:00.000000');
insert into education values (2, 1, 'institution2', 'faculty2', 'stage2', 'field2', 'FULL_TIME', '1000-01-01 00:00:00.000000', '1000-01-01 00:00:00.000000');
insert into education values (3, 2, 'institution2', 'faculty2', 'stage2', 'field2', 'FULL_TIME', '1000-01-01 00:00:00.000000', '1000-01-01 00:00:00.000000');
insert into education values (4, 3, 'institution2', 'faculty2', 'stage2', 'field2', 'FULL_TIME', '1000-01-01 00:00:00.000000', '1000-01-01 00:00:00.000000');

insert into experience values (1, 1, 'company1', 'position1', 'responsibility1', '1000-01-01 00:00:00.000000', '1000-01-01 00:00:00.000000');
insert into experience values (2, 2, 'company2', 'position2', 'responsibility2', '1000-01-01 00:00:00.000000', '1000-01-01 00:00:00.000000');
insert into experience values (3, 3, 'company3', 'position3', 'responsibility3', '1000-01-01 00:00:00.000000', '1000-01-01 00:00:00.000000');

insert into interest (id, name, user_id, budget, industry, country) values (1, 'name1', 1, 100, 'AGRICULTURE', 'AFGHANISTAN');
insert into interest (id, name, user_id, budget, industry, country) values (2, 'name2', 2, 200, 'BANKING', 'ALBANIA');
insert into interest (id, name, user_id, budget, industry, country) values (3, 'name3', 3, 300, 'COMMUNICATIONS', 'ALGERIA');

insert into project (id, name, user_id, lastChange, industry, address_id) values (1, 'name1', 1, '1000-01-01 00:00:00.000000', 'AGRICULTURE', 1);
insert into project (id, name, user_id, lastChange, industry, address_id) values (2, 'name2', 2, '1000-01-01 00:00:00.000000', 'BANKING', 2);
insert into project (id, name, user_id, lastChange, industry, address_id) values (3, 'name1', 3, '1000-01-01 00:00:00.000000', 'COMMUNICATIONS', 3);