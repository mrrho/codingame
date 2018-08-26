package com.codingame.tumbler;

import org.junit.Assert;
import org.junit.Test;

public class TestSolution {
    @Test
    public void testOne() {
        Assert.assertEquals("..#..#.##.##.###", Solution.calculateTumble(5, 3, 1, "..#...##.######"));
    }
}
