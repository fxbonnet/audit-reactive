/*
 * Copyright 2014 OCTO Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.octo.reactive.audit;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

class AuditLogFormat extends Formatter
{

	private final Date dat = new Date();
	private final String format;

	AuditLogFormat(String format)
	{
		this.format = format;
	}

	@SuppressWarnings("ThrowableResultOfMethodCallIgnored")
	@Override
	public synchronized String format(LogRecord record)
	{
		dat.setTime(record.getMillis());
		String source;
		if (record.getSourceClassName() != null)
		{
			source = record.getSourceClassName();
			if (record.getSourceMethodName() != null)
			{
				source += " " + record.getSourceMethodName();
			}
		}
		else
		{
			source = record.getLoggerName();
		}
		String message = formatMessage(record);
		String throwable = "";
		if (record.getThrown() != null)
		{
			ReactiveAudit.config.incSuppress();
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			pw.println();
			record.getThrown().printStackTrace(pw);
			pw.close();
			throwable = sw.toString();
			ReactiveAudit.config.decSuppress();
		}
		return String.format(format,
							 dat,
							 source,
							 record.getLoggerName(),
							 LoadParams.convLevelString(record.getLevel()),
							 message,
							 throwable);
	}

	String getFormat()
	{
		return format;
	}
}

