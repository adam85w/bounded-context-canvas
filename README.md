# Description
The Bounded Context Canvas Application project aims to automate the creation of bounded context canvases.

The application utilizes the documentation prepared by Domain-Driven Design Crew  available at: https://github.com/ddd-crew/bounded-context-canvas which fulfill the DDD principles.
# Usage
To build the application use the maven command
```bash
mvn install
```
To run the application the Java 17 is needed, to check your version of Java use the command:
```bash
java --version
```
To execute the application:
```bash
java -jar ./bounded-context-canvas-x.x.jar
```
The swagger interface is available at: /swagger-ui/index.html


# Extending
## Templates
To write a new template you have to create a new html file and put in the directory: 
```
main/resource/templates/original/
```
The name of a file without extension will be the name of your created template.
## Model and templates
To extend the model you have to create your own model class extending the BoundedContext class.

To start using your classes as a model you have to create your own set of controllers.
To do it follow the next steps. 

1. Create controllers and add on each an annotation for enabling only them:
```java
@ConditionalOnProperty(value = "application.enable-api", havingValue = "{your_name}")
```
2. Add your package for scanning, modifying the annotation @SpringBootApplication on the BoundedContextCanvasApplication class:
```java
@SpringBootApplication(scanBasePackages = {"net.adam85w.ddd.boundedcontextcanvas", "your package"})
```
3. Enable your controllers and disable the original controllers, changing the configuration in an application.yaml file:
```yaml
application:
  enable-api: original
```

The best way to create new templates using your own model is to create a new directory for them in:
```
main/resource/templates/
```
and add the path to template names in created controllers.

# License & Author
## License
The Bounded Context Canvas Application is licensed under GNU GENERAL PUBLIC LICENSE v3.
## Author
Adam Woźniak <adam85.w@gmail.com>
## Credits
Domain-Driven Design Crew 

