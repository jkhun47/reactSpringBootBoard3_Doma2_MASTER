import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const BoardWrite = () => {
  const navigate = useNavigate();

  const [board, setBoard] = useState({
    emp_no: "",
    gender: "",
    first_name: "",
    last_name: "",
    birth_date: "",
    hire_date: "",
  });

  const { emp_no, gender, first_name, last_name, birth_date, hire_date } =
    board; //비구조화 할당

  const onChange = (event) => {
    const { value, name } = event.target;
    setBoard({
      ...board,
      [name]: value,
    });
  };

  const saveBoard = async () => {
    await axios.post(`//localhost:8080/employee`, board).then((res) => {
      alert("등록되었습니다");
      navigate("/board");
    });
  };

  const backToList = () => {
    navigate("/board");
  };

  return (
    <div id="register">
      <h3>회원추가</h3>
      <form>
        <label>번호</label>
        <input type="text" name="emp_no" class="textType" onChange={onChange} />
        <br />
        <label>성별</label>
        <input type="text" name="gender" onChange={onChange} />
        <br />
        <label>FIRST NAME</label>
        <input type="text" name="first_name" class="text" onChange={onChange} />
        <br />
        <label>LAST NAME</label>
        <input type="text" name="last_name" class="text" onChange={onChange} />
        <br />
        <label>생년월일</label>
        <input type="date" name="birth_date" onChange={onChange} />
        <br />
        <label>입사일</label>
        <input type="date" name="hire_date" onChange={onChange} />
        <br />
        <div>
          <button className="button" onClick={saveBoard}>
            저장
          </button>
          &nbsp;&nbsp;
          <button className="button" onClick={backToList}>
            취소
          </button>
        </div>
      </form>
      {/* <div id="register">
        <h3>Registration page</h3>
        <form
          method="post"
          name="userRegistrationForm"
          onSubmit={this.submituserRegistrationForm}
        >
          <label>Name</label>
          <input
            type="text"
            name="username"
            value={this.state.fields.username}
            onChange={this.handleChange}
          />
          <div className="errorMsg">{this.state.errors.username}</div>
          <label>Email ID:</label>
          <input
            type="text"
            name="emailid"
            value={this.state.fields.emailid}
            onChange={this.handleChange}
          />
          <div className="errorMsg">{this.state.errors.emailid}</div>
          <label>Mobile No:</label>
          <input
            type="text"
            name="mobileno"
            value={this.state.fields.mobileno}
            onChange={this.handleChange}
          />
          <div className="errorMsg">{this.state.errors.mobileno}</div>
          <label>Password</label>
          <input
            type="password"
            name="password"
            value={this.state.fields.password}
            onChange={this.handleChange}
          />
          <div className="errorMsg">{this.state.errors.password}</div>
          <input type="submit" className="button" value="Register" />
        </form>
      </div> */}
    </div>
  );
};

export default BoardWrite;
