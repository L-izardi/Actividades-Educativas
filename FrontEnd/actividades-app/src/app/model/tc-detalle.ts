import { TcTema } from './tc-tema';
export class TcDetalle {
    
    public idCorrelativo: number;
    public tcTema:TcTema;
    public mes:number;
    public subtema:String;
    public fechaDesarrollar:Date;
    public fechaRevision:Date;
    public porcAvanceSemanal:number;
    public porcAvanceMensual:number;
    public estado:String;
}
