package com.chao.storagebox.dao;

import com.chao.storagebox.entity.Area;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AreaMapper {
	List<Area> getAreaList();

	Area getAreaById(@Param("areaId") String areaId);

	int addArea(Area area);

	int updateArea(Area area);

	int deleteArea(@Param("areaId") String areaId);
}