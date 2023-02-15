source_dir := src/main/java/org/example
exec_dir := target/classes

main.build:
	@javac -d target/classes $(source_dir)/Main.java -cp $(exec_dir)

main.run: main.build
	@java -XX:+ShowCodeDetailsInExceptionMessages -cp $(exec_dir) org.example.Main
