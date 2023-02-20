SELECT *
FROM myproject.employee;

delete
from employee;

ALTER TABLE employee
    AUTO_INCREMENT = 0;
insert employee (nickName, lastName, mail, phone,isAdmin)
values ('Bob', 'Petrov', '@mail', '050***',true);
update employee
set nickName = 'Rob',
    lastName  = 'Kotov',
    mail      = '@mail',
    phone='0670***'
where id = 1;

SELECT *
FROM myproject.workdays;
delete
from workdays;
ALTER TABLE workdays
    AUTO_INCREMENT = 0;
insert workdays (day) value ('2025-01-01');
update workdays
set day = '2024-02-01'
where id = 1;

SELECT *
FROM myproject.workingshift;
delete
from workingshift;

ALTER TABLE workingshift
    AUTO_INCREMENT = 0;

insert workingshift (end, start) value ('10:30', '15:45');
update workingshift
set end = '15:45'
where id = 1;

select nickName, lastName, mail, phone, work_day_id, shift_id
from employee
         join schedule on employee_id = employee.id
order by work_day_id;

select day, nickName, lastName, mail, phone, start, end
from employee
         join schedule on employee_id = employee.id
         join workdays on workdays.id = schedule.work_day_id
         join workingshift on workingshift.id = schedule.shift_id
order by work_day_id;