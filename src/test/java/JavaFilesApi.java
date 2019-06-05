import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jdk.nashorn.internal.ir.annotations.Ignore;
import model.Employee;
import model.Menuitem;
import model.Popup;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JavaFilesApi {

    @Test
    public void testCreateFile() throws IOException {
        String HOME = System.getProperty("user.home");
        String fileName = "myfile_" + UUID.randomUUID().toString() + ".txt";
        Path p = Paths.get(HOME + "/" + fileName);
        assertFalse(Files.exists(p));

        Files.createFile(p);
        assertTrue(Files.exists(p));
    }

    @Test
    public void testReadFile() throws IOException, URISyntaxException {
        URL u = getClass().getClassLoader()
                .getResource("Employee.json");
        Path path = Paths.get(Optional.ofNullable(u).get().toURI());

        Stream<String> lines = Files.lines(path);
        String data = lines.collect(Collectors.joining("\n"));
        lines.close();
        System.out.println(data);
    }


    @Test
    public void testReadJsonToJava() throws URISyntaxException, IOException {
        URL u = getClass().getClassLoader()
                .getResource("Employee.json");
        Path path = Paths.get(Optional.ofNullable(u).get().toURI());
        ObjectMapper mapper = new ObjectMapper();
        String json="{\n" +
                "  \"id\": \"123\",\n" +
                "  \"name\": \"test\",\n" +
                "  \"popup\": {\n" +
                "    \"menuitem\": [\n" +
                "      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\n" +
                "      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\n" +
                "      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\n" +
                "    ]\n" +
                "  }\n" +
                "}";
        Employee e = mapper.readValue(json, Employee.class);
        System.out.println(e);

        BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toString()));

        Gson gson=new Gson();
        System.out.println(gson.fromJson(bufferedReader, Employee.class));
    }
}
