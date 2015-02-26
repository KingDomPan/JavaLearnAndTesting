package com.panqd.lucene;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;

public class TestQuery {
	public static void main(String[] args) throws Exception {
		TopDocs topDocs = null;
		String queryString = "中华";
		File indexDir = new File("c:\\indexes");
		Directory d = new SimpleFSDirectory(indexDir);
		Query query = null;
		Analyzer analyzer = new StandardAnalyzer();
		IndexReader reader = DirectoryReader.open(d);
		IndexSearcher searcher = new IndexSearcher(reader);
		try {
			QueryParser qp = new QueryParser("body", analyzer);
			query = qp.parse(queryString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (null != searcher) {
			topDocs = searcher.search(query, 100);
		}

		if (topDocs.totalHits > 0) {
			System.out.println("找到:" + topDocs.totalHits + "个结果");
			for(ScoreDoc sd : topDocs.scoreDocs){
				System.out.println(sd);
			}
			System.out.println("最大评分:" + topDocs.getMaxScore());
		}
	}
}
