package io.wetalfrogggroup.game.see_battle.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import io.wetalfrogggroup.game.see_battle.util.ResourceReader;

import java.io.InputStream;

public class FirebaseApplication {

    public FirebaseApplication() throws Exception {
        final var projectId = "seabattlegame-994a8";
        final InputStream stream = ResourceReader.read("/privatekey.json")
                .orElseThrow(() -> new NoSuchFieldException("privatekey.json"));

        GoogleCredentials credentials = GoogleCredentials.fromStream(stream);
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .setProjectId(projectId)
                .build();
        FirebaseApp.initializeApp(options);
    }
}
