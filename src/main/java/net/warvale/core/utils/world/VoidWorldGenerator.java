package net.warvale.core.utils.world;

import net.warvale.core.Main;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class VoidWorldGenerator extends ChunkGenerator implements Cloneable {
    public static String getName() {
        return Main.get().getDescription().getName();
    }

    public static VoidWorldGenerator getGenerator() {
        if (generator == null) {
            generator = new VoidWorldGenerator();
        }
        return generator.clone();
    }

    private static VoidWorldGenerator generator;

    private VoidWorldGenerator() {
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return Arrays.asList(new BlockPopulator[0]);
    }

    @Override
    public boolean canSpawn(World world, int x, int z) {
        return true;
    }

    @Override
    public byte[] generate(World world, Random rand, int chunkx, int chunkz) {
        return new byte['耀'];
    }


    @Override
    public Location getFixedSpawnLocation(World world, Random random) {
        return new Location(world, 0.0D, 50.0D, 0.0D);
    }

    @Override
    public VoidWorldGenerator clone() {
        return new VoidWorldGenerator();
    }
}
