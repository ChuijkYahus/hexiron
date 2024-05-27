package net.beholderface.hexiron.mixin;

import at.petrak.hexcasting.interop.HexInterop;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.patchouli.api.PatchouliAPI;

@Mixin(HexInterop.class)
public class InteropFlagMixin {
    @Inject(
            method = "initPatchouli",
            at = @At("RETURN"),
            remap = false
    )
    private static void enableInteropFlag(CallbackInfo ci){
        PatchouliAPI.get().setConfigFlag(HexInterop.PATCHOULI_ANY_INTEROP_FLAG, true);
    }
}