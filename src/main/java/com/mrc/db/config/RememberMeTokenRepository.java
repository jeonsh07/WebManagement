package com.mrc.db.config;

import com.mrc.db.dao.GlobalDao;
import com.mrc.db.dto.member.persistent_logins;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RememberMeTokenRepository implements PersistentTokenRepository {

	// Logger logger = LoggerFactory.getLogger(getClass());


	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		persistent_logins data = persistent_logins.builder().saveType("N").series(token.getSeries())
				.user_name(token.getUsername()).token(token.getTokenValue()).build();
		// TODO Auto-generated method stub
		GlobalDao.PersistentLoginsDao.save(data);
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		// TODO Auto-generated method stub
		persistent_logins data = persistent_logins.builder().saveType("u").series(series).token(tokenValue).build();
		GlobalDao.PersistentLoginsDao.save(data);
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		// TODO Auto-generated method stub
		persistent_logins cond = persistent_logins.builder().series(seriesId).build();
		persistent_logins data = GlobalDao.PersistentLoginsDao.getData(cond);

		PersistentRememberMeToken rtn = new PersistentRememberMeToken(data.getUser_name(), data.getSeries(),
				data.getToken(), data.getLast_used());

		return rtn;
	}

	@Override
	public void removeUserTokens(String username) {
		// TODO Auto-generated method stub
		persistent_logins data = persistent_logins.builder().saveType("d").user_name(username).build();
		GlobalDao.PersistentLoginsDao.save(data);
	}

}
