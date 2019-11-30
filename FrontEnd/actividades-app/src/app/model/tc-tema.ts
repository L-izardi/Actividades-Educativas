import { TcHeaders } from './tc-headers';
import { TcDetalle } from './tc-detalle';

export class TcTema {

    public idTema:number;
    public nombre:String;
    public estado:String;
    public tcHeaders = TcHeaders;
    public detalle = TcDetalle;
}
