
. ./common.env

if [ $# -eq 0 ] ; then
  echo "ERROR: Missing store ID"
  exit 1
  fi
id=$1

url=$STORES_URL/$id?request_id=foo

echo "URL=$url"

curl $OPTS $url | tee $BODY_FILE

echo ""
echo "URL=$url"
grep HTTP $HEADER_FILE
