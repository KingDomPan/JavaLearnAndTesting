package com.panqd.jdk8.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by panqd on 3/2/16.
 */
public class StreamTest {
    public static void main(String[] args) throws Exception {
        // of 生成一组新的Stream
        // collect 及早求值方法, 将流中的数据转换成为集合
        List<String> collected = Stream.of("a", "b", "c").collect(Collectors.toList());
        System.out.println(collected);

        // flatMap将一连串的流进行合并
        List<Integer> togeter = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4)).flatMap(numbers -> numbers.stream())
                .collect(Collectors.toList());
        System.out.println(togeter);

        List<Track> tracks = Arrays.asList(
                new Track("Bak", 100),
                new Track("Violet", 200),
                new Track("Time Was", 3000)
        );

        Track t = tracks.stream()
                .min(Comparator.comparing(track -> track.getLength()))  // 返回Optional对象
                .get();
        System.out.print(t == tracks.get(0));
    }
}

class Track {
    private String name;
    private int num;

    public Track(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public int getLength() {
        return this.name.length();
    }
}