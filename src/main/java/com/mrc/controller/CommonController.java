/**
 * 
 */
package com.mrc.controller;

import com.mrc.db.dao.GlobalDao;
import com.mrc.db.dto.address.address_cond;
import com.mrc.db.dto.address.location_dong;
import com.mrc.db.dto.address.t_address_area;
import com.mrc.db.dto.common.*;
import com.mrc.framework.ExcelInfo;
import com.mrc.framework.Global;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * @author jsh
 *
 */
@RestController
public class CommonController {
	

	@ResponseBody
	@PostMapping("/common/getSelectItemList")
	public List<SelectItem> getSelectItemList(@RequestBody common_cond cond)
	{
		List<SelectItem> rtn = GlobalDao.CommonDao.getSelectItemList(cond);
		return rtn;
	}
	
	@RequestMapping(value = "/excelDown")
	public void excelDown(HttpServletResponse response) throws Exception {
		
		List<SelectItem> list = new ArrayList<SelectItem>();

		list.add(SelectItem.builder().sub_code(1).name("1번").build());
		list.add(SelectItem.builder().sub_code(2).name("2번").build());
		List<String> headers = new ArrayList<String>();
		headers.add("코드");
		headers.add("이름");
		try {
			new ExcelInfo<SelectItem>(SelectItem.class).listToExcel(response, list, headers, "엑셀다운로드");
		}catch(Exception ex) {}
	}
	
	@ResponseBody
	@PostMapping("/common/getAddressAreaList")
	public List<t_address_area> getAddressAreaList(@RequestBody t_address_area cond)
	{
		List<t_address_area> rtn = GlobalDao.CommonDao.getAddressAreaList(cond);
		return rtn;
	}
	
	@ResponseBody
	@PostMapping("/common/getCommonList")
	public List<t_common> getCommonList(@RequestBody common_cond cond)
	{
		List<t_common> rtn = GlobalDao.CommonDao.getList(cond);
		return rtn;
	}
	
	@ResponseBody
	@PostMapping("/common/save")
	public ResultData CommonSave(@RequestBody t_common data)
	{
		
		data.setInsert_code(Global.LoginInfo.getIsLogin() == true ? Global.LoginInfo.getMember().getMember_code() : 1);
		data.setCommon_code(data.getCommon_code() == null ? -1 : data.getCommon_code());
		ResultData rtn = GlobalDao.CommonDao.save(data);
		return rtn;
	}
	@ResponseBody
	@PostMapping("/common/delete")
	public ResultData commonDelete(@RequestBody t_common data)
	{
		ResultData rtn = GlobalDao.CommonDao.delete(data);
		return rtn;
	}
	
	@ResponseBody
	@PostMapping("/common/getTableDesc")
	public String getTableDesc()
	{
		return StringUtils.join(GlobalDao.CommonDao.getTableToHtml(), "");
	}
	
	@ResponseBody
	@PostMapping("/common/getCurrentDong")
	public List<location_dong> getCurrentDong(@RequestBody Kakaomap_cond cond) {
		
		List<location_dong> rtn = GlobalDao.AddressAreaDao.getAddressAreaBylocation(cond);
		return rtn;
		//return GlobalDao.AddressLocationDao.getCurrentDong(cond);
	}
	
	@ResponseBody
	@PostMapping("/common/getCodeByAddress")
	public List<location_dong> getCodeByAddress(@RequestBody address_cond cond) {
		List<location_dong> list = GlobalDao.AddressAreaDao.getCodeByAddress(cond);
		//if(list.size() > 0 && idx > 0) {list.get(0).setIdx(idx);}
		return list;
	}
}
