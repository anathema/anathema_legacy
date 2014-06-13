package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EquipmentPersonalizationModelTest {

  @Test
  public void forwardsTitleChangesDirectly() throws Exception {
    IEquipmentItem item = mock(IEquipmentItem.class);
    EquipmentPersonalizationModel model = new EquipmentPersonalizationModel(item);
    model.setTitle("Title");
    verify(item).setPersonalization("Title", null);
  }

  @Test
  public void forwardsDescriptionChangesDirectly() throws Exception {
    IEquipmentItem item = mock(IEquipmentItem.class);
    EquipmentPersonalizationModel model = new EquipmentPersonalizationModel(item);
    model.setDescription("Description");
    verify(item).setPersonalization(null, "Description");
  }
}