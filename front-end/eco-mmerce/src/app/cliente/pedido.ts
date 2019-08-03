import {Cliente} from './cliente';

export class Pedido {
  id: number;
  precoTotal: number;
  frte: number;
  numeroPedido: string;
  idProduto: any;
  cliente: Cliente;

}
