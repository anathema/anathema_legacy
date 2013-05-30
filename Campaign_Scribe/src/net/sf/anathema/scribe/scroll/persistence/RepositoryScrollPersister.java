package net.sf.anathema.scribe.scroll.persistence;

import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.lib.control.IChangeListener;
import org.jmock.example.announcer.Announcer;

import java.util.Collection;

public class RepositoryScrollPersister implements ScrollPersister{

  private final Announcer<IChangeListener> listChangeAnnouncer = new Announcer(IChangeListener.class);
  private IRepository model;
  private Clock clock;

  public RepositoryScrollPersister(IRepository model, Clock clock) {
    this.model = model;
    this.clock = clock;
  }

  @Override
  public void saveScroll(Scroll scroll) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Scroll loadScroll(RepositoryId id) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Scroll newScroll() {
    ScrollDto scrollDto = new ScrollDto("", "");
    TimedRepositoryData repositoryData = new TimedRepositoryData(clock);
    String repositoryId = model.createUniqueRepositoryId(repositoryData);
    return new Scroll(scrollDto, new SimpleRepositoryId(repositoryId));
  }

  @Override
  public Collection<ScrollReference> listAll() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void addScrollListChangeListener(IChangeListener listener) {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}