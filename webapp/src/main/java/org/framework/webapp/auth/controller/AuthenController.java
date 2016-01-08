package org.framework.webapp.auth.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.org.apache.bcel.internal.generic.NEW;

@Controller
@RequestMapping("auth/authen")
public class AuthenController {
	protected static final Log LOG=LogFactory.getLog(AuthenController.class);
	private static final String DEFAULT_LOGIN_URL="login";
	private String loginUrl=DEFAULT_LOGIN_URL;
	
	private static final String DEFAULT_SUCCESS_URL="index";
	private String successUrl=DEFAULT_SUCCESS_URL;
	
	private static final String DEFAULT_CAPTCHA_PARAM="captcha";
	private String captchaParam=DEFAULT_CAPTCHA_PARAM;
	
	private static final String DEFAULT_CAPTCHA_KEY="captcha";
	private String captchaKey=DEFAULT_CAPTCHA_KEY;
	
	private boolean captchaEnable=true;
	
	public static final String DEFAULT_USERNAME_PARAM = "username";
    public static final String DEFAULT_PASSWORD_PARAM = "password";
    public static final String DEFAULT_REMEMBER_ME_PARAM = "rememberMe";
    
    private String usernameParam = DEFAULT_USERNAME_PARAM;
    private String passwordParam = DEFAULT_PASSWORD_PARAM;
    private String rememberMeParam = DEFAULT_REMEMBER_ME_PARAM;
	
    @Resource
	private ConfigurableCaptchaService configurableCaptchaService;
	
	

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	

	public String getCaptchaParam() {
		return captchaParam;
	}

	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}

	public String getCaptchaKey() {
		return captchaKey;
	}

	public void setCaptchaKey(String captchaKey) {
		this.captchaKey = captchaKey;
	}

	public boolean isCaptchaEnable() {
		return captchaEnable;
	}

	public void setCaptchaEnable(boolean captchaEnable) {
		this.captchaEnable = captchaEnable;
	}
	
	

	public String getUsernameParam() {
		return usernameParam;
	}

	public void setUsernameParam(String usernameParam) {
		this.usernameParam = usernameParam;
	}

	public String getPasswordParam() {
		return passwordParam;
	}

	public void setPasswordParam(String passwordParam) {
		this.passwordParam = passwordParam;
	}

	public String getRememberMeParam() {
		return rememberMeParam;
	}

	public void setRememberMeParam(String rememberMeParam) {
		this.rememberMeParam = rememberMeParam;
	}
	
	@ModelAttribute("user")
	private UserModel getNewUserModel(){
		UserModel um=new UserModel();
		um.captchaEnable=isCaptchaEnable();
		return um;
	}

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String toLoginPage(Model model){
		Subject subject=SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			return "redirect:"+getSuccessUrl();
		}
		
		model.addAttribute("captchaEnable", isCaptchaEnable());
		
		return getLoginUrl();
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute("user")UserModel user,Errors errors){
		validation(user, errors);
		if(errors.hasErrors()){
			return getLoginUrl();
		}
		
		String username=request.getParameter(getUsernameParam());
		String password=request.getParameter(getPasswordParam());
		String  rememberMe=request.getParameter(getRememberMeParam());
		
		return "";
	}
	
	@RequestMapping("/captcha")
	public void captcha(HttpServletRequest request,HttpServletResponse response){
		
        OutputStream out=null;
        
        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        try {
			out=response.getOutputStream();
			String value=EncoderHelper.getChallangeAndWriteImage(configurableCaptchaService, "png", out);
			System.out.println(value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(out!=null){
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void validation(UserModel user,Errors errors){
		String username=user.getUsername();
		String password=user.getPassword();
		String captcha=user.getCaptcha();
		
		if(isCaptchaEnable()){
			
		}
		
		if(username==null||"".equals(username.trim())){
			errors.rejectValue(getUsernameParam(), "base.authen.login.username.err.null", "用户名不能为空");
			return;
		}
		if(username.trim().length()<6||username.trim().length()>20){
			errors.rejectValue(getUsernameParam(), "base.authen.login.username.err.length", new Object[]{6,20}, "用户名长度必须为{0}-{1}");
			return;
		}
	}
	
	private class UserModel implements Serializable{
		private String username;
		private String password;
		private String captcha;
		private	Boolean captchaEnable;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getCaptcha() {
			return captcha;
		}
		public void setCaptcha(String captcha) {
			this.captcha = captcha;
		}
		public Boolean getCaptchaEnable() {
			return captchaEnable;
		}
		
		
	}
}
