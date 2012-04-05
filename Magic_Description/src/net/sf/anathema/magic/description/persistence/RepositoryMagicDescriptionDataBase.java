package net.sf.anathema.magic.description.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

import static net.sf.anathema.magic.description.module.MagicDescriptionItemTypeConfiguration.MAGIC_DESCRIPTION_ITEM_TYPE_ID;

public class RepositoryMagicDescriptionDataBase implements MagicDescriptionDataBase {

  public static RepositoryMagicDescriptionDataBase CreateFrom(IAnathemaModel anathemaModel) {
    IRepository repository = anathemaModel.getRepository();
    IItemType itemType = getItemType(anathemaModel);
    return new RepositoryMagicDescriptionDataBase(repository, itemType);
  }

  private static IItemType getItemType(IAnathemaModel anathemaModel) {
    IItemTypeRegistry registry = anathemaModel.getItemTypeRegistry();
    return registry.getById(MAGIC_DESCRIPTION_ITEM_TYPE_ID);
  }

  private IRepository repository;
  private IItemType itemType;
  private final Gson gson;

  public RepositoryMagicDescriptionDataBase(IRepository repository, IItemType itemType) {
    this.repository = repository;
    this.itemType = itemType;
    this.gson = new GsonBuilder().setPrettyPrinting().create();
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
    MagicDescriptionPO persistenceObject = MagicDescriptionPO.ForIdAndDescription(charmId, description);
    return gson.toJson(persistenceObject);
  }

  @Override
  public String loadDescription(String charmId) {
    if (!repository.knowsItem(itemType, charmId)) {
      return null;
    }
    String jsonRepresentation = readJsonRepresentation(charmId);
    MagicDescriptionPO persistenceObject = gson.fromJson(jsonRepresentation, MagicDescriptionPO.class);
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
