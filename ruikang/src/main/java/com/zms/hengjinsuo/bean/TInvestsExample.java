package com.zms.hengjinsuo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TInvestsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TInvestsExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andInvestsidIsNull() {
            addCriterion("investsId is null");
            return (Criteria) this;
        }

        public Criteria andInvestsidIsNotNull() {
            addCriterion("investsId is not null");
            return (Criteria) this;
        }

        public Criteria andInvestsidEqualTo(String value) {
            addCriterion("investsId =", value, "investsid");
            return (Criteria) this;
        }

        public Criteria andInvestsidNotEqualTo(String value) {
            addCriterion("investsId <>", value, "investsid");
            return (Criteria) this;
        }

        public Criteria andInvestsidGreaterThan(String value) {
            addCriterion("investsId >", value, "investsid");
            return (Criteria) this;
        }

        public Criteria andInvestsidGreaterThanOrEqualTo(String value) {
            addCriterion("investsId >=", value, "investsid");
            return (Criteria) this;
        }

        public Criteria andInvestsidLessThan(String value) {
            addCriterion("investsId <", value, "investsid");
            return (Criteria) this;
        }

        public Criteria andInvestsidLessThanOrEqualTo(String value) {
            addCriterion("investsId <=", value, "investsid");
            return (Criteria) this;
        }

        public Criteria andInvestsidLike(String value) {
            addCriterion("investsId like", value, "investsid");
            return (Criteria) this;
        }

        public Criteria andInvestsidNotLike(String value) {
            addCriterion("investsId not like", value, "investsid");
            return (Criteria) this;
        }

        public Criteria andInvestsidIn(List<String> values) {
            addCriterion("investsId in", values, "investsid");
            return (Criteria) this;
        }

        public Criteria andInvestsidNotIn(List<String> values) {
            addCriterion("investsId not in", values, "investsid");
            return (Criteria) this;
        }

        public Criteria andInvestsidBetween(String value1, String value2) {
            addCriterion("investsId between", value1, value2, "investsid");
            return (Criteria) this;
        }

        public Criteria andInvestsidNotBetween(String value1, String value2) {
            addCriterion("investsId not between", value1, value2, "investsid");
            return (Criteria) this;
        }

        public Criteria andRongziidIsNull() {
            addCriterion("rongziId is null");
            return (Criteria) this;
        }

        public Criteria andRongziidIsNotNull() {
            addCriterion("rongziId is not null");
            return (Criteria) this;
        }

        public Criteria andRongziidEqualTo(Integer value) {
            addCriterion("rongziId =", value, "rongziid");
            return (Criteria) this;
        }

        public Criteria andRongziidNotEqualTo(Integer value) {
            addCriterion("rongziId <>", value, "rongziid");
            return (Criteria) this;
        }

        public Criteria andRongziidGreaterThan(Integer value) {
            addCriterion("rongziId >", value, "rongziid");
            return (Criteria) this;
        }

        public Criteria andRongziidGreaterThanOrEqualTo(Integer value) {
            addCriterion("rongziId >=", value, "rongziid");
            return (Criteria) this;
        }

        public Criteria andRongziidLessThan(Integer value) {
            addCriterion("rongziId <", value, "rongziid");
            return (Criteria) this;
        }

        public Criteria andRongziidLessThanOrEqualTo(Integer value) {
            addCriterion("rongziId <=", value, "rongziid");
            return (Criteria) this;
        }

        public Criteria andRongziidIn(List<Integer> values) {
            addCriterion("rongziId in", values, "rongziid");
            return (Criteria) this;
        }

        public Criteria andRongziidNotIn(List<Integer> values) {
            addCriterion("rongziId not in", values, "rongziid");
            return (Criteria) this;
        }

        public Criteria andRongziidBetween(Integer value1, Integer value2) {
            addCriterion("rongziId between", value1, value2, "rongziid");
            return (Criteria) this;
        }

        public Criteria andRongziidNotBetween(Integer value1, Integer value2) {
            addCriterion("rongziId not between", value1, value2, "rongziid");
            return (Criteria) this;
        }

        public Criteria andRzbankidIsNull() {
            addCriterion("rzBankId is null");
            return (Criteria) this;
        }

        public Criteria andRzbankidIsNotNull() {
            addCriterion("rzBankId is not null");
            return (Criteria) this;
        }

        public Criteria andRzbankidEqualTo(Integer value) {
            addCriterion("rzBankId =", value, "rzbankid");
            return (Criteria) this;
        }

        public Criteria andRzbankidNotEqualTo(Integer value) {
            addCriterion("rzBankId <>", value, "rzbankid");
            return (Criteria) this;
        }

        public Criteria andRzbankidGreaterThan(Integer value) {
            addCriterion("rzBankId >", value, "rzbankid");
            return (Criteria) this;
        }

        public Criteria andRzbankidGreaterThanOrEqualTo(Integer value) {
            addCriterion("rzBankId >=", value, "rzbankid");
            return (Criteria) this;
        }

        public Criteria andRzbankidLessThan(Integer value) {
            addCriterion("rzBankId <", value, "rzbankid");
            return (Criteria) this;
        }

        public Criteria andRzbankidLessThanOrEqualTo(Integer value) {
            addCriterion("rzBankId <=", value, "rzbankid");
            return (Criteria) this;
        }

        public Criteria andRzbankidIn(List<Integer> values) {
            addCriterion("rzBankId in", values, "rzbankid");
            return (Criteria) this;
        }

        public Criteria andRzbankidNotIn(List<Integer> values) {
            addCriterion("rzBankId not in", values, "rzbankid");
            return (Criteria) this;
        }

        public Criteria andRzbankidBetween(Integer value1, Integer value2) {
            addCriterion("rzBankId between", value1, value2, "rzbankid");
            return (Criteria) this;
        }

        public Criteria andRzbankidNotBetween(Integer value1, Integer value2) {
            addCriterion("rzBankId not between", value1, value2, "rzbankid");
            return (Criteria) this;
        }

        public Criteria andRzmoneyIsNull() {
            addCriterion("rzMoney is null");
            return (Criteria) this;
        }

        public Criteria andRzmoneyIsNotNull() {
            addCriterion("rzMoney is not null");
            return (Criteria) this;
        }

        public Criteria andRzmoneyEqualTo(Float value) {
            addCriterion("rzMoney =", value, "rzmoney");
            return (Criteria) this;
        }

        public Criteria andRzmoneyNotEqualTo(Float value) {
            addCriterion("rzMoney <>", value, "rzmoney");
            return (Criteria) this;
        }

        public Criteria andRzmoneyGreaterThan(Float value) {
            addCriterion("rzMoney >", value, "rzmoney");
            return (Criteria) this;
        }

        public Criteria andRzmoneyGreaterThanOrEqualTo(Float value) {
            addCriterion("rzMoney >=", value, "rzmoney");
            return (Criteria) this;
        }

        public Criteria andRzmoneyLessThan(Float value) {
            addCriterion("rzMoney <", value, "rzmoney");
            return (Criteria) this;
        }

        public Criteria andRzmoneyLessThanOrEqualTo(Float value) {
            addCriterion("rzMoney <=", value, "rzmoney");
            return (Criteria) this;
        }

        public Criteria andRzmoneyIn(List<Float> values) {
            addCriterion("rzMoney in", values, "rzmoney");
            return (Criteria) this;
        }

        public Criteria andRzmoneyNotIn(List<Float> values) {
            addCriterion("rzMoney not in", values, "rzmoney");
            return (Criteria) this;
        }

        public Criteria andRzmoneyBetween(Float value1, Float value2) {
            addCriterion("rzMoney between", value1, value2, "rzmoney");
            return (Criteria) this;
        }

        public Criteria andRzmoneyNotBetween(Float value1, Float value2) {
            addCriterion("rzMoney not between", value1, value2, "rzmoney");
            return (Criteria) this;
        }

        public Criteria andBacktypeIsNull() {
            addCriterion("backType is null");
            return (Criteria) this;
        }

        public Criteria andBacktypeIsNotNull() {
            addCriterion("backType is not null");
            return (Criteria) this;
        }

        public Criteria andBacktypeEqualTo(Integer value) {
            addCriterion("backType =", value, "backtype");
            return (Criteria) this;
        }

        public Criteria andBacktypeNotEqualTo(Integer value) {
            addCriterion("backType <>", value, "backtype");
            return (Criteria) this;
        }

        public Criteria andBacktypeGreaterThan(Integer value) {
            addCriterion("backType >", value, "backtype");
            return (Criteria) this;
        }

        public Criteria andBacktypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("backType >=", value, "backtype");
            return (Criteria) this;
        }

        public Criteria andBacktypeLessThan(Integer value) {
            addCriterion("backType <", value, "backtype");
            return (Criteria) this;
        }

        public Criteria andBacktypeLessThanOrEqualTo(Integer value) {
            addCriterion("backType <=", value, "backtype");
            return (Criteria) this;
        }

        public Criteria andBacktypeIn(List<Integer> values) {
            addCriterion("backType in", values, "backtype");
            return (Criteria) this;
        }

        public Criteria andBacktypeNotIn(List<Integer> values) {
            addCriterion("backType not in", values, "backtype");
            return (Criteria) this;
        }

        public Criteria andBacktypeBetween(Integer value1, Integer value2) {
            addCriterion("backType between", value1, value2, "backtype");
            return (Criteria) this;
        }

        public Criteria andBacktypeNotBetween(Integer value1, Integer value2) {
            addCriterion("backType not between", value1, value2, "backtype");
            return (Criteria) this;
        }

        public Criteria andContracttimeIsNull() {
            addCriterion("contractTime is null");
            return (Criteria) this;
        }

        public Criteria andContracttimeIsNotNull() {
            addCriterion("contractTime is not null");
            return (Criteria) this;
        }

        public Criteria andContracttimeEqualTo(Date value) {
            addCriterionForJDBCDate("contractTime =", value, "contracttime");
            return (Criteria) this;
        }

        public Criteria andContracttimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("contractTime <>", value, "contracttime");
            return (Criteria) this;
        }

        public Criteria andContracttimeGreaterThan(Date value) {
            addCriterionForJDBCDate("contractTime >", value, "contracttime");
            return (Criteria) this;
        }

        public Criteria andContracttimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("contractTime >=", value, "contracttime");
            return (Criteria) this;
        }

        public Criteria andContracttimeLessThan(Date value) {
            addCriterionForJDBCDate("contractTime <", value, "contracttime");
            return (Criteria) this;
        }

        public Criteria andContracttimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("contractTime <=", value, "contracttime");
            return (Criteria) this;
        }

        public Criteria andContracttimeIn(List<Date> values) {
            addCriterionForJDBCDate("contractTime in", values, "contracttime");
            return (Criteria) this;
        }

        public Criteria andContracttimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("contractTime not in", values, "contracttime");
            return (Criteria) this;
        }

        public Criteria andContracttimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("contractTime between", value1, value2, "contracttime");
            return (Criteria) this;
        }

        public Criteria andContracttimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("contractTime not between", value1, value2, "contracttime");
            return (Criteria) this;
        }

        public Criteria andLastbacktimeIsNull() {
            addCriterion("lastBackTime is null");
            return (Criteria) this;
        }

        public Criteria andLastbacktimeIsNotNull() {
            addCriterion("lastBackTime is not null");
            return (Criteria) this;
        }

        public Criteria andLastbacktimeEqualTo(Date value) {
            addCriterionForJDBCDate("lastBackTime =", value, "lastbacktime");
            return (Criteria) this;
        }

        public Criteria andLastbacktimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("lastBackTime <>", value, "lastbacktime");
            return (Criteria) this;
        }

        public Criteria andLastbacktimeGreaterThan(Date value) {
            addCriterionForJDBCDate("lastBackTime >", value, "lastbacktime");
            return (Criteria) this;
        }

        public Criteria andLastbacktimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("lastBackTime >=", value, "lastbacktime");
            return (Criteria) this;
        }

        public Criteria andLastbacktimeLessThan(Date value) {
            addCriterionForJDBCDate("lastBackTime <", value, "lastbacktime");
            return (Criteria) this;
        }

        public Criteria andLastbacktimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("lastBackTime <=", value, "lastbacktime");
            return (Criteria) this;
        }

        public Criteria andLastbacktimeIn(List<Date> values) {
            addCriterionForJDBCDate("lastBackTime in", values, "lastbacktime");
            return (Criteria) this;
        }

        public Criteria andLastbacktimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("lastBackTime not in", values, "lastbacktime");
            return (Criteria) this;
        }

        public Criteria andLastbacktimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("lastBackTime between", value1, value2, "lastbacktime");
            return (Criteria) this;
        }

        public Criteria andLastbacktimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("lastBackTime not between", value1, value2, "lastbacktime");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("memo is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("memo is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("memo =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("memo <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("memo >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("memo >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("memo <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("memo <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("memo like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("memo not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("memo in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("memo not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("memo between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("memo not between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(Integer value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(Integer value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(Integer value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(Integer value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(Integer value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<Integer> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<Integer> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(Integer value1, Integer value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("flag not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andNeedmoneyIsNull() {
            addCriterion("needMoney is null");
            return (Criteria) this;
        }

        public Criteria andNeedmoneyIsNotNull() {
            addCriterion("needMoney is not null");
            return (Criteria) this;
        }

        public Criteria andNeedmoneyEqualTo(Float value) {
            addCriterion("needMoney =", value, "needmoney");
            return (Criteria) this;
        }

        public Criteria andNeedmoneyNotEqualTo(Float value) {
            addCriterion("needMoney <>", value, "needmoney");
            return (Criteria) this;
        }

        public Criteria andNeedmoneyGreaterThan(Float value) {
            addCriterion("needMoney >", value, "needmoney");
            return (Criteria) this;
        }

        public Criteria andNeedmoneyGreaterThanOrEqualTo(Float value) {
            addCriterion("needMoney >=", value, "needmoney");
            return (Criteria) this;
        }

        public Criteria andNeedmoneyLessThan(Float value) {
            addCriterion("needMoney <", value, "needmoney");
            return (Criteria) this;
        }

        public Criteria andNeedmoneyLessThanOrEqualTo(Float value) {
            addCriterion("needMoney <=", value, "needmoney");
            return (Criteria) this;
        }

        public Criteria andNeedmoneyIn(List<Float> values) {
            addCriterion("needMoney in", values, "needmoney");
            return (Criteria) this;
        }

        public Criteria andNeedmoneyNotIn(List<Float> values) {
            addCriterion("needMoney not in", values, "needmoney");
            return (Criteria) this;
        }

        public Criteria andNeedmoneyBetween(Float value1, Float value2) {
            addCriterion("needMoney between", value1, value2, "needmoney");
            return (Criteria) this;
        }

        public Criteria andNeedmoneyNotBetween(Float value1, Float value2) {
            addCriterion("needMoney not between", value1, value2, "needmoney");
            return (Criteria) this;
        }

        public Criteria andBegintimeIsNull() {
            addCriterion("beginTime is null");
            return (Criteria) this;
        }

        public Criteria andBegintimeIsNotNull() {
            addCriterion("beginTime is not null");
            return (Criteria) this;
        }

        public Criteria andBegintimeEqualTo(Date value) {
            addCriterionForJDBCDate("beginTime =", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("beginTime <>", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeGreaterThan(Date value) {
            addCriterionForJDBCDate("beginTime >", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("beginTime >=", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeLessThan(Date value) {
            addCriterionForJDBCDate("beginTime <", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("beginTime <=", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeIn(List<Date> values) {
            addCriterionForJDBCDate("beginTime in", values, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("beginTime not in", values, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("beginTime between", value1, value2, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("beginTime not between", value1, value2, "begintime");
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

        public Criteria andLongtimeEqualTo(Short value) {
            addCriterion("longTime =", value, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeNotEqualTo(Short value) {
            addCriterion("longTime <>", value, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeGreaterThan(Short value) {
            addCriterion("longTime >", value, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeGreaterThanOrEqualTo(Short value) {
            addCriterion("longTime >=", value, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeLessThan(Short value) {
            addCriterion("longTime <", value, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeLessThanOrEqualTo(Short value) {
            addCriterion("longTime <=", value, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeIn(List<Short> values) {
            addCriterion("longTime in", values, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeNotIn(List<Short> values) {
            addCriterion("longTime not in", values, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeBetween(Short value1, Short value2) {
            addCriterion("longTime between", value1, value2, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeNotBetween(Short value1, Short value2) {
            addCriterion("longTime not between", value1, value2, "longtime");
            return (Criteria) this;
        }

        public Criteria andFirsttimeIsNull() {
            addCriterion("firstTime is null");
            return (Criteria) this;
        }

        public Criteria andFirsttimeIsNotNull() {
            addCriterion("firstTime is not null");
            return (Criteria) this;
        }

        public Criteria andFirsttimeEqualTo(Date value) {
            addCriterionForJDBCDate("firstTime =", value, "firsttime");
            return (Criteria) this;
        }

        public Criteria andFirsttimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("firstTime <>", value, "firsttime");
            return (Criteria) this;
        }

        public Criteria andFirsttimeGreaterThan(Date value) {
            addCriterionForJDBCDate("firstTime >", value, "firsttime");
            return (Criteria) this;
        }

        public Criteria andFirsttimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("firstTime >=", value, "firsttime");
            return (Criteria) this;
        }

        public Criteria andFirsttimeLessThan(Date value) {
            addCriterionForJDBCDate("firstTime <", value, "firsttime");
            return (Criteria) this;
        }

        public Criteria andFirsttimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("firstTime <=", value, "firsttime");
            return (Criteria) this;
        }

        public Criteria andFirsttimeIn(List<Date> values) {
            addCriterionForJDBCDate("firstTime in", values, "firsttime");
            return (Criteria) this;
        }

        public Criteria andFirsttimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("firstTime not in", values, "firsttime");
            return (Criteria) this;
        }

        public Criteria andFirsttimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("firstTime between", value1, value2, "firsttime");
            return (Criteria) this;
        }

        public Criteria andFirsttimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("firstTime not between", value1, value2, "firsttime");
            return (Criteria) this;
        }

        public Criteria andAprIsNull() {
            addCriterion("apr is null");
            return (Criteria) this;
        }

        public Criteria andAprIsNotNull() {
            addCriterion("apr is not null");
            return (Criteria) this;
        }

        public Criteria andAprEqualTo(Float value) {
            addCriterion("apr =", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprNotEqualTo(Float value) {
            addCriterion("apr <>", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprGreaterThan(Float value) {
            addCriterion("apr >", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprGreaterThanOrEqualTo(Float value) {
            addCriterion("apr >=", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprLessThan(Float value) {
            addCriterion("apr <", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprLessThanOrEqualTo(Float value) {
            addCriterion("apr <=", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprIn(List<Float> values) {
            addCriterion("apr in", values, "apr");
            return (Criteria) this;
        }

        public Criteria andAprNotIn(List<Float> values) {
            addCriterion("apr not in", values, "apr");
            return (Criteria) this;
        }

        public Criteria andAprBetween(Float value1, Float value2) {
            addCriterion("apr between", value1, value2, "apr");
            return (Criteria) this;
        }

        public Criteria andAprNotBetween(Float value1, Float value2) {
            addCriterion("apr not between", value1, value2, "apr");
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