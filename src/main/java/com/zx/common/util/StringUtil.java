package com.zx.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ning
 * 创建于 2017年10月18日下午4:19:14
 * //TODO 字符串工具类
 */
public final class StringUtil {

    public static String leftPad(Object obj, int len, char appendChar) {
        String result = null;
        if (obj != null) {
            String str = String.valueOf(obj);
            if (str.length() < len) {
                result = "";
                for (int i = 0; i < len - str.length(); i++) {
                    result += appendChar;
                }
                result += str;
            } else {
                result = str;
            }
        }
        return result;
    }

    public static String substringAfterLast(String str, String separator) {
        if (isEmpty(str)) {
            return str;
        }
        if (isEmpty(separator)) {
            return "";
        }
        int pos = str.lastIndexOf(separator);
        if (pos == -1 || pos == str.length() - separator.length()) {
            return "";
        } else {
            return str.substring(pos + separator.length());
        }
    }

    public static String viewValue(Object o) {
        if (o == null) {
            return "-";
        }
        if (isEmpty(o.toString())) {
            return "-";
        }
        return o.toString();
    }

    /**
     * returns true if null or empty, otherwise false.
     */
    public static boolean isEmpty(String string) {
        return (string == null) || (string.trim().length() == 0);
    }

    public static boolean notEmpty(String string) {
        return !isEmpty(string);
    }

    /**
     * encode the HTML tags
     * 
     * @param str
     * @return
     */
    public static String encodeHtml(String str) {
        String eh = str;
        if (null != eh) {
            eh = eh.replaceAll("&", "&amp;");
            eh = eh.replaceAll(">", "&gt;");
            eh = eh.replaceAll("<", "&lt;");
            eh = eh.replaceAll("\"", "&quot;");
            eh = eh.replaceAll("'", "&#39;");
            eh = eh.replaceAll(" ", "&nbsp;");
            eh = eh.replaceAll("\r\n", "<br/>");
            eh = eh.replaceAll("\n", "<br/>");
            eh = eh.replaceAll("\r", "");
        }

        return eh;
    }

    /**
     * encode the HTML tags except character '&nbsp;'
     * 
     * @param str
     * @return
     */
    public static String encodeHtmlExceptNbsp(String str) {
        String eh = str;
        if (null != eh) {
            eh = eh.replaceAll("&", "&amp;");
            eh = eh.replaceAll(">", "&gt;");
            eh = eh.replaceAll("<", "&lt;");
            eh = eh.replaceAll("\"", "&quot;");
            eh = eh.replaceAll("'", "&#39;");
            eh = eh.replaceAll("\r\n", "<br/>");
            eh = eh.replaceAll("\n", "<br/>");
            eh = eh.replaceAll("\r", "");
        }
        return eh;
    }

    public static String replaceNbspWithSpace(String str) {
        if (str != null) {
            str = str.replaceAll("&nbsp;", " ");
        }

        return str;
    }

    private static final byte[] VAL = { 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F };

    /**
     * unescape the String variable which comes from javascript escape() method.
     */
    public static String unescape(String param) {
        if (null == param || "".equals(param.trim())) {
            return "";
        }

        StringBuffer sbuf = new StringBuffer();
        int i = 0;
        int len = param.length();
        while (i < len) {
            int ch = param.charAt(i);
            if ('A' <= ch && ch <= 'Z') {
                sbuf.append((char) ch);
            } else if ('a' <= ch && ch <= 'z') {
                sbuf.append((char) ch);
            } else if ('0' <= ch && ch <= '9') {
                sbuf.append((char) ch);
            } else if (ch == '-' || ch == '_' || ch == '.' || ch == '!' || ch == '~' || ch == '*' || ch == '\'' || ch == '(' || ch == ')') {
                sbuf.append((char) ch);
            } else if (ch == '%') {
                int cint = 0;
                if ('u' != param.charAt(i + 1)) {
                    cint = (cint << 4) | VAL[param.charAt(i + 1)];
                    cint = (cint << 4) | VAL[param.charAt(i + 2)];
                    i += 2;
                } else {
                    cint = (cint << 4) | VAL[param.charAt(i + 2)];
                    cint = (cint << 4) | VAL[param.charAt(i + 3)];
                    cint = (cint << 4) | VAL[param.charAt(i + 4)];
                    cint = (cint << 4) | VAL[param.charAt(i + 5)];
                    i += 5;
                }
                sbuf.append((char) cint);
            } else {
                sbuf.append((char) ch);
            }
            i++;
        }
        return sbuf.toString();
    }

