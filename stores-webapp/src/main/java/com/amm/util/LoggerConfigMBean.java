package com.amm.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(
   objectName = "com.amm:name=LoggerConfig",
   description = "Change log4j settings at runtime")

public class LoggerConfigMBean {
	private static final Logger logger = Logger.getLogger(LoggerConfigMBean.class);
   
	@ManagedOperation(description = "Get logger level")
	@ManagedOperationParameters({ @ManagedOperationParameter(name = "loggerName", description="Logger Name") })

	public String getLoggerLevel(String loggerName) {
		Logger loggerTarget = Logger.getLogger(loggerName);
		Level loggerLevel = loggerTarget.getLevel();
		return loggerLevel==null ? "logger " + loggerName + " has not level" : loggerLevel.toString() ;
	}

	@ManagedOperation(description = "Change logger Level")
	@ManagedOperationParameters({
		@ManagedOperationParameter(name = "loggerName", description="Logger Name"),
		@ManagedOperationParameter(name = "loggerLevel", description="Logger Level") })

	public void setLoggerLevel(String loggerName, String loggerLevel) {
		Logger loggerTarget = Logger.getLogger(loggerName);
		loggerTarget.setLevel(Level.toLevel(loggerLevel, Level.INFO));
		logger.info("Changed logger " +loggerName+" to level " + loggerTarget.getLevel());

	}

}
