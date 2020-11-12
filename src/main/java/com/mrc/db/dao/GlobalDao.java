/**
 *
 */
package com.mrc.db.dao;

import com.mrc.db.dao.impl.*;
import com.mrc.db.dto.address.*;
import com.mrc.db.dto.banner.banner_cond;
import com.mrc.db.dto.banner.t_banner;
import com.mrc.db.dto.board.board_cond;
import com.mrc.db.dto.board.t_board;
import com.mrc.db.dto.board.t_board_read;
import com.mrc.db.dto.common.SelectItem;
import com.mrc.db.dto.common.common_cond;
import com.mrc.db.dto.common.t_common;
import com.mrc.db.dto.file.file_cond;
import com.mrc.db.dto.file.t_file;
import com.mrc.db.dto.keyword.keyword_cond;
import com.mrc.db.dto.keyword.t_keyword;
import com.mrc.db.dto.mediaShop.localbox_cond;
import com.mrc.db.dto.mediaShop.t_localbox;
import com.mrc.db.dto.member.*;
import com.mrc.db.dto.menu.menu_cond;
import com.mrc.db.dto.menu.t_menu;
import com.mrc.db.dto.mongoDb.log_cond;
import com.mrc.db.dto.mongoDb.t_log;
import com.mrc.db.dto.signage.signage_cond;
import com.mrc.db.dto.signage.t_signage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jsh
 *
 */
@Component
public class GlobalDao {

	public static IMemberDao<member_cond, t_member, memberList> MemberDao;
	public static IMemberWithDrawDao<t_member_withdraw> MemberWithDrawDao;
	public static ICommonDao<common_cond, t_common, SelectItem> CommonDao;
	public static IMenuDao<menu_cond, t_menu> MenuDao;
	public static IFileDao<file_cond, t_file> FileDao;
	public static ApiDao ApiDao;
	public static IBannerDao<banner_cond, t_banner> BannerDao;
	public static IAddressLocationDao<address_location_cond, t_address_location> AddressLocationDao;
	public static IMapSquareDao<map_square_cond, t_map_square> MapSquareDao;

	public static IKeywordDao<keyword_cond, t_keyword> KeywordDao;
	public static IBoardDao<board_cond, t_board, t_board_read> BoardDao;
	public static IMongodbDao<log_cond, t_log> MongodbDao;
	public static IPersistentLoginsDao<persistent_logins> PersistentLoginsDao;
	public static IMediaShopDao<localbox_cond, t_localbox> MediaShopDao;
	public static IAddressAreaDao<t_address_area> AddressAreaDao;
	public static IMemberBookmarkDao<t_member_bookmark> MemberBookmarkDao;
	public static ISignageDao<signage_cond, t_signage> SignageDao;

	@Autowired
	private GlobalDao(IMemberDao<member_cond, t_member, memberList> member
			        , IMemberWithDrawDao<t_member_withdraw> memberWithdraw
					, ICommonDao<common_cond, t_common, SelectItem> common
					, IMenuDao<menu_cond, t_menu> menudao
					, IFileDao<file_cond, t_file> filedao
					, ApiDao api
					, IBannerDao<banner_cond, t_banner> bannerdao
					, IAddressLocationDao<address_location_cond, t_address_location> addressLocationDao
					, IMapSquareDao<map_square_cond, t_map_square> mapSquareDao
					, IKeywordDao<keyword_cond, t_keyword> keywordDao
					, IBoardDao<board_cond, t_board, t_board_read> boardDao
					, IMongodbDao<log_cond, t_log> mongodbDao
					, IPersistentLoginsDao<persistent_logins> persistentloginsdao
					, IMediaShopDao<localbox_cond, t_localbox> mediaShopDao
					, IAddressAreaDao<t_address_area> addressAreaDao
					, IMemberBookmarkDao<t_member_bookmark> memberBookmarkDao
					, ISignageDao<signage_cond, t_signage> signageDao
			) {
		MemberDao = member;
		MemberWithDrawDao = memberWithdraw;
		CommonDao = common;
		MenuDao = menudao;
		FileDao = filedao;
		ApiDao = api;
		BannerDao = bannerdao;
		AddressLocationDao = addressLocationDao;
		MapSquareDao = mapSquareDao;

		KeywordDao = keywordDao;
		BoardDao = boardDao;
		MongodbDao = mongodbDao;
		PersistentLoginsDao = persistentloginsdao;
		MediaShopDao = mediaShopDao;
		AddressAreaDao = addressAreaDao;
		MemberBookmarkDao = memberBookmarkDao;
		SignageDao = signageDao;
	}
}
