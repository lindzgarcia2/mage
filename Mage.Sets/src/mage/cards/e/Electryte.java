/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.cards.e;

import java.util.List;
import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.DealsCombatDamageToAPlayerTriggeredAbility;
import mage.abilities.effects.OneShotEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.SubType;
import mage.filter.common.FilterBlockingCreature;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;

/**
 *
 * @author rystan
 */
public class Electryte extends CardImpl {

    public Electryte(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{3}{R}{R}");
        this.subtype.add(SubType.BEAST);

        this.power = new MageInt(3);
        this.toughness = new MageInt(3);

        // Whenever Electryte deals combat damage to defending player, it deals damage equal to its power to each blocking creature.
        this.addAbility(new ElectryteTriggeredAbility());
    }

    public Electryte(final Electryte card) {
        super(card);
    }

    @Override
    public Electryte copy() {
        return new Electryte(this);
    }
}

class ElectryteTriggeredAbility extends DealsCombatDamageToAPlayerTriggeredAbility {

    ElectryteTriggeredAbility() {
        super(new ElectryteEffect(), false);
    }

    ElectryteTriggeredAbility(final ElectryteTriggeredAbility effect) {
        super(effect);
    }

    @Override
    public ElectryteTriggeredAbility copy() {
        return new ElectryteTriggeredAbility(this);
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        if (super.checkTrigger(event, game)) {
            return game.getCombat().getDefenderId(getSourceId()).equals(event.getPlayerId());
        }
        return false;
    }

    @Override
    public String getRule() {
        return "Whenever {this} deals combat damage to defending player, "
                + "it deals damage equal to its power to each blocking creature";
    }
}

class ElectryteEffect extends OneShotEffect {

    static private FilterBlockingCreature filter = new FilterBlockingCreature();

    public ElectryteEffect() {
        super(Outcome.Damage);
        staticText = "it deals damage equal to its power to each blocking creature";
    }

    public ElectryteEffect(final ElectryteEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Permanent thisCreature = game.getPermanent(source.getSourceId());
        int amount = thisCreature.getPower().getValue();
        List<Permanent> permanents = game.getBattlefield().getActivePermanents(filter, source.getControllerId(), source.getSourceId(), game);
        for (Permanent permanent : permanents) {
            permanent.damage(amount, source.getSourceId(), game, false, true);
        }
        return true;
    }

    @Override
    public ElectryteEffect copy() {
        return new ElectryteEffect(this);
    }
}