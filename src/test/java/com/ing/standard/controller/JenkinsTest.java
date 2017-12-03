package com.ing.standard.controller;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

public class JenkinsTest {
	@Test
	public void jenkinsTest() {
		assertEquals("1", 1);
	}
	
	@Test
	public void restAPITest() throws IOException {
		String param = "/srv/IBM/Interface/jenkins/jenkins_restapi_call.sh&343&34234";
		String[] command = param.split("&");
		StringBuffer output = new StringBuffer();
		Process process = null;
		BufferedReader bufferedReader = null;
		Runtime runtime = Runtime.getRuntime();
		String osName = System.getProperty("os.name");
		
		/*if(osName.indexOf("Windows") > -1) {
			command = "cmd /c" + command;  
		}*/
			process = runtime.exec(command);
			//shell 실행이 정상 동작
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String msg = null;
			while ((msg=bufferedReader.readLine()) != null) {
				output.append(msg + System.getProperty("line.separator"));
			}
			bufferedReader.close();
			//shell 실행시 에러가 발생
			bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while ((msg=bufferedReader.readLine()) != null) {
				output.append(msg + System.getProperty("line.separator"));
				
			}
			process.destroy();
			System.out.println(output.toString());
	}
}
