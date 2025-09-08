public class Main2 {

        public static void main(String[] args) {
            Calculadora<Integer> calc1 = new Calculadora<>(10);
            System.out.println(calc1.getDouble());

            Calculadora<Double> calc2 = new Calculadora<>(5.5);
            System.out.println(calc2.getDouble());

            /// NO seria posible definirla como String ni otros tipos no numericos

        }
    }
/// CLASE GENERICA QUE SOLO ACEPTA TIPOS QUE HEREDAN DE NUMBER
    class Calculadora<T extends Number>{
        private T numero;

        public Calculadora(T numero) {
            this.numero = numero;
        }

        public double getDouble() {
            return numero.doubleValue();
        }
    }

