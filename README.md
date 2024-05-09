# Description
The Bounded Context Canvas Application project aims to automate the creation of bounded context canvases.

The application utilizes the documentation prepared by Domain-Driven Design Crew  available at: https://github.com/ddd-crew/bounded-context-canvas which fulfill the DDD principles.

# Key features
1. Bounded context canvas generated from JSON using a REST API.
2. Model of the bounded context canvas in version 5.
3. Swagger with descriptions and a built-in example.
4. Five different types of formats, including HTML, image, PDF, MARKDOWN, and Confluence HTML.
5. Six different templates available out of the box.

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
The swagger interface is available at: :8083/swagger-ui/index.html

# Docker & Podman
```bash
podman build -t bounded-context-canvas .
```
```bash
podman run -dp 8083:8083 bounded-context-canvas
```

# Extending
## Templates
To write a new template you have to create a new html file and put it in the directory: 
```
main/resource/canvas/templates/default/
```
And describe the newly created template in the canvas/templates/default/context.yaml file.

The name of a file without extension will be the name of your created template.
## Model and template
To extend the model you have to create your own model class extending the BoundedContext class.

To start using your classes as a model you have to create your own set of controllers.
To do it follow the next steps. 

1. Create controllers and add on each an annotation pointing to your brand:
```java
@ConditionalOnProperty(value = "application.brand", havingValue = "default")
```
2. Add your package for scanning, modifying the annotation @SpringBootApplication on the BoundedContextCanvasApplication class:
```java
@SpringBootApplication(scanBasePackages = {"net.adam85w.ddd.boundedcontextcanvas", "your package goes here"})
```
3. Enable your controllers and disable the original controllers, changing the configuration in an application.yaml file:
```yaml
application:
  brand: "your brand"
```
The correct way to create new templates using your own model is to create a new directory for them in:
```
main/resource/canvas/templates/
```
The directory must match with the name of your brand defined in the configuration file:
```yaml
application:
  brand: "your brand"
```
It is also mandatory to write the context file in the template directory, to see how it should be done please look at context.yaml in the default brand.
# License & Author
## License
The Bounded Context Canvas Application is licensed under GNU GENERAL PUBLIC LICENSE v3.
## Author
Adam Woźniak <adam85.w@gmail.com>
## Credits
Domain-Driven Design Crew 

