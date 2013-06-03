package net.sf.anathema.scribe.scroll.conversion;

import net.sf.anathema.framework.presenter.action.IFileProvider;
import net.sf.anathema.framework.repository.Repository;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.scribe.scroll.persistence.ScrollDto;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

import static net.sf.anathema.campaign.module.NoteTypeConfiguration.ITEM_TYPE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NoteToScrollConverter_Test {
  private ScrollDto actualScroll;
  private ScrollDto expectedScroll;

  @Before
  public void convertScroll() throws Exception {
    File repositoryFolder = Paths.get(".").toFile();
    Repository repository = new Repository(repositoryFolder);
    IRepositoryReadAccess readAccess = repository.openReadAccess(ITEM_TYPE, new IFileProvider() {
      @Override
      public File getFile() {
        try {
          URL url = ClassLoader.getSystemResource("repository/Notes/Input.not");
          return Paths.get(url.toURI()).toFile();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    });
    actualScroll = new NoteToScrollConverter().convert(readAccess);
  }

  @Before
  public void createExpectedScroll() {
    expectedScroll = new ScrollDto("Hallo Welt",
            "Ich bin eine Notiz mit **FETTEM** Text und *kursivem* Test und einigen ++unterstrichenen++ Zeichen.");
  }

  @Test
  public void copiesTitleVerbatim() throws Exception {
    assertThat(actualScroll.title, is(expectedScroll.title));
  }


  @Test
  public void recreatesTextInWikiSyntax() throws Exception {
    assertThat(actualScroll.wikiText, is(expectedScroll.wikiText));
  }
}
