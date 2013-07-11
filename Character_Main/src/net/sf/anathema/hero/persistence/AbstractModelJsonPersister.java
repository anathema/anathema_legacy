package net.sf.anathema.hero.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.framework.messaging.NullMessaging;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.exception.PersistenceException;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class AbstractModelJsonPersister<P, M extends HeroModel> implements HeroModelPersister {

  protected IMessaging messaging = new NullMessaging();
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
  public void setMessaging(IMessaging messaging) {
    this.messaging = messaging;
  }

  @Override
  public final void load(Hero hero, HeroModel model, HeroModelLoader loader) throws PersistenceException {
    P pto = loadPto(loader);
    if (pto == null) {
      return;
    }
    loadModelFromPto(hero, (M) model, pto);
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

  protected abstract void loadModelFromPto(Hero hero, M model, P pto);

  @Override
  public final void save(HeroModel heroModel, HeroModelSaver saver) {
    P pto = saveModelToPto((M) heroModel);
    String json = gson.toJson(pto);
    writePersistence(saver, json);
  }

  protected abstract P saveModelToPto(M heroModel);

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