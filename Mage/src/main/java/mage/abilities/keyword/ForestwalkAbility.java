
package mage.abilities.keyword;

import mage.constants.SubType;
import mage.filter.common.FilterLandPermanent;

/**
 *
 * @author BetaSteward_at_googlemail.com
 */
public class ForestwalkAbility extends LandwalkAbility {

    private static final FilterLandPermanent filter = new FilterLandPermanent("forest");

    static {
        filter.add(SubType.FOREST.getPredicate());
    }

    public ForestwalkAbility() {
        this(true);
    }

    public ForestwalkAbility(boolean withHintText) {
        super(filter, withHintText);
    }

    public ForestwalkAbility(final ForestwalkAbility ability) {
        super(ability);
    }

    @Override
    public ForestwalkAbility copy() {
        return new ForestwalkAbility(this);
    }
}
