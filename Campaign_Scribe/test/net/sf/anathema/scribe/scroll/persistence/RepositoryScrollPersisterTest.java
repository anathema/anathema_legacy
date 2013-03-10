package net.sf.anathema.scribe.scroll.persistence;

import net.sf.anathema.framework.repository.IBasicRepositoryIdData;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.scribe.scroll.ScrollItemType;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RepositoryScrollPersisterTest {

  private final IRepository repository = mock(IRepository.class);
  private final Date date = new Date();
  private final Clock clock = new StaticClock(date);
  private final RepositoryScrollPersister persister = new RepositoryScrollPersister(repository, clock);
  private final static String createdId = "createdId";

  @Test
  public void createsNewScrollWithIdFromRepository() throws Exception {
    when(repository.createUniqueRepositoryId(isA(IBasicRepositoryIdData.class))).thenReturn(createdId);
    Scroll scroll = createScroll();
    RepositoryId expectedId = new SimpleRepositoryId(createdId);
    assertThat(scroll.repositoryId, is(expectedId));
  }

  @Test
  public void requestsIdWithScrollItemType() throws Exception {
    IBasicRepositoryIdData data = createScrollAndCaptureData();
    assertThat(data.getItemType(), is(ScrollItemType.ITEM_TYPE));
  }

  @Test
  public void suggestsTimeFromClockAsId() throws Exception {
    IBasicRepositoryIdData data = createScrollAndCaptureData();
    assertThat(data.getIdProposal(), is(String.valueOf(date.getTime())));
  }

  private IBasicRepositoryIdData createScrollAndCaptureData() {
    ArgumentCaptor<IBasicRepositoryIdData> captor = ArgumentCaptor.forClass(IBasicRepositoryIdData.class);
    when(repository.createUniqueRepositoryId(captor.capture())).thenReturn(createdId);
    createScroll();
    return captor.getValue();
  }

  private Scroll createScroll() {
    return persister.newScroll();
  }
}