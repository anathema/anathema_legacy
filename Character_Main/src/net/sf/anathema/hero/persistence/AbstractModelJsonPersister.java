package net.sf.anathema.hero.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.exception.PersistenceException;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class AbstractModelJsonPersister<P, M extends HeroModel> implements HeroModelPersister {

  private final String persistenceId;
  private final Gson gson;
  private Class<P> ptoClass;

  public AbstractModelJsonPersister(String persistenceId, Class<P> ptoClass) {
    this.persistenceId = persistenceId;
    this.ptoClass = ptoClass;
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setPrettyPrinting();
    gson = gsonBuilder.create();
  }

  @Override
  public final void load(HeroModel model, HeroModelLoader loader) throws PersistenceException {
    P pto = loadPto(loader);
    if (pto == null) {
      return;
    }
    fillModel((M) model, pto);
  }

  private P loadPto(HeroModelLoader loader) {
    InputStream inputStream = null;
    try {
      inputStream = loader.openInputStream(persistenceId);
      if (inputStream != null) {
        return readFromJson(inputStream);
      }
    } catch (IOException e) {
      throw new AnathemaException(e);
    } finally {
      IOUtils.closeQuietly(inputStream);
    }
    return null;
  }

  private P readFromJson(InputStream inputStream) throws IOException {
    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    IOUtils.copy(inputStream, byteStream);
    String json = new String(byteStream.toByteArray());
    return gson.fromJson(json, ptoClass);
  }

  protected abstract void fillModel(M model, P pto);

  @Override
  public final void save(HeroModel heroModel, HeroModelSaver saver) {
    P pto = createPto((M) heroModel);
    String json = gson.toJson(pto);
    writePersistence(saver, json);
  }

  protected abstract P createPto(M heroModel);

  private void writePersistence(HeroModelSaver persistence, String json) {
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