    public static boolean containsInvalidChar(String str) {
        // "<", ">", "'", """, ")", ";", "%", or "_"
        if (isEmpty(str)) {
            return false;
        }

        String[] chars = new String[] { "<", ">", "'", "\"", ")", ";", "%", "_" };

        for (String string : chars) {
            if (str.contains(string)) {
                return true;
            }
        }
        return false;
    }

    public static boolean getBoolean(String str) {
        return "YES".equalsIgnoreCase(str) || "TRUE".equalsIgnoreCase(str);
    }

    /**
     * lefPad with septial Char
     * 
     * @param str
     * @param fillChar
     * @param len
     * @return
     */
    public static String leftPad(String str, char fillChar, int len) {
        if (str == null) {
            return null;
        }
        if (str.length() >= len) {
            return str;
        }
        StringBuilder buf = new StringBuilder(str);
        while (buf.length() < len) {
            buf.insert(0, fillChar);
        }
        return buf.toString();
    }

    /**
     * rightPad with septial Char
     * 
     * @param str
     * @param fillChar
     * @param len
     * @return
     */
    public static String rightPad(String str, char fillChar, int len) {
        if (str == null) {
            return null;
        }
        if (str.length() >= len) {
            return str;
        }
        StringBuilder buf = new StringBuilder(str);
        while (buf.length() < len) {
            buf.append(fillChar);
        }
        return buf.toString();
    }

    /**
     * split 2016年5月30日 上午10:32:48
     * 
     * @param strs
     * @return TODO：分解string
     */
    public static String[] split(String strs) {
        String[] s = null;
        if (StringUtil.notEmpty(strs)) {
            s = strs.split(",");
        }
        return s;
    }

    /**
     * split 2016年5月30日 上午10:32:48
     * 
     * @param strs
     * @return TODO：分解string
     */
    public static String[] splitByPattern(String strs, String pattern) {
        String[] s = null;
        if (StringUtil.notEmpty(strs) && StringUtil.notEmpty(pattern)) {
            s = strs.split(pattern);
        }
        return s;
    }

    /**
     * strArryToString 2016年6月6日 下午1:24:47
     * 
     * @param strs
     * @return TODO：将字符串数组转换成数组格式的字符串
     */
    public static String strArryToString(String[] strs) {
        StringBuilder sb = new StringBuilder();
        if (strs != null && strs.length > 0) {
            for (String string : strs) {
                sb = sb.append(",").append(string);
            }
        }
        return "[" + sb.toString().substring(1) + "]";
    }
    
    /**
     *TODO 将"[\"1\",\"2\",\"3\"]"格式字符串转换成字符串数组["1","2","3"]
     * @param snArrStr
     * @return
     * ning 创建于  2018年1月15日 下午2:42:19 
     */
    public static String[] convertArrStringToArr(String snArrStr){
        if(StringUtil.notEmpty(snArrStr) && snArrStr.length() > 2){
            snArrStr = snArrStr.substring(1,snArrStr.length() - 1);
            String[] arr = snArrStr.split(",");
            if(arr != null && arr.length > 0){
                String[] arr1 = new String[arr.length];
                for (int i = 0; i < arr.length; i++) {
                    arr1[i] = arr[i].substring(1, arr[i].length() - 1);
                }
                return arr1;
            }else{
                return null;
            }
        }
        return null;
    }
    
