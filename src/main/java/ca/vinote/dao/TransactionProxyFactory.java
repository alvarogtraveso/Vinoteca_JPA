package ca.vinote.dao;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class TransactionProxyFactory implements ApplicationContextAware {
	private ApplicationContext context;
	
	public TransactionProxy createTransactionProxy() {
		return context.getBean("transactionProxy", TransactionProxy.class);
	}

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
		
	}
	
	
}
