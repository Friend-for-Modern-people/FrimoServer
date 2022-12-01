package com.gachon.frimo;

import java.util.function.Consumer;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SshTunneling {

    private static JSch jsch = new JSch();

    private String url = "localhost";// "218.48.213.10";
    private String username = "root";
    private String password = "1234";
    private int port = 22; // ssh포트
    private int lport = 26016; // 외부포트
    private int rport = 27017; // 내부포트

    private Session session;

    public SshTunneling init(Consumer<Boolean> arg) {
        try {
            session = jsch.getSession(username, url, port);
            session.setPassword(password);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            session.setPortForwardingL(lport, "127.0.0.1", rport);
            arg.accept(true);
        } catch (Exception e) {
            arg.accept(false);
        }
        return this;
    }

    public void shutdown() throws Exception {
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }
}

// bean 생성 순서에 따른 에러가 날경우 아래의 코드 참고

// public class SshTunneling {

// private static JSch jsch = new JSch();

// private String url = "원격주소";
// private String username = "아이디";
// private String password = "비밀번호";
// private int port = 22; //ssh포트
// private int lport = 26016; //원격 접속 후 가상으로 포워딩할 포트
// private int rport = 27017; //실제 사용할 데이터베이스 포트

// private Session session;

// public SshTunneling init(Consumer<Boolean> arg) {
// try {
// session = jsch.getSession(username, url, port);
// session.setPassword(password);
// java.util.Properties config = new java.util.Properties();
// config.put("StrictHostKeyChecking", "no");
// session.setConfig(config);
// session.connect();
// session.setPortForwardingL(lport, "127.0.0.1", rport);
// arg.accept(true);
// } catch (Exception e) {
// arg.accept(false);
// }
// return this;
// }

// public void shutdown() throws Exception {
// if (session != null && session.isConnected()) {
// session.disconnect();
// }
// }
// }
