package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JsonTagLoaderTest {

  @Test
  public void producesTag() throws Exception {
    String json = "[{}]";
    List<Tag> tags = new JsonTagLoader().loadFrom(json);
    assertThat(tags.size(), is(1));
  }

  @Test
  public void producesTagWithName() throws Exception {
    String json = "[{\"name\":\"v4.2.1\"}]";
    List<Tag> tags = new JsonTagLoader().loadFrom(json);
    assertThat(tags.get(0).name, is("v4.2.1"));
  }

  @Test
  public void producesTagFromFullCode() throws Exception {
    String json = "[\n" +
            "  {\n" +
            "    \"commit\": {\n" +
            "      \"sha\": \"e986584d8aac4f035511f8ea68c495881582a9a3\",\n" +
            "      \"url\": \"https://api.github.com/repos/anathema/anathema/commits/e986584d8aac4f035511f8ea68c495881582a9a3\"\n" +
            "    },\n" +
            "    \"zipball_url\": \"https://github.com/anathema/anathema/zipball/v4.1.0\",\n" +
            "    \"tarball_url\": \"https://github.com/anathema/anathema/tarball/v4.1.0\",\n" +
            "    \"name\": \"v4.1.0\"\n" +
            "  }]";
    List<Tag> tags = new JsonTagLoader().loadFrom(json);
    assertThat(tags.get(0).name, is("v4.1.0"));
  }
}