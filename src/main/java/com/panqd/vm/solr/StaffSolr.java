package com.panqd.vm.solr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;

public class StaffSolr {
    public static void main(String[] args) {
        /**
         * <root> <orgId></orgId> <searchStaffName>ad</searchStaffName>
         * <filterStaffWhere></filterStaffWhere> <currentPage>1</currentPage>
         * </root>
         */
        StringBuffer xmlReturn = new StringBuffer("<root>");
        try {
            int status = -1;
            int pageSize = 10;
            int currentPage = 1;
            long totalNum = 0L;
            long totalPage = 0L;
            int startIndex = pageSize * (currentPage - 1);
            String queryTemplate = "(staff_name:? OR org_name:? OR user_name:? OR fuzzy_user_name:? OR region_name:? OR mobile:? OR email:? OR tel:? OR phs:? OR fax:? OR address:?)";
            String SolrServerUrl = "http://192.168.111.134:20010/staff";
            String queryWord = "敏";
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
            // solrQuery.setSort("staff_name", SolrQuery.ORDER.asc); //按员工名升序排序,
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
                    + (totalNum = response.getResults().getNumFound()));
            if (status == 0) {
                if (totalNum % pageSize == 0) {
                    totalPage = (totalNum / pageSize);// 行记录数刚好被页大小整除
                } else {
                    totalPage = (1 + (totalNum / pageSize));// 行记录数刚好不被页大小整除
                }
                xmlReturn.append("<error_code>0</error_code>");
                xmlReturn.append("<totalNum>" + totalNum + "</totalNum>");
                xmlReturn.append("<totalPage>" + totalPage + "</totalPage>");
                xmlReturn.append("<rowset>");
                QueryResponse rsp = server.query(solrQuery);
                Iterator<SolrDocument> iter = rsp.getResults().iterator();

                String staff_name = null;
                String staff_id = null;
                String org_name = null;
                String org_id = null;
                String staff_post = null;
                String region_name = null;
                String region_id = null;
                String mobile = null;

                while (iter.hasNext()) {
                    SolrDocument resultDoc = iter.next();
                    staff_name = (String) resultDoc.getFieldValue("staff_name");
                    staff_id = (String) resultDoc.getFieldValue("id");
                    org_id = (String) resultDoc.getFieldValue("org_id");
                    org_name = (String) resultDoc.getFieldValue("org_name");
                    staff_post = resultDoc.getFieldValue("staff_post") != null ? (String) resultDoc
                            .getFieldValue("staff_post") : "";
                    region_name = resultDoc.getFieldValue("region_name") != null ? (String) resultDoc
                            .getFieldValue("region_name") : "";
                    region_id = resultDoc.getFieldValue("region_id") != null ? (String) resultDoc
                            .getFieldValue("region_id") : "";
                    mobile = resultDoc.getFieldValue("mobile") != null ? (String) resultDoc
                            .getFieldValue("mobile") : "";
                    xmlReturn.append("<row id='" + staff_id + "' org_id='"
                            + org_id + "' staff_post='" + staff_post
                            + "' org_name='" + org_name + "' staff_name='"
                            + staff_name + "' region_name='" + region_name
                            + "' region_id='" + region_id + "' mobile='"
                            + mobile + "'>");
                    xmlReturn.append(staff_name);
                    xmlReturn.append("[").append(org_name).append("]");
                    xmlReturn.append("</row>");
                }
                xmlReturn.append("</rowset></root>");
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
