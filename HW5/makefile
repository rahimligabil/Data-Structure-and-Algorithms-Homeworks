# Define the source and build directories
SRC = .
BUILD = build

# Collect all Java source files and list them in sources.txt
collect:
	find $(SRC) -name "*.java" > sources.txt

# Compile the Java source files into the build directory
build: collect
	javac -d $(BUILD) @sources.txt

# Generate Javadoc for all Java source files and place it in the docs folder inside the project directory
javadoc: collect
	javadoc -d ./docs @sources.txt

# Run the program by using the compiled classes in the build directory
run:
	java -cp $(BUILD) Main.Main $(ARGS)

# Clean up the build directory, remove .class files, sources.txt, and docs folder
clean:
	rm -rf $(BUILD)/*.class sources.txt docs
