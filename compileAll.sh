
for i in `cat scala_files`
do
  echo "compiling $i ..."
  dotc -indent -rewrite -classpath "target/scala-0.21/classes:lib/flatfiledatabase_2.12-0.5.jar" $i
done


