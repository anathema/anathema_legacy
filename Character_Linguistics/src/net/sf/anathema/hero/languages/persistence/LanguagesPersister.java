package net.sf.anathema.hero.languages.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.anathema.hero.languages.model.LanguagesModel;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.persistence.HeroModelPersistence;
import net.sf.anathema.hero.persistence.HeroModelPersister;
import net.sf.anathema.hero.persistence.HeroModelPersisterCollected;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.Identifier;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@HeroModelPersisterCollected
public class LanguagesPersister implements HeroModelPersister {

  private final String persistenceId = "languages";
  private final Gson gson;

  public LanguagesPersister() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setPrettyPrinting();
    gson = gsonBuilder.create();
  }

  @Override
  public Identifier getModelId() {
    return LanguagesModel.ID;
  }

  @Override
  public void load(HeroModel model, HeroModelPersistence persistence) throws PersistenceException {
    InputStream inputStream = null;
    try {
      inputStream = persistence.openInputStream(persistenceId);
      LanguagesPto pto = readFromJson(inputStream);
      fillModel((LanguagesModel) model, pto);
    } catch (IOException e) {
      throw new AnathemaException(e);
    } finally {
      IOUtils.closeQuietly(inputStream);
    }
  }

  private LanguagesPto readFromJson(InputStream inputStream) throws IOException {
    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    IOUtils.copy(inputStream, byteStream);
    String json = new String(byteStream.toByteArray());
    return gson.fromJson(json, LanguagesPto.class);
  }

  private void fillModel(LanguagesModel model, LanguagesPto pto) {
    for (String name : pto.languages) {
      Identifier language = model.getPredefinedLanguageById(name);
      if (language != null) {
        model.selectLanguage(language);
      } else {
        model.selectBarbarianLanguage(name);
      }
      model.commitSelection();
    }
  }

  @Override
  public void save(HeroModel heroModel, HeroModelPersistence persistence) {
    String json = fillPto((LanguagesModel) heroModel);
    writePersistence(persistence, json);
  }

  private String fillPto(LanguagesModel model) {
    LanguagesPto pto = new LanguagesPto();
    for (Identifier language : model.getEntries()) {
      pto.languages.add(language.getId());
    }
    return gson.toJson(pto);
  }

  private void writePersistence(HeroModelPersistence persistence, String json) {
    OutputStream outputStream = null;
    try {
      outputStream = persistence.openOutputStream(persistenceId);
      outputStream.write(json.getBytes());
    } catch (IOException e) {
      throw new AnathemaException(e);
    } finally {
      IOUtils.closeQuietly(outputStream);
    }
  }
}