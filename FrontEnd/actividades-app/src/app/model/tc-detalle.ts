import { TcTema } from './tc-tema';
export class TcDetalle {
    
    public idFacultad:number;
    public idTema:TcTema;
    public mes:number;
    public subTema:String;
    public fechaDesarrollar:Date;
    public fechaRevision:Date;
    public porcAvanceSemanal:number;
    public porcAvanceMensual:number;
    public estado:String;
}
