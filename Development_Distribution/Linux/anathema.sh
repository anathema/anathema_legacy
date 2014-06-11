#!/bin/sh

# get the absolute path of the current script
SOURCE=$(readlink -f "$0")
# resolve $SOURCE until the file is no longer a symlink
while [ -h "$SOURCE" ]; do
  DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"
  SOURCE="$(readlink "$SOURCE")"
  # if $SOURCE was a relative symlink, we need to resolve it relative 
  #   to the path where the symlink file was located
  [[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE" 
done
ANATHEMA_DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"
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

if [ $JAVAMAJOR -lt 1 -o \( $JAVAMAJOR -eq 1 -a $JAVAMINOR -lt 7 \) ]; then
    echo "==> You must have a java version 1.7 or greater to run Anathema."
    exit 1
fi

echo "Running Anathema from $ANATHEMA_DIR with repository $REPO_DIR"

cd $ANATHEMA_DIR
$JAVA_BIN -Drepository="${REPO_DIR}" -jar anathema.jar
