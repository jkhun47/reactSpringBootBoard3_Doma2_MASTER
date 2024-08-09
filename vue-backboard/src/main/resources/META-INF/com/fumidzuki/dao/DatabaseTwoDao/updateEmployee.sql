update employees.employees
set
    emp_no = /* entity.emp_no */50000
    , birth_date = /* entity.birth_date */'2021-01-01'
    , first_name = /* entity.first_name */'Jessica'
    , last_name = /* entity.last_name */'SIMPSON'
    , gender = /* entity.gender */'M'
    , hire_date = /* entity.hire_date */'2022-01-01'
where
    emp_no = /* entity.emp_no */100
