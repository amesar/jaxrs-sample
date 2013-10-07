
. ./common.env

url=$BASE_URL/v1/admin/stores

echo "URL=$url"

curl $OPTS $url | tee $BODY_FILE

echo ""
echo "URL=$url"
grep HTTP $HEADER_FILE

