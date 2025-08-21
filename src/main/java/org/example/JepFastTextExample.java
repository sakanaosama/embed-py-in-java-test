package org.example;

import jep.SharedInterpreter;

public class JepFastTextExample {
    public static void main(String[] args) {
        try (SharedInterpreter interp = new SharedInterpreter()) {
            // Import gensim
            interp.exec("from gensim.models import FastText");

            // Sample corpus
            interp.exec("corpus = [\n" +
                    "    ['machine', 'learning', 'is', 'fun'],\n" +
                    "    ['fasttext', 'creates', 'word', 'embeddings'],\n" +
                    "    ['embeddings', 'are', 'useful', 'for', 'nlp']\n" +
                    "]");

            // Train FastText
            interp.exec("model = FastText(sentences=corpus, vector_size=50, window=3, min_count=1, sg=1)");

            // Convert vector to list of floats
            interp.exec("vector = model.wv['embeddings'][:5].astype(float).tolist()");

            // Get vector in Java
            double[] embedding = interp.getValue("vector", double[].class);

            System.out.print("Vector for 'embeddings': ");
            for (double v : embedding) {
                System.out.printf("%.4f ", v);
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
