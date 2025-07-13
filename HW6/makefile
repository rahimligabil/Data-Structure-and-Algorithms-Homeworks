SRC = .
BUILD = build

collect:
	find $(SRC) -name "*.java" > sources.txt

build: collect
	javac -d $(BUILD) @sources.txt

javadoc: collect
	javadoc -d ./docs @sources.txt

run:
	java -cp $(BUILD) SpellChecker.SpellChecker  # veya Main.Main, senin Main.java'daki package yapısına göre

clean:
	rm -rf $(BUILD) sources.txt docs
