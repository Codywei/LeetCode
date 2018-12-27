/**
 Sort������

 �������Ԫ����Ҫʵ�� Java �� Comparable �ӿڣ��ýӿ��� compareTo() �����������������ж�����Ԫ�صĴ�С��ϵ��
 �о������㷨�ĳɱ�ģ��ʱ��������ǱȽϺͽ����Ĵ�����
 ʹ�ø������� less() �� swap() �����бȽϺͽ����Ĳ�����ʹ�ô���Ŀɶ��ԺͿ���ֲ�Ը��á�
 * */
public abstract class Sort<T extends Comparable<T>> {

    public abstract void sort(T[] nums);

    protected boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    protected void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}