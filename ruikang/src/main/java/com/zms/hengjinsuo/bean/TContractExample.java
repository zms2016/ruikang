package com.zms.hengjinsuo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TContractExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TContractExample() {
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

        public Criteria andCreatecontracttimeIsNull() {
            addCriterion("createContractTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatecontracttimeIsNotNull() {
            addCriterion("createContractTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatecontracttimeEqualTo(Date value) {
            addCriterion("createContractTime =", value, "createcontracttime");
            return (Criteria) this;
        }

        public Criteria andCreatecontracttimeNotEqualTo(Date value) {
            addCriterion("createContractTime <>", value, "createcontracttime");
            return (Criteria) this;
        }

        public Criteria andCreatecontracttimeGreaterThan(Date value) {
            addCriterion("createContractTime >", value, "createcontracttime");
            return (Criteria) this;
        }

        public Criteria andCreatecontracttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createContractTime >=", value, "createcontracttime");
            return (Criteria) this;
        }

        public Criteria andCreatecontracttimeLessThan(Date value) {
            addCriterion("createContractTime <", value, "createcontracttime");
            return (Criteria) this;
        }

        public Criteria andCreatecontracttimeLessThanOrEqualTo(Date value) {
            addCriterion("createContractTime <=", value, "createcontracttime");
            return (Criteria) this;
        }

        public Criteria andCreatecontracttimeIn(List<Date> values) {
            addCriterion("createContractTime in", values, "createcontracttime");
            return (Criteria) this;
        }

        public Criteria andCreatecontracttimeNotIn(List<Date> values) {
            addCriterion("createContractTime not in", values, "createcontracttime");
            return (Criteria) this;
        }

        public Criteria andCreatecontracttimeBetween(Date value1, Date value2) {
            addCriterion("createContractTime between", value1, value2, "createcontracttime");
            return (Criteria) this;
        }

        public Criteria andCreatecontracttimeNotBetween(Date value1, Date value2) {
            addCriterion("createContractTime not between", value1, value2, "createcontracttime");
            return (Criteria) this;
        }

        public Criteria andGetcontracttimeIsNull() {
            addCriterion("getContractTime is null");
            return (Criteria) this;
        }

        public Criteria andGetcontracttimeIsNotNull() {
            addCriterion("getContractTime is not null");
            return (Criteria) this;
        }

        public Criteria andGetcontracttimeEqualTo(Date value) {
            addCriterionForJDBCDate("getContractTime =", value, "getcontracttime");
            return (Criteria) this;
        }

        public Criteria andGetcontracttimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("getContractTime <>", value, "getcontracttime");
            return (Criteria) this;
        }

        public Criteria andGetcontracttimeGreaterThan(Date value) {
            addCriterionForJDBCDate("getContractTime >", value, "getcontracttime");
            return (Criteria) this;
        }

        public Criteria andGetcontracttimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("getContractTime >=", value, "getcontracttime");
            return (Criteria) this;
        }

        public Criteria andGetcontracttimeLessThan(Date value) {
            addCriterionForJDBCDate("getContractTime <", value, "getcontracttime");
            return (Criteria) this;
        }

        public Criteria andGetcontracttimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("getContractTime <=", value, "getcontracttime");
            return (Criteria) this;
        }

        public Criteria andGetcontracttimeIn(List<Date> values) {
            addCriterionForJDBCDate("getContractTime in", values, "getcontracttime");
            return (Criteria) this;
        }

        public Criteria andGetcontracttimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("getContractTime not in", values, "getcontracttime");
            return (Criteria) this;
        }

        public Criteria andGetcontracttimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("getContractTime between", value1, value2, "getcontracttime");
            return (Criteria) this;
        }

        public Criteria andGetcontracttimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("getContractTime not between", value1, value2, "getcontracttime");
            return (Criteria) this;
        }

        public Criteria andPutcontracttimeIsNull() {
            addCriterion("putContractTime is null");
            return (Criteria) this;
        }

        public Criteria andPutcontracttimeIsNotNull() {
            addCriterion("putContractTime is not null");
            return (Criteria) this;
        }

        public Criteria andPutcontracttimeEqualTo(Date value) {
            addCriterionForJDBCDate("putContractTime =", value, "putcontracttime");
            return (Criteria) this;
        }

        public Criteria andPutcontracttimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("putContractTime <>", value, "putcontracttime");
            return (Criteria) this;
        }

        public Criteria andPutcontracttimeGreaterThan(Date value) {
            addCriterionForJDBCDate("putContractTime >", value, "putcontracttime");
            return (Criteria) this;
        }

        public Criteria andPutcontracttimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("putContractTime >=", value, "putcontracttime");
            return (Criteria) this;
        }

        public Criteria andPutcontracttimeLessThan(Date value) {
            addCriterionForJDBCDate("putContractTime <", value, "putcontracttime");
            return (Criteria) this;
        }

        public Criteria andPutcontracttimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("putContractTime <=", value, "putcontracttime");
            return (Criteria) this;
        }

        public Criteria andPutcontracttimeIn(List<Date> values) {
            addCriterionForJDBCDate("putContractTime in", values, "putcontracttime");
            return (Criteria) this;
        }

        public Criteria andPutcontracttimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("putContractTime not in", values, "putcontracttime");
            return (Criteria) this;
        }

        public Criteria andPutcontracttimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("putContractTime between", value1, value2, "putcontracttime");
            return (Criteria) this;
        }

        public Criteria andPutcontracttimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("putContractTime not between", value1, value2, "putcontracttime");
            return (Criteria) this;
        }

        public Criteria andMaybememonyIsNull() {
            addCriterion("maybeMemony is null");
            return (Criteria) this;
        }

        public Criteria andMaybememonyIsNotNull() {
            addCriterion("maybeMemony is not null");
            return (Criteria) this;
        }

        public Criteria andMaybememonyEqualTo(Float value) {
            addCriterion("maybeMemony =", value, "maybememony");
            return (Criteria) this;
        }

        public Criteria andMaybememonyNotEqualTo(Float value) {
            addCriterion("maybeMemony <>", value, "maybememony");
            return (Criteria) this;
        }

        public Criteria andMaybememonyGreaterThan(Float value) {
            addCriterion("maybeMemony >", value, "maybememony");
            return (Criteria) this;
        }

        public Criteria andMaybememonyGreaterThanOrEqualTo(Float value) {
            addCriterion("maybeMemony >=", value, "maybememony");
            return (Criteria) this;
        }

        public Criteria andMaybememonyLessThan(Float value) {
            addCriterion("maybeMemony <", value, "maybememony");
            return (Criteria) this;
        }

        public Criteria andMaybememonyLessThanOrEqualTo(Float value) {
            addCriterion("maybeMemony <=", value, "maybememony");
            return (Criteria) this;
        }

        public Criteria andMaybememonyIn(List<Float> values) {
            addCriterion("maybeMemony in", values, "maybememony");
            return (Criteria) this;
        }

        public Criteria andMaybememonyNotIn(List<Float> values) {
            addCriterion("maybeMemony not in", values, "maybememony");
            return (Criteria) this;
        }

        public Criteria andMaybememonyBetween(Float value1, Float value2) {
            addCriterion("maybeMemony between", value1, value2, "maybememony");
            return (Criteria) this;
        }

        public Criteria andMaybememonyNotBetween(Float value1, Float value2) {
            addCriterion("maybeMemony not between", value1, value2, "maybememony");
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

        public Criteria andManagernameIsNull() {
            addCriterion("managerName is null");
            return (Criteria) this;
        }

        public Criteria andManagernameIsNotNull() {
            addCriterion("managerName is not null");
            return (Criteria) this;
        }

        public Criteria andManagernameEqualTo(String value) {
            addCriterion("managerName =", value, "managername");
            return (Criteria) this;
        }

        public Criteria andManagernameNotEqualTo(String value) {
            addCriterion("managerName <>", value, "managername");
            return (Criteria) this;
        }

        public Criteria andManagernameGreaterThan(String value) {
            addCriterion("managerName >", value, "managername");
            return (Criteria) this;
        }

        public Criteria andManagernameGreaterThanOrEqualTo(String value) {
            addCriterion("managerName >=", value, "managername");
            return (Criteria) this;
        }

        public Criteria andManagernameLessThan(String value) {
            addCriterion("managerName <", value, "managername");
            return (Criteria) this;
        }

        public Criteria andManagernameLessThanOrEqualTo(String value) {
            addCriterion("managerName <=", value, "managername");
            return (Criteria) this;
        }

        public Criteria andManagernameLike(String value) {
            addCriterion("managerName like", value, "managername");
            return (Criteria) this;
        }

        public Criteria andManagernameNotLike(String value) {
            addCriterion("managerName not like", value, "managername");
            return (Criteria) this;
        }

        public Criteria andManagernameIn(List<String> values) {
            addCriterion("managerName in", values, "managername");
            return (Criteria) this;
        }

        public Criteria andManagernameNotIn(List<String> values) {
            addCriterion("managerName not in", values, "managername");
            return (Criteria) this;
        }

        public Criteria andManagernameBetween(String value1, String value2) {
            addCriterion("managerName between", value1, value2, "managername");
            return (Criteria) this;
        }

        public Criteria andManagernameNotBetween(String value1, String value2) {
            addCriterion("managerName not between", value1, value2, "managername");
            return (Criteria) this;
        }

        public Criteria andInvertnameIsNull() {
            addCriterion("invertName is null");
            return (Criteria) this;
        }

        public Criteria andInvertnameIsNotNull() {
            addCriterion("invertName is not null");
            return (Criteria) this;
        }

        public Criteria andInvertnameEqualTo(String value) {
            addCriterion("invertName =", value, "invertname");
            return (Criteria) this;
        }

        public Criteria andInvertnameNotEqualTo(String value) {
            addCriterion("invertName <>", value, "invertname");
            return (Criteria) this;
        }

        public Criteria andInvertnameGreaterThan(String value) {
            addCriterion("invertName >", value, "invertname");
            return (Criteria) this;
        }

        public Criteria andInvertnameGreaterThanOrEqualTo(String value) {
            addCriterion("invertName >=", value, "invertname");
            return (Criteria) this;
        }

        public Criteria andInvertnameLessThan(String value) {
            addCriterion("invertName <", value, "invertname");
            return (Criteria) this;
        }

        public Criteria andInvertnameLessThanOrEqualTo(String value) {
            addCriterion("invertName <=", value, "invertname");
            return (Criteria) this;
        }

        public Criteria andInvertnameLike(String value) {
            addCriterion("invertName like", value, "invertname");
            return (Criteria) this;
        }

        public Criteria andInvertnameNotLike(String value) {
            addCriterion("invertName not like", value, "invertname");
            return (Criteria) this;
        }

        public Criteria andInvertnameIn(List<String> values) {
            addCriterion("invertName in", values, "invertname");
            return (Criteria) this;
        }

        public Criteria andInvertnameNotIn(List<String> values) {
            addCriterion("invertName not in", values, "invertname");
            return (Criteria) this;
        }

        public Criteria andInvertnameBetween(String value1, String value2) {
            addCriterion("invertName between", value1, value2, "invertname");
            return (Criteria) this;
        }

        public Criteria andInvertnameNotBetween(String value1, String value2) {
            addCriterion("invertName not between", value1, value2, "invertname");
            return (Criteria) this;
        }

        public Criteria andRongzinameIsNull() {
            addCriterion("rongziName is null");
            return (Criteria) this;
        }

        public Criteria andRongzinameIsNotNull() {
            addCriterion("rongziName is not null");
            return (Criteria) this;
        }

        public Criteria andRongzinameEqualTo(String value) {
            addCriterion("rongziName =", value, "rongziname");
            return (Criteria) this;
        }

        public Criteria andRongzinameNotEqualTo(String value) {
            addCriterion("rongziName <>", value, "rongziname");
            return (Criteria) this;
        }

        public Criteria andRongzinameGreaterThan(String value) {
            addCriterion("rongziName >", value, "rongziname");
            return (Criteria) this;
        }

        public Criteria andRongzinameGreaterThanOrEqualTo(String value) {
            addCriterion("rongziName >=", value, "rongziname");
            return (Criteria) this;
        }

        public Criteria andRongzinameLessThan(String value) {
            addCriterion("rongziName <", value, "rongziname");
            return (Criteria) this;
        }

        public Criteria andRongzinameLessThanOrEqualTo(String value) {
            addCriterion("rongziName <=", value, "rongziname");
            return (Criteria) this;
        }

        public Criteria andRongzinameLike(String value) {
            addCriterion("rongziName like", value, "rongziname");
            return (Criteria) this;
        }

        public Criteria andRongzinameNotLike(String value) {
            addCriterion("rongziName not like", value, "rongziname");
            return (Criteria) this;
        }

        public Criteria andRongzinameIn(List<String> values) {
            addCriterion("rongziName in", values, "rongziname");
            return (Criteria) this;
        }

        public Criteria andRongzinameNotIn(List<String> values) {
            addCriterion("rongziName not in", values, "rongziname");
            return (Criteria) this;
        }

        public Criteria andRongzinameBetween(String value1, String value2) {
            addCriterion("rongziName between", value1, value2, "rongziname");
            return (Criteria) this;
        }

        public Criteria andRongzinameNotBetween(String value1, String value2) {
            addCriterion("rongziName not between", value1, value2, "rongziname");
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