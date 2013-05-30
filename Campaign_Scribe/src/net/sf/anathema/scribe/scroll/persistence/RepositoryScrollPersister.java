package net.sf.anathema.scribe.scroll.persistence;

import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.framework.repository.access.printname.ReferenceAccess;
import net.sf.anathema.framework.repository.access.printname.ReferenceBuilder;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.scribe.scroll.ScrollItemType;
import net.sf.anathema.scribe.scroll.gson.ScrollGson;
import net.sf.anathema.scribe.scroll.gson.ScrollReferenceBuilder;
import org.apache.commons.io.IOUtils;
import org.jmock.example.announcer.Announcer;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

public class RepositoryScrollPersister implements ScrollPersister {

  private final Announcer<IChangeListener> listChangeAnnouncer = new Announcer(IChangeListener.class);
  private final IRepository model;
  private final Clock clock;
  private final ScrollGson scrollGson = new ScrollGson();

  public RepositoryScrollPersister(IRepository model, Clock clock) {
    this.model = model;
    this.clock = clock;
  }

  @Override
  public void saveScroll(Scroll scroll) {
    OutputStream outputStream = null;
    try {
      outputStream = createOutputStreamFor(scroll);
      scrollGson.save(scroll, outputStream);
      listChangeAnnouncer.announce().changeOccurred();
    } finally {
      IOUtils.closeQuietly(outputStream);
    }
  }

  private OutputStream createOutputStreamFor(Scroll scroll) {
    String repositoryId = scroll.repositoryId.getStringRepresentation();
    IRepositoryWriteAccess writeAccess = model.createWriteAccess(ScrollItemType.ITEM_TYPE, repositoryId);
    return writeAccess.createMainOutputStream();
  }

  @Override
  public Scroll loadScroll(RepositoryId id) {
    IRepositoryReadAccess readAccess = model.openReadAccess(ScrollItemType.ITEM_TYPE, id.getStringRepresentation());
    InputStream inputStream = null;
    try {
      inputStream = readAccess.openMainInputStream();
      return scrollGson.load(inputStream);
    } finally {
      IOUtils.closeQuietly(inputStream);
    }
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
    ReferenceBuilder<ScrollReference> builder = new ScrollReferenceBuilder();
    ReferenceAccess<ScrollReference> access = model.createReferenceAccess(ScrollItemType.ITEM_TYPE, builder);
    return access.collectAllItemReferences();
  }

  @Override
  public void addScrollListChangeListener(IChangeListener listener) {
    listChangeAnnouncer.addListener(listener);
  }
}