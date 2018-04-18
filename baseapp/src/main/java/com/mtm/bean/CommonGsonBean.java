package com.mtm.bean;


/**
 * 万能解析Bean
 * @author MtmWp
 * @date 2017-11-20 13:32.
 */

public class CommonGsonBean {
    private String resultFlag;//返回响应结果    200为请求成功,其他为失败
    private String message;//返回手持机提示信息    例如当402时返回执行时抛出异常
    private String resultRule;//响应结果规则     200 请求成功,402方法执行时抛出异常
    private Object data;//返回数据
    private HibernatePage page;//分页数据
    private String messageCode;//消息码

    public String getResultFlag() {
        return resultFlag;
    }

    public void setResultFlag(String resultFlag) {
        this.resultFlag = resultFlag;
    }

    public String getResultMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultRule() {
        return resultRule;
    }

    public void setResultRule(String resultRule) {
        this.resultRule = resultRule;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public HibernatePage getPage() {
        return page;
    }

    public void setPage(HibernatePage page) {
        this.page = page;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String toString() {
        return "CommonGsonBean{" +
                "resultFlag='" + resultFlag + '\'' +
                ", message='" + message + '\'' +
                ", resultRule='" + resultRule + '\'' +
                ", data=" + data +
                ", page=" + page +
                ", messageCode='" + messageCode + '\'' +
                '}';
    }
}
