import React from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Board = ({
  emp_no,
  first_name,
  last_name,
  gender,
  birth_date,
  hire_date,
}) => {
  const navigate = useNavigate(); //Url의 파라미터 값을 가져와서 이용하기위해

  const moveToUpdate = () => {
    navigate("/update/" + emp_no);
  };

  const deleteBoard = async () => {
    if (window.confirm("게시글을 삭제하시겠습니까?")) {
      await axios.delete(`//localhost:8080/employee/${emp_no}`).then((res) => {
        alert("삭제되었습니다");
        navigate("/board");
      });
    }
  };

  const moveToList = () => {
    navigate("/board");
  };

  return (
    <div>
      <div class="list">
        <table>
          <thead>
            <tr>
              <th>번호</th>
              <th>이름</th>
              <th>성별</th>
              <th>생일</th>
              <th>입사일</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>{emp_no}</td>
              <td>
                {first_name} {last_name}
              </td>
              <td>{gender}</td>
              <td>{birth_date}</td>
              <td>{hire_date}</td>
            </tr>
          </tbody>
          <button class="editbtn" onClick={moveToUpdate}>
            수정
          </button>
          <button class="deletebtn" onClick={deleteBoard}>
            삭제
          </button>
          <button class="listbtn" onClick={moveToList}>
            목록
          </button>
        </table>
      </div>
      {/* <table class="w3-table-all">
        <tbody>
          <tr>
            <th>번호</th>
            <td>{emp_no}</td>
          </tr>
          <tr>
            <th>이름</th>
            <td>
              {first_name} {last_name}
            </td>
          </tr>
          <tr>
            <th>성별</th>
            <td>{gender}</td>
          </tr>
          <tr>
            <th>생일</th>
            <td>{birth_date}</td>
          </tr>
          <tr>
            <th>입사일</th>
            <td>{hire_date}</td>
          </tr>
        </tbody>
      </table> */}
      {/* <div>
        <h2>{emp_no}</h2>
        <h3>
          {first_name}&nbsp;
          {last_name}
        </h3>
        <hr />
        <p>{gender}</p>
        <p>{birth_date}</p>
        <p>{hire_date}</p>
      </div> */}
      {/* <div>
        <button onClick={moveToUpdate}>수정</button>
        <button onClick={deleteBoard}>삭제</button>
        <button onClick={moveToList}>목록</button>
      </div> */}
    </div>
  );
};

export default Board;
