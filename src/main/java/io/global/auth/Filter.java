package io.global.auth;

import io.domain.member.entity.Member;
import io.domain.member.service.MemberService;
import io.global.config.AccessUrlConfig;
import io.global.config.Container;
import io.global.util.UserRequest;
import java.util.Objects;

public class Filter {
    private final UserRequest userRequest;
    private final MemberService memberService;

    public Filter(UserRequest userRequest) {
        this.userRequest = userRequest;
        this.memberService = Container.getMemberService();
    }

    public boolean isValidRequest() {
        String originUrl = userRequest.getOriginUrl();

        UrlAuthType urlAuthType = sorting(originUrl);

        boolean isLogOn = userRequest.isLogon();
        if (urlAuthType == UrlAuthType.ANONYMOUS) {
            if (isLogOn) {
                return false;
            }
        } else if (urlAuthType == UrlAuthType.ADMIN) {
            if (!isLogOn) {
                return false;
            }

            Member findMember = memberService.findById(userRequest.getSession().getCurrentMemberId());

            return Objects.equals(findMember.getRole().getValue(), UrlAuthType.ADMIN.getValue());
        } else if (urlAuthType == UrlAuthType.HAS_AUTH) {
            if (!isLogOn) {
                return false;
            }
        }

        return true;
    }

    private UrlAuthType sorting(String url) {
        if (isAnonymous(url)) {
            return UrlAuthType.ANONYMOUS;
        }
        if (isAdmin(url)) {
            return UrlAuthType.ADMIN;
        }

        if (isNeedSignIn(url)) {
            return UrlAuthType.HAS_AUTH;
        }

        return UrlAuthType.PERMIT_ALL;
    }

    private boolean isNeedSignIn(String url) {
        String[] targetUrls = AccessUrlConfig.needSignInUrls;
        for (String target : targetUrls) {
            if (target.equals(url)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAdmin(String url) {
        String[] targetUrls = AccessUrlConfig.hasAdminAuthUrls;
        for (String target : targetUrls) {
            if (target.equals(url)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAnonymous(String url) {
        String[] targetUrls = AccessUrlConfig.anonymousUrls;
        for (String target : targetUrls) {
            if (target.equals(url)) {
                return true;
            }
        }
        return false;
    }
}
