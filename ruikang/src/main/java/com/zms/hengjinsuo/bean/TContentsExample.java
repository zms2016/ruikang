package com.zms.hengjinsuo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TContentsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TContentsExample() {
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

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Date value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Date value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Date value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Date value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Date value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Date> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Date> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Date value1, Date value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Date value1, Date value2) {
            addCriterion("time not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTypeidIsNull() {
            addCriterion("typeId is null");
            return (Criteria) this;
        }

        public Criteria andTypeidIsNotNull() {
            addCriterion("typeId is not null");
            return (Criteria) this;
        }

        public Criteria andTypeidEqualTo(Integer value) {
            addCriterion("typeId =", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotEqualTo(Integer value) {
            addCriterion("typeId <>", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidGreaterThan(Integer value) {
            addCriterion("typeId >", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("typeId >=", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidLessThan(Integer value) {
            addCriterion("typeId <", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidLessThanOrEqualTo(Integer value) {
            addCriterion("typeId <=", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidIn(List<Integer> values) {
            addCriterion("typeId in", values, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotIn(List<Integer> values) {
            addCriterion("typeId not in", values, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidBetween(Integer value1, Integer value2) {
            addCriterion("typeId between", value1, value2, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotBetween(Integer value1, Integer value2) {
            addCriterion("typeId not between", value1, value2, "typeid");
            return (Criteria) this;
        }

        public Criteria andFiletitleIsNull() {
            addCriterion("fileTitle is null");
            return (Criteria) this;
        }

        public Criteria andFiletitleIsNotNull() {
            addCriterion("fileTitle is not null");
            return (Criteria) this;
        }

        public Criteria andFiletitleEqualTo(String value) {
            addCriterion("fileTitle =", value, "filetitle");
            return (Criteria) this;
        }

        public Criteria andFiletitleNotEqualTo(String value) {
            addCriterion("fileTitle <>", value, "filetitle");
            return (Criteria) this;
        }

        public Criteria andFiletitleGreaterThan(String value) {
            addCriterion("fileTitle >", value, "filetitle");
            return (Criteria) this;
        }

        public Criteria andFiletitleGreaterThanOrEqualTo(String value) {
            addCriterion("fileTitle >=", value, "filetitle");
            return (Criteria) this;
        }

        public Criteria andFiletitleLessThan(String value) {
            addCriterion("fileTitle <", value, "filetitle");
            return (Criteria) this;
        }

        public Criteria andFiletitleLessThanOrEqualTo(String value) {
            addCriterion("fileTitle <=", value, "filetitle");
            return (Criteria) this;
        }

        public Criteria andFiletitleLike(String value) {
            addCriterion("fileTitle like", value, "filetitle");
            return (Criteria) this;
        }

        public Criteria andFiletitleNotLike(String value) {
            addCriterion("fileTitle not like", value, "filetitle");
            return (Criteria) this;
        }

        public Criteria andFiletitleIn(List<String> values) {
            addCriterion("fileTitle in", values, "filetitle");
            return (Criteria) this;
        }

        public Criteria andFiletitleNotIn(List<String> values) {
            addCriterion("fileTitle not in", values, "filetitle");
            return (Criteria) this;
        }

        public Criteria andFiletitleBetween(String value1, String value2) {
            addCriterion("fileTitle between", value1, value2, "filetitle");
            return (Criteria) this;
        }

        public Criteria andFiletitleNotBetween(String value1, String value2) {
            addCriterion("fileTitle not between", value1, value2, "filetitle");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andKeywordsIsNull() {
            addCriterion("keywords is null");
            return (Criteria) this;
        }

        public Criteria andKeywordsIsNotNull() {
            addCriterion("keywords is not null");
            return (Criteria) this;
        }

        public Criteria andKeywordsEqualTo(String value) {
            addCriterion("keywords =", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotEqualTo(String value) {
            addCriterion("keywords <>", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsGreaterThan(String value) {
            addCriterion("keywords >", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsGreaterThanOrEqualTo(String value) {
            addCriterion("keywords >=", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLessThan(String value) {
            addCriterion("keywords <", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLessThanOrEqualTo(String value) {
            addCriterion("keywords <=", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLike(String value) {
            addCriterion("keywords like", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotLike(String value) {
            addCriterion("keywords not like", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsIn(List<String> values) {
            addCriterion("keywords in", values, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotIn(List<String> values) {
            addCriterion("keywords not in", values, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsBetween(String value1, String value2) {
            addCriterion("keywords between", value1, value2, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotBetween(String value1, String value2) {
            addCriterion("keywords not between", value1, value2, "keywords");
            return (Criteria) this;
        }

        public Criteria andReadcountIsNull() {
            addCriterion("readCount is null");
            return (Criteria) this;
        }

        public Criteria andReadcountIsNotNull() {
            addCriterion("readCount is not null");
            return (Criteria) this;
        }

        public Criteria andReadcountEqualTo(Integer value) {
            addCriterion("readCount =", value, "readcount");
            return (Criteria) this;
        }

        public Criteria andReadcountNotEqualTo(Integer value) {
            addCriterion("readCount <>", value, "readcount");
            return (Criteria) this;
        }

        public Criteria andReadcountGreaterThan(Integer value) {
            addCriterion("readCount >", value, "readcount");
            return (Criteria) this;
        }

        public Criteria andReadcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("readCount >=", value, "readcount");
            return (Criteria) this;
        }

        public Criteria andReadcountLessThan(Integer value) {
            addCriterion("readCount <", value, "readcount");
            return (Criteria) this;
        }

        public Criteria andReadcountLessThanOrEqualTo(Integer value) {
            addCriterion("readCount <=", value, "readcount");
            return (Criteria) this;
        }

        public Criteria andReadcountIn(List<Integer> values) {
            addCriterion("readCount in", values, "readcount");
            return (Criteria) this;
        }

        public Criteria andReadcountNotIn(List<Integer> values) {
            addCriterion("readCount not in", values, "readcount");
            return (Criteria) this;
        }

        public Criteria andReadcountBetween(Integer value1, Integer value2) {
            addCriterion("readCount between", value1, value2, "readcount");
            return (Criteria) this;
        }

        public Criteria andReadcountNotBetween(Integer value1, Integer value2) {
            addCriterion("readCount not between", value1, value2, "readcount");
            return (Criteria) this;
        }

        public Criteria andImagefilenameIsNull() {
            addCriterion("imageFileName is null");
            return (Criteria) this;
        }

        public Criteria andImagefilenameIsNotNull() {
            addCriterion("imageFileName is not null");
            return (Criteria) this;
        }

        public Criteria andImagefilenameEqualTo(String value) {
            addCriterion("imageFileName =", value, "imagefilename");
            return (Criteria) this;
        }

        public Criteria andImagefilenameNotEqualTo(String value) {
            addCriterion("imageFileName <>", value, "imagefilename");
            return (Criteria) this;
        }

        public Criteria andImagefilenameGreaterThan(String value) {
            addCriterion("imageFileName >", value, "imagefilename");
            return (Criteria) this;
        }

        public Criteria andImagefilenameGreaterThanOrEqualTo(String value) {
            addCriterion("imageFileName >=", value, "imagefilename");
            return (Criteria) this;
        }

        public Criteria andImagefilenameLessThan(String value) {
            addCriterion("imageFileName <", value, "imagefilename");
            return (Criteria) this;
        }

        public Criteria andImagefilenameLessThanOrEqualTo(String value) {
            addCriterion("imageFileName <=", value, "imagefilename");
            return (Criteria) this;
        }

        public Criteria andImagefilenameLike(String value) {
            addCriterion("imageFileName like", value, "imagefilename");
            return (Criteria) this;
        }

        public Criteria andImagefilenameNotLike(String value) {
            addCriterion("imageFileName not like", value, "imagefilename");
            return (Criteria) this;
        }

        public Criteria andImagefilenameIn(List<String> values) {
            addCriterion("imageFileName in", values, "imagefilename");
            return (Criteria) this;
        }

        public Criteria andImagefilenameNotIn(List<String> values) {
            addCriterion("imageFileName not in", values, "imagefilename");
            return (Criteria) this;
        }

        public Criteria andImagefilenameBetween(String value1, String value2) {
            addCriterion("imageFileName between", value1, value2, "imagefilename");
            return (Criteria) this;
        }

        public Criteria andImagefilenameNotBetween(String value1, String value2) {
            addCriterion("imageFileName not between", value1, value2, "imagefilename");
            return (Criteria) this;
        }

        public Criteria andStartshowtimeIsNull() {
            addCriterion("startShowTime is null");
            return (Criteria) this;
        }

        public Criteria andStartshowtimeIsNotNull() {
            addCriterion("startShowTime is not null");
            return (Criteria) this;
        }

        public Criteria andStartshowtimeEqualTo(Date value) {
            addCriterion("startShowTime =", value, "startshowtime");
            return (Criteria) this;
        }

        public Criteria andStartshowtimeNotEqualTo(Date value) {
            addCriterion("startShowTime <>", value, "startshowtime");
            return (Criteria) this;
        }

        public Criteria andStartshowtimeGreaterThan(Date value) {
            addCriterion("startShowTime >", value, "startshowtime");
            return (Criteria) this;
        }

        public Criteria andStartshowtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("startShowTime >=", value, "startshowtime");
            return (Criteria) this;
        }

        public Criteria andStartshowtimeLessThan(Date value) {
            addCriterion("startShowTime <", value, "startshowtime");
            return (Criteria) this;
        }

        public Criteria andStartshowtimeLessThanOrEqualTo(Date value) {
            addCriterion("startShowTime <=", value, "startshowtime");
            return (Criteria) this;
        }

        public Criteria andStartshowtimeIn(List<Date> values) {
            addCriterion("startShowTime in", values, "startshowtime");
            return (Criteria) this;
        }

        public Criteria andStartshowtimeNotIn(List<Date> values) {
            addCriterion("startShowTime not in", values, "startshowtime");
            return (Criteria) this;
        }

        public Criteria andStartshowtimeBetween(Date value1, Date value2) {
            addCriterion("startShowTime between", value1, value2, "startshowtime");
            return (Criteria) this;
        }

        public Criteria andStartshowtimeNotBetween(Date value1, Date value2) {
            addCriterion("startShowTime not between", value1, value2, "startshowtime");
            return (Criteria) this;
        }

        public Criteria andSupportIsNull() {
            addCriterion("support is null");
            return (Criteria) this;
        }

        public Criteria andSupportIsNotNull() {
            addCriterion("support is not null");
            return (Criteria) this;
        }

        public Criteria andSupportEqualTo(Integer value) {
            addCriterion("support =", value, "support");
            return (Criteria) this;
        }

        public Criteria andSupportNotEqualTo(Integer value) {
            addCriterion("support <>", value, "support");
            return (Criteria) this;
        }

        public Criteria andSupportGreaterThan(Integer value) {
            addCriterion("support >", value, "support");
            return (Criteria) this;
        }

        public Criteria andSupportGreaterThanOrEqualTo(Integer value) {
            addCriterion("support >=", value, "support");
            return (Criteria) this;
        }

        public Criteria andSupportLessThan(Integer value) {
            addCriterion("support <", value, "support");
            return (Criteria) this;
        }

        public Criteria andSupportLessThanOrEqualTo(Integer value) {
            addCriterion("support <=", value, "support");
            return (Criteria) this;
        }

        public Criteria andSupportIn(List<Integer> values) {
            addCriterion("support in", values, "support");
            return (Criteria) this;
        }

        public Criteria andSupportNotIn(List<Integer> values) {
            addCriterion("support not in", values, "support");
            return (Criteria) this;
        }

        public Criteria andSupportBetween(Integer value1, Integer value2) {
            addCriterion("support between", value1, value2, "support");
            return (Criteria) this;
        }

        public Criteria andSupportNotBetween(Integer value1, Integer value2) {
            addCriterion("support not between", value1, value2, "support");
            return (Criteria) this;
        }

        public Criteria andOppositionIsNull() {
            addCriterion("opposition is null");
            return (Criteria) this;
        }

        public Criteria andOppositionIsNotNull() {
            addCriterion("opposition is not null");
            return (Criteria) this;
        }

        public Criteria andOppositionEqualTo(Integer value) {
            addCriterion("opposition =", value, "opposition");
            return (Criteria) this;
        }

        public Criteria andOppositionNotEqualTo(Integer value) {
            addCriterion("opposition <>", value, "opposition");
            return (Criteria) this;
        }

        public Criteria andOppositionGreaterThan(Integer value) {
            addCriterion("opposition >", value, "opposition");
            return (Criteria) this;
        }

        public Criteria andOppositionGreaterThanOrEqualTo(Integer value) {
            addCriterion("opposition >=", value, "opposition");
            return (Criteria) this;
        }

        public Criteria andOppositionLessThan(Integer value) {
            addCriterion("opposition <", value, "opposition");
            return (Criteria) this;
        }

        public Criteria andOppositionLessThanOrEqualTo(Integer value) {
            addCriterion("opposition <=", value, "opposition");
            return (Criteria) this;
        }

        public Criteria andOppositionIn(List<Integer> values) {
            addCriterion("opposition in", values, "opposition");
            return (Criteria) this;
        }

        public Criteria andOppositionNotIn(List<Integer> values) {
            addCriterion("opposition not in", values, "opposition");
            return (Criteria) this;
        }

        public Criteria andOppositionBetween(Integer value1, Integer value2) {
            addCriterion("opposition between", value1, value2, "opposition");
            return (Criteria) this;
        }

        public Criteria andOppositionNotBetween(Integer value1, Integer value2) {
            addCriterion("opposition not between", value1, value2, "opposition");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNull() {
            addCriterion("orderid is null");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNotNull() {
            addCriterion("orderid is not null");
            return (Criteria) this;
        }

        public Criteria andOrderidEqualTo(Integer value) {
            addCriterion("orderid =", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotEqualTo(Integer value) {
            addCriterion("orderid <>", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThan(Integer value) {
            addCriterion("orderid >", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThanOrEqualTo(Integer value) {
            addCriterion("orderid >=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThan(Integer value) {
            addCriterion("orderid <", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThanOrEqualTo(Integer value) {
            addCriterion("orderid <=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidIn(List<Integer> values) {
            addCriterion("orderid in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotIn(List<Integer> values) {
            addCriterion("orderid not in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidBetween(Integer value1, Integer value2) {
            addCriterion("orderid between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotBetween(Integer value1, Integer value2) {
            addCriterion("orderid not between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andIsshowIsNull() {
            addCriterion("isShow is null");
            return (Criteria) this;
        }

        public Criteria andIsshowIsNotNull() {
            addCriterion("isShow is not null");
            return (Criteria) this;
        }

        public Criteria andIsshowEqualTo(Boolean value) {
            addCriterion("isShow =", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowNotEqualTo(Boolean value) {
            addCriterion("isShow <>", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowGreaterThan(Boolean value) {
            addCriterion("isShow >", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isShow >=", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowLessThan(Boolean value) {
            addCriterion("isShow <", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowLessThanOrEqualTo(Boolean value) {
            addCriterion("isShow <=", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowIn(List<Boolean> values) {
            addCriterion("isShow in", values, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowNotIn(List<Boolean> values) {
            addCriterion("isShow not in", values, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowBetween(Boolean value1, Boolean value2) {
            addCriterion("isShow between", value1, value2, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isShow not between", value1, value2, "isshow");
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