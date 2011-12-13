package net.sf.anathema.demo.campaign.music;

import java.awt.Dimension;
import java.io.File;

import javax.swing.JComponent;

import de.jdemo.junit.DemoAsTestRunner;
import net.sf.anathema.campaign.music.impl.model.MusicDatabase;
import net.sf.anathema.campaign.music.impl.view.MusicDatabaseView;
import net.sf.anathema.campaign.music.impl.view.library.LibraryControlView;
import net.sf.anathema.campaign.music.model.IMusicDatabase;
import net.sf.anathema.campaign.music.presenter.LibraryControlProperties;
import net.sf.anathema.campaign.music.presenter.MusicDataBasePresenter;
import net.sf.anathema.campaign.music.presenter.MusicLibraryColumnViewSettings;
import net.sf.anathema.campaign.music.presenter.library.LibraryControlPresenter;
import net.sf.anathema.campaign.music.view.IMusicDatabaseView;
import net.sf.anathema.framework.resources.AnathemaResources;

import de.jdemo.extensions.SwingDemoCase;
import org.junit.runner.RunWith;

@RunWith(DemoAsTestRunner.class)
public class FunctionalDemo extends SwingDemoCase {

  public void demoMusicDataBase() throws Exception {
    AnathemaResources resources = new AnathemaResources();
    IMusicDatabase dataBase = new MusicDatabase(new File("TestMusic.yap")); //$NON-NLS-1$
    IMusicDatabaseView databaseView = new MusicDatabaseView("Music database", null); //$NON-NLS-1$
    new MusicDataBasePresenter(resources, dataBase, databaseView).initPresentation();
    JComponent content = databaseView.getComponent();
    showContent(content);
  }

  private void showContent(JComponent content) {
    content.setPreferredSize(new Dimension(1000, 650));
    show(content);
  }

  public void demoLibraries() throws Exception {
    AnathemaResources resources = new AnathemaResources();
    IMusicDatabase dataBase = new MusicDatabase(new File("TestMusic.yap")); //$NON-NLS-1$
    LibraryControlView controlView = new LibraryControlView(new MusicLibraryColumnViewSettings(
        dataBase.getLibraryControl()), new LibraryControlProperties(resources));
    controlView.addLibraryView();
    controlView.initGui();
    new LibraryControlPresenter(controlView, dataBase, resources).initPresentation();
    JComponent content = controlView.getComponent();
    showContent(content);
  }
}