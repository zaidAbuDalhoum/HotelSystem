package com.hotelSys.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.log4j.Logger;

public final class RequestWrapper extends HttpServletRequestWrapper {

  private static Logger logger = Logger.getLogger(RequestWrapper.class);

  public RequestWrapper(HttpServletRequest servletRequest) {
    super(servletRequest);
  }

  public String[] getParameterValues(String parameter) {
    logger.info("ParameterValues .. parameter .......");
    String[] values = super.getParameterValues(parameter);
    if (values == null) {
      return null;
    }
    int count = values.length;
    String[] encodedValues = new String[count];
    for (int i = 0; i < count; i++) {
      encodedValues[i] = cleanXSS(values[i]);
    }
    return encodedValues;
  }

  public String getParameter(String parameter) {
    logger.info("Parameter .. parameter .......");
    String value = super.getParameter(parameter);
    if (value == null) {
      return null;
    }
    logger.info("Parameter RequestWrapper ........ value .......");
    return cleanXSS(value);
  }

  public String getHeader(String name) {
    logger.info("Header .. parameter .......");
    String value = super.getHeader(name);
    if (value == null) {
      return null;
    }
    logger.info("Header RequestWrapper ........... value ....");
    return cleanXSS(value);
  }

  private String cleanXSS(String value) {
    // You'll need to remove the spaces from the html entities below
    logger.info("InXSS RequestWrapper ..............." + value);
    //value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
    //value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
    //value = value.replaceAll("'", "& #39;");
    value = value.replaceAll("eval\\((.*)\\)", "");
    value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");

    value = value.replaceAll("(?i)<script.*?>.*?<script.*?>", "");
    value = value.replaceAll("(?i)<script.*?>.*?</script.*?>", "");
    value = value.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "");
    value = value.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");
    //value = value.replaceAll("<script>", "");
    //value = value.replaceAll("</script>", "");
    logger.info("OutXSS RequestWrapper ........ value ......." + value);
    return value;
  }
}
