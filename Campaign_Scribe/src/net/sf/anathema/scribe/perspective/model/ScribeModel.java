package net.sf.anathema.scribe.perspective.model;

import net.sf.anathema.campaign.module.NoteTypeConfiguration;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.scribe.editor.model.ScrollModel;

import java.util.Collection;

public class ScribeModel {
  public final ScrollModel scrollModel = new ScrollModel();
  private IAnathemaModel applicationModel;

  public ScribeModel(IAnathemaModel applicationModel) {
    this.applicationModel = applicationModel;
  }

  public Collection<PrintNameFile> collectAllNotes() {
    IItemType characterItemType = NoteTypeConfiguration.ITEM_TYPE;
    IRepository repository = applicationModel.getRepository();
    IPrintNameFileAccess access = repository.getPrintNameFileAccess();
    return access.collectAllPrintNameFiles(characterItemType);
  }
}
