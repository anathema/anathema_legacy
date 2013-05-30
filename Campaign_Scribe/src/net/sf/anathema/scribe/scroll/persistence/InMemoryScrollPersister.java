package net.sf.anathema.scribe.scroll.persistence;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import net.sf.anathema.lib.control.IChangeListener;
import org.jmock.example.announcer.Announcer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryScrollPersister implements ScrollPersister {

  private int counter = 0;
  private final Map<RepositoryId, Scroll> scrollsByRepositoryId = new HashMap<>();

  @Override
  public void saveScroll(Scroll scroll) {
    RepositoryId repositoryId = scroll.repositoryId;
    boolean isNew = !scrollsByRepositoryId.containsKey(repositoryId);
    scrollsByRepositoryId.put(repositoryId, scroll);
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
      @Override
      public ScrollReference apply(Scroll input) {
        return new ScrollReference(input.repositoryId, input.dto.title);
      }
    });
  }
}