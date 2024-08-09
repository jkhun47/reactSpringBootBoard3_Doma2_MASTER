import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";

const BoardUpdate = () => {
  const navigate = useNavigate();
  const { emp_no } = useParams();

  const [board, setBoard] = useState({
    // emp_no: "",
    gender: "",
    first_name: "",
    last_name: "",
    birth_date: "",
    hire_date: "",
  });

  const { gender, first_name, last_name, birth_date, hire_date } = board; //비구조화 할당

  const getBoard = async () => {
    const resp = await (
      await axios.get(`//localhost:8080/employee/${emp_no}`)
    ).data;
    setBoard(resp);
  };

  const onChange = (event) => {
    const { value, name } = event.target; //event.target에서 name과 value만 가져오기
    setBoard({
      ...board,
      [name]: value,
    });
  };
  const updateBoard = async () => {
    await axios.patch(`//localhost:8080/employee`, board).then((res) => {
      alert("수정되었습니다");
      navigate("/board/" + emp_no);
    });
  };
  const backToDetail = () => {
    navigate("/board/" + emp_no);
  };

  useEffect(() => {
    getBoard();
  }, []);
  return (
    <div id="register">
      <h3>회원정보수정</h3>
      <br />
      <form>
        <label>번호</label>
        <input type="text" name="emp_no" value={emp_no} disabled={true} />

        <br />

        <label>성별</label>
        <input
          type="text"
          name="gender"
          value={gender}
          readonly={true}
          onChange={onChange}
        />

        <br />

        <label>FIRST NAME</label>
        <input
          type="text"
          name="first_name"
          value={first_name}
          disabled={true}
        />

        <br />

        <label>LAST NAME</label>
        <input type="text" name="last_name" value={last_name} disabled={true} />

        <br />

        <label>생년월일</label>
        <input
          type="date"
          name="birth_date"
          value={birth_date}
          readonly={true}
          onChange={onChange}
        />

        <br />

        <label>입사일</label>
        <input
          type="date"
          name="hire_date"
          value={hire_date}
          readonly={true}
          onChange={onChange}
        />

        <br />
        <div>
          <button class="button" onClick={updateBoard}>
            수정
          </button>
          <button class="button" onClick={backToDetail}>
            취소
          </button>
        </div>
      </form>
    </div>
  );
};

export default BoardUpdate;
