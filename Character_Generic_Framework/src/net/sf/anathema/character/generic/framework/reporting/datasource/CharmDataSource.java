package net.sf.anathema.character.generic.framework.reporting.datasource;

import java.util.Collections;
import java.util.List;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.magic.compare.MagicComparator;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.CostStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.HealthCostStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.IMagicInfoStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.MagicInfoStringBuilder;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.general.IMagicSource;
import net.sf.anathema.framework.reporting.IReportDataSource;
import net.sf.anathema.lib.resources.IResources;

public class CharmDataSource implements IReportDataSource {

  public static final String COLUMN_PRINT_NAME = "PRINT_NAME"; //$NON-NLS-1$
  public static final String COLUMN_COST = "COST"; //$NON-NLS-1$
  public static final String COLUMN_DURATION = "DURATION"; //$NON-NLS-1$
  public static final String COLUMN_TYPE = "TYPE"; //$NON-NLS-1$
  public static final String COLUMN_SOURCE = "SOURCE"; //$NON-NLS-1$

  private final List<IMagic> magicList;
  private final IResources resources;
  private final IGenericCharacter character;
  private final IMagicInfoStringBuilder costStringBuilder;

  public CharmDataSource(final IResources resources, final IGenericCharacter character) {
    this.resources = resources;
    CostStringBuilder essenceBuilder = new CostStringBuilder(resources, "CharacterSheet.Charm.Mote"); //$NON-NLS-1$
    CostStringBuilder willpowerBuilder = new CostStringBuilder(resources, "CharacterSheet.Charm.Willpower"); //$NON-NLS-1$
    HealthCostStringBuilder healthBuilder = new HealthCostStringBuilder(resources, "CharacterSheet.Charm.HealthLevel"); //$NON-NLS-1$
    CostStringBuilder experienceBuilder = new CostStringBuilder(resources, "CharacterSheet.Charm.ExperiencePoints"); //$NON-NLS-1$
    this.costStringBuilder = new MagicInfoStringBuilder(
        resources,
        essenceBuilder,
        willpowerBuilder,
        healthBuilder,
        experienceBuilder);
    this.character = character;
    this.magicList = character.getAllLearnedMagic();
    // CharmOrderType preferredOrderType = CharmOrderType.valueOf(CHARACTER_PREFERENCES.get(
    // CHARMORDER_PREFERENCE,
    // DEFAULT_CHARMORDER));
    // final Comparator[] charmComparator = new Comparator[1];
    // preferredOrderType.accept(new ICharmOrderTypeVisitor() {
    // public void visitAlphabet(CharmOrderType type) {
    // charmComparator[0] = new I18nedIdentificateComparator(resources);
    // }
    //
    // public void visitTreeOrder(CharmOrderType type) {
    // charmComparator[0] = new MagicComparator(character.getTemplate().getTemplateType().getCharacterType());
    // }
    // });
    // Collections.sort(magicList, charmComparator[0]);
    Collections.sort(magicList, new MagicComparator(
        character.getTemplate().getTemplateType().getCharacterType(),
        character.getRules()));
  }

  public int getRowCount() {
    return magicList.size();
  }

  public Object getValue(int currentRow, String columnName) {
    if (currentRow >= getRowCount()) {
      return ""; //$NON-NLS-1$
    }
    IMagic magic = magicList.get(currentRow);
    if (COLUMN_PRINT_NAME.equals(columnName)) {
      int currentLearnCount = 1;
      boolean alien = false;
      if (magic instanceof ICharm) {
        ICharm charm = (ICharm) magic;
        alien = character.isAlienCharm(charm);
        currentLearnCount = character.getLearnCount(charm);
      }
      return (alien ? "*" : "") + resources.getString(magic.getId()) + (currentLearnCount > 1 ? " (" + currentLearnCount + ")" : ""); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
    }
    if (COLUMN_COST.equals(columnName)) {
      return costStringBuilder.createCostString(magic);
    }
    if (COLUMN_DURATION.equals(columnName)) {
      final String[] duration = new String[1];
      magic.accept(new IMagicVisitor() {
        public void visitCharm(ICharm charm) {
          duration[0] = charm.getDuration().getText(resources);
        }

        public void visitSpell(ISpell spell) {
          duration[0] = resources.getString("CharmDataSource.Duration.NA"); //$NON-NLS-1$
        }
      });
      return duration[0];
    }
    if (COLUMN_TYPE.equals(columnName)) {
      final String[] type = new String[1];
      magic.accept(new IMagicVisitor() {
        public void visitCharm(ICharm charm) {
          type[0] = charm.getCharmTypeModel().getCharmType().getId();
        }

        public void visitSpell(ISpell spell) {
          type[0] = spell.getCircleType().getId();
        }
      });
      return resources.getString(type[0]);
    }
    if (COLUMN_SOURCE.equals(columnName)) {
      if (magic.getSource() == null) {
        return resources.getString("CharmDataSource.Source.Custom"); //$NON-NLS-1$
      }
      IMagicSource source = magic.getSource();
      return source.getSource() + (source.getPage() == null ? "" : ", " + source.getPage()); //$NON-NLS-1$//$NON-NLS-2$
    }
    throw new IllegalArgumentException("No column with name '" + columnName + "'."); //$NON-NLS-1$ //$NON-NLS-2$  
  }
}