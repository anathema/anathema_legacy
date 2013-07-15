package net.sf.anathema.magic.description.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.Repository;
import net.sf.anathema.framework.repository.RepositoryStringAccess;
import net.sf.anathema.magic.description.module.MagicDescriptionItemTypeConfiguration;

public class RepositoryMagicDescriptionDataBase implements MagicDescriptionDataBase {

  public static RepositoryMagicDescriptionDataBase CreateFrom(IApplicationModel anathemaModel) {
    Repository repository = anathemaModel.getRepository();
    IItemType itemType = new MagicDescriptionItemTypeConfiguration().getItemType();
    return new RepositoryMagicDescriptionDataBase(repository, itemType);
  }

  private Repository repository;
  private IItemType itemType;
  private final Gson gson;

  public RepositoryMagicDescriptionDataBase(Repository repository, IItemType itemType) {
    this.repository = repository;
    this.itemType = itemType;
    this.gson = new GsonBuilder().setPrettyPrinting().create();
  }

  @Override
  public void saveDescription(String charmId, String description) {
    String jsonRepresentation = createJSonRepresentation(charmId, description);
    new RepositoryStringAccess(repository, itemType).write(charmId, jsonRepresentation);
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
    String jsonRepresentation = new RepositoryStringAccess(repository, itemType).read(charmId);
    MagicDescriptionPO persistenceObject = gson.fromJson(jsonRepresentation, MagicDescriptionPO.class);
    return persistenceObject.description;
  }
}