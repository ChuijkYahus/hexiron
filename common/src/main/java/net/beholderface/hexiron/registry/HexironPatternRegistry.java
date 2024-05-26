package net.beholderface.hexiron.registry;

import at.petrak.hexcasting.api.PatternRegistry;
import at.petrak.hexcasting.api.misc.MediaConstants;
import at.petrak.hexcasting.api.spell.Action;
import at.petrak.hexcasting.api.spell.math.HexDir;
import at.petrak.hexcasting.api.spell.math.HexPattern;
import kotlin.Triple;
import net.beholderface.hexiron.Hexiron;
import net.beholderface.hexiron.casting.patterns.spells.OpIdentifierStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

import static net.beholderface.hexiron.Hexiron.id;

public class HexironPatternRegistry {
    public static List<Triple<HexPattern, Identifier, Action>> PATTERNS = new ArrayList<>();
    public static List<Triple<HexPattern, Identifier, Action>> PER_WORLD_PATTERNS = new ArrayList<>();
    // IMPORTANT: be careful to keep the registration calls looking like this, or you'll have to edit the hexdoc pattern regex.
    //public static HexPattern CONGRATS = reghjgfhcgfdisterPerWorld(HexPattern.fromAngles("eed", HexDir.WEST), "congrats", new OpCongrats());
    //public static HexPattern SIGNUM = regishjgfhcgfdter(HexPattern.fromAngles("edd", HexDir.NORTH_WEST), "signum", new OpSignum());
    public static HexPattern ANGEL_WINGS = registerPerWorld(HexPattern.fromAngles("qqqqqaewwqawdwaqwwe", HexDir.SOUTH_WEST), "angelwings",
            new OpIdentifierStatusEffect(new Identifier("irons_spellbooks", "angel_wings"), MediaConstants.DUST_UNIT / 2, false, false, true));
    public static HexPattern PLANAR_SIGHT = registerPerWorld(HexPattern.fromAngles("qqqqqaewwqqwqqaeedwawe", HexDir.SOUTH_WEST), "planarsight",
            new OpIdentifierStatusEffect(new Identifier("irons_spellbooks", "planar_sight"), MediaConstants.DUST_UNIT * 2, false, false, true));
    public static HexPattern TRUE_INVISIBILITY = registerPerWorld(HexPattern.fromAngles("qqqqqaewwqadadaqwwe", HexDir.SOUTH_WEST), "trueinvis",
            new OpIdentifierStatusEffect(new Identifier("irons_spellbooks", "true_invisibility"), MediaConstants.DUST_UNIT, false, false, true));
    public static HexPattern ABYSSAL_SHROUD = registerPerWorld(HexPattern.fromAngles("qqqqqaewwqqwqqawdwdwawe", HexDir.SOUTH_WEST), "abyssshroud",
            new OpIdentifierStatusEffect(new Identifier("irons_spellbooks", "abyssal_shroud"), MediaConstants.DUST_UNIT * 3, false, false, true));


    public static void init() {
        try {
            for (Triple<HexPattern, Identifier, Action> patternTriple : PATTERNS) {
                PatternRegistry.mapPattern(patternTriple.getFirst(), patternTriple.getSecond(), patternTriple.getThird());
            }
            for (Triple<HexPattern, Identifier, Action> patternTriple : PER_WORLD_PATTERNS) {
                PatternRegistry.mapPattern(patternTriple.getFirst(), patternTriple.getSecond(), patternTriple.getThird(), true);
            }
        } catch (PatternRegistry.RegisterPatternException e) {
            e.printStackTrace();
        }
    }

    private static HexPattern register(HexPattern pattern, String name, Action action) {
        Triple<HexPattern, Identifier, Action> triple = new Triple<>(pattern, id(name), action);
        PATTERNS.add(triple);
        return pattern;
    }

    private static HexPattern registerPerWorld(HexPattern pattern, String name, Action action) {
        Triple<HexPattern, Identifier, Action> triple = new Triple<>(pattern, id(name), action);
        PER_WORLD_PATTERNS.add(triple);
        return pattern;
    }
}
