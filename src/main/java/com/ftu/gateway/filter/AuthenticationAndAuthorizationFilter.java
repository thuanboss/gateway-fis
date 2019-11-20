package com.ftu.gateway.filter;

import com.ftu.gateway.common.JwtProvider;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationAndAuthorizationFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationAndAuthorizationFilter.class);

    private static final String API_TYPE = "3";

    @Autowired
    private JwtProvider tokenProvider;

    @Autowired


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.warn("Request Method: {}, Request URL: {}, Request URI: {}", request.getMethod(), request.getRequestURI(), request.getRequestURI());

//        if (isInWhiteList(request.getRequestURI())) {
//            return null;
//        }
//
//        log.warn("Validate JWT token for request: {}", request.getRequestURI());
//        String accessToken = getJwt(request);
//
//        if (accessToken == null) {
//            throw new ZuulRuntimeException(new ZuulException(ErrorCode.ERROR_100, 100, ErrorCode.ERROR_100));
//        }
//
//        if (!tokenProvider.validateTokenRSA(accessToken)) {
//            throw new ZuulRuntimeException(new ZuulException(ErrorCode.ERROR_100, 100, ErrorCode.ERROR_100));
//        }

        ctx.getResponse();

        return null;
    }

    private boolean isInWhiteList(String uri) {
        List<String> whiteList = new ArrayList<>();
        whiteList.add("/identity-service/api/auth/login");
        whiteList.add("/identity-service/api/auth/logout");

        for (String s : whiteList) {
            if (uri.equals(s))
                return true;
        }

        return false;
    }

    private String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }

        return null;
    }

//    private void setFailedRequest(String body, int code) {
//        log.debug("Reporting error ({}): {}", code, body);
//        RequestContext ctx = RequestContext.getCurrentContext();
//        ctx.setResponseStatusCode(code);
//        if (ctx.getResponseBody() == null) {
//            ctx.setResponseBody(body);
//            ctx.getResponse().setHeader("Content-Type", "text/plain;charset=UTF-8");
//            ctx.setSendZuulResponse(false);
//        }
//    }

//    private boolean userHasPermissionForURL(MobifoneUserCache mobifoneUserCache, String url) {
//        List<SiteMapData> siteMapDataList = mobifoneUserCache.getSiteMapDataList();
//        List<SiteMapData> siteMapDataAPIList = siteMapDataList.stream().filter(x -> x.getType().equals(API_TYPE)).collect(Collectors.toList());
//        boolean isAccess = false;
//        boolean flag = false;
//        for (SiteMapData s : siteMapDataAPIList) {
//            if (s.getItems().isEmpty()) {
//                isAccess = hasAccessUrl(s, url);
//                if (isAccess) {
//                    break;
//                }
//            } else {
//                for (SiteMapData sc : s.getItems()) {
//                    isAccess = hasAccessUrl(sc, url);
//                    if (isAccess) {
//                        flag = true;
//                        break;
//                    }
//                }
//            }
//            if(flag) {
//                break;
//            }
//        }
//        return isAccess;
//    }

//    private boolean hasAccessUrl(SiteMapData siteMapData, String url) {
//        if (siteMapData.getUrl() != null && !siteMapData.getUrl().isEmpty()) {
//            if (url.contains(siteMapData.getUrl())) {
//                return hasAccessPrivilege(siteMapData.getRights());
//            }
//        }
//        return false;
//    }

//    private boolean hasAccessPrivilege(List<String> rights) {
//        for (String s : rights) {
//            if ("E".equals(s))
//                return true;
//        }
//        return false;
//    }

//    private boolean checkRegexUri(String template, String url){
//        UriTemplate template1 = new UriTemplate(template + "/{var}");
//        return template1.matches(url);
//    }

//    private boolean hasAccessUrl(SiteMapData siteMapData, String url) {
//        if (siteMapData.getUrl() != null && !siteMapData.getUrl().isEmpty()) {
//            if (siteMapData.getUrl().equals(url)) {
//                return hasAccessPrivilege(siteMapData.getPrivileges());
//            }
//        }
//        return false;
//    }
//
//    private boolean hasAccessPrivilege(List<PrivilegeData> privilegeDataList) {
//        for (PrivilegeData p : privilegeDataList) {
//            if ("ACCESS".equals(p.getCode()))
//                return true;
//        }
//        return false;
//    }

//    private boolean isAccessPermission(Set<String> setGroupByUser, String path, String method){
//        String url = "http://access-management-service/api/authz/is-access";
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("Content-Type", "application/json");
//
//        JSONObject json = new JSONObject();
//        json.put("setGroupByUser", setGroupByUser);
//        json.put("path", path);
//        json.put("method", method);
//
//        HttpEntity<String> httpEntity = new HttpEntity<>(json.toString(), httpHeaders);
//
//        String response = restTemplate.postForObject(url, httpEntity, String.class);
//
//        JSONObject jsonObj = new JSONObject(response);
//        boolean result = (boolean) jsonObj.get("result");
//
//        return result;
//    }
}
