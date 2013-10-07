
. ./common.env

if [ $# -lt 2 ] ; then
  echo "ERROR: Expecting store ID and JSON file"
  exit
  fi
id=$1
ifile=$2
url=$STORES_URL/$id

curl $OPTS -X PUT -T $ifile $url | tee $BODY_FILE

echo ""
echo "URL=$url"
echo "FILE=$ifile"
grep HTTP $HEADER_FILE | grep -v 100
