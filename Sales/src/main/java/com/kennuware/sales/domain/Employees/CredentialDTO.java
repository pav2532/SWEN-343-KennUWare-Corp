package com.kennuware.sales.domain.Employees;

/**
 * Created by rywils21 on 12/3/2016.
 */
public class CredentialDTO {

    private String username;
    private String sessionID;

    public CredentialDTO(String username, String sessionID) {
        this.username = username;
        this.sessionID = sessionID;
    }
}
