SRC = .
BUILD = build
DOCS = docs

collect:
	find $(SRC) -name "*.java" > sources.txt

build:
	javac -d $(BUILD) @sources.txt

run:
	@java -cp $(BUILD) HWSystem.Main $(ARGS)

clean:
	rm -rf $(BUILD) sources.txt $(DOCS)/*
	rm -f logs/*

javadoc:
	javadoc -d $(DOCS) @sources.txt
