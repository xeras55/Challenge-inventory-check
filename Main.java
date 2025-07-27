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
        Producto producto4 = new Producto("004", "Gorra", "Accesorios", 15.0, 100);
        Producto producto5 = new Producto("005", "Gorra", "Accesorios", 15.0, 100);
        Producto producto6 = new Producto("006", "Gorra", "Accesorios", 15.0, 0);
        Producto producto7 = new Producto("007", "Gorra", "Accesorios", 15.0, 0);

        // Crear almacén 1 y agregar productos
        Almacen almacen1 = new Almacen("Almacén Norte");
        almacen1.agregarProducto(producto1);
        almacen1.agregarProducto(producto2);
        almacen1.agregarProducto(producto3);
        almacen1.agregarProducto(producto5);
        almacen1.agregarProducto(producto6);

        // Crear almacén 2 y agregar productos (con algunas diferencias)
        Almacen almacen2 = new Almacen("Almacén Sur");
        almacen2.agregarProducto(producto1);
        almacen2.agregarProducto(new Producto("002", "Pantalón", "Ropa", 45.0, 20)); // Diferente stock
        almacen2.agregarProducto(producto4); // Producto diferente
        almacen2.agregarProducto(producto6);
        almacen2.agregarProducto(producto7);

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
        /*productos.stream()
        .forEach(p1 -> {
            boolean existe = otroAlmacen.productos.stream()
            .anyMatch(p2 -> p1.equals(p2));
        if (existe) {
            otroAlmacen.productos.stream()
            .filter(p2 -> p1.equals(p2))
            .map(p2 -> {
                int mayor = Math.max(p1.getStock(), p2.getStock());
                int menor = Math.max(p1.getStock(), p2.getStock());
                if (mayor != menor) {
                    int res = mayor - menor;
                    return "Diferencia de Stock: " + res; 
                }else {
                    return "No hay diferencia de Stock";
                }
            }).forEach(System.out::print);
        }else{
            System.out.println("No exitste en :" + otroAlmacen.nombre);
        }
        });*/
        
        
        
        /*productos.stream()
                .flatMap(p1 ->
                    otroAlmacen.productos.stream()
                        .filter(p2 -> p1.equals(p2))
                        .map(p2 -> {
                        int stock1 = p1.getStock();
                        int stock2 = p2.getStock();
                        int mayor = Math.max(stock1, stock2);
                        int menor = Math.min(stock1, stock2);
                        int diferencia = mayor - menor;
                        if (diferencia != 0) {
                            return "Producto " + p1.getCodigo() + ": Diferencia de stock = " + diferencia;
                        } else {
                            return "Producto " + p1.getCodigo() + ": No hay diferencia de stock";
                    }
                    })
                    
                    )
                    .forEach(System.out::println);*/

        /*for (Producto producto : productos) {
            for (Producto producto2 : otroAlmacen.productos) {
                //System.out.println(producto.equals(producto2));
                if (producto.equals(producto2)) {
                    //System.out.println(producto.equals(producto2));
                    //System.out.println(producto.getCodigo() + " " + producto2.getCodigo());
                    
                    int mayor = Math.max(producto.getStock(), producto2.getStock());
                    int menor = Math.min(producto.getStock(), producto2.getStock());
                    if (mayor != menor) {
                        int res = mayor - menor;
                        System.out.println("Diferencia de stock: " + res);
                    }else{
                        System.out.println("No hay diferencia de Stock");
                    }
                    
                }else {
                //System.out.println(false);
                System.out.println("No existe en: " + otroAlmacen.nombre);
                }
            }
        }*/
        productos.stream()
            .flatMap(p1 -> {
        // Filtramos productos del otro almacén que son iguales a p1
        List<Producto> iguales = otroAlmacen.productos.stream()
            .filter(p2 -> p1.equals(p2))
            .toList();
        // Si no hay iguales, devolvemos mensaje de que no existe en el otro almacén
        if (iguales.isEmpty()) {
            return Stream.of("Producto " + p1.getCodigo() + ": no existe en " + otroAlmacen.nombre);
        }
        // Si hay coincidencias, comparamos stock
        return iguales.stream().map(p2 -> {
            int stock1 = p1.getStock();
            int stock2 = p2.getStock();
            int diferencia = Math.abs(stock1 - stock2);
            if (diferencia != 0) {
                return "Producto " + p1.getCodigo() + ": Diferencia de stock = " + diferencia;
            } else {
                return "Producto " + p1.getCodigo() + ": No hay diferencia de stock";
            }
        });
    })
    .forEach(System.out::println);

    otroAlmacen.productos.stream()
                .filter(p2 -> productos.stream().noneMatch(p1 -> p1.equals(p2)))
                .map(p2 -> "Producto " + p2.getCodigo() + ": no existe en: " + nombre)
                .forEach(System.out::println);
    }
}