package com.dimogo.open.myjobs.manager.admin.menu;

import org.springframework.batch.admin.web.resources.BaseMenu;

/**
 * Created by Auser on 2017/5/23.
 */
public class UserList extends BaseMenu{
    public UserList() {
        super("/url", "UserManager", 9);
    }
}
