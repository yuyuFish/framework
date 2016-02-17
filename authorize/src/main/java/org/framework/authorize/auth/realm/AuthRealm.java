package org.framework.authorize.auth.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class AuthRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		if(token !=null&& token instanceof UsernamePasswordToken){
			UsernamePasswordToken upToken=(UsernamePasswordToken) token;
			String username=upToken.getUsername();
			if(username==null||!"supperadmin".equals(username)){
				throw new UnknownAccountException();
			}
			SimpleAuthenticationInfo authen=new SimpleAuthenticationInfo("supperadmin", 
					"5081570e9511922c3a5f3621f0c6c6d0", ByteSource.Util.bytes("authorize"), getName());
			return authen;
		}
		return null;
	}

}
