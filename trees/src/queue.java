// private int compareValues(T value1, T value2) {
//        // 自定义的值比较逻辑，根据 T 类型的实际情况进行实现
//        // 这里假设 T 实现了 Comparable 接口
//        if (value1 instanceof Comparable && value2 instanceof Comparable) {
//            return ((Comparable<T>) value1).compareTo(value2);
//        }
//        // 如果 T 类型不支持比较，则根据实际需求进行处理
//        // 可以抛出异常或者采用其他策略
//        throw new UnsupportedOperationException("Cannot compare values of type T");
//    }
//两者的主要区别在于当队列已满时的处理方式：
//
//add() 方法在队列已满时会抛出异常，适用于有界队列。
//offer() 方法在队列已满时会返回 false，适用于无界队列或者需要手动处理队列已满情况的有界队列。