    /**
     *TODO 将1，2，3，4，5，6格式字符串转换成字符串集合
     * @param snArrStr
     * @return
     * ning 创建于  2018年2月23日 下午5:59:04 
     */
    public static List<String> convertArrStringToList(String snArrStr){
        List<String> strList = new ArrayList<>();
        String[] arr = snArrStr.split(",");
        if(arr != null && arr.length > 0){
            for (int i = 0; i < arr.length; i++) {
                strList.add(arr[i]);
            }
        }
        return strList;
    }
    
    public static final String REGEX = "[\u4e00-\u9fa5]";
    public static String convertUnicode(String str){
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String chinese = matcher.group(0) + "";
            str = str.replace(chinese, gbEncoding(chinese));
        }
        return str;
    }
    
    // 中文转Unicode
    public static String gbEncoding(String gbString) { // gbString = "测试"
        char[] utfBytes = gbString.toCharArray(); // utfBytes = [测, 试]
        String unicodeBytes = "";
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            String hexB = Integer.toHexString(utfBytes[byteIndex]); // 转换为16进制整型字符串
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
       // System.out.println("unicodeBytes is: " + unicodeBytes);
        return unicodeBytes;
    }


    public static void main(String[] args) {
//        List<String> snArr = new ArrayList<>();
//        long bltStart = Long.parseLong("123456784321", 16);
//        long bltEnd = Long.parseLong("123456784720", 16);
//        System.out.println(bltStart);
//        System.out.println(bltEnd);
//        
     /*   for (long i = 20015998321441L; i <= 20015998322464L; i++) {
            String sn = Long.toHexString(i).toUpperCase();
            if(!Validators.isNumeric(sn)){
                System.out.println(sn);
                snArr.add(sn);
            }
        }
        System.out.println(snArr.size());*/
        
      /*  boolean a = true, b = true, c = false;
        System.out.println(a || b && c);*/


      System.out.println(Integer.valueOf("0000"));

    }

    /**
     *TODO 将["1","2"]格式字符串转换成可sql in查询的字符串 '1','2','3'
     * @param ids
     * @return
     * ning 创建于  2018年1月8日 下午4:40:05 
     */
    public static String getSqlInString(String ids){
        if(StringUtil.notEmpty(ids)){
            ids = ids.substring(1, ids.length() - 1);
            ids = ids.replace("\"", "'");
        }
        return ids;
    }
    
    /**
     *TODO 比较两个集合，找去在前一个集合中没有，后一个集合中有的元素
     * @param openBuildIds【前一个集合】
     * @param applicationBuildIds【后一个集合】
     * @return
     * ning 创建于  2017年12月28日 上午11:49:58 
     */
    public static List<String> getDeleteElements(List<String> openBuildIds, List<String> applicationBuildIds) {
        
        List<String> deleteElements = new ArrayList<>();
        if(!CommonUtil.isListEmpty(applicationBuildIds)){
            for (String applicationBuild : applicationBuildIds) {
                if(!openBuildIds.contains(applicationBuild)){
                    deleteElements.add(applicationBuild);
                }
            }
        }
        return deleteElements;
    }

    public static boolean endsWithIgnoreCase(String str, String suffix) {
        if (str == null || suffix == null) {
            return false;
        }
        if (str.endsWith(suffix)) {
            return true;
        }
        if (str.length() < suffix.length()) {
            return false;
        }

        String lcStr = str.substring(str.length() - suffix.length()).toLowerCase();
        String lcSuffix = suffix.toLowerCase();
        return lcStr.equals(lcSuffix);
    }

    public static String convertFloorNo(Integer floorId) {
        if(floorId < 0){
            return "FloorB" + Math.abs(floorId);
        }else{
            return "Floor" + floorId;
        }
    }


}
