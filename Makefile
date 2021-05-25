all:
	$(info Compiling code...)
	@javac ./src/*.java

clean:
	rm ./src/*.class