package com.southwind.controller;


import com.southwind.entity.Student;
import com.southwind.form.RuleForm;
import com.southwind.form.SearchForm;
import com.southwind.form.StudentForm;
import com.southwind.service.StudentService;
import com.southwind.util.ResultVOUtil;
import com.southwind.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-06-01
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public ResultVO save(@RequestBody Student student){
        Boolean saveStudent = this.studentService.saveStudent(student);
        if(!saveStudent) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        return ResultVOUtil.success(this.studentService.list(page, size));
    }

    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm){
        return ResultVOUtil.success(this.studentService.search(searchForm));
    }

    @GetMapping("/findById/{id}")
    public ResultVO findById(@PathVariable("id") Integer id){
        Student student = this.studentService.getById(id);
        StudentForm studentForm = new StudentForm();
        BeanUtils.copyProperties(student, studentForm);
        studentForm.setOldDormitoryId(student.getDormitoryId());
        return ResultVOUtil.success(studentForm);
    }

    @PutMapping("/update")
    public ResultVO update(@RequestBody StudentForm studentForm){
        Boolean update = this.studentService.update(studentForm);
        if(!update) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResultVO deleteById(@PathVariable("id") Integer id){
        Boolean delete = this.studentService.deleteById(id);
        if(!delete) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }
}

