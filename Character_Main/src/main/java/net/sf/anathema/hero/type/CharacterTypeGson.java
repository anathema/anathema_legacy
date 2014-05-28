package net.sf.anathema.hero.type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.anathema.character.framework.type.CharacterType;
import org.apache.commons.io.IOUtils;

import java.net.URL;

public class CharacterTypeGson {
  private Gson gson;

  public CharacterTypeGson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setPrettyPrinting();
    gson = gsonBuilder.create();
  }

  public CharacterType fromJson(URL url) {
    try {
      String json = IOUtils.toString(url.openStream());
      return gson.fromJson(json, SimpleCharacterType.class);
    } catch (Exception e) {
      throw new RuntimeException("Error parsing: " + url.toExternalForm(), e);
    }
  }
}
