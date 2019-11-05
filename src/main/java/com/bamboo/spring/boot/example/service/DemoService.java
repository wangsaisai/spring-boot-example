package com.bamboo.spring.boot.example.service;

import com.bamboo.spring.boot.example.dao.DemoDao;
import com.bamboo.spring.boot.example.entity.Demo;
import com.bamboo.spring.boot.example.model.DemoSearchInfo;
import com.bamboo.spring.boot.example.model.DemoType;
import com.bamboo.spring.boot.example.repository.DemoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

  @Autowired
  private DemoDao demoDao;

  @Autowired
  private DemoRepository demoRepository;

  public Demo findById(Long id) {
    return demoRepository.findById(id).orElseThrow(RuntimeException::new);
  }

  public List<Demo> findByIds(List<Long> ids) {
    return demoRepository.findByIds(ids);
  }

  public Demo findByName(String name) {
    return demoRepository.findByName(name);
  }

  public List<Demo> findByType(DemoType demoType) {
    return demoRepository.findByType(demoType);
  }

  public List<Demo> search(DemoSearchInfo demoSearchInfo) {
    return demoDao.search(demoSearchInfo);
  }

}
