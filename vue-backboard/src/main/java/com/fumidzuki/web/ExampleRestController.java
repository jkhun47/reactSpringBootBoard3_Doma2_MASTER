package com.fumidzuki.web;

import java.util.List;
import java.util.Map;

import com.fumidzuki.dtos.EmployeesDto;
import com.fumidzuki.entity.EmployeesEntity;
import com.fumidzuki.model.Header;
import com.fumidzuki.page.Pagination;
import com.fumidzuki.service.AccountService;
import com.fumidzuki.service.EmployeesService;
import org.seasar.doma.jdbc.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


//@Slf4j
//@RequiredArgsConstructor
@CrossOrigin
@RestController
//@RequestMapping("/board")
//@RequestMapping("/employee")
public class ExampleRestController {

  @Autowired
  AccountService accountService;

  @Autowired
  EmployeesService employeesService;

//  @GetMapping("one")
//  public List<AccountOne> one() {
//    return this.accountService.getAccountOne();
//  }

@GetMapping("/employee/list")
public Header<List<EmployeesEntity>> boardList(@RequestParam Map<String, String> queryString) {
//public Header<List<EmployeesEntity>> boardList(@RequestParam Map<String, String> params) {
//  EmployeesDto listResult = this.employeesService.getListResult(params.get("page"),params.get("sk"),params.get("sv"),params);
  EmployeesDto listResult = this.employeesService.getListResult(queryString);
  List<EmployeesEntity>  limitDatas = listResult.getList();
  Pagination pagination = listResult.getPage();

//  return null;
  return Header.OK(limitDatas,pagination);
}

  @GetMapping("/employee/{emp_no}")
  public EmployeesEntity getBoard(@PathVariable int emp_no){
    EmployeesEntity data = this.employeesService.getEmployee(emp_no);
//    return this.accountService.getBoard(id);
    return data;
  }

  @PostMapping("/employee")
  public Result<EmployeesEntity> create(@RequestBody EmployeesDto employeesDto){
    Result<EmployeesEntity> data = employeesService.register(employeesDto);
    return data;
  }

  @PatchMapping("/employee")
  public Result<EmployeesEntity> update(@RequestBody EmployeesDto employeesDto){
    Result<EmployeesEntity> data = employeesService.modify(employeesDto);
    return data;
  }

  @DeleteMapping("/employee/{emp_no}")
  public Result<EmployeesEntity> delete(@PathVariable int emp_no) {
   return employeesService.delete(emp_no);
  }

  @GetMapping("/employeeMax")
  public EmployeesEntity getNewEmpNo(){
    EmployeesEntity newEmpNo = this.employeesService.getNewEmpNo();
    return newEmpNo;
  }

  //old List Controller------------------------------------------------------------------------------------------------------------------
//  @GetMapping("/employees/list")
////    public EmployeesEntity boardList(@RequestParam Map<String, String> params) {
//    public Header<List<EmployeesEntity>> boardList(@RequestParam String page, @RequestParam String sk, @RequestParam String sv ) {
//    EmployeesDto listResult = this.employeesService.getListResult(page,sk,sv);
//    List<EmployeesEntity>  limitDatas = listResult.getList();
//    Pagination pagination = listResult.getPage();
//
//    return Header.OK(limitDatas,pagination);
//  }
////  public List<EmployeesEntity> boardList() {
////    List<EmployeesEntity> datas = this.employeesService.getEmployeesAll();
////    return datas;
//  }

//-------------------------------------------Board Controller-----------------------------------------
//  @GetMapping("list")
////  public List<BoardDto> boardList() {
////    List<BoardDto> datas = this.accountService.getBoardAll();
////    return datas;
//
////  public List<BoardEntity> boardList() {
////    List<BoardEntity> datas = this.accountService.getBoardAll();
////    return datas;
////  }
//
//  public Header<List<BoardEntity>> boardList(@RequestParam String page, @RequestParam String sk, @RequestParam String sv) {
////    List<BoardEntity> datas = this.accountService.getBoardAll();
////    List<BoardEntity> searchDatas = this.accountService.getSearchBoard(sk,sv);
////    int listCnt = datas.size();
////    int searchListCnt = searchDatas.size();
////    int curPage = Integer.parseInt(page);
////    Pagination pagination = null;
////    if(!sk.isEmpty() && !sv.isEmpty()){
////      pagination = new Pagination(searchListCnt,curPage,10,10);
////    }else{
////      pagination = new Pagination(listCnt,curPage,10,10);
////    }
////    List<BoardEntity> limitDatas = this.accountService.getBoardLimit(pagination,sk,sv);
//    BoardDto listResult = this.accountService.getListResult(page,sk,sv);
//    List<BoardEntity>  limitDatas = listResult.getList();
//    Pagination pagination = listResult.getPage();
////    return Header.OK(result,pagination);
//    return Header.OK(limitDatas,pagination);
//  }
//
//  @GetMapping("{id}")
//  public BoardEntity getBoard(@PathVariable Long id){
//    BoardEntity data = this.accountService.getBoard(id);
////    return this.accountService.getBoard(id);
//    return data;
//  }
//
//  @PostMapping("")
//    public Result<BoardEntity> create(@RequestBody BoardDto boardDto){
//    Result<BoardEntity> data = accountService.register(boardDto);
//    return data;
//  }
//  //  @PostMapping("")
////  public Result<BoardEntity> create(@RequestBody BoardDto boardDto){
////    Result<BoardEntity> data = accountService.addBoard(boardDto);
//////    return accountService.addBoard(boardEntity);
////    return data;
////  }
//  @PatchMapping("")
//    public Result<BoardEntity> update(@RequestBody BoardDto boardDto){
//    Result<BoardEntity> data = accountService.modify(boardDto);
//    return data;
//  }
//
////  @PatchMapping("")
////    public Result<BoardEntity> update(@RequestBody BoardDto boardDto){
////    Result<BoardEntity> data = accountService.modify(boardDto);
////    return data;
////  }
// ------------------------------------------Board controller---------------------------------------------
}
