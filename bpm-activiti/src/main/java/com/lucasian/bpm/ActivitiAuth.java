/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucasian.bpm;

import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.UserQuery;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lucasian
 */

public class ActivitiAuth {
    public static void activitiLogin(){
        //activitiLogin(ProcessEngineFactory.findCurrentUser());
    }
    public static void activitiLogin(String userId){        
        UserQuery query = ProcessEngines.getDefaultProcessEngine().getIdentityService().createUserQuery();
        query.userId(userId);
        if(query.count()>0){
            ProcessEngines.getDefaultProcessEngine().getIdentityService().setAuthenticatedUserId(userId);
        }
    }
    public static void activitiLogin(String userId, String password){        
        boolean logged = ProcessEngines.getDefaultProcessEngine().getIdentityService().checkPassword(userId, password);
        if(logged){
            ProcessEngines.getDefaultProcessEngine().getIdentityService().setAuthenticatedUserId(userId);
        }        
    }
}
