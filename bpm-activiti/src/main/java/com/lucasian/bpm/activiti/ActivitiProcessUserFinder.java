/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucasian.bpm.activiti;

import com.lucasian.bpm.ProcessUserFinder;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.UserQuery;

/**
 *
 * @author Lucasian
 */
public class ActivitiProcessUserFinder implements ProcessUserFinder{

    public String findCurrentUser() {
        return org.activiti.engine.impl.identity.Authentication.getAuthenticatedUserId();
    }

    public boolean isUserAdmin(String userId) {
        UserQuery query = ProcessEngines.getDefaultProcessEngine().getIdentityService().createUserQuery();
        query.userId(userId);
        query.memberOfGroup("admin");
        return query.count()>0?true:false;
    }

    public boolean isUserValid(String userId) {
        UserQuery query = ProcessEngines.getDefaultProcessEngine().getIdentityService().createUserQuery();
        query.userId(userId);
        return query.count()>0?true:false;
    }
    
}
