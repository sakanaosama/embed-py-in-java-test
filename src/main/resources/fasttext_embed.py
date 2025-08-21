from gensim.models import FastText

# textData is a Java object passed from Java
corpus = textData.getTextList()

# Train FastText model
model = FastText(sentences=corpus, vector_size=50, window=3, min_count=1, sg=1)

# Compute embeddings for each sentence
embedding_list = []
for sentence in corpus:
    vecs = [model.wv[word].astype(float).tolist() for word in sentence]
    # Sum word vectors to get sentence embedding
    sentence_vec = [sum(x) for x in zip(*vecs)]
    embedding_list.append(sentence_vec)

# Set embeddings back to Java object
textData.setEmbeddingList(embedding_list)
