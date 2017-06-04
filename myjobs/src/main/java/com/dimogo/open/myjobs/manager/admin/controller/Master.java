package com.dimogo.open.myjobs.manager.admin.controller;

import com.dimogo.open.myjobs.dto.MasterInfo;
import com.dimogo.open.myjobs.dto.User2DTO;
import com.dimogo.open.myjobs.dto.UserDTO;
import com.dimogo.open.myjobs.manager.admin.service.MyJobsService;
import com.dimogo.open.myjobs.types.UserRoleType;
import com.dimogo.open.myjobs.utils.AuthUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ModelAndViewMethodReturnValueHandler;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Ethan Xiao on 2017/4/19.
 */
@Controller
public class Master {
    @Resource(name = "clusteredJobService")
    private MyJobsService service;

    @RequestMapping(value = "/master", method = RequestMethod.GET)
    public String executors(ModelMap model,Authentication authentication, SecurityContextHolderAwareRequestWrapper request) {
        AuthUtils.setClusterAuthentication(request, model);
        MasterInfo masterInfo = service.findMaster();
        model.addAttribute("master", masterInfo);
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
                .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        String username= (String) authentication.getPrincipal();
        UserDTO user = service.findUser(username);
        String password = user.getPassword();
        List<User2DTO> user2DTOs = service.listUsers();
        UserRoleType[] userRoleTypes = UserRoleType.values();
        for (UserRoleType roleType : userRoleTypes) {
            System.out.println(roleType.name());
        }
        model.addAttribute("users",user2DTOs);
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("roles",userRoleTypes);

        return "master";
    }
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(@RequestParam String username,@RequestParam String password, SecurityContextHolderAwareRequestWrapper  request){
//            System.out.println(username);
//            System.out.println(password);
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
                .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        List<GrantedAuthority> GrantedAuthoritys = (List<GrantedAuthority>) securityContextImpl.getAuthentication().getAuthorities();
        List<String> roleList = new ArrayList<String>();
        for (GrantedAuthority grantedAuthority : GrantedAuthoritys) {
            roleList.add(grantedAuthority.toString());
        }
        service.updateUser(username,password, roleList);
        return "index";
    }
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@RequestParam String username,@RequestParam String password,@RequestParam String authority){
        List<String> roleList = new ArrayList<String>();
        roleList.add(authority);
        service.updateUser(username,password, roleList);
        return "index";
    }
//    @RequestMapping(value = "/updateUserBefore", method = RequestMethod.GET)
//    public String updateUserBefore( ModelMap model, SecurityContextHolderAwareRequestWrapper request , Authentication authentication) {
//        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
//                .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
//        String username= (String) authentication.getPrincipal();
//        UserDTO user = service.findUser(username);
//        String password = user.getPassword();
//        List<User2DTO> user2DTOs = service.listUsers();
//        model.addAttribute("users",user2DTOs);
//        model.addAttribute("username", username);
//        model.addAttribute("password", password);
//        return "update";
//    }
    @RequestMapping(value = "/updateOthersBefore/{username}", method = RequestMethod.GET)
    public String updateOthersBefore(@PathVariable String username, ModelMap model, SecurityContextHolderAwareRequestWrapper request , Authentication authentication) {
        User2DTO user2DTO = new User2DTO();
        UserDTO user = service.findUser(username);
        user2DTO.setRoles(user.getRoles().toString());
        user2DTO.setUsername(user.getUserName());
        user2DTO.setPassword(user.getPassword());
        UserRoleType[] userRoleTypes = UserRoleType.values();
        model.addAttribute("roles",userRoleTypes);
        model.addAttribute("user",user2DTO);
        return "update";
    }
    @RequestMapping(value = "/updateAuthority", method = RequestMethod.POST)
    public String updateAuthority(@RequestParam String username,@RequestParam String password,@RequestParam String authority, SecurityContextHolderAwareRequestWrapper  request){
        List<String> roleList = new ArrayList<String>();
        roleList.add(authority);
        service.updateUser(username,password, roleList);
        return "index";
    }
    public void setService(MyJobsService service) {
        this.service = service;
    }
}
