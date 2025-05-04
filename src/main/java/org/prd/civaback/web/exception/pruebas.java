package org.prd.civaback.web.exception;

public class pruebas {
    public static void main(String[] args) {
        //Pruebas de codigo pra hacer debug
        System.out.println("Hola");
        prueba();
        int  a = 1;
        int  b = 2;
        int sum = suma(a, b);
        System.out.println("Resultado de la suma: " + sum);

        //Pruebas de ordenamiento burbuja
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Array original:");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        burbuja(arr);
        System.out.println("Array ordenado:");
        for (int i : arr) {
            System.out.print(i + " ");
        }


    }

    public static void prueba() {
        //Pruebas con debug
        System.out.println("Hola");
    }

    public static int suma(int a, int b) {
        //Pruebas con debug
        return a + b;
    }
    //algoritmo
    //algoritmo de ordenamiento burbuja
    public static void burbuja(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

}