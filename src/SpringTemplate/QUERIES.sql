insert into user(name,password,username)
values('User','a5700387a0b64f00ff5fe28ee22eb088c2f684a7ad9645d231bdac76b9456c7f','user');   -- pass = user

insert into user(name,password,username)
values('Admin','a4a88c0872bf652bb9ed803ece5fd6e82354838a9bf59ab4babb1dab322154e1','admin'); -- pass = admin

insert into user(name,password,username)
values('teste','10ef0b2657accdd0f5aa1299c244ac80c8974fd588d02a882782612b14fe57ae','teste'); -- pass = teste


insert into role(role) values('ROLE_USER');
insert into role(role) values('ROLE_ADMIN');


insert into permission(permission) values('PERM_TEAM_LIST');
insert into permission(permission) values('PERM_TEAM_ADD');
insert into permission(permission) values('PERM_TEAM_EDIT');
insert into permission(permission) values('PERM_TEAM_DEL');
insert into permission(permission) values('PERM_MAIL_SEND');
insert into permission(permission) values('PERM_MAIL_SEND_HTML');


insert into user_role values(1,1);
insert into user_role values(2,2);
insert into user_role values(3,1);


insert into role_permission values(1,1);
insert into role_permission values(2,1);
insert into role_permission values(2,2);
insert into role_permission values(2,3);
insert into role_permission values(2,4);
insert into role_permission values(2,5);
insert into role_permission values(2,6);

commit;

SELECT * FROM USER;
select * from user_role;
select * from role;
select * from role_permission;
select * from permission;

select r.role, p.permission
from role r, permission p, role_permission rp
where r.id = rp.Role_id
and rp.permissions_id = p.id;
