select
    *
from
    employees.employees
where
    /*%if emp_no != 0 */
    emp_no = /* emp_no */40000
    /*%elseif gender != null */
    or
    gender = /* gender */'M'
    /*%elseif birth_date != null */
    or
    birth_date = /* birth_date */'2024-07-26'
    /*%elseif hire_date != null */
    or
    hire_date = /* hire_date */'2024-07-26'
    /*%end*/
order by
    emp_no desc
