package com.admin.spring.service.searching;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.admin.spring.service.UserDtoService;
import com.admin.spring.web.paging.InitialPagingSizes;
import com.admin.spring.web.paging.Pager;

@Service
public class UserSearchErrorResponse {

    private final UserDtoService userDtoService;

    public UserSearchErrorResponse(UserDtoService userDtoService) {
        this.userDtoService = userDtoService;
    }

    public ModelAndView respondToNumberFormatException(UserSearchResult userSearchResult, ModelAndView modelAndView) {
        Pager pager = new Pager(userSearchResult.getUserPage().getTotalPages(), userSearchResult.getUserPage().getNumber(),
                                InitialPagingSizes.BUTTONS_TO_SHOW, userSearchResult.getUserPage().getTotalElements());

        modelAndView.addObject("numberFormatException", true);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("users", userSearchResult.getUserPage());
        modelAndView.setViewName("adminPage/user/users");
        return modelAndView;
    }

    public ModelAndView respondToEmptySearchResult(ModelAndView modelAndView, PageRequest pageRequest) {
        modelAndView.addObject("noMatches", true);
        modelAndView.addObject("users", userDtoService.findAllPageable(pageRequest));
        return modelAndView;
    }
}
