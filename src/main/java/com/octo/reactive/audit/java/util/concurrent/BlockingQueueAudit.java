package com.octo.reactive.audit.java.util.concurrent;

import com.octo.reactive.audit.AbstractCPUAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.LOW;
import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods: 4
@Aspect
public class BlockingQueueAudit extends AbstractCPUAudit
{
	@Before("call(* java.util.concurrent.BlockingQueue.offer(Object,long,java.util.concurrent.TimeUnit) )")
	public void offer(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.util.concurrent.BlockingQueue.poll(long,java.util.concurrent.TimeUnit) )")
	public void poll(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

	@Before("call(* java.util.concurrent.BlockingQueue.put(Object) )")
	public void put(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.util.concurrent.BlockingQueue.take())")
	public void take(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

}