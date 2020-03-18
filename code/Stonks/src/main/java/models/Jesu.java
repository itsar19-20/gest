package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "jesu")
public class Jesu {

	public Jesu() {
	}

	public Jesu(Date date, String authType, String characterEncoding, String contentType,
			String contextPath, String localAddr, String localName, String method, String pathInfo,
			String pathTranslated, String protocol, String queryString, String remoteAddr, String remoteHost,
			String remoteUser, String requestSessionId, String requestURI, String scheme, String serverName,
			String servletPath, String requestToString, Integer contentLength, Integer localPort, Integer remotePort,
			Integer serverPort, Integer requestHashCode, String attributeNames, String requestClass, String cookies,
			Integer responseBufferSize, String responseCharacterEncoding, String responseContentType,
			Integer responseStatus, Integer responseHashCode, String responseToString,
			Integer numberOfCharactersInTheResponseString) {
		super();
		this.date = date;
		this.authType = authType;
		this.characterEncoding = characterEncoding;
		this.contentType = contentType;
		this.contextPath = contextPath;
		this.localAddr = localAddr;
		this.localName = localName;
		this.method = method;
		this.pathInfo = pathInfo;
		this.pathTranslated = pathTranslated;
		this.protocol = protocol;
		this.queryString = queryString;
		this.remoteAddr = remoteAddr;
		this.remoteHost = remoteHost;
		this.remoteUser = remoteUser;
		this.requestSessionId = requestSessionId;
		this.requestURI = requestURI;
		this.scheme = scheme;
		this.serverName = serverName;
		this.servletPath = servletPath;
		this.requestToString = requestToString;
		this.contentLength = contentLength;
		this.localPort = localPort;
		this.remotePort = remotePort;
		this.serverPort = serverPort;
		this.requestHashCode = requestHashCode;
		this.attributeNames = attributeNames;
		this.requestClass = requestClass;
		this.cookies = cookies;
		this.responseBufferSize = responseBufferSize;
		this.responseCharacterEncoding = responseCharacterEncoding;
		this.responseContentType = responseContentType;
		this.responseStatus = responseStatus;
		this.responseHashCode = responseHashCode;
		this.responseToString = responseToString;
		this.numberOfCharactersInTheResponseString = numberOfCharactersInTheResponseString;
	}

