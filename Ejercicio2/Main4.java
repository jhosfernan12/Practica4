import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Cliente 
{
    private String nombre;
    private String direccion;
    private String telefono;
    private List<Integer> compras;

    public Cliente(String nombre, String direccion, String telefono) 
    {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.compras = new ArrayList<>();
    }

    public String getNombre() 
    {
        return nombre;
    }

    public String getDireccion() 
    {
        return direccion;
    }

    public String getTelefono() 
    {
        return telefono;
    }

    public List<Integer> getCompras() 
    {
        return compras;
    }

    public void addCompra(int factura)
    {
        compras.add(factura);
    }
}

class Producto 
{
    private int codigo;
    private String descripcion;
    private double precio;
    private String proveedor;

    public Producto(int codigo, String descripcion, double precio, String proveedor)
     {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.proveedor = proveedor;
    }

    public int getCodigo() 
    {
        return codigo;
    }

    public String getDescripcion()
     {
        return descripcion;
    }

    public double getPrecio() 
    {
        return precio;
    }

    public String getProveedor() 
    {
        return proveedor;
    }
}

class Venta 
{
    private int factura;
    private String fecha;
    private Cliente cliente;
    private List<Producto> productosVendidos;

    public Venta(int factura, String fecha, Cliente cliente)
     {
        this.factura = factura;
        this.fecha = fecha;
        this.cliente = cliente;
        this.productosVendidos = new ArrayList<>();
    }

    public int getFactura() 
    {
        return factura;
    }

    public String getFecha() 
    {
        return fecha;
    }

    public Cliente getCliente() 
    {
        return cliente;
    }

    public List<Producto> getProductosVendidos() 
    {
        return productosVendidos;
    }

    public void agregarProducto(Producto producto) 
    {
        productosVendidos.add(producto);
    }
}

class RegistroPagos
 {
    private int factura;
    private String fecha;
    private double cantidadPagada;

    public RegistroPagos(int factura, String fecha, double cantidadPagada) 
    {
        this.factura = factura;
        this.fecha = fecha;
        this.cantidadPagada = cantidadPagada;
    }

    public int getFactura() 
    {
        return factura;
    }

    public String getFecha() 
    {
        return fecha;
    }

    public double getCantidadPagada()
     {
        return cantidadPagada;
    }
}

class Tienda 
{
    private List<Cliente> clientes;
    private List<Producto> productos;
    private List<Venta> ventas;
    private List<RegistroPagos> pagos;

    public Tienda() 
    {
        this.clientes = new ArrayList<>();
        this.productos = new ArrayList<>();
        this.ventas = new ArrayList<>();
        this.pagos = new ArrayList<>();
    }

    public Cliente buscarCliente(int id) 
    {
        for (Cliente cliente : clientes) 
        {
            if (cliente.getCompras().contains(id)) 
            {
                return cliente;
            }
        }
        return null;
    }

    public Producto buscarProducto(int codigo) 
    {
        for (Producto producto : productos) 
        {
            if (producto.getCodigo() == codigo) 
            {
                return producto;
            }
        }
        return null;
    }

    public Venta buscarVenta(int factura) 
    {
        for (Venta venta : ventas) 
        {
            if (venta.getFactura() == factura) 
            {
                return venta;
            }
        }
        return null;
    }

    public void agregarCliente(String nombre, String direccion, String telefono) 
    {
        clientes.add(new Cliente(nombre, direccion, telefono));
    }

    public void agregarProducto(int codigo, String descripcion, double precio, String proveedor) 
    {
        productos.add(new Producto(codigo, descripcion, precio, proveedor));
    }

    public void agregarVenta(int factura, String fecha, int idCliente, List<Integer> codigosProductos) 
    {
        Cliente cliente = buscarCliente(idCliente);
        if (cliente == null) {
            System.out.println("El cliente con ID " + idCliente + " no existe.");
            return;
        }

        Venta venta = new Venta(factura, fecha, cliente);

        for (int codigo : codigosProductos) 
        {
            Producto producto = buscarProducto(codigo);
            if (producto != null) 
            {
                venta.agregarProducto(producto);
            } 
            else
            {
                System.out.println("El producto con código " + codigo + " no existe.");
            }
        }

        ventas.add(venta);
        cliente.addCompra(factura);
    }

    public void agregarPago(int factura, String fecha, double cantidadPagada) 
    {
        pagos.add(new RegistroPagos(factura, fecha, cantidadPagada));
    }

    public void mostrarClientes() 
    {
        System.out.println("Clientes:");
        for (Cliente cliente : clientes) {
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("  Dirección: " + cliente.getDireccion());
            System.out.println("  Teléfono: " + cliente.getTelefono());
            System.out.println("  Compras realizadas: " + cliente.getCompras().stream().map(Object::toString).collect(Collectors.joining(" ")));
        }
    }

    public void mostrarProductos() 
    {
        System.out.println("Productos:");
        for (Producto producto : productos) {
            System.out.println("Código: " + producto.getCodigo() + " - Descripción: " + producto.getDescripcion());
            System.out.println("  Precio: $" + producto.getPrecio());
            System.out.println("  Proveedor: " + producto.getProveedor());
        }
    }

    public void mostrarVentas() 
    {
        System.out.println("Ventas:");
        for (Venta venta : ventas) {
            System.out.println("Factura: " + venta.getFactura() + " - Fecha: " + venta.getFecha());
            System.out.println("  Cliente: " + venta.getCliente().getNombre());
            System.out.println("  Productos vendidos:");
            for (Producto producto : venta.getProductosVendidos())
            {
                System.out.println("    Código: " + producto.getCodigo() + " - Descripción: " + producto.getDescripcion());
                System.out.println("    Precio: $" + producto.getPrecio());
                System.out.println("    Proveedor: " + producto.getProveedor());
            }
        }
    }

    public void mostrarPagos() 
    {
        System.out.println("Pagos:");
        for (RegistroPagos pago : pagos) 
        {
            System.out.println("Factura: " + pago.getFactura() + " - Fecha: " + pago.getFecha());
            System.out.println("  Cantidad pagada: $" + pago.getCantidadPagada());
        }
    }

    public static void main(String[] args) 
    {
        Tienda tienda = new Tienda();

        tienda.agregarCliente("Juan Perez", "Calle Falsa 123", "123456789");
        tienda.agregarCliente("Maria Garcia", "Avenida Siempreviva 456", "987654321");

        tienda.agregarProducto(101, "Camiseta", 19.99, "Proveedor A");
        tienda.agregarProducto(102, "Pantalones", 29.99, "Proveedor B");

        tienda.agregarVenta(1, "2024-04-12", 1, List.of(101, 102));

        tienda.agregarPago(1, "2024-04-12", 100);

        tienda.mostrarClientes();
        tienda.mostrarProductos();
        tienda.mostrarVentas();
        tienda.mostrarPagos();
    }
}
