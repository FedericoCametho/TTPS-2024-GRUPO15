export class PlatoDelDia {
    private nombre: string;
    private descripcion: string;
    private precio: number;
    private imagen: string;
    private dia: string;

    constructor (nombre: string, descripcion: string, precio: number, imagen: string, dia: string){
        this.nombre=  nombre;
        this.descripcion= descripcion;
        this.precio= precio;
        this.imagen= imagen;
        this.dia= dia;
    }

    public getNombre(): string {
        return this.nombre;
    }

    public getDescripcion(): string {
        return this.descripcion;
    }

    public getPrecio(): number {
        return this.precio;
    }

    public getImagen(): string {
        return this.imagen;
    }

    public getDia(): string {
        return this.dia;
    }
}
