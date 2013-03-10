package net.sf.anathema.scribe.scroll.persistence;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import net.sf.anathema.lib.control.IChangeListener;
import org.jmock.example.announcer.Announcer;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryScrollPersister implements ScrollPersister {

  private int counter = 0;
  private final Map<RepositoryId, Scroll> scrollsByRepositoryId = new HashMap<>();
  private final Announcer<IChangeListener> listChangeAnnouncer = new Announcer(IChangeListener.class);

  @Override
  public void saveScroll(Scroll scroll) {
    RepositoryId repositoryId = scroll.repositoryId;
    boolean isNew = !scrollsByRepositoryId.containsKey(repositoryId);
    scrollsByRepositoryId.put(repositoryId, scroll);
    if (isNew) {
      listChangeAnnouncer.announce().changeOccurred();
    }
  }

  @Override
  public Scroll loadScroll(RepositoryId id) {
    return scrollsByRepositoryId.get(id);
  }

  @Override
  public Scroll newScroll() {
    ScrollDto newScrollDto = new ScrollDto("", "");
    String id = String.valueOf(counter++);
    return new Scroll(newScrollDto, new SimpleRepositoryId(id));
  }

  @Override
  public Collection<ScrollReference> listAll() {
    Collection<Scroll> values = scrollsByRepositoryId.values();
    return Collections2.transform(values, new Function<Scroll, ScrollReference>() {
      @Nullable
      @Override
      public ScrollReference apply(@Nullable Scroll input) {
        return new ScrollReference(input.repositoryId, input.dto.title);
      }
    });
  }

  @Override
  public void addScrollListChangeListener(IChangeListener listener) {
    listChangeAnnouncer.addListener(listener);
  }
}