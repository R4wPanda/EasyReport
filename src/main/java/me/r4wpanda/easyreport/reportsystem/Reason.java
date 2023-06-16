package me.r4wpanda.easyreport.reportsystem;

public enum Reason {
    CHAT_SPAMMING("Intentionally spamming the chat!"),
    CHAT_FLOODING("Intentionally flooding the chat!"),
    CHAT_CURSING("Cursing in chat (all slur words)"),
    CHAT_RACISM("Racist behavior in chat!"),
    CHAT_SEXISM("Sexist behavior in chat!"),
    CHAT_LEAKING("Leaking personal information of another player!"),
    ADVERTISEMENT_SERVER_IP("Advertising another server ip!"),
    ADVERTISEMENT_SERVER_WEBSITE("Advertising another server website!"),
    ADVERTISEMENT_SERVER_PLATFORM("Advertising another server name/platform!"),
    BEHAVIOR_DISRESPECT("Disrepectfull behavior towards other players!"),
    BEHAVIOR_ARGUE_WITH_STAFF("Arguing with staff!"),
    HACK_KILLAURA("Hackign with: KILLAURA"),
    HACK_ANTI_AFK("Hacking with: ANTI_AFK"),
    HACK_ANTI_KNOCKBACK("Hacking with: ANTI_KNOCKBACK"),
    HACK_AUTO_ARMOUR("Hacking with: AUTO_ARMOUR"),
    HACK_AUTO_EAT("Hacking with: AUTO_EAT"),
    HACK_AUTO_BUILD("Hacking with: AUTO_BUILD"),
    HACK_AUTO_LEAVE("Hacking with: AUTO_LEAVE"),
    HACK_AUTO_RESPAWN("Hacking with: AUTO_RESPAWN"),
    HACK_AUTO_SIGN("Hacking with: AUTO_SIGN"),
    HACK_AUTO_SOUP("Hacking with: AUTO_SOUP"),
    HACK_ANTI_SPAM("Hacking with: ANTI_SPAM"),
    HACK_AUTO_FARM("Hacking with: AUTO_FARM"),
    HACK_AUTO_FISH("Hacking with: AUTO_FISH"),
    HACK_AUTO_SPRINT("Hacking with: AUTO_SPRINT"),
    HACK_AUTO_STEAL("Hacking with: AUTO_STEAL"),
    HACK_AUTO_WALK("Hacking with: AUTO_WALK"),
    HACK_BASE_FINDER("Hacking with: BASE_FINDER"),
    HACK_CAMERA("Hacking with: CAMERA"),
    HACK_NO_CLIP("Hacking with: NO_CLIP"),
    HACK_CAVE_FINDER("Hacking with: CAVE_FINDER"),
    HACK_CHEST_ESP("Hacking with: CHEST_ESP"),
    HACK_CRASH_CHEST("Hacking with: CRASH_CHEST"),
    HACK_CRITICALS("Hacking with: CRITICALS"),
    HACK_DERP("Hacking with: DERP"),
    HACK_DOLPHIN("Hacking with: DOLPHIN"),
    HACK_EXCAVATOR("Hacking with: EXCAVATOR"),
    HACK_EXTRA_ELYTRA("Hacking with: EXTRA_ELYTRA"),
    HACK_FAST_LADDER("Hacking with: FAST_LADDER"),
    HACK_FAST_BREAK("Hacking with: FAST_BREAK"),
    HACK_FAST_PLACE("Hacking with: FAST_PLACE"),
    HACK_FIGHT_BOT("Hacking with: FIGHT_BOT"),
    HACK_FISH("Hacking with: FISH"),
    HACK_FLIGHT("Hacking with: FLIGHT"),
    HACK_FOLLOW("Hacking with: FOLLOW"),
    HACK_FREECAM("Hacking with: FREECAM"),
    HACK_HAND_NO_CLIP("Hacking with: HAND_NO_CLIP"),
    HACK_INSTANT_BUNKER("Hacking with: INSTANT_BUNKER"),
    HACK_ITEM_ESP("Hacking with: ITEM_ESP"),
    HACK_ITEM_GENERATOR("Hacking with: ITEM_GENERATOR"),
    HACK_JESUS("Hacking with: JESUS"),
    HACK_JETPACK("Hacking with: JETPACK");

    private final String text;

    Reason(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
