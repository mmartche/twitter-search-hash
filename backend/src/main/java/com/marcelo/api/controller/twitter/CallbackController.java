package com.marcelo.api.controller.twitter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.api.controller.dto.TwitterCallbackDto;

import twitter4j.Twitter;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

@RestController
@RequestMapping("/twitterCallback")
public class CallbackController {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(CallbackController.class); 
    
    @GetMapping()
    public ResponseEntity<TwitterCallbackDto> twitterCallback(@RequestParam(value="oauth_verifier", required=false) String oauthVerifier,
    	@RequestParam(value="denied", required=false) String denied,
    	HttpServletRequest request, HttpServletResponse response, Model model) {

    	if (denied != null) {
            //if we get here, the user didn't authorize the app
    		return ResponseEntity.notFound().build();
    	}
    	
    	//get the objects from the session
    	Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
        RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
        
        try {
            //get the access token
            AccessToken token = twitter.getOAuthAccessToken(requestToken, oauthVerifier);
            
            //take the request token out of the session
            request.getSession().removeAttribute("requestToken");
            
            //store the user name so we can display it on the web page
            model.addAttribute("username", twitter.getScreenName());
            
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            LOGGER.error("Problem getting token!",e);
            return ResponseEntity.notFound().build();
        }
    }
}
