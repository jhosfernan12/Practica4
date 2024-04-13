class Cliente:
    def __init__(self, nombre, direccion, telefono):
        self.nombre = nombre
        self.direccion = direccion
        self.telefono = telefono
        self.compras = []

class Producto:
    def __init__(self, codigo, descripcion, precio, proveedor):
        self.codigo = codigo
        self.descripcion = descripcion
        self.precio = precio
        self.proveedor = proveedor

class Venta:
    def __init__(self, factura, fecha, cliente):
        self.factura = factura
        self.fecha = fecha
        self.cliente = cliente
        self.productosVendidos = []

    def agregarProducto(self, producto):
        self.productosVendidos.append(producto)

class RegistroPagos:
    def __init__(self, factura, fecha, cantidadPagada):
        self.factura = factura
        self.fecha = fecha
        self.cantidadPagada = cantidadPagada

class Tienda:
    def __init__(self):
        self.clientes = []
        self.productos = []
        self.ventas = []
        self.pagos = []

    def buscarCliente(self, id):
        for cliente in self.clientes:
            if id in cliente.compras:
                return cliente
        return None

    def buscarProducto(self, codigo):
        for producto in self.productos:
            if codigo == producto.codigo:
                return producto
        return None

    def buscarVenta(self, factura):
        for venta in self.ventas:
            if factura == venta.factura:
                return venta
        return None

    def agregarCliente(self, nombre, direccion, telefono):
        self.clientes.append(Cliente(nombre, direccion, telefono))

    def agregarProducto(self, codigo, descripcion, precio, proveedor):
        self.productos.append(Producto(codigo, descripcion, precio, proveedor))

    def agregarVenta(self, factura, fecha, idCliente, codigosProductos):
        cliente = self.buscarCliente(idCliente)
        if cliente is None:
            print(f"El cliente con ID {idCliente} no existe.")
            return

        venta = Venta(factura, fecha, cliente)

        for codigo in codigosProductos:
            producto = self.buscarProducto(codigo)
            if producto is not None:
                venta.agregarProducto(producto)
            else:
                print(f"El producto con código {codigo} no existe.")

        self.ventas.append(venta)
        cliente.compras.append(factura)

    def agregarPago(self, factura, fecha, cantidadPagada):
        self.pagos.append(RegistroPagos(factura, fecha, cantidadPagada))

    def mostrarClientes(self):
        print("Clientes:")
        for cliente in self.clientes:
            print(f"Nombre: {cliente.nombre}")
            print(f"  Dirección: {cliente.direccion}")
            print(f"  Teléfono: {cliente.telefono}")
            print("  Compras realizadas:", " ".join(map(str, cliente.compras)))

    def mostrarProductos(self):
        print("Productos:")
        for producto in self.productos:
            print(f"Código: {producto.codigo} - Descripción: {producto.descripcion}")
            print(f"  Precio: ${producto.precio}")
            print(f"  Proveedor: {producto.proveedor}")

    def mostrarVentas(self):
        print("Ventas:")
        for venta in self.ventas:
            print(f"Factura: {venta.factura} - Fecha: {venta.fecha}")
            print(f"  Cliente: {venta.cliente.nombre}")
            print("  Productos vendidos:")
            for producto in venta.productosVendidos:
                print(f"    Código: {producto.codigo} - Descripción: {producto.descripcion}")
                print(f"    Precio: ${producto.precio}")
                print(f"    Proveedor: {producto.proveedor}")

    def mostrarPagos(self):
        print("Pagos:")
        for pago in self.pagos:
            print(f"Factura: {pago.factura} - Fecha: {pago.fecha}")
            print(f"  Cantidad pagada: ${pago.cantidadPagada}")


tienda = Tienda()

tienda.agregarCliente("Juan Perez", "Calle Falsa 123", "123456789")
tienda.agregarCliente("Maria Garcia", "Avenida Siempreviva 456", "987654321")

tienda.agregarProducto(101, "Camiseta", 19.99, "Proveedor A")
tienda.agregarProducto(102, "Pantalones", 29.99, "Proveedor B")

tienda.agregarVenta(1, "2024-04-12", 1, [101, 102])

tienda.agregarPago(1, "2024-04-12", 100) # He corregido este método para incluir el parámetro de cantidadPagada.

tienda.mostrarClientes()
tienda.mostrarProductos()
tienda.mostrarVentas()
tienda.mostrarPagos()