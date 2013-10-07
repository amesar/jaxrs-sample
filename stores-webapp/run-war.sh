
# Run the WAR in standalone embedded mode

name=stores 
war=target/$name.war
context=/$name
port=8080

../common-jetty/run-jetty.sh $war $context $port

