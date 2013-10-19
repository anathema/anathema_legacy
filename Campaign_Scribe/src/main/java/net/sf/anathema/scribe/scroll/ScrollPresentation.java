package net.sf.anathema.scribe.scroll;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.scribe.scroll.persistence.RepositoryScrollPersister;

public class ScrollPresentation implements IItemTypeViewProperties {
  private final Resources resources;
  private final RepositoryScrollPersister persister;
  private final ScrollUi ui;

  public ScrollPresentation(Resources resources, RepositoryScrollPersister persister) {
    this.resources = resources;
    this.persister = persister;
    this.ui = new ScrollUi(persister);
  }

  @Override
  public RelativePath getIcon() {
    return AgnosticUIConfiguration.NO_ICON;
  }

  @Override
  public String getLabelKey() {
    return "Scrolls";
  }

  @Override
  public AgnosticUIConfiguration<PrintNameFile> getItemTypeUI() {
    return ui;
  }
}