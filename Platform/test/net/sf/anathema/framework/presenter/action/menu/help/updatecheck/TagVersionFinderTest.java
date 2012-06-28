package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import net.sf.anathema.framework.Version;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TagVersionFinderTest {

  @Test
  public void returnsVersionFromTag() throws Exception {
    TagVersionFinder finder = new TagVersionFinder();
    Tag tag = createTag("v4.1.0");
    List<Tag> tags = newArrayList(tag);
    Version version = finder.findLatestTaggedVersion(tags);
    assertThat(version.compareTo(new Version(4, 1, 0)), is(0));
  }

  @Test
  public void returnsLatestVersionFromTag() throws Exception {
    TagVersionFinder finder = new TagVersionFinder();
    Tag tag410 = createTag("v4.1.0");
    Tag tag411 = createTag("v4.1.1");
    List<Tag> tags = newArrayList(tag410, tag411);
    Version version = finder.findLatestTaggedVersion(tags);
    assertThat(version.compareTo(new Version(4, 1, 1)), is(0));
  }

  @Test
  public void ignoresNonVersionTags() throws Exception {
    TagVersionFinder finder = new TagVersionFinder();
    Tag tag410 = createTag("v4.1.0");
    Tag tag411 = createTag("v4.1.1");
    Tag tagIllegible = createTag("vwvvv");
    List<Tag> tags = newArrayList(tag410, tag411, tagIllegible);
    Version version = finder.findLatestTaggedVersion(tags);
    assertThat(version.compareTo(new Version(4, 1, 1)), is(0));
  }

  private Tag createTag(String version) {
    Tag tag410 = new Tag();
    tag410.name = version;
    return tag410;
  }
}