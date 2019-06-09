package com.chao.storagebox.atom;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import com.chao.storagebox.entity.Box;
import com.chao.storagebox.dao.BoxMapper;
import java.util.List;

@Service
@Transactional
public class BoxAtom {
	@Resource
	private BoxMapper boxMapper;

	public List<Box> getBoxList(String areaId) {
		return boxMapper.getBoxList(areaId);
	}

	public Box getBoxById(String boxId)
	{
		return boxMapper.getBoxById(boxId);
	}

	public boolean addBox(Box box)
	{
		return boxMapper.addBox(box) > 0;
	}

	public boolean updateBox(Box box)
	{
		return boxMapper.updateBox(box) > 0;
	}

	public boolean deleteBox(String boxId)
	{
		return boxMapper.deleteBox(boxId) > 0;
	}
}