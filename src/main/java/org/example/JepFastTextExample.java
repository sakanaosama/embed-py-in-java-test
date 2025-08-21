package org.example;

import jep.SharedInterpreter;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class JepFastTextExample {

    public static void main(String[] args) {

        // Prepare TextData object
        TextData textData = new TextData();
        textData.setTextList(Arrays.asList(
                Arrays.asList("machine", "learning", "is", "fun"),
                Arrays.asList("fasttext", "creates", "word", "embeddings"),
                Arrays.asList("embeddings", "are", "useful", "for", "nlp")
        ));

        try (SharedInterpreter interp = new SharedInterpreter()) {

            // Pass Java object to Python
            interp.set("textData", textData);

            // Read Python file from resources using pure Java
            InputStream inputStream = JepFastTextExample.class
                    .getClassLoader()
                    .getResourceAsStream("fasttext_embed.py");
            if (inputStream == null) {
                throw new RuntimeException("Python script not found in resources!");
            }

            String pythonCode = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            // Execute Python script
            interp.exec(pythonCode);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Print embeddings returned from Python
        System.out.println("Embedding list from Python:");
        for (List<Double> vec : textData.getEmbeddingList()) {
            System.out.println(vec);
        }
    }
}
