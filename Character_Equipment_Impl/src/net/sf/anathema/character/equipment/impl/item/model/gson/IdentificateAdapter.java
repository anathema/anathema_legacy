package net.sf.anathema.character.equipment.impl.item.model.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.sf.anathema.lib.util.Identificate;
import net.sf.anathema.lib.util.Identified;

import java.io.IOException;

public class IdentificateAdapter extends TypeAdapter<Identified> {
  @Override
  public void write(JsonWriter out, Identified value) throws IOException {
    out.value(value.getId());
  }

  @Override
  public Identified read(JsonReader in) throws IOException {
    String id = in.nextString();
    return new Identificate(id);
  }
}