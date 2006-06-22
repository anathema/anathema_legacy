package net.sf.anathema.campaign.music.impl.module;

import javax.swing.Action;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.AbstractAnathemaModule;
import net.sf.anathema.framework.module.AbstractItemTypeConfiguration;
import net.sf.anathema.framework.resources.IAnathemaResources;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.resources.IResources;

public class MusicDatabaseModule extends AbstractAnathemaModule {
  private AbstractItemTypeConfiguration musicConfiguration = new MusicDatabaseItemTypeConfiguration();

  public MusicDatabaseModule() {
    addItemTypeConfiguration(musicConfiguration);
  }

  @Override
  public void initPresentation(IResources resources, IAnathemaModel model, IAnathemaView view) {
    super.initPresentation(resources, model, view);
    view.addTools(new Action[] { StartDatabaseAction.createToolAction(resources, getAnathemaModel()) });
  }

  @Override
  public void initAnathemaResources(IAnathemaResources resources) {
    super.initAnathemaResources(resources);
    resources.addStringResourceHandler(createStringProvider("MusicModule", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("MusicView", resources.getLocale())); //$NON-NLS-1$
  }
}