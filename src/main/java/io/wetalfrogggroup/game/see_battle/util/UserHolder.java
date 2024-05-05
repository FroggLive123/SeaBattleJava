package io.wetalfrogggroup.game.see_battle.util;

import io.wetalfrogggroup.game.see_battle.model.Session;
import io.wetalfrogggroup.game.see_battle.model.User;
import lombok.SneakyThrows;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.UUID;

public class UserHolder {

    private static UserHolder instance;

    public static UserHolder getInstance() {
        if (instance == null) {
            instance = new UserHolder();
        }
        return instance;
    }

    private User user;

    @SneakyThrows
    public UserHolder() {
        InetAddress localHost = InetAddress.getLocalHost();
        NetworkInterface ni = NetworkInterface.getByInetAddress(localHost);
        byte[] hardwareAddress = ni.getHardwareAddress();

        var md = MessageDigest.getInstance("SHA1");
        md.update(hardwareAddress);
        var id = HexFormat.of().formatHex(md.digest());

        user = new User(id, String.valueOf(System.currentTimeMillis()));
    }

    public User getUser() {
        return user;
    }
}
