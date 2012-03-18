package net.sf.anathema.charm.description.persistence;

import com.google.gson.Gson;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.IItemTypeRegistry;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.lib.exception.PersistenceException;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static net.sf.anathema.charm.description.module.CharmDescriptionItemTypeConfiguration.CHARM_DESCRIPTION_ITEM_TYPE_ID;

public class RepositoryCharmDescriptionDataBase implements CharmDescriptionDataBase {

  public static RepositoryCharmDescriptionDataBase CreateFrom(IAnathemaModel anathemaModel) {
    IRepository repository = anathemaModel.getRepository();
    IItemType itemType = getItemType(anathemaModel);
    return new RepositoryCharmDescriptionDataBase(repository, itemType);
  }

  private static IItemType getItemType(IAnathemaModel anathemaModel) {
    IItemTypeRegistry registry = anathemaModel.getItemTypeRegistry();
    return registry.getById(CHARM_DESCRIPTION_ITEM_TYPE_ID);
  }

  private IRepository repository;
  private IItemType itemType;
  private final Gson gson = new Gson();

  public RepositoryCharmDescriptionDataBase(IRepository repository, IItemType itemType) {
    this.repository = repository;
    this.itemType = itemType;
  }

  @Override
  public void saveDescription(String charmId, String description) {
    try {
      String jsonRepresentation = createJSonRepresentation(charmId, description);
      IRepositoryWriteAccess writeAccess = repository.createWriteAccess(itemType, charmId);
      OutputStream stream = writeAccess.createMainOutputStream();
      stream.write(jsonRepresentation.getBytes());
    } catch (IOException e) {
      throw new PersistenceException(e);
    }
  }

  private String createJSonRepresentation(String charmId, String description) {
    CharmDescriptionPO persistenceObject = CharmDescriptionPO.ForIdAndDescription(charmId, description);
    return gson.toJson(persistenceObject);
  }

  @Override
  public String loadDescription(String charmId) {
    if (!repository.knowsItem(itemType, charmId)) {
      return null;
    }
    String jsonRepresentation = readJsonRepresentation(charmId);
    CharmDescriptionPO persistenceObject = gson.fromJson(jsonRepresentation, CharmDescriptionPO.class);
    return persistenceObject.description;
  }

  private String readJsonRepresentation(String charmId) {
    InputStream stream = null;
    try {
      IRepositoryReadAccess access = repository.openReadAccess(itemType, charmId);
      stream = access.openMainInputStream();
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      IOUtils.copy(stream, outputStream);
      return new String(outputStream.toByteArray());
    } catch (IOException e) {
      throw new PersistenceException(e);
    } finally {
      IOUtils.closeQuietly(stream);
    }
  }
}
