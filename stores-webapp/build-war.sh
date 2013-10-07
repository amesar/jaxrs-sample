
#
# Builds WAR with specified provider
#

#serviceProvider=mongodb
serviceProvider=mock-provider

if [ $# -gt 0 ] ; then
  serviceProvider=$1
  fi

mvn clean install -DserviceProvider=$serviceProvider

echo "serviceProvider=$serviceProvider"
