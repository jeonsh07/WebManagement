package com.mrc.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mrc.db.dto.address.t_address_location1;

@Mapper
public interface IAddressLocation1Mapper {
	Integer save(t_address_location1 data);
	List<t_address_location1> getList(t_address_location1 cond);
}
