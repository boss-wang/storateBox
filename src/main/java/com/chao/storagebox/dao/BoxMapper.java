package com.chao.storagebox.dao;

import com.chao.storagebox.entity.Box;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoxMapper {
	List<Box> getBoxList(@Param("areaId") String areaId);

	Box getBoxById(@Param("boxId") String boxId);

	int addBox(Box box);

	int updateBox(Box box);

	int deleteBox(@Param("boxId") String boxId);
}