package com.souche.springboot.dao.repository;

import com.souche.springboot.dao.dao.StudentDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StudentRepository extends PagingAndSortingRepository<StudentDao, Integer> {

    StudentDao getById(Integer id);


//    @Query("select u from student u where id = ?1")
//    StudentDao SerSql(Integer id);

}
