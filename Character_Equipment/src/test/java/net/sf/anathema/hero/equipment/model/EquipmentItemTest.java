package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.character.equipment.impl.character.model.stats.NullModifierFactory;
import net.sf.anathema.equipment.core.IEquipmentTemplate;
import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.lib.control.ChangeListener;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EquipmentItemTest {

  @Test
  public void notifiesListenersOfPersonalizationChange() throws Exception {
    IEquipmentTemplate template = mock(IEquipmentTemplate.class);
    EquipmentItem item = new EquipmentItem(template, MagicalMaterial.Adamant, new NullAttunementProvider(), new NullModifierFactory());
    ChangeListener listener = mock(ChangeListener.class);
    item.addChangeListener(listener);
    item.setPersonalization("Title", "Description");
    verify(listener).changeOccurred();
  }
}