package org.demo.auto.common.service.impl;

import org.demo.auto.common.service.IRemoteService;

public class RemoteService implements IRemoteService{

	@Override
	public String sayHelloTo(String someone) {
		return "hello " + someone;
	}

}
