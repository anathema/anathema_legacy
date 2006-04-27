package net.sf.anathema.character.generic.framework.magic.stringbuilder.test;

import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.lib.testing.BasicTestCase;

public class CharmTypeStringBuilderTest extends BasicTestCase {

  private CharmTypeStringBuilder builder;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    DummyResources resources = new DummyResources();
    resources.putString("CharmTreeView.ToolTip.Type", "Type"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("ExtraAction", "Extra Action"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("Simple", "Simple"); //$NON-NLS-1$ //$NON-NLS-2$     
    resources.putString("Supplemental", "Supplemental"); //$NON-NLS-1$ //$NON-NLS-2$     
    resources.putString("Reflexive", "Reflexive"); //$NON-NLS-1$ //$NON-NLS-2$     
    builder = new CharmTypeStringBuilder(resources);
  }

  public void testNoSpecialModel() throws Exception {
    final CharmTypeModel charmTypeModel = new CharmTypeModel();
    charmTypeModel.setCharmType(CharmType.ExtraAction);
    String string = builder.buildString(charmTypeModel);
    assertEquals("Extra Action", string); //$NON-NLS-1$
  }
}