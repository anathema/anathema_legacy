#!/bin/sh

ANATHEMA_DIR=/usr/share/anathema
REPO_DIR=${HOME}/.anathema/repository
JAVA_BIN=

# Check for java in $JAVA_HOME
if [ -x ${JAVA_HOME}/jre/bin/java ]; then
    JAVA_BIN=${JAVA_HOME}/jre/bin/java
else
    # Try to locate java elsewhere.
    JAVA_BIN=`which java`
    if [ $? -eq 1 ]; then
        echo "Java could not be found." && exit 1;
    fi
fi

JAVAVER=`$JAVA_BIN -version 2>&1 | grep version | awk '{print $3}' | awk -F '"' '{print $2}'`
JAVAMAJOR=`echo $JAVAVER | awk -F '.' '{print $1}'`
JAVAMINOR=`echo $JAVAVER | awk -F '.' '{print $2}'`

echo "Java version $JAVAVER detected in ${JAVA_BIN}..."

if [ $JAVAMAJOR -lt 1 -o \( $JAVAMAJOR -eq 1 -a $JAVAMINOR -lt 5 \) ]; then
    echo "==> You must have a java version 1.5 or greater to run Anathema."
    exit 1
fi

echo "Running Anathema from $ANATHEMA_DIR with repository $REPO_DIR"
exit 1

cd $ANATHEMA_DIR
$JAVA_BIN -Drepository="${REPO_DIR}" -jar anathema.jar
