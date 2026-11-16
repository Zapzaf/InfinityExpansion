package io.github.mooy1.infinityexpansion;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.mockbukkit.mockbukkit.MockBukkit;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;

// InfinityLib (f784252486) refuses to start unless shade-relocated, and its test bypass only works with the
// pre-4.x MockBukkit plugin constructor. Shading runs after tests, so this cannot pass until InfinityLib is updated.
@Disabled("InfinityLib relocation check is incompatible with MockBukkit 4 (Paper 26.3)")
class TestInfinityExpansion {

    @BeforeAll
    public static void load() {
        MockBukkit.mock();
        MockBukkit.load(Slimefun.class);
    }

    @AfterAll
    public static void unload() {
        MockBukkit.unmock();
    }

    @Test
    void testLoad() {
        MockBukkit.load(InfinityExpansion.class);
    }

}
