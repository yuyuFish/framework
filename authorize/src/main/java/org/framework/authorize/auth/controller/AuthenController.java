package org.framework.authorize.auth.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("auth/authen")
public class AuthenController {
	protected static final Log LOG=LogFactory.getLog(AuthenController.class);
	private static final String DEFAULT_LOGIN_URL="auth/login";
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
    
    public static final String DEFAULT_ERROR_MESSAGE = "errorMessage";
    private String errorMessageKey=DEFAULT_ERROR_MESSAGE;
    
    @Resource(name="messageSource")
    private ReloadableResourceBundleMessageSource messageSource;
	
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

	

	public String getErrorMessageKey() {
		return errorMessageKey;
	}

	public void setErrorMessageKey(String errorMessageKey) {
		this.errorMessageKey = errorMessageKey;
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
	

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String toLoginPage(HttpServletRequest request,Model model){
		Subject subject=SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			return "redirect:"+getSuccessUrl();
		}
		
		model.addAttribute("captchaEnable", checkCaptchaEnable());
		
		return "WEB-INF/"+getLoginUrl();
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(HttpServletRequest request,HttpServletResponse response,Model model){
		
		if(!validation(request)){
			return getLoginUrl();
		}
		Subject subject=SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			return "redirect:"+getSuccessUrl();
		}
		String username=request.getParameter(getUsernameParam());
		String password=request.getParameter(getPasswordParam());
		String  rememberMe=request.getParameter(getRememberMeParam());
		UsernamePasswordToken token=new UsernamePasswordToken(username.trim(), password);
		token.setRememberMe(rememberMe!=null&&"true".equalsIgnoreCase(rememberMe.trim()));
		try {
			subject.login(token);
			return "redirect:"+getSuccessUrl();
		}catch(UnknownAccountException e){
			LOG.info(">>>>用户名错误");
			request.setAttribute(errorMessageKey, getMessage("base.authen.login.unamepwd.err.match",null,"用户名或密码有误",request));
			return getLoginUrl();
		}catch(IncorrectCredentialsException e){
			LOG.info(">>>>认证错误");
			request.setAttribute(errorMessageKey, getMessage("base.authen.login.unamepwd.err.match",null,"用户名或密码有误",request));
			return getLoginUrl();
		}catch (AuthenticationException e) {
			LOG.info(">>>>未知错误");
			request.setAttribute(errorMessageKey, getMessage("base.authen.login.unamepwd.err.unknown",null,"未知错误",request));
			return getLoginUrl();
		}
		
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
			request.getSession().setAttribute(this.captchaKey, value);
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
	
	public String getCaptchaValue(HttpSession session){
		Object captcha=session.getAttribute(this.captchaKey);
		session.removeAttribute(this.captchaKey);
		if(captcha!=null&&captcha instanceof String){
			return (String) captcha;
		}else{
			return null;
		}
	}
	
	private String getMessage(String code, Object[] args, String defaultMessage,HttpServletRequest request){
		Locale locale=RequestContextUtils.getLocale(request);
		LOG.debug(">>>>"+locale);
		String message=messageSource.getMessage(code, args, defaultMessage, locale);
		LOG.debug(">>>>message:"+message);
		return message;
	}
	
	private boolean validation(HttpServletRequest request){
		String username=request.getParameter(getUsernameParam());
		String password=request.getParameter(getPasswordParam());
		String captcha=request.getParameter(getCaptchaParam());
		
		if(checkCaptchaEnable()&&(captcha==null||!captcha.equalsIgnoreCase(getCaptchaValue(request.getSession())))){
			request.setAttribute(errorMessageKey, getMessage("base.authen.login.captcha.err.match",null,"验证码不匹配",request));
			return false;
		}
		
		if(username==null||"".equals(username.trim())){
			request.setAttribute(errorMessageKey, getMessage("base.authen.login.username.err.null",null,"用户名不能为空",request));
			return false;
		}
		if(username.trim().length()<6||username.trim().length()>20){
			request.setAttribute(errorMessageKey, getMessage("base.authen.login.username.err.length",new Object[]{6,20},"用户名长度必须为{0}-{1}",request));
			return false;
		}
		
		if(password==null||"".equals(password.trim())){
			request.setAttribute(errorMessageKey, getMessage("base.authen.login.password.err.null",null,"密码不能为空",request));
			return false;
		}
		if(password.trim().length()<6||password.trim().length()>20){
			request.setAttribute(errorMessageKey, getMessage("base.authen.login.password.err.length",new Object[]{6,20},"密码长度必须为{0}-{1}",request));
			return false;
		}
		
		return true;
	}
	
	private boolean checkCaptchaEnable(){
		boolean result=true;
		//插入判断什么时候需要开启验证码，如密码错误尝试到一定次数，账号异常等
		return result;
	}
	
}
