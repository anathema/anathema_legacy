package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonTagLoader {
  public List<Tag> loadFrom(String json) {
    Type type = new TypeToken<List<Tag>>() {
    }.getType();
    return new Gson().fromJson(json, type);
  }
}
