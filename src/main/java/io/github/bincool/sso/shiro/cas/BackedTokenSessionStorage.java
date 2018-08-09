/**
 * Filename: BackedTokenSessionStorage.java
 * Package: io.github.bincool.sso.shiro.cas
 * Copyright: Wchy-bingV All Rights Reserved.QQ:891946049.
 * Description: BackedTokenSessionStorage.java
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
 * Description: 用HashMap存储cas server认证返回的token-sessionId.
 * </p>
 * <p>
 * 详细描述.
 * </p>
 * Author: wchy.
 * Since: 2018/6/17.20:59.
 */
public final class BackedTokenSessionStorage
{

    /**
     * Maps the ID from the CAS server to the Session ID.
     */
    private final Map<String, Serializable> MANAGED_SESSIONS_ID = new HashMap<>();

    /**
     * 增加sessionId.
     * @param token
     *      token.
     * @param session
     *      session.
     */
    public synchronized void addSessionById(String token, Session session)
    {
        MANAGED_SESSIONS_ID.put(token, session.getId());
    }

    /**
     *
     * @param token
     *      token.
     * @return
     */
    public synchronized Serializable getSessionIDByToken(String token)
    {
        return MANAGED_SESSIONS_ID.get(token);
    }

}

