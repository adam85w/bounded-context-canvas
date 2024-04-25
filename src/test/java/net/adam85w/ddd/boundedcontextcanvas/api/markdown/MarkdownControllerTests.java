package net.adam85w.ddd.boundedcontextcanvas.api.markdown;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.adam85w.ddd.boundedcontextcanvas.api.ErrorResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;

import java.io.IOException;
import java.nio.charset.Charset;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MarkdownControllerTests {

    private final String ENDPOINT = "/api/bounded-context-canvas/markdown";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void correctRequestShouldReturnOk() throws IOException {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(ENDPOINT, createEntity("ok"), String.class);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void invalidJsonSyntaxShouldReturnError() throws IOException {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(ENDPOINT, createEntity("invalid_syntax"), String.class);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        ErrorResponse response = objectMapper.readValue(responseEntity.getBody(), ErrorResponse.class);
        Assertions.assertThat(response.getMessage()).isEqualTo("JSON parse error: Unexpected character ('\"' (code 34)): was expecting comma to separate Object entries");
    }

    @Test
    public void missingMandatoryElementShouldReturnError() throws IOException {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(ENDPOINT, createEntity("missing_mandatory_element"), String.class);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        ErrorResponse response = objectMapper.readValue(responseEntity.getBody(), ErrorResponse.class);
        Assertions.assertThat(response.getMessage()).isEqualTo("Value of the field: name is invalid against rule: must not be blank");
    }

    @Test
    public void unrecognizedTemplateNameShouldReturnError() throws IOException {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(ENDPOINT + "?templateName=invalid_name", createEntity("ok"), String.class);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        ErrorResponse response = objectMapper.readValue(responseEntity.getBody(), ErrorResponse.class);
        Assertions.assertThat(response.getMessage()).isEqualTo("Template name is incorrect or does not support the MARKDOWN type.");
    }

    @Test
    public void unsupportedTemplateNameShouldReturnError() throws IOException {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(ENDPOINT + "?templateName=keyboard", createEntity("ok"), String.class);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        ErrorResponse response = objectMapper.readValue(responseEntity.getBody(), ErrorResponse.class);
        Assertions.assertThat(response.getMessage()).isEqualTo("Template name is incorrect or does not support the MARKDOWN type.");
    }

    private HttpEntity<String> createEntity(String name) throws IOException {
        String request = resourceLoader.getResource(String.format("classpath:/requests/%s.json", name)).getContentAsString(Charset.defaultCharset());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<String>(request ,headers);
    }
}
