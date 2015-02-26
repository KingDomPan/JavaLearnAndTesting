package com.panqd.vm.solr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import net.sf.json.JSONObject;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;

public class StaffSolr4Node {
    public static void main(String[] args) {
        StringBuffer xmlReturn = new StringBuffer("");
        try {
            int status = -1;
            int pageSize = 10;
            int currentPage = 1;
            int startIndex = pageSize * (currentPage - 1);
            String queryTemplate = "(name:? OR hw_info:? OR dhcp_mac:? OR facts:? OR hostname:? OR boot_count:? OR ipmi_hostname:? OR ipmi_username:? OR metadata:? OR installed:?)";
            String SolrServerUrl = "http://192.168.111.134:20010/node";
            String queryWord = "11";
            StringBuffer querySbf = new StringBuffer();
            for (String word : tokenizeToStringArray(queryWord, " ", true, true)) {
                querySbf.append(queryTemplate.replaceAll("[?]", word)).append(
                        " AND ");
            }
            querySbf.append("(*:*)");
            HttpSolrServer server = new HttpSolrServer(SolrServerUrl);
            server.setConnectionTimeout(5000); // 连接超时时间
            server.setSoTimeout(5000); // 读取数据超时时间
            server.setAllowCompression(false); // 是否压缩数据
            SolrQuery solrQuery = new SolrQuery().setQuery(querySbf.toString());
            // 默认按文档得分降序排列
            solrQuery.setRows(10); // 允许返回的最大结果集数
            solrQuery.setStart(startIndex); // 查询结果集的起始位置
            QueryResponse response = server.query(solrQuery);
            System.out.println("solr request url:" + SolrServerUrl);
            System.out.println("solr request search:" + querySbf.toString()
                    + ",start:" + startIndex);
            System.out.println("solr response status:"
                    + (status = response.getStatus()));
            System.out.println("solr response nums:"
                    + (response.getResults().getNumFound()));
            if (status == 0) {
                Iterator<SolrDocument> iter = response.getResults().iterator();

                while (iter.hasNext()) {
                    SolrDocument resultDoc = iter.next();
                    System.out.println("_________");
                    System.out.println(resultDoc);
                    System.out.println("_________");
                    JSONObject json = new JSONObject();
                    json.put("id", (String) resultDoc.getFieldValue("id"));
                    json.put("name", (String) resultDoc.getFieldValue("name"));
                    json.put("hw_info", (String) resultDoc.getFieldValue("hw_info"));
                    json.put("dhcp_mac", (String) resultDoc.getFieldValue("dhcp_mac"));
                    json.put("facts", (String) resultDoc.getFieldValue("facts"));
                    json.put("hostname", (String) resultDoc.getFieldValue("hostname"));
                    json.put("boot_count", (String) resultDoc.getFieldValue("boot_count"));
                    json.put("ipmi_hostname", (String) resultDoc.getFieldValue("ipmi_hostname"));
                    json.put("ipmi_username", (String) resultDoc.getFieldValue("ipmi_username"));
                    json.put("metadata", (String) resultDoc.getFieldValue("metadata"));
                    json.put("installed", (String) resultDoc.getFieldValue("installed"));
                    json.put("installed_at", (String) resultDoc.getFieldValue("installed_at"));
                    xmlReturn.append(json.toString()).append("\n");
                }
                System.out.println(xmlReturn.toString());
            } else {
                xmlReturn.append("<error_code>1</error_code><msg>搜索出错,请检查Solr是否启动!</msg></root>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            xmlReturn.append("<error_code>1</error_code><msg>" + e.getMessage() + "</msg></root>");
        }
    }

    public static String[] tokenizeToStringArray(String str, String delimiters,
            boolean trimTokens, boolean ignoreEmptyTokens) {

        StringTokenizer st = new StringTokenizer(str, delimiters);
        List<String> tokens = new ArrayList<String>();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (trimTokens) {
                token = token.trim();
            }
            if (!ignoreEmptyTokens || token.length() > 0) {
                tokens.add(token);
            }
        }
        return (String[]) tokens.toArray(new String[tokens.size()]);
    }
}
