package com.amm.common.jetty;

import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.server.Server;

/**
 * Launch an embedded Jetty against specified WAR.
 * <ul>
 * <li>Usage: java com..JettyWebappLauncher WAR /CONTEXT_PATH PORT
 * <li>Example: java com..JettyWebappLauncher myapp.war /myapp 8080");
 * </ul>
 */
public class JettyWebappLauncher {
	public static void main(String[] args) throws Exception {
		JettyWebappLauncher pgm = new JettyWebappLauncher();
		pgm.process(args);
	}

	public void process(String[] args) throws Exception {

		if (args.length < 3) {
			info("ERROR: Expecting WAR CONTEXT PORT");
			info("example: myapp.war /myapp 8080");
			return;
		}

		String warPath = args[0];
		String contextPath = args[1];
		int port = Integer.parseInt(args[2]);

		info("warPath="+warPath); 
		info("contextPath="+contextPath);
		info("port="+port);

		Server server = new Server(port);
 
		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath(contextPath);
		webapp.setWar(warPath);
		server.setHandler(webapp);
 
		server.start();
		server.join();
	}

	private void info(Object o) { System.out.println(o); }
}
