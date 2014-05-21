package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.*;

/**
 * Created by pprados on 06/05/14.
 */
public class ByteArrayOutputStreamTest extends OutputStreamTest
{
	@Override
	protected OutputStream newOutputStream() throws IOException
	{
		return new ByteArrayOutputStream();
	}

	@Test
	public void derived()
	{
		class Derived extends ByteArrayOutputStream
		{
			Derived()
			{
				super(10);
			}
		};
		new Derived();
	}
}