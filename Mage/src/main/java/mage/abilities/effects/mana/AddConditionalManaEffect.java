/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mage.abilities.effects.mana;

import mage.Mana;
import mage.abilities.Ability;
import mage.abilities.effects.common.ManaEffect;
import mage.abilities.mana.builder.ConditionalManaBuilder;
import mage.game.Game;

/**
 * @author LevelX2
 */
public class AddConditionalManaEffect extends ManaEffect {

    private final Mana mana;
    private final ConditionalManaBuilder manaBuilder;

    public AddConditionalManaEffect(Mana mana, ConditionalManaBuilder manaBuilder) {
        super();
        this.mana = mana;
        this.manaBuilder = manaBuilder;
        staticText = "Add " + this.mana.toString() + ". " + manaBuilder.getRule();
    }

    public AddConditionalManaEffect(final AddConditionalManaEffect effect) {
        super(effect);
        this.mana = effect.mana.copy();
        this.manaBuilder = effect.manaBuilder;
    }

    @Override
    public AddConditionalManaEffect copy() {
        return new AddConditionalManaEffect(this);
    }

    @Override
    public Mana produceMana(Game game, Ability source) {
        return manaBuilder.setMana(mana, source, game).build();
    }

    public Mana getMana() {
        return mana;
    }
}
