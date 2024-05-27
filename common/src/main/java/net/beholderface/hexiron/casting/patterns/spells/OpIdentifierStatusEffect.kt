package net.beholderface.hexiron.casting.patterns.spells

import at.petrak.hexcasting.api.misc.MediaConstants
import at.petrak.hexcasting.api.spell.*
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import net.beholderface.hexiron.Hexiron
import net.beholderface.hexiron.HexironConfig
import net.beholderface.hexiron.registry.HexironPatternRegistry
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.util.Identifier

//janky workaround goes brr
class OpIdentifierStatusEffect(val effectID : Identifier, val allowPotency : Boolean, val potencyCubic : Boolean,
                               override val isGreat: Boolean) : SpellAction {
    override val argc: Int
        get() = if (this.allowPotency) 3 else 2
    override fun execute(args: List<Iota>, ctx: CastingContext): Triple<RenderedSpell, Int, List<ParticleSpray>>? {
        val target = args.getLivingEntityButNotArmorStand(0, argc)
        val duration = args.getPositiveDouble(1, argc)
        val potency = if(this.allowPotency) args.getPositiveDoubleUnderInclusive(2, 127.0, argc) else 1.0
        ctx.assertEntityInRange(target)
        val effect = Hexiron.getStatusEffectRegistry().get(effectID)!!
        val statusIDs = HexironPatternRegistry.statusIDs
        //val serverconfigaccess = HexironConfig.server
        val configuredCost : Int = if (effectID == statusIDs[0]){
            MediaConstants.DUST_UNIT / 2
        } else if (effectID == statusIDs[1]){
            MediaConstants.DUST_UNIT * 2
        } else if (effectID == statusIDs[2]){
            MediaConstants.DUST_UNIT
        } else if (effectID == statusIDs[3]){
            MediaConstants.DUST_UNIT
        } else if (effectID == statusIDs[4]){
            MediaConstants.DUST_UNIT
        } else {
            MediaConstants.DUST_UNIT
        }
        val cost = configuredCost * duration * if (potencyCubic) {
            potency * potency * potency
        } else {
            potency * potency
        }
        return Triple(
            Spell(effect, target, duration, potency),
            cost.toInt(),
            listOf(ParticleSpray.cloud(target.pos.add(0.0, target.standingEyeHeight / 2.0, 0.0), 1.0))
        )
    }

    private class Spell(val effect: StatusEffect, val target: LivingEntity, val duration: Double, val potency: Double) :
        RenderedSpell {
        override fun cast(ctx: CastingContext) {
            if (duration > 1.0 / 20.0) {
                val instance = StatusEffectInstance(effect, (duration * 20).toInt(), potency.toInt() - 1)
                target.addStatusEffect(instance)
            }
        }
    }
}