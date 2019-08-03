import {Cliente} from './cliente';

export class Endereco {
  id: number;
  cliente: Cliente;
  logradouro: string;
  numero: string;
  cep: string;
  bairro: string;
  cidade: string;
  estado: string;
  complemento: string;
  referencia: string;

}
