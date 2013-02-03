package net.sf.anathema.character.perspective;

import net.sf.anathema.character.itemtype.CharacterItemTypeRetrieval;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.registry.IRegistry;

import java.util.Arrays;
import java.util.List;

import static net.sf.anathema.character.itemtype.CharacterItemTypeRetrieval.retrieveCharacterItemType;

public class CharacterSystemModel {

  private final IAnathemaModel model;

  public CharacterSystemModel(IAnathemaModel model) {
    this.model = model;
  }

  public List<PrintNameFile> collectCharacterPrintNameFiles() {
    IItemType characterItemType = getCharacterItemType();
    IPrintNameFileAccess access = model.getRepository().getPrintNameFileAccess();
    PrintNameFile[] printNameFiles = access.collectAllPrintNameFiles(characterItemType);
    return Arrays.asList(printNameFiles);
  }

  public IItem loadItem(CharacterIdentifier identifier) {
    IRepositoryReadAccess readAccess = createReadAccess(identifier.getId());
    IRepositoryItemPersister persister = extractPersister();
    return persister.load(readAccess);
  }

  private IRepositoryItemPersister extractPersister() {
    IRegistry<IItemType,IRepositoryItemPersister> registry = model.getPersisterRegistry();
    return registry.get(getCharacterItemType());
  }

  private IRepositoryReadAccess createReadAccess(String repositoryId) {
    IRepository repository = model.getRepository();
    return repository.openReadAccess(getCharacterItemType(), repositoryId);
  }

  private IItemType getCharacterItemType() {
    return retrieveCharacterItemType(model);
  }
}
