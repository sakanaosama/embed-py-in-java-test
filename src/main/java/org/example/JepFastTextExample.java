package org.example;

import jep.SharedInterpreter;
import java.util.Arrays;
import java.util.List;

public class JepFastTextExample {
    public static void main(String[] args) {

        // Create TextData object and set textList
        TextData textData = new TextData();
        textData.setTextList(Arrays.asList(
                Arrays.asList("machine", "learning", "is", "fun"),
                Arrays.asList("fasttext", "creates", "word", "embeddings"),
                Arrays.asList("embeddings", "are", "useful", "for", "nlp")
        ));

        try (SharedInterpreter interp = new SharedInterpreter()) {

            // Pass Java object to Python
            interp.set("textData", textData);

            // Import gensim
            interp.exec("from gensim.models import FastText");

            // Get the textList from Java object
            interp.exec("corpus = textData.getTextList()");

            // Train FastText model
            interp.exec("model = FastText(sentences=corpus, vector_size=50, window=3, min_count=1, sg=1)");

            // Compute embeddings for each sentence (sum of word vectors as an example)
            interp.exec(
                    "embedding_list = []\n" +
                            "for sentence in corpus:\n" +
                            "    vecs = [model.wv[word].astype(float).tolist() for word in sentence]\n" +
                            "    sentence_vec = [sum(x) for x in zip(*vecs)]  # sum word vectors to get sentence embedding\n" +
                            "    embedding_list.append(sentence_vec)\n" +
                            "\n" +
                            "textData.setEmbeddingList(embedding_list)"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Print embeddings in Java
        System.out.println("Embedding list from Python:");
        for (List<Double> vec : textData.getEmbeddingList()) {
            System.out.println(vec);
        }
    }
}
