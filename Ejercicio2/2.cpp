#include <iostream>
#include <string>
#include <vector>

using namespace std;

class Cliente 
{
public:
    string nombre;
    vector<int> compras;
    string direccion;
    string telefono;

    Cliente(string nombre, string direccion, string telefono):
        nombre(nombre), direccion(direccion), telefono(telefono) {}
};

class Producto 
{
public:
    int codigo;
    string descripcion;
    float precio;
    string proveedor;

    Producto(int codigo, string descripcion, float precio, string proveedor):
        codigo(codigo), descripcion(descripcion), precio(precio), proveedor(proveedor) {}
};

class Venta 
{
public:
    int factura;
    string fecha;
    Cliente* cliente;
    vector<Producto> productosVendidos;

    Venta(int factura, string fecha, Cliente* cliente):
        factura(factura), fecha(fecha), cliente(cliente) {}

    void agregarProducto(const Producto& producto) 
    {
        productosVendidos.push_back(producto);
    }
};

class RegistroPagos 
{
public:
    int factura;
    string fecha;
    float cantidadPagada;

    RegistroPagos(int factura, string fecha, float cantidadPagada):
        factura(factura), fecha(fecha), cantidadPagada(cantidadPagada) {}
};

class Tienda 
{
private:
    vector<Cliente> clientes;
    vector<Producto> productos;
    vector<Venta> ventas;
    vector<RegistroPagos> pagos;

    // Funcion para buscar un cliente por su ID
    Cliente* buscarCliente(int id) 
    {
        for (auto& cliente : clientes) 
        {
            if (id == cliente.compras[0]) 
            {
                return &cliente;
            }
        }
        return nullptr;
    }

    // Funcion para buscar un producto por su codigo
    Producto* buscarProducto(int codigo) 
    {
        for (auto& producto : productos)
         {
            if (codigo == producto.codigo) 
            {
                return &producto;
            }
        }
        return nullptr;
    }

    // Funcion para buscar una venta por su factura
    Venta* buscarVenta(int factura) {
        for (auto& venta : ventas) {
            if (factura == venta.factura) 
            {
                return &venta;
            }
        }
        return nullptr;
    }

public:
    void agregarCliente(const string& nombre, const string& direccion, const string& telefono) 
    {
        clientes.push_back(Cliente(nombre, direccion, telefono));
    }

    void agregarProducto(int codigo, const string& descripcion, float precio, const string& proveedor)
     {
        productos.push_back(Producto(codigo, descripcion, precio, proveedor));
    }

    void agregarVenta(int factura, const string& fecha, int idCliente, const vector<int>& codigosProductos) 
    {
        // Verificar que el cliente exista
        Cliente* cliente = buscarCliente(idCliente);
        if (cliente == nullptr) {
            cout << "El cliente con ID " << idCliente << " no existe." << endl;
            return;
        }

        // Crear una nueva venta
        Venta venta(factura, fecha, cliente);

        // Agregar productos vendidos
        for (int codigo : codigosProductos) 
        {
            Producto* producto = buscarProducto(codigo);
            if (producto != nullptr) 
            {
                venta.agregarProducto(*producto);
            } else 
            {
                cout << "El producto con codigo " << codigo << " no existe." << endl;
            }
        }

        // Guardar la venta
        ventas.push_back(venta);

        // Agregar la venta a la lista de compras del cliente
        cliente->compras.push_back(factura);
    }

    void agregarPago(int factura, const string& fecha, float cantidadPagada) {
        pagos.push_back(RegistroPagos(factura, fecha, cantidadPagada));
    }

    void mostrarClientes() 
    {
        cout << "Clientes:" << endl;
        for (const Cliente& cliente : clientes) 
        {
            cout << "Nombre: " << cliente.nombre << endl;
            cout << "  Direccion: " << cliente.direccion << endl;
            cout << "  Telefono: " << cliente.telefono << endl;
            cout << "  Compras realizadas: ";
            for (int factura : cliente.compras) 
            {
                cout << factura << " ";
            }
            cout << endl;
        }
    }

    void mostrarProductos() 
    {
        cout << "Productos:" << endl;
        for (const Producto& producto : productos) {
            cout << "Codigo: " << producto.codigo << " - Descripcion: " << producto.descripcion << endl;
            cout << "  Precio: $" << producto.precio << endl;
            cout << "  Proveedor: " << producto.proveedor << endl;
        }
    }

    void mostrarVentas() 
    {
        cout << "Ventas:" << endl;
        for (const Venta& venta : ventas) 
        {
            cout << "Factura: " << venta.factura << " - Fecha: " << venta.fecha << endl;
            cout << "  Cliente: " << venta.cliente->nombre << endl;
            cout << "  Productos vendidos:" << endl;
            for (const Producto& producto : venta.productosVendidos) 
            {
                cout << "    Codigo: " << producto.codigo << " - Descripcion: " << producto.descripcion << endl;
                cout << "    Precio: $" << producto.precio << endl;
                cout << "    Proveedor: " << producto.proveedor << endl;
            }
        }
    }

    void mostrarPagos() 
    {
        cout << "Pagos:" << endl;
        for (const RegistroPagos& pago : pagos) 
        {
            cout << "Factura: " << pago.factura << " - Fecha: " << pago.fecha << endl;
            cout << "  Cantidad pagada: $" << pago.cantidadPagada << endl;
        }
    }
};

int main() 
{
    Tienda tienda;

    // Agregar clientes
    tienda.agregarCliente("Juan Perez", "Calle Falsa 123", "123456789");
    tienda.agregarCliente("Maria Garcia", "Avenida Siempreviva 456", "987654321");

    // Agregar productos
    tienda.agregarProducto(101, "Camiseta", 19.99, "Proveedor A");
    tienda.agregarProducto(102, "Pantalones", 29.99, "Proveedor B");

    // Agregar ventas
    tienda.agregarVenta(1, "2024-04-12", 1, {101, 102});

    // Agregar pagos
    tienda.agregarPago(1, "2024-04-12", 49.98);

    // Mostrar informacion
    tienda.mostrarClientes();
    tienda.mostrarProductos();
    tienda.mostrarVentas();
    tienda.mostrarPagos();

    return 0;
}
