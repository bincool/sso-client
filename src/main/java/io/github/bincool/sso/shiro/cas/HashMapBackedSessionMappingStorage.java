/**
 * Filename: HashMapBackedSessionMappingStorage.java
 * Package: io.github.bincool.sso.shiro.cas
 * Copyright: Wchy-bingV All Rights Reserved.QQ:891946049.
 * Description: HashMapBackedSessionMappingStorage.java
 * Author: wchy.
 * Date: 2018/6/17 20:59.
 * Content: 新增.
 * Version: V1.0.
 */
package io.github.bincool.sso.shiro.cas;

import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Description: 一句话描述该类.
 * </p>
 * <p>
 * 详细描述.
 * </p>
 * Author: wchy.
 * Since: 2018/6/17.20:59.
 */
public final class HashMapBackedSessionMappingStorage
{

    /**
     * Maps the ID from the CAS server to the Session ID.
     */
    private final Map<String,Serializable> MANAGED_SESSIONS_ID = new HashMap<String,Serializable>();

    public synchronized void addSessionById(String mappingId, Session session)
    {
        MANAGED_SESSIONS_ID.put(mappingId, session.getId());
    }

    public synchronized Serializable getSessionIDByMappingId(String mappingId)
    {
        return MANAGED_SESSIONS_ID.get(mappingId);
    }

}

