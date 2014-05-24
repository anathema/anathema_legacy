package net.sf.anathema.hero.type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.anathema.character.framework.type.CharacterType;
import net.sf.anathema.hero.template.magic.FavoringTraitType;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;

public class CharacterTypeGson {
  private Gson gson;

  public CharacterTypeGson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(FavoringTraitType.class, new FavoringTraitTypeAdapter());
    gsonBuilder.setPrettyPrinting();
    gson = gsonBuilder.create();
  }

  public CharacterType fromJson(URL url) {
    try {
      String json = IOUtils.toString(url.openStream());
      return gson.fromJson(json, SimpleCharacterType.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
