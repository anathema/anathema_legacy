package net.sf.anathema.campaign.music.impl.module;

import net.sf.anathema.campaign.music.impl.model.MusicDatabase;
import net.sf.anathema.campaign.music.impl.view.MusicDatabaseView;
import net.sf.anathema.campaign.music.model.IMusicDatabase;
import net.sf.anathema.campaign.music.presenter.MusicDataBasePresenter;
import net.sf.anathema.campaign.music.presenter.MusicUI;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;
import net.sf.anathema.initialization.reflections.ReflectionObjectFactory;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.IResources;

import java.nio.file.Path;

@PerspectiveAutoCollector
@Weight(weight = 8000)
public class MusicDatabasePerspective implements Perspective {
  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setIcon("TaskBarMusic24.png");
    toggle.setTooltip("ItemType.MusicDatabase.PrintName");
  }

  @Override
  public void initContent(Container container, IAnathemaModel model, IResources resources, ReflectionObjectFactory objectFactory) {
    Path parentFolder = model.getRepository().getDataBaseDirectory("music");
    IMusicDatabase database = new MusicDatabase(parentFolder.resolve("musicdatabase.yap"));
    MusicUI musicUI = new MusicUI(resources);
    MusicDatabaseView view = new MusicDatabaseView(resources.getString("ItemType.MusicDatabase.PrintName"), musicUI); //$NON-NLS-1$
    new MusicDataBasePresenter(resources, database, view).initPresentation();
    container.setSwingContent(view.getComponent());
  }
}
