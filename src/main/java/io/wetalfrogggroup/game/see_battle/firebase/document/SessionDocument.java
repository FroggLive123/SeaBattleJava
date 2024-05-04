package io.wetalfrogggroup.game.see_battle.firebase.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SessionDocument {

    private long timestamp;

    private String player1Id;
    private String player1Name;

    private String player2Id;
    private String player2Name;

}
