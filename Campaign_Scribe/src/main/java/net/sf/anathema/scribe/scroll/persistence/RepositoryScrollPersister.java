package net.sf.anathema.scribe.scroll.persistence;

import net.sf.anathema.framework.repository.Repository;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.framework.repository.access.printname.ReferenceAccess;
import net.sf.anathema.framework.repository.access.printname.ReferenceBuilder;
import net.sf.anathema.scribe.scroll.ScrollItemType;
import net.sf.anathema.scribe.scroll.gson.ScrollGson;
import net.sf.anathema.scribe.scroll.gson.ScrollReferenceBuilder;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

public class RepositoryScrollPersister implements ScrollPersister {

  private final Repository model;
  private final Clock clock;
  private final ScrollGson scrollGson = new ScrollGson();

  public RepositoryScrollPersister(Repository model, Clock clock) {
    this.model = model;
    this.clock = clock;
  }

  @Override
  public void saveScroll(Scroll scroll) {
    OutputStream outputStream = null;
    try {
      outputStream = createOutputStreamFor(scroll);
      scrollGson.save(scroll, outputStream);
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
    RepositoryId id = createRepositoryId();
    return new Scroll(scrollDto, id);
  }

  @Override
  public Collection<ScrollReference> listAll() {
    ReferenceBuilder<ScrollReference> builder = new ScrollReferenceBuilder();
    ReferenceAccess<ScrollReference> access = model.createReferenceAccess(ScrollItemType.ITEM_TYPE, builder);
    return access.collectAllItemReferences();
  }

  @Override
  public boolean hasAny() {
    return !listAll().isEmpty();
  }

  public RepositoryId createRepositoryId() {
    TimedRepositoryData repositoryData = new TimedRepositoryData(clock);
    String repositoryId = model.createUniqueRepositoryId(repositoryData);
    return new SimpleRepositoryId(repositoryId);
  }
}