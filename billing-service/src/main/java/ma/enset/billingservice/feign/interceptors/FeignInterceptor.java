package ma.enset.billingservice.feign.interceptors;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        var context = SecurityContextHolder.getContext();
        var authentication = context.getAuthentication();
        if (authentication == null) return;
        var oAuth2AuthenticationToken = (JwtAuthenticationToken) authentication;
        var jwt = oAuth2AuthenticationToken.getToken();
        requestTemplate.header("Authorization", "Bearer " + jwt.getTokenValue());
    }
}
