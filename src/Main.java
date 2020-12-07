public class Main {

    public static void main(String[] args) {
        byte[] arr = {1, 0, 0, 1, 1, 0, 0, 1};
        int[] array;
        int[] sbox;
        byte check=1;
        ByteVector set = new ByteVector(arr);
        array = set.calculatePolynomial();
        for (int i = 0; i < 8; i++)
            System.out.println(array[i]);
        System.out.println(set.getPolynomialValue());
        System.out.print("[");
        for (int i = 0; i < 256; i++) {

            System.out.print(",");
        }
        System.out.print("]");
    }
}