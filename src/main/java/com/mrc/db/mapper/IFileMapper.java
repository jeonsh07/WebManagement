package com.mrc.db.mapper;

import com.mrc.db.dto.file.file_cond;
import com.mrc.db.dto.file.t_file;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;



@Mapper
public interface IFileMapper {
	List<t_file> getList(file_cond cond);
	void save(t_file data);
	void remove(t_file data);
	void update(t_file data);
}
