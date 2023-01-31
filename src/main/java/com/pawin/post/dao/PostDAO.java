package com.pawin.post.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface PostDAO {
    //test용
	public List<Map<String, Object>> selectPostListTest();
}
