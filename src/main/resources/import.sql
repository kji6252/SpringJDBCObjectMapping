--
--    Copyright 2015-2016 the original author or authors.
--
--    Licensed under the Apache License, Version 2.0 (the "License");
--    you may not use this file except in compliance with the License.
--    You may obtain a copy of the License at
--
--       http://www.apache.org/licenses/LICENSE-2.0
--
--    Unless required by applicable law or agreed to in writing, software
--    distributed under the License is distributed on an "AS IS" BASIS,
--    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--    See the License for the specific language governing permissions and
--    limitations under the License.
--

create table USERS (id int8 primary key auto_increment, name varchar, email varchar, regdt DATE );

insert into USERS (name, email, regdt) values ('철수', 'avc@naver.com', SYSDATE);
insert into USERS (name, email, regdt) values ('영희', 'swdasd@gmail.com', SYSDATE);
insert into USERS (name, email, regdt) values ('길동', 'lakjklsjd@sdfhanmail.net', SYSDATE);