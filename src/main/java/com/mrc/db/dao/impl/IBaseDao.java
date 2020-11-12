package com.mrc.db.dao.impl;

import com.mrc.db.dto.common.ResultData;

import java.util.List;

/**
 * 기본 Base Dao 인터페이스
 * @author jsh
 *
 * @param <TCond> 제너릭 조건
 * @param <TResult> 제너릭 결과
 */
public interface IBaseDao<TCond,TData> {
	/**
	 * 리스트 조회
	 * @param cond
	 * @return
	 */
	List<TData> getList(TCond cond);
	/**
	 * 데이터조회
	 * @param cond
	 * @return
	 */
	TData getData(TCond cond);
	/**
	 * 리스트저장
	 * @param list
	 * @return
	 */
	ResultData saveList(List<TData> list);
	/**
	 * 데이터저장
	 * @param list
	 * @return
	 */
	ResultData save(TData data);

}
