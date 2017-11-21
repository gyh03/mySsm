package com.gyh.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TFileExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TFileExample() {
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

        public Criteria andOrgiNameIsNull() {
            addCriterion("orgi_name is null");
            return (Criteria) this;
        }

        public Criteria andOrgiNameIsNotNull() {
            addCriterion("orgi_name is not null");
            return (Criteria) this;
        }

        public Criteria andOrgiNameEqualTo(String value) {
            addCriterion("orgi_name =", value, "orgiName");
            return (Criteria) this;
        }

        public Criteria andOrgiNameNotEqualTo(String value) {
            addCriterion("orgi_name <>", value, "orgiName");
            return (Criteria) this;
        }

        public Criteria andOrgiNameGreaterThan(String value) {
            addCriterion("orgi_name >", value, "orgiName");
            return (Criteria) this;
        }

        public Criteria andOrgiNameGreaterThanOrEqualTo(String value) {
            addCriterion("orgi_name >=", value, "orgiName");
            return (Criteria) this;
        }

        public Criteria andOrgiNameLessThan(String value) {
            addCriterion("orgi_name <", value, "orgiName");
            return (Criteria) this;
        }

        public Criteria andOrgiNameLessThanOrEqualTo(String value) {
            addCriterion("orgi_name <=", value, "orgiName");
            return (Criteria) this;
        }

        public Criteria andOrgiNameLike(String value) {
            addCriterion("orgi_name like", value, "orgiName");
            return (Criteria) this;
        }

        public Criteria andOrgiNameNotLike(String value) {
            addCriterion("orgi_name not like", value, "orgiName");
            return (Criteria) this;
        }

        public Criteria andOrgiNameIn(List<String> values) {
            addCriterion("orgi_name in", values, "orgiName");
            return (Criteria) this;
        }

        public Criteria andOrgiNameNotIn(List<String> values) {
            addCriterion("orgi_name not in", values, "orgiName");
            return (Criteria) this;
        }

        public Criteria andOrgiNameBetween(String value1, String value2) {
            addCriterion("orgi_name between", value1, value2, "orgiName");
            return (Criteria) this;
        }

        public Criteria andOrgiNameNotBetween(String value1, String value2) {
            addCriterion("orgi_name not between", value1, value2, "orgiName");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNull() {
            addCriterion("file_name is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("file_name is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("file_name =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("file_name <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("file_name >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("file_name >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("file_name <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("file_name <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("file_name like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("file_name not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("file_name in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("file_name not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("file_name between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("file_name not between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileRealPathIsNull() {
            addCriterion("file_real_path is null");
            return (Criteria) this;
        }

        public Criteria andFileRealPathIsNotNull() {
            addCriterion("file_real_path is not null");
            return (Criteria) this;
        }

        public Criteria andFileRealPathEqualTo(String value) {
            addCriterion("file_real_path =", value, "fileRealPath");
            return (Criteria) this;
        }

        public Criteria andFileRealPathNotEqualTo(String value) {
            addCriterion("file_real_path <>", value, "fileRealPath");
            return (Criteria) this;
        }

        public Criteria andFileRealPathGreaterThan(String value) {
            addCriterion("file_real_path >", value, "fileRealPath");
            return (Criteria) this;
        }

        public Criteria andFileRealPathGreaterThanOrEqualTo(String value) {
            addCriterion("file_real_path >=", value, "fileRealPath");
            return (Criteria) this;
        }

        public Criteria andFileRealPathLessThan(String value) {
            addCriterion("file_real_path <", value, "fileRealPath");
            return (Criteria) this;
        }

        public Criteria andFileRealPathLessThanOrEqualTo(String value) {
            addCriterion("file_real_path <=", value, "fileRealPath");
            return (Criteria) this;
        }

        public Criteria andFileRealPathLike(String value) {
            addCriterion("file_real_path like", value, "fileRealPath");
            return (Criteria) this;
        }

        public Criteria andFileRealPathNotLike(String value) {
            addCriterion("file_real_path not like", value, "fileRealPath");
            return (Criteria) this;
        }

        public Criteria andFileRealPathIn(List<String> values) {
            addCriterion("file_real_path in", values, "fileRealPath");
            return (Criteria) this;
        }

        public Criteria andFileRealPathNotIn(List<String> values) {
            addCriterion("file_real_path not in", values, "fileRealPath");
            return (Criteria) this;
        }

        public Criteria andFileRealPathBetween(String value1, String value2) {
            addCriterion("file_real_path between", value1, value2, "fileRealPath");
            return (Criteria) this;
        }

        public Criteria andFileRealPathNotBetween(String value1, String value2) {
            addCriterion("file_real_path not between", value1, value2, "fileRealPath");
            return (Criteria) this;
        }

        public Criteria andFileReltPathIsNull() {
            addCriterion("file_relt_path is null");
            return (Criteria) this;
        }

        public Criteria andFileReltPathIsNotNull() {
            addCriterion("file_relt_path is not null");
            return (Criteria) this;
        }

        public Criteria andFileReltPathEqualTo(String value) {
            addCriterion("file_relt_path =", value, "fileReltPath");
            return (Criteria) this;
        }

        public Criteria andFileReltPathNotEqualTo(String value) {
            addCriterion("file_relt_path <>", value, "fileReltPath");
            return (Criteria) this;
        }

        public Criteria andFileReltPathGreaterThan(String value) {
            addCriterion("file_relt_path >", value, "fileReltPath");
            return (Criteria) this;
        }

        public Criteria andFileReltPathGreaterThanOrEqualTo(String value) {
            addCriterion("file_relt_path >=", value, "fileReltPath");
            return (Criteria) this;
        }

        public Criteria andFileReltPathLessThan(String value) {
            addCriterion("file_relt_path <", value, "fileReltPath");
            return (Criteria) this;
        }

        public Criteria andFileReltPathLessThanOrEqualTo(String value) {
            addCriterion("file_relt_path <=", value, "fileReltPath");
            return (Criteria) this;
        }

        public Criteria andFileReltPathLike(String value) {
            addCriterion("file_relt_path like", value, "fileReltPath");
            return (Criteria) this;
        }

        public Criteria andFileReltPathNotLike(String value) {
            addCriterion("file_relt_path not like", value, "fileReltPath");
            return (Criteria) this;
        }

        public Criteria andFileReltPathIn(List<String> values) {
            addCriterion("file_relt_path in", values, "fileReltPath");
            return (Criteria) this;
        }

        public Criteria andFileReltPathNotIn(List<String> values) {
            addCriterion("file_relt_path not in", values, "fileReltPath");
            return (Criteria) this;
        }

        public Criteria andFileReltPathBetween(String value1, String value2) {
            addCriterion("file_relt_path between", value1, value2, "fileReltPath");
            return (Criteria) this;
        }

        public Criteria andFileReltPathNotBetween(String value1, String value2) {
            addCriterion("file_relt_path not between", value1, value2, "fileReltPath");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
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