	@Override
	public String toString() {
		return "Jesu [\nid=" + id + ", \ndate=" + date + ", \nauthType=" + authType + ", \ncharacterEncoding="
				+ characterEncoding + ", \ncontentType=" + contentType + ", \ncontextPath=" + contextPath + ", \nlocalAddr="
				+ localAddr + ", \nlocalName=" + localName + ", \nmethod=" + method + ", \npathInfo=" + pathInfo
				+ ", \npathTranslated=" + pathTranslated + ", \nprotocol=" + protocol + ", \nqueryString=" + queryString
				+ ", \nremoteAddr=" + remoteAddr + ", \nremoteHost=" + remoteHost + ", \nremoteUser=" + remoteUser
				+ ", \nrequestSessionId=" + requestSessionId + ", \nrequestURI=" + requestURI + ", \nscheme=" + scheme
				+ ", \nserverName=" + serverName + ", \nservletPath=" + servletPath + ", \nrequestToString=" + requestToString
				+ ", \ncontentLength=" + contentLength + ", \nlocalPort=" + localPort + ", \nremotePort=" + remotePort
				+ ", \nserverPort=" + serverPort + ", \nrequestHashCode=" + requestHashCode + ", \nattributeNames="
				+ attributeNames + ", \nrequestClass=" + requestClass + ", \ncookies=" + cookies + ", \nresponseBufferSize="
				+ responseBufferSize + ", \nresponseCharacterEncoding=" + responseCharacterEncoding
				+ ", \nresponseContentType=" + responseContentType + ", \nresponseStatus=" + responseStatus
				+ ", \nresponseHashCode=" + responseHashCode + ", \nresponseToString=" + responseToString
				+ ", \nnumberOfCharactersInTheResponseString=" + numberOfCharactersInTheResponseString + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@JsonProperty("date")
	private Date date;

	@JsonProperty("authType")
	private String authType;

	@JsonProperty("characterEncoding")
	private String characterEncoding;

	@JsonProperty("contentType")
	private String contentType;

	@JsonProperty("contextPath")
	private String contextPath;

	@JsonProperty("localAddr")
	private String localAddr;

	@JsonProperty("localName")
	private String localName;

	@JsonProperty("method")
	private String method;

	@JsonProperty("pathInfo")
	private String pathInfo;

	@JsonProperty("pathTranslated")
	private String pathTranslated;

	@JsonProperty("protocol")
	private String protocol;

	@JsonProperty("queryString")
	private String queryString;

	@JsonProperty("remoteAddr")
	private String remoteAddr;

	@JsonProperty("remoteHost")
	private String remoteHost;

	@JsonProperty("remoteUser")
	private String remoteUser;

	@JsonProperty("requestSessionId")
	private String requestSessionId;

	@JsonProperty("requestURI")
	private String requestURI;

	@JsonProperty("scheme")
	private String scheme;

	@JsonProperty("serverName")
	private String serverName;

	@JsonProperty("servletPath")
	private String servletPath;

	@JsonProperty("requestToString")
	private String requestToString;

	@JsonProperty("contentLength")
	private Integer contentLength;

	@JsonProperty("localPort")
	private Integer localPort;

	@JsonProperty("remotePort")
	private Integer remotePort;

	@JsonProperty("serverPort")
	private Integer serverPort;

	@JsonProperty("requestHashCode")
	private Integer requestHashCode;

	@JsonProperty("attributeNames")
	private String attributeNames;

	@JsonProperty("requestClass")
	private String requestClass;

	@JsonProperty("cookies")
	private String cookies;

	@JsonProperty("responseBufferSize")
	private Integer responseBufferSize;

	@JsonProperty("responseCharacterEncoding")
	private String responseCharacterEncoding;

	@JsonProperty("responseContentType")
	private String responseContentType;

	@JsonProperty("responseStatus")
	private Integer responseStatus;

	@JsonProperty("responseHashCode")
	private Integer responseHashCode;

	@JsonProperty("responseToString")
	private String responseToString;

	@JsonProperty("numberOfCharactersInTheResponseString")
	private Integer numberOfCharactersInTheResponseString;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public String getCharacterEncoding() {
		return characterEncoding;
	}

	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getLocalAddr() {
		return localAddr;
	}

	public void setLocalAddr(String localAddr) {
		this.localAddr = localAddr;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPathInfo() {
		return pathInfo;
	}

	public void setPathInfo(String pathInfo) {
		this.pathInfo = pathInfo;
	}

	public String getPathTranslated() {
		return pathTranslated;
	}

	public void setPathTranslated(String pathTranslated) {
		this.pathTranslated = pathTranslated;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public String getRemoteUser() {
		return remoteUser;
	}

	public void setRemoteUser(String remoteUser) {
		this.remoteUser = remoteUser;
	}

	public String getRequestSessionId() {
		return requestSessionId;
	}

	public void setRequestSessionId(String requestSessionId) {
		this.requestSessionId = requestSessionId;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServletPath() {
		return servletPath;
	}

	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}

	public String getRequestToString() {
		return requestToString;
	}

	public void setRequestToString(String requestToString) {
		this.requestToString = requestToString;
	}

	public Integer getContentLength() {
		return contentLength;
	}

	public void setContentLength(Integer contentLength) {
		this.contentLength = contentLength;
	}

	public Integer getLocalPort() {
		return localPort;
	}

	public void setLocalPort(Integer localPort) {
		this.localPort = localPort;
	}

	public Integer getRemotePort() {
		return remotePort;
	}

	public void setRemotePort(Integer remotePort) {
		this.remotePort = remotePort;
	}

	public Integer getServerPort() {
		return serverPort;
	}

	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}

	public Integer getRequestHashCode() {
		return requestHashCode;
	}

	public void setRequestHashCode(Integer requestHashCode) {
		this.requestHashCode = requestHashCode;
	}

	public String getAttributeNames() {
		return attributeNames;
	}

	public void setAttributeNames(String attributeNames) {
		this.attributeNames = attributeNames;
	}

	public String getRequestClass() {
		return requestClass;
	}

	public void setRequestClass(String requestClass) {
		this.requestClass = requestClass;
	}

	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

	public Integer getResponseBufferSize() {
		return responseBufferSize;
	}

	public void setResponseBufferSize(Integer responseBufferSize) {
		this.responseBufferSize = responseBufferSize;
	}

	public String getResponseCharacterEncoding() {
		return responseCharacterEncoding;
	}

	public void setResponseCharacterEncoding(String responseCharacterEncoding) {
		this.responseCharacterEncoding = responseCharacterEncoding;
	}

	public String getResponseContentType() {
		return responseContentType;
	}

	public void setResponseContentType(String responseContentType) {
		this.responseContentType = responseContentType;
	}

	public Integer getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(Integer responseStatus) {
		this.responseStatus = responseStatus;
	}

	public Integer getResponseHashCode() {
		return responseHashCode;
	}

	public void setResponseHashCode(Integer responseHashCode) {
		this.responseHashCode = responseHashCode;
	}

	public String getResponseToString() {
		return responseToString;
	}

	public void setResponseToString(String responseToString) {
		this.responseToString = responseToString;
	}

	public Integer getNumberOfCharactersInTheResponseString() {
		return numberOfCharactersInTheResponseString;
	}

	public void setNumberOfCharactersInTheResponseString(Integer numberOfCharactersInTheResponseString) {
		this.numberOfCharactersInTheResponseString = numberOfCharactersInTheResponseString;
	}

}
