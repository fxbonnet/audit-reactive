package com.octo.reactive.audit.java.util.concurrent.locks;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.CPUAuditReactiveException;
import org.junit.Test;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class AbstractQueuedSynchronizerTest
{
	final AbstractQueuedSynchronizer c = new AbstractQueuedSynchronizer()
	{

	};

	@Test(expected = CPUAuditReactiveException.class)
	public void acquire() throws InterruptedException
	{
		AuditReactive.strict.commit();
		c.acquire(1);
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void acquireInterruptibly() throws InterruptedException
	{
		AuditReactive.strict.commit();
		c.acquireInterruptibly(1);
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void acquireShared() throws InterruptedException
	{
		AuditReactive.strict.commit();
		c.acquireShared(1);
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void acquireSharedInterruptibly() throws InterruptedException
	{
		AuditReactive.strict.commit();
		c.acquireSharedInterruptibly(1);
	}
}