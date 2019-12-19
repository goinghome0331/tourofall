package net.bulldozer.tourofall.user.service;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.web.context.request.WebRequest;

import net.bulldozer.tourofall.user.dto.SocialService;
import net.bulldozer.tourofall.user.dto.UserRegistrationForm;
import net.bulldozer.tourofall.user.util.RandomUtil;

public class ConnectionManager {
	private FacebookConnectionFactory facebookConnectionFactory;
	private ProviderSignInUtils providerSignInUtils;

	public ConnectionManager(FacebookConnectionFactory facebookConnectionFactory,
			ProviderSignInUtils providerSignInUtils) {
		this.facebookConnectionFactory = facebookConnectionFactory;
		this.providerSignInUtils = providerSignInUtils;
	}

	public Connection<?> getConnection(WebRequest request) {
		return providerSignInUtils.getConnectionFromSession(request);
	}

	public void signupForConnectionRepository(String userId, WebRequest request) {
		providerSignInUtils.doPostSignUp(userId, request);
	}

	public UserRegistrationForm createRegistrationUserForm(Connection<?> connection) {
		UserRegistrationForm dto = new UserRegistrationForm();
		if (connection != null) {
			ConnectionData connectionData = connection.createData();
			switch (connectionData.getProviderId()) {
			case "facebook": {
				AccessGrant accessGrant = new AccessGrant(connectionData.getAccessToken());
				Connection<Facebook> facebookConnection = facebookConnectionFactory.createConnection(accessGrant);
				Facebook facebook = facebookConnection.getApi();
				String[] fields = { "first_name", "last_name", "gender", "id" };
				User userProfile = facebook.fetchObject("me", User.class, fields);
				dto.setUsername(userProfile.getId());

				final String password = Integer.toString(RandomUtil.getEightChiphersInteger());
				dto.setPassword(password);
				dto.setConfirmPassword(password);

				if (userProfile.getGender().equals("male"))
					dto.setGender(false);
				else
					dto.setGender(true);

				dto.setFirstName(userProfile.getFirstName());
				dto.setLastName(userProfile.getLastName());

				ConnectionKey providerKey = facebookConnection.getKey();
				dto.setSignInProvider(SocialService.valueOf(providerKey.getProviderId()));
				break;
			}
			case "twitter": {

				break;
			}
			}
		}
		return dto;
	}
}
