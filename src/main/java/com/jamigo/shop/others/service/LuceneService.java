package com.jamigo.shop.others.service;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LuceneService {

    private Directory index;
    private Analyzer analyzer;

    public LuceneService() {
        this.index = new RAMDirectory();
        this.analyzer = new StandardAnalyzer();
    }

    public void indexDocument(String id, String content) throws IOException {
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(index, config);
        Document doc = new Document();
        doc.add(new StringField("id", id, Field.Store.YES));
        doc.add(new TextField("content", content, Field.Store.YES));
        writer.addDocument(doc);
        writer.close();
    }

    public TopDocs searchIndex(String queryString) throws IOException, ParseException {
        Query query = new QueryParser("content", analyzer).parse(queryString);
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        return searcher.search(query, 10);
    }

    public Document getDocument(int docID) throws IOException {
        IndexReader reader = DirectoryReader.open(index);
        return reader.document(docID);
    }

    public void clearIndex() throws IOException {
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(index, config);
        writer.deleteAll();
        writer.close();
    }
}
