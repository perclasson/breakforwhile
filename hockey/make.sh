rm -f team22
ln -s ../src/team22
javac -classpath .:hockey.jar ../src/team22/*.java && java -jar hockey.jar
