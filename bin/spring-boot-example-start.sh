#!/usr/bin/env bash

# resolve links - $0 may be a softlink
PRG="${0}"

BASEDIR=`dirname ${PRG}`
BASEDIR=`cd ${BASEDIR}/..;pwd`

echo "spring boot example start..."

CONF_DIR=${CONF_DIR:-$BASEDIR/conf}
FLYWAY_DIR=${FLYWAY_DIR:-$BASEDIR/flyway}

# setup config
POSTGRES_HOST="${POSTGRES_HOST:-postgres}"
POSTGRES_PORT="${POSTGRES_PORT:-5432}"
POSTGRES_DB="${POSTGRES_DB:-exampledb}"
POSTGRES_USER="${POSTGRES_USER:-postgres}"
POSTGRES_PASSWORD="${POSTGRES_PASSWORD:-postgres}"

# setup config
sed -i "s/{{ POSTGRES_HOST }}/${POSTGRES_HOST}/" $FLYWAY_DIR/conf/flyway.conf
sed -i "s/{{ POSTGRES_PORT }}/${POSTGRES_PORT}/" $FLYWAY_DIR/conf/flyway.conf
sed -i "s/{{ POSTGRES_DB }}/${POSTGRES_DB}/" $FLYWAY_DIR/conf/flyway.conf
sed -i "s/{{ POSTGRES_USER }}/${POSTGRES_USER}/" $FLYWAY_DIR/conf/flyway.conf
sed -i "s/{{ POSTGRES_PASSWORD }}/${POSTGRES_PASSWORD}/" $FLYWAY_DIR/conf/flyway.conf

# flyway migrate
$FLYWAY_DIR/flyway migrate

RETVAL=$?
[ $RETVAL -eq 0 ] && echo "flyway migrate successfully!!!"
[ $RETVAL -ne 0 ] && echo "flyway migrate error!!!" && [ "X$SKIP_FLYEAY_ERROR" != "Xtrue" ] && exit 1


CPPATH=$CONF_DIR
# Construct classpath using jars from libs/ directory.
for i in "${BASEDIR}/lib/"*.jar; do
  CPPATH="${CPPATH}:$i"
done

CLASSNAME=com.bamboo.spring.boot.example.Application

java $JAVA_OPTS -cp "${CPPATH}" $CLASSNAME