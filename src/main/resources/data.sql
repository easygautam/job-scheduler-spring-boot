insert into ad(id) values(1);
insert into ad(id) values(2);
insert into ad(id) values(3);
insert into ad(id) values(4);

insert into mobile_app(id) values(1);

insert into ad_job(day, time, ad_id, mobile_app_id, type, enable) values(5, TO_TIMESTAMP('17:30:00', 'hh24:mi:ss'), 1, 1, 'NOTIFICATION', true);
insert into ad_job(day, time, ad_id, mobile_app_id, type, enable) values(5, TO_TIMESTAMP('17:30:30', 'hh24:mi:ss'), 1, 1, 'NOTIFICATION', true);
insert into ad_job(day, time, ad_id, mobile_app_id, type, enable) values(5, TO_TIMESTAMP('17:31:00', 'hh24:mi:ss'), 1, 1, 'NOTIFICATION', true);
insert into ad_job(day, time, ad_id, mobile_app_id, type, enable) values(5, TO_TIMESTAMP('17:31:30', 'hh24:mi:ss'), 1, 1, 'NOTIFICATION', true);