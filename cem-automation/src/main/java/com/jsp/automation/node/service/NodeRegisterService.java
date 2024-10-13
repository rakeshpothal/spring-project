package com.jsp.automation.node.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class NodeRegisterService {
	Map<String, String> classMap = new HashMap<>();
	
	public  void addDatatoClassMap() {
		classMap.put("start", "/cem-automation/src/main/java/com/jsp/automation/service/node/impl/StartNodeServiceImpl.java");
		classMap.put("end", "/cem-automation/src/main/java/com/jsp/automation/service/node/impl/EndNodeServiceImpl.java");
	}

	public NodeExecutionService getNodeImlementation(String nodeType)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {

		String classFullyqualifiedName = classMap.get(nodeType);
		return (NodeExecutionService) Class.forName(classFullyqualifiedName).getDeclaredConstructor().newInstance();

	}
}
