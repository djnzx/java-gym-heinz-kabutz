echo Building for day 30
git pull
rm -f out/production/jgym/io/jgym/warmups/day30/*.class
cd src/main/java
javac -d ../../../out/production/jgym io/jgym/warmups/day30/*.java
cd ../../../

echo Executing io/jgym/warmups/day30/Greeter
java -showversion $2 -cp out/production/jgym io/jgym/warmups/day30/Greeter
