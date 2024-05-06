package io.wetalfrogggroup.game.see_battle.firebase.document;

import io.wetalfrogggroup.game.see_battle.model.ShipDocument;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SessionDocument {

    private long timestamp;

    private String player1Id;
    private String player1Name;
    private List<ShipDocument> player1Ships;
    private List<String> player1Shots;

    private String player2Id;
    private String player2Name;
    private List<ShipDocument> player2Ships;
    private List<String> player2Shots;

}
