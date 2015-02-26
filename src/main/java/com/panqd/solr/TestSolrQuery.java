package com.panqd.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class TestSolrQuery {
	public static void main(String[] args) throws Exception {
		String host = "http://192.168.111.134:4444/solr";
		SolrServer server = new HttpSolrServer(host);
		SolrQuery query = new SolrQuery("数据");
		query.setFacetLimit(1);
		query.setRows(10);
		query.setStart(0);
		query.setHighlight(true);
		query.setParam("hl.fl", "title, content");
		query.setHighlightSimplePre("<font color='red'>");
		query.setHighlightSimplePost("</font>");
		query.setSort("name", ORDER.desc);
		try {
			QueryResponse qr = server.query(query);
			SolrDocumentList docList = qr.getResults();
			System.out.println("文档个数：" + docList.getNumFound());
            System.out.println("查询时间：" + qr.getQTime());
            for(SolrDocument doc : docList) {
            	System.out.println("doc: " + doc);
            	System.out.println("title: " + doc.getFieldValue("title"));
            	System.out.println("content: " + doc.getFieldValue("content"));
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
