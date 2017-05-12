package com.dimogo.open.myjobs.manager.admin.menu;

import org.springframework.batch.admin.web.resources.BaseMenu;

/**
 * Created by ethanx on 2017/5/12.
 */
public class Logout extends BaseMenu {

	public Logout() {
		super("/logout", "Logout", 9);
	}

}
