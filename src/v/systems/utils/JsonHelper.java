package v.systems.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

public class JsonHelper {
    private static Gson gson = null;
    private static JsonParser parser = null;

    public static Gson getGsonInstance() {
        if (gson == null) {
            gson = new GsonBuilder().create();
        }
        return gson;
    }

    public static JsonParser getParserInstance() {
        if (parser == null) {
            parser = new JsonParser();
        }
        return parser;
    }
}
