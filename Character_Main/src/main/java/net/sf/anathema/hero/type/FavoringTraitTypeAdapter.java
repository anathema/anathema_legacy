package net.sf.anathema.hero.type;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.sf.anathema.hero.template.magic.AbilityFavoringType;
import net.sf.anathema.hero.template.magic.AttributeFavoringType;
import net.sf.anathema.hero.template.magic.FavoringTraitType;

import java.io.IOException;

public class FavoringTraitTypeAdapter extends TypeAdapter<FavoringTraitType> {
  @Override
  public void write(JsonWriter out, FavoringTraitType value) throws IOException {
    //nothing to do
  }

  @Override
  public FavoringTraitType read(JsonReader in) throws IOException {
    String string = in.nextString();
    if (string.equals("Ability")) {
      return new AbilityFavoringType();
    }
    if (string.equals("Attribute")) {
      return new AttributeFavoringType();
    }
    throw new IllegalArgumentException("Unknown trait type: " + string);
  }
}
