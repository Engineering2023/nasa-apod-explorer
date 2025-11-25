package com.example.apod.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApodResponse {
private String date;
private String explanation;
private String hdurl;
private String mediaType;
private String serviceVersion;
private String title;
private String url;
private String copyright;

@JsonProperty("media_type")
public void setMediaType(String mediaType) { this.mediaType = mediaType; }
public String getMediaType() { return mediaType; }

// getters and setters
public String getDate() { return date; }
public void setDate(String date) { this.date = date; }

public String getExplanation() { return explanation; }
public void setExplanation(String explanation) { this.explanation = explanation; }

public String getHdurl() { return hdurl; }
public void setHdurl(String hdurl) { this.hdurl = hdurl; }

@JsonProperty("service_version")
public void setServiceVersion(String serviceVersion) { this.serviceVersion = serviceVersion; }
public String getServiceVersion() { return serviceVersion; }

public String getTitle() { return title; }
public void setTitle(String title) { this.title = title; }

public String getUrl() { return url; }
public void setUrl(String url) { this.url = url; }

public String getCopyright() { return copyright; }
public void setCopyright(String copyright) { this.copyright = copyright; }
}
