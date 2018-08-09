package com.test.ldap;

import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class ADTest {

	public static void main(String[] args) throws NamingException {
		String ntUserId = "자신이 사용 할 ADMIN ID";// (예: cn=Administrator,cn=admin,dc=admin,dc=com)
		String ntPasswd = "자신이 사용 할 ADMIN PWD";
		String url = "LDAP 서버에서 사용 하고 있는 IP";// (예: LDAP://127.0.0.0)

		try {
			String usrId = "Admin 계정에서 등록한 아이디";// ------------------------------------(예: test001)
			String usrPw = "Admin 계정에서 등록한 아이디의 패스워드";// ----------------------(예: test001!)
			String baseRdn = "ou=admin,ou=administrator,dc=admin,dc=com";// ----------------예시입니다.
			Hashtable<String, String> env = new Hashtable<String, String>();

			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, url);
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			env.put(Context.SECURITY_PRINCIPAL, ntUserId);
			env.put(Context.SECURITY_CREDENTIALS, ntPasswd);

			LdapContext ctx = new InitialLdapContext(env, null);

			System.out.println("Active Directory Connection: CONNECTED");

			// Hashtable 부터 LdapContext까지 LDAP 접속의 대한 인증을 합니다. ntUserId, ntPasswd, url 세가지로
			// 연결 확인을 합니다.

			// 정상적인 연결이 되면 "CONNECTED"가 출력됩니다.

			SearchControls ctls = new SearchControls();
			ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			ctls.setReturningAttributes(new String[] { "cn" });

			// 인증이 확인 됬다면 usrId, usrPw, baseRdn(유저가 등록된 위치)으로 Admin에서 등록한 유저를 찾아봅시다!

			String searchFilter = String.format("(cn=%s)", usrId);
			NamingEnumeration results = ctx.search(baseRdn, searchFilter, ctls);

			Hashtable<String, String> usrEnv = new Hashtable<String, String>();
			usrEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			usrEnv.put(Context.PROVIDER_URL, url);
			usrEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
			usrEnv.put(Context.SECURITY_PRINCIPAL, String.format("%s=%s,%s", "cn", usrId, baseRdn));
			usrEnv.put(Context.SECURITY_CREDENTIALS, usrPw);
			new InitialLdapContext(usrEnv, null);

			// 이 부분도 마찬가지로 ID, PW, 유저가 등록된 위치로 유저를 찾습니다.

			// 아래는 Active Directory에서 발생한 에러처리 입니다.

		} catch (AuthenticationException e) {

			String msg = e.getMessage();

			if (msg.indexOf("data 525") > 0) {

				System.out.println("사용자를 찾을 수 없음.");

			} else if (msg.indexOf("data 773") > 0) {

				System.out.println("사용자는 암호를 재설정해야합니다.");

			} else if (msg.indexOf("data 52e") > 0) {

				System.out.println("ID와 비밀번호가 일치하지 않습니다.확인 후 다시 시도해 주십시오.");

			} else if (msg.indexOf("data 533") > 0) {

				System.out.println("입력한 ID는 비활성화 상태 입니다.");

			} else if (msg.indexOf("data 532") > 0) {

				System.out.println("암호가 만료되었습니다.");

			} else if (msg.indexOf("data 701") > 0) {

				System.out.println("AD에서 계정이 만료됨");

			} else {
				System.out.println("정상!");

			}

		}

		// 이 부분은 Active Directory와 JAVA가 연결 되지 않을 때의 예외처리입니다. 연결이 안되면 FAILED를 출력합니다.

	}

	{

		System.out.println("Active Directory Connection: FAILED");

	}

}
