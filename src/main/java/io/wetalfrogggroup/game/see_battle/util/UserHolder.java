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
        user = new User(generateUserId(), String.valueOf(System.currentTimeMillis()));
    }

    @SneakyThrows
    private String generateUserId() {
        // We use MAC address as a unique identifier for the user
        // MAC address are constant for each device
        InetAddress localHost = InetAddress.getLocalHost();
        NetworkInterface ni = NetworkInterface.getByInetAddress(localHost);
        byte[] hardwareAddress = ni.getHardwareAddress();

        // We hash the MAC address to avoid exposing it
        var md = MessageDigest.getInstance("SHA1");
        md.update(hardwareAddress);
        return HexFormat.of().formatHex(md.digest());
    }

    public User getUser() {
        return user;
    }
}
