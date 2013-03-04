package net.sf.anathema.scribe.perspective.model;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.scribe.editor.model.ScrollModel;
import net.sf.anathema.scribe.scroll.ScrollItemType;

import java.util.Collection;

public class ScribeModel {
  public final ScrollModel scrollModel = new ScrollModel();
  private final IApplicationModel applicationModel;

  public ScribeModel(IApplicationModel applicationModel) {
    this.applicationModel = applicationModel;
  }

  public Collection<PrintNameFile> collectAllScrolls() {
    IItemType itemType = ScrollItemType.ITEM_TYPE;
    IRepository repository = applicationModel.getRepository();
    IPrintNameFileAccess access = repository.getPrintNameFileAccess();
    return access.collectAllPrintNameFiles(itemType);
  }
}
