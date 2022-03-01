package GOST_34_10_18;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface HASH {
    public Byte[] HASH_Func(@NotNull Byte[] input);
}
