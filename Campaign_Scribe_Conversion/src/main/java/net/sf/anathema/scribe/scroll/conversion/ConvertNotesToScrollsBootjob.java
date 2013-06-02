package net.sf.anathema.scribe.scroll.conversion;


import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.initialization.BootJob;
import net.sf.anathema.initialization.IBootJob;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.scribe.scroll.persistence.RepositoryScrollPersister;
import net.sf.anathema.scribe.scroll.persistence.Scroll;
import net.sf.anathema.scribe.scroll.persistence.ScrollDto;
import net.sf.anathema.scribe.scroll.persistence.SystemClock;

import java.util.Collection;

import static net.sf.anathema.campaign.module.NoteTypeConfiguration.ITEM_TYPE;

@BootJob
@Weight(weight = 13)
public class ConvertNotesToScrollsBootjob implements IBootJob {

  @Override
  public void run(Resources resources, IApplicationModel model) {
    populateRepository(model);

  }

  private void populateRepository(IApplicationModel model) {
    RepositoryScrollPersister persister = new RepositoryScrollPersister(model.getRepository(), new SystemClock());
    IRepository repository = model.getRepository();
    Collection<PrintNameFile> noteAccesses = repository.getPrintNameFileAccess().collectAllPrintNameFiles(ITEM_TYPE);
    for (PrintNameFile noteAccess : noteAccesses) {
      IRepositoryReadAccess readAccess = repository.openReadAccess(ITEM_TYPE, noteAccess.getRepositoryId());
      ScrollDto dto = new NoteToScrollConverter().convert(readAccess);
      Scroll scroll = new Scroll(dto, persister.createRepositoryId());
      persister.saveScroll(scroll);
      repository.deleteAssociatedItem(noteAccess);
    }
  }
}