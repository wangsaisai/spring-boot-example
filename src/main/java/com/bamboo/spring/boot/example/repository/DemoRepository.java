package com.bamboo.spring.boot.example.repository;

import com.bamboo.spring.boot.example.entity.Demo;
import com.bamboo.spring.boot.example.model.DemoType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DemoRepository extends JpaRepository<Demo, Long> {

  @Query("select demo from Demo demo where demo.id in :ids")
  List<Demo> findByIds(@Param(("ids")) List<Long> ids);

  Demo findByName(@Param("name") String name);

  @Transactional
  @Query("select demo from Demo demo where demo.demoType = :demoType")
  List<Demo> findByType(@Param("demoType") DemoType demoType);

  @Transactional
  @Query("select demo from Demo demo where demo.enable = enable")
  List<Demo> findByEnable(@Param("enable") Boolean enable);

  @Modifying
  @Transactional
  @Query("update Demo demo set demo.enable = :enable where demo.id = :id")
  int setEnable(@Param("enable") Boolean enable, @Param("id") Long id);
}
