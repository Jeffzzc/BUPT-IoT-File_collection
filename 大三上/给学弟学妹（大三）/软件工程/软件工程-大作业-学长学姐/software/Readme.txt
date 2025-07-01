At the begining, you need to decide what operating system you are using.
And you need an JDK(It is best to use JDK11).




If you are Windows operating system, here is your guide:

First, use the console to go to the folder "projectRamen" (use 'cd')

Second, run this command to compile the program:
javac -classpath .;./lib/jfreechart-1.0.19.jar;./lib/jcommon-1.0.23.jar;./lib/commons-csv-1.8.jar;./lib/fastjson-1.2.68.jar;  ./src/datautil/*.java ./src/domain/*.java ./src/service/*.java ./src/pages/*.java ./src/worker/*.java ./src/management/domain/*.java ./src/management/page/*.java ./src/management/service/*.java ./src/management/util/*.java ./src/management/report/*.java ./src/management/worker/*.java -encoding UTF-8 -d .

Then, run this command to start the main program:
java -cp .;./lib/jfreechart-1.0.19.jar;./lib/jcommon-1.0.23.jar;./lib/commons-csv-1.8.jar;./lib/fastjson-1.2.68.jar;./src; worker/MainWorker

If you want to start the management system, you should run this:
java -cp .;./lib/jfreechart-1.0.19.jar;./lib/jcommon-1.0.23.jar;./lib/commons-csv-1.8.jar;./lib/fastjson-1.2.68.jar;./src; management/worker/MainWorker

The username of admin is "admin"
password is 123456

If you want to start the report generator, you should run this:
java -cp .;./lib/jfreechart-1.0.19.jar;./lib/jcommon-1.0.23.jar;./lib/commons-csv-1.8.jar;./lib/fastjson-1.2.68.jar;./src; management/report/Generator







If you are OS X (mac OS) operating system, here is your guide:

First, use the console to go to the folder "projectRamen" (use 'cd')

Second, run this command compile:
javac -classpath ./lib/commons-csv-1.8.jar:./lib/fastjson-1.2.68.jar:./lib/jfreechart-1.0.19.jar:./lib/jcommon-1.0.23.jar ./src/datautil/*.java ./src/domain/*.java ./src/service/*.java ./src/pages/*.java ./src/worker/*.java ./src/management/domain/*.java ./src/management/page/*.java ./src/management/service/*.java ./src/management/util/*.java ./src/management/report/*.java ./src/management/worker/*.java -encoding UTF-8 -d .

Then, run this command to start the main program:
java -classpath .:./lib/commons-csv-1.8.jar:./lib/fastjson-1.2.68.jar:./lib/jfreechart-1.0.19.jar:./lib/jcommon-1.0.23.jar:./src worker/MainWorker

If you want to start the management system, you should run this:
java -classpath .:./lib/commons-csv-1.8.jar:./lib/fastjson-1.2.68.jar:./lib/jfreechart-1.0.19.jar:./lib/jcommon-1.0.23.jar:./src management/worker/MainWorker

The username of admin is "admin"
password is 123456

If you want to start the report generator, you should run this:
java -classpath .:./lib/commons-csv-1.8.jar:./lib/fastjson-1.2.68.jar:./lib/jfreechart-1.0.19.jar:./lib/jcommon-1.0.23.jar:./src management/report/Generator












If you are Linux operating system, here is your guide:

First, use the console to go to the folder "projectRamen" (use 'cd')

Second, you need to set the environment variable:

export PRAMEN=$PRAMEN:.:./lib/commons-csv-1.8.jar:./lib/fastjson-1.2.68.jar:./lib/jfreechart-1.0.19.jar:./lib/jcommon-1.0.23.jar

Third, run this command to compile:
javac -classpath $PRAMEN  ./src/datautil/*.java ./src/domain/*.java ./src/service/*.java ./src/pages/*.java ./src/worker/*.java ./src/management/domain/*.java ./src/management/page/*.java ./src/management/service/*.java ./src/management/util/*.java ./src/management/report/*.java ./src/management/worker/*.java -encoding UTF-8 -d .

Then, run this command to start the main program:
java -cp $PRAMEN worker/MainWorker

If you want to start the management system, you should run this(sometimes there will be an error):
java -cp $PRAMEN management/worker/MainWorker

The username of admin is "admin"
password is 123456

If you want to start the report generator, you should run this:
java -cp $PRAMEN management/report/Generator






The ticket files and order files will be generated in the folder "projectRamen" 
