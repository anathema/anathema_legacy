package net.sf.anathema.character.equipment.impl.item.model.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import java.io.IOException;

public class IdentificateAdapter extends TypeAdapter<IIdentificate> {
  @Override
  public void write(JsonWriter out, IIdentificate value) throws IOException {
    out.value(value.getId());
  }

  @Override
  public IIdentificate read(JsonReader in) throws IOException {
    String id = in.nextString();
    return new Identificate(id);
  }
}