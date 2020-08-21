package com.gidp.sure3odds.filter;


import com.gidp.sure3odds.config.CachingHttpRequestWrapper;
import com.gidp.sure3odds.config.RequestFilterConfig;
import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.helper.FilterHelper;
import com.gidp.sure3odds.service.users.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RequestFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(RequestFilter.class);
    private RequestFilterConfig requestFilterConfig;
    private List<Pattern> whiteList = new ArrayList<>();
    private static final String AUTHENTICATION_SCHEME="Bearer ";
    private final AuthenticationService authenticationService;
    static final String ORIGIN = "Origin";

    public RequestFilter(RequestFilterConfig requestSignatureFilterConfig,AuthenticationService authenticationService) {
        this.requestFilterConfig = requestSignatureFilterConfig;
        this.authenticationService=authenticationService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // filter disabled
        if (requestFilterConfig.getDisabled()) {
            return true;
        }
        String uri = request.getRequestURI();
        return whiteList.parallelStream()
                .anyMatch(wl -> wl.matcher(uri).matches());
    }

    @Override
    protected void initFilterBean() throws ServletException {
        logger.info("Instantiating RequestSignature Filter");
        whiteList = FilterHelper.compileWhiteListPattern(requestFilterConfig.getWhitelist());
        logger.debug("Request Filter config: \n" + requestFilterConfig);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.debug("Running Request Filter");

        //get the request origin to add necessary headers to enable cors
        request.getHeader(ORIGIN);
        String origin = request.getHeader(ORIGIN);
        response.addHeader("Access-Control-Allow-Origin", origin);
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));

        //check if request is options (preflight) request, and send ok status
        if (request.getMethod().equalsIgnoreCase("OPTIONS") ) {
            response.setStatus(HttpStatus.OK.value());
            return;
        }


        String authorization = request.getHeader("authorization");
        
        if (StringUtils.isEmpty(authorization) ) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Missing required headers.");
            return;
        }
        
        CachingHttpRequestWrapper cachingRequestWrapper = new CachingHttpRequestWrapper(request);
        authorization= authorization.substring(AUTHENTICATION_SCHEME.length()).trim();
        
        //validate jwtoken 
        BaseResponse authRp =authenticationService.validateToken(authorization);
        if (authRp.getStatusCode()!=HttpStatus.OK.value()) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid authorization token");
            return;
        }


        String userid = authenticationService.getUserID(authorization);
        //check if the string is empty and return a response
        if (userid.equals("none")) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "could not process token");
            return;
        }

        long userId = Long.parseLong(userid);

//		// set the userId in the request so we can get it from the request
        request.setAttribute("UserID", userId);


        //otherwise everything went well, let's proceed with the request
        filterChain.doFilter(cachingRequestWrapper, response);

    }
}
