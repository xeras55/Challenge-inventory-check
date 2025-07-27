import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // Tu código comienza aquí
        System.out.println("Reto: Comparación de inventario entre almacenes");

        // Crear productos de ejemplo
        Producto producto1 = new Producto("001", "Camiseta", "Ropa", 25.0, 50);
        Producto producto2 = new Producto("002", "Pantalón", "Ropa", 45.0, 30);
        Producto producto3 = new Producto("003", "Zapatos", "Calzado", 80.0, 20);
        Producto producto4 = new Producto("004", "Tenis", "Calzado", 25.0, 124);
        Producto producto5 = new Producto("005", "Calcetas", "Ropa", 10.0, 100);
        Producto producto6 = new Producto("006", "Boxers", "Ropa", 15.0, 10);
        Producto producto7 = new Producto("007", "Playera", "Ropa", 30.0, 20);
        Producto producto8 = new Producto("008", "Gorra", "Accesorios", 15.0, 30);

        // Crear almacén 1 y agregar productos
        Almacen almacen1 = new Almacen("Almacén Norte");
        almacen1.agregarProducto(producto1);
        almacen1.agregarProducto(producto2);
        almacen1.agregarProducto(producto3);
        almacen1.agregarProducto(producto4);
        almacen1.agregarProducto(producto5);
        almacen1.agregarProducto(producto6);
        almacen1.agregarProducto(producto8);

        // Crear almacén 2 y agregar productos (con algunas diferencias)
        Almacen almacen2 = new Almacen("Almacén Sur");
        almacen2.agregarProducto(producto1);
        almacen2.agregarProducto(new Producto("002", "Pantalón", "Ropa", 45.0, 20)); // Diferente stock
        almacen2.agregarProducto(new Producto("003", "Zapatos", "Calzado", 80.0, 100));// Diferente stock
        almacen2.agregarProducto(new Producto("005", "Calcetas", "Ropa", 10.0, 50));// Diferente stock
        almacen2.agregarProducto(new Producto("004", "Tenis", "Calzado", 25.0, 19));
        almacen2.agregarProducto(producto6); 
        almacen2.agregarProducto(producto7); // Producto diferente

        // Comparar los almacenes
        System.out.println("\nComparación de inventario:");
        almacen1.compararInventario(almacen2);
    }
}

class Producto {
    private String codigo;
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;

    public Producto(String codigo, String nombre, String categoria, double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    // Setters
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Producto producto = (Producto) obj;
        return codigo.equals(producto.codigo);
    }

}


class Almacen {
    private String nombre;
    private List<Producto> productos;
    //private Stream<Producto> streamProductos = productos.stream();
    

    public Almacen(String nombre) {
        this.nombre = nombre;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void compararInventario(Almacen otroAlmacen) {
        System.out.println("Comparando " + this.nombre + " con " + otroAlmacen.nombre + ":");
        // TODO: Implementar la lógica para comparar los inventarios de los dos almacenes
        // 1. Iterar sobre los productos del primer almacén (this.productos)
        // 2. Para cada producto, buscar si existe en el otro almacén (otroAlmacen.productos)
        // 3. Si el producto existe en ambos almacenes, comparar el stock
        // 4. Si el stock es diferente, imprimir un mensaje indicando la diferencia
        // 5. Si el producto no existe en el otro almacén, imprimir un mensaje indicando que no se encuentra
        // 6. Repetir el proceso para los productos que están en el otro almacén pero no en el primero
        productos.stream()
            .flatMap(p1 -> {
        List<Producto> iguales = otroAlmacen.productos.stream()
            .filter(p2 -> p1.equals(p2))
            .toList();
        if (iguales.isEmpty()) {
            return Stream.of("Producto " + p1.getCodigo() + ": No existe en " + otroAlmacen.nombre);
        }
        return iguales.stream().map(p2 -> {
            int stockAlmacenPrincipal = p1.getStock();
            int stockALmacenSecundario = p2.getStock();
            if (stockAlmacenPrincipal > stockALmacenSecundario) {
                int res = stockAlmacenPrincipal - stockALmacenSecundario;
                return "Producto " + p1.getCodigo() + ": Piezas en " + nombre + " " + stockAlmacenPrincipal + "," + " piezas en " + otroAlmacen.nombre + " " + stockALmacenSecundario + " diferencia de: " + res;
            } else if(stockALmacenSecundario > stockAlmacenPrincipal){
                int res = stockALmacenSecundario - stockAlmacenPrincipal;
                return "Producto " + p2.getCodigo() + ": Piezas en " + otroAlmacen.nombre + " " + stockALmacenSecundario + "," + " piezas en " + nombre + " " + stockAlmacenPrincipal + " diferencia de: " + res;
            } else
            return "Producto " + p1.getCodigo() + ": No hay diferencia de stock";
        });
    })
    .forEach(System.out::println);
    otroAlmacen.productos.stream()
                .filter(p2 -> productos.stream().noneMatch(p1 -> p1.equals(p2)))
                .map(p2 -> "Producto " + p2.getCodigo() + ": No existe en " + nombre)
                .forEach(System.out::println);
    }
}