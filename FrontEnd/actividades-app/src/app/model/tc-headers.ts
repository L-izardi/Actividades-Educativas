import { TcCatedratico } from './tc-catedratico';
import { TcCurso } from './tc-curso';
import { TcCentro } from './tc-centro';

export class TcHeaders {
    
    public idCorrelativo:number;
    public idCentro:TcCentro;
    public idCurso:TcCurso;
    public idCatedratico:TcCatedratico;
    public semestre:number;
    public seccion:String;
    public horario:String;
    public esetado:String;
        
}
