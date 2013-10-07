
. ./common.env

if [ $# -eq 0 ] ; then
  echo "ERROR: Missing store ID"
  exit
  fi
id=$1

url=$STORES_URL/$id

curl $OPTS -X DELETE  $url | tee $BODY_FILE

echo ""
echo "URL=$url"
grep HTTP $HEADER_FILE

