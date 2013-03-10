package net.sf.anathema.scribe.scroll.persistence;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.IBasicRepositoryIdData;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.scribe.scroll.ScrollItemType;
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
    return new Scroll(null, new SimpleRepositoryId(model.createUniqueRepositoryId(new IBasicRepositoryIdData() {
      @Override
      public String getIdProposal() {
        return String.valueOf(clock.getCurrentTimeInMillis());
      }

      @Override
      public IItemType getItemType() {
        return ScrollItemType.ITEM_TYPE;
      }
    })));
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