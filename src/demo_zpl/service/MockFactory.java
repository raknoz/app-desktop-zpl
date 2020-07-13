package demo_zpl.service;

import demo_zpl.model.BodyLayawayInformation;
import demo_zpl.model.BodySaleInformation;
import demo_zpl.model.HeaderInformation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MockFactory {

    public static HeaderInformation getHeaderInformation() {
        return new HeaderInformation("El Store de EZ", "Dirección 1234", "123456", "9:00 AM",
                "6:00 PM", "9:00 AM", "6:00 PM", "AAAB 123", "1234566",
                "00001", "Customer Name Test", "Customer Address Test", "012012121212", "ADBVD 1234");
    }

    public static BodyLayawayInformation getBodyInformation() {
        final BodyLayawayInformation.Payment payment = new BodyLayawayInformation.Payment("25/12/2020", "125.00");
        final BodyLayawayInformation.PendingPayment pendingPayment = new BodyLayawayInformation.PendingPayment("25/01/2021", "125.00");
        return new BodyLayawayInformation(Collections.singletonList(payment), Collections.singletonList(pendingPayment));
    }

    public static BodySaleInformation getBodySaleInformation() {
        final BodySaleInformation.Item item0 = new BodySaleInformation.Item("1", "This is a long description of Item Zero, with the " +
                "specification about the features! This is an example wit a lot of characters", 1,
                2.8);
        final BodySaleInformation.Item item1 = new BodySaleInformation.Item("2", "This is a description of item Two", 2, 3.5);
        final List<BodySaleInformation.Item> items = new ArrayList<>();
        items.add(item0);
        items.add(item1);
        return new BodySaleInformation(items);
    }

    public static String getTest1() {
        return "^FO32,0^GFA,12288,12288,00064,:Z64:\n" +
                "eJztWU1vG8cZntnligTliCJiVqmRmKwLFAIDqEbQAILqWiuUjC5tQwFcKIcodIv24oOjABakg5Ud00hjOIce8g" +
                "PKpkBBLAtaR4Eq3HUTw0BPEirVyYHl2kgQgQZWDEBVqsvl9JklKYmURSnuIYfwlXa5Mzsvn3nm/ZgPEtKTnvSkJz" +
                "3pSU+eRdTm1ZNvtajfdAe+zUIH/y91v5F9VlVVVWOaptdU9elvD1ccahg1ICfFixyqCWozxZk3T6hOD6H7De" +
                "Nr4NPdjgptmu/M7+z8+4j2ykQ7oOJ0NHDBpSPxw4/NtnIg09GfopBQsXiEfupBu/5Q5Wn4/iPxf5xrL59hHQ2" +
                "4zufendZ31P2qA49U52pb81Sn/ZuDH+383qZERtrLY50NwL60Hgymnq5OphbbivSQnwA9Iz6PwD9W9Hm+EYsn" +
                "422Vnq4qbW9d9v7MkY0lCVe0raqtNDNTnDo9tE7xGAqFCMV/UKV4QjFINYJ/ouAiSjA4SELIE1RLhE6jXQs/I" +
                "2X9TDz251Yy8kpGWmHyysoyWVlZkUUFI7kc+oe6LFkpiIellwv9BdbiX9WupZJx5IFpzisyr0zy1UnOa0TnfJ" +
                "JbXm7iqUqIF28Jr4tm1fn667zpB1HgG1GX/8BW3ZK41Vc3Zc6fkDucoyRvZchtXEJ/E/qE9G1VfljvrzfxF4tTi5/O" +
                "TAXxGAbC87wyxFe93OGEcy6LDjFoIuqG8JZyDhfk1fF6mLcG02BGtuF9/YZhyUZGzplyIWewgmGI0grzPzGWQd" +
                "tAklyBsVaMpWjBv8d/cXK+llqPx1x8a4hXwtwEIjdxeXkV/FN4irn4qPfgAn5gH19y8fF1A3Y+I9tWIG/25c" +
                "sGs/O2XN6Uc2zENm4z4rXLlmzniWTnly4aA/mm/k9Wh4prMxua6eKbYYG/Ciy+BtZeXvOWrsMQfLWFTwL4H" +
                "K/t888C35+VgH8ml2P9uewZgyEf5Rj4yrll4I8ZGA3wz2Vko8AwSktjBX8rYcxXJ+evzgNZJSlHU1N8V+er01" +
                "wD/nYS+JP8HuBhetRdgl+gBa/qtVRdbeJnBH83CM+C01l7c6xs9tkv2xl7k/jLT6QtVrfrtoXRMTJ+22YjeV65" +
                "WB6xW/zXgs7ajFME/5QjjCvwh0rUWXMqgquXK9yBVYhgnHI4G+dkVq+F6y3+GdLCP4NJ8Iyx/LLB/AYF/4zwCH/h" +
                "eo4/MpaIP+fOFRgNFhlbjrb4hz+PaXFMApb4dg2u7ujcnAboP8A/IEJAuB8CIMw1Vd/hpl7zePTa+AH8RhQQMpI/x0" +
                "aM5ct5c+AxvZ0G30A578//FsNnVMgAEC8/splAju6OtOz/0hoVkhL8uXNdL30G/uFSqPQhxhn49/kQ3+Yuf359cY0z" +
                "HTOYvjv+r6Z+M/XcyohOFFg0t2wI/v25G7B3v5H7wEhjzHJL8I4VNvZRjuUwSmLEmvqpajIGmb5EVHCf0B84jr4qPP" +
                "4+8F/neolPC5tXhN3v8Qe6ia6oMFK1Ff8NfAkfl8s2uVIuFAzz7OO6fcPOWwP5rbLt5cRbXoJncGY/2hJuQfKbI09" +
                "a/JNBISHMa8CnDfyww0vIIQgF3QF/1QtQ4F+HUwp8wr8ar7b4N1c+wB/LLRPjVmE5x84YBcT+iuU34BmB/xL6JAPPyF0" +
                "30kbGcPmPLTf1vY6gH4tfFfg1CtpVXUR8DWb/PFxP8foQj3kRf6h7j8f1z5EZPfwrfQ/f2MO/Us4S+2NjE/a/XX4i" +
                "l8uZgfJIPR+wiVQX/n/7Rj5dtgT/8uZIax5/qUgp+NME5loH+A/4f8T4OzW55Ky6+GGuKk380l39Id9t54/QbuJHlw" +
                "g1boAh84uZIMfw8gw3AihzJqJDyt0yMgV0GPdMi7++LhwgPqtSvQqg+7zi2h/TgKm6+CmRD4DvIB1MC/4xVfCPNRxA" +
                "LL8+wldiVK9UiJy/YWfyZhQcEfhkpOzn5YBdvoPYHdklfWXbsAw7b9rWiN1MAEqxVNS0RHAW/KtE4T+FhVdTziARE" +
                "0+49hKvhR2nhFVIqoYU6PAK5w/uAp83E6AP+CLUhVULWSmHfA8/XxYdK2SiyzL4YyK4g5XQsqgqZAxEiRiFQhN/foff" +
                "29mG/bHWQcKd5J8gxqukkedrQ7waFvkP+FVhAngH5xP8IeetBAD8NHwK+Hnjz323pTwrm1cwuv583r5oeLceBfJ2H" +
                "o2vbJKobeezF8t22nhYNlr8HxRL7xWL9A1CRcKH2zO+KrwrwEt8HF7YwId3NvHHO/Czt1r4bsKXDJZj8ASsBoy/Fp" +
                "YpXwL/3B0xUZAo4mO531iRjD/srZjjcyk+Ob8Yr6lxOP00+Mca+K8L2JqCJCDmH2SHqqo72CikRE78Aq/UpuNL" +
                "Al/KEmpv1QbqUp3Y5tsVsRqweX2X8gr41wcIebdC3t4lV2qSvYU1wZNyMwHQ1WAx5GyEPhP8a0MO5ZSv6RV3NuTc" +
                "1S9xHhbe4f6LuJT5XbFAaQgjIqpFGshmsz4xEAYbxvj38807u1n62ApggXDWTVQ3GRnOklvoLMu2dkxKLO5M7qxP" +
                "L2KVpSVDmpL0aBNaUmSDjdRiUtmpeu/p62ex6koqqNaSnuntOGYM0aQhElYAJHpoAY44HbhGqGV5TUIudr7dExqkWP" +
                "5PzXRuAKhw/3FC71aE/oUj9YGflcQc3FmNNBH4PaFmRbYIGd1v/YGUPufz+ZobMZXGJufAiG+7W729SyULRPWGTe" +
                "hTJPxrjcZ45Z2GaAlNu9TaCMCxxBawE38BbX/HZHNX9GR/1yX5/bej0awvYzV5Ds44oeDMIf4EkN77pmwu0Bqh+7" +
                "ue5+OhB5q2pq3Ntmr8Lv4h/qAs37BktkDqRLb2qmWfPxe9dfMma+Fj8R/Tdubb9hlUV118MfjXiE7C5t6roZ+HUnP" +
                "33knO7XVJLPA7NqABLCdgMpCX2SgZt8bZPv9T/rHhv126+f0Wvhak2P4VNw7qYyZq6F/zsgsiEvZfhbRBLRifCGqJ" +
                "ZsUwaWzCDqjLIjvA5al1UYa+zNs2vb62YZrQ56+m5nUOxnsS5nEOfQ+1wl7zXbjiHlfVPTBwTwFaRwOXsfzswA9bZ" +
                "JwNwAJWwMvOdmx4OoQuFtdfKjozpYP4+DfD3hqtBN5n4S7KQqKH8ZFtAmZgvEYs+Qb60V3md6qpHX2njT9Mbwaw5q" +
                "t4vyQd+Ie2hlj6deIHYAIrgDm3Qr8Uha4yU9yY2SiWNg7gexk83osEt0B3jtV/Cj700XUmYhD7BdZdX4fwueTBkx5k" +
                "HOqmHeH73iP0Wgru6Y8ROfhKQMPzRQwenfhaMsP5dqK9SkAvNPCPs76L3xn7stAXt1FynPUJ0bRYZ5ULLfQvHMn+AP" +
                "7hKpFuFsTtePZPFQrVa+J29tn1R0+or6puQJP9cx1VnRAZHzk3ERZGVtv/1KO/a08EPqmQyvGj//T+VwT/yqGzrhPL" +
                "rsDfHe886zuxLHiE6++nvbao73441NAXXdDrxzc8Uh9DoD8TfR8j7ty3S9xJx/eUFl31Q0mYGOuWbTEBqmojLzRviZ" +
                "h4SHT9qSAibhf2Ztz014B2JTjo6ntX3RINHcQaPOXeu+pHBMpFX+CmW5LaDgZ9At/HuvbiR1SLKW8pelJRSYx4lHgs" +
                "riiJeFyF3RPJpBJTEslu+hHy0a2+i+f4n06dR/hL2J//6o/+m9FIhvyFkNlb6VOvWOlu+oN0QxtKjTv/fG6WpoCpTM" +
                "09eO5TLbRKHDI4uxF689WHG930RxmTSPivm2n0RJz9RiLX01jsAd8gPkm66WMZqZv+3EQ8nkzxe68lr07oap" +
                "JcnYt//LPTv569pHAyNTW5/mbii0m1K/80GQ7X/95HMvQxFifnIwz4lyQLGwPfDeMmYT/Id1EnU1RRRsPbn3yHrlI" +
                "ORz4fVOQXBy8FLcUhwQ9nXkiak9Pd9BmRfKMB5ifDjGxCPxJhsLngv0t8aTyxrsNPVEU5dUH75BeeZJIukgtKUntP" +
                "Of2iOmcpVaJ5Y28kJyYPzRdt+BLwfcwvRVr4aamFT8QTO9cV3wT+6NTdgVBCTIqjwcTa+/T8d+ldFaXTimdwYsLTN" +
                "QBgfd9whPl9ruOP4gOe8EIjCvwIjUh3+mTjtVBs8Tf3kzPaKiaGq4m5eF/86jvqfZH535pMJn45/VrX32os4I9+7+N" +
                "zL5wzUYoOn2N9BP1JZ1A6I0Uilz6QusafFhsc1GjydGOBG1JD9LTn1QmiCf6DNJFQNSXRTV/EvZ8NYxBcym6wv8LE8" +
                "QhEnJJmjvmpwCNmPI+nmeY94mO2UX+CxN+QToDISRWbYnaUuxI+gT77Wtoq2f8JSD1wbzyppCc96UlPetKTnv" +
                "Tkm5T/AWQHiGU=:AB45";
        /* End image */
    }
}
