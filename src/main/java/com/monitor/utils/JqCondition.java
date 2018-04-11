package com.monitor.utils;


import com.monitor.exceptions.JqQueryValueFormatException;

public class JqCondition {

	private String operator;
	private String value;
	private String datatype;
	private String key;

	public String getOperator() {
		if (StringTool.isNullOrEmpty(this.operator))
			return "";
		if (this.operator.equals("bw")) {
			return "";
		} else if ("eq".equalsIgnoreCase(this.operator)) {
			return "=";
		} else if ("~".equalsIgnoreCase(this.operator)) {
			return "";
		} else if ("=".equalsIgnoreCase(this.operator)) {
			return "like";
		}
		if (StringTool.isNullOrEmpty(this.operator)) {
			if ("string".equalsIgnoreCase(this.getDatatype()) || "text".equalsIgnoreCase(this.getDatatype())) {
				return "like";
			} else if ("boolean".equalsIgnoreCase(this.getDatatype())) {
				return "=";
			}
		}
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getValue() {

		// 如果操作符为bw，并且
		if ("bw".equals(operator)) {
			String[] arrValue = this.value.split("-");
			if (arrValue.length == 2) {
				if (this.datatype.equals("date")) {
					arrValue[0] = arrValue[0] + " 00:00:00";
					arrValue[1] = arrValue[1] + " 23:59:59";
				}

				return ">='" + arrValue[0] + "' and " + this.key + " <= '" + arrValue[1] + "'";
			} else if (arrValue.length == 1) {
				if ("date".equalsIgnoreCase(this.datatype)) {
					return ">='" + this.value + " 00:00:00' and " + this.key + " <= '" + this.value + " 23:59:59'";
				} else {
					throw new JqQueryValueFormatException(key + "字段格式错误，值" + this.value);
				}
			} else
				throw new JqQueryValueFormatException(key + "字段格式错误，值" + this.value);
		} else if ("~".equalsIgnoreCase(operator)) {
			String[] arrValue = this.value.split("-");
			if (arrValue.length == 2) {

				return ">='" + arrValue[0] + "' and " + this.key + " <= '" + arrValue[1] + "'";
			} else {
				throw new JqQueryValueFormatException(key + "字段格式错误，值" + this.value);
			}
		} else if ("string".equalsIgnoreCase(this.getDatatype()) || "text".equalsIgnoreCase(this.getDatatype())) {
			if ("eq".equalsIgnoreCase(this.operator)) {
				return "'" + value + "'";
			} else if (StringTool.isNullOrEmpty(operator)) {
				return "'%" + value + "%'";
			} else if ("like".equals(operator)) {
				return "'%" + value + "%'";
			} else if ("=".equals(operator)) {
				return "'%" + value + "%'";
			} else {
				return "'" + value + "'";
			}
		} else if ("ip".equalsIgnoreCase(this.getDatatype())) {
			return "'" + value + "'";
		} else if ("mac".equalsIgnoreCase(this.getDatatype())) {
			return "'" + value + "'";
		} else
			return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
