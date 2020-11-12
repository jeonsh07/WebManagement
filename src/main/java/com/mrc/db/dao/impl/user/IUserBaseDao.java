package com.mrc.db.dao.impl.user;

import java.util.List;

/**
 * 사용자 Base Dao
 * @author jeon.sh
 *
 * @param <TCond>
 * @param <TData>
 */
public interface IUserBaseDao<TCond,TData> {
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
}
