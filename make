rm -rf output
javac -d output *.java && javac Synchro/*.java -d output/Synchro
cd output && java Main PhiloSemState 10  && cd ..
