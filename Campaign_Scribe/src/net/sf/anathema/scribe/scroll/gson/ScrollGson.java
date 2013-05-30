package net.sf.anathema.scribe.scroll.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.scribe.scroll.persistence.RepositoryId;
import net.sf.anathema.scribe.scroll.persistence.Scroll;
import net.sf.anathema.scribe.scroll.persistence.ScrollDto;
import net.sf.anathema.scribe.scroll.persistence.SimpleRepositoryId;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ScrollGson {

  private Gson gson;

  public ScrollGson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setPrettyPrinting();
    gson = gsonBuilder.create();
  }

  public void save(Scroll scroll, OutputStream outputStream) {
    try {
      String json = toJson(scroll);
      outputStream.write(json.getBytes());
    } catch (IOException e) {
      throw new PersistenceException(e);
    }
  }

  public String toJson(Scroll scroll) {
    ScrollRepositoryItem item = new ScrollRepositoryItem();
    item.repositoryId = scroll.repositoryId.getStringRepresentation();
    item.title = scroll.dto.title;
    item.wikiText = scroll.dto.wikiText;
    return gson.toJson(item);
  }

  public Scroll load(InputStream inputStream) {
    try {
    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    IOUtils.copy(inputStream, byteStream);
    String json = new String(byteStream.toByteArray());
    return fromJson(json);
    } catch (IOException e) {
      throw  new PersistenceException(e);
    }
  }

  public Scroll fromJson(String json) {
    ScrollRepositoryItem item = gson.fromJson(json, ScrollRepositoryItem.class);
    ScrollDto dto = new ScrollDto(item.title, item.wikiText);
    RepositoryId repositoryId = new SimpleRepositoryId(item.repositoryId);
    return new Scroll(dto, repositoryId);
  }
}