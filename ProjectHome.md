A simple tool to generate JUnit 3.x or 4.x test cases automatically. This tool will only work with Java 5 and 6 source code.

JUGen has two modes: GUI mode and CLI mode.

To use the GUI (on Windows, double clicking on the JAR may work):
```
Usage: java -jar jugen.jar
```
To use the CLI:
```
Usage: java -jar jugen.jar <project_dir> <destination_dir> <template_dir> <junit_version> 
[--overwrite=[yes|no]] [--exclude=[comma_separated_list]]
where:
  * project_dir: the directory where the Java source files are located
  * destination_dir: the directory where the JUnit files will be created
  * template_dir: the directory where the JUGen templates are located
  * junit_version: 3 or 4
  * overwrite: when this flag is set to yes, JUGen will overwrite the JUnit files in the destination_dir even they already exists
  * exclude: the list of Java files to be excluded from processing
```

In Eclipse, we can run JUGen easily by creating an External Tools Configuration. For example:
```
Location: /usr/bin/java
Working Directory: <the_workspace>
Arguments:
-jar 
/home/fredy/jugen/jugen.jar
/home/fredy/workspace/test-app/src
/home/fredy/workspace/test-app/test 
/home/fredy/jugen/templates 
4
overwrite
```