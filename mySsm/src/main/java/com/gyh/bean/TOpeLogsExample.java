package com.gyh.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TOpeLogsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TOpeLogsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOpeTypeIsNull() {
            addCriterion("ope_type is null");
            return (Criteria) this;
        }

        public Criteria andOpeTypeIsNotNull() {
            addCriterion("ope_type is not null");
            return (Criteria) this;
        }

        public Criteria andOpeTypeEqualTo(String value) {
            addCriterion("ope_type =", value, "opeType");
            return (Criteria) this;
        }

        public Criteria andOpeTypeNotEqualTo(String value) {
            addCriterion("ope_type <>", value, "opeType");
            return (Criteria) this;
        }

        public Criteria andOpeTypeGreaterThan(String value) {
            addCriterion("ope_type >", value, "opeType");
            return (Criteria) this;
        }

        public Criteria andOpeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ope_type >=", value, "opeType");
            return (Criteria) this;
        }

        public Criteria andOpeTypeLessThan(String value) {
            addCriterion("ope_type <", value, "opeType");
            return (Criteria) this;
        }

        public Criteria andOpeTypeLessThanOrEqualTo(String value) {
            addCriterion("ope_type <=", value, "opeType");
            return (Criteria) this;
        }

        public Criteria andOpeTypeLike(String value) {
            addCriterion("ope_type like", value, "opeType");
            return (Criteria) this;
        }

        public Criteria andOpeTypeNotLike(String value) {
            addCriterion("ope_type not like", value, "opeType");
            return (Criteria) this;
        }

        public Criteria andOpeTypeIn(List<String> values) {
            addCriterion("ope_type in", values, "opeType");
            return (Criteria) this;
        }

        public Criteria andOpeTypeNotIn(List<String> values) {
            addCriterion("ope_type not in", values, "opeType");
            return (Criteria) this;
        }

        public Criteria andOpeTypeBetween(String value1, String value2) {
            addCriterion("ope_type between", value1, value2, "opeType");
            return (Criteria) this;
        }

        public Criteria andOpeTypeNotBetween(String value1, String value2) {
            addCriterion("ope_type not between", value1, value2, "opeType");
            return (Criteria) this;
        }

        public Criteria andNodeIsNull() {
            addCriterion("node is null");
            return (Criteria) this;
        }

        public Criteria andNodeIsNotNull() {
            addCriterion("node is not null");
            return (Criteria) this;
        }

        public Criteria andNodeEqualTo(String value) {
            addCriterion("node =", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeNotEqualTo(String value) {
            addCriterion("node <>", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeGreaterThan(String value) {
            addCriterion("node >", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeGreaterThanOrEqualTo(String value) {
            addCriterion("node >=", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeLessThan(String value) {
            addCriterion("node <", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeLessThanOrEqualTo(String value) {
            addCriterion("node <=", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeLike(String value) {
            addCriterion("node like", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeNotLike(String value) {
            addCriterion("node not like", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeIn(List<String> values) {
            addCriterion("node in", values, "node");
            return (Criteria) this;
        }

        public Criteria andNodeNotIn(List<String> values) {
            addCriterion("node not in", values, "node");
            return (Criteria) this;
        }

        public Criteria andNodeBetween(String value1, String value2) {
            addCriterion("node between", value1, value2, "node");
            return (Criteria) this;
        }

        public Criteria andNodeNotBetween(String value1, String value2) {
            addCriterion("node not between", value1, value2, "node");
            return (Criteria) this;
        }

        public Criteria andResultFlagIsNull() {
            addCriterion("result_flag is null");
            return (Criteria) this;
        }

        public Criteria andResultFlagIsNotNull() {
            addCriterion("result_flag is not null");
            return (Criteria) this;
        }

        public Criteria andResultFlagEqualTo(Boolean value) {
            addCriterion("result_flag =", value, "resultFlag");
            return (Criteria) this;
        }

        public Criteria andResultFlagNotEqualTo(Boolean value) {
            addCriterion("result_flag <>", value, "resultFlag");
            return (Criteria) this;
        }

        public Criteria andResultFlagGreaterThan(Boolean value) {
            addCriterion("result_flag >", value, "resultFlag");
            return (Criteria) this;
        }

        public Criteria andResultFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("result_flag >=", value, "resultFlag");
            return (Criteria) this;
        }

        public Criteria andResultFlagLessThan(Boolean value) {
            addCriterion("result_flag <", value, "resultFlag");
            return (Criteria) this;
        }

        public Criteria andResultFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("result_flag <=", value, "resultFlag");
            return (Criteria) this;
        }

        public Criteria andResultFlagIn(List<Boolean> values) {
            addCriterion("result_flag in", values, "resultFlag");
            return (Criteria) this;
        }

        public Criteria andResultFlagNotIn(List<Boolean> values) {
            addCriterion("result_flag not in", values, "resultFlag");
            return (Criteria) this;
        }

        public Criteria andResultFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("result_flag between", value1, value2, "resultFlag");
            return (Criteria) this;
        }

        public Criteria andResultFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("result_flag not between", value1, value2, "resultFlag");
            return (Criteria) this;
        }

        public Criteria andParamJsonIsNull() {
            addCriterion("param_json is null");
            return (Criteria) this;
        }

        public Criteria andParamJsonIsNotNull() {
            addCriterion("param_json is not null");
            return (Criteria) this;
        }

        public Criteria andParamJsonEqualTo(String value) {
            addCriterion("param_json =", value, "paramJson");
            return (Criteria) this;
        }

        public Criteria andParamJsonNotEqualTo(String value) {
            addCriterion("param_json <>", value, "paramJson");
            return (Criteria) this;
        }

        public Criteria andParamJsonGreaterThan(String value) {
            addCriterion("param_json >", value, "paramJson");
            return (Criteria) this;
        }

        public Criteria andParamJsonGreaterThanOrEqualTo(String value) {
            addCriterion("param_json >=", value, "paramJson");
            return (Criteria) this;
        }

        public Criteria andParamJsonLessThan(String value) {
            addCriterion("param_json <", value, "paramJson");
            return (Criteria) this;
        }

        public Criteria andParamJsonLessThanOrEqualTo(String value) {
            addCriterion("param_json <=", value, "paramJson");
            return (Criteria) this;
        }

        public Criteria andParamJsonLike(String value) {
            addCriterion("param_json like", value, "paramJson");
            return (Criteria) this;
        }

        public Criteria andParamJsonNotLike(String value) {
            addCriterion("param_json not like", value, "paramJson");
            return (Criteria) this;
        }

        public Criteria andParamJsonIn(List<String> values) {
            addCriterion("param_json in", values, "paramJson");
            return (Criteria) this;
        }

        public Criteria andParamJsonNotIn(List<String> values) {
            addCriterion("param_json not in", values, "paramJson");
            return (Criteria) this;
        }

        public Criteria andParamJsonBetween(String value1, String value2) {
            addCriterion("param_json between", value1, value2, "paramJson");
            return (Criteria) this;
        }

        public Criteria andParamJsonNotBetween(String value1, String value2) {
            addCriterion("param_json not between", value1, value2, "paramJson");
            return (Criteria) this;
        }

        public Criteria andResultJsonIsNull() {
            addCriterion("result_json is null");
            return (Criteria) this;
        }

        public Criteria andResultJsonIsNotNull() {
            addCriterion("result_json is not null");
            return (Criteria) this;
        }

        public Criteria andResultJsonEqualTo(String value) {
            addCriterion("result_json =", value, "resultJson");
            return (Criteria) this;
        }

        public Criteria andResultJsonNotEqualTo(String value) {
            addCriterion("result_json <>", value, "resultJson");
            return (Criteria) this;
        }

        public Criteria andResultJsonGreaterThan(String value) {
            addCriterion("result_json >", value, "resultJson");
            return (Criteria) this;
        }

        public Criteria andResultJsonGreaterThanOrEqualTo(String value) {
            addCriterion("result_json >=", value, "resultJson");
            return (Criteria) this;
        }

        public Criteria andResultJsonLessThan(String value) {
            addCriterion("result_json <", value, "resultJson");
            return (Criteria) this;
        }

        public Criteria andResultJsonLessThanOrEqualTo(String value) {
            addCriterion("result_json <=", value, "resultJson");
            return (Criteria) this;
        }

        public Criteria andResultJsonLike(String value) {
            addCriterion("result_json like", value, "resultJson");
            return (Criteria) this;
        }

        public Criteria andResultJsonNotLike(String value) {
            addCriterion("result_json not like", value, "resultJson");
            return (Criteria) this;
        }

        public Criteria andResultJsonIn(List<String> values) {
            addCriterion("result_json in", values, "resultJson");
            return (Criteria) this;
        }

        public Criteria andResultJsonNotIn(List<String> values) {
            addCriterion("result_json not in", values, "resultJson");
            return (Criteria) this;
        }

        public Criteria andResultJsonBetween(String value1, String value2) {
            addCriterion("result_json between", value1, value2, "resultJson");
            return (Criteria) this;
        }

        public Criteria andResultJsonNotBetween(String value1, String value2) {
            addCriterion("result_json not between", value1, value2, "resultJson");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Integer value) {
            addCriterion("create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Integer value) {
            addCriterion("create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Integer value) {
            addCriterion("create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Integer value) {
            addCriterion("create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Integer> values) {
            addCriterion("create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Integer> values) {
            addCriterion("create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andLoginIpIsNull() {
            addCriterion("login_ip is null");
            return (Criteria) this;
        }

        public Criteria andLoginIpIsNotNull() {
            addCriterion("login_ip is not null");
            return (Criteria) this;
        }

        public Criteria andLoginIpEqualTo(String value) {
            addCriterion("login_ip =", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpNotEqualTo(String value) {
            addCriterion("login_ip <>", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpGreaterThan(String value) {
            addCriterion("login_ip >", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpGreaterThanOrEqualTo(String value) {
            addCriterion("login_ip >=", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpLessThan(String value) {
            addCriterion("login_ip <", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpLessThanOrEqualTo(String value) {
            addCriterion("login_ip <=", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpLike(String value) {
            addCriterion("login_ip like", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpNotLike(String value) {
            addCriterion("login_ip not like", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpIn(List<String> values) {
            addCriterion("login_ip in", values, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpNotIn(List<String> values) {
            addCriterion("login_ip not in", values, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpBetween(String value1, String value2) {
            addCriterion("login_ip between", value1, value2, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpNotBetween(String value1, String value2) {
            addCriterion("login_ip not between", value1, value2, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginBrowserIsNull() {
            addCriterion("login_browser is null");
            return (Criteria) this;
        }

        public Criteria andLoginBrowserIsNotNull() {
            addCriterion("login_browser is not null");
            return (Criteria) this;
        }

        public Criteria andLoginBrowserEqualTo(String value) {
            addCriterion("login_browser =", value, "loginBrowser");
            return (Criteria) this;
        }

        public Criteria andLoginBrowserNotEqualTo(String value) {
            addCriterion("login_browser <>", value, "loginBrowser");
            return (Criteria) this;
        }

        public Criteria andLoginBrowserGreaterThan(String value) {
            addCriterion("login_browser >", value, "loginBrowser");
            return (Criteria) this;
        }

        public Criteria andLoginBrowserGreaterThanOrEqualTo(String value) {
            addCriterion("login_browser >=", value, "loginBrowser");
            return (Criteria) this;
        }

        public Criteria andLoginBrowserLessThan(String value) {
            addCriterion("login_browser <", value, "loginBrowser");
            return (Criteria) this;
        }

        public Criteria andLoginBrowserLessThanOrEqualTo(String value) {
            addCriterion("login_browser <=", value, "loginBrowser");
            return (Criteria) this;
        }

        public Criteria andLoginBrowserLike(String value) {
            addCriterion("login_browser like", value, "loginBrowser");
            return (Criteria) this;
        }

        public Criteria andLoginBrowserNotLike(String value) {
            addCriterion("login_browser not like", value, "loginBrowser");
            return (Criteria) this;
        }

        public Criteria andLoginBrowserIn(List<String> values) {
            addCriterion("login_browser in", values, "loginBrowser");
            return (Criteria) this;
        }

        public Criteria andLoginBrowserNotIn(List<String> values) {
            addCriterion("login_browser not in", values, "loginBrowser");
            return (Criteria) this;
        }

        public Criteria andLoginBrowserBetween(String value1, String value2) {
            addCriterion("login_browser between", value1, value2, "loginBrowser");
            return (Criteria) this;
        }

        public Criteria andLoginBrowserNotBetween(String value1, String value2) {
            addCriterion("login_browser not between", value1, value2, "loginBrowser");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}