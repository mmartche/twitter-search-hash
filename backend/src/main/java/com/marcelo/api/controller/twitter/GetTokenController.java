package com.marcelo.api.controller.twitter;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.marcelo.api.controller.dto.TwitterResultDto;
import com.marcelo.api.controller.form.BuscaPostForm;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

@RestController
@RequestMapping("/twitter")
public class GetTokenController {
	
	@Value("${api.twitter.consumerKey}")
	private String consumerKey;
	
	@Value("${api.twitter.consumerSecret}")
	private String consumerSecret;
	
	@Value("${api.twitter.accessToken}")
	private String accessToken;
	
	@Value("${api.twitter.accessTokenSecret}")
	private String accessTokenSecret;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GetTokenController.class);
	
    @RequestMapping("/getToken")
    public RedirectView getToken(HttpServletRequest request,Model model) {
    	//this will be the URL that we take the user to
    	String twitterUrl = "";
    	
		try {
			//get the Twitter object
			Twitter twitter = getTwitter();
			
			//get the callback url so they get back here
			String callbackUrl = "http://127.0.0.1:8090/twitterCallback";

			//go get the request token from Twitter
			RequestToken requestToken = twitter.getOAuthRequestToken(callbackUrl);
			
			//put the token in the session because we'll need it later
			request.getSession().setAttribute("requestToken", requestToken);
			
			//let's put Twitter in the session as well
			request.getSession().setAttribute("twitter", twitter);
			
			//now get the authorization URL from the token
			twitterUrl = requestToken.getAuthorizationURL();
			LOGGER.info("requestToken " + requestToken);
			LOGGER.info("twitter " + twitter);
			LOGGER.info("Authorization url is " + twitterUrl);
		} catch (Exception e) {
			LOGGER.error("Problem logging in with Twitter!", e);
		}
    	
		//redirect to the Twitter URL
		RedirectView redirectView = new RedirectView();
	    redirectView.setUrl(twitterUrl);
	    return redirectView;
    }

    @PostMapping
    public List<QueryResult> detalhar(@RequestBody BuscaPostForm form) {
    	Twitter twitter = getTwitter();
    	
        Query query = new Query("euro");
        QueryResult result = null;
		try {
			result = twitter.search(query);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println(result);
        for (Status status : result.getTweets()) {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }
    	return Arrays.asList(result);
    }
    
    /*
     * Instantiates the Twitter object
     */
    public Twitter getTwitter() {
    	Twitter twitter = null;
    	
		//build the configuration
    	ConfigurationBuilder builder = new ConfigurationBuilder();
    	builder.setOAuthConsumerKey(consumerKey);
    	builder.setOAuthConsumerSecret(consumerSecret);
    	builder.setOAuthAccessToken(accessToken);
    	builder.setOAuthAccessTokenSecret(accessTokenSecret);
 
    	Configuration configuration = builder.build();
    	
    	//instantiate the Twitter object with the configuration
    	TwitterFactory factory = new TwitterFactory(configuration);
    	twitter = factory.getInstance();
    	
    	return twitter;
    }
}
