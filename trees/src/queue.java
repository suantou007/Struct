// private int compareValues(T value1, T value2) {
//        // �Զ����ֵ�Ƚ��߼������� T ���͵�ʵ���������ʵ��
//        // ������� T ʵ���� Comparable �ӿ�
//        if (value1 instanceof Comparable && value2 instanceof Comparable) {
//            return ((Comparable<T>) value1).compareTo(value2);
//        }
//        // ��� T ���Ͳ�֧�ֱȽϣ������ʵ��������д���
//        // �����׳��쳣���߲�����������
//        throw new UnsupportedOperationException("Cannot compare values of type T");
//    }
//���ߵ���Ҫ�������ڵ���������ʱ�Ĵ���ʽ��
//
//add() �����ڶ�������ʱ���׳��쳣���������н���С�
//offer() �����ڶ�������ʱ�᷵�� false���������޽���л�����Ҫ�ֶ������������������н���С