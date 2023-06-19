package com.jamigo.member.member_data.core;

import lombok.Data;

import java.io.Serializable;

@Data
public class Core implements Serializable {
    private static final long serialVersionUID = 1457755989409740329L;
    private boolean successful;
    private String message;
}
