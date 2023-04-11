package org.example;
import com.vdurmont.emoji.EmojiParser;

public enum Icon {
    // INFO: https://github.com/Coding/emoji-java


    WEATHER_MAIN(":triangular_flag_on_post:"),
    WEATHER_HUMIDITY(":droplet:"),
    CITY(":cityscape:"),

    RADIO(":radio_button:"),

    THERMOMETER(":thermometer:"),
    METRO(":metro:"),
    WEATHER_DESCRIPTION("noEmoji"),
    FEELS_LIKE_TEMP(":thinking:");

    //add emoji here (java emoji)

    private String value;

    public String get() {
        return EmojiParser.parseToUnicode(value);
    }

    public void setValue(String value) {
        this.value = value;
    }

    Icon(String value) {
        this.value = value;
    }
    }