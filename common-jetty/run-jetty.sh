
#
# Runs embedded jetty agains specified WAR.
# Example: run.jetty.sh slapi.war /slapi 8080
#

DIRNAME=`dirname $0`
CPATH="$CPATH:.:$DIRNAME/target/classes"

dist=common-jetty
LIBDIR=$DIRNAME/target/$dist-distribution/$dist/lib
if [ ! -d $LIBDIR ] ; then
  LIBDIR=$DIRNAME/lib
  fi

for file in `find $LIBDIR -name *.jar` ; do
  CPATH="$CPATH:$file" ; done

PGM=com.amm.common.jetty.JettyWebappLauncher
java -classpath $CPATH $PGM $* | tee log.txt

