select
    *
from
    employees.employees
where
    /*%if empNo != 0 */
    emp_no = /* empNo */40000
    /*%end*/
    /*%if gender != null */
    and
    gender = /* gender */'M'
    /*%end*/
    /*%if firstName != null */
    and
    first_name = /* firstName */'Smith'
    /*%end*/
    /*%if lastName != null */
    and
    last_name = /* lastName */'Wilson'
    /*%end*/
    /*%if birthDateFrom != null */
    and
    birth_date >= /* birthDateFrom */'2024-07-26'
    /*%end*/
    /*%if birthDateTo != null */
    and
    birth_date <= /* birthDateTo */'2024-07-26'
    /*%end*/
    /*%if hireDateFrom != null */
    and
    hire_date >= /* hireDateFrom */'2024-07-26'
    /*%end*/
    /*%if hireDateTo != null */
    and
    hire_date <= /* hireDateTo */'2024-07-26'
    /*%end*/
order by
    emp_no desc
