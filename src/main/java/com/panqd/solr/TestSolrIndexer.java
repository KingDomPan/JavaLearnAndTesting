package com.panqd.solr;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;

public class TestSolrIndexer {
	public static void main(String[] args) throws Exception {
		String host = "http://192.168.111.134:4444/solr";
		SolrServer server = new HttpSolrServer(host);
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", "2");
		doc.addField("title", "信息!科技");
		doc.addField("content", "企业信息门户, 元数据, 数字沙盘, 知识管理");
		server.add(doc);
		server.commit();
	}
}
