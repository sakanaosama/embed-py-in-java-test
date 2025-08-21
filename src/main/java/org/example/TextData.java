package org.example;

import java.util.List;

public class TextData {
    private List<List<String>> textList;        // list of tokenized sentences
    private List<List<Double>> embeddingList;   // corresponding embeddings

    public List<List<String>> getTextList() {
        return textList;
    }

    public void setTextList(List<List<String>> textList) {
        this.textList = textList;
    }

    public List<List<Double>> getEmbeddingList() {
        return embeddingList;
    }

    public void setEmbeddingList(List<List<Double>> embeddingList) {
        this.embeddingList = embeddingList;
    }

    @Override
    public String toString() {
        return "TextData{" +
                "textList=" + textList +
                ", embeddingList=" + embeddingList +
                '}';
    }
}
