package net.sf.anathema.hero.type;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.hero.template.magic.FavoringTraitType;

import java.io.IOException;
import java.util.Collection;

public class FavoringTraitTypeAdapter extends TypeAdapter<FavoringTraitType> {
  private final ObjectFactory objectFactory;

  public FavoringTraitTypeAdapter(ObjectFactory objectFactory) {
    this.objectFactory = objectFactory;
  }

  @Override
  public void write(JsonWriter out, FavoringTraitType value) throws IOException {
    //nothing to do
  }

  @Override
  public FavoringTraitType read(JsonReader in) throws IOException {
    String string = in.nextString();
    Collection<FavoringTraitType> types = objectFactory.instantiateAllImplementers(FavoringTraitType.class);
    for (FavoringTraitType type : types) {
      if (type.getId().equals(string)) {
        return type;
      }
    }
    throw new IllegalArgumentException("Unknown trait type: " + string);
  }
}
