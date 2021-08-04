
/**
 * @author Daniel Lugo Cano
 * ANALISIS DE ALGORITMOS
 * FACULTAD DE CIENCIAS - UNAM
 * PROGRAMA 3: EXPONENTIAL SEARCH
 */

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

import java.io.FileWriter;
import java.io.IOException;

public class ES {

    // Usamos la misma instancia de Random para todo el programa
    // la cual llamamos randum
    static Random randum;

    /**
     * Método que construye un arreglo con elementos aleatorios ordenados
     * 
     * @param k numero introducido por el usuario que se multiplicará por 50 para
     *          dar como resultado el tamaño del arreglo
     * @return array un arreglo construido con números positivos (naturales) de
     *         tamaño k*50
     */
    public static int[] createArray(int k) {
        randum = new Random();
        int[] array = new int[k * 50];
        int arrayElem = Math.abs(randum.nextInt());
        array[0] = arrayElem;
        for (int i = 1; i < array.length; i++) {
            array[i] = arrayElem + i;
        }
        return array;
    }

    /**
     * Método para escribir el arreglo en un archivo llamado array.txt
     * 
     * @param array el arreglo a escribir en el archivo
     */
    public static void writeFile(String array) {

        try {
            FileWriter myWriter = new FileWriter("array.txt");
            myWriter.write(array);
            myWriter.close();
            System.out.println("Arreglo creado correctamente en array.txt");
        } catch (IOException e) {
            System.out.println("Ocurrio un ERROR");
            e.printStackTrace();
        }

    }

    /**
     * Método que implementa el algoritmo de búsqueda exponencial
     * 
     * @param array un arreglo de tipo int
     * @param n     el tamaño del arreglo
     * @param key   la llave a buscar en el arreglo
     * @return si la llave SI esta en el arreglo, regrersa el indice del elemento
     * @return si la llave NO esta en el arreglo, regresa el tamaño del arreglo con
     *         signo negativo
     */
    public static int exponentialSearch(int[] array, int n, int key) {
        // Si la llave esta en el primer indice del arreglo regresamos 0 pues es la
        // primer posicion
        if (array[0] == key)
            return 0;

        // Buscamos el intervalo en el que se encuentra la llave, cada intervalo es de
        // la forma de potencias de 2.
        int i = 1;
        while (i < n && array[i] <= key)
            i = i * 2;

        /**
         * Funcion de Java que implementa una busqueda binaria en un arreglo ordenado
         * 
         * @param array       es el arreglo donde se ejecutará la busqueda binaria
         * @param i           / 2 indice que funciona como cota inferior de la busqueda
         * @param Math.min(i, n - 1) indice que funciona como cota superior de la
         *                    busqueda
         * @param key         llave a ser buscada en el arreglo
         */
        return Arrays.binarySearch(array, i / 2, Math.min(i, n - 1), key);
    }

    /**
     * Método que genera 2 llaves, una que si se encuentre en el arreglo y otra que
     * no, y que además, en caso de que si se encuentre, esta en el último intervalo
     * de busqueda para así generar el peor caso.
     * 
     * @param array el arreglo de donde tomaremos la llave
     * @param k     numero introducido por el usuario que se multiplicará por 50
     *              para dar como resultado el tamaño del arreglo
     * @return una llave a ser buscada en el arrelo
     */
    public static int keyGenerator(int[] array, int k) {
        int general_bound = k * 50;
        randum = new Random();

        // key1 es la llave que es seguro que se encuentre en el arreglo y en el
        // ultimo itervalo
        // lo que asegura sea el peor caso posible
        int key1 = array[general_bound - 20] + randum.nextInt(array[array.length - 1] - array[general_bound - 20] + 1);

        // Defino cota superior y cota inferior para generar una segunda llave
        // pseudoaleatoria
        int lowerbound = array[array.length - 10];
        int upperbound = array[array.length - 1] + 150;

        // key2 es una llave que tiene mucha más probabilidad de no estar en el
        // arreglo, pero además aseguramos que
        // en caso de que si se cnuentre, este en el ultimo intervalo para asegurar sea
        // el peor caso posible
        int key2 = randum.nextInt(upperbound - lowerbound) + lowerbound;

        // Quitar comentarios de estas lineas si se desea ver exactamente cuáles son las
        // cotas de indices y las dos posibles llaves a buscar

        // System.out.println("UPPERBOUND: " + upperbound);
        // System.out.println("LOWERBOUND: " + lowerbound);
        // System.out.println("LLAVE 1: " + key1);
        // System.out.println("LLAVE 2: " + key2);

        // Regresamos una de las 2 llaves generadas, así tenemos 50% de probabilidad de
        // que se encuentre y
        // 50% de que no se encuentre.
        return randum.nextBoolean() ? key1 : key2;

    }

    /**
     * Metodo manejador para probar el código
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce un número entre 1 y 20");

        int k = sc.nextInt();
        int[] array = createArray(k);
        int key = keyGenerator(array, k);
        int resultado = (exponentialSearch(array, array.length, key));

        writeFile(Arrays.toString(array));

        // Para ver a detalle las variables que se toman para ejecutar Exponential
        // Search

        // System.out.println(Array.toString(array));
        // System.out.println("Array length: " + array.length);
        // System.out.println("La llave es: " + key);

        // Si el resultado es un valor negativo entonces la llave no fue encontrada
        // System.out.println("Llave encontrada en el indice: " + resultado);

        Object n = null;

        if (resultado < 0) {
            System.out.println(n);
        } else {
            System.out.println(key);
        }
    }
}