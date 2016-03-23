package com.zms.hengjinsuo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TReceiveExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TReceiveExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andInvestidIsNull() {
            addCriterion("investId is null");
            return (Criteria) this;
        }

        public Criteria andInvestidIsNotNull() {
            addCriterion("investId is not null");
            return (Criteria) this;
        }

        public Criteria andInvestidEqualTo(Integer value) {
            addCriterion("investId =", value, "investid");
            return (Criteria) this;
        }

        public Criteria andInvestidNotEqualTo(Integer value) {
            addCriterion("investId <>", value, "investid");
            return (Criteria) this;
        }

        public Criteria andInvestidGreaterThan(Integer value) {
            addCriterion("investId >", value, "investid");
            return (Criteria) this;
        }

        public Criteria andInvestidGreaterThanOrEqualTo(Integer value) {
            addCriterion("investId >=", value, "investid");
            return (Criteria) this;
        }

        public Criteria andInvestidLessThan(Integer value) {
            addCriterion("investId <", value, "investid");
            return (Criteria) this;
        }

        public Criteria andInvestidLessThanOrEqualTo(Integer value) {
            addCriterion("investId <=", value, "investid");
            return (Criteria) this;
        }

        public Criteria andInvestidIn(List<Integer> values) {
            addCriterion("investId in", values, "investid");
            return (Criteria) this;
        }

        public Criteria andInvestidNotIn(List<Integer> values) {
            addCriterion("investId not in", values, "investid");
            return (Criteria) this;
        }

        public Criteria andInvestidBetween(Integer value1, Integer value2) {
            addCriterion("investId between", value1, value2, "investid");
            return (Criteria) this;
        }

        public Criteria andInvestidNotBetween(Integer value1, Integer value2) {
            addCriterion("investId not between", value1, value2, "investid");
            return (Criteria) this;
        }

        public Criteria andBackmoneyIsNull() {
            addCriterion("backMoney is null");
            return (Criteria) this;
        }

        public Criteria andBackmoneyIsNotNull() {
            addCriterion("backMoney is not null");
            return (Criteria) this;
        }

        public Criteria andBackmoneyEqualTo(Float value) {
            addCriterion("backMoney =", value, "backmoney");
            return (Criteria) this;
        }

        public Criteria andBackmoneyNotEqualTo(Float value) {
            addCriterion("backMoney <>", value, "backmoney");
            return (Criteria) this;
        }

        public Criteria andBackmoneyGreaterThan(Float value) {
            addCriterion("backMoney >", value, "backmoney");
            return (Criteria) this;
        }

        public Criteria andBackmoneyGreaterThanOrEqualTo(Float value) {
            addCriterion("backMoney >=", value, "backmoney");
            return (Criteria) this;
        }

        public Criteria andBackmoneyLessThan(Float value) {
            addCriterion("backMoney <", value, "backmoney");
            return (Criteria) this;
        }

        public Criteria andBackmoneyLessThanOrEqualTo(Float value) {
            addCriterion("backMoney <=", value, "backmoney");
            return (Criteria) this;
        }

        public Criteria andBackmoneyIn(List<Float> values) {
            addCriterion("backMoney in", values, "backmoney");
            return (Criteria) this;
        }

        public Criteria andBackmoneyNotIn(List<Float> values) {
            addCriterion("backMoney not in", values, "backmoney");
            return (Criteria) this;
        }

        public Criteria andBackmoneyBetween(Float value1, Float value2) {
            addCriterion("backMoney between", value1, value2, "backmoney");
            return (Criteria) this;
        }

        public Criteria andBackmoneyNotBetween(Float value1, Float value2) {
            addCriterion("backMoney not between", value1, value2, "backmoney");
            return (Criteria) this;
        }

        public Criteria andBacktimeIsNull() {
            addCriterion("backTime is null");
            return (Criteria) this;
        }

        public Criteria andBacktimeIsNotNull() {
            addCriterion("backTime is not null");
            return (Criteria) this;
        }

        public Criteria andBacktimeEqualTo(Date value) {
            addCriterionForJDBCDate("backTime =", value, "backtime");
            return (Criteria) this;
        }

        public Criteria andBacktimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("backTime <>", value, "backtime");
            return (Criteria) this;
        }

        public Criteria andBacktimeGreaterThan(Date value) {
            addCriterionForJDBCDate("backTime >", value, "backtime");
            return (Criteria) this;
        }

        public Criteria andBacktimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("backTime >=", value, "backtime");
            return (Criteria) this;
        }

        public Criteria andBacktimeLessThan(Date value) {
            addCriterionForJDBCDate("backTime <", value, "backtime");
            return (Criteria) this;
        }

        public Criteria andBacktimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("backTime <=", value, "backtime");
            return (Criteria) this;
        }

        public Criteria andBacktimeIn(List<Date> values) {
            addCriterionForJDBCDate("backTime in", values, "backtime");
            return (Criteria) this;
        }

        public Criteria andBacktimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("backTime not in", values, "backtime");
            return (Criteria) this;
        }

        public Criteria andBacktimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("backTime between", value1, value2, "backtime");
            return (Criteria) this;
        }

        public Criteria andBacktimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("backTime not between", value1, value2, "backtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeIsNull() {
            addCriterion("realTime is null");
            return (Criteria) this;
        }

        public Criteria andRealtimeIsNotNull() {
            addCriterion("realTime is not null");
            return (Criteria) this;
        }

        public Criteria andRealtimeEqualTo(Date value) {
            addCriterionForJDBCDate("realTime =", value, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("realTime <>", value, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeGreaterThan(Date value) {
            addCriterionForJDBCDate("realTime >", value, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("realTime >=", value, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeLessThan(Date value) {
            addCriterionForJDBCDate("realTime <", value, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("realTime <=", value, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeIn(List<Date> values) {
            addCriterionForJDBCDate("realTime in", values, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("realTime not in", values, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("realTime between", value1, value2, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("realTime not between", value1, value2, "realtime");
            return (Criteria) this;
        }

        public Criteria andBackflagIsNull() {
            addCriterion("backFlag is null");
            return (Criteria) this;
        }

        public Criteria andBackflagIsNotNull() {
            addCriterion("backFlag is not null");
            return (Criteria) this;
        }

        public Criteria andBackflagEqualTo(Integer value) {
            addCriterion("backFlag =", value, "backflag");
            return (Criteria) this;
        }

        public Criteria andBackflagNotEqualTo(Integer value) {
            addCriterion("backFlag <>", value, "backflag");
            return (Criteria) this;
        }

        public Criteria andBackflagGreaterThan(Integer value) {
            addCriterion("backFlag >", value, "backflag");
            return (Criteria) this;
        }

        public Criteria andBackflagGreaterThanOrEqualTo(Integer value) {
            addCriterion("backFlag >=", value, "backflag");
            return (Criteria) this;
        }

        public Criteria andBackflagLessThan(Integer value) {
            addCriterion("backFlag <", value, "backflag");
            return (Criteria) this;
        }

        public Criteria andBackflagLessThanOrEqualTo(Integer value) {
            addCriterion("backFlag <=", value, "backflag");
            return (Criteria) this;
        }

        public Criteria andBackflagIn(List<Integer> values) {
            addCriterion("backFlag in", values, "backflag");
            return (Criteria) this;
        }

        public Criteria andBackflagNotIn(List<Integer> values) {
            addCriterion("backFlag not in", values, "backflag");
            return (Criteria) this;
        }

        public Criteria andBackflagBetween(Integer value1, Integer value2) {
            addCriterion("backFlag between", value1, value2, "backflag");
            return (Criteria) this;
        }

        public Criteria andBackflagNotBetween(Integer value1, Integer value2) {
            addCriterion("backFlag not between", value1, value2, "backflag");
            return (Criteria) this;
        }

        public Criteria andBacksortIsNull() {
            addCriterion("backSort is null");
            return (Criteria) this;
        }

        public Criteria andBacksortIsNotNull() {
            addCriterion("backSort is not null");
            return (Criteria) this;
        }

        public Criteria andBacksortEqualTo(Integer value) {
            addCriterion("backSort =", value, "backsort");
            return (Criteria) this;
        }

        public Criteria andBacksortNotEqualTo(Integer value) {
            addCriterion("backSort <>", value, "backsort");
            return (Criteria) this;
        }

        public Criteria andBacksortGreaterThan(Integer value) {
            addCriterion("backSort >", value, "backsort");
            return (Criteria) this;
        }

        public Criteria andBacksortGreaterThanOrEqualTo(Integer value) {
            addCriterion("backSort >=", value, "backsort");
            return (Criteria) this;
        }

        public Criteria andBacksortLessThan(Integer value) {
            addCriterion("backSort <", value, "backsort");
            return (Criteria) this;
        }

        public Criteria andBacksortLessThanOrEqualTo(Integer value) {
            addCriterion("backSort <=", value, "backsort");
            return (Criteria) this;
        }

        public Criteria andBacksortIn(List<Integer> values) {
            addCriterion("backSort in", values, "backsort");
            return (Criteria) this;
        }

        public Criteria andBacksortNotIn(List<Integer> values) {
            addCriterion("backSort not in", values, "backsort");
            return (Criteria) this;
        }

        public Criteria andBacksortBetween(Integer value1, Integer value2) {
            addCriterion("backSort between", value1, value2, "backsort");
            return (Criteria) this;
        }

        public Criteria andBacksortNotBetween(Integer value1, Integer value2) {
            addCriterion("backSort not between", value1, value2, "backsort");
            return (Criteria) this;
        }

        public Criteria andLastflagIsNull() {
            addCriterion("lastFlag is null");
            return (Criteria) this;
        }

        public Criteria andLastflagIsNotNull() {
            addCriterion("lastFlag is not null");
            return (Criteria) this;
        }

        public Criteria andLastflagEqualTo(Boolean value) {
            addCriterion("lastFlag =", value, "lastflag");
            return (Criteria) this;
        }

        public Criteria andLastflagNotEqualTo(Boolean value) {
            addCriterion("lastFlag <>", value, "lastflag");
            return (Criteria) this;
        }

        public Criteria andLastflagGreaterThan(Boolean value) {
            addCriterion("lastFlag >", value, "lastflag");
            return (Criteria) this;
        }

        public Criteria andLastflagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("lastFlag >=", value, "lastflag");
            return (Criteria) this;
        }

        public Criteria andLastflagLessThan(Boolean value) {
            addCriterion("lastFlag <", value, "lastflag");
            return (Criteria) this;
        }

        public Criteria andLastflagLessThanOrEqualTo(Boolean value) {
            addCriterion("lastFlag <=", value, "lastflag");
            return (Criteria) this;
        }

        public Criteria andLastflagIn(List<Boolean> values) {
            addCriterion("lastFlag in", values, "lastflag");
            return (Criteria) this;
        }

        public Criteria andLastflagNotIn(List<Boolean> values) {
            addCriterion("lastFlag not in", values, "lastflag");
            return (Criteria) this;
        }

        public Criteria andLastflagBetween(Boolean value1, Boolean value2) {
            addCriterion("lastFlag between", value1, value2, "lastflag");
            return (Criteria) this;
        }

        public Criteria andLastflagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("lastFlag not between", value1, value2, "lastflag");
            return (Criteria) this;
        }

        public Criteria andLongtimeIsNull() {
            addCriterion("longTime is null");
            return (Criteria) this;
        }

        public Criteria andLongtimeIsNotNull() {
            addCriterion("longTime is not null");
            return (Criteria) this;
        }

        public Criteria andLongtimeEqualTo(Integer value) {
            addCriterion("longTime =", value, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeNotEqualTo(Integer value) {
            addCriterion("longTime <>", value, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeGreaterThan(Integer value) {
            addCriterion("longTime >", value, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("longTime >=", value, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeLessThan(Integer value) {
            addCriterion("longTime <", value, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeLessThanOrEqualTo(Integer value) {
            addCriterion("longTime <=", value, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeIn(List<Integer> values) {
            addCriterion("longTime in", values, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeNotIn(List<Integer> values) {
            addCriterion("longTime not in", values, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeBetween(Integer value1, Integer value2) {
            addCriterion("longTime between", value1, value2, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeNotBetween(Integer value1, Integer value2) {
            addCriterion("longTime not between", value1, value2, "longtime");
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