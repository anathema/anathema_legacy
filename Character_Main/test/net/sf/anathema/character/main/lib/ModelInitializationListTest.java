package net.sf.anathema.character.main.lib;

import net.sf.anathema.character.generic.template.ConfiguredModel;
import net.sf.anathema.hero.initialization.ModelInitializationList;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModelInitializationListTest {

  private List<SimpleModelTreeEntry> availableTreeEntries = new ArrayList<>();
  private List<ConfiguredModel> configuredModels = new ArrayList<>();

  @Test
  public void hasSingleEntryWithoutRequirementsFirst() throws Exception {
    addConfiguredEntry("Single");
    assertContainsOrder("Single");
  }

  @Test
  public void hasMultipleEntryWithoutRequirementsFirst() throws Exception {
    addConfiguredEntry("FirstSingle");
    addConfiguredEntry("SecondSingle");
    assertContainsOrder("FirstSingle", "SecondSingle");
  }

  @Test
  public void sortsRequirementEarlierThanEntry() throws Exception {
    addConfiguredEntry("Configured", new SimpleIdentifier("Required"));
    assertContainsOrder("Required", "Configured");
  }

  @Test
  public void doesNotAddDuplicates() throws Exception {
    addConfiguredEntry("FirstConfigured", new SimpleIdentifier("Required"));
    addConfiguredEntry("SecondConfigured", new SimpleIdentifier("Required"));
    assertContainsOrder("Required", "FirstConfigured", "SecondConfigured");
  }

  @Test
  public void respectsTransitiveRelations() throws Exception {
    addConfiguredEntry("End", new SimpleIdentifier("Middle"));
    addConfiguredEntry("Middle", new SimpleIdentifier("Start"));
    assertContainsOrder("Start", "Middle", "End");
  }

  @Test
  public void doesNotConfigureEntriesThatAreNotRequired() throws Exception {
    addAvailableEntry("End", new SimpleIdentifier("Middle"));
    addConfiguredEntry("Middle", new SimpleIdentifier("Start"));
    assertContainsOrder("Start", "Middle");
  }

  @Test
  public void respectsTransitiveRelationsEvenWhenTheyAreNotRegistered() throws Exception {
    addConfiguredEntry("End", new SimpleIdentifier("Middle"));
    addAvailableEntry("Middle", new SimpleIdentifier("Start"));
    assertContainsOrder("Start", "Middle", "End");
  }

  private void addAvailableEntry(String id, Identifier... requirements) {
    SimpleIdentifier modelId = new SimpleIdentifier(id);
    availableTreeEntries.add(new SimpleModelTreeEntry(modelId, requirements));
  }

  private void addConfiguredEntry(String id, Identifier... requirements) {
    SimpleIdentifier modelId = new SimpleIdentifier(id);
    availableTreeEntries.add(new SimpleModelTreeEntry(modelId, requirements));
    configuredModels.add(new ConfiguredModel(modelId.getId(), null));
  }

  private void assertContainsOrder(String... ids) {
    ModelInitializationList list = new ModelInitializationList(configuredModels, availableTreeEntries);
    for (int index = 0; index < ids.length; index++) {
      assertThat(list.get(index).getId(), is(ids[index]));
    }
    assertThat(list.size(), is(ids.length));
  }
}
