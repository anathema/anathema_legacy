package net.sf.anathema.character.reporting.sheet.common.magic.stats;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.IMagicSourceStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.MagicInfoStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.source.MagicSourceStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.source.SpellSourceStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.type.ShortCharmTypeStringBuilder;
import net.sf.anathema.character.generic.framework.reporting.datasource.CharmDataSource;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.magic.charms.type.ICharmTypeModel;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class MagicStats implements IMagicStats {

  private final IMagic magic;
  private final IExaltedEdition edition;

  public MagicStats(IMagic magic, IExaltedEdition edition) {
    this.magic = magic;
    this.edition = edition;
  }

  public IIdentificate getName() {
    return new Identificate(magic.getId());
  }

  public String getCostString(IResources resources) {
    MagicInfoStringBuilder infoBuilder = CharmDataSource.createMagicInfoStringBuilder(resources);
    return infoBuilder.createCostString(magic);
  }

  public String getGroupName(final IResources resources) {
    final String[] group = new String[1];
    magic.accept(new IMagicVisitor() {

      public void visitCharm(ICharm charm) {
        group[0] = resources.getString(charm.getGroupId());
      }

      public void visitSpell(ISpell spell) {
        group[0] = resources.getString("Sheet.Magic.Group.Sorcery"); //$NON-NLS-1$
      }
    });
    return group[0];
  }

  public String getType(final IResources resources) {
    final String[] group = new String[1];
    magic.accept(new IMagicVisitor() {

      public void visitCharm(ICharm charm) {
        ICharmTypeModel model = charm.getCharmTypeModel();
        group[0] = new ShortCharmTypeStringBuilder(resources).createTypeString(model);
      }

      public void visitSpell(ISpell spell) {
        group[0] = resources.getString(spell.getCircleType().getId());
      }
    });
    return group[0];
  }

  public String getDurationString(final IResources resources) {
    final String[] group = new String[1];
    magic.accept(new IMagicVisitor() {
      public void visitCharm(ICharm charm) {
        group[0] = charm.getDuration().getText(resources);
      }

      public void visitSpell(ISpell spell) {
        group[0] = "-"; //$NON-NLS-1$
      }
    });
    return group[0];
  }

  public String getSourceString(final IResources resources) {
    final String[] group = new String[1];
    magic.accept(new IMagicVisitor() {
      public void visitCharm(ICharm charm) {
        final IMagicSourceStringBuilder<ICharm> stringBuilder = new MagicSourceStringBuilder<ICharm>(resources);
        group[0] = stringBuilder.createShortSourceString(charm);
      }

      public void visitSpell(ISpell spell) {
        final IMagicSourceStringBuilder<ISpell> stringBuilder = new SpellSourceStringBuilder(resources, edition);
        group[0] = stringBuilder.createShortSourceString(spell);
      }
    });
    return group[0];
  }

  public String[] getDetailKeys() {
    final List<String> details = new ArrayList<String>();
    magic.accept(new IMagicVisitor() {
      public void visitCharm(ICharm charm) {
        for (ICharmAttribute attribute : charm.getAttributes()) {
          final String attributeId = attribute.getId();
          if (attribute.isVisualized() && !attributeId.contains("Combo")) { //$NON-NLS-1$
            details.add("Keyword." + attributeId); //$NON-NLS-1$
          }
        }
      }

      public void visitSpell(ISpell spell) {
        final String target = spell.getTarget();
        if (target != null) {
          details.add("Spells.Target." + target); //$NON-NLS-1$
        }
      }
    });
    return details.toArray(new String[details.size()]);
  }
}