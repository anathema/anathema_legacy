package net.sf.anathema.character.equipment.impl.item.model.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.io.IOException;

public class IdentificateAdapter extends TypeAdapter<Identifier> {
  @Override
  public void write(JsonWriter out, Identifier value) throws IOException {
    out.value(value.getId());
  }

  @Override
  public Identifier read(JsonReader in) throws IOException {
    String id = in.nextString();
    return new SimpleIdentifier(id);
  }
}