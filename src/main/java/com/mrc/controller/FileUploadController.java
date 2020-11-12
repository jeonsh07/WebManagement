/**
 * 
 */
package com.mrc.controller;


import java.io.IOException;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.jcodec.api.JCodecException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.common.UploadFiles;
import com.mrc.db.dto.common.enResultType;
import com.mrc.db.dto.file.videoFileCond;
import com.mrc.framework.ExcelInfo;
import com.mrc.framework.ExcelParser;
import com.mrc.framework.FileInfo;
import com.mrc.framework.Global;



/**
 * @author jsh
 *
 */

@RestController
public class FileUploadController {
	
    
	@PostMapping("/file/uploadfilepond")
	public List<UploadFiles> uploadfilepond(@RequestParam MultipartFile[] filepond
			                              , @RequestParam(value= "isThumbnail", required = false) Boolean isThumbnail
			                              , @RequestParam(value= "size", required = false) int size) throws IOException {
		if(isThumbnail != null)
		{
			return Global.FileInfo.uploadFile(filepond,"", isThumbnail,size);
		}
		else
			return Global.FileInfo.uploadFile(filepond);
	}
	
	
	@PostMapping("/file/addressAreaexcelUpload")
	public String excelUpload(@RequestParam MultipartFile[] filepond) throws Exception {
		List<UploadFiles> list = Global.FileInfo.uploadFile(filepond);
		
		@SuppressWarnings("rawtypes")
		ExcelInfo excelUPload = new ExcelInfo();
		excelUPload.setCallBack(new ExcelInfo.IExcelToMapCallBack() {
			@Override
			public void result(Map<String, Object> map) {
				// TODO Auto-generated method stub
				ObjectMapper mapper = new ObjectMapper();
				String json;
				try {
					json = mapper.writeValueAsString(map);
					System.out.println("addressAreaexcelUpload 일반엑셀 업로드 리턴 " +json); 
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		String fileUrl = Global.ConfigInfo.getFileBasePath() + list.get(0).getAttachmentUrl();
		excelUPload.convertExcelToList( fileUrl);
		return "완료";
	}
	
	@PostMapping("/file/excelUpload")
	public ResultData excelUpload2(@RequestParam MultipartFile[] filepond) throws Exception {
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		List<UploadFiles> list = Global.FileInfo.uploadFile(filepond);
		
		if(list.size()> 0 )
		{
			Global.ExcelParser.setCallback(new ExcelParser.IExcelRowDataHandler() {
				
				@Override
				public void rowData(int rowNum, Map<String, Object> valueByHeader, List<String> headerList) throws Exception {
					// TODO Auto-generated method stub
					
					ObjectMapper mapper = new ObjectMapper();
					String json = mapper.writeValueAsString(valueByHeader);
					System.out.println("엑셀 업로드 리턴 " +json); 
				}
			});
			String fileUrl = Global.ConfigInfo.getFileBasePath() + list.get(0).getAttachmentUrl();
			Global.ExcelParser.parseExcel(fileUrl);
		}
		return rtn;
	}
	
	@PostMapping("/file/addressLocationfilepond")
	public String addressLocationfilepond(@RequestParam MultipartFile[] filepond) throws Exception {
		
		List<UploadFiles> rtn = Global.FileInfo.uploadFile(filepond);
		Global.FileInfo.setCallBack(new FileInfo.textfileToArrayListCallBack() {
			
			@Override
			public void result(Map<String, Object> map) throws Exception {
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(map);
				System.out.println("location 텍스트 업로드 리턴 " +json); 
			}
		});
		String res = Global.FileInfo.textfileToArrayList( rtn.get(0).getAttachmentUrl());
		if(res == "success") res= "업로드완료";
		return res;
	}
	
	@PostMapping("/file/uploadimge")
	public List<UploadFiles> uploadimage(@RequestParam MultipartFile[] image) throws IOException {
		return Global.FileInfo.uploadFile(image,"image");
	}
	
	@PostMapping("/file/multiimgprocess")
	public List<UploadFiles> multiimgprocess(@RequestParam(required=false) MultipartFile[] add , @RequestParam(required=false) List<String> del) throws IOException {
		return Global.FileInfo.multiImgProcess(add, del);
	}
	
	@ResponseBody
	@PostMapping("/file/deletefile")
	public ResultData deletefile(@RequestBody UploadFiles fileinfo) throws IOException {
		String filePath = Global.FileInfo.getRealFilePath(fileinfo.getAttachmentUrl());
		
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			if(filePath != "") Global.FileInfo.FileDelete(filePath);
			filePath =Global.FileInfo.getRealFilePath(fileinfo.getPickThumb());
			if(filePath != "") Global.FileInfo.FileDelete(filePath);
			filePath = Global.FileInfo.getRealFilePath(fileinfo.getThumbNailImage());
			if(filePath != "") Global.FileInfo.FileDelete(filePath);
			if(fileinfo.getArrThumbNailImage() != null)
			{
				List<String> thumblist = fileinfo.getArrThumbNailImage();
				
				for (String data : thumblist) {
					filePath = Global.FileInfo.getRealFilePath(data);
					if(filePath != "") Global.FileInfo.FileDelete(filePath);	
				}
			}
		
		}catch(Exception ex) {
			rtn.setEnResultType(enResultType.Error);
			rtn.setMessage(ex.getMessage());
		}
		return rtn;
	}
	
	@ResponseBody
	@PostMapping("/file/deletefile2")
	public ResultData deletefile2(@RequestBody UploadFiles fileinfo) throws IOException {
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		String filePath = fileinfo.getAttachmentUrl();
		filePath = filePath.replace("/files/", "").replace("_takeP", "").replace("thumb_", "");
		
		String[] arr2 = filePath.split("\\/");
		String Extention =FilenameUtils.getExtension(filePath);
		String DirName = arr2[0];
		if(filePath != "") Global.FileInfo.FileDelete(Global.FileInfo.getRealFilePath(filePath));
		
		if(Extention.contains("mp4") ||Extention.contains("avi") || Extention.contains("mov") ||Extention.contains("wmv") ) {
			String fileName =filePath.replace("." + Extention, "").replace(DirName + "/","");
			filePath = DirName + "/" + fileName + ".png";
			if(filePath != "") Global.FileInfo.FileDelete(Global.FileInfo.getRealFilePath(filePath));
			filePath =  DirName + "/" + "thumb_" +fileName + ".png";
			if(filePath != "") Global.FileInfo.FileDelete(Global.FileInfo.getRealFilePath(filePath));
			
			filePath = DirName + "/" + fileName +"_takeP.png";
			if(filePath != "") Global.FileInfo.FileDelete(Global.FileInfo.getRealFilePath(filePath));
			for(int i=1; i<=6; i++)
			{
				filePath = DirName + "/thumb_" +fileName +"_" + Integer.toString(i) + ".png";
				if(filePath != "") Global.FileInfo.FileDelete(Global.FileInfo.getRealFilePath(filePath));
			}
		}
		else {
			String fileName =filePath.replace("."  + Extention, "").replace(DirName + "/","");
			//filePath = DirName + "/" + fileName + "." + Extention;
			//if(filePath != "") Global.FileInfo.FileDelete(Global.FileInfo.getRealFilePath(filePath));
			filePath = DirName + "/thumb_" + fileName + "." + Extention;
			if(filePath != "") Global.FileInfo.FileDelete(Global.FileInfo.getRealFilePath(filePath));
		}
		
		return rtn;
	}
	

	@PostMapping("/file/upload")
	public List<UploadFiles> upload(@RequestParam MultipartFile[] file) throws IOException {
		return Global.FileInfo.uploadFile(file);
	}
	@RequestMapping("/file/download/{dirpath}/{fileName:.+}")
	public void download(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("dirpath") String dirpath, @PathVariable("fileName") String fileName) throws IOException {
		Global.FileInfo.downloadFile(response, dirpath, fileName);
	}
	
	@RequestMapping("/filestream/{dirpath}/{fileName:.+}")
	 public StreamingResponseBody stream(HttpServletRequest req
			 								, @PathVariable("dirpath") String dirpath
			 								, @PathVariable("fileName") String fileName) throws Exception {
	  String filePath = dirpath + "/" + fileName; 
      return Global.FileInfo.stream(filePath);
   }
	
	@PostMapping("/file/GetVideoTakePicture")
	public String GetVideoTakePicture(@RequestBody videoFileCond cond) throws IOException, JCodecException
	{
		return Global.FileInfo.makeVideoPictureToThumbnail(cond);
	}
	
	class fileInfo{
		public String filePath;
		public double second;
		public Integer size;
	}
}
