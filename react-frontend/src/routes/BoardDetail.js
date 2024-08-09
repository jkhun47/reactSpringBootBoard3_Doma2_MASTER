import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import Board from "../components/Board";

const BoardDetail = () => {
  const { emp_no } = useParams(); // url에 있는 param값을 가져오겠다.
  const [loading, setLoading] = useState(true);
  const [board, setBoard] = useState({});
  const getBoardDetail = async () => {
    const resp = await (
      await axios.get(`//localhost:8080/employee/${emp_no}`)
    ).data;
    setBoard(resp);
    // console.log(resp);
    setLoading(false);
  };

  useEffect(() => {
    getBoardDetail();
  }, []);
  return (
    <div>
      {/* 게시판 상세보기가 표시됩니다. */}
      {loading ? (
        <h2>loading...</h2>
      ) : (
        <Board
          emp_no={board.emp_no}
          first_name={board.first_name}
          last_name={board.last_name}
          gender={board.gender}
          birth_date={board.birth_date}
          hire_date={board.hire_date}
        />
      )}
    </div>
  );
};

export default BoardDetail;
