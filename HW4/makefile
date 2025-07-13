SRC = src
BUILD = build
DOCS = docs

collect:
	find $(SRC) -name "*.java" > sources.txt

build:
	javac -d $(BUILD) @sources.txt

run:
	@java -cp build Main.Main $(ARGS)

clean:
	rm -rf build
	rm -f sources.txt output.txt error.txt
	rm -rf docs/*

javadoc:
		javadoc -d $(DOCS) @sources.txt
