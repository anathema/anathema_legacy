package net.sf.anathema.hero.advance.overview.presenter;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.hero.advance.experience.ExperiencePointManagement;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageType;

import static java.text.MessageFormat.format;
import static net.sf.anathema.lib.message.MessageDuration.Permanent;
import static net.sf.anathema.lib.message.MessageType.NORMAL;
import static net.sf.anathema.lib.message.MessageType.WARNING;

public class TotalExperiencePresenter implements IOverviewSubPresenter {
  private final Hero hero;
  private final Resources resources;
  private final IMessaging messaging;
  private final ExperiencePointManagement management;

  public TotalExperiencePresenter(Hero hero, Resources resources, IMessaging messaging, ExperiencePointManagement management) {
    this.hero = hero;
    this.resources = resources;
    this.messaging = messaging;
    this.management = management;
  }

  @Override
  public void update() {
    int spending = management.getTotalCosts();
    int allotment = ExperienceModelFetcher.fetch(hero).getExperiencePoints().getTotalExperiencePoints();
    String pattern;
    MessageType type;
    if (spending <= allotment) {
      pattern = resources.getString("Overview.Creation.ExperiencePoints.Spent");
      type = NORMAL;
    } else {
      pattern = resources.getString("Overview.Creation.ExperiencePoints.Overspent");
      type = WARNING;
    }
    messaging.addMessage(new Message(format(pattern, spending, allotment), type, Permanent)); 
  }
}
