package com.zms.hengjinsuo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TLogsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TLogsExample() {
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

        public Criteria andManageridIsNull() {
            addCriterion("managerId is null");
            return (Criteria) this;
        }

        public Criteria andManageridIsNotNull() {
            addCriterion("managerId is not null");
            return (Criteria) this;
        }

        public Criteria andManageridEqualTo(Integer value) {
            addCriterion("managerId =", value, "managerid");
            return (Criteria) this;
        }

        public Criteria andManageridNotEqualTo(Integer value) {
            addCriterion("managerId <>", value, "managerid");
            return (Criteria) this;
        }

        public Criteria andManageridGreaterThan(Integer value) {
            addCriterion("managerId >", value, "managerid");
            return (Criteria) this;
        }

        public Criteria andManageridGreaterThanOrEqualTo(Integer value) {
            addCriterion("managerId >=", value, "managerid");
            return (Criteria) this;
        }

        public Criteria andManageridLessThan(Integer value) {
            addCriterion("managerId <", value, "managerid");
            return (Criteria) this;
        }

        public Criteria andManageridLessThanOrEqualTo(Integer value) {
            addCriterion("managerId <=", value, "managerid");
            return (Criteria) this;
        }

        public Criteria andManageridIn(List<Integer> values) {
            addCriterion("managerId in", values, "managerid");
            return (Criteria) this;
        }

        public Criteria andManageridNotIn(List<Integer> values) {
            addCriterion("managerId not in", values, "managerid");
            return (Criteria) this;
        }

        public Criteria andManageridBetween(Integer value1, Integer value2) {
            addCriterion("managerId between", value1, value2, "managerid");
            return (Criteria) this;
        }

        public Criteria andManageridNotBetween(Integer value1, Integer value2) {
            addCriterion("managerId not between", value1, value2, "managerid");
            return (Criteria) this;
        }

        public Criteria andTypeflagIsNull() {
            addCriterion("typeFlag is null");
            return (Criteria) this;
        }

        public Criteria andTypeflagIsNotNull() {
            addCriterion("typeFlag is not null");
            return (Criteria) this;
        }

        public Criteria andTypeflagEqualTo(Integer value) {
            addCriterion("typeFlag =", value, "typeflag");
            return (Criteria) this;
        }

        public Criteria andTypeflagNotEqualTo(Integer value) {
            addCriterion("typeFlag <>", value, "typeflag");
            return (Criteria) this;
        }

        public Criteria andTypeflagGreaterThan(Integer value) {
            addCriterion("typeFlag >", value, "typeflag");
            return (Criteria) this;
        }

        public Criteria andTypeflagGreaterThanOrEqualTo(Integer value) {
            addCriterion("typeFlag >=", value, "typeflag");
            return (Criteria) this;
        }

        public Criteria andTypeflagLessThan(Integer value) {
            addCriterion("typeFlag <", value, "typeflag");
            return (Criteria) this;
        }

        public Criteria andTypeflagLessThanOrEqualTo(Integer value) {
            addCriterion("typeFlag <=", value, "typeflag");
            return (Criteria) this;
        }

        public Criteria andTypeflagIn(List<Integer> values) {
            addCriterion("typeFlag in", values, "typeflag");
            return (Criteria) this;
        }

        public Criteria andTypeflagNotIn(List<Integer> values) {
            addCriterion("typeFlag not in", values, "typeflag");
            return (Criteria) this;
        }

        public Criteria andTypeflagBetween(Integer value1, Integer value2) {
            addCriterion("typeFlag between", value1, value2, "typeflag");
            return (Criteria) this;
        }

        public Criteria andTypeflagNotBetween(Integer value1, Integer value2) {
            addCriterion("typeFlag not between", value1, value2, "typeflag");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andModitimeIsNull() {
            addCriterion("modiTime is null");
            return (Criteria) this;
        }

        public Criteria andModitimeIsNotNull() {
            addCriterion("modiTime is not null");
            return (Criteria) this;
        }

        public Criteria andModitimeEqualTo(Date value) {
            addCriterion("modiTime =", value, "moditime");
            return (Criteria) this;
        }

        public Criteria andModitimeNotEqualTo(Date value) {
            addCriterion("modiTime <>", value, "moditime");
            return (Criteria) this;
        }

        public Criteria andModitimeGreaterThan(Date value) {
            addCriterion("modiTime >", value, "moditime");
            return (Criteria) this;
        }

        public Criteria andModitimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modiTime >=", value, "moditime");
            return (Criteria) this;
        }

        public Criteria andModitimeLessThan(Date value) {
            addCriterion("modiTime <", value, "moditime");
            return (Criteria) this;
        }

        public Criteria andModitimeLessThanOrEqualTo(Date value) {
            addCriterion("modiTime <=", value, "moditime");
            return (Criteria) this;
        }

        public Criteria andModitimeIn(List<Date> values) {
            addCriterion("modiTime in", values, "moditime");
            return (Criteria) this;
        }

        public Criteria andModitimeNotIn(List<Date> values) {
            addCriterion("modiTime not in", values, "moditime");
            return (Criteria) this;
        }

        public Criteria andModitimeBetween(Date value1, Date value2) {
            addCriterion("modiTime between", value1, value2, "moditime");
            return (Criteria) this;
        }

        public Criteria andModitimeNotBetween(Date value1, Date value2) {
            addCriterion("modiTime not between", value1, value2, "moditime");
            return (Criteria) this;
        }

        public Criteria andSourceipIsNull() {
            addCriterion("sourceIp is null");
            return (Criteria) this;
        }

        public Criteria andSourceipIsNotNull() {
            addCriterion("sourceIp is not null");
            return (Criteria) this;
        }

        public Criteria andSourceipEqualTo(String value) {
            addCriterion("sourceIp =", value, "sourceip");
            return (Criteria) this;
        }

        public Criteria andSourceipNotEqualTo(String value) {
            addCriterion("sourceIp <>", value, "sourceip");
            return (Criteria) this;
        }

        public Criteria andSourceipGreaterThan(String value) {
            addCriterion("sourceIp >", value, "sourceip");
            return (Criteria) this;
        }

        public Criteria andSourceipGreaterThanOrEqualTo(String value) {
            addCriterion("sourceIp >=", value, "sourceip");
            return (Criteria) this;
        }

        public Criteria andSourceipLessThan(String value) {
            addCriterion("sourceIp <", value, "sourceip");
            return (Criteria) this;
        }

        public Criteria andSourceipLessThanOrEqualTo(String value) {
            addCriterion("sourceIp <=", value, "sourceip");
            return (Criteria) this;
        }

        public Criteria andSourceipLike(String value) {
            addCriterion("sourceIp like", value, "sourceip");
            return (Criteria) this;
        }

        public Criteria andSourceipNotLike(String value) {
            addCriterion("sourceIp not like", value, "sourceip");
            return (Criteria) this;
        }

        public Criteria andSourceipIn(List<String> values) {
            addCriterion("sourceIp in", values, "sourceip");
            return (Criteria) this;
        }

        public Criteria andSourceipNotIn(List<String> values) {
            addCriterion("sourceIp not in", values, "sourceip");
            return (Criteria) this;
        }

        public Criteria andSourceipBetween(String value1, String value2) {
            addCriterion("sourceIp between", value1, value2, "sourceip");
            return (Criteria) this;
        }

        public Criteria andSourceipNotBetween(String value1, String value2) {
            addCriterion("sourceIp not between", value1, value2, "sourceip");
            return (Criteria) this;
        }

        public Criteria andAccessurlIsNull() {
            addCriterion("accessUrl is null");
            return (Criteria) this;
        }

        public Criteria andAccessurlIsNotNull() {
            addCriterion("accessUrl is not null");
            return (Criteria) this;
        }

        public Criteria andAccessurlEqualTo(String value) {
            addCriterion("accessUrl =", value, "accessurl");
            return (Criteria) this;
        }

        public Criteria andAccessurlNotEqualTo(String value) {
            addCriterion("accessUrl <>", value, "accessurl");
            return (Criteria) this;
        }

        public Criteria andAccessurlGreaterThan(String value) {
            addCriterion("accessUrl >", value, "accessurl");
            return (Criteria) this;
        }

        public Criteria andAccessurlGreaterThanOrEqualTo(String value) {
            addCriterion("accessUrl >=", value, "accessurl");
            return (Criteria) this;
        }

        public Criteria andAccessurlLessThan(String value) {
            addCriterion("accessUrl <", value, "accessurl");
            return (Criteria) this;
        }

        public Criteria andAccessurlLessThanOrEqualTo(String value) {
            addCriterion("accessUrl <=", value, "accessurl");
            return (Criteria) this;
        }

        public Criteria andAccessurlLike(String value) {
            addCriterion("accessUrl like", value, "accessurl");
            return (Criteria) this;
        }

        public Criteria andAccessurlNotLike(String value) {
            addCriterion("accessUrl not like", value, "accessurl");
            return (Criteria) this;
        }

        public Criteria andAccessurlIn(List<String> values) {
            addCriterion("accessUrl in", values, "accessurl");
            return (Criteria) this;
        }

        public Criteria andAccessurlNotIn(List<String> values) {
            addCriterion("accessUrl not in", values, "accessurl");
            return (Criteria) this;
        }

        public Criteria andAccessurlBetween(String value1, String value2) {
            addCriterion("accessUrl between", value1, value2, "accessurl");
            return (Criteria) this;
        }

        public Criteria andAccessurlNotBetween(String value1, String value2) {
            addCriterion("accessUrl not between", value1, value2, "accessurl");
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