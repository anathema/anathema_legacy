package net.sf.anathema.character.equipment.impl.item.model.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.sf.anathema.character.equipment.item.model.ICollectionFactory;

import java.lang.reflect.Type;

public class CollectionFactoryFactory implements JsonDeserializer<ICollectionFactory> {
  @Override
  public ICollectionFactory deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    return new GsonCollectionFactory();
  }
}