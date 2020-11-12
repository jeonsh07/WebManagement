/**
 * 
 */
package com.mrc.db.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jsh 글로벌 맵퍼
 */
@Component
public class GlobalMapper {

	public static IMemberMapper MemberMapper;
	public static IMemberWithdrawMapper MemberWithdrawMapper;
	public static ICommonMapper CommonMapper;
	public static IMenuMapper MenuMapper;
	public static IFileMapper FileMapper;
	public static IBannerMapper BannerMapper;
	public static IAddressLocationMapper AddressLocationMapper;
	public static IMapSquareMapper MapSquareMapper;
	public static IKeywordMapper KeywordMapper;
	public static IBoardMapper BoardMapper;
	public static IPersistentLoginsMapper PersistentLoginsMapper;
	public static IMediaShopMapper MediaShopMapper;
	public static IAddressAreaMapper AddressArea;
	public static IMemberBookmarkMapper MemberBookmark;
	public static IAddressLocation1Mapper AddressLocation1Mapper;
	public static ISignageMapper SignageMapper;
	public static ISignagePlaceMapper SignagePlaceMapper;
	
	@Autowired
	private GlobalMapper(IMemberMapper member
			            , IMemberWithdrawMapper memberWithdraw
						, ICommonMapper common
						, IMenuMapper menu, IFileMapper file
			            , IAddressLocationMapper addressLocation
			            , IMapSquareMapper mapSquare, IBannerMapper banner,
			             IKeywordMapper keyword, IBoardMapper board, IPersistentLoginsMapper persistentLoginsMapper,
			             IMediaShopMapper mediaShop, IAddressAreaMapper addressArea, IMemberBookmarkMapper memberBookmark,
			             IAddressLocation1Mapper addressLocation1Mapper
			             ,ISignageMapper signageMapper
			             ,ISignagePlaceMapper signagePlaceMapper) {
		MemberMapper = member;
		MemberWithdrawMapper = memberWithdraw;
		CommonMapper = common;
		MenuMapper = menu;
		FileMapper = file;
		BannerMapper = banner;
		AddressLocationMapper = addressLocation;
		MapSquareMapper = mapSquare;
		KeywordMapper = keyword;
		BoardMapper = board;
		PersistentLoginsMapper = persistentLoginsMapper;
		MediaShopMapper = mediaShop;
		AddressArea = addressArea;
		MemberBookmark = memberBookmark;
		AddressLocation1Mapper = addressLocation1Mapper;
		SignageMapper = signageMapper;
		SignagePlaceMapper = signagePlaceMapper;
	}
}
