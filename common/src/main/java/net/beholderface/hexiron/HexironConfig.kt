package net.beholderface.hexiron

import at.petrak.hexcasting.api.misc.MediaConstants
import net.minecraft.util.Identifier

object HexironConfig {
    interface CommonConfigAccess { }

    interface ClientConfigAccess { }

    interface ServerConfigAccess {
        val oakskinCost : Int
        val sightCost : Int
        val chargeCost : Int
        val wingsCost : Int
        val invisCost : Int

        companion object {
            const val DEFAULT_OAKSKIN_COST : Int = MediaConstants.DUST_UNIT
            const val DEFAULT_SIGHT_COST : Int = MediaConstants.DUST_UNIT * 2
            const val DEFAULT_CHARGE_COST : Int = MediaConstants.DUST_UNIT
            const val DEFAULT_WINGS_COST : Int = MediaConstants.DUST_UNIT / 2
            const val DEFAULT_INVIS_COST : Int = MediaConstants.DUST_UNIT
        }
    }

    // Simple extensions for resource location configs
    @JvmStatic
    fun anyMatch(keys: MutableList<out String>, key: Identifier): Boolean {
        for (s in keys) {
            if (Identifier.isValid(s)) {
                val rl = Identifier(s)
                if (rl == key) {
                    return true
                }
            }
        }
        return false
    }

    @JvmStatic
    fun noneMatch(keys: MutableList<out String>, key: Identifier): Boolean {
        return !anyMatch(keys, key)
    }

    private object DummyCommon : CommonConfigAccess {  }
    private object DummyClient : ClientConfigAccess {  }
    private object DummyServer : ServerConfigAccess {
        override val oakskinCost: Int
            get() = throw IllegalStateException("Attempted to access property of Dummy Config Object")
        override val sightCost: Int
            get() = throw IllegalStateException("Attempted to access property of Dummy Config Object")
        override val chargeCost: Int
            get() = throw IllegalStateException("Attempted to access property of Dummy Config Object")
        override val wingsCost: Int
            get() = throw IllegalStateException("Attempted to access property of Dummy Config Object")
        override val invisCost: Int
            get() = throw IllegalStateException("Attempted to access property of Dummy Config Object")
    }

    @JvmStatic
    var common: CommonConfigAccess = DummyCommon
        set(access) {
            if (field != DummyCommon) {
                Hexiron.LOGGER.warn("CommonConfigAccess was replaced! Old {} New {}",
                    field.javaClass.name, access.javaClass.name)
            }
            field = access
        }

    @JvmStatic
    var client: ClientConfigAccess = DummyClient
        set(access) {
            if (field != DummyClient) {
                Hexiron.LOGGER.warn("ClientConfigAccess was replaced! Old {} New {}",
                    field.javaClass.name, access.javaClass.name)
            }
            field = access
        }

    @JvmStatic
    var server: ServerConfigAccess = DummyServer
        set(access) {
            if (field != DummyServer) {
                Hexiron.LOGGER.warn("ServerConfigAccess was replaced! Old {} New {}",
                    field.javaClass.name, access.javaClass.name)
            }
            field = access
        }
}