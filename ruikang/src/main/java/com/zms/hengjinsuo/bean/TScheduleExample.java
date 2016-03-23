package com.zms.hengjinsuo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TScheduleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TScheduleExample() {
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

        public Criteria andContractidIsNull() {
            addCriterion("contractId is null");
            return (Criteria) this;
        }

        public Criteria andContractidIsNotNull() {
            addCriterion("contractId is not null");
            return (Criteria) this;
        }

        public Criteria andContractidEqualTo(String value) {
            addCriterion("contractId =", value, "contractid");
            return (Criteria) this;
        }

        public Criteria andContractidNotEqualTo(String value) {
            addCriterion("contractId <>", value, "contractid");
            return (Criteria) this;
        }

        public Criteria andContractidGreaterThan(String value) {
            addCriterion("contractId >", value, "contractid");
            return (Criteria) this;
        }

        public Criteria andContractidGreaterThanOrEqualTo(String value) {
            addCriterion("contractId >=", value, "contractid");
            return (Criteria) this;
        }

        public Criteria andContractidLessThan(String value) {
            addCriterion("contractId <", value, "contractid");
            return (Criteria) this;
        }

        public Criteria andContractidLessThanOrEqualTo(String value) {
            addCriterion("contractId <=", value, "contractid");
            return (Criteria) this;
        }

        public Criteria andContractidLike(String value) {
            addCriterion("contractId like", value, "contractid");
            return (Criteria) this;
        }

        public Criteria andContractidNotLike(String value) {
            addCriterion("contractId not like", value, "contractid");
            return (Criteria) this;
        }

        public Criteria andContractidIn(List<String> values) {
            addCriterion("contractId in", values, "contractid");
            return (Criteria) this;
        }

        public Criteria andContractidNotIn(List<String> values) {
            addCriterion("contractId not in", values, "contractid");
            return (Criteria) this;
        }

        public Criteria andContractidBetween(String value1, String value2) {
            addCriterion("contractId between", value1, value2, "contractid");
            return (Criteria) this;
        }

        public Criteria andContractidNotBetween(String value1, String value2) {
            addCriterion("contractId not between", value1, value2, "contractid");
            return (Criteria) this;
        }

        public Criteria andBillidIsNull() {
            addCriterion("billId is null");
            return (Criteria) this;
        }

        public Criteria andBillidIsNotNull() {
            addCriterion("billId is not null");
            return (Criteria) this;
        }

        public Criteria andBillidEqualTo(Integer value) {
            addCriterion("billId =", value, "billid");
            return (Criteria) this;
        }

        public Criteria andBillidNotEqualTo(Integer value) {
            addCriterion("billId <>", value, "billid");
            return (Criteria) this;
        }

        public Criteria andBillidGreaterThan(Integer value) {
            addCriterion("billId >", value, "billid");
            return (Criteria) this;
        }

        public Criteria andBillidGreaterThanOrEqualTo(Integer value) {
            addCriterion("billId >=", value, "billid");
            return (Criteria) this;
        }

        public Criteria andBillidLessThan(Integer value) {
            addCriterion("billId <", value, "billid");
            return (Criteria) this;
        }

        public Criteria andBillidLessThanOrEqualTo(Integer value) {
            addCriterion("billId <=", value, "billid");
            return (Criteria) this;
        }

        public Criteria andBillidIn(List<Integer> values) {
            addCriterion("billId in", values, "billid");
            return (Criteria) this;
        }

        public Criteria andBillidNotIn(List<Integer> values) {
            addCriterion("billId not in", values, "billid");
            return (Criteria) this;
        }

        public Criteria andBillidBetween(Integer value1, Integer value2) {
            addCriterion("billId between", value1, value2, "billid");
            return (Criteria) this;
        }

        public Criteria andBillidNotBetween(Integer value1, Integer value2) {
            addCriterion("billId not between", value1, value2, "billid");
            return (Criteria) this;
        }

        public Criteria andMemonyIsNull() {
            addCriterion("memony is null");
            return (Criteria) this;
        }

        public Criteria andMemonyIsNotNull() {
            addCriterion("memony is not null");
            return (Criteria) this;
        }

        public Criteria andMemonyEqualTo(Float value) {
            addCriterion("memony =", value, "memony");
            return (Criteria) this;
        }

        public Criteria andMemonyNotEqualTo(Float value) {
            addCriterion("memony <>", value, "memony");
            return (Criteria) this;
        }

        public Criteria andMemonyGreaterThan(Float value) {
            addCriterion("memony >", value, "memony");
            return (Criteria) this;
        }

        public Criteria andMemonyGreaterThanOrEqualTo(Float value) {
            addCriterion("memony >=", value, "memony");
            return (Criteria) this;
        }

        public Criteria andMemonyLessThan(Float value) {
            addCriterion("memony <", value, "memony");
            return (Criteria) this;
        }

        public Criteria andMemonyLessThanOrEqualTo(Float value) {
            addCriterion("memony <=", value, "memony");
            return (Criteria) this;
        }

        public Criteria andMemonyIn(List<Float> values) {
            addCriterion("memony in", values, "memony");
            return (Criteria) this;
        }

        public Criteria andMemonyNotIn(List<Float> values) {
            addCriterion("memony not in", values, "memony");
            return (Criteria) this;
        }

        public Criteria andMemonyBetween(Float value1, Float value2) {
            addCriterion("memony between", value1, value2, "memony");
            return (Criteria) this;
        }

        public Criteria andMemonyNotBetween(Float value1, Float value2) {
            addCriterion("memony not between", value1, value2, "memony");
            return (Criteria) this;
        }

        public Criteria andMemonytimeIsNull() {
            addCriterion("memonyTime is null");
            return (Criteria) this;
        }

        public Criteria andMemonytimeIsNotNull() {
            addCriterion("memonyTime is not null");
            return (Criteria) this;
        }

        public Criteria andMemonytimeEqualTo(Date value) {
            addCriterionForJDBCDate("memonyTime =", value, "memonytime");
            return (Criteria) this;
        }

        public Criteria andMemonytimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("memonyTime <>", value, "memonytime");
            return (Criteria) this;
        }

        public Criteria andMemonytimeGreaterThan(Date value) {
            addCriterionForJDBCDate("memonyTime >", value, "memonytime");
            return (Criteria) this;
        }

        public Criteria andMemonytimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("memonyTime >=", value, "memonytime");
            return (Criteria) this;
        }

        public Criteria andMemonytimeLessThan(Date value) {
            addCriterionForJDBCDate("memonyTime <", value, "memonytime");
            return (Criteria) this;
        }

        public Criteria andMemonytimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("memonyTime <=", value, "memonytime");
            return (Criteria) this;
        }

        public Criteria andMemonytimeIn(List<Date> values) {
            addCriterionForJDBCDate("memonyTime in", values, "memonytime");
            return (Criteria) this;
        }

        public Criteria andMemonytimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("memonyTime not in", values, "memonytime");
            return (Criteria) this;
        }

        public Criteria andMemonytimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("memonyTime between", value1, value2, "memonytime");
            return (Criteria) this;
        }

        public Criteria andMemonytimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("memonyTime not between", value1, value2, "memonytime");
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

        public Criteria andVipnameIsNull() {
            addCriterion("vipName is null");
            return (Criteria) this;
        }

        public Criteria andVipnameIsNotNull() {
            addCriterion("vipName is not null");
            return (Criteria) this;
        }

        public Criteria andVipnameEqualTo(String value) {
            addCriterion("vipName =", value, "vipname");
            return (Criteria) this;
        }

        public Criteria andVipnameNotEqualTo(String value) {
            addCriterion("vipName <>", value, "vipname");
            return (Criteria) this;
        }

        public Criteria andVipnameGreaterThan(String value) {
            addCriterion("vipName >", value, "vipname");
            return (Criteria) this;
        }

        public Criteria andVipnameGreaterThanOrEqualTo(String value) {
            addCriterion("vipName >=", value, "vipname");
            return (Criteria) this;
        }

        public Criteria andVipnameLessThan(String value) {
            addCriterion("vipName <", value, "vipname");
            return (Criteria) this;
        }

        public Criteria andVipnameLessThanOrEqualTo(String value) {
            addCriterion("vipName <=", value, "vipname");
            return (Criteria) this;
        }

        public Criteria andVipnameLike(String value) {
            addCriterion("vipName like", value, "vipname");
            return (Criteria) this;
        }

        public Criteria andVipnameNotLike(String value) {
            addCriterion("vipName not like", value, "vipname");
            return (Criteria) this;
        }

        public Criteria andVipnameIn(List<String> values) {
            addCriterion("vipName in", values, "vipname");
            return (Criteria) this;
        }

        public Criteria andVipnameNotIn(List<String> values) {
            addCriterion("vipName not in", values, "vipname");
            return (Criteria) this;
        }

        public Criteria andVipnameBetween(String value1, String value2) {
            addCriterion("vipName between", value1, value2, "vipname");
            return (Criteria) this;
        }

        public Criteria andVipnameNotBetween(String value1, String value2) {
            addCriterion("vipName not between", value1, value2, "vipname");
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

        public Criteria andVipidIsNull() {
            addCriterion("vipId is null");
            return (Criteria) this;
        }

        public Criteria andVipidIsNotNull() {
            addCriterion("vipId is not null");
            return (Criteria) this;
        }

        public Criteria andVipidEqualTo(Integer value) {
            addCriterion("vipId =", value, "vipid");
            return (Criteria) this;
        }

        public Criteria andVipidNotEqualTo(Integer value) {
            addCriterion("vipId <>", value, "vipid");
            return (Criteria) this;
        }

        public Criteria andVipidGreaterThan(Integer value) {
            addCriterion("vipId >", value, "vipid");
            return (Criteria) this;
        }

        public Criteria andVipidGreaterThanOrEqualTo(Integer value) {
            addCriterion("vipId >=", value, "vipid");
            return (Criteria) this;
        }

        public Criteria andVipidLessThan(Integer value) {
            addCriterion("vipId <", value, "vipid");
            return (Criteria) this;
        }

        public Criteria andVipidLessThanOrEqualTo(Integer value) {
            addCriterion("vipId <=", value, "vipid");
            return (Criteria) this;
        }

        public Criteria andVipidIn(List<Integer> values) {
            addCriterion("vipId in", values, "vipid");
            return (Criteria) this;
        }

        public Criteria andVipidNotIn(List<Integer> values) {
            addCriterion("vipId not in", values, "vipid");
            return (Criteria) this;
        }

        public Criteria andVipidBetween(Integer value1, Integer value2) {
            addCriterion("vipId between", value1, value2, "vipid");
            return (Criteria) this;
        }

        public Criteria andVipidNotBetween(Integer value1, Integer value2) {
            addCriterion("vipId not between", value1, value2, "vipid");
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

        public Criteria andContractidintIsNull() {
            addCriterion("contractidInt is null");
            return (Criteria) this;
        }

        public Criteria andContractidintIsNotNull() {
            addCriterion("contractidInt is not null");
            return (Criteria) this;
        }

        public Criteria andContractidintEqualTo(Integer value) {
            addCriterion("contractidInt =", value, "contractidint");
            return (Criteria) this;
        }

        public Criteria andContractidintNotEqualTo(Integer value) {
            addCriterion("contractidInt <>", value, "contractidint");
            return (Criteria) this;
        }

        public Criteria andContractidintGreaterThan(Integer value) {
            addCriterion("contractidInt >", value, "contractidint");
            return (Criteria) this;
        }

        public Criteria andContractidintGreaterThanOrEqualTo(Integer value) {
            addCriterion("contractidInt >=", value, "contractidint");
            return (Criteria) this;
        }

        public Criteria andContractidintLessThan(Integer value) {
            addCriterion("contractidInt <", value, "contractidint");
            return (Criteria) this;
        }

        public Criteria andContractidintLessThanOrEqualTo(Integer value) {
            addCriterion("contractidInt <=", value, "contractidint");
            return (Criteria) this;
        }

        public Criteria andContractidintIn(List<Integer> values) {
            addCriterion("contractidInt in", values, "contractidint");
            return (Criteria) this;
        }

        public Criteria andContractidintNotIn(List<Integer> values) {
            addCriterion("contractidInt not in", values, "contractidint");
            return (Criteria) this;
        }

        public Criteria andContractidintBetween(Integer value1, Integer value2) {
            addCriterion("contractidInt between", value1, value2, "contractidint");
            return (Criteria) this;
        }

        public Criteria andContractidintNotBetween(Integer value1, Integer value2) {
            addCriterion("contractidInt not between", value1, value2, "contractidint");
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