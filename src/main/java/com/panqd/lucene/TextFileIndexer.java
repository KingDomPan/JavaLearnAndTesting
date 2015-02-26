package com.panqd.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;

public class TextFileIndexer {

	public static void main(String[] args) throws Exception {

		long start = new Date().getTime();
		File fileDir = new File("c:\\data");
		File indexDir = new File("c:\\indexes");
		Analyzer luceneAnalyzer = new StandardAnalyzer();
		Directory d = new SimpleFSDirectory(indexDir);
		IndexWriterConfig iwc = new IndexWriterConfig(null, luceneAnalyzer);
		IndexWriter indexWriter = new IndexWriter(d, iwc);
		File[] datas = fileDir.listFiles();
		for (File data : datas) {
			if (data.isFile() && data.getName().endsWith("txt")) {
				System.out.println(" File  " + data.getCanonicalPath()
						+ " 正在被索引. ");
				String temp = FileReaderAll(data, "UTF-8");
				System.out.println(temp);
				Document doc = new Document();
				Field fieldPath = new TextField("path", data.getPath(),
						Field.Store.YES);
				Field fieldBody = new TextField("body", temp, Field.Store.YES);
				doc.add(fieldPath);
				doc.add(fieldBody);
				indexWriter.addDocument(doc);
			}
		}
		indexWriter.commit();
		indexWriter.close();

		long end = new Date().getTime();
		System.out.println("建立索引一共花了" + (end - start) + "mm");
	}

	public static String FileReaderAll(File file, String charset)
			throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file), charset));
		String line = new String();
		String temp = new String();
		while ((line = br.readLine()) != null) {
			temp += line;
		}
		br.close();
		return temp;
	}
